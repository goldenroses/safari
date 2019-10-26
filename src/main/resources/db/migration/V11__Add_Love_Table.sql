CREATE SEQUENCE love_id_seq;
CREATE TABLE love (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('love_id_seq'),
  content CHAR(2000) DEFAULT NULL,
  created_at CHAR(300) DEFAULT NULL
);
ALTER SEQUENCE love_id_seq OWNED BY love.id;