--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4
-- Dumped by pg_dump version 12.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plantations2020; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE plantations2020 WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'French_France.1252' LC_CTYPE = 'French_France.1252';


ALTER DATABASE plantations2020 OWNER TO postgres;

\connect plantations2020

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: champ; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.champ (
    id integer NOT NULL,
    nom text,
    distanceferme text,
    taille integer,
    fertilitesol text
);


ALTER TABLE public.champ OWNER TO postgres;

--
-- Name: collection_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.collection_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.collection_id_seq OWNER TO postgres;

--
-- Name: collection_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.collection_id_seq OWNED BY public.champ.id;


--
-- Name: semences; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.semences (
    id integer NOT NULL,
    typesemence text,
    dateplantation text,
    champ_id integer
);


ALTER TABLE public.semences OWNER TO postgres;

--
-- Name: champ id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.champ ALTER COLUMN id SET DEFAULT nextval('public.collection_id_seq'::regclass);


--
-- Data for Name: champ; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.champ VALUES (1, 'Champ no 1', '1,3km', 1200, 'fertile');
INSERT INTO public.champ VALUES (2, 'Champ no 2', '1,7km', 500, 'peu fertile');
INSERT INTO public.champ VALUES (3, 'Champ no 3', '400m', 1653, 'très fertile');
INSERT INTO public.champ VALUES (4, 'Champ no 4', '6,3km', 1443, 'appauvris');
INSERT INTO public.champ VALUES (5, 'Champ no 5
', '3,2km', 750, 'fertile');
INSERT INTO public.champ VALUES (6, 'Champ no 6', '1,1km', 3500, 'très fertile');
INSERT INTO public.champ VALUES (7, 'Champ no 7', '2,3km', 6000, 'peu fertile');
INSERT INTO public.champ VALUES (8, 'Champ no 8', '3,5km', 2180, 'fertile');


--
-- Data for Name: semences; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.semences VALUES (1, 'pommes de terre', '23/07/2020', 1);
INSERT INTO public.semences VALUES (2, 'radis', '12/01/2020', 1);
INSERT INTO public.semences VALUES (4, 'betteraves', '19/03/2020', 1);
INSERT INTO public.semences VALUES (3, 'maïs', '13/05/2020', 2);
INSERT INTO public.semences VALUES (5, 'blé', '23/03/2020', 5);
INSERT INTO public.semences VALUES (6, 'foin', '17/08/2020', 3);
INSERT INTO public.semences VALUES (7, 'blé', '18/07/2020', 4);
INSERT INTO public.semences VALUES (8, 'orge', '22/07/2020', 4);
INSERT INTO public.semences VALUES (9, NULL, NULL, NULL);
INSERT INTO public.semences VALUES (10, NULL, NULL, NULL);
INSERT INTO public.semences VALUES (11, NULL, NULL, NULL);
INSERT INTO public.semences VALUES (12, NULL, NULL, NULL);


--
-- Name: collection_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.collection_id_seq', 12, true);


--
-- Name: champ collection_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.champ
    ADD CONSTRAINT collection_pkey PRIMARY KEY (id);


--
-- Name: semences semences_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.semences
    ADD CONSTRAINT semences_pkey PRIMARY KEY (id);


--
-- Name: semences champ_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.semences
    ADD CONSTRAINT champ_id FOREIGN KEY (champ_id) REFERENCES public.champ(id) NOT VALID;


--
-- PostgreSQL database dump complete
--

