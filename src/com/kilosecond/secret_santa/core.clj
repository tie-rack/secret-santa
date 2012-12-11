(ns com.kilosecond.secret-santa.core
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
     (fresh [pair rev-pair]
            (== pair [x y])
            (== rev-pair [y x])
            (!= p pair)
            (!= p rev-pair)
            (not-pairso ps x y))))

(defn santa-pairso
  "Pairs"
  [pairs gifters]
  (fresh [giftees]
         (strict-permuteo gifters giftees)
         (pairso pairs gifters giftees)))

(comment
  (run 1 [q]
       (santa-pairso q '(chris
                         christine
                         russell
                         marie
                         sumeet
                         harish
                         rob))
       (not-pairso q 'chris 'christine)
       (not-pairso q 'chris 'russell)
       (not-pairso q 'christine 'russell)
       (not-pairso q 'russell 'marie)))
