(ns Player
    (:gen-class))

(defn move [x y lx ly]
    (let [u (cond (< x lx) (+ x 1) (> x lx) (- x 1) :else x)
          v (cond (< y ly) (+ y 1) (> y ly) (- y 1) :else y)
          dx (cond (< x lx) "E" (> x lx) "W" :else "")
          dy (cond (< y ly) "S" (> y ly) "N" :else "")]
         {:x u :y v :dir (str dy dx)}))

(defn -main [& args]
    (let [lightX (read) lightY (read) initialTX (read) initialTY (read)]
        (loop [lx lightX ly lightY x initialTX y initialTY]
            (read)
            (let [res (move x y lx ly)]
                (println (:dir res))
                (recur lx ly (:x res) (:y res))))))
