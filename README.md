CREATE DATABASE adventure_world
CREATE TABLE locations (id serial PRIMARY KEY, roomtitle varchar, roomdescription varchar);
CREATE TABLE exits (id serial PRIMARY KEY, direction int, locationid int, leadsto int);
