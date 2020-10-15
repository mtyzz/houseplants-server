(ns houseplants-server.core
  (:require [houseplants-server.db :refer [new-table-with-seed]]
            [houseplants-server.routes :as routes]
            [muuntaja.core :as m]
            [org.httpkit.server :refer [run-server]]
            [reitit.coercion.spec]
            [reitit.ring :as ring]
            [reitit.ring.coercion :as c]
            [reitit.ring.middleware.exception :refer [exception-middleware]]
            [reitit.ring.middleware.muuntaja :as mm]
            [reitit.ring.middleware.parameters :refer [parameters-middleware]]
            [reitit.swagger-ui :as swagger-ui]))

;; app routes
(def app
  (ring/ring-handler
   (ring/router
    [routes/swagger-route
     routes/app-routes
     routes/plants-routes]
    {:data {:coercion reitit.coercion.spec/coercion
            :muuntaja m/instance
            :middleware [parameters-middleware
                         mm/format-negotiate-middleware
                         mm/format-response-middleware
                         exception-middleware
                         mm/format-request-middleware
                         c/coerce-request-middleware
                         c/coerce-response-middleware
                         c/coerce-exceptions-middleware]}})
   (ring/routes
    (ring/redirect-trailing-slash-handler)
    (swagger-ui/create-swagger-ui-handler
     {:path "/"
      :config {:validatorUrl nil
               :operationsSorter "alpha"}})
    (ring/create-default-handler
     {:not-found (constantly {:status 404
                              :body "Not Found"})}))))

(defn -main []
  (println "Creating new plants table with data")
  (new-table-with-seed)
  (println "Starting server")
  (run-server app {:port 4000}))
