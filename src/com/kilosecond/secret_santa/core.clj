(ns com.kilosecond.secret-santa.core
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic]
        [com.kilosecond.secret-santa.rot13]))

(defne no-doubleso
  "pairs does not have a pair with identical members"
  [pairs]
  ([()])
  ([[p . ps]]
     (fresh [a b]
            (== p [a b])
            (!= a b)
            (no-doubleso ps))))

(defne zipo
  "zipped is zipped pairs of xl and yl"
  [xl yl zipped]
  ([() () ()])
  ([[x . xs] [y . ys] [z . zs]]
     (== z [x y])
     (zipo xs ys zs)))

(defne not-pairedo
  "x and y aren't paired in pairs"
  [pairs x y]
  ([() _ _])
  ([[p . ps] _ _]
     (!= p [x y])
     (!= p [y x])
     (not-pairedo ps x y)))

(defn santa-pairso
  "Can generate Secret Santa pairings from a list of gifters"
  [pairs gifters]
  (fresh [recipients]
         (permuteo gifters recipients)
         (zipo gifters recipients pairs)
         (no-doubleso pairs)))

(defmacro secret-santa
  "Generate a secret santa pairing from names, excluding pairs in
  bad-pairs."
  [names bad-pairs]
  (let [exclusions (map (fn [[p1 p2]] `(not-pairedo ~'q ~p1 ~p2)) bad-pairs)]
    `(first (run 1 [~'q]
                 (santa-pairso ~'q ~names)
                 ~@exclusions))))

(comment
  (map (fn [[gifter recipient]] [gifter (rot13 recipient)])
       (secret-santa (shuffle ["chris" "christine" "russell" "marie" "harish" "sumeet"])
                     [["chris" "christine"]
                      ["chris" "russell"]
                      ["christine" "russell"]
                      ["russell" "marie"]
                      ["russell" "sumeet"]])))
