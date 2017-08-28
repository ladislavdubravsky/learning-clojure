(ns Solution (:gen-class)
             (:require [clojure.string :as s]))

(defn -main [& args]
    (let [n (read) _ (read-line) inp (read-line)
          temps (if (empty? inp) '(0) (map read-string (s/split inp #" ")))
          minabs (apply min-key #(Math/abs %) temps)]
      
        (println (if (some #(= (Math/abs minabs) %) temps)
                     (Math/abs minabs)
                     minabs))))
