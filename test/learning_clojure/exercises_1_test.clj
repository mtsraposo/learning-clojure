(ns learning-clojure.exercises-1-test
  (:require [clojure.test :refer :all]
            [learning-clojure.exercises-1 :refer :all]))

(deftest inc100-test
  (testing "Increment a number by 100"
    (is (=
          (inc100 100)
          200)
        )
    )
  )