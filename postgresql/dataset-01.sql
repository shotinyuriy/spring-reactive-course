DROP TABLE weather_data;
CREATE TABLE IF NOT EXISTS weather_data (
    weather_data_id serial PRIMARY KEY,
    city character varying(100) NOT NULL,
    date_time timestamp NOT NULL,
    temperature integer NOT NULL
);

TRUNCATE TABLE weather_data;
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (1, 'Саратов', '2021-07-01 00:00:00.000', 20);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (2, 'Саратов', '2021-07-01 06:00:00.000', 21);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (3, 'Саратов', '2021-07-01 12:00:00.000', 22);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (4, 'Саратов', '2021-07-01 18:00:00.000', 23);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (5, 'Саратов', '2021-07-02 00:00:00.000', 24);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (6, 'Саратов', '2021-07-02 06:00:00.000', 25);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (7, 'Саратов', '2021-07-02 12:00:00.000', 25);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (8, 'Саратов', '2021-07-02 18:00:00.000', 25);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (9, 'Саратов', '2021-07-03 00:00:00.000', 25);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (10, 'Саратов', '2021-07-03 06:00:00.000', 25);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (11, 'Саратов', '2021-07-03 12:00:00.000', 25);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (12, 'Саратов', '2021-07-03 18:00:00.000', 25);

INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (13, 'Энгельс', '2021-07-01 00:00:00.000', 22);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (14, 'Энгельс', '2021-07-01 06:00:00.000', 25);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (15, 'Энгельс', '2021-07-01 12:00:00.000', 23);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (16, 'Энгельс', '2021-07-01 18:00:00.000', 20);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (17, 'Энгельс', '2021-07-02 00:00:00.000', 20);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (18, 'Энгельс', '2021-07-02 06:00:00.000', 23);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (19, 'Энгельс', '2021-07-02 12:00:00.000', 27);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (20, 'Энгельс', '2021-07-02 18:00:00.000', 20);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (21, 'Энгельс', '2021-07-03 00:00:00.000', 19);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (22, 'Энгельс', '2021-07-03 06:00:00.000', 26);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (23, 'Энгельс', '2021-07-03 12:00:00.000', 19);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (24, 'Энгельс', '2021-07-03 18:00:00.000', 15);

INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (25, 'Архангельск', '2021-07-01 00:00:00.000', 10);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (26, 'Архангельск', '2021-07-01 06:00:00.000', 13);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (27, 'Архангельск', '2021-07-01 12:00:00.000', 20);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (28, 'Архангельск', '2021-07-01 18:00:00.000', 25);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (29, 'Архангельск', '2021-07-02 00:00:00.000', 14);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (30, 'Архангельск', '2021-07-02 06:00:00.000', 15);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (31, 'Архангельск', '2021-07-02 12:00:00.000', 16);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (32, 'Архангельск', '2021-07-02 18:00:00.000', 15);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (33, 'Архангельск', '2021-07-03 00:00:00.000', 15);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (34, 'Архангельск', '2021-07-03 06:00:00.000', 18);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (35, 'Архангельск', '2021-07-03 12:00:00.000', 20);
INSERT INTO weather_data (weather_data_id, city, date_time, temperature) VALUES (36, 'Архангельск', '2021-07-03 18:00:00.000', 18);