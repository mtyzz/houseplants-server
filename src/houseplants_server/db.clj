(ns houseplants-server.db
  (:require [hugsql.core :as hugsql]))

(def config
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname "//localhost:5432/houseplants_server"
   :user "postgres"
   :password "password"})

(defn create-sql-array [con coll]
  (println "config is " con "and coll is " coll)
  (.createArrayOf (clojure.java.jdbc/get-connection con) "varchar" (into-array String coll)))

(hugsql/def-db-fns "plants.sql")
(comment
  (remove-plants-table config)
  (create-plants-table config)
  (get-all-plants config)
  (insert-plants
   config {:plants
           [[(create-sql-array config ["Heartleaf Philodendron" "Philodendron Brasil"])
             "Philodendron cordatum"
             7
             "bright shade"]
            [(create-sql-array config ["Silver Pothos" "Satin Pothos"])
             "Scindapsus pictus"
             9
             "part sun"]
            [(create-sql-array config ["Corn Plant"])
             "Dracaena fragrans"
             14
             "part shade"]
            [(create-sql-array config ["Boston Fern"])
             "Nephrolepsis-exaltata"
             3
             "shade"]]})
  (insert-plant config {:common-names (create-sql-array config ["Devil's Ivy" "Pothos"]) :scientific-name "Epipremnum aureum" :cadence 7 :light "shade"})
  (insert-plants
   config {:plants
           [["Heartleaf Philodendron"
             "Philodendron cordatum"
             7
             "bright shade"]
            ["Philodendron Brasil"
             "Philodendron cordatum"
             7
             "bright shade"]
            ["Silver Pothos"
             "Scindapsus pictus"
             9
             "part sun"]
            ["Satin Pothos"
             "Scindapsus pictus"
             9
             "part sun"]
            ["Corn Plant"
             "Dracaena fragrans"
             14
             "part shade"]
            ["Boston Fern"
             "Nephrolepsis-exaltata"
             3
             "shade"]]}))
