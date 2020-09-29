;   Copyright (c) Phil Mohun. All rights reserved.
;   The use and distribution terms for this software are covered by the MIT License 
;   By using this software in any fashion, you are agreeing to be bound by the terms of this license.
;   You must not remove this notice, or any other, from this software.

;; import namespaces
(require '[clojure.repl :refer :all]) ;; import namespace with additional REPL functions
(import '[clojure.math.numeric-tower])
(import '[clojure.contrib.trace])

(defn vector-components
"Breaks a vector into it's respective scalar components along the x and y axis. Accepts a vector of length 2, with the magnitude in the first position and the angle from the x axis in the second. Assumes cartesian coordinates."
[c]
(let [mag (first c) 
      theta (degrees2radians (second c))
; return a vector with the x and y components, respectively
      x (* mag (Math/cos theta)) 
      y (* mag (Math/sin theta))]
      [x y]))

(defn add-first [v]
  (let [v (first v)] (recur (next v))))

(defn resultant-magnitude
"Uses the component method to determine the magnitude of a resulant vector when given corresponding x and y components in a Cartesian coordinates system. "
[c]
(let [x (first c) 
      y (second c)
      R (Math/sqrt (+ (Math/pow x 2) (Math/pow y 2)))]
      ; return the magnitude of the Resultant vector
      [R]))

(defn resultant-direction
"Uses the x and y components of a vector to determine it's direction within a Cartesian coordinate system"
[c]
(let [x (first c) 
      y (second c)
      theta (+ 180 (Math/toDegrees (Math/atan (/ y x))))]
      ; return the angle of the Resultant vector with respect to the x-axis, in radians
      [theta]))

(defn compute-resultant
[c]
(let [x (first (vector-components c))
      y (second (vector-components c))
      R (resultant-magnitude [x y])
      theta (resultant-direction [x y])]
      [R theta]))

(defn test-fxn
([c]
(map vector-components c)))
;(loop [i count (map results)] 
;(while (>= i 0) 
;(dec i) 
;(map + results))))))

(defn multi-vector-resultant
"Given two or more vectors, compute the Resulant vector. Assumes vectors contain two values, each with a magnitude and direction using Cartesian coordinates."
([c1 c2] (let 
  ;; Take each vector and break down into component vectors.
  [x1 (first (vector-components c1))
  y1 (second (vector-components c1))
  x2 (first (vector-components c2))
  y2 (second (vector-components c2))
  ;; Add component vectors
  x (+ x1 x2)
  y (+ y1 y2)
  ;; Calculate magnitude and direction of resultant and print as vector
  R (resultant-magnitude [x y])
  theta (resultant-direction [x y])]
  [R theta]
))
([c1 c2 &args] (print "Please try again with two vectors.")))

(defn foo [n] (fn [i] (+ i n)))

(defn Example []
   (loop [x 10]
      (when (> x 1)
         (println x)
         (recur (- x 2))))) 