INSERT INTO  ws_technology  (name,description)
VALUES
('Java','Programming language'),
('JSP','View Framework'),
('JSF','View Framework'),
('Angular','View Framework'),
('Javascript','programming language'),
('PHP','programming language'),
('C++','programming language'),
('NodeJS','programming language'),
('Mysql','programming language'),
('Bootstrap','programming language'),
('HTML','programming language'),
('CSS','programming language');

INSERT INTO  ws_website  (name,url,rating_count,rating_sum,rating,id_country)
VALUES
('economicompras','www.economicompras.com',0,0,0,3),
('appcomercio','www.apcomercio.com',0,0,0,1),
('Shani BellyDance Studio','www.shanibellydance.com',0,0,0,1),
('Mercado Libre','www.mercadolibre.com',0,0,0,1);

INSERT INTO  ws_country  (name)
VALUES
('Colombia'),
('United States');

INSERT INTO  ws_site_technology  (id_site,id_technology)
VALUES

(1,6),
(1,9),
(1,11),
(1,12),
(2,6),
(2,9),
(2,11),
(2,12),
(3,6),
(4,11);


