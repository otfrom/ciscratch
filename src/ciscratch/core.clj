(ns ciscratch.core
  (:use [incanter.core]
        [incanter.stats]
        [incanter.charts]
        [clojure.set]))

(defn get-shared-prefs [prefs critic1 critic2]
  (intersection (set (keys (prefs critic1))) (set (keys (prefs critic2)))))

(defn sim-distance [prefs critic1 critic2]
  (let [shared (get-shared-prefs prefs critic1 critic2)
        diffs (map #(- (get-in prefs [critic1 %]) (get-in prefs [critic2 %])) shared)
        squares (sum-of-squares diffs)]
    (if (seq diffs)
      (/ 1 (+ 1 (sqrt squares)))
      0)))

(defn sim-pearson [prefs critic1 critic2]
  (let [shared (get-shared-prefs prefs critic1 critic2)
        len (float (length shared))]
    (if (= 0 len)
      ;; nothing in common return -1
      -1
      (let [ratings1 (map #(get-in prefs [critic1 %]) shared)
            ratings2 (map #(get-in prefs [critic2 %]) shared)
            ;; add up all preferences
            sum1 (sum ratings1)
            sum2 (sum ratings2)
            ;; sum of squares
            sum1sq (sum-of-squares ratings1)
            sum2sq (sum-of-squares ratings2)
            ;; sum of products
            psum (sum (map * ratings1 ratings2))
            numerator (- psum (/ (* sum1 sum2) len))
            denominator (sqrt (* (- sum1sq (/ (pow sum1 2) len)) (- sum2sq (/ (pow sum2 2) len))))]
        (if (= 0 denominator)
          ;; divide by 0, return 1.0
          1.0
          (do
            (println "Shared: " shared)
            (println "Length: " len)
            (println "Ratings1: " ratings1)
            (println "Ratings2: " ratings2)
            (println "sum1: " sum1)
            (println "sum2: " sum2)
            (println "sum1sq: " sum1sq)
            (println "sum2sq: " sum2sq)
            (println "psum: " psum)
            (println "numerator: " numerator)
            (println "denominator: " denominator)
            (/ numerator denominator)))))))
