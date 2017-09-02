(ns Player
    (:gen-class)
    (:require [clojure.string :as s]))

(defn -main [& args]
    (let [w (read) h (read) _ (read) x (read) y (read)]
        (loop [x x y y xmin 0 ymin 0 xmax (- w 1) ymax (- h 1)]
            (let [bdir (name (read))
                  ymaxnew (if (.contains bdir "U") (- y 1) ymax)
                  yminnew (if (.contains bdir "D") (+ y 1) ymin)
                  xminnew (if (.contains bdir "R") (+ x 1) xmin)
                  xmaxnew (if (.contains bdir "L") (- x 1) xmax)
                  xnew (quot (+ xminnew xmaxnew) 2)
                  ynew (quot (+ yminnew ymaxnew) 2)]

                (println (apply str [xnew " " ynew]))
                (recur xnew ynew xminnew yminnew xmaxnew ymaxnew)))))
