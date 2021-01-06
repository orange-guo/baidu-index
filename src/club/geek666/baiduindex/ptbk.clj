(ns club.geek666.baiduindex.ptbk
    (:require [clojure.string :as clj-str]))

(defn split-in-half [coll]
    (partition (/ (count coll) 2) coll))

; (into [] (last two-part))
(defn ptbk-decode [ptbk data]
    (let [two-halves (split-in-half (vec ptbk))
          secret-map (zipmap (first two-halves) (last two-halves))]
        (->> (map #(get secret-map %1) data) (clj-str/join ""))))