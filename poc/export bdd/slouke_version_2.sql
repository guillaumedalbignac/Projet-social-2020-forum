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
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.messages VALUES (1, 'Premier mesage ever de Slouke !', 'NLG_', '14:38:09', 2);


--
-- Data for Name: salon; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.salon VALUES (1, 'Salon Générale');
INSERT INTO public.salon VALUES (2, 'Salon Privé');


--
-- Name: messages messages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (id);


--
-- Name: salon salon_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.salon
    ADD CONSTRAINT salon_pkey PRIMARY KEY (id);


--
-- Name: messages id_salon; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT id_salon FOREIGN KEY (id_salon) REFERENCES public.salon(id) NOT VALID;


--
-- PostgreSQL database dump complete
--

