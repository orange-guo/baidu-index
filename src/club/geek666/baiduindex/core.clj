(ns club.geek666.baiduindex.core
    (:require [clj-http.client :as client]
              [clojure.string :as str :only (join)]
              [clojure.data.json :as json]))

; area=0&word=[[{"name":"迪奥","wordType":1}]]&days=30
; cookie must contains BDUSS

(def baidu-uss "ndyWnQxQkpqaS1LZGZDNktQb1BORE9VTUhXaUhyNXRRT2JUc01CMFFweX5JQnBnSVFBQUFBJCQAAAAAAAAAAAEAAAC-5SM2yta7-sbBu7XBywAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAL-T8l-~k~JfM")

(def with-bduss ["BDUSS" baidu-uss])

(defn search-index [keyword]
    (-> (client/get
            "https://index.baidu.com/api/SearchApi/index"
            {:headers      {"User-Agent" "Mozilla/5.0 (X11; Linux x86_64)",
                            "Cookie"     (str/join "=" with-bduss)}
             :query-params {"area" 0,
                            "word" (str "[[{\"name\":\"" keyword "\",\"wordType\":1}]]"),
                            "days" 30}})
        :body
        json/read-str))

(defn exchange-ptbk [unique-id]
    (-> (client/get
            "http://index.baidu.com/Interface/ptbk"
            {:headers      {"User-Agent" "Mozilla/5.0 (X11; Linux x86_64)",
                            "Cookie"     (str/join "=" with-bduss)}
             :query-params {"uniqid" unique-id}}) :body json/read-str))

(defn ptbk-decode [ptbk data]
    (let [values-with-keys (partition (/ (count ptbk) 2) (vec ptbk))
          kvs (zipmap (into [] (first values-with-keys)) (into [] (last values-with-keys)))]
        (map #(get kvs %1) data)))

(defn get-keyword [keyword]
    (let [body (search-index keyword) ptbk (get-in body ["data" "uniqid"])]
        (println ptbk)))

(defn -main []
    (println (ptbk-decode
                 "xZBKuD9vhL7mGyX7-8%4392.+,6150" "x9ux7x9yD7xxxD7xxXD7BxGX7ByBx7x9yy7Bvvv7BDmm7x9um7BGmy7BDBu7x9uy7xx9G7xmmX7xxvm7xBXy7xBmu7BGxy7BBBx79vmD7B9B979D9X7GXXBD799mx7Bxm97BvGD7xxux7xuuv7xGXy7mmXG7m9X97xDuB7xvDx")))