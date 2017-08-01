*Contact steve@zaske.com to get a copy of the config.properties file.  Place this file in the root of your project file to get access to the database.*

# _Takeover of The Coding Zombies_

#### _Brief Description: Program presents user with a series of movement choices as they move through a play field populated with zombies and obstacles, July 27, 2017, v1.0_

#### By _**Michael Dunlap, Laura Hamilton, Steve Zaske & Joel Bakken**_

## Description
_This program is a text based adventure game that uses a database to track the location of zombies, your location and the movement options available to you._
* _The goal of this adventure is to find your way through the play field without being caught by any zombies. Don't forget to get your noodles on your way out..._
* _Use the commands: NORTH, EAST, SOUTH, WEST, GET NOODLES to navigate and interact with the gameplay environment.

## What's included
Within the repository you'll find the following directories and files:
```
epicodus-zombies/
├── src/
│    └── main/
│    │    └── java/
|    │    │     └── App.java
|    │    │     └── Exit.java
|    │    │     └── DB.java
|    │    │     └── Location.java
|    │    │     └── Zombie.java
|    │    │     └── VelocityTemplateEngine.java
|    |    └── resources/
|    |          └──public/
|    |                └──css/
|    |                    └──zombie.css
|    |                └──fonts/
|    |                    └──V5Xtende.ttf
|    |                └──img/
|    |                    └──1.jpg
|    |                    └──2.jpg
|    |                    └──3.jpg
|    |                    └──4.jpg
|    |                    └──5.jpg
|    |                    └──6.jpg
|    |                    └──7.jpg
|    |                    └──8.jpg
|    |                    └──9.jpg
|    |                    └──10.jpg
|    |                    └──11.jpg
|    |                    └──12.jpg
|    |                    └──13.jpg
|    |                    └──14.jpg
|    |                    └──15.jpg
|    |                    └──16.jpg
|    |                    └──17.jpg
|    |                    └──18.jpg
|    |                    └──game_over.png
|    |          └──templates/
|    |               └──death.vtl
|    |               └──index.vtl
|    |               └──layout.vtl
|    |               └──start.vtl
|    └── test/
│         └── java/
|               └── DatabaseRule.java
|               └── ExitTest.java
|               └── LocationTest.java
|               └── ZombieTest.java
├── .gitignore
├── build.gradle
└── README.md
```

## Setup/Installation Requirements

* _Install gradle on your device_
* _LOCAL: Go to Terminal_
* _Clone this repository:_
```
$ cd ~/Desktop
$ git clone https://github.com/szaske/epicodus-zombies.git
$ cd epicodus-zombies
$gradle run
```
* _Open browser window:_
```
localhost:4567
```
NOTE: Our database runs from AWS, so there is no need to run Postgres locally.  However, if you want to run the database yourself, or if the database is no longer live, here are the instructions to rebuild the database.

## Database Rebuild

* _Run Postgres with terminal command:_
```
$ postgres
```
* _Open a new tab in terminal by pressing [command ⌘] + [T]_
* _In the new tab, create 'epicodus_zombies' database:_
```
$ psql
$ CREATE DATABASE epicodus_zombies;
$ \c epicodus_zombies;
$ CREATE TABLE locations (id serial PRIMARY KEY, roomtitle varchar, roomdescription varchar);
$ CREATE TABLE exits (id serial PRIMARY KEY, locationid int,  leadsto int, direction varchar);
$ CREATE TABLE zombies (id serial PRIMARY KEY, name varchar, description varchar, location int);
$ CREATE TABLE items (id serial PRIMARY KEY, name varchar, description varchar, location int, status int, userid varchar);
$ CREATE DATABASE epicodus_zombies_test WITH TEMPLATE sepicodus_zombies;

OR

You can just restore the database that we have included with the project by using the following commands:
(You may need to create the database first)

$ psql -U <postgresql username> -d epicodus_zombies -f media.sql


```
## Known Bugs

_No known bugs at this time_

## Support and contact details

_For questions or feedback, please notify Michael Dunlap, Laura Hamilton, Steve Zaske or Joel Bakken via Epicodus._

## Technologies Used

_Languages used include Java._
_Amazon AWS database server used for database hosting._
_IDE used - Atom_
_Images created using Autodesk Sketchbook Express and Wacom Bamboo Pen_

### License

*This software runs under the MIT license*

Copyright (C) 2017 **_Michael Dunlap, Laura Hamilton, Steve Zaske & Joel Bakken_**
