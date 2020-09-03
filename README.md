# maximator

Thin Clojure wrapper around [MaxMind GeoIP2](http://dev.maxmind.com/geoip/#GeoIP2) for IP geolocalization.

Documentation can be found on [cljdoc](https://cljdoc.org/d/com.oscaro/maximator/CURRENT).

## Installation

`maximator` is available as a Maven artifact from
[Clojars](http://clojars.org/com.oscaro/maximator):

[![Clojars Project](http://clojars.org/com.oscaro/maximator/latest-version.svg)](http://clojars.org/com.oscaro/maximator)


## Usage

First you need to download one of MaxMind GeoIP2 databases. You can find the free Geolite2 City [here](http://dev.maxmind.com/geoip/geoip2/geolite2/).

```clojure
(use 'com.oscaro.maximator)
(use 'clojure.java.io)

;; you can load db from a path easily
;; there are a couple of options to select default language and file-mode, see docstring
(def loc (make-locator "GeoLite2-City.mmdb"))

;; You can also load your db from resources in a jar
(def loc (-> "GeoLite2-City.mmdb"
             (resource)
             (input-stream)
             (make-locator)))

;; then you use lookup-country or lookup-city on any IP as string
(lookup-country loc "8.8.8.8")
=>
{:country {:confidence nil, :iso-code "US", :in-european-union? false,
           :geo-name-id 6252001, :name "USA",
           :names {:de "USA", :ru "США", :pt-BR "Estados Unidos",
                   :ja "アメリカ合衆国", :en "United States", :fr "États-Unis",
                   :zh-CN "美国", :es "Estados Unidos"}},
           :max-mind {:queries-remaining nil},
           :represented-country {:confidence nil, :type nil, :iso-code nil,
                                :in-european-union? false, :geo-name-id nil,
                                :name nil, :names {}},
           :traits {:user-type nil, :hosting-provider? false, :static-ip-score nil,
                   :organization nil, :isp nil, :satellite-provider? false,
                   :anonymous-vpn? false, :tor-exit-node? false, :user-count nil,
                   :legitimate-proxy? false, :anonymous? false, :anonymous-proxy? false,
                   :connection-type nil, :autonomous-system-number nil, :domain nil,
                   :autonomous-system-organization nil, :public-proxy? false,
                   :ip-address "8.8.8.8"},
           :registered-country {:confidence nil, :iso-code "US", :in-european-union? false,
                               :geo-name-id 6252001, :name "USA",
                               :names {:de "USA", :ru "США", :pt-BR "Estados Unidos",
                               :ja "アメリカ合衆国", :en "United States", :fr "États-Unis",
                               :zh-CN "美国", :es "Estados Unidos"}},
           :continent {:code "NA", :geo-name-id 6255149, :name "Nordamerika",
                       :names {:de "Nordamerika", :ru "Северная Америка",
                               :pt-BR "América do Norte", :ja "北アメリカ",
                               :en "North America", :fr "Amérique du Nord",
                               :zh-CN "北美洲", :es "Norteamérica"}}}
```

## TODO

 - Wrap WebAPI client

## License

Copyright © 2014 Oscaro.com

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
