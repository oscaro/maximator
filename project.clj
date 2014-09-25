(defproject com.oscaro/maximator "0.1.0-SNAPSHOT"
  :description "Thin Clojure wrapper around MaxMind GeoIP2 for IP geolocalization"
  :url "https://github.com/oscaro/maximator"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.maxmind.geoip2/geoip2 "0.9.0"]
                 [gavagai "0.3.1"]]
  :plugins [[codox "0.8.10"]]
  :codox {:src-dir-uri "https://github.com/oscaro/maximator/blob/master/"
          :src-linenum-anchor-prefix "L"
          :defaults {:doc/format :markdown}})
