(ns Solution
    (:gen-class))

(defn succ [n] (->> n
                    (partition-by identity)
                    (map #(list (count %) (first %)))
                    (apply concat)))

(defn -main [& args]
    (let [R (->> (read) list) L (read)]
        (println (clojure.string/join " " (last (take L (iterate succ R)))))))
