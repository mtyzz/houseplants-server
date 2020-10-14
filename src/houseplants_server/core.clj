(ns houseplants-server.core
  (:require [houseplants-server.db :as db]
            [muuntaja.core :as m]
            [org.httpkit.server :refer [run-server]]
            [reitit.ring :as ring]
            [reitit.ring.middleware.exception :refer [exception-middleware]]
            [reitit.ring.middleware.muuntaja :refer [format-negotiate-middleware
                                                     format-request-middleware
                                                     format-response-middleware]]))

;;TODO: Move server atom and repl functionality to separate namespace

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)))

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

#_(defn -main []
  (println "Starting server")
    (run-server app {:port 4000}))

(defn -main []
  (println "Starting server")
  (reset! server (run-server app {:port 4000})))

(defn restart-server []
  (stop-server)
  (-main))

(comment
  (restart-server)
  @server
  (app {:request-method :get
         :uri "/plants"}))
