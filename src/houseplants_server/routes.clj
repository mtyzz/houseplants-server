(ns houseplants-server.routes
  (:require [houseplants-server.handlers :as h]))

(def app-routes
  ["/health" {:get (fn [req]
                     {:status 200
                      :body "ok"})}])

(def plants-routes
  ["/plants"
   ["/" {:get h/get-all-plants}]
   ["/:id" {:parameters {:path {:id int?}}
            :get h/get-plant-by-id}]
   ["/name/:name" {:parameters {:path {:name string?}}
                   :get h/get-plants-by-name}]
   ["/cadence/:cadence" {:parameters {:path {:cadence int?}}
                         :get h/get-plants-by-cadence}]
   ["/light/:light" {:parameters {:path {:light string?}}
                     :get h/get-plants-by-light}]])
