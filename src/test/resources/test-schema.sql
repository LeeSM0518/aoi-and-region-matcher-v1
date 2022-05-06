DROP TABLE IF EXISTS aoi;
DROP TABLE IF EXISTS region;

CREATE TABLE aoi
(
    aoi_id SERIAL PRIMARY KEY,
    name   TEXT,
    area   GEOMETRY('Polygon', 4326)
);

CREATE TABLE region
(
    region_id SERIAL PRIMARY KEY,
    NAME      TEXT,
    area      GEOMETRY('Polygon', 4326)
);