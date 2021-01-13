(defproject baidu-index "0.0.1"
    :description ""
    :url "http://example.com/FIXME"
    :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
              :url  "https://www.eclipse.org/legal/epl-2.0/"}
    :dependencies [[org.clojure/clojure "1.10.1"]
                   [nrepl/nrepl "0.6.0"]
                   [clj-http "3.10.3"]
                   [org.clojure/data.json "1.0.0"]
                   [clj-commons/clj-yaml "0.7.0"]
                   [org.clojure/data.csv "1.0.0"]
                   [io.cucumber/cucumber-clojure "2.0.1" :scope "test"]]
    ; [com.siili/lein-cucumber "1.0.7"]
    ;:plugins [[com.siili/lein-cucumber "1.0.7"]]
    ; :cucumber-feature-paths ["test/features"]
    ; :test-paths ["test" "features", "resources"]
    :jar-name "baidu-index-%s.jar"
    :uberjar-name "baidu-index-standalone-%s.jar"

    :mirrors {#"central" {:name "huaweicloud" :url "https://mirrors.huaweicloud.com/repository/maven/" :repo-manager true}}
    ;:repositories {"huaweicloud" {:url "https://mirrors.huaweicloud.com/repository/maven/"}
    ;               "alimaven:"   {:url "http://maven.aliyun.com/nexus/content/groups/public"}
    ;               "clojars"     {:url "https://repo.clojars.org"}}
    :main club.geek66.baiduindex.main
    :aot [club.geek66.baiduindex.main]
    :repl-options {:init-ns club.geek66.baiduindex.main})