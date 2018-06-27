(def bin
  (memoize
    (fn [n k]
      (cond (= n 1) 1
            (= k 0) 1
            (= k n) 1
            :else (mod (+ (bin (- n 1) (- k 1))
                          (bin (- n 1) k)) 100000007)))))

(let [t (Integer/parseInt (read-line))
      ts0 (map #(clojure.string/split % #" ") (repeatedly t read-line))
      ts (map (fn [x] (map #(Integer/parseInt %) x)) ts0)]
  (doseq [i (range t)]
    (println (bin (first (nth ts i)) (last (nth ts i))))))
