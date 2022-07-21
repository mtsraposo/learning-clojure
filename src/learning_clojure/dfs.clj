(ns learning-clojure.dfs
  (:gen-class))

(defn visit-connections
  [start graph visited stack]
  (loop [connected (get graph start)
         updated-visited visited
         updated-stack stack]
    (if-not connected
      [updated-visited updated-stack]
      (let [[end & end-remaining] connected]
        ;(println (str "end at: " end))
        (recur end-remaining
               (if-not (contains? visited end)
                 (conj visited end)
                 updated-visited)
               (if-not (contains? visited end)
                 (conj updated-stack end)
                 updated-stack)
               )
        )
      )
    )
  )


(defn dfs
  [graph query]
  (loop [visited (hash-set (nth (keys graph), 0))
         stack [(nth (keys graph), 0)]]
    (if (empty? stack)
      "not_found"
      (let [[start & start-remaining] stack]
        ;(println (str "starting at: " start))
        (if (= start query)
          "found"
          (let [[updated-visited updated-stack]
                (visit-connections start graph visited start-remaining)]
            (recur updated-visited updated-stack)
            )
          )
        )
      )
    )
  )