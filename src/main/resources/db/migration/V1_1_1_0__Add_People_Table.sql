CREATE SEQUENCE people_id_seq;
CREATE TABLE people (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('people_id_seq'),
  name CHAR(300) DEFAULT NULL,
  profile_image_url CHAR(255) DEFAULT NULL,
  email bigint DEFAULT NULL,
  place_id bigint DEFAULT NULL,
  service_type_id bigint DEFAULT NULL
);