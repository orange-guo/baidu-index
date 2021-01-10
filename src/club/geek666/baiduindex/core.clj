(ns club.geek666.baiduindex.core
	(:gen-class)
	(:import (java.time.format DateTimeFormatter)))

(defn iterate-days [start-day] (iterate #(. % plusDays 1) start-day))

(defn local-date-range [start-day] (->> (iterate-days start-day) (map #(. % format DateTimeFormatter/ISO_LOCAL_DATE))))