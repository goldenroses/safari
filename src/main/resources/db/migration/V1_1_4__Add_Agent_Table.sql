CREATE SEQUENCE agent_id_seq;
CREATE TABLE agent (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('agent_id_seq'),
  title CHAR(300) DEFAULT NULL,
  introduction bigint DEFAULT NULL,
  category_id bigint DEFAULT NULL,
  person_id bigint DEFAULT NULL,
  review_id bigint DEFAULT NULL
);
ALTER SEQUENCE agent_id_seq OWNED BY agent.id;