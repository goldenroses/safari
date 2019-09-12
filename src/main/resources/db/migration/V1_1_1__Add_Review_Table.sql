CREATE SEQUENCE review_id_seq;
CREATE TABLE review (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('review_id_seq'),
  title CHAR(300) DEFAULT NULL,
  rating bigint DEFAULT NULL,
  comment CHAR(255) DEFAULT NULL,
  person_id bigint DEFAULT NULL,
  review_id bigint DEFAULT NULL
);
ALTER SEQUENCE review_id_seq OWNED BY review.id;