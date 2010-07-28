(ns salary-cap
  (:use clojure.contrib.combinatorics)
  (:use clojure.contrib.str-utils))

(def players [
    ["Alex" 198000 98     ],
    ["Barry" 159000 92    ],
    ["Carlton" 138000 87  ],
    ["Donald" 112000 67   ],
    ["Everett" 150000 78  ],
    ["Frank" 135000 71    ],
    ["Gerhardt" 143000 82 ],
    ["Hank" 171000 93     ],
    ["Isaac" 111000 69    ]
  ]
)

(def affordable-teams (filter (fn [ps] (>= 700000 (apply + (map (fn [p] (p 1)) ps)))) (combinations players 5)))

(def best-team (last (sort-by (fn [team] (apply + (map (fn [t] (t 2)) team))) affordable-teams)))

(doseq [player best-team] (println (str-join ", " player)))
