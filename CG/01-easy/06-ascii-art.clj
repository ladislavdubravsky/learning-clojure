(ns Solution
    (:gen-class))

(defn get-ind [c]
    (let [i (int c)]
    (cond (and (> i 64) (< i 91)) (- i 65)
          (and (> i 96) (< i 123)) (- i 97)
          :else 26)))

(defn -main [& args]
    (let [L (read) H (read) _ (read-line) inds (map get-ind (read-line))]

    (loop [i H]
        (when (> i 0)
            (let [row (read-line)
                  res (map #(take L (drop (* % L) row)) inds)]
                (println (apply str (apply concat res)))
                (recur (dec i)))))))
