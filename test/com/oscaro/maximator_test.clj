(ns com.oscaro.maximator-test
  "This test requires the *.mmdb files to be present in the test root folder.
  Please download them from the MaxMind website."
  (:require [clojure.test :refer :all]
            [com.oscaro.maximator :refer :all]
))


(def locators {:city    (make-locator "dev-resources/GeoLite2-City.mmdb" :locales [:en])
               :country (make-locator "dev-resources/GeoLite2-Country.mmdb" :locales [:de :fr])})


(deftest basic-tests
  (testing "city"
    (let [city (lookup-city (:city locators) "2.21.172.132")]
      (is (= "Vienna" (get-in city [:city :name])))
      (is (= "AT" (get-in city [:country :iso-code])))))
  (testing "country"
    (let [country (lookup-country (:country locators) "8.8.8.8")]
      (is (= "US" (get-in country [:country :iso-code]))))))
