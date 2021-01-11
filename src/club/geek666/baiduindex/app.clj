(ns club.geek666.baiduindex.app
    (:gen-class))

;(require '[clj-yaml.core :as yml])
;(require '[clojure.java.io :as io])
;
;(defn parse-class-path-yml [class-path]
;    (memoize #(-> class-path io/resource slurp yml/parse-string)))
;
;(def read-app-cfg (parse-class-path-yml "application.yml"))

(def baidu-uss (System/getenv "BAIDU_USS"))

(def keywords (->> (clojure.string/split (System/getenv "KEYWORDS") #",") (map #(clojure.string/trim %)) (into '())))

(def start-date (System/getenv "START_DATE"))
(def end-date (System/getenv "END_DATE"))