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
-- Name: journal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.journal (
    id integer NOT NULL,
    moment timestamp without time zone,
    connections bigint
);


ALTER TABLE public.journal OWNER TO postgres;

--
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
-- Name: journal_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.journal_id_seq OWNED BY public.journal.id;


--
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
-- Name: salon; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.salon (
    id integer NOT NULL,
    nom text
);


ALTER TABLE public.salon OWNER TO postgres;

--
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
-- Name: journal id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.journal ALTER COLUMN id SET DEFAULT nextval('public.journal_id_seq'::regclass);


--
-- Data for Name: journal; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.journal VALUES (1, '2020-12-10 21:44:21.556592', 9);
INSERT INTO public.journal VALUES (2, '2020-12-10 21:45:05.378815', 9);


--
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.messages VALUES (1, 'Premier mesage ever de Slouke !', 'NLG_', '14:38:09', 2);
INSERT INTO public.messages VALUES (2, 'Slt ça va ?', 'Fuzzy', '14:52:30', 1);
INSERT INTO public.messages VALUES (3, 'Qu''est ce qu''il est jolie ce salon !', 'NLG_', '08:51:17', 3);
INSERT INTO public.messages VALUES (4, 'Dsl j''étais parti manger !', 'Arceusyo_Luffy', '13:52:00', 2);
INSERT INTO public.messages VALUES (5, 'C''est pour demain ? ou pas ?', 'Fuzzy', '18:09:59', 1);
INSERT INTO public.messages VALUES (6, 'J''aime pas ce qu''il a fait franchement...', 'NLG_', '01:42:11', 2);
INSERT INTO public.messages VALUES (7, 'J''avoue il est très beau', 'Arceusyo_Luffy', '10:42:52', 3);
INSERT INTO public.messages VALUES (8, 'Qui est chaud pour un Minecraft ?', 'Arceusyo_Luffy', '15:28:14', 1);
INSERT INTO public.messages VALUES (9, 'Il a plu toute la journée...', 'NLG_', '22:07:10', 3);


--
-- Data for Name: salon; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.salon VALUES (1, 'Salon Générale');
INSERT INTO public.salon VALUES (2, 'Salon Privé');
INSERT INTO public.salon VALUES (3, 'Salon VIP');


--
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.utilisateur VALUES (1, 'NLG_', 20, 'Fatman en puissance', 'gdalbi14@gmail.com');
INSERT INTO public.utilisateur VALUES (2, 'Arceusyo_Luffy
', 20, 'N''importe quoi', 'random@free.fr');
INSERT INTO public.utilisateur VALUES (3, 'Fuzzy', 20, 'Aime manger des sausscisses et de la crême', 'simon.delarue2@gmail.com');


--
-- Name: journal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.journal_id_seq', 2, true);


--
-- Name: journal journal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.journal
    ADD CONSTRAINT journal_pkey PRIMARY KEY (id);


--
-- Name: salon salon_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.salon
    ADD CONSTRAINT salon_pkey PRIMARY KEY (id);


--
-- Name: utilisateur utilisateur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

