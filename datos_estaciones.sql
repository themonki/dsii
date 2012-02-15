
ALTER SEQUENCE estacion_id_seq RESTART WITH 1;

DELETE FROM estacion;
DELETE FROM estacion_paradero;
DELETE FROM estacion_principal;
DELETE FROM ruta;
DELETE FROM ruta_formado_estacion;

INSERT INTO estacion VALUES(DEFAULT,'Carrera 1 #62-05',true);
INSERT INTO estacion VALUES(DEFAULT,'Carrera 1 #56-10',true);
INSERT INTO estacion VALUES(DEFAULT,'Carrera 1 #47-03',true);
INSERT INTO estacion VALUES(DEFAULT,'Carrera 1 #44-20',true);
INSERT INTO estacion VALUES(DEFAULT,'Carrera 1 #39-01',true);
INSERT INTO estacion VALUES(DEFAULT,'Carrera 1 #30-00',true);
INSERT INTO estacion VALUES(DEFAULT,'Carrera 1 #24-56',true);
INSERT INTO estacion VALUES(DEFAULT,'Carrera 1 #21-1',true);
INSERT INTO estacion VALUES(DEFAULT,'Carrera 4 #23-13',true);
INSERT INTO estacion VALUES(DEFAULT,'Avenida 3N #52N-02',true);
INSERT INTO estacion VALUES(DEFAULT,'Avenida 3N #44N-00',true);
INSERT INTO estacion VALUES(DEFAULT,'Avenida 3N #38N-01',true);
INSERT INTO estacion VALUES(DEFAULT,'Avenida Americas #24N-01',true);
INSERT INTO estacion VALUES(DEFAULT,'Avenida Americas #21-00',true);
INSERT INTO estacion VALUES(DEFAULT,'Avenida 3N #15-01',true);
INSERT INTO estacion VALUES(DEFAULT,'Calle 70 #1-00',true);
INSERT INTO estacion VALUES(DEFAULT,'Calle 70 #4CN-02',true);
INSERT INTO estacion VALUES(DEFAULT,'Calle 70 #4N-56',true);
INSERT INTO estacion VALUES(DEFAULT,'Avenida 6N #70-02',true);
INSERT INTO estacion VALUES(DEFAULT,'Avenida 6N #41-00',true);
INSERT INTO estacion VALUES(DEFAULT,'Avenida 6N #30N-32',true);
INSERT INTO estacion VALUES(DEFAULT,'Avenida 6N #25-02',true);
INSERT INTO estacion VALUES(DEFAULT,'Avenida 6N #22-02',true);
INSERT INTO estacion VALUES(DEFAULT,'Calle 18 #4N-02',true);

INSERT INTO estacion_principal (id_estacion, nombre, id_operario) VALUES (1,'Chiminangos','7');
INSERT INTO estacion_principal (id_estacion, nombre, id_operario) VALUES (2,'Flora Industrial','7');
INSERT INTO estacion_principal (id_estacion, nombre, id_operario) VALUES (3,'Salomia','7');
INSERT INTO estacion_principal (id_estacion, nombre, id_operario) VALUES (4,'Popular','7');
INSERT INTO estacion_principal (id_estacion, nombre, id_operario) VALUES (5,'Manzanares','7');
INSERT INTO estacion_principal (id_estacion, nombre, id_operario) VALUES (6,'Fatima','7');
INSERT INTO estacion_principal (id_estacion, nombre, id_operario) VALUES (7,'Piloto','7');
INSERT INTO estacion_principal (id_estacion, nombre, id_operario) VALUES (8,'San Nicolas','7');
INSERT INTO estacion_principal (id_estacion, nombre, id_operario) VALUES (9,'Rio Cali','7');
INSERT INTO estacion_principal (id_estacion, nombre, id_operario) VALUES (10,'Alamos','7');
INSERT INTO estacion_principal (id_estacion, nombre, id_operario) VALUES (11,'Vipasa','7');
INSERT INTO estacion_principal (id_estacion, nombre, id_operario) VALUES (12,'Prados del norte','7');
INSERT INTO estacion_principal (id_estacion, nombre, id_operario) VALUES (13,'Las americas','7');
INSERT INTO estacion_principal (id_estacion, nombre, id_operario) VALUES (14,'Versalles','7');
INSERT INTO estacion_principal (id_estacion, nombre, id_operario) VALUES (15,'Torre de Cali','7');

INSERT INTO estacion_paradero VALUES(16);
INSERT INTO estacion_paradero VALUES(17);
INSERT INTO estacion_paradero VALUES(18);
INSERT INTO estacion_paradero VALUES(19);
INSERT INTO estacion_paradero VALUES(20);
INSERT INTO estacion_paradero VALUES(21);
INSERT INTO estacion_paradero VALUES(22);
INSERT INTO estacion_paradero VALUES(23);
INSERT INTO estacion_paradero VALUES(24);


INSERT INTO ruta VALUES ('E31','chiminango-rioCali','t');
INSERT INTO ruta VALUES ('E21','alamos-torreDeCali','t');
INSERT INTO ruta VALUES ('E27','salomialasAmericas','t');
INSERT INTO ruta VALUES ('P30A', 'floraIndustrial-TorreDeCali','t');

INSERT INTO ruta_formado_estacion VALUES ('E31',1);
INSERT INTO ruta_formado_estacion VALUES ('E31',2);
INSERT INTO ruta_formado_estacion VALUES ('E31',3);
INSERT INTO ruta_formado_estacion VALUES ('E31',4);
INSERT INTO ruta_formado_estacion VALUES ('E31',5);
INSERT INTO ruta_formado_estacion VALUES ('E31',6);
INSERT INTO ruta_formado_estacion VALUES ('E31',7);
INSERT INTO ruta_formado_estacion VALUES ('E31',8);
INSERT INTO ruta_formado_estacion VALUES ('E31',9);
INSERT INTO ruta_formado_estacion VALUES ('E21',10);
INSERT INTO ruta_formado_estacion VALUES ('E21',11);
INSERT INTO ruta_formado_estacion VALUES ('E21',12);
INSERT INTO ruta_formado_estacion VALUES ('E21',13);
INSERT INTO ruta_formado_estacion VALUES ('E21',14);
INSERT INTO ruta_formado_estacion VALUES ('E21',15);
INSERT INTO ruta_formado_estacion VALUES ('E27',3);
INSERT INTO ruta_formado_estacion VALUES ('E27',2);
INSERT INTO ruta_formado_estacion VALUES ('E27',1);
INSERT INTO ruta_formado_estacion VALUES ('E27',10);
INSERT INTO ruta_formado_estacion VALUES ('E27',11);
INSERT INTO ruta_formado_estacion VALUES ('E27',12);
INSERT INTO ruta_formado_estacion VALUES ('E27',13);
INSERT INTO ruta_formado_estacion VALUES ('P30A',2);
INSERT INTO ruta_formado_estacion VALUES ('P30A',16);
INSERT INTO ruta_formado_estacion VALUES ('P30A',17);
INSERT INTO ruta_formado_estacion VALUES ('P30A',18);
INSERT INTO ruta_formado_estacion VALUES ('P30A',19);
INSERT INTO ruta_formado_estacion VALUES ('P30A',20);
INSERT INTO ruta_formado_estacion VALUES ('P30A',21);
INSERT INTO ruta_formado_estacion VALUES ('P30A',22);
INSERT INTO ruta_formado_estacion VALUES ('P30A',23);
INSERT INTO ruta_formado_estacion VALUES ('P30A',24);
INSERT INTO ruta_formado_estacion VALUES ('P30A',15);



