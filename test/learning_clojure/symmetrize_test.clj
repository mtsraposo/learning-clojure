(ns learning-clojure.symmetrize-test
  (:require [clojure.test :refer :all]
            [learning-clojure.symmetrize :refer :all]))

(deftest matching-part-test
  (testing "Match single part"
    (is (=
          (matching-part {:name "left-eye" :size 1})
          {:name "right-eye" :size 1}
          )
        )
    )
  )

(deftest symmetrize-test
  (testing "Symmetrize all body parts"
    (is (=
          (symmetrize-body-parts [{:name "head" :size 3}
                                  {:name "left-eye" :size 1}]
                                 )
          [{:name "head" :size 3}
           {:name "left-eye" :size 1}
           {:name "right-eye" :size 1}]
          )
        )
    )
  )

(deftest symmetrize-with-reduce-test
  (testing "Symmetrize all body parts using reduce"
    (is (=
          (symmetrize-body-parts-with-reduce [{:name "head" :size 3}
                                              {:name "left-eye" :size 1}]
                                             )
          [{:name "head" :size 3}
           {:name "left-eye" :size 1}
           {:name "right-eye" :size 1}]
          )
        )
    )
  )

(deftest hit-test
  (testing "Hit body part"
    (let [sym-body-parts [{:name "head" :size 3}
                          {:name "left-eye" :size 1}
                          {:name "right-eye" :size 1}]]
      (is (=
            (hit sym-body-parts 2)
            {:name "head" :size 3}
            )
          )
      (is (=
            (hit sym-body-parts 3)
            {:name "left-eye" :size 1}
            )
          )
      (is (=
            (hit sym-body-parts 6)
            {:error "out-of-bounds"}
            )
          )
      )
    )
  )