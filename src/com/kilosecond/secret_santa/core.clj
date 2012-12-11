(ns com.kilosecond.secret-santa.core
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic]))

(defne position-distincto
  "A relation where xl and yl have different members at each position"
  [xl yl]
  ([() ()])
  ([[x . xs] [y . ys]]
     (position-distincto xs ys)
     (!= x y)))

(defn strict-permuteo
  "A relation where xl and yl are permutations of each other and have
  distinct members at each position"
  [xl yl]
  (all
   (permuteo xl yl)
   (position-distincto xl yl)))

(defne pairso
  "xl is zipped pairs of yl and zl"
  [xl yl zl]
  ([() () ()])
  ([[x . xs] [y . ys] [z . zs]]
     (== x [y z])
     (pairso xs ys zs)))

(defne not-pairso
  "x and y aren't paired in pl"
  [pl x y]
  ([() _ _])
  ([[p . ps] _ _]
     (!= p [x y])
     (!= p [y x])
     (not-pairso ps x y)))

(defn santa-pairso
  "Pairs"
  [pairs gifters]
  (fresh [giftees]
         (strict-permuteo gifters giftees)
         (pairso pairs gifters giftees)))

(defmacro secret-santa
  "Generate a secret santa pairing from names, excluding pairs in
  bad-pairs."
  [names bad-pairs]
  (let [exclusions (map (fn [[p1 p2]] `(not-pairso ~'q ~p1 ~p2)) bad-pairs)]
    `(first (run 1 [~'q]
                 (santa-pairso ~'q ~names)
                 ~@exclusions))))

(comment
  (secret-santa ["chris" "christine" "russell" "marie" "harish" "sumeet"]
                [["chris" "christine"]
                 ["chris" "russell"]
                 ["christine" "russell"]
                 ["russell" "marie"]
                 ["russell" "sumeet"]]))
