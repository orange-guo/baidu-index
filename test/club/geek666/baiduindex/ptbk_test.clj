(ns club.geek666.baiduindex.ptbk-test
    (:require [clojure.test :refer :all])
    (:require [club.geek666.baiduindex.ptbk :refer :all]))

(def ptbk "xZBKuD9vhL7mGyX7-8%4392.+,6150")
(def data "x9ux7x9yD7xxxD7xxXD7BxGX7ByBx7x9yy7Bvvv7BDmm7x9um7BGmy7BDBu7x9uy7xx9G7xmmX7xxvm7xBXy7xBmu7BGxy7BBBx79vmD7B9B979D9X7GXXBD799mx7Bxm97BvGD7xxux7xuuv7xGXy7mmXG7m9X97xDuB7xvDx")
(def expect-result "7947,7953,7773,7703,8710,8587,7955,8222,8366,7946,8165,8384,7945,7791,7660,7726,7805,7864,8175,8887,9263,8989,9390,10083,9967,8769,8213,7747,7442,7105,6601,6909,7348,7237")

(deftest ptbk-decode-test
    (testing (is (= (ptbk-decode ptbk data) expect-result))))

(deftest split-in-half-test
    (testing (is (= (split-in-half [4 3 9 6]) '((4 3) (9 6))))))
