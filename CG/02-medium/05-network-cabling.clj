(ns Solution
    (:gen-class))

(defn medianf [x]
    (let [n (count x)
          sorted (sort x)
          half (quot n 2)]
        (if (odd? n)
            (nth sorted half)
            (quot (+ (nth sorted (- half 1)) (nth sorted half)) 2))))

(defn -main [& args]
    (let [n (read)
          inp (repeatedly (* 2 n) read)
          [xs ys] [(take-nth 2 inp) (take-nth 2 (rest inp))]
          med (medianf ys)]
    
        (println (+ (reduce + (for [y ys] (Math/abs (- y med)))) (apply max xs) (- (apply min xs))))))
