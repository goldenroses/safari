CREATE SEQUENCE category_id_seq;
CREATE TABLE category (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('category_id_seq'),
  description CHAR(300) DEFAULT NULL,
  title CHAR(255) DEFAULT NULL
);