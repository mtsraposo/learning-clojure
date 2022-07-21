(ns learning-clojure.symmetrize_alien
  (:gen-class))

(require '[clojure.string :as str])

(defn prepend-segment
  [segment part]
  {:name (str segment "-" (:name part))
   :size (:size part)})

(defn parse-part-name
  [part]
  {:name (str/replace (:name part) #"^.*?-" "")
   :size (:size part)})

(defn segment-to-part-tuples
  [part segments]
  (map (fn [segment] [segment (parse-part-name part)]) segments))

(defn matches-segment
  [part segment]
  (not= (re-find (re-pattern (str "^" segment "-")) (:name part)) nil))

(defn matches-any-segment
  [part segments]
  (reduce (fn [found-match segment]
            (or found-match (matches-segment part segment)))
          false
          segments))

(defn matching-parts
  [part segments]
  (if (matches-any-segment part segments)
    (set
      (map
        (fn [[segment part]] (prepend-segment segment part))
        (segment-to-part-tuples part segments)
        )
      )
    (hash-set part))
  )

(defn symmetrize-alien
  [asym-body-parts segments]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (matching-parts part segments)))
          (set [])
          asym-body-parts))