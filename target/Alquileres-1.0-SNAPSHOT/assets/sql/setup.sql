DROP DATABASE IF EXISTS `alquileres`;
CREATE DATABASE `alquileres`;
USE `alquileres`;

CREATE TABLE propietario
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    usuario VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE propiedad
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    direccion VARCHAR(255) NOT NULL,
    numero VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    estado ENUM('DISPONIBLE', 'OCUPADO', 'FUERA DE SERVICIO') DEFAULT 'DISPONIBLE',
    fechaRegistro DATETIME DEFAULT CURRENT_TIMESTAMP,
    propietario_id INT NOT NULL,
    FOREIGN KEY (propietario_id) REFERENCES propietario(id)
);

CREATE TABLE inquilino
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cedula VARCHAR(255) NOT NULL,
    nombres VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    telefono VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL
);

CREATE TABLE pago
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fechaRegistro DATETIME DEFAULT CURRENT_TIMESTAMP,
    monto DECIMAL(10,2) NOT NULL,
    formaPago ENUM('EFECTIVO', 'TRANSFERENCIA', 'DEPOSITO') DEFAULT 'EFECTIVO',
    detallePago VARCHAR(255) NOT NULL,
    estado BOOLEAN DEFAULT true,
    inquilino_id INT NOT NULL,
    FOREIGN KEY (inquilino_id) REFERENCES inquilino(id)
);

CREATE TABLE contrato
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    valorMensual DECIMAL(10,2) NOT NULL,
    diaMaximoDePago INT NOT NULL,
    fechaInicioContrato DATETIME NOT NULL,
    fechaFinContrato DATETIME NOT NULL,
    estado BOOLEAN DEFAULT true,
    inquilino_id INT NOT NULL,
    propiedad_id INT NOT NULL,
    FOREIGN KEY (propiedad_id) REFERENCES propiedad(id),
    FOREIGN KEY (inquilino_id) REFERENCES inquilino(id)
);

INSERT INTO propietario (nombres, apellidos, usuario, password) VALUES ('Pablo', 'Perez', 'admin', '1234');