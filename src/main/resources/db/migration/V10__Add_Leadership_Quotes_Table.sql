CREATE SEQUENCE leadership_id_seq;
CREATE TABLE leadership (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('leadership_id_seq'),
  content CHAR(2000) DEFAULT NULL,
  created_at CHAR(300) DEFAULT NULL
);
ALTER SEQUENCE leadership_id_seq OWNED BY leadership.id;