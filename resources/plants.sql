-- :name create-plants-table
-- :command :execute
-- :result :raw
-- :doc creates plants table

CREATE TABLE plants (
  id SERIAL PRIMARY KEY,
  common_name TEXT,
  scientific_name TEXT,
  cadence INTEGER,
  light TEXT,
  created_at TIMESTAMP NOT NULL DEFAULT current_timestamp
  );

-- :name remove-plants-table
-- :command :execute
-- :result :raw
-- :doc removes the plants table

DROP TABLE plants;

-- :name get-all-plants :query :*
SELECT * FROM plants;

-- :name get-plant-by-id :query :*
SELECT * FROM plants
where id = :id;

-- :name get-plant-by-name :query :*
SELECT * FROM plants
where common_name ilike :name OR scientific_name ilike :name;

-- :name insert-plant :insert :*
INSERT INTO plants (common_name, scientific_name, cadence, light)
VALUES (:common-name, :scientific-name, :cadence, :light)
RETURNING id;

-- :name update-plant-by-id :execute :1
UPDATE plants
SET common_name = :common-name, scientific_name = :scientific-name, cadence = :cadence, light = :light
WHERE id = :id;

-- :name delete-plant-by-id :execute :1
DELETE FROM plants WHERE id = :id;

-- :name insert-plants :! :n
-- :doc Insert multiple plants at once

INSERT INTO plants (common_name, scientific_name, cadence, light)
VALUES :tuple*:plants;
