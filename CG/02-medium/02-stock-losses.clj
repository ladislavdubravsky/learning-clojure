(ns Solution
    (:gen-class))

(defn -main [& args]
    (let [n (read) _ (read-line) vs (map read-string (clojure.string/split (read-line) #" "))]
        (println (apply min (map - (reverse (reductions min (reverse vs))) (reductions max vs))))))
