(ns club.geek666.baiduindex.main)

(require '[club.geek666.baiduindex.api :as api] '[club.geek666.baiduindex.app :as app] '[club.geek666.baiduindex.ptbk :as ptbk])



(defn -main []
    (let [index (api/search-index (app/baidu-uss) ["鸡你太美" "蔡徐坤"])
          unique-id (:unique-id index)
          ptbk (api/exchange-ptbk (app/baidu-uss) unique-id)
          data-set (->> index :indexes (map #(get-in % ["all" "data"])) (map #(ptbk/ptbk-decode ptbk %)) (into '()))]
        (dorun (for [data data-set] (println data)))))