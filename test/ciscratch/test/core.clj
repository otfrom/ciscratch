(ns ciscratch.test.core
  (:use [ciscratch.core] :reload)
  (:use [clojure.test]))

(def test-critics {"Lisa Rose"  {"Lady in the Water"  2.5
                                 "Snakes on a Plane"  3.5
                                 "Just My Luck"  3.0
                                 "Superman Returns"  3.5
                                 "You, me and Dupree"  2.5
                                 "The Night Listener"  3.0}
                   "Gene Seymour"  {"Lady in the Water"  3.0
                                    "Snakes on a Plane"  3.5
                                    "Just My Luck"  1.5
                                    "Superman Returns"  5.0
                                    "You, me and Dupree"  3.5
                                    "The Night Listener"  3.0}
                   "Michael Phllips"  {"Lady in the Water"  2.5
                                       "Snakes on a Plane"  3.0
                                       "Superman Returns"  3.5
                                       "The Night Listener"  4.0}
                   "Claudia Puig"  {"Lady in the Water"  3.0
                                    "Snakes on a Plane"  4.0
                                    "Just My Luck"  2.0
                                    "Superman Returns"  4.0
                                    "You, me and Dupree"  2.0}
                   "Mick LaSalle"  {"Lady in the Water"  3.0
                                    "Snakes on a Plane"  4.0
                                    "Just My Luck"  2.0
                                    "Superman Returns"  3.0
                                    "You, me and Dupree"  2.0
                                    "The Night Listener"  3.0}
                   "Jack Mathews"  {"Lady in the Water"  3.0
                                    "Snakes on a Plane"  4.0
                                    "Superman Returns"  5.0
                                    "You, me and Dupree"  3.5
                                    "The Night Listener"  3.0}
                   "Toby"  {"Snakes on a Plane"  4.5
                            "Superman Returns"  4.0
                            "You, me and Dupree"  1.0}
                   "Test0"  {"Snakes on a Plane"  4.5
                            "You, me and Dupree"  1.0}
                   "Test1"  {"Snakes on a Plane"  4.5
                            "You, me and Dupree"  1.0}
                   "Test2"  {"Remains of the Day"  2.5
                            "Star Wars"  5.0}})

(deftest test-get-shared-prefs
  (is (= #{"Snakes on a Plane" "You, me and Dupree"}
         (get-shared-prefs test-critics "Toby" "Test0")))
  (is (= #{"Snakes on a Plane" "You, me and Dupree"}
         (get-shared-prefs test-critics "Test0" "Toby")))
  (is (= #{}
         (get-shared-prefs test-critics "Test1" "Test2"))))

(deftest sim-distance-test
  (is (= 0.14814814814814814 (sim-distance test-critics "Lisa Rose" "Gene Seymour")))
  (is (= 1.0 (sim-distance test-critics "Test0" "Test1")))
  (is (= 0.0 (sim-distance test-critics "Test1" "Test2"))))
