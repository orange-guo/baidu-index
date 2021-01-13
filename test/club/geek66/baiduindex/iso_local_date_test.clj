(ns club.geek66.baiduindex.iso-local-date-test
    (:require [clojure.test :refer :all])
    (:require [club.geek66.baiduindex.iso-local-date :refer :all])
    (:import (java.time LocalDate)))

(deftest test-parse-format
    (testing "parse"
        (is (= 2 (. (parse-date "2012-02-02") getDayOfMonth))))
    (testing "format"
        (is (= "2012-02-03" (format-date (LocalDate/of 2012 2 3))))))

(deftest local-date-range-test
    (testing "get second index"
        (is (= "2012-01-02" (-> (date-range "2012-01-01") (nth 1)))))
    (testing "count range 2012-01-01 ... 2012-01-03"
        (is (= 2 (count (date-range "2012-01-01" "2012-01-03"))))))

(deftest plus-days-test
    (testing "test 2012-01-03 + 4 should be 2012-01-07"
        (is (= "2012-01-07" (plus-days "2012-01-03" 4)))))