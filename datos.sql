INSERT INTO empleado VALUES('1','cc','0001-01-01',0,'000','prueba','Administrador','prueba','0001-01-01',0,'admin',md5('admin'),true);
INSERT INTO empleado VALUES('2','cc','0001-01-01',0,'000','prueba','Director','prueba','0001-01-01',1,'dir',md5('dir'),true);
INSERT INTO empleado VALUES('3','cc','0001-01-01',0,'000','prueba','Operario','prueba','0001-01-01',2,'oper',md5('oper'),true);
INSERT INTO empleado VALUES('4','cc','0001-01-01',0,'000','prueba','Auxiliar','prueba','0001-01-01',3,'aux',md5('aux'),true);
INSERT INTO empleado VALUES('5','cc','0001-01-01',0,'000','prueba','Conductor','prueba','0001-01-01',4,'cond',md5('cond'),true);

INSERT INTO director VALUES('2');
INSERT INTO operario VALUES('3','2');
INSERT INTO auxiliar (id,id_jefe) VALUES('4','3');
INSERT INTO conductor (id) VALUES('5');
