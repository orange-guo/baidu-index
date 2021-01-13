(ns club.geek66.baiduindex.iso-local-date
    (:import (java.time.format DateTimeFormatter)
             (java.time LocalDate))
    (:gen-class))

; date str to Java LocalDate
(defn parse-date [date-str] (. LocalDate parse date-str DateTimeFormatter/ISO_LOCAL_DATE))
; Java LocalDate to formatted iso local date str
(defn format-date [date] (. DateTimeFormatter/ISO_LOCAL_DATE format date))

(defn date-iterate [identity] (iterate #(. % plusDays 1) identity))

(defn plus-days [date-str days] (-> (. (parse-date date-str) plusDays days) format-date))

(defn date-range
    ([start] (->> (parse-date start) date-iterate (map format-date)))
    ([start end] (->>
                     (date-range start)
                     (take-while #(> 0 (compare (parse-date %) (parse-date end)))))))