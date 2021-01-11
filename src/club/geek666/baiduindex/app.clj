(ns club.geek666.baiduindex.app
    (:gen-class))

;(require '[clj-yaml.core :as yml])
;(require '[clojure.java.io :as io])
;
;(defn parse-class-path-yml [class-path]
;    (memoize #(-> class-path io/resource slurp yml/parse-string)))
;
;(def read-app-cfg (parse-class-path-yml "application.yml"))

(def baidu-uss (memoize #(System/getenv "BAIDU_USS")))

(def keywords (memoize #(->> (clojure.string/split (System/getenv "KEYWORDS") #",")
                            (map (fn [keyword] (clojure.string/trim keyword))))))

(def start-date (memoize #(System/getenv "START_DATE")))
(def end-date (memoize #(System/getenv "END_DATE")))