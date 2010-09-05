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
      (/ 1 (+ 1 squares))
      0)))

