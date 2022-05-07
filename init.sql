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

INSERT INTO aoi (name, area)
VALUES ('경복궁',
        st_geomfromtext('Polygon((126.979 37.576, 126.979 37.584, 126.974 37.584, 126.974 37.576, 126.979 37.576))',
                        4326)),
       ('북한산',
        st_geomfromtext('Polygon((127.02 37.742, 127.023 37.664, 126.945 37.605, 126.962 37.692, 127.02 37.742))',
                        4326)),
       ('관악산', st_geomfromtext(
               'Polygon((126.985 37.464, 126.980 37.429, 126.933 37.406, 126.910 37.432, 126.931 37.456, 126.985 37.464))',
               4326));

INSERT INTO region (name, area)
VALUES ('서울시',
        st_geomfromtext('Polygon((126.835 37.688, 127.155 37.702, 127.184 37.474, 126.821 37.454, 126.835 37.688))',
                        4326)),
       ('과천시',
        st_geomfromtext('Polygon((126.998 37.463, 127.028 37.436, 126.992 37.403, 126.965 37.421, 126.998 37.463))',
                        4326)),
       ('의정부시',
        st_geomfromtext(
                'Polygon((127.025 37.766, 127.096 37.766, 127.099 37.724, 127.035 37.705, 127.020 37.742, 127.025 37.766))',
                4326));