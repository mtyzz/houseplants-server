(ns houseplants-server.db
  (:require [hugsql.core :as hugsql]))

(def config
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname "//localhost:5432/houseplants_server"
   :user "postgres"
   :password "password"})

;;All sql expressions are defined in resources/plants.sql
;;hugsql turns these into Clojure fns
(hugsql/def-db-fns "plants.sql")

;;set up and seed the database
(defn new-table-with-seed []
  (remove-plants-table config)
  (create-plants-table config)
  (insert-plants
   config (->> "seed.edn"
               clojure.java.io/resource
               slurp
               clojure.edn/read-string)))
