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
-- Name: collection_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.collection_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: collection_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.collection_id_seq OWNED BY public.champ.id;



--
-- Name: champ id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.champ ALTER COLUMN id SET DEFAULT nextval('public.collection_id_seq'::regclass);

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

