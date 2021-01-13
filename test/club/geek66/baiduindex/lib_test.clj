(ns club.geek66.baiduindex.lib-test
    (:require [clojure.test :refer :all]))

(deftest thread-first-test
    (-> (= "123345456789" (-> "123" (str "345" "456") (str "789"))) is testing))
(deftest thread-last-test
    (-> (= "789345456123" (->> "123" (str "345" "456") (str "789"))) is testing))