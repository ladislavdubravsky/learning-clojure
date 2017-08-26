(ns Solution (:gen-class))

; for future: thread-last macro ->> and mapcat function

(defn chuckify [run]
    (concat (if (= (first run) \1) "0 " "00 ") (repeat (count run) 0) " "))

(defn pad-left [n string]
    (apply str (concat (repeat (- n (count string)) "0") string)))

(defn -main [& args]
    (let [msg (read-line)
          msg-in-bin (apply str (map (comp #(pad-left 7 %) #(Integer/toString % 2) int) msg))
          msg-grouped (partition-by identity msg-in-bin)
          msg-final (apply str (map (comp #(apply str %) chuckify) msg-grouped))]

      (println (apply str (drop-last msg-final)))))
