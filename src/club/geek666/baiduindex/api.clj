(ns club.geek666.baiduindex.api
    (:gen-class)
    (:require [clj-http.client :as client]
              [clojure.data.json :as json]
              [clojure.string :as str]))

(defn header-with-auth [baidu-uss] {"User-Agent" "Mozilla/5.0 (X11; Linux x86_64)"
                                    "Cookie"     (str/join "=" ["BDUSS" baidu-uss])})

(defn query-params [req]
    {"area"      0,
     "word"      (json/write-str (doall (map (fn [keyword] [{"name" keyword, "wordType" 1}]) (:keywords req)))),
     "startDate" (:start-date req)
     "endDate"   (:end-date req)})

(defn get-for-idx [req]
    (client/get "https://index.baidu.com/api/SearchApi/index"
        {:headers      (header-with-auth (:baidu-uss req))
         :query-params (query-params req)}))

(defn search-index [req]
    (let [result (-> (get-for-idx req) :body json/read-str (get "data"))]
        {:unique-id (get result "uniqid") :indexes (get result "userIndexes")}))


(defn exchange-ptbk [req]
    (-> (client/get "http://index.baidu.com/Interface/ptbk"
            {:headers      (header-with-auth (:baidu-uss req))
             :query-params {"uniqid" (:unique-id req)}})
        :body json/read-str (get "data")))

(require '[club.geek666.baiduindex.ptbk :refer [ptbk-decode]])
(defn data-list-decode [ptbk data-list]
    (into '() (map #(ptbk-decode ptbk %) data-list)))

(defn search [req]
    (let [index (search-index req)
          ptbk (exchange-ptbk {:baidu-uss (:baidu-uss req) :unique-id (:unique-id index)})]
        (->> index :indexes (map #(get-in % ["all" "data"])) (data-list-decode ptbk))))