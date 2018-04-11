
CREATE TABLE Genero
(
	codGen               INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nomGen               VARCHAR(50) NULL,
    desGen               VARCHAR(100) NULL,
	estGen               CHAR(1) NULL
);


CREATE TABLE Categoria
(
	codCat               INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nomCat               VARCHAR(50) NULL,
    imgCat               VARCHAR(200) NULL,
    rutaCat 			 VARCHAR(50) NULL,
	estCat               CHAR(1) NULL
);


CREATE TABLE Historial
(
	codHis               INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	fechaHis             TIMESTAMP NULL,
	codPer               INTEGER NULL,
	codVid               INTEGER NULL
);


CREATE TABLE Lista
(
	codList              INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	codPer               INTEGER NULL,
	codVid               INTEGER NULL
);


CREATE TABLE Personas
(
	codPer               INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nomPer               VARCHAR(50) NULL,
	apePer               VARCHAR(50) NULL,
	dniPer               CHAR(8) NULL,
	emailPer             VARCHAR(50) NULL,
	userPer              VARCHAR(50) NULL,
	passPer              VARCHAR(50) NULL,
	celPer               VARCHAR(9) NULL,
    stylePer             VARCHAR(5) NULL,
    createBy			 VARCHAR(3) NULL,
    cantUser			 VARCHAR(5) NULL,
    tipoPer				 CHAR(1) NULL,
	estPer               CHAR(1) NULL
);


CREATE TABLE Videos
(
	codVid               INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nomVid               VARCHAR(50) NULL,
	yearVid              DATE NULL,
	duraVid              TIME NULL,
    trailer				 VARCHAR(200) NULL,
	desVid               VARCHAR(2000) NULL,
	ubiVid               VARCHAR(300) NULL,
	portVid              VARCHAR(200) NULL,
    imgVid				 VARCHAR(200) NULL,
    sinVid				 VARCHAR(700) NULL,
	tagVid               VARCHAR(20) NULL,
	codCat               INTEGER NULL,
	codGen               INTEGER NULL,
	estVid               CHAR(1) NULL
);


ALTER TABLE Historial
ADD CONSTRAINT R_3 FOREIGN KEY (codPer) REFERENCES Personas (codPer);

ALTER TABLE Historial
ADD CONSTRAINT R_4 FOREIGN KEY (codVid) REFERENCES Videos (codVid);

ALTER TABLE Lista
ADD CONSTRAINT R_5 FOREIGN KEY (codPer) REFERENCES Personas (codPer);

ALTER TABLE Lista
ADD CONSTRAINT R_6 FOREIGN KEY (codVid) REFERENCES Videos (codVid);

ALTER TABLE Videos
ADD CONSTRAINT R_1 FOREIGN KEY (codCat) REFERENCES Categoria (codCat);

ALTER TABLE Videos
ADD CONSTRAINT R_2 FOREIGN KEY (codGen) REFERENCES Genero (codGen);

INSERT INTO Personas VALUES (null,'Alvaro','Iturrizaga Vargas','76350841','alvaro_vita@hotmail.com','aiturrizaga','12345678','951403587','light','0','9000','1','A');
INSERT INTO Personas VALUES (null,'Oscar','Ancajima Rodriguez','71436256','oscar_2008_10@hotmail.com','oancajima','12345678','99900356','light','0','9000','1','A');
INSERT INTO Personas VALUES (null,'Paul','Demo Sanchez','41521650','demo@hotmail.com','demo','12345678','995230165','light','1','50','2','A');


INSERT INTO Genero VALUES (NULL,'BLANCO','NIÑOS PEQUEÑOS/ TODAS LAS EDADES','A');
INSERT INTO Genero VALUES (NULL,'AMARILLO','NIÑOS PEQUEÑOS CON SUPERVISION DE LOS PADRES','A');
INSERT INTO Genero VALUES (NULL,'PURPURA','NO SE RECOMIENDA PARA NIÑOS PERO NO ESTA RESTRINGIDO','A');
INSERT INTO Genero VALUES (NULL,'ROJO','LIMITADO PARA UN PUBLICO DE MAS EDAD/ O ACOMPAÑADOS POR UN ADULTO','A');
INSERT INTO Genero VALUES (NULL,'NEGRO','RESERVADO SOLO PARA ADULTOS','A');


INSERT INTO Categoria VALUES (NULL,'ACCION','https://s26.postimg.org/apj1lcg5l/die_hard_5.jpg','accion','A');
INSERT INTO Categoria VALUES (NULL,'AVENTURA','https://s26.postimg.org/u7dp1a5dl/Couple.jpg','aventura','A');
INSERT INTO Categoria VALUES (NULL,'ANIME','https://s26.postimg.org/rdajnt8c9/anime.jpg','anime','A');
INSERT INTO Categoria VALUES (NULL,'BOLLYWOOD','https://s26.postimg.org/ovysgjw5l/bollywood.jpg','bollywood','A');
INSERT INTO Categoria VALUES (NULL,'COMEDIA','https://s26.postimg.org/ovysgkbl5/comedia.jpg','comedia','A');
INSERT INTO Categoria VALUES (NULL,'CLASICAS','https://s26.postimg.org/y3r0x9axl/clasic.jpg','clasicas','A');
INSERT INTO Categoria VALUES (NULL,'DEPORTES','https://s26.postimg.org/i5ib75jah/deport.jpg','deportes','A');
INSERT INTO Categoria VALUES (NULL,'DOCUMENTALES','https://s26.postimg.org/s2tc07j6h/documentales.jpg','documentales','A');
INSERT INTO Categoria VALUES (NULL,'DRAMA','https://s26.postimg.org/8kyoka1o9/dramas.jpg','drama','A');
INSERT INTO Categoria VALUES (NULL,'FICCION','https://s26.postimg.org/j7shppp95/ficcion.jpg','ficcion','A');
INSERT INTO Categoria VALUES (NULL,'FAMILIARES','https://s26.postimg.org/uwwhdo8i1/familiares.jpg','familiares','A');
INSERT INTO Categoria VALUES (NULL,'ROMANTICAS','https://s26.postimg.org/hfziutt1l/romanticas.jpg','romanticas','A');
INSERT INTO Categoria VALUES (NULL,'TERROR','https://s26.postimg.org/chc0gahix/terror.jpg','terror','A');
INSERT INTO Categoria VALUES (NULL,'MUSICALES','https://s26.postimg.org/5e450o4dl/musicales.jpg','musicales','A');
INSERT INTO Categoria VALUES (NULL,'SERIES','https://s26.postimg.org/qb0d5js6h/series-aos.jpg','series','A');
INSERT INTO Categoria VALUES (NULL,'ANIMADOS','https://s26.postimg.org/ii9pdabu1/animados.jpg','animados','A');


INSERT INTO Videos VALUES (NULL,'XMEN','2000-07-14','01:44:20','',
'La película empieza con un ambiente de finales de la segunda guerra mundial. En el año 1944, con Polonia ocupada por los nazis, el joven Erik Lehnsherr es separado de sus padres. Los alemanes los llevan a un campo de concentración, donde les espera una muerte segura. En su desesperación, intentando ir tras ellos, se manifiestan sus poderes magnéticos doblando un par de puertas metálicas, antes de ser golpeado por los guardias y perder la consciencia.',
'https://servicesmedia.blob.core.windows.net/asset-a5b6b199-4d2a-429d-a9b4-9853de45758a/X-men_1.mp4?sv=2015-07-08&sr=c&si=deff4194-12e6-4fbb-88cf-2c9ca77d5281&sig=ZbDTpYcvOSegJG2Xln5NpQzpKjjpTbpE64fsSIcWXoM%3D&st=2018-02-03T16%3A30%3A20Z&se=2118-02-03T16%3A30%3A20Z',
'https://s26.postimg.org/6tpz15evt/xmen.jpg',
'https://s26.postimg.org/ls8dvkm89/xmen1-thb.jpg',
'Dos mutantes vienen a una academia privada para su clase, cuyo equipo de superhéroes residentes debe oponerse a una organización terrorista con poderes similares.',
'N','10','3','A');
INSERT INTO Videos VALUES (NULL,'XMEN 2','2003-05-01','02:13:47','',
'Los mutantes continúan con su lucha en contra de la sociedad, que les teme y desconfía de ellos. Su causa se torna incluso más desesperada después de sufrir un increible ataque a manos de un agresor todavía no identificado que posee habilidades extraordinarias. El aterrador ataque reaviva la protesta política y pública por imponer un Acta se Registro de Mutantes y un movimiento antimutante, que en esta ocasión es encabezado por William Stryker, un acaudalado ex comandante del ejército de quien se dice ha experimentado con ellos.',
'https://servicesmedia.blob.core.windows.net/asset-be8fb592-8dc6-41c9-844c-709014245ed6/X-men_2.mp4?sv=2015-07-08&sr=c&si=914d37bb-06b4-4a7e-8bdb-ce51dd7af723&sig=kLHGr3MGZWOdXK%2FW74wQ54VGhTGrb%2B%2Fk0AXwZrEah8I%3D&st=2018-02-06T00%3A21%3A23Z&se=2118-02-06T00%3A21%3A23Z',
'https://s26.postimg.org/adbwqzcgp/xmen2.jpg',
'https://s26.postimg.org/ombj9i1eh/x-men-2-.jpg',
'Los X-Men se unen para encontrar un asesino mutante que ha intentado la vida del Presidente, mientras que la Academia Mutante es atacada por las fuerzas militares.',
'N','10','3','A');
INSERT INTO Videos VALUES (NULL,'GANTZ:O','2016-10-14','01:35:31','',
'Gantz:O está basada en el manga original Gantz creado por Hiroya Oku. Gantz:O se ambienta en un futuro cercano en el que un grupo de vigilantes tratan de mantener seguros a los habitantes de la ciudad de Tokyo. Al más puro estilo de una patrulla de policía especializada, este grupo de jóvenes tendrá que enfrentarse a una nueva amenaza que acecha en las calles de la capital japonesa.',
'https://servicesmedia.blob.core.windows.net/asset-926eaada-ff3f-4364-af92-3af4fbe142b6/gantz-movie.MP4?sv=2015-07-08&sr=c&si=504eb10e-5fa3-4133-b1dd-348203173b63&sig=CSHWxYB2i3llbyWp6V9c5uCAldoc2%2B%2FHPH%2BphU%2B3TO0%3D&st=2018-02-05T22%3A49%3A45Z&se=2118-02-05T22%3A49%3A45Z',
'https://s26.postimg.org/bxh8pl2x5/gantzo-movie.jpg',
'https://s26.postimg.org/ubrrtdcl5/gantz-movie.jpg',
'Después de ser brutalmente asesinado en una estación de metro, un adolescente despierta y se ve obligado a luchar contra una gran fuerza de invasores extranjeros en Osaka.',
'N','16','4','A');
INSERT INTO Videos VALUES (NULL,'Mi Villano Favorito','2010-07-24','01:34:48','',
'En una alegre urbanización con cuidados jardines delimitados por vallas de madera pintadas de blanco y llenos de rosales, sobresale una casa negra con el césped amarillento. Los vecinos ignoran que debajo de la vivienda hay un enorme escondite secreto. Allí está Gru, rodeado por un pequeño ejército de lacayos, planeando el mayor robo de toda la Historia: quiere robar la Luna. Gru es malo y disfruta siéndolo. Armado con un poderoso arsenal de rayos menguantes y congelantes, así como vehículos de combate de tierra y aire, derriba a cualquiera que se interponga en su camino. Hasta el día en que se topa con la tremenda testarudez de tres niñas huérfanas que ven algo inaudito en este oscuro personaje: un padre en potencia.',
'https://servicesmedia.blob.core.windows.net/asset-c37dbcc9-a993-433e-8f70-375e21477b3b/Despcable-me-1.mp4?sv=2015-07-08&sr=c&si=f897de5f-2454-435e-b831-2ab93831e224&sig=Hu5l9CHMxBwKok71Hoy%2B4HTLzEnZzlZMpsf3j8QpC8c%3D&st=2018-02-07T22%3A19%3A42Z&se=2118-02-07T22%3A19%3A42Z',
'https://s26.postimg.org/sswdod3ft/despicable-me1.jpg',
'https://s26.postimg.org/6ksebb9u1/despicableme1.jpg',
'Gru es un hombre deplorable que planea el acto criminal más increíble de la Historia: robar la Luna. Incitado por una madre malvada, sólo encontrará un obstáculo en su camino: tres niñas huérfanas a las que tendá que cuidar temporalmente.',
'N','16','1','A');


CREATE OR REPLACE VIEW VW_LIST_VIDEOS AS
SELECT 
Videos.CODVID AS codvideo,
Videos.NOMVID AS nomvideo,
DATE_FORMAT(Videos.YEARVID,"%d/%M/%Y") AS fechavid,
Videos.DURAVID AS duravideo,
Videos.DESVID AS desvideo,
Videos.UBIVID AS ubivideo,
Videos.PORTVID AS portada,
Videos.IMGVID AS imgvideo,
Videos.SINVID AS sinopsis,
Videos.TAGVID AS tagvideo,
Videos.CODCAT AS codcategoria,
Categoria.NOMCAT AS nomcategoria,
Videos.CODGEN AS codgenero,
Genero.NOMGEN AS nomgenero,
Videos.ESTVID AS estvideo,
CASE
    WHEN Genero.NOMGEN = 'BLANCO' THEN "PG"
    WHEN Genero.NOMGEN = 'AMARILLO' THEN "+ 05"
    WHEN Genero.NOMGEN = 'PURPURA' THEN "+ 12"
    WHEN Genero.NOMGEN = 'ROJO' THEN "+ 16"
    WHEN Genero.NOMGEN = 'NEGRO' THEN "+ 18"
END AS genero
FROM Videos INNER JOIN Categoria ON Videos.CODCAT = Categoria.CODCAT
INNER JOIN Genero ON Videos.CODGEN = Genero.CODGEN WHERE Videos.ESTVID = 'A' ORDER BY YEARVID DESC;


CREATE OR REPLACE VIEW VW_LIST_PROXIMOS AS
SELECT 
Videos.CODVID AS codvideo,
Videos.NOMVID AS nomvideo,
DATE_FORMAT(Videos.YEARVID,"%d/%M/%Y") AS fechavid,
Videos.DURAVID AS duravideo,
Videos.TRAILER AS trailer,
Videos.DESVID AS desvideo,
Videos.UBIVID AS ubivideo,
Videos.PORTVID AS portada,
Videos.IMGVID AS imgvideo,
Videos.SINVID AS sinopsis,
Videos.TAGVID AS tagvideo,
Videos.CODCAT AS codcategoria,
Categoria.NOMCAT AS nomcategoria,
Videos.CODGEN AS codgenero,
Genero.NOMGEN AS nomgenero,
Videos.ESTVID AS estvideo,
CASE
    WHEN Genero.NOMGEN = 'BLANCO' THEN "PG"
    WHEN Genero.NOMGEN = 'AMARILLO' THEN "+ 05"
    WHEN Genero.NOMGEN = 'PURPURA' THEN "+ 12"
    WHEN Genero.NOMGEN = 'ROJO' THEN "+ 16"
    WHEN Genero.NOMGEN = 'NEGRO' THEN "+ 18"
END AS genero
FROM Videos INNER JOIN Categoria ON Videos.CODCAT = Categoria.CODCAT
INNER JOIN Genero ON Videos.CODGEN = Genero.CODGEN WHERE Videos.ESTVID = 'P';

CREATE OR REPLACE VIEW VW_LIST_HISTORIAL AS
SELECT 
Historial.CODHIS AS codigohis,
Historial.FECHAHIS AS fechahis,
Historial.CODPER AS codigoper,
Historial.CODVID AS codigovid,
Videos.NOMVID AS nomvideo,
DATE_FORMAT(Videos.YEARVID,"%d/%M/%Y") AS fechavid,
Videos.DURAVID AS duravideo,
Videos.DESVID AS desvideo,
Videos.UBIVID AS ubivideo,
Videos.PORTVID AS portada,
Videos.IMGVID AS imgvideo,
Videos.SINVID AS sinopsis,
Videos.TAGVID AS tagvideo,
Videos.CODCAT AS codcategoria,
Categoria.NOMCAT AS nomcategoria,
Videos.CODGEN AS codgenero,
Genero.NOMGEN AS nomgenero,
Videos.ESTVID AS estvideo,
CASE
    WHEN Genero.NOMGEN = 'BLANCO' THEN "PG"
    WHEN Genero.NOMGEN = 'AMARILLO' THEN "+ 05"
    WHEN Genero.NOMGEN = 'PURPURA' THEN "+ 12"
    WHEN Genero.NOMGEN = 'ROJO' THEN "+ 16"
    WHEN Genero.NOMGEN = 'NEGRO' THEN "+ 18"
END AS genero
FROM Historial INNER JOIN Personas ON Historial.CODPER = Personas.CODPER 
INNER JOIN Videos ON Historial.CODVID = Videos.CODVID INNER JOIN Categoria ON Videos.CODCAT = Categoria.CODCAT
INNER JOIN Genero ON Videos.CODGEN = Genero.CODGEN WHERE Videos.ESTVID = 'A';

CREATE OR REPLACE VIEW VW_LIST_MILISTA AS
SELECT 
Lista.CODLIST AS codigolis,
Lista.CODPER AS codigoper,
Lista.CODVID AS codigovid,
Videos.NOMVID AS nomvideo,
DATE_FORMAT(Videos.YEARVID,"%d/%M/%Y") AS fechavid,
Videos.DURAVID AS duravideo,
Videos.DESVID AS desvideo,
Videos.UBIVID AS ubivideo,
Videos.PORTVID AS portada,
Videos.IMGVID AS imgvideo,
Videos.SINVID AS sinopsis,
Videos.TAGVID AS tagvideo,
Videos.CODCAT AS codcategoria,
Categoria.NOMCAT AS nomcategoria,
Videos.CODGEN AS codgenero,
Genero.NOMGEN AS nomgenero,
Videos.ESTVID AS estvideo,
CASE
    WHEN Genero.NOMGEN = 'BLANCO' THEN "PG"
    WHEN Genero.NOMGEN = 'AMARILLO' THEN "+ 05"
    WHEN Genero.NOMGEN = 'PURPURA' THEN "+ 12"
    WHEN Genero.NOMGEN = 'ROJO' THEN "+ 16"
    WHEN Genero.NOMGEN = 'NEGRO' THEN "+ 18"
END AS genero
FROM Lista INNER JOIN Personas ON Lista.CODPER = Personas.CODPER 
INNER JOIN Videos ON Lista.CODVID = Videos.CODVID INNER JOIN Categoria ON Videos.CODCAT = Categoria.CODCAT
INNER JOIN Genero ON Videos.CODGEN = Genero.CODGEN WHERE Videos.ESTVID = 'A';