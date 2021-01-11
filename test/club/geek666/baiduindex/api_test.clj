(ns club.geek666.baiduindex.api-test
    (:require [clojure.test :refer :all])
    (:require [club.geek666.baiduindex.api :refer :all]
              [clojure.data.json :as json]
              [club.geek666.baiduindex.app :as app])
    (:import (java.util Map)))

;(deftest gen-keyword-filter-test
;    (-> (= [{"name" "鸡你太美" "wordType" 1}] (gen-keyword-filter "鸡你太美")) is testing))

(def expect-params-result {"area" 0 "word" "[[{\"name\":\"Jack\",\"wordType\":1}],[{\"name\":\"Tom\",\"wordType\":1}]]" "startDate" "2020-12-07" "endDate" "2020-12-08"})
(deftest query-params-test
    (-> (= expect-params-result (query-params {:keywords   ["Jack" "Tom"]
                                               :start-date "2020-12-07"
                                               :end-date   "2020-12-08"})) is testing))

(deftest get-for-idx-test
    (testing
        (is
            (= "not login"
                (-> (get-for-idx {:baidu-uss "xxx" :keywords ["Jack" "Tom"]})
                    :body
                    json/read-str
                    (get "message"))))))

(deftest search-index-test
    (->
        (= nil
            (-> (search-index {:baidu-uss (app/baidu-uss) :keywords ["鸡你太美"]})
                :indexes (first) (get-in ["all" "data"])))
        (is) (testing)))