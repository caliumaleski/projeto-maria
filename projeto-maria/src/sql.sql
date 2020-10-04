CREATE DATABASE "projeto-maria"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       CONNECTION LIMIT = -1;
	   
	   
	   
CREATE SEQUENCE usersequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 8
  CACHE 1;
ALTER TABLE usersequence
  OWNER TO postgres;
	   
	   
CREATE TABLE resultado
(
  id bigint NOT NULL DEFAULT nextval('usersequence'::regclass),
  login character varying(500),
  senha character varying(20),
  jogo character varying(500),
  placar character varying(500),
  minimotemporada character varying(500),
  maximotemporada character varying(500),
  quebraminimo character varying(500),
  quebramaximo character varying(500),
  CONSTRAINT resultado_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE resultado
  OWNER TO postgres;
  

  
  INSERT INTO resultado(
            id, login, senha, jogo, placar, minimotemporada, maximotemporada, quebraminimo, quebramaximo)
    VALUES (1, 'maria', 'maria','22','44','4','4','2','2');


  INSERT INTO resultado(
            id, login, senha, jogo, placar, minimotemporada, maximotemporada, quebraminimo, quebramaximo)
    VALUES (2, 'admin', 'admin','22','44','4','4','2','2');