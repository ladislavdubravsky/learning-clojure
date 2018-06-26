(defn parse-pt [pt]
    (map #(Integer/parseInt %) (clojure.string/split pt #" ")))

(defn rotate [lst]
    (cons (last lst) (butlast lst)))

(defn det [[x1 y1] [x2 y2]]
    (- (* x1 y2) (* y1 x2)))

(let [n (Integer/parseInt (read-line))
      pts (map parse-pt (repeatedly n read-line))]
    (print (* 0.5 (Math/abs (reduce + (map det pts (rotate pts)))))))
