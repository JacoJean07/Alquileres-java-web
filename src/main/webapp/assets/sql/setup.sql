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
    fechaInicioContrato DATETIME DEFAULT ('2024-07-01 00:00:00'),
    fechaFinContrato DATETIME DEFAULT ('2024-08-01 00:00:00'),
    estado BOOLEAN DEFAULT true,
    inquilino_id INT NOT NULL,
    propiedad_id INT NOT NULL,
    FOREIGN KEY (propiedad_id) REFERENCES propiedad(id),
    FOREIGN KEY (inquilino_id) REFERENCES inquilino(id)
);

INSERT INTO propietario (nombres, apellidos, usuario, password) VALUES ('Pablo', 'Perez', 'admin', '1234');


# store procedure para obtener los inquilinos
DELIMITER $$
CREATE PROCEDURE `obtenerInquilinos`()
BEGIN
    SELECT * FROM inquilino;
END$$
DELIMITER ;

# store procedure para obtener las propiedades
DELIMITER $$
CREATE PROCEDURE `obtenerPropiedades`()
BEGIN
    SELECT * FROM propiedad;
END$$
DELIMITER ;

# store procedure para obtener los contratos
DELIMITER $$
CREATE PROCEDURE `obtenerContratos`()
BEGIN
    SELECT * FROM contrato;
END$$
DELIMITER ;

# store procedure para obtener los pagos
DELIMITER $$
CREATE PROCEDURE `obtenerPagos`()
BEGIN
    SELECT * FROM pago;
END$$
DELIMITER ;

# store procedure para obtener los inquilinos por id
DELIMITER $$
CREATE PROCEDURE `obtenerInquilinoPorId`(IN id INT)
BEGIN
    SELECT * FROM inquilino WHERE id = id;
END$$
DELIMITER ;

# store procedure para obtener las propiedades por id
DELIMITER $$
CREATE PROCEDURE `obtenerPropiedadePorId`(IN id INT)
BEGIN
    SELECT * FROM propiedad WHERE id = id;
END$$
DELIMITER ;

# store procedure para obtener los contratos por id
DELIMITER $$
CREATE PROCEDURE `obtenerContratoPorId`(IN id INT)
BEGIN
    SELECT * FROM contrato WHERE id = id;
END$$
DELIMITER ;

# store procedure para obtener los pagos por id
DELIMITER $$
CREATE PROCEDURE `obtenerPagoPorId`(IN id INT)
BEGIN
    SELECT * FROM pago WHERE id = id;
END$$
DELIMITER ;

# store procedure para editar los inquilinos por id
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `editarInquilinoPorId`(IN id INT, IN nombres VARCHAR(255), IN apellidos VARCHAR(255), IN cedula VARCHAR(255), IN telefono VARCHAR(255), IN correo VARCHAR(255))
BEGIN
    UPDATE inquilino SET nombres = nombres, apellidos = apellidos, cedula = cedula, telefono = telefono, correo = correo WHERE id = id;
END$$
DELIMITER ;

# store procedure para editar las propiedades por id
DELIMITER $$
CREATE PROCEDURE `editarPropiedadPorId`(IN id INT, IN direccion VARCHAR(255), IN numero VARCHAR(255), IN descripcion VARCHAR(255), IN estado VARCHAR(255))
BEGIN
    UPDATE propiedad SET direccion = direccion, numero = numero, descripcion = descripcion, estado = estado WHERE id = id;
END$$
DELIMITER ;

# store procedure para editar los contratos por id
DELIMITER $$
CREATE PROCEDURE `editarContratoPorId`(IN id INT, IN valorMensual DECIMAL(10,2), IN diaMaximoDePago INT, IN estado BOOLEAN)
BEGIN
    UPDATE contrato SET valorMensual = valorMensual, diaMaximoDePago = diaMaximoDePago, estado = estado WHERE id = id;
END$$
DELIMITER ;

# store procedure para eliminar los inquilinos por id
DELIMITER $$
CREATE PROCEDURE `eliminarInquilinoPorId`(IN in_id INT)
BEGIN
    DELETE FROM inquilino WHERE id = in_id;
END$$
DELIMITER ;


# store procedure para eliminar las propiedades por id
DELIMITER $$
CREATE PROCEDURE `eliminarPropiedadPorId`(IN in_id INT)
BEGIN
    DELETE FROM propiedad WHERE id = in_id;
END$$
DELIMITER ;

# store procedure para eliminar los contratos por id
DELIMITER $$
CREATE PROCEDURE `eliminarContratoPorId`(IN in_id INT)
BEGIN
    DELETE FROM contrato WHERE id = in_id;
END$$
DELIMITER ;

# store procedure para eliminar los pagos por id
DELIMITER $$
CREATE PROCEDURE `eliminarPagoPorId`(IN in_id INT)
BEGIN
    DELETE FROM pago WHERE id = in_id;
END$$
DELIMITER ;

# store procedure para obtener la cantidad de inquilinos
DELIMITER $$
CREATE PROCEDURE `obtenerCantidadInquilinos`()
BEGIN
    SELECT COUNT(*) FROM inquilino;
END$$
DELIMITER ;

# store procedure para obtener la cantidad de propiedades
DELIMITER $$
CREATE PROCEDURE `obtenerCantidadPropiedades`()
BEGIN
    SELECT COUNT(*) FROM propiedad;
END$$
DELIMITER ;

# store procedure para obtener la cantidad de contratos
DELIMITER $$
CREATE PROCEDURE `obtenerCantidadContratos`()
BEGIN
    SELECT COUNT(*) FROM contrato;
END$$
DELIMITER ;

# store procedure para obtener la cantidad de pagos del mes actual
DELIMITER $$
CREATE PROCEDURE `obtenerCantidadPagosDelMesActual`()
BEGIN
    SELECT COUNT(*) FROM pago WHERE MONTH(fechaRegistro) = MONTH(NOW());
END$$
DELIMITER ;

# store procedure para ingresar un inquilino
DELIMITER $$
CREATE PROCEDURE `ingresarInquilino`(IN nombres VARCHAR(255), IN apellidos VARCHAR(255), IN cedula VARCHAR(255), IN telefono VARCHAR(255), IN correo VARCHAR(255))
BEGIN
    INSERT INTO inquilino (nombres, apellidos, cedula, telefono, correo) VALUES (nombres, apellidos, cedula, telefono, correo);
END$$
DELIMITER ;

# store procedure para ingresar una propiedad
DELIMITER $$
CREATE PROCEDURE `ingresarPropiedad`(IN direccion VARCHAR(255), IN numero VARCHAR(255), IN descripcion VARCHAR(255), IN estado VARCHAR(255))
BEGIN
    INSERT INTO propiedad (direccion, numero, descripcion, estado) VALUES (direccion, numero, descripcion, estado);
END$$
DELIMITER ;

# store procedure para ingresar un contrato
DELIMITER $$
CREATE PROCEDURE `ingresarContrato`(IN valorMensual DECIMAL(10,2), IN diaMaximoDePago INT, IN estado BOOLEAN, IN inquilino_id INT, IN propiedad_id INT)
BEGIN
    INSERT INTO contrato (valorMensual, diaMaximoDePago, estado, inquilino_id, propiedad_id) VALUES (valorMensual, diaMaximoDePago, estado, inquilino_id, propiedad_id);
END$$
DELIMITER ;

# store procedure para ingresar un pago
DELIMITER $$
CREATE PROCEDURE `ingresarPago`( IN monto DECIMAL(10,2), IN formaPago ENUM('EFECTIVO', 'TRANSFERENCIA', 'DEPOSITO'), IN detallePago VARCHAR(255), IN estado BOOLEAN, IN inquilino_id INT)
BEGIN
    INSERT INTO pago ( monto, formaPago, detallePago, estado, inquilino_id) VALUES ( monto, formaPago, detallePago, estado, inquilino_id);
END$$
DELIMITER ;


# INSERT INTO DE 20 INQUILINOS EN UN SOLO INSERT

INSERT INTO inquilino (nombres, apellidos, cedula, telefono, correo) 
VALUES 
    ('Juan', 'Perez', '123456789', '123456789', 'juan@gmail.com'),
    ('Pablo', 'Perez', '123456789', '123456789', 'pablo@gmail.com'),
    ('Carlos', 'Perez', '123456789', '123456789', 'carlos@gmail.com'),
    ('Maria', 'Perez', '123456789', '123456789', 'maria@gmail.com'),
    ('Jose', 'Perez', '123456789', '123456789', 'jose@gmail.com'),
    ('Luis', 'Perez', '123456789', '123456789', 'luis@gmail.com'),
    ('Pedro', 'Perez', '123456789', '123456789', 'pedro@gmail.com'),
    ('Daniel', 'Perez', '123456789', '123456789', 'daniel@gmail.com'),
    ('Fernando', 'Perez', '123456789', '123456789', 'fernando@gmail.com'),
    ('Juan', 'Perez', '123456789', '123456789', 'juan@gmail.com'),
    ('Pablo', 'Perez', '123456789', '123456789', 'pablo@gmail.com'),
    ('Carlos', 'Perez', '123456789', '123456789', 'carlos@gmail.com'),
    ('Maria', 'Perez', '123456789', '123456789', 'maria@gmail.com');


INSERT INTO contrato (valorMensual, diaMaximoDePago, estado, inquilino_id, propiedad_id) 
VALUES 
    (1000, 5, true, 19, 3),
    (2000, 5, true, 19, 5),
    (3000, 5, true, 19, 3),
    (4000, 5, true, 19, 3),
    (5000, 5, true, 19, 5),
    (6000, 5, true, 19, 5),
    (7000, 5, true, 19, 5);

INSERT INTO pago (monto, formaPago, detallePago, estado, inquilino_id) 
VALUES 
    (1000, 'EFECTIVO', 'Pago 1', true, 19),
    (2000, 'EFECTIVO', 'Pago 2', true, 19),
    (3000, 'EFECTIVO', 'Pago 3', true, 19),
    (4000, 'EFECTIVO', 'Pago 4', true, 19),
    (5000, 'EFECTIVO', 'Pago 5', true, 19),
    (6000, 'EFECTIVO', 'Pago 6', true, 19),
    (7000, 'EFECTIVO', 'Pago 7', true, 19);