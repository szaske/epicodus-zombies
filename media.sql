--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: exits; Type: TABLE; Schema: public; Owner: epicodus_student
--

CREATE TABLE exits (
    id integer NOT NULL,
    locationid integer,
    leadsto integer,
    direction character varying
);


ALTER TABLE exits OWNER TO epicodus_student;

--
-- Name: exits_id_seq; Type: SEQUENCE; Schema: public; Owner: epicodus_student
--

CREATE SEQUENCE exits_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE exits_id_seq OWNER TO epicodus_student;

--
-- Name: exits_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: epicodus_student
--

ALTER SEQUENCE exits_id_seq OWNED BY exits.id;


--
-- Name: items; Type: TABLE; Schema: public; Owner: epicodus_student
--

CREATE TABLE items (
    id integer NOT NULL,
    name character varying,
    description character varying,
    location integer,
    status integer,
    userid character varying
);


ALTER TABLE items OWNER TO epicodus_student;

--
-- Name: items_id_seq; Type: SEQUENCE; Schema: public; Owner: epicodus_student
--

CREATE SEQUENCE items_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE items_id_seq OWNER TO epicodus_student;

--
-- Name: items_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: epicodus_student
--

ALTER SEQUENCE items_id_seq OWNED BY items.id;


--
-- Name: locations; Type: TABLE; Schema: public; Owner: epicodus_student
--

CREATE TABLE locations (
    id integer NOT NULL,
    roomtitle character varying,
    roomdescription character varying
);


ALTER TABLE locations OWNER TO epicodus_student;

--
-- Name: locations_id_seq; Type: SEQUENCE; Schema: public; Owner: epicodus_student
--

CREATE SEQUENCE locations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE locations_id_seq OWNER TO epicodus_student;

--
-- Name: locations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: epicodus_student
--

ALTER SEQUENCE locations_id_seq OWNED BY locations.id;


--
-- Name: zombies; Type: TABLE; Schema: public; Owner: epicodus_student
--

CREATE TABLE zombies (
    id integer NOT NULL,
    name character varying,
    description character varying,
    location integer
);


ALTER TABLE zombies OWNER TO epicodus_student;

--
-- Name: zombies_id_seq; Type: SEQUENCE; Schema: public; Owner: epicodus_student
--

CREATE SEQUENCE zombies_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE zombies_id_seq OWNER TO epicodus_student;

--
-- Name: zombies_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: epicodus_student
--

ALTER SEQUENCE zombies_id_seq OWNED BY zombies.id;


--
-- Name: exits id; Type: DEFAULT; Schema: public; Owner: epicodus_student
--

ALTER TABLE ONLY exits ALTER COLUMN id SET DEFAULT nextval('exits_id_seq'::regclass);


--
-- Name: items id; Type: DEFAULT; Schema: public; Owner: epicodus_student
--

ALTER TABLE ONLY items ALTER COLUMN id SET DEFAULT nextval('items_id_seq'::regclass);


--
-- Name: locations id; Type: DEFAULT; Schema: public; Owner: epicodus_student
--

ALTER TABLE ONLY locations ALTER COLUMN id SET DEFAULT nextval('locations_id_seq'::regclass);


--
-- Name: zombies id; Type: DEFAULT; Schema: public; Owner: epicodus_student
--

ALTER TABLE ONLY zombies ALTER COLUMN id SET DEFAULT nextval('zombies_id_seq'::regclass);


--
-- Data for Name: exits; Type: TABLE DATA; Schema: public; Owner: epicodus_student
--

COPY exits (id, locationid, leadsto, direction) FROM stdin;
3	2	3	SOUTH
2	2	1	EAST
5	2	8	NORTH
6	3	2	NORTH
7	4	2	EAST
8	4	5	WEST
9	5	4	EAST
10	5	6	NORTH
11	6	5	SOUTH
12	6	7	EAST
13	6	11	NORTH
14	7	6	WEST
15	7	8	EAST
16	8	7	WEST
17	8	2	SOUTH
18	8	9	NORTH
19	9	8	SOUTH
20	9	10	WEST
21	9	14	NORTH
22	10	9	EAST
23	10	13	NORTH
24	10	11	WEST
25	11	10	EAST
26	11	6	SOUTH
4	2	4	WEST
27	11	12	NORTH
28	12	11	SOUTH
29	12	13	EAST
30	13	12	WEST
31	13	10	SOUTH
32	13	14	EAST
33	14	13	WEST
34	14	9	SOUTH
35	14	15	EAST
36	15	14	WEST
37	15	16	NORTH
38	15	17	EAST
39	16	15	SOUTH
40	17	15	WEST
41	17	18	SOUTH
42	18	17	NORTH
1	1	2	WEST
43	18	19	EAST
\.


--
-- Name: exits_id_seq; Type: SEQUENCE SET; Schema: public; Owner: epicodus_student
--

SELECT pg_catalog.setval('exits_id_seq', 28, true);


--
-- Data for Name: items; Type: TABLE DATA; Schema: public; Owner: epicodus_student
--

COPY items (id, name, description, location, status, userid) FROM stdin;
1	noodles	tangy plum sauce noodles	16	1	szaske
\.


--
-- Name: items_id_seq; Type: SEQUENCE SET; Schema: public; Owner: epicodus_student
--

SELECT pg_catalog.setval('items_id_seq', 1, false);


--
-- Data for Name: locations; Type: TABLE DATA; Schema: public; Owner: epicodus_student
--

COPY locations (id, roomtitle, roomdescription) FROM stdin;
6	Computer Room - West Middle	To your East, you see a row of stools between the computer rows overturned.
7	Computer Room - Middle	You are between two walls of computer desks. This doesn't feel safe.
5	Computer Room - SW Corner	You can see your car on fire outside the window to the West. The one day you scored street parking.
17	Kitchen - SE Corner	This is it, you're almost out of here as you can make out the glass doors to your South to the hallway.
8	Computer Room - East Middle	There is a whiteboard to your East with meetup dates written in what appears to be blood.
13	Lounge - Middle	There are puzzle pieces scattered all over the floor and furniture. You step over the bloody remains of Steve who is clutching a handfull of puzzle pieces in his left hand.
4	Computer Room - South Middle	There are a wall of expensive Mac computers to the North of you that you can barely make out in the dark.
9	Computer Room - NE Corner	You can make out some strange shapes in the darkness to the North of you.
10	Computer Room - N Middle	To the South of you, there is a row of Mac computers with smoking still sizzling out of the screens.
11	Computer Room - NW Corner	To the West of you, the window is broken, and through it, you can hear faint screaming from the floors below.
12	Lounge - West	To the North of you, there is a pile of couches and kitchen furniture stacked high against the windows. 
15	Kitchen - South	There's a terrible smell in the kitchen...
14	Lounge - East	You are at the East corner of the lounge. You try to make out your own hand in the darkness. 
1	South Hallway	You realize you have left your leftovers in the Epicodus fridge on a Friday. You are in the South Hallway, standing with the Epicodus classroom door in front of you, and a twisted stack of desks engulfed in flames to your left. The lights went out hours ago, and everyone has gone home. There's a bloody handprint on the glass of the door from the opposite side. You must get to the classroom fridge to collect your lunch leftovers and leave the office through the elevators before whatever left that handprint finds you... and your noodles...
16	Kitchen - North	To the West of you, you make out the two fridges: one is tipped over with the freezer door missing and is leaking something dark and foul on the floor, the second is leaning precariously on top of the first, but is quietly humming. Thank goodness you put your noodles in the second fridge.
3	Elysia's Office	This is, or was Elysia's office, with a single door to the North. You can hear the wind howling from the broken window to the South.
2	Computer Room - SE Corner	You are in the computer room. Elysia's office is to the South of you, and the only light is coming from the windows to the West with the glow of abandoned fires throughout the city. You hear shuffling on the carpet...
18	North Hallway	You are in the hallway in front of the elevators. You push the down button, it glows. Your body relaxes as you take a bite of noodles. There's a familiar "ding" as the elevator doors open, revealing complete darkness.You think you hear something from the inside, but this is the only way out...
19	Elevator	Death
\.


--
-- Name: locations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: epicodus_student
--

SELECT pg_catalog.setval('locations_id_seq', 26, true);


--
-- Data for Name: zombies; Type: TABLE DATA; Schema: public; Owner: epicodus_student
--

COPY zombies (id, name, description, location) FROM stdin;
1	Small Zombie	A small'ish and quiet tatooed zombie.	3
2	A Female Zombie	The zombie formally known as Jill	17
3	John	Zombie hunter and intro to coding teacher	11
\.


--
-- Name: zombies_id_seq; Type: SEQUENCE SET; Schema: public; Owner: epicodus_student
--

SELECT pg_catalog.setval('zombies_id_seq', 1, false);


--
-- Name: exits exits_pkey; Type: CONSTRAINT; Schema: public; Owner: epicodus_student
--

ALTER TABLE ONLY exits
    ADD CONSTRAINT exits_pkey PRIMARY KEY (id);


--
-- Name: items items_pkey; Type: CONSTRAINT; Schema: public; Owner: epicodus_student
--

ALTER TABLE ONLY items
    ADD CONSTRAINT items_pkey PRIMARY KEY (id);


--
-- Name: locations locations_pkey; Type: CONSTRAINT; Schema: public; Owner: epicodus_student
--

ALTER TABLE ONLY locations
    ADD CONSTRAINT locations_pkey PRIMARY KEY (id);


--
-- Name: zombies zombies_pkey; Type: CONSTRAINT; Schema: public; Owner: epicodus_student
--

ALTER TABLE ONLY zombies
    ADD CONSTRAINT zombies_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus_student
--

REVOKE ALL ON SCHEMA public FROM rdsadmin;
REVOKE ALL ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO epicodus_student;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

