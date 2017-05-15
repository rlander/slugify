(ns slugify.core
  (:require [clojure.string :as s])
  (:import [java.text Normalizer Normalizer$Form]))

(defn- trim-to [string-to-trim trim-value]
  (apply str (take trim-value string-to-trim)))

(defn- normalize [string-to-normalize]
  (let [normalized (Normalizer/normalize string-to-normalize Normalizer$Form/NFD)
        ascii (s/replace normalized #"[\P{ASCII}]+" "")]
    (s/lower-case ascii)))

(defn slugify
  "Returns a slugified string. Takes two optional parameters:
  delimiter (str): string that interleaves valid words,
  trim-value (int): max url value."
  ([string-to-slugify]           (slugify string-to-slugify "-"))
  ([string-to-slugify delimiter] (slugify string-to-slugify delimiter 250))
  ([string-to-slugify delimiter trim-value]
   (let [normalized (normalize string-to-slugify)
         split-s (s/split (s/triml normalized) #"[\p{Space}\p{P}]+")
         combined (s/join delimiter split-s)]
     (trim-to combined trim-value))))
