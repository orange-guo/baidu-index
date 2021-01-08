(ns club.geek666.baiduindex.api-test
    (:require [clojure.test :refer :all])
    (:require [club.geek666.baiduindex.api :refer :all]
              [clojure.data.json :as json]))

(deftest gen-keyword-filter-test
    (-> (= [{"name" "鸡你太美" "wordType" 1}] (gen-keyword-filter "鸡你太美")) is testing))

(def expect-params-result {"area" 0 "word" "[[{\"name\":\"Jack\",\"wordType\":1}],[{\"name\":\"Tom\",\"wordType\":1}]]" "startDate" "2020-12-07" "endDate" "2020-12-08"})
(deftest query-params-test
    (-> (= expect-params-result (query-params ["Jack" "Tom"] "2020-12-07" "2020-12-08")) is testing))

(deftest get-for-idx-test
    (testing
        (is (= "not login" (-> (get-for-idx "xxx" ["Jack" "Tom"]) :body json/read-str (get "message"))))))

(require '[club.geek666.baiduindex.app :as app])
(deftest search-index-test
    (-> (instance? String (-> (search-index (app/baidu-uss) ["鸡你太美"]) :indexes (first) (get-in ["all" "data"]))) (is) (testing)))