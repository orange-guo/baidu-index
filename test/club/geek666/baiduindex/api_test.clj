(ns club.geek666.baiduindex.api-test
    (:require [clojure.test :refer :all])
    (:require [club.geek666.baiduindex.api :refer :all]
              [clojure.data.json :as json]))

(deftest gen-keyword-filter-test
    (-> (= [{"name" "鸡你太美" "wordType" 1}] (gen-keyword-filter "鸡你太美")) is testing))

(deftest query-params-test
    (testing
        (is (= {"area" 0
                "word" "[[{\"name\":\"Jack\",\"wordType\":1}],[{\"name\":\"Tom\",\"wordType\":1}]]"
                "days" 30} (query-params ["Jack" "Tom"])))))

(deftest req-for-idx-test
    (testing
        (is (= "not login" (-> (req-for-idx "xxx" ["Jack" "Tom"]) :body json/read-str (get "message"))))))

(deftest baidu-uss-tmp "UNsNjd2bVFQWnBkUVFFU2VydWNhR2pEYnVMMFpiaWdMUmN4cjdHNVZOSDNwaHBnSVFBQUFBJCQAAAAAAAAAAAEAAAC-5SM2yta7-sbBu7XBywAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPcZ81~3GfNfS")
(deftest search-index-test
    (-> (instance? Number (-> (search-index baidu-uss-tmp ["鸡你太美"]) (get "status"))) is testing))