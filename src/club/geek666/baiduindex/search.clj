(ns club.geek666.baiduindex.search
    (:require [clj-http.client :as client]
              [clojure.data.json :as json]))

(def url-search-index "https://index.baidu.com/api/SearchApi/index")
;[{"name":"蔡徐坤","wordType":1}]
(defn gen-index-filter [name]
    [{"name" name, "wordType" 1}])