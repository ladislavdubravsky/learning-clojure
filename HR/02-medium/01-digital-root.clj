(defn digsum [n]
    (reduce + (map #(- (int %) 48) n)))

(defn mdsum [n]
    (+ 1 (mod (- n 1) 9)))

(let [[nn kk] (clojure.string/split (read-line) #" ")
      k (Integer/parseInt kk)
      n (* k (digsum nn))]
    (println (mdsum n)))
