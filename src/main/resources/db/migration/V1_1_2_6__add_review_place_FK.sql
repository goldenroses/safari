ALTER TABLE review
ADD CONSTRAINT place_id
  FOREIGN KEY (place_id)
  REFERENCES place (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
