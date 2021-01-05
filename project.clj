(defproject baidu-index "0.1.0-SNAPSHOT"
    :description "FIXME: write description"
    :url "http://example.com/FIXME"
    :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
              :url  "https://www.eclipse.org/legal/epl-2.0/"}
    :dependencies [[org.clojure/clojure "1.10.1"]
                   [nrepl/nrepl "0.6.0"]
                   [clj-http "3.10.3"]
                   [org.clojure/data.json "1.0.0"]]

    :mirrors {#"central" {:name "huaweicloud" :url "https://mirrors.huaweicloud.com/repository/maven/" :repo-manager true}
              "*"        {:name "aliyun" :url "http://maven.aliyun.com/nexus/content/groups/public" :repo-manager true}}
    :repositories {"huaweicloud" {:url "https://mirrors.huaweicloud.com/repository/maven/"}
                   "aliyun"      {:url "http://maven.aliyun.com/nexus/content/groups/public"}}
    :main club.geek666.baiduindex.core
    :repl-options {:init-ns club.geek666.baiduindex.core})