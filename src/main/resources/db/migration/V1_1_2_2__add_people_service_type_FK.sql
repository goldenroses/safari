ALTER TABLE people
ADD CONSTRAINT service_type_id
  FOREIGN KEY (service_type_id)
  REFERENCES service_type (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
