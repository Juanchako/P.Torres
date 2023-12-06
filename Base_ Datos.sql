-- Crear la base de datos
CREATE DATABASE JaimeTorres;
USE JaimeTorres;

-- Crear la tabla Artistas
CREATE TABLE artistas (
    ID INT not null PRIMARY KEY auto_increment,
    TIPO_IDE VARCHAR(5) not null,
    NUMERO_IDE VARCHAR(20) not null,
    NOMBRES VARCHAR(30) not null,
    APELLIDOS VARCHAR(30) not null);

-- Crear la tabla Obras
CREATE TABLE obras (
    ID INT not null PRIMARY KEY auto_increment,
    NOMBRE VARCHAR(20) not null,
    PAIS VARCHAR(30) not null,
    ID_ARTISTA INT not null);
    
alter table obras add constraint fk_artistas_obras
foreign key(ID_ARTISTA) references artistas(ID);
 
 -- Crear la tabla Museos
CREATE TABLE Museos (
    ID int not null PRIMARY KEY auto_increment,
    NOMBRE VARCHAR(30) not null,
    PAIS VARCHAR(30) not null,
    CIUDAD VARCHAR(30) not null,
    DIRECCION VARCHAR(30) not null);
 
-- Crear la tabla Museos_x_obras
CREATE TABLE Museos_x_obras (
    ID_MUSEO INT not null,
    ID_OBRA INT not null,
    PRIMARY KEY (ID_MUSEO, ID_OBRA),
   
FOREIGN KEY (ID_OBRA) REFERENCES obras(ID),
FOREIGN KEY (ID_MUSEO) REFERENCES museos(ID));


