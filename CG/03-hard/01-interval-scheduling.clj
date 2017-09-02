(ns Solution
    (:gen-class))

(defn count-tasks [[[end start] & tasks] min-end cnt]
    (cond (nil? end) cnt
          (>= start min-end) (recur tasks end (inc cnt))
          :else (recur tasks min-end cnt)))

(defn -main [& args]
    (let [n (read) _ (read-line)
          tasks (repeatedly n read-line)
          res (->> tasks
                   (map #(clojure.string/split % #" "))
                   (map #(map (fn [x] (Integer/parseInt x)) %))
                   (map #(vector (+ (first %) (last %)) (first %)))
                   sort
                   (#(count-tasks % 0 0))
                   println)]))
