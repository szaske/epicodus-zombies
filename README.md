CREATE DATABASE adventure_world
CREATE TABLE locations (id serial PRIMARY KEY, roomtitle varchar, roomdescription varchar);
CREATE TABLE exits (id serial PRIMARY KEY, direction int, locationid int, leadsto int);

Team Dev TODO list is at: https://docs.google.com/document/d/1JvA3Q6teH_uK631jiM7oua0K3VxNrkMbHlg-U9yywjY/edit?ts=597686e0
