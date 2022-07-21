(ns learning-clojure.symmetrize-alien-test
  (:require [clojure.test :refer :all]
            [learning-clojure.symmetrize_alien :refer :all]))


(deftest prepend-segment-test
  (testing "Prepend segment to part name"
    (is (=
          (prepend-segment "left" {:name "eye" :size 1})
          {:name "left-eye" :size 1})
        )
    )
  )

(deftest parse-part-name-test
  (testing "Remove segment of part name"
    (is (=
          (parse-part-name {:name "left-eye" :size 1})
          {:name "eye" :size 1})
        )
    )
  )

(deftest segment-to-part-tuples-test
  (testing "Return a vector of tuples containing segments and a part"
    (is (=
          (segment-to-part-tuples {:name "left-eye" :size 1} ["left" "right"])
          [["left" {:name "eye" :size 1}]
           ["right" {:name "eye" :size 1}]])
        )
    )
  )

(deftest matches-segment-test
  (testing "Checks if part matches a given segment"
    (is (=
          (matches-segment {:name "left-eye" :size 1} "left")
          true)
        )
    (is (=
          (matches-segment {:name "left-eye" :size 1} "right")
          false)
        )
    (is (=
          (matches-segment {:name "eye" :size 1} "right")
          false)
        )
    )
  )

(deftest matches-any-segment-test
  (testing "Checks if part matches any segment in a vector"
    (is (=
          (matches-any-segment {:name "eye" :size 1} ["left", "right"])
          false)
        )
    (is (=
          (matches-any-segment {:name "right-eye" :size 1} ["left", "right"])
          true)
        )
    (is (=
          (matches-any-segment {:name "left-eye" :size 1} ["left", "right"])
          true)
        )
    )
  )

(deftest matching-parts-test
  (testing "Returns a set of parts matching a given part"
    (is (=
          (matching-parts {:name "head" :size 1} ["left", "right"])
          (set [{:name "head" :size 1}])
          )
        )
    (is (=
          (matching-parts {:name "left-eye" :size 1} ["left", "right", "center"])
          (set [{:name "left-eye" :size 1}
                {:name "right-eye" :size 1}
                {:name "center-eye" :size 1}])
          )
        )
    )
  )

(deftest symmetrize-alien-test
  (testing "Symmetrize alien to a vector of body segments"
    (is (=
          (symmetrize-alien
            [{:name "head" :size 3}
             {:name "left-eye" :size 1}]
            ["left" "right" "center" "top" "bottom"])
          (set [{:name "head" :size 3}
           {:name "left-eye" :size 1}
           {:name "right-eye" :size 1}
           {:name "center-eye" :size 1}
           {:name "top-eye" :size 1}
           {:name "bottom-eye" :size 1}])
          )
        )
    )
  )