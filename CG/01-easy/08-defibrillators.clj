(ns Solution
    (:gen-class))

(defn to-rad [n] (->> n
                      (#(clojure.string/replace % #"," "."))
                      Double/parseDouble
                      (#(/ (* Math/PI %) 180))))

(defn distance [x0 y0 x y]
    (let [u (* (- x x0) (Math/cos (* 0.5 (+ y0 y))))
          v (- y0 y)]
         (* 6371 (Math/sqrt (+ (* u u) (* v v))))))

(defn -main [& args]
    (let [lon (to-rad (read-line))
          lat (to-rad (read-line))
          n (read) _ (read-line)
          defibs (map #(clojure.string/split % #";") (repeatedly n read-line))
          coords (map (comp #(map to-rad %) #(drop 4 %)) defibs)
          dists (map #(distance lon lat (first %) (last %)) coords)]

        (binding [*out* *err*] (println (str lon " " lat "\n" (doseq [r defibs] (println r)))))
        (println (nth (nth defibs (.indexOf dists (apply min dists))) 1))))
