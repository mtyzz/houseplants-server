(ns houseplants-server.core-test
  (:require [houseplants-server.core :refer :all]
            [clojure.test :refer :all]
            [ring.mock.request :as mock]))

(deftest test-app-routes
  (testing "main route redirect"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 302))
      (is (= (:body response) ""))))

  (testing "health check"
    (let [response (app (mock/request :get "/health"))]
      (is (= (:status response) 200))
      (is (= (:body response) "ok"))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
