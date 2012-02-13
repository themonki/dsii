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

--insertar datos

--insertar empleados
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


INSERT INTO estacion VALUES(DEFAULT,'en algun lugar1',true);
INSERT INTO estacion VALUES(DEFAULT,'en algun lugar2',true);
INSERT INTO estacion VALUES(DEFAULT,'en algun lugar3',true);
INSERT INTO estacion VALUES(DEFAULT,'en algun lugar4',true);
INSERT INTO estacion VALUES(DEFAULT,'en algun lugar5',true);
INSERT INTO estacion VALUES(DEFAULT,'en algun lugar6',true);

INSERT INTO estacion_principal (id_estacion, nombre) VALUES (1,'estacion1');
INSERT INTO estacion_principal (id_estacion, nombre) VALUES (2,'estacion2');
INSERT INTO estacion_principal (id_estacion, nombre) VALUES (3,'estacion3');
INSERT INTO estacion_principal (id_estacion, nombre) VALUES (4,'estacion4');
INSERT INTO estacion_paradero VALUES(5);
INSERT INTO estacion_paradero VALUES(6);


--Insertando algunas  Tarjetas
insert into tarjeta values('001',0,true,1,0,'1-1-1',1); -- Representa la tarjeta del usuario generico
insert into tarjeta values('002',0,true,1,0,'1-1-1',1);
insert into tarjeta values('003',0,true,1,0,'1-1-1',1);
insert into tarjeta values('004',0,true,1,0,'1-1-1',1);
--Insertar Tarjeta Personalizada
insert into tarjeta_personalizada values ('001',1);
insert into tarjeta_personalizada values ('002',1);
insert into tarjeta_personalizada values ('003',1);
insert into tarjeta_personalizada values ('004',1);
--Insertar Usuarios:
insert into usuario values('0000001','CC','Generico','Generico','Generico','Generico','1-1-1','g','001','123',true);
insert into usuario values('1144150245','CC','Generico','g','g','g','1-1-1','g','002','123',true);
insert into usuario values('1144150246','CC','Generico','g','g','g','1-1-1','g','003','123',true);
insert into usuario values('1144150247','CC','Generico','g','g','g','1-1-1','g','004','123',true);



