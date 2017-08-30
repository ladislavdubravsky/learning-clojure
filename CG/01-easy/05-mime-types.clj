(ns Solution
    (:gen-class)
    (:require [clojure.string :as s]))

; for future: use let in repeatedly and wrap in doall to force eval and avoid lazy havoc

(defn -main [& args]
    (let [n (read)
          q (read)
          _ (read-line)
          dict (repeatedly n #(s/split (read-line) #" "))
          dict-lower-keys (into {} (map (fn [[c d]] [(s/lower-case c) d]) dict))
          fnames (map s/lower-case (repeatedly q read-line))]
    
      (def get-ext #(last (s/split % #"\.")))
      (def get-type #(if (or (.endsWith % ".") (= (.indexOf % ".") -1))
                         "UNKNOWN"
                         (get dict-lower-keys (get-ext %) "UNKNOWN")))
    
      (doseq [r (map get-type fnames)] (println r))))
