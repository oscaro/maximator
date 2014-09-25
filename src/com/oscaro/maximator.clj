(ns com.oscaro.maximator
  (:require [clojure.java.io :as io]
            [gavagai.core :as g])
  (:import [com.maxmind.geoip2 DatabaseReader$Builder]
           [java.net InetAddress]))

(def translator
  (g/register-converters
    {:exclude [:class]}
    [["com.maxmind.geoip2.model.CityResponse"]
     ["com.maxmind.geoip2.model.ConnectionTypeResponse"]
     ["com.maxmind.geoip2.model.IspResponse"]
     ["com.maxmind.geoip2.model.DomainResponse"]
     ["com.maxmind.geoip2.record.City"]
     ["com.maxmind.geoip2.record.Continent"]
     ["com.maxmind.geoip2.record.Subdivision"]
     ["com.maxmind.geoip2.record.RepresentedCountry"]
     ["com.maxmind.geoip2.record.Location"]
     ["com.maxmind.geoip2.record.MaxMind"]
     ["com.maxmind.geoip2.record.Postal"]
     ["com.maxmind.geoip2.record.Country"]
     ["com.maxmind.geoip2.record.Traits"]]))


(defn make-locator
  "Returns a locator from either a stroing file path or a File object"
  [f]
  (let [builder (DatabaseReader$Builder. (io/file f))]
    (.build builder)))

(defn get-city
  [db ip]
  (if-let [city (.city db (InetAddress/getByName ip))]
    (g/translate translator city {})))
