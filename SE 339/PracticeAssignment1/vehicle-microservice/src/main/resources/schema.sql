CREATE TABLE IF NOT EXISTS vehicle (
  id                BIGINT AUTO_INCREMENT,
  make              CHAR(20),
  model             CHAR(20),
  model_year        INT,
  registration_id   BIGINT,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS registration (
  id                BIGINT AUTO_INCREMENT,
  license_plate     CHAR(10),
  licensed_to       CHAR(128),
  PRIMARY KEY (id)
)
