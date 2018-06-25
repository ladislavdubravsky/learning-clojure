(defn polynomial [coef pows]
    (fn [x] (reduce + (map #(* %1 (Math/pow x %2)) coef pows))))

(defn parse [str]
    (map #(Integer/parseInt %) (clojure.string/split str #" ")))

(let [coef (parse (read-line))
      pows (parse (read-line))
      [lb ub] (parse (read-line))
      fun (polynomial coef pows)
      intlen 0.0001]
    (prn (reduce + (map #(* intlen (fun %)) (range lb ub intlen))))
    (prn (reduce + (map #(reduce * [intlen Math/PI (fun %) (fun %)]) (range lb ub intlen)))))
