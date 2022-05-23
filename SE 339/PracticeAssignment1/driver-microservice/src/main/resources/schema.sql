CREATE TABLE IF NOT EXISTS driver (
  id                BIGINT AUTO_INCREMENT,
  first_name        CHAR(24),
  last_name         CHAR(24),
  phone_number      CHAR(24),
  vehicle_id        BIGINT,
  PRIMARY KEY (id)
);
