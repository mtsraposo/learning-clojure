(ns learning-clojure.exercises-1-test
  (:require [clojure.test :refer :all]
            [learning-clojure.exercises-1 :refer :all]))

(deftest dfs-test
  (testing "Perform a depth-first search on a DAG"
    (is (=
          (dfs {:1 [:2 :3], :2 [:4 :5], :3 [:2 :6]}
                :5)
          "found"))
    (is (=
          (dfs {:1 [:2 :3], :2 [:4 :5], :3 [:2 :6]}
               :7)
          "not_found"))))
