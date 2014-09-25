(ns com.oscaro.maximator
  (:require [clojure.java.io :as io]
            [gavagai.core :as g])
  (:import [com.maxmind.geoip2 DatabaseReader$Builder]
           [com.maxmind.geoip2.record AbstractNamedRecord]
           [com.maxmind.geoip2 GeoIp2Provider]
           [com.maxmind.db Reader$FileMode]
           [java.net InetAddress]))

(def ^:no-doc file-modes
  {:memory-mapped (Reader$FileMode/valueOf "MEMORY_MAPPED")
   :memory (Reader$FileMode/valueOf "MEMORY")})

(defn- ^:no-doc names-as-kw
  [^AbstractNamedRecord o]
  (persistent!
   (reduce
    (fn [acc [k v]]
      (assoc! acc (keyword k) v))
    (transient {}) (.getNames o))))

(def ^:no-doc translator
  (g/register-converters
    {:exclude [:class]}
    [["com.maxmind.geoip2.model.CityResponse"]
     ["com.maxmind.geoip2.model.ConnectionTypeResponse"]
     ["com.maxmind.geoip2.model.IspResponse"]
     ["com.maxmind.geoip2.model.DomainResponse"]
     ["com.maxmind.geoip2.record.City" :exclude [:names] :add {:names names-as-kw}]
     ["com.maxmind.geoip2.record.Continent" :exclude [:names] :add {:names names-as-kw}]
     ["com.maxmind.geoip2.record.Subdivision" :exclude [:names] :add {:names names-as-kw}]
     ["com.maxmind.geoip2.record.RepresentedCountry" :exclude [:names] :add {:names names-as-kw}]
     ["com.maxmind.geoip2.record.Location"]
     ["com.maxmind.geoip2.record.MaxMind"]
     ["com.maxmind.geoip2.record.Postal"]
     ["com.maxmind.geoip2.record.Country" :exclude [:names] :add {:names names-as-kw}]
     ["com.maxmind.geoip2.record.Traits"]]))

(defn make-locator
  "Returns a locator from either a String file path, a File object or an InputStream. This locator is threadsafe. Takes 2 optional arguments:
 + **:locales** List of recognised locales, for example [:en :fr], used for default :name
 + **:file-mode** Either *:memory-mapped* (default) or *:memory*"
  [f & {:keys [locales file-mode]}]
  (let [arg (if (instance? java.io.InputStream f) f (io/file f))
        builder (DatabaseReader$Builder. arg)]
    (-> builder
        (cond-> locales (.locales (map name locales)))
        (cond-> file-mode (.fileMode (get file-modes file-mode)))
        (.build))))

(defn lookup
  "Lookup an IP given as a String in the given locator and returns a map with results"
  [^GeoIp2Provider db ip]
  (if-let [city (.city db (InetAddress/getByName ip))]
    (g/translate translator city {:lazy? false})))
