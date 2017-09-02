(ns Solution
    (:gen-class))

(def prices {\e 1 \a 1 \i 1 \o 1 \n 1 \r 1 \t 1 \l 1 \s 1 \u 1 \d 2 \g 2
             \b 3 \c 3 \m 3 \p 3 \f 4 \h 4 \v 4 \w 4 \y 4 \k 5 \j 8 \x 8 \q 10 \z 10})

(defn price [word] (reduce + (map #(get prices %) word)))

(defn delete-one [x xs]
    (let [[n m] (split-with (partial not= x) xs)] (concat n (rest m))))

(defn can-be-made-of [letters word]
    (cond (= (count word) 0) true
          (= (count letters) 0) false
          :else (if (some #(= (first word) %) letters)
                    (can-be-made-of (delete-one (first word) letters) (rest word))
                    false)))

(defn -main [& args]
    (let [n (read)
          _ (doall (read-line))
          words (doall (repeatedly n read-line))
          letters (doall (read-line))
          possible (filter #(can-be-made-of letters %) words)]

        (println (apply max-key (flatten [price (reverse possible)])))))
