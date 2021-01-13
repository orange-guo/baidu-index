(ns club.geek66.baiduindex.cucumber.core
    (:use [cucumber.runtime.clj]
          [clojure.test])
    (:import (cucumber.api.cli Main)))
; 不知道为什么只有workdir在test目录下才能生效
(deftest run-cukes
    (. Main (main (into-array ["--plugin" "pretty" "--glue" "cucumber/test.clj" "test.feature"]))))