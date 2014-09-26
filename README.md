# maximator

Thin Clojure wrapper around [MaxMind GeoIP2](http://dev.maxmind.com/geoip/#GeoIP2) for IP geolocalization.

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

;; then you use lookup on any IP as string
(lookup loc "8.8.8.8")
=>
{:city {:confidence nil, :geo-name-id 5375480, :name "Mountain View",
        :names {:de "Mountain View", :en "Mountain View", :fr "Mountain View",
                :ja "マウンテンビュー", :ru "Маунтин-Вью", :zh-CN "芒廷维尤"}},
 :continent {:code "NA", :geo-name-id 6255149, :name "Amérique du Nord",
             :names {:de "Nordamerika", :en "North America",
                     :es "Norteamérica", :fr "Amérique du Nord", :ja "北アメリカ",
                     :pt-BR "América do Norte", :ru "Северная Америка",
                     :zh-CN "北美洲"}},
 :country {:confidence nil, :geo-name-id 6252001, :iso-code "US",
           :name "États-Unis",
           :names {:de "USA", :en "United States", :es "Estados Unidos",
                   :fr "États-Unis", :ja "アメリカ合衆国", :pt-BR "Estados Unidos",
                   :ru "Сша", :zh-CN "美国"}},
 :location {:accuracy-radius nil, :latitude 37.386, :longitude -122.0838,
            :metro-code 807, :time-zone "America/Los_Angeles"},
 :max-mind {:queries-remaining nil},
 :most-specific-subdivision {:confidence nil, :geo-name-id 5332921,
                             :iso-code "CA", :name "Californie",
                             :names {:de "Kalifornien", :en "California",
                                     :es "California", :fr "Californie",
                                     :ja "カリフォルニア州", :pt-BR "Califórnia",
                                     :ru "Калифорния", :zh-CN "加利福尼亚州"}},
 :postal {:code nil, :confidence nil},
 :registered-country {:confidence nil, :geo-name-id 6252001, :iso-code "US",
                      :name "États-Unis",
                      :names {:de "USA", :en "United States",
                              :es "Estados Unidos", :fr "États-Unis",
                              :ja "アメリカ合衆国", :pt-BR "Estados Unidos",
                              :ru "Сша", :zh-CN "美国"}},
 :represented-country {:confidence nil, :geo-name-id nil, :iso-code nil,
                       :name nil, :names {}, :type nil},
 :subdivisions [{:confidence nil, :geo-name-id 5332921, :iso-code "CA",
                 :name "Californie",
                 :names {:de "Kalifornien", :en "California", :es "California",
                         :fr "Californie", :ja "カリフォルニア州", :pt-BR "Califórnia",
                         :ru "Калифорния", :zh-CN "加利福尼亚州"}}],
 :traits {:anonymous-proxy? false, :autonomous-system-number nil,
          :autonomous-system-organization nil, :domain nil,
          :ip-address "8.8.8.8", :isp nil, :organization nil,
          :satellite-provider? false, :user-type nil}}
```

## TODO

 - Wrap WebAPI client

## License

Copyright © 2014 Oscaro.com

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
