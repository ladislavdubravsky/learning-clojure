; avoiding leiningen probably doesn't make life easier or much sense
; cd dir, lein new app app-name
; cd C:\Clojure\first-proj, for repl lein repl
;                           to run lein run
;                           to create standalone lein uberjar
; to reload: (use 'first-proj.core :reload-all)  - does it have to be so long???

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
{"key1" + "key2" *}
; getting items:
((get dict "key2") 7 8)
((dict "key2") 7 8)
(get-in {:a 0 :b {:c "ho hum"}} [:b :c])

; hash-map constructor + keyword :kw is just something that evaluates to itself
(hash-map :a 2 :b 15)
(:a {:a 1 :b 2 :c 3}) ; keyword as func

; what is hashable / can be a key?
(get {+ "reads plus" * "reads times"} +) ; no error


; VECTORS
(vector "a" "b" "c")
[1 2 "a"] ; vector literal
(get [:a :b :c] 0)
([:a :b :c] 1)
(last [1 2 3 4])


; LISTS have fast cons (conj here), slower nth
'(1 2 3 4) ; list literal
(nth '(1 2 3 :a) 0)
(list "x" {:a 1 :b 2})


; SETS
; again, hash sets, check sorted sets later
(hash-set 1 1 2 2) ; gives:
#(1 2) ; hash set literal
(conj #{:a :b} :b)

; membership tests
(contains? #{:a :b} :a)
(:a #{:a :b})
(get #{:a :b} :a)


; FUNCTIONS
; arity overloading
(defn multi-arity
  ;; 3-arity arguments and body
  ([first-arg second-arg third-arg]
     (+ first-arg second-arg third-arg))
  ;; 2-arity arguments and body
  ([first-arg second-arg]
     (* first-arg second-arg))
  ;; 1-arity arguments and body
  ([first-arg]
     (println first-arg)))

; pythonic rest
(defn mysum [& args] (reduce + args))

; haskell-y matching (destructuring here) function arguments
(defn stuff1 [fst snd & rest] (println snd))
(defn stuff2 [[fst snd & rest]] (println snd))
(defn stuff3 [{a :key1 b :key2}] (println a))
; (stuff1 1 2 3 4 5)
; (stuff3 {:key1 4 :key2 8})








