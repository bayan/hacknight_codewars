(ns decoder
  (:use clojure.contrib.combinatorics)
  (:use clojure.contrib.str-utils))

(use '[clojure.contrib.duck-streams :only (reader)])

(def words (set (line-seq (reader "/usr/share/dict/words"))))

(def number-mapping [
  []
  []
  [\a \b \c]
  [\d \e \f]
  [\g \h \i]
  [\j \k \i]
  [\m \n \o]
  [\p \q \r \s]
  [\t \u \v]
  [\w \x \y \z]
  ])

(def encoded-text (re-seq #"\w+" "843 78425 27696 369 586733 6837 843 5299 364"))

(defn decode-word [word] 
  (first (for [w (map #(apply str %) (apply cartesian-product (map (fn [digit] (number-mapping digit)) (for [c word] (Integer/parseInt (str c)))))) :when (words w)] w)))

(str-join " " (for [word encoded-text] (decode-word word)))
