--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4
-- Dumped by pg_dump version 12.4

-- Started on 2021-02-15 16:40:07

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
-- TOC entry 2846 (class 1262 OID 24629)
-- Name: slouke; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE slouke WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'French_France.1252' LC_CTYPE = 'French_France.1252';


ALTER DATABASE slouke OWNER TO postgres;

\connect slouke

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
-- TOC entry 207 (class 1255 OID 24630)
-- Name: compterconnections(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.compterconnections() RETURNS void
    LANGUAGE plpgsql
    AS $$
DECLARE
	compte bigint;

BEGIN	
	compte:= (SELECT count(numbackends) FROM pg_stat_database);
	
	INSERT into journal(id, moment, connections) VALUES(DEFAULT, NOW(), compte);
	
	raise notice 'Nombre de connections: %', compte;
END
$$;


ALTER FUNCTION public.compterconnections() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 24631)
-- Name: journal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.journal (
    id integer NOT NULL,
    moment timestamp without time zone,
    connections bigint
);


ALTER TABLE public.journal OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 24634)
-- Name: journal_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.journal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.journal_id_seq OWNER TO postgres;

--
-- TOC entry 2847 (class 0 OID 0)
-- Dependencies: 203
-- Name: journal_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.journal_id_seq OWNED BY public.journal.id;


--
-- TOC entry 204 (class 1259 OID 24636)
-- Name: messages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.messages (
    id integer NOT NULL,
    message text,
    pseudo text,
    heure time without time zone,
    id_salon integer
);


ALTER TABLE public.messages OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 24642)
-- Name: salon; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.salon (
    id integer NOT NULL,
    nom text
);


ALTER TABLE public.salon OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 24648)
-- Name: utilisateur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utilisateur (
    id integer NOT NULL,
    "pseudo " text,
    age integer,
    bio text,
    email text
);


ALTER TABLE public.utilisateur OWNER TO postgres;

--
-- TOC entry 2703 (class 2604 OID 24654)
-- Name: journal id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.journal ALTER COLUMN id SET DEFAULT nextval('public.journal_id_seq'::regclass);


--
-- TOC entry 2836 (class 0 OID 24631)
-- Dependencies: 202
-- Data for Name: journal; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.journal (id, moment, connections) VALUES (1, '2020-12-10 21:44:21.556592', 9);
INSERT INTO public.journal (id, moment, connections) VALUES (2, '2020-12-10 21:45:05.378815', 9);


--
-- TOC entry 2838 (class 0 OID 24636)
-- Dependencies: 204
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2839 (class 0 OID 24642)
-- Dependencies: 205
-- Data for Name: salon; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.salon (id, nom) VALUES (1, 'Salon Générale');
INSERT INTO public.salon (id, nom) VALUES (2, 'Salon Privé');
INSERT INTO public.salon (id, nom) VALUES (3, 'Salon VIP');


--
-- TOC entry 2840 (class 0 OID 24648)
-- Dependencies: 206
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.utilisateur (id, "pseudo ", age, bio, email) VALUES (1, 'NLG_', 20, 'Fatman en puissance', 'gdalbi14@gmail.com');
INSERT INTO public.utilisateur (id, "pseudo ", age, bio, email) VALUES (2, 'Arceusyo_Luffy
', 20, 'N''importe quoi', 'random@free.fr');
INSERT INTO public.utilisateur (id, "pseudo ", age, bio, email) VALUES (3, 'Fuzzy', 20, 'Aime manger des sausscisses et de la crême', 'simon.delarue2@gmail.com');


--
-- TOC entry 2848 (class 0 OID 0)
-- Dependencies: 203
-- Name: journal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.journal_id_seq', 2, true);


--
-- TOC entry 2705 (class 2606 OID 24656)
-- Name: journal journal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.journal
    ADD CONSTRAINT journal_pkey PRIMARY KEY (id);


--
-- TOC entry 2707 (class 2606 OID 24658)
-- Name: salon salon_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.salon
    ADD CONSTRAINT salon_pkey PRIMARY KEY (id);


--
-- TOC entry 2709 (class 2606 OID 24660)
-- Name: utilisateur utilisateur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (id);


-- Completed on 2021-02-15 16:40:08

--
-- PostgreSQL database dump complete
--

