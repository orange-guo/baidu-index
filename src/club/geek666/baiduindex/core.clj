(ns club.geek666.baiduindex.core
	(:gen-class)
	(:import (java.time.format DateTimeFormatter)
			 (java.time LocalDate)))

(defn parse-iso-date [date-str] (. LocalDate parse date-str DateTimeFormatter/ISO_LOCAL_DATE))
(defn format-iso-date [date] (. DateTimeFormatter/ISO_LOCAL_DATE format date))

(defn date-iterate [identity] (iterate #(. % plusDays 1) identity))

(defn local-date-range
	([start-date] (->> (parse-iso-date start-date) date-iterate (map format-iso-date)))
	([start-date end-date] (->>
							   (local-date-range start-date)
							   (take-while #(> 0 (compare (parse-iso-date %) (parse-iso-date end-date)))))))