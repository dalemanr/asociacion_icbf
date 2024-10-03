create database asociacion_icbf;

use asociacion_icbf;


create table roles(
idrol int primary key not null auto_increment,
nombre varchar (45)
);


create table usuarios(
idusuario int primary key not null auto_increment,
foto varchar(255),
cedula varchar(45),
nombre varchar(45),
apellidos varchar(45),
fecha_nacimiento date,
telefono varchar(45),
celular varchar(45),
direccion varchar(45),
correo varchar (45),
clave varchar(45),
idrol int,

foreign key (idrol) references roles(idrol)

);

create table administradores(
idadministrador int primary key not null auto_increment,
idusuario int,


foreign key (idusuario) references usuarios(idusuario)
);


create table acudientes(
idacudiente int primary key not null auto_increment,
idusuario int,


foreign key (idusuario) references usuarios(idusuario)
);


create table jardines(
idjardin int primary key not null auto_increment,
nombre varchar(255),
estado varchar(255)

);


create table madres_comunitarias(
idmadrecomunitaria int primary key not null auto_increment,
idusuario int,
idjardin int,

foreign key (idjardin) references jardines(idjardin),
foreign key (idusuario) references usuarios(idusuario)
);


create table kids(
idkid int primary key not null auto_increment,
niup varchar(50),
foto varchar(255),
nombre varchar (255),
apellido varchar(255),
fecha_nacimiento date,
sangre varchar(45),
ciudad_nacimiento varchar(45),
eps varchar(45),
idacudiente int,
idjardin int,


foreign key(idjardin) references jardines(idjardin),
foreign key(idacudiente) references acudientes(idacudiente)
);


create table asistencia(
idasistencia int primary key not null auto_increment,
asistencia varchar(45),
fecha date default CURRENT_TIMESTAMP,
descripción varchar(45),
idkid int,

foreign key (idkid) references kids(idkid)
);


create table avance_academico(
idavance_academico int primary key not null auto_increment,
año_escolar varchar(45),
nivel varchar(45),
nota varchar(45),
descripcion varchar(255),
fecha_entrega_notas date,
idkid int,

foreign key(idkid) references kids(idkid)
);

INSERT INTO `asociacion_icbf`.`roles` (`nombre`) VALUES ('Administrador');
INSERT INTO `asociacion_icbf`.`roles` (`nombre`) VALUES ('Madre Comunitaria');
INSERT INTO `asociacion_icbf`.`roles` (`nombre`) VALUES ('Acudiente');

INSERT INTO `asociacion_icbf`.`usuarios` (`foto`, `cedula`, `nombre`, `apellidos`, `fecha_nacimiento`, `telefono`, `celular`, `direccion`, `correo`, `clave`, `idrol`) VALUES ('', '123432423', 'Sebas', 'Rojas', '2000-11-10', '3108990987', '3108990987', 'cl 187c #6-39', 'jdaleman5324@gmail.com', '1234567', '1');

DELIMITER $
CREATE TRIGGER insertar_rol AFTER INSERT ON usuarios
FOR EACH ROW
BEGIN
    IF NEW.idrol = 1 THEN
        INSERT INTO administradores (idusuario) VALUES (NEW.idusuario);   
	ELSEIF NEW.idrol = 3 THEN
        INSERT INTO acudientes (idusuario) VALUES (NEW.idusuario);
    END IF;
end $

SELECT COUNT(kids.niup) AS cantidad_niup, jardines.nombre 
FROM jardines 
LEFT JOIN kids ON kids.idjardin = jardines.idjardin 
GROUP BY jardines.nombre;
 
select kids.nombre, kids.apellido, kids.niup, asistencia.fecha, asistencia.descripción
from kids inner join asistencia where asistencia.idkid = kids.idkid and asistencia.descripción = "enfermedad";

select * from jardines where jardines.estado="Denegado";

select * from asistencia 
inner join kids on kids.idkid = asistencia.idkid
inner join jardines on jardines.idjardin = kids.idjardin
WHERE asistencia.fecha >= DATE_SUB(NOW(), INTERVAL 1 WEEK);



