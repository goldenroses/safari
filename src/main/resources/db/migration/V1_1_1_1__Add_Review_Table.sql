CREATE SEQUENCE review_id_seq;
CREATE TABLE review (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('review_id_seq'),
  rating CHAR(300) DEFAULT NULL,
  comment CHAR(255) DEFAULT NULL,
  person_id bigint DEFAULT NULL,
  place_id bigint DEFAULT NULL
);
ALTER SEQUENCE review_id_seq OWNED BY review.id;