(defn sums [lst]
    (for [i (range (dec (count lst)))]
        (+ (nth lst i) (nth lst (inc i)))))

(defn return-nth-row [n]
    (let [res
        (cond (= n 0) '(1)
              (= n 1) (concat [1] (return-nth-row (dec n)))
              :else (concat [1] (sums (return-nth-row (dec n))) [1]))]
        (print (str (apply str (map #(str % " ") res)) "\n"))
        res))

(let [n (Integer/parseInt (read-line))]
    (return-nth-row (dec n)))
