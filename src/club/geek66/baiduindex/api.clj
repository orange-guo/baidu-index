(ns club.geek66.baiduindex.api
    (:require [clj-http.client :as client]
              [clojure.data.json :as json]
              [clojure.string :as str])
    (:gen-class))

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

(require '[club.geek66.baiduindex.ptbk :refer [ptbk-decode]])
;(defn data-list-decode [ptbk data-list]
;    (into '() (map #(ptbk-decode ptbk %) data-list)))

(defn parse-data [data]
    (doall (map (fn [str] (Integer/parseInt str)) (clojure.string/split data #","))))

(defn search [req]
    (let [result (search-index req)
          ptbk (exchange-ptbk {:baidu-uss (:baidu-uss req) :unique-id (:unique-id result)})
          f-decode (partial ptbk-decode ptbk)]
        (for [idx (:indexes result)] {:keyword (-> idx (get "word") first (get "name"))
                                      :index   {:all  (parse-data (-> (get-in idx ["all" "data"]) f-decode))
                                                :pc   (parse-data (f-decode (get-in idx ["pc" "data"])))
                                                :wise (parse-data (f-decode (get-in idx ["wise" "data"])))
                                                :type (get idx "type")}})))