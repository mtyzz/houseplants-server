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


;;function for seeding the database
(defn seed-existing-table []
  (db/insert-plants
   db/config db/seed-data))
