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

;;set up and seed the database
(defn new-table-with-seed []
  (remove-plants-table config)
  (create-plants-table config)
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
             "part sun"]
            ["Boston Fern"
             "Nephrolepsis-exaltata"
             3
             "shade"]]}))
