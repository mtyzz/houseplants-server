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

