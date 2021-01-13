(ns club.geek66.baiduindex.main
    (:gen-class))

(require '[club.geek66.baiduindex.api :as api]
    '[club.geek66.baiduindex.app :refer [baidu-uss keywords start-date end-date]]
    '[club.geek66.baiduindex.iso-local-date :refer :all]
    '[clojure.data.csv :as csv]
    '[clojure.java.io :as io])

; (doall (map vector '(1 2 3) '(4 5 6)))
(defn to-vec [keyword data-range index-list]
    (map vector (repeat (count data-range) keyword) data-range index-list))

(defn write-excel [file-name title content]
    (with-open [writer (io/writer file-name)]
        (csv/write-csv writer
            (conj content title))))

(defn -main []
    (let [key-indexes (api/search {:baidu-uss (baidu-uss) :keywords (keywords) :start-date (start-date) :end-date (end-date)})
          range (date-range (start-date) (plus-days (end-date) 1))
          excel-input (mapcat (fn [key-index]
                                  (to-vec (:keyword key-index) range (-> key-index :index :all))) key-indexes)]
        (write-excel "output.csv" ["keyword" "date" "index"] excel-input)))