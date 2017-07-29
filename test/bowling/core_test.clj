(ns bowling.core-test
  (:use midje.sweet)
  (:use [bowling.core]))

(defn roll-many [pins rolls]
  (if (= rolls 1)
    (list pins)
    (concat (list pins) (roll-many pins (dec rolls)))))

(facts "the bowling game"
  (fact "game of all ones scores twenty"
    (calculate-score (roll-many 1 20)) => 20)
  (fact "spare doubles next roll"
    (calculate-score (concat (list 9 1) (roll-many 1 18))) => 29)
  (fact "only count a spare when both rolls are in same frame"
    (calculate-score (concat (list 1 8 2) (roll-many 1 17))) => 28)
  (fact "strike doubles next two rolls"
    (calculate-score (concat (list 10) (roll-many 1 18))) => 30)
  (fact "strike in final frame grants two extra rolls"
    (calculate-score (concat (roll-many 0 18) (list 10 1 1))) => 12)
  (fact "spare in final frame grants two extra rolls"
    (calculate-score (concat (roll-many 0 18) (list 1 9 1))) => 11))

