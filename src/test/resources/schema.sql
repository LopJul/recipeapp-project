CREATE TABLE app_user (
id BIGSERIAL PRIMARY KEY,
username VARCHAR(250) NOT NULL,
password VARCHAR(250) NOT NULL,
role VARCHAR(100) NOT NULL
);

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
