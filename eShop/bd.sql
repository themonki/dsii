--base de datos para correr el ejemplo

CREATE TABLE productos
(
   id VARCHAR(3) PRIMARY KEY,
   nombre VARCHAR(20),
   precio INTEGER
);

CREATE TABLE usuario
(
   username VARCHAR(10) PRIMARY KEY,
   password VARCHAR(10),
   card_num VARCHAR(10)
);

INSERT INTO productos VALUES('p1','Pencil',12);
INSERT INTO productos VALUES('p2','Eraser',8);
INSERT INTO productos VALUES('p3','Ball Pen',21);

INSERT INTO usuario VALUES('u1','p1','1234');
INSERT INTO usuario VALUES('u2','p2','1245');
INSERT INTO usuario VALUES('u3','p3','8542');
