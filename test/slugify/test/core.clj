(ns slugify.test.core
  (:use [slugify.core])
  (:use [clojure.test]))

(deftest test-slugify
  (testing "Simple slugfy"
    (is (= "charlie-brown" (slugify "charlie brown")))
    (is (= "charlie-brown" (slugify "Charlie Brown"))))
  (testing "Arbitrary delimiter"
    (is (= "charlie_brown" (slugify "charlie brown" "_"))))
  (testing "Unecessary spaces"
    (is (= "charlie-brown" (slugify "    Charlie Brown    "))))
  (testing "Delimiter and trim value"
    (is (= "charlie-brown-peppermint-patty" (slugify "Charlie Brown, Peppermint Patty and Lucy van Pelt" "-" 30))))
  (testing "Nonascii chars"
    (is (= "aaaaaaaaaa" (slugify "áÁàÀãÃâÂäÄ")))
    (is (= "eeeeeeeeee" (slugify "éÉèÈẽẼêÊëË")))
    (is (= "iiiiiiiiii" (slugify "íÍìÌĩĨîÎïÏ")))
    (is (= "oooooooooo" (slugify "óÓòÒõÕôÔöÖ")))
    (is (= "uuuuuuuuuu" (slugify "úÚùÙũŨûÛüÜ")))
    (is (= "cccccc" (slugify "ćĆĉĈçÇ")))))
