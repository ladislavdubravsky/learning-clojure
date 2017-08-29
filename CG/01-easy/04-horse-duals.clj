(ns Solution (:gen-class))

(defn -main [& args]
    (let [n (read) ps (sort (repeatedly n read))
          _ (println (apply min (map - (rest ps) ps)))]))
