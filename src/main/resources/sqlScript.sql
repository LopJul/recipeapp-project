DROP TABLE IF EXISTS recipe_ingredient;
DROP TABLE IF EXISTS favourites;

DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS ingredient;
DROP TABLE IF EXISTS app_user;

CREATE TABLE app_user (
id BIGSERIAL PRIMARY KEY,
username VARCHAR(250) NOT NULL,
password VARCHAR(250) NOT NULL,
role VARCHAR(100) NOT NULL
);

INSERT INTO app_user(username, password, role)
VALUES
('user', '$2a$10$1DTvwpXVBArGFixHBuzVJObjTuXhIOkx5pse6KsYs8/C2ckxnGEou', 'USER'),
('admin', '$2a$10$cDZgyF4xaPMmmoRW3OVcmuf.8o2YSx8.M7CeRKqi.1PVw.t3E8uEC', 'ADMIN');

CREATE TABLE ingredient (
  id BIGSERIAL  PRIMARY KEY,
  name VARCHAR(150) NOT NULL
);

CREATE TABLE recipe (
  id BIGSERIAL PRIMARY KEY,
  title VARCHAR(150) NOT NULL,
  instructions TEXT NOT NULL,
  app_user_id BIGINT REFERENCES app_user(id)
);

CREATE TABLE recipe_ingredient (
  id BIGSERIAL PRIMARY KEY,
  recipe_id BIGINT REFERENCES recipe(id),
  ingredient_id BIGINT REFERENCES ingredient(id)
);

CREATE TABLE favourites (
  app_user_id BIGINT REFERENCES app_user(id),
  recipe_id BIGINT REFERENCES recipe(id),
  PRIMARY KEY (app_user_id, recipe_id)
);

select * from recipe;