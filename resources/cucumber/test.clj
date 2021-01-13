(ns cucumber.test
    (:require [cucumber.runtime.clj :refer :all]))

(When #"^I test$" []
    (println "I test"))

(Then #"^I test successful$" []
    (println "I test successful"))