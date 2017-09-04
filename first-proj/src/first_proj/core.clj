; avoiding leiningen probably doesn't make life easier or much sense
; cd dir, lein new app app-name
; cd C:\Clojure\first-proj, for repl             : lein repl
;                           to run               : lein run
;                           to create standalone : lein uberjar
;                           to reload            : (use 'first-proj.core :reload-all)
;                                                   - does it have to be so long???

(ns first-proj.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
  
; define constant
(def some-vect ["More types in a single vector?" 3 true])
  
; define function + imperative style chaining + conditions
(defn triple-string
  [arg1]
  (do
    (println "Let's see what happens if I don't feed this a string.")
    (if (> (count arg1) 10)
      "String too long."
      (str arg1 arg1 arg1))))

; exact fractions, nice touch
(+ 1/3 1/2) ; 5/6


; MAPS: hash maps (remember to check sorted maps later)
(def dict {"key1" + "key2" *})
{"key1" + "key2" *} ; literal

; getting items:
((get dict "key2") 7 8)
((dict "key2") 7 8)
(get-in {:a 0 :b {:c "ho hum"}} [:b :c]) ; nested getting
(get {:a 1 :b 2} :z "missing")

; hash-map constructor + keyword :kw is just something that evaluates to itself, interned string
(hash-map :a 2 :b 15)
(:a {:a 1 :b 2 :c 3}) ; keyword as func

; what is hashable / can be a key?
(get {+ "reads plus" * "reads times"} +) ; no error

; add to map
(assoc {} :key 10)

; keys vals
(keys (assoc {} :key 10))
(vals (assoc {} :key 10))

; VECTORS
(vector "a" "b" "c")
[1 2 "a"] ; vector literal
(get [:a :b :c] 0)
([:a :b :c] 1)
(last [1 2 3 4])


; LISTS have fast cons (called conj), slower nth
'(1 2 3 4) ; list literal
(nth '(1 2 3 :a) 0)
(list "x" {:a 1 :b 2}) ; constructor + different item types


; SETS
; again, hash sets, check sorted sets later
(hash-set 1 1 2 2) ; eval and print gives:
#(1 2) ; hash set literal
(conj #{:a :b} :b)

; membership tests
(contains? #{:a :b} :a)
(:a #{:a :b})
(get #{:a :b} :a)


; FUNCTIONS
; arity overloading
(defn multi-arity
  ([first-arg second-arg]
     (* first-arg second-arg)) ; body dispatchec when 2 args given
  ([first-arg]
     (println first-arg)))     ; body dispatched when 1 arg given

; Python-like rest
(defn mysum [& args] (reduce + args))

; Haskell-like matching (called destructuring) function arguments
(defn stuff1 [fst snd & rest] (println snd))
(defn stuff2 [[fst snd & rest]] (println snd))
(defn stuff3 [{a :key1 b :key2}] (println a))
; (stuff1 1 2 3 4 5)
; (stuff3 {:key1 4 :key2 8})

; in general no tail call optimization
; loop + recur is the only non-stack-consuming looping construct in Clojure
(defn looptest [a b c]
    (loop [a a b b c c] ; three pairs of bindings
    (if (> a 0)
        (do (println a b c)
            (recur (dec a) (+ b 7) c))
        (println "Finished."))))
; if recur was not in tail position we'd get error
; recur can go without loop - looks like it falls back to defn:
(defn looptest2 [a b c]
    (if (> a 0)
        (do (println a b c)
            (recur (dec a) (+ b 7) c))
        (println "Finished.")))


; programming to abstraction: sounds like Python's duck typing & protocols

; SEQUENCE (SEQ) ABSTRACTION
; is sth that has first, rest and cons implemented (Haskell's head, tail, cons)
(seq #{1 2 3}) ; or seq whatever

; seq to map
(into {} [[:a 1] [:c 3] [:b 2]])

; funcs acting on seq:
; map. maps swallow Haskell's zipWiths!
(map str ["a" "b" "c"] ["A" "B" "C"])
(map #(map % [1 2 3] [2 3 4]) [+ * - /])

(reduce - '(1 2 3 4)) ; -8
(reduce - 0 '(1 2 3 4)) ; -10
; reductions instead of scanl. Right folds absent for some (good) reason, use two reverses
(reductions - 0 '(1 2 3 4))

; completely haskell-ish:
(take 5 (cycle [1 2 3]))
(drop 5 [1 2 3])
(take-while #(< % 5) (range 1 100))
(drop-while #(< % 5) (range 1 100))
(filter odd? (range 100))
(some #(> % 5) [0 1])

; sort
(sort-by first ["ebc" "aff" "afd"])

(concat [1 2] '(3 4) {:a 2})
; <- all the stuff is returning lazy seqs, even if they were not pushed in
; constructor to use e.g. in defns is lazy-seq

; repeating
(take 50 (repeat 5))
(repeat 8 #(rand-int 10))
(repeatedly 8 #(rand-int 10))


; COLLECTION ABSTRACTION
; vectors, maps, lists, sets are both collection and seq
; seq is about elements, collection about whole: empty? every? count

; into is for conversion back to a data structure as stuff always keeps changing to seq... uh
(into #{:not-neccessarily-empty} (map identity [:a :a]))

; conj, a bit different
(conj [0] :arg1 :arg2) ; [0 :arg1 :arg2]


; apply - replace head just like in Mathematica
(apply max [1 2 3])

; partial - less elegant than Haskell's (+10), but whatever
(map (partial + 10) #{7 8 9})

; complement

; resource read
(slurp "resources/test.csv")
(slurp "http://clojuredocs.org")
; write
(spit "resources/out.txt" "test")


; PROJECT ORGANIZATION
; require
(require 'clojure.string)
; (require '[clojure.string :as s])
; look at this when doing real project


; MACROS
; reader
(read-string "(+ 1 2)") ; (+ 1 2)
(eval (read-string "(+ 1 2)"))
(read-string "#(+ 1 %)")

; evaluator
(type (read-string "+")) ; clojure.Lang.Symbol
; special forms can't be covered by funcs: if, quote, def, do, let, fn, loop, recur...

(defmacro ignore-last
  [function-call]
  (butlast function-call))

(macroexpand '(ignore-last (+ 1 2 (* 2 3))))
(macroexpand '(for [i [1 2 3]] (* i i))) ; not all that helpful
(macroexpand '(and cond1 cond2))

; threading macros -> ->> etc.






