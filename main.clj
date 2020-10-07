;   Copyright (c) Phil Mohun. All rights reserved.
;   The use and distribution terms for this software are covered by the MIT License 
;   By using this software in any fashion, you are agreeing to be bound by the terms of this license.
;   You must not remove this notice, or any other, from this software.

;; import namespaces
(require '[clojure.repl :refer :all]) ;; import namespace with additional REPL functions
(import '[clojure.math.numeric-tower])
(use 'clojure.test)
(import '[clojure.contrib.trace])

(defn vector-components
"Breaks a vector into it's respective scalar components along the x and y axis. Accepts a vector of length 2, with the magnitude in the first position and the angle from the x axis in the second. Assumes cartesian coordinates."
[c]
(let [mag (first c) 
      theta (Math/toRadians (second c))
; return a vector with the x and y components, respectively
      x (* mag (Math/cos theta)) 
      y (* mag (Math/sin theta))]
      [x y]))

(defn angle-adjustor
"An internal function that adjusts the angle theta with respect to the x-axis when converting from radians. Outputs are Longs. Useful when given x and y components of a vector and you need to calculate the angle in degrees. Called by resultant-direction."
[c]
(let [x (first c) 
      y (second c)
      adjustment (if (> x 0)
                    (if (> y 0)
                      0 ; 1st quadrant
                      (if (< y 0)
                        360 ; 4th quandrant
                        "Something is fucked up y'all."))
                    (if (> y 0)
                      180 ; 2nd quadrant
                      180))] ; 3rd quadrant
      adjustment))

(defn compute-components
"A wrapper around the vector-components function that accepts an arbitrary number of arguments and returns the respective x and y scalar components in a Cartesian coordinates system"
;; If one arguments is passed
([c] (vector-components c))
;; If multiple arguments are passed
([c & args] 
  (let [components (map vector-components (concat (vector c) args))]
    (apply mapv + components))))


(defn resultant-magnitude
"Uses the component method to determine the magnitude of a resulant vector when given corresponding x and y components in a Cartesian coordinates system. "
[c]
(let [x (first c) 
      y (second c)
      R (Math/sqrt (+ (Math/pow x 2) (Math/pow y 2)))]
      ; return the magnitude of the Resultant vector
      R))

(defn resultant-direction
"Uses the x and y components of a vector to determine it's direction within a Cartesian coordinate system"
[c]
(let [x (first c) 
      y (second c)
      angle (angle-adjustor c)
      theta (+ angle (Math/toDegrees (Math/atan (/ y x))))]
      ; return the angle of the Resultant vector with respect to the x-axis, in radians
      theta))

(defn compute-resultant
; Accepts a series of vectors c
([c]
(let [components (compute-components c) 
      direction (resultant-direction components)
      magnitude (resultant-magnitude components)]
      [magnitude direction]))
([c & args]
(let [components (apply compute-components c args)
      direction (resultant-direction components)
      magnitude (resultant-magnitude components)]
      [magnitude direction])))

(defn velocity [distance elapsedTime] (double (/ distance elapsedTime)))
(defn distance [velocity elapsedTime] (* velocity elapsedTime))
(defn elapsedTime [velocity distance] (double (/ distance velocity)))

(defn slope [y2 y1 x2 x1] (double (/ (- y2 y1) (- x2 x1))))


;; ----- Schaum's Problems ----- ;;

;; 1.1 
(println "1.1: " (distance 0.25 240))
;; 1.2 
(println "1.2: " (velocity 10000 1800))
; 1.3 
(println "1.3: " (elapsedTime 4.25 17))
; 1.4
(println "1.4: " (* 0.2 0.00001 3600 24 365))
; 1.5 
(println "1.5: " (slope 5 1 10 2))
; 1.6 
(println "1.6: " (resultant-magnitude [6 8]))
; 1.7
(println "1.7a: " (velocity 200 25))
(println "1.7b: " (velocity 0 25))
; 1.8
(println "1.8: " (compute-resultant [2 40] [4 127]))
; 1.9
(println "1.9: " (compute-components [25 210]))
; 1.10
(println "1.10a: " (compute-components [2 40]))
(println "1.10b: " (compute-components [4 127]))
; 1.11 
(println "1.11: " (compute-resultant [30 30] [20 140]))
; 1.12 and 1.13 are graphical and not in scope
; 1.14
(println "1.15: "  )


;; -----------TESTS----------- ;;

(deftest test-vector-components
  (is (= [(* 10 (Math/cos (Math/toRadians 45))) (* 10 (Math/sin (Math/toRadians 45)))] (vector-components [10 45]))))

(deftest test-resultant-magnitude
  (is (= (Math/sqrt 8) (Math/sqrt (+ (Math/pow 2 2) (Math/pow 2 2))))))

(deftest test-resultant-direction
  (is (= (Math/toDegrees (Math/atan (/ 10 20))) (resultant-direction [20 10]))))

(deftest istwoplustwo
(is (= 4 (+ 2 2))))
