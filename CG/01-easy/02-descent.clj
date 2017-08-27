(ns Player (:gen-class))

(defn -main [& args]
    (while true
        (let [inp (repeatedly 8 read)]
        (println (.indexOf inp (apply max inp))))))
