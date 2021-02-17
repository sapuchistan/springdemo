CREATE SEQUENCE ws_website_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2147483647
  CACHE 1;

CREATE TABLE ws_website (
  id int8 NOT NULL DEFAULT nextval('ws_website_id_seq'),
  name VARCHAR (255),
  url VARCHAR (255),
  rating int4,
  id_country VARCHAR (255),
  PRIMARY KEY (id));


CREATE SEQUENCE ws_type_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2147483647
  CACHE 1;

CREATE TABLE ws_type (
  id int8 NOT NULL DEFAULT nextval('ws_type_id_seq'),
  name VARCHAR(255),
  description VARCHAR(255),
  PRIMARY KEY(id));



CREATE TABLE ws_site_types (
  id_site int8 NOT NULL ,
  id_type int8 NOT NULL ,
  PRIMARY KEY (id_site, id_type));


  CREATE SEQUENCE ws_technology_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE ws_technology (
  id int8 NOT NULL DEFAULT nextval('ws_technology_id_seq'),
  name VARCHAR(255),
  description VARCHAR(255),
  PRIMARY KEY(id));



  CREATE SEQUENCE ws_rating_id_seq
      START WITH 1
      INCREMENT BY 1
      NO MINVALUE
      MAXVALUE 2147483647
      CACHE 1;

  CREATE TABLE ws_rating (
    id int8 NOT NULL DEFAULT nextval('ws_rating_id_seq'),
    rating  int8 NOT NULL,
    comment NOT NULL VARCHAR(255),
    PRIMARY KEY(id));

    CREATE TABLE ws_site_rating (
      id_site int8 NOT NULL ,
      id_rating int8 NOT NULL ,
      PRIMARY KEY (id_site, id_rating));



  CREATE TABLE ws_site_technology (
    id_site int8 NOT NULL ,
    id_technology int8 NOT NULL ,
    PRIMARY KEY (id_site, id_technology));


  CREATE SEQUENCE ws_country_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

  CREATE TABLE ws_country (
    id int8  NOT NULL DEFAULT nextval('ws_country_id_seq'),
    name VARCHAR(255),
   PRIMARY KEY(id));





ALTER TABLE if EXISTS ws_site_types
ADD CONSTRAINT ws_site_fk
FOREIGN KEY (id_site) REFERENCES ws_website;

ALTER TABLE if EXISTS ws_site_types
ADD CONSTRAINT ws_type_fk
FOREIGN KEY (id_type) REFERENCES ws_type;

ALTER TABLE if EXISTS ws_site_technology
ADD CONSTRAINT ws_site_fk
FOREIGN KEY (id_site) REFERENCES ws_website;

ALTER TABLE if EXISTS ws_site_technology
ADD CONSTRAINT ws_technology_fk
FOREIGN KEY(id_technology) REFERENCES ws_technology;

