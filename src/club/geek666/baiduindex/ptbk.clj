(ns club.geek666.baiduindex.ptbk
    (:require [clojure.string :as clojure-str]))


(defn split-in-half [coll]
    (partition (/ (count coll) 2) coll))

; (into [] (last two-part))
(defn ptbk-decode [ptbk data]
    (let [two-halves (partition (/ (count ptbk) 2) (vec ptbk))
          secret-map (zipmap (first two-halves) (last two-halves))]
        (->> (map #(get secret-map %1) data) (clojure-str/join ""))))