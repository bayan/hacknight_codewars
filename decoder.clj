(ns decoder
  (:use clojure.contrib.combinatorics)
  (:use clojure.contrib.str-utils))

(use '[clojure.contrib.duck-streams :only (reader)])

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

(def words (set (with-open [rdr (reader "/usr/share/dict/words")] (doall (line-seq rdr)))))

(def encoded-text (re-seq #"\w+" "843 78425 27696 369 586733 6837 843 5299 364"))

(defn decode [word]
  (first (for [w (map #(apply str %)
                      (apply cartesian-product
                             (map (fn [digit] (number-mapping digit))
                                  (for [char word] (Integer/parseInt (str char))))))
               :when (words w)] w)))

(println (str-join " " (for [word encoded-text] (decode word))))
