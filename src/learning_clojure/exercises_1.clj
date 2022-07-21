(ns learning-clojure.exercises-1
  (:gen-class))

(defn inc100
  [num]
  (+ num 100))

(defn dec-maker
  [dec-by]
  #(- % dec-by))

(defn mapset
  [fun nums]
  (set (map fun nums)))