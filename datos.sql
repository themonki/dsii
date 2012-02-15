--por si se llega a necesitar
--como saber que valor tiene una sucuencia
--select last_value from nombre_sucuencia;

--limpiar tablas
ALTER SEQUENCE estacion_id_seq RESTART WITH 1;
ALTER SEQUENCE medida_id_seq RESTART WITH 1;
ALTER SEQUENCE reclamo_ticket_seq RESTART WITH 1;

DELETE FROM auxiliar;
DELETE FROM bus;
DELETE FROM conductor;
DELETE FROM director;
DELETE FROM empleado;
DELETE FROM estacion;
DELETE FROM estacion_paradero;
DELETE FROM estacion_principal;
DELETE FROM medida;
DELETE FROM medida_reclamo_operario_agrega;
DELETE FROM operario;
DELETE FROM recarga;
DELETE FROM reclamo;
DELETE FROM ruta;
DELETE FROM ruta_formado_estacion;
DELETE FROM tarjeta;
DELETE FROM tarjeta_generica;
DELETE FROM tarjeta_personalizada;
DELETE FROM tarjeta_uso_ruta;
DELETE FROM usuario;

--INSERTar datos

--INSERTar empleados
INSERT INTO empleado VALUES('1','cc',0,'000','prueba','Administrador','','prueba','','0001-01-01','0001-01-01',0,'email','admin',md5('admin'),true);
INSERT INTO empleado VALUES('2','cc',0,'000','prueba','Director','','prueba','','0001-01-01','0001-01-01',1,'email','dir',md5('dir'),true);
INSERT INTO empleado VALUES('3','cc',0,'000','prueba','Operario','','prueba','','0001-01-01','0001-01-01',2,'email','oper',md5('oper'),true);
INSERT INTO empleado VALUES('4','cc',0,'000','prueba','Auxiliar','','prueba','','0001-01-01','0001-01-01',3,'email','aux',md5('aux'),true);
INSERT INTO empleado VALUES('5','cc',0,'000','prueba','Conductor','','prueba','','0001-01-01','0001-01-01',4,'email','cond',md5('cond'),true);

INSERT INTO director VALUES('2');
INSERT INTO operario VALUES('3','2');
INSERT INTO auxiliar (id,id_jefe) VALUES('4','3');
INSERT INTO conductor (id) VALUES('5');

INSERT INTO empleado (id, nombre, apellido, login, password, estado,rol) VALUES ('6','seis','seis','seis','seis',true,1);
INSERT INTO empleado (id, nombre, apellido, login, password, estado,rol) VALUES ('7','siete','siete','siete','siete',true,2);
INSERT INTO empleado (id, nombre, apellido, login, password, estado,rol) VALUES ('8','ocho','ocho','ocho','ocho',true,3);
INSERT INTO empleado (id, nombre, apellido, login, password, estado,rol) VALUES ('9','nueve','nueve','nueve','nueve',true,4);

INSERT INTO director VALUES('6');
INSERT INTO operario VALUES('7','2');
INSERT INTO auxiliar (id,id_jefe) VALUES('8','3');
INSERT INTO conductor (id) VALUES('9');


INSERT INTO estacion VALUES(DEFAULT,'B. Chiminangos',true);
INSERT INTO estacion VALUES(DEFAULT,'B. Flora Industrial',true);
INSERT INTO estacion VALUES(DEFAULT,'Universidad el Valle',true);
INSERT INTO estacion VALUES(DEFAULT,'Jardin Plaza',true);
INSERT INTO estacion VALUES(DEFAULT,'B. Salomia',true);
INSERT INTO estacion VALUES(DEFAULT,'B. Manzanares',true);
INSERT INTO estacion VALUES(DEFAULT,'B. Buitrera',true);
INSERT INTO estacion VALUES(DEFAULT,'B. Melendez',true);
INSERT INTO estacion VALUES(DEFAULT,'C. 56 # 32-44',true);
INSERT INTO estacion VALUES(DEFAULT,'C. 7 # 72-04',true);
INSERT INTO estacion VALUES(DEFAULT,'C. 8 # 52-03',true);
INSERT INTO estacion VALUES(DEFAULT,'C. 4 #  2-44',true);

INSERT INTO estacion_principal (id_estacion, nombre) VALUES (1,'Chiminangos');
INSERT INTO estacion_principal (id_estacion, nombre) VALUES (2,'Flora Industrial');
INSERT INTO estacion_principal (id_estacion, nombre) VALUES (3,'Univalle');
INSERT INTO estacion_principal (id_estacion, nombre) VALUES (4,'Universidades');
INSERT INTO estacion_principal (id_estacion, nombre) VALUES (5,'Salomia');
INSERT INTO estacion_principal (id_estacion, nombre) VALUES (6,'Manzanares');
INSERT INTO estacion_principal (id_estacion, nombre) VALUES (7,'Buitrera');
INSERT INTO estacion_principal (id_estacion, nombre) VALUES (8,'Melendez');
INSERT INTO estacion_paradero VALUES(9);
INSERT INTO estacion_paradero VALUES(10);
INSERT INTO estacion_paradero VALUES(11);
INSERT INTO estacion_paradero VALUES(12);


--Insertando algunas  Tarjetas
INSERT INTO tarjeta VALUES('001',0,true,1,0,'1-1-1',1); -- Representa la tarjeta del usuario generico
INSERT INTO tarjeta VALUES('002',0,true,1,0,'1-1-1',1);
INSERT INTO tarjeta VALUES('003',0,true,1,0,'1-1-1',1);
INSERT INTO tarjeta VALUES('004',0,true,1,0,'1-1-1',1);
INSERT INTO tarjeta VALUES('005',0,true,1,0,'1-1-1',1); -- Representa la tarjeta del usuario generico
INSERT INTO tarjeta VALUES('006',0,true,1,0,'1-1-1',1);
INSERT INTO tarjeta VALUES('007',0,true,1,0,'1-1-1',1);
INSERT INTO tarjeta VALUES('008',0,true,1,0,'1-1-1',1);
--Insertar Tarjeta Personalizada
INSERT INTO tarjeta_personalizada VALUES ('001',1);
INSERT INTO tarjeta_personalizada VALUES ('002',1);
INSERT INTO tarjeta_personalizada VALUES ('003',1);
INSERT INTO tarjeta_personalizada VALUES ('004',1);
INSERT INTO tarjeta_personalizada VALUES ('005',1);
INSERT INTO tarjeta_personalizada VALUES ('006',1);
INSERT INTO tarjeta_personalizada VALUES ('007',1);
INSERT INTO tarjeta_personalizada VALUES ('008',1);
--Insertar Usuarios:

INSERT INTO usuario VALUES('0000001','CC','Usuario Generico','Usuario Genrico','Generico','Generico','1-1-1','000000','001','123',true);
INSERT INTO usuario VALUES('1144150245','CC','Yerminson','Gonzalez','Calle 56 # 1D2-42','yermigon@gmail.com','1991-07-20','3701837','002','loro',true);
INSERT INTO usuario VALUES('1144150246','CC','Andres','Escobar','Calle 10 # 16-42','andrescobar@gmail.com','1981-07-20','4304489','003','loro',true);
INSERT INTO usuario VALUES('1144150247','CC','Camilo','Puerta','Calle 20 # 15-42','camilopuerta@gmail.com','1971-07-20','3212121','004','loro',true);
INSERT INTO usuario VALUES('1144150248','CC','Hernan','Aguirre','Calle 35 # 13-42','hernanaguirre@gmail.com','1961-07-20','3808080','005','loro',true);
INSERT INTO usuario VALUES('1144150249','CC','Catalina','Alarcon','Calle 45 # 12-42','catalarcon@gmail.com','2001-07-20','4202020','006','loro',true);
INSERT INTO usuario VALUES('1144150250','CC','Cassandra','Alegria','Calle 56 # 1-32','cassalegria@gmail.com','2005-07-20','3854535','007','loro',true);
INSERT INTO usuario VALUES('1144150251','CC','Alberto','Cortez','Calle 12 # 20-1','albercortez@gmail.com','2007-07-20','4444432','008','loro',true);


INSERT INTO medida VALUES(DEFAULT,'Realizar llamada telefocina usuario');
INSERT INTO medida VALUES(DEFAULT,'Reportar problemas en la estructura de la estacion');
INSERT INTO medida VALUES(DEFAULT,'Realizar llamado de atencion al empleado mencionado');
INSERT INTO medida VALUES(DEFAULT,'Pago de comision');
INSERT INTO medida VALUES(DEFAULT,'Reporte y entrega de pruebas al respecto');
INSERT INTO medida VALUES(DEFAULT,'Visita al usuario');
INSERT INTO medida VALUES(DEFAULT,'Bonificacion de transporte para el usuario');
INSERT INTO medida VALUES(DEFAULT,'Ignorado por falta de argumentos');
INSERT INTO medida VALUES(DEFAULT,'No se atendio por falta de informacion');
INSERT INTO medida VALUES(DEFAULT,'Regalo folleto informativo al usuario');


