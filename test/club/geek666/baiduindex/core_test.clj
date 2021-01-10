(ns club.geek666.baiduindex.core-test
	(:require [clojure.test :refer :all])
	(:require [club.geek666.baiduindex.core :refer [date-iterate local-date-range]]))

(deftest local-date-range-test
	(testing
		(is (= "2012-01-02" (-> (local-date-range "2012-01-01") (nth 1)))))
	(testing
		(is (= 2 (count (local-date-range "2012-01-01" "2012-01-03"))))))

