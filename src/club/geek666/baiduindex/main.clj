(ns club.geek666.baiduindex.main
	(:gen-class))

(require '[club.geek666.baiduindex.api :as api]
		 '[club.geek666.baiduindex.app :refer [baidu-uss keywords start-date end-date]]
		 '[club.geek666.baiduindex.core :refer :all])

(defn -main []
	(let [result (api/search {:baidu-uss  baidu-uss
							  :keywords   keywords
							  :start-date start-date
							  :end-date   end-date})]
		(println result)))