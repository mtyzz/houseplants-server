(ns houseplants-server.handlers
  (:require [houseplants-server.db :as db]))

(defn get-all-plants [_]
  {:status 200
   :body (db/get-all-plants db/config)})

(defn get-plant-by-id [{:keys [parameters]}]
  {:status 200
   :body (db/get-plant-by-id db/config (:path parameters))})

(defn get-plants-by-name [{:keys [parameters]}]
  (let [n (get-in parameters [:path :name])]
    {:status 200
     :body (db/get-plant-by-name db/config {:name (str "%" n "%")})}))

(defn get-plants-by-cadence [{:keys [parameters]}]
  {:status 200
   :body (db/get-plant-by-cadence db/config (:path parameters))})

(defn get-plants-by-light [{:keys [parameters]}]
  (let [light (get-in parameters [:path :light])]
    {:status 200
     :body (db/get-plant-by-light db/config {:light (str "%" light "%")})}))
