CREATE SEQUENCE inspirational_id_seq;
CREATE TABLE inspirational (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('inspirational_id_seq'),
  content CHAR(2000) DEFAULT NULL,
  created_at CHAR(300) DEFAULT NULL
);
ALTER SEQUENCE inspirational_id_seq OWNED BY inspirational.id;