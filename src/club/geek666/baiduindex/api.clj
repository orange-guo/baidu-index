(ns club.geek666.baiduindex.api
    (:require [clj-http.client :as client]
              [clojure.data.json :as json]
              [clojure.string :as str]))

(def url-search-index "https://index.baidu.com/api/SearchApi/index")

(def baidu-uss "UNsNjd2bVFQWnBkUVFFU2VydWNhR2pEYnVMMFpiaWdMUmN4cjdHNVZOSDNwaHBnSVFBQUFBJCQAAAAAAAAAAAEAAAC-5SM2yta7-sbBu7XBywAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPcZ81~3GfNfS")

(def common-header {"User-Agent" "Mozilla/5.0 (X11; Linux x86_64)"
                    "Cookie"     (str/join "=" ["BDUSS" baidu-uss])})

(defn gen-index-filter [name]
    [{"name" name, "wordType" 1}])

(defn query-params [names]
    {"area" 0,
     "word" (-> (map gen-index-filter names) json/write-str),
     "days" 30})

(defn search-index [keyword]
    (get (-> (client/get
                 url-search-index
                 {:headers      common-header
                  :query-params {"area" 0,
                                 "word" (str "[[{\"name\":\"" keyword "\",\"wordType\":1}]]"),
                                 "days" 7}})
             :body
             json/read-str) "data"))