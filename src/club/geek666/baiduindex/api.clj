(ns club.geek666.baiduindex.api
    (:require [clj-http.client :as client]
              [clojure.data.json :as json]
              [clojure.string :as str]))

(def url-search-index "https://index.baidu.com/api/SearchApi/index")

(def baidu-uss "UNsNjd2bVFQWnBkUVFFU2VydWNhR2pEYnVMMFpiaWdMUmN4cjdHNVZOSDNwaHBnSVFBQUFBJCQAAAAAAAAAAAEAAAC-5SM2yta7-sbBu7XBywAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPcZ81~3GfNfS")

(defn common-header [baidu-uss] {"User-Agent" "Mozilla/5.0 (X11; Linux x86_64)"
                                 "Cookie"     (str/join "=" ["BDUSS" baidu-uss])})

(defn gen-keyword-filter [name]
    [{"name" name, "wordType" 1}])

(defn query-params [keywords]
    {"area" 0,
     "word" (-> (map gen-keyword-filter keywords) json/write-str),
     "days" 30})

(defn req-for-idx [baidu-uss keywords]
    (client/get
        url-search-index
        {:headers      (common-header baidu-uss)
         :query-params (query-params keywords)}))

(defn search-index [baidu-uss keywords]
    (-> (req-for-idx baidu-uss keywords) :body json/read-str))