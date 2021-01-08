(ns club.geek666.baiduindex.api
    (:gen-class)
    (:require [clj-http.client :as client]
              [clojure.data.json :as json]
              [clojure.string :as str]))

(defn common-header [baidu-uss] {"User-Agent" "Mozilla/5.0 (X11; Linux x86_64)"
                                 "Cookie"     (str/join "=" ["BDUSS" baidu-uss])})

(defn gen-keyword-filter [name]
    [{"name" name, "wordType" 1}])

(defn query-params [keywords]
    {"area" 0,
     "word" (-> (map gen-keyword-filter keywords) json/write-str),
     "days" 30})



(def url-search-index "https://index.baidu.com/api/SearchApi/index")

(defn get-for-idx [baidu-uss keywords]
    (client/get url-search-index {:headers (common-header baidu-uss) :query-params (query-params keywords)}))

(defn search-index [baidu-uss keywords]
    (let [result (-> (get-for-idx baidu-uss keywords) :body json/read-str (get "data"))]
        {:unique-id (get result "uniqid") :indexes (get result "userIndexes")}))



(def url-get-ptbk "http://index.baidu.com/Interface/ptbk")

(defn get-ptbk [baidu-uss unique-id]
    (client/get url-get-ptbk {:headers (common-header baidu-uss) :query-params {"uniqid" unique-id}}))

(defn exchange-ptbk [baidu-uss unique-id]
    (-> (get-ptbk baidu-uss unique-id) :body json/read-str (get "data")))