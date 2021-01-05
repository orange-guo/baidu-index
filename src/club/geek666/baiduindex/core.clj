(ns club.geek666.baiduindex.core
    (:require [clj-http.client :as client]
              [clojure.string :as str :only (join)]
              [clojure.data.json :as json])
    (:import java.lang.Integer)
    (:gen-class))

; area=0&word=[[{"name":"迪奥","wordType":1}]]&days=30

(def baidu-uss "UNsNjd2bVFQWnBkUVFFU2VydWNhR2pEYnVMMFpiaWdMUmN4cjdHNVZOSDNwaHBnSVFBQUFBJCQAAAAAAAAAAAEAAAC-5SM2yta7-sbBu7XBywAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPcZ81~3GfNfS")

(def with-bduss ["BDUSS" baidu-uss])

(defn search-index [keyword]
    (get (-> (client/get
                 "https://index.baidu.com/api/SearchApi/index"
                 {:headers      {"User-Agent" "Mozilla/5.0 (X11; Linux x86_64)",
                                 "Cookie"     (str/join "=" with-bduss)}
                  :query-params {"area" 0,
                                 "word" (str "[[{\"name\":\"" keyword "\",\"wordType\":1}]]"),
                                 "days" 30}})
             :body
             json/read-str) "data"))

(defn exchange-ptbk [unique-id]
    (get (-> (client/get
                 "http://index.baidu.com/Interface/ptbk"
                 {:headers      {"User-Agent" "Mozilla/5.0 (X11; Linux x86_64)",
                                 "Cookie"     (str/join "=" with-bduss)}
                  :query-params {"uniqid" unique-id}}) :body json/read-str) "data"))

(defn ptbk-decode [ptbk data]
    (let [values-with-keys (partition (/ (count ptbk) 2) (vec ptbk))
          kvs (zipmap (into [] (first values-with-keys)) (into [] (last values-with-keys)))]
        (map #(get kvs %1) data)))

(defn get-keyword [keyword]
    (let [body (search-index keyword)
          data (get-in (first (get-in body ["userIndexes"])) ["all" "data"])
          ptbk (-> (get-in body ["uniqid"]) exchange-ptbk)]
        (ptbk-decode ptbk data)))

(defn -main []
    (let [index (get-keyword "鸡你太美")]
        (println (map #(Integer/parseInt %) (clojure.string/split (clojure.string/join "" (into [] index)) #",")))))