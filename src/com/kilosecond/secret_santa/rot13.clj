(ns com.kilosecond.secret-santa.rot13
  (:require [clojure.string :as s]))

(def alphabet "abcdefghijklmnopqrstuvwxyz")

(def rotmap
  (assoc
    (zipmap alphabet (take 26 (drop 13 (cycle alphabet))))
    \space \space))

(defn rot13
  "Lowercased ROT13 of a string. Swallows non-alpha, non-space
  characters. Not for production."
  [string]
  (apply str (map rotmap (s/lower-case string))))
