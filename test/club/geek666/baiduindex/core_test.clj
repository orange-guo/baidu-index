(ns club.geek666.baiduindex.core-test
	(:require [clojure.test :refer :all])
	(:require [club.geek666.baiduindex.core :refer [iterate-days local-date-range]])
	(:import (java.time ZonedDateTime)
			 (java.time.format DateTimeFormatter)))

(deftest iterate-days-test
	(-> (= 5 (->> (iterate-days (ZonedDateTime/now)) (take 5) count)) is testing))


(deftest local-date-range-test
	(-> (= (. DateTimeFormatter/ISO_LOCAL_DATE format (ZonedDateTime/now))
		   (-> (local-date-range (ZonedDateTime/now)) (first)))
		is testing))