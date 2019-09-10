CREATE SEQUENCE service_type_id_seq;
CREATE TABLE service_type (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('service_type_id_seq'),
  title CHAR(300) DEFAULT NULL,
  description CHAR(255) DEFAULT NULL
);
ALTER SEQUENCE service_type_id_seq OWNED BY service_type.id;