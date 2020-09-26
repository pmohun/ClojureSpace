(require '[clojure.repl :refer :all]) ;; import namespace with additional functions
;(require '[clojure.math]) ;; import math namespace

(dir clojure.repl) ;; list all functions in imported namespace
(source dir) ;; let's check out the sourcecode of one of our new macros


;"1) Define a function greet that takes no arguments and prints Hello. Replace the with the implementation: (defn greet [] )"

;(defn greet [] (print "hello"))

; 2) Redefine greet using def, first with the fn special form and then with the #() reader macro.

;(def greet (fn [] print "hello"))
; (def greet #(print "hello"))

; 3) Define a function greeting which:
;Given no arguments, returns "Hello, World!"
;Given one argument x, returns "Hello, x!"
;Given two arguments x and y, returns "x, y!"

;(defn conditional ([] (print "Hello, world!")) ([x] (print "Hello, " [x])) ([x y] (print [x] ", " [y] "!")))

;; For testing
;(assert (= "Hello, World!" (greeting)))
;(assert (= "Hello, Clojure!" (greeting "Clojure")))
;(assert (= "Good morning, Clojure!" (greeting "Good morning" "Clojure")))

;4) Define a function do-nothing which takes a single argument x and returns it, unchanged.

(defn do-nothing [x] x)
;(do-nothing 10)

;5) Define a function always-thing which takes any number of arguments, ignores all of them, and returns the number 100.

(defn always-thing [& x] (print "100"))
;(always-thing)

;6) Define a function make-thingy which takes a single argument x. It should return another function, which takes any number of arguments and always returns x.

(defn make-thingy [x] (fn [& args] x))
;((make-thingy 10))

;7) Define a function triplicate which takes another function and calls it three times, without any arguments.

(defn triplicate [f] (f)(f)(f))
;(triplicate always-thing)

;8) Define a function opposite which takes a single argument f. It should return another function which takes any number of arguments, applies f on them, and then calls not on the result. The not function in Clojure does logical negation.

;; Define a clojure function that accepts an argument, adds ten, and prints the result.
(defn add-ten [x] (+ x 10)) 

(defn opposite [f] (fn [& args] (not (apply f args))))
; ((opposite add-ten) 0)
; ((opposite equals) 10)

;; so look at this one "nested"
;; trying to determine which order arguments are evaluated
(defn nested [x] (fn [& args] (fn [& zyx] print zyx)))
;;(((nested 10) 20) 30)

;; write a function that, when given the magnitude and direction of a vector breaks it into components
;(defn vector-components [mag theta] (* mag Math/cos theta) (mag Math/sin theta))

;; write a function that calculates displacement of multiple vectors
(defn calc-displacement [mag1 theta1 mag2 theta2] )
;; calculate the x and y components of each vector)
