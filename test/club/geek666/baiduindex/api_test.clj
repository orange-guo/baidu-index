(ns club.geek666.baiduindex.api-test
    (:require [clojure.test :refer :all])
    (:require [club.geek666.baiduindex.api :refer [gen-index-filter]]))

;(deftest gen-filter-test
;    (testing "TEST gen-filter"
;        (is (= (gen-index-filter "4396") [{"name" "4396" "wordType" 1}]))))

(deftest gen-index-filter-test
    (testing (is (= '() '()))))