(ns bowling.core)

(declare sum-up-score)

(defn sum-next [n rolls]
  (if (= n 1)
    (first rolls)
    (+
      (first rolls)
      (sum-next (dec n) (rest rolls)))))

(defn is-strike [rolls]
  (= (first rolls) 10))

(defn is-spare [rolls]
  (= (sum-next 2 rolls) 10))

(defn is-last [frame]
  (= frame 10))

(defn score-spare [rolls frame]
  (if (is-last frame)
    (sum-next 3 rolls)
    (+
      (sum-next 3 rolls)
      (sum-up-score (drop 2 rolls) (inc frame)))))

(defn score-strike [rolls frame]
  (if (is-last frame)
    (sum-next 3 rolls)
    (+
      (sum-next 3 rolls)
      (sum-up-score (rest rolls) (inc frame)))))

(defn score-neither [rolls frame]
  (+
    (sum-next 2 rolls)
    (sum-up-score (drop 2 rolls) (inc frame))))

(defn sum-up-score [rolls frame]
  (if (> frame 10)
    0
    (if (is-strike rolls)
      (score-strike rolls frame)
      (if (is-spare rolls)
        (score-spare rolls frame)
        (score-neither rolls frame)))))

(defn calculate-score [rolls]
  (sum-up-score rolls 1))
