(ns club.geek666.baiduindex.ptbk
    (:require [clojure.string :as clj-str]
              [clojure.data.json :as json]
              [clojure.string :as str :only (join)])
    (:gen-class))

(defn split-in-half [coll]
    (partition (/ (count coll) 2) coll))

; (into [] (last two-part))
(defn ptbk-decode [ptbk data]
    (let [two-halves (split-in-half (vec ptbk))
          secret-map (zipmap (first two-halves) (last two-halves))]
        (->> data (map #(get secret-map %1)) (clj-str/join ""))))