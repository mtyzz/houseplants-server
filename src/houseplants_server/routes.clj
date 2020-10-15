(ns houseplants-server.routes
  (:require [houseplants-server.handlers :as h]
            [reitit.swagger :as swagger]))

;;Raw swagger documentation in json
(def swagger-route
  ["/swagger.json"
   {:get {:no-doc true
          :swagger {:info {:title "houseplants-server"}}
          :handler (swagger/create-swagger-handler)}}])

;;health check
(def app-routes
  ["/health" {:get (fn [req]
               {:status 200 :body "ok"})}])

;;API routes
(def plants-routes
  ["/plants" {:swagger {:tags ["plants"]}}

   ["/" {:get {:summary "Retrieve all plants from the database."
               :handler h/get-all-plants}}]

   ["/:id" {:get {:summary "Retrieves a plant by id."
                  :parameters {:path {:id int?}}
                  :handler h/get-plant-by-id}}]

   ["/name/:name" {:get {:summary "Retrieves any plants that match a partial or full common or scientific name."
                         :parameters {:path {:name string?}}
                         :handler h/get-plants-by-name}}]

   ["/cadence/:cadence" {:get {:summary "Retrieves plants with the given watering cadence."
                               :handler h/get-plants-by-cadence
                               :parameters {:path {:cadence int?}}}}]

   ["/light/:light" {
                     :get {:summary "Retrieves plants that match a partial or full light specification e.g. 'sun' or 'full sun'."
                           :parameters {:path {:light string?}}
                           :handler h/get-plants-by-light}}]])
