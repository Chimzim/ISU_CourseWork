CREATE TABLE IF NOT EXISTS usage_statistic (
  id                    BIGINT AUTO_INCREMENT,
  created_date          TIMESTAMP,
  speed                 DOUBLE,
  fuel_level            DOUBLE,
  rotations_per_minute  BIGINT,
  latitude              DOUBLE,
  longitude             DOUBLE,
  driver_id             BIGINT,
  driver_fullname       CHAR(50),
  vehicle_id            BIGINT,
  vehicle_license_plate CHAR(10),
  PRIMARY KEY (id)
);
