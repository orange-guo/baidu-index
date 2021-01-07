(ns club.geek666.baiduindex.app
    (:gen-class))

(require '[clj-yaml.core :as yml])
(require '[clojure.java.io :as io])

(defn parse-class-path-yml [class-path]
    (memoize #(-> class-path io/resource slurp yml/parse-string)))

(def read-app-cfg (parse-class-path-yml "application.yml"))

(defn baidu-uss []
    (-> (read-app-cfg) :baidu-uss))