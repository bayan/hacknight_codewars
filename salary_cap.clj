(ns salary-cap
  (:use clojure.contrib.combinatorics)
  (:use clojure.contrib.str-utils))

(def all-players [["Alex" 198000 98     ],
                  ["Barry" 159000 92    ],
                  ["Carlton" 138000 87  ],
                  ["Donald" 112000 67   ],
                  ["Everett" 150000 78  ],
                  ["Frank" 135000 71    ],
                  ["Gerhardt" 143000 82 ],
                  ["Hank" 171000 93     ],
                  ["Isaac" 111000 69    ]])

(defn calc-team-totals
 [players]
 (loop [player (first players)
        remaining-players (rest players)
        performance 0
        cost 0]
   (if player
     (recur (first remaining-players)
            (rest  remaining-players)
            (+ performance (player 2))
            (+ cost (player 1)))
     {:players players :performance performance :cost cost})))

(def all-teams (map calc-team-totals (combinations all-players 5)))

(def affordable-teams (filter (fn [team] (>= 700000 (:cost team))) all-teams))

(let [best-team (last (sort-by :performance affordable-teams))]
  (do
    (println "Players:")
    (doseq [player (best-team :players)] (println "\t" (str-join ", " player)))
    (println "Performance:" (best-team :performance))
    (println "Cost:" (best-team :cost))))
