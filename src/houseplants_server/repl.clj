(ns houseplants-server.repl
  (:require [houseplants-server.routes :as routes]
            [houseplants-server.db :as db]))

;; functions for running and reloading server in repl

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)))

(defn repl-main []
  (println "Starting server")
  (reset! server (run-server app {:port 4000})))

(defn restart-server []
  (stop-server)
  (repl-main))

(defn check-server []
  (restart-server)
  @server
  (app {:request-method :get
        :uri "/plants"}))


;;functions for setting up and seeding the database

(defn new-table-with-seed []
  (db/remove-plants-table db/config)
  (db/create-plants-table db/config)
  (db/insert-plants
   db/config {:plants
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

(defn seed-existing-table []
  (db/insert-plants
   db/config {:plants
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
