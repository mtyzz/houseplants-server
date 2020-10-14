(ns houseplants-server.core
  (:require [houseplants-server.db :as db]
            [muuntaja.core :as m]
            [org.httpkit.server :refer [run-server]]
            [reitit.ring :as ring]
            [reitit.ring.middleware.exception :refer [exception-middleware]]
            [reitit.ring.middleware.muuntaja :refer [format-negotiate-middleware
                                                     format-request-middleware
                                                     format-response-middleware]]))
;; app routes
(def app
  (ring/ring-handler
   (ring/router
    [["/health" {:get (fn [req]
                        {:status 200
                         :body "ok"})}]
     ["/plants" {:get (fn [req]
                        {:status 200
                         :body (db/get-all-plants db/config)})}]]
    {:data {:muuntaja m/instance
            :middleware [format-negotiate-middleware
                         format-response-middleware
                         exception-middleware
                         format-request-middleware]}})
   (ring/routes
    (ring/redirect-trailing-slash-handler)
    (ring/create-default-handler
     {:not-found (constantly {:status 404
                              :body "Not Found"})}))))

;;set up and seed the database
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
                "part sun"]
               ["Boston Fern"
                "Nephrolepsis-exaltata"
                3
                "shade"]]}))

(defn -main []
  (println "Creating new plants table with data")
  (new-table-with-seed)
  (println "Starting server")
  (run-server app {:port 4000}))
