--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 11.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: coversheet; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA coversheet;


ALTER SCHEMA coversheet OWNER TO postgres;


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: ControlPlates; Type: TABLE; Schema: coversheet; Owner: postgres
--

CREATE TABLE coversheet."ControlPlates" (
    "Sign" text,
    "Stamp" text,
    "Number" integer
);


ALTER TABLE coversheet."ControlPlates" OWNER TO postgres;

--
-- Name: DeliveredPlates; Type: TABLE; Schema: coversheet; Owner: postgres
--

CREATE TABLE coversheet."DeliveredPlates" (
    "Operation" integer,
    "Count" integer,
    "Number" integer,
    "OriginalBatch" integer,
    "OTK" integer
);


ALTER TABLE coversheet."DeliveredPlates" OWNER TO postgres;

--
-- Name: RemovedPlates; Type: TABLE; Schema: coversheet; Owner: postgres
--

CREATE TABLE coversheet."RemovedPlates" (
    "Operation" integer,
    "Count" integer,
    "Number" integer,
    "Defects" text
);


ALTER TABLE coversheet."RemovedPlates" OWNER TO postgres;

--
-- Data for Name: ControlPlates; Type: TABLE DATA; Schema: coversheet; Owner: postgres
--

INSERT INTO coversheet."ControlPlates" VALUES ('Test1', 'Test1', 676);
INSERT INTO coversheet."ControlPlates" VALUES ('Test2', 'Test2', 4443);
INSERT INTO coversheet."ControlPlates" VALUES ('Тестовое обозначение', 'Тестовая марка', 123);
INSERT INTO coversheet."ControlPlates" VALUES ('qwerqwr', 'qwrqwr', 888);


--
-- Data for Name: DeliveredPlates; Type: TABLE DATA; Schema: coversheet; Owner: postgres
--

INSERT INTO coversheet."DeliveredPlates" VALUES (3, 100, 4443, 645, 123);
INSERT INTO coversheet."DeliveredPlates" VALUES (4, 234, 676, 423, 234);
INSERT INTO coversheet."DeliveredPlates" VALUES (4, 100, 123, 545, 45);
INSERT INTO coversheet."DeliveredPlates" VALUES (3, 1000, 888, 312, 56);


--
-- Data for Name: RemovedPlates; Type: TABLE DATA; Schema: coversheet; Owner: postgres
--

INSERT INTO coversheet."RemovedPlates" VALUES (123, 5000, 4443, 'Тест');
INSERT INTO coversheet."RemovedPlates" VALUES (4, 100, 676, 'Test2');
INSERT INTO coversheet."RemovedPlates" VALUES (5, 60, 123, 'Тестовый вид брака');
INSERT INTO coversheet."RemovedPlates" VALUES (9, 10, 888, 'Test6');

--
-- Name: ControlPlates ControlPlates_Number_key; Type: CONSTRAINT; Schema: coversheet; Owner: postgres
--

ALTER TABLE ONLY coversheet."ControlPlates"
    ADD CONSTRAINT "ControlPlates_Number_key" UNIQUE ("Number");


--
-- Name: DeliveredPlates DeliveredPlates_Number_key; Type: CONSTRAINT; Schema: coversheet; Owner: postgres
--

ALTER TABLE ONLY coversheet."DeliveredPlates"
    ADD CONSTRAINT "DeliveredPlates_Number_key" UNIQUE ("Number");


--
-- Name: Number_AI; Type: INDEX; Schema: coversheet; Owner: postgres
--

CREATE UNIQUE INDEX "Number_AI" ON coversheet."RemovedPlates" USING btree ("Number");


--
-- Name: ControlPlates ControlPlates_Number_fkey; Type: FK CONSTRAINT; Schema: coversheet; Owner: postgres
--

ALTER TABLE ONLY coversheet."ControlPlates"
    ADD CONSTRAINT "ControlPlates_Number_fkey" FOREIGN KEY ("Number") REFERENCES coversheet."RemovedPlates"("Number") ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: DeliveredPlates DeliveredPlates_Number_fkey; Type: FK CONSTRAINT; Schema: coversheet; Owner: postgres
--

ALTER TABLE ONLY coversheet."DeliveredPlates"
    ADD CONSTRAINT "DeliveredPlates_Number_fkey" FOREIGN KEY ("Number") REFERENCES coversheet."RemovedPlates"("Number") ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

