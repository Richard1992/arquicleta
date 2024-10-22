SET NAMES 'utf8mb4';

CREATE DATABASE IF NOT EXISTS db_usuarios;

USE db_usuarios;

-- Tabla para países
CREATE TABLE pais (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    codigo_iso VARCHAR(3) NOT NULL UNIQUE, -- Código ISO 3166 (ej: 'CO' para Colombia)
    moneda VARCHAR(50), -- Moneda oficial del país
    zona_horaria VARCHAR(50) -- Zona horaria del país (ej: 'America/Bogota')
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla para departamentos
CREATE TABLE departamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    codigo VARCHAR(10), -- Código opcional del departamento (puede ser numérico o alfanumérico)
    prefijo_telefonico VARCHAR(10), -- Prefijo telefónico del departamento (opcional)
    id_pais INT,
    FOREIGN KEY (id_pais) REFERENCES pais(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla para ciudades
CREATE TABLE ciudad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    codigo_postal VARCHAR(20), -- Código postal de la ciudad (opcional)
    prefijo_telefonico VARCHAR(10), -- Prefijo telefónico de la ciudad (opcional)
    id_departamento INT,
    FOREIGN KEY (id_departamento) REFERENCES departamento(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla para tipos de usuarios
CREATE TABLE tipo_usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla para usuarios
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    direccion VARCHAR(255),
    edad INT,
    genero ENUM('Masculino', 'Femenino', 'Otro'),
    telefono VARCHAR(20),
    id_tipo_usuario INT,
    id_ciudad INT, -- Relación con la tabla ciudad
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_tipo_usuario) REFERENCES tipo_usuarios(id),
    FOREIGN KEY (id_ciudad) REFERENCES ciudad(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla para calificaciones
CREATE TABLE calificaciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_ciclista INT, -- Usuario que califica (ciclista)
    id_entrenador INT, -- Usuario que es calificado (entrenador)
    calificacion INT CHECK (calificacion BETWEEN 1 AND 5), -- Calificación numérica (1 a 5)
    comentario TEXT, -- Comentario opcional del ciclista
    fecha_calificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Fecha de la calificación
    FOREIGN KEY (id_ciclista) REFERENCES usuarios(id),
    FOREIGN KEY (id_entrenador) REFERENCES usuarios(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO pais (nombre, codigo_iso, moneda, zona_horaria) VALUES 
('Afghanistan', 'AFG', 'Afghan afghani', 'Asia/Kabul'),
('Albania', 'ALB', 'Albanian lek', 'Europe/Tirane'),
('Algeria', 'DZA', 'Algerian dinar', 'Africa/Algiers'),
('Andorra', 'AND', 'Euro', 'Europe/Andorra'),
('Angola', 'AGO', 'Angolan kwanza', 'Africa/Luanda'),
('Argentina', 'ARG', 'Argentine peso', 'America/Argentina/Buenos_Aires'),
('Armenia', 'ARM', 'Armenian dram', 'Asia/Yerevan'),
('Australia', 'AUS', 'Australian dollar', 'Australia/Sydney'),
('Austria', 'AUT', 'Euro', 'Europe/Vienna'),
('Azerbaijan', 'AZE', 'Azerbaijani manat', 'Asia/Baku'),
('Bahamas', 'BHS', 'Bahamian dollar', 'America/Nassau'),
('Bahrain', 'BHR', 'Bahraini dinar', 'Asia/Bahrain'),
('Bangladesh', 'BGD', 'Bangladeshi taka', 'Asia/Dhaka'),
('Barbados', 'BRB', 'Barbadian dollar', 'America/Barbados'),
('Belarus', 'BLR', 'Belarusian ruble', 'Europe/Minsk'),
('Belgium', 'BEL', 'Euro', 'Europe/Brussels'),
('Belize', 'BLZ', 'Belize dollar', 'America/Belize'),
('Benin', 'BEN', 'West African CFA franc', 'Africa/Porto-Novo'),
('Bhutan', 'BTN', 'Bhutanese ngultrum', 'Asia/Thimphu'),
('Bolivia', 'BOL', 'Bolivian boliviano', 'America/La_Paz'),
('Bosnia and Herzegovina', 'BIH', 'Bosnia and Herzegovina convertible mark', 'Europe/Sarajevo'),
('Botswana', 'BWA', 'Botswana pula', 'Africa/Gaborone'),
('Brazil', 'BRA', 'Brazilian real', 'America/Sao_Paulo'),
('Brunei', 'BRN', 'Brunei dollar', 'Asia/Brunei'),
('Bulgaria', 'BGR', 'Bulgarian lev', 'Europe/Sofia'),
('Burkina Faso', 'BFA', 'West African CFA franc', 'Africa/Ouagadougou'),
('Burundi', 'BDI', 'Burundian franc', 'Africa/Bujumbura'),
('Cambodia', 'KHM', 'Cambodian riel', 'Asia/Phnom_Penh'),
('Cameroon', 'CMR', 'Central African CFA franc', 'Africa/Douala'),
('Canada', 'CAN', 'Canadian dollar', 'America/Toronto'),
('Cape Verde', 'CPV', 'Cape Verdean escudo', 'Atlantic/Cape_Verde'),
('Central African Republic', 'CAF', 'Central African CFA franc', 'Africa/Bangui'),
('Chad', 'TCD', 'Central African CFA franc', 'Africa/Ndjamena'),
('Chile', 'CHL', 'Chilean peso', 'America/Santiago'),
('China', 'CHN', 'Renminbi', 'Asia/Shanghai'),
('Colombia', 'COL', 'Colombian peso', 'America/Bogota'),
('Comoros', 'COM', 'Comorian franc', 'Indian/Comoro'),
('Congo (Congo-Brazzaville)', 'COG', 'Central African CFA franc', 'Africa/Brazzaville'),
('Congo (Congo-Kinshasa)', 'COD', 'Congolese franc', 'Africa/Kinshasa'),
('Costa Rica', 'CRI', 'Costa Rican colon', 'America/Costa_Rica'),
('Croatia', 'HRV', 'Euro', 'Europe/Zagreb'),
('Cuba', 'CUB', 'Cuban peso', 'America/Havana'),
('Cyprus', 'CYP', 'Euro', 'Asia/Nicosia'),
('Czech Republic', 'CZE', 'Czech koruna', 'Europe/Prague'),
('Denmark', 'DNK', 'Danish krone', 'Europe/Copenhagen'),
('Djibouti', 'DJI', 'Djiboutian franc', 'Africa/Djibouti'),
('Dominica', 'DMA', 'East Caribbean dollar', 'America/Dominica'),
('Dominican Republic', 'DOM', 'Dominican peso', 'America/Santo_Domingo'),
('Ecuador', 'ECU', 'United States dollar', 'America/Guayaquil'),
('Egypt', 'EGY', 'Egyptian pound', 'Africa/Cairo'),
('El Salvador', 'SLV', 'United States dollar', 'America/El_Salvador'),
('Equatorial Guinea', 'GNQ', 'Central African CFA franc', 'Africa/Malabo'),
('Eritrea', 'ERI', 'Eritrean nakfa', 'Africa/Asmara'),
('Estonia', 'EST', 'Euro', 'Europe/Tallinn'),
('Eswatini', 'SWZ', 'Swazi lilangeni', 'Africa/Mbabane'),
('Ethiopia', 'ETH', 'Ethiopian birr', 'Africa/Addis_Ababa');

-- Obtener el ID de Colombia
INSERT INTO departamento (nombre, codigo, prefijo_telefonico, id_pais) VALUES
('Amazonas', 'AMA', '08', 36),
('Antioquia', 'ANT', '04', 36),
('Arauca', 'ARA', '07', 36),
('Atlántico', 'ATL', '05', 36),
('Bolívar', 'BOL', '06', 36),
('Boyacá', 'BOY', '07', 36),
('Caldas', 'CAL', '09', 36),
('Caquetá', 'CAQ', '08', 36),
('Casanare', 'CAS', '07', 36),
('Cauca', 'CAU', '02', 36),
('Cesar', 'CES', '05', 36),
('Chocó', 'CHO', '04', 36),
('Córdoba', 'COR', '05', 36),
('Cundinamarca', 'CUN', '01', 36),
('Guainía', 'GUA', '08', 36),
('Guaviare', 'GUV', '07', 36),
('Huila', 'HUI', '03', 36),
('La Guajira', 'LAG', '05', 36),
('Magdalena', 'MAG', '05', 36),
('Meta', 'MET', '08', 36),
('Nariño', 'NAR', '02', 36),
('Norte de Santander', 'NSA', '07', 36),
('Putumayo', 'PUT', '06', 36),
('Quindío', 'QUI', '06', 36),
('Risaralda', 'RIS', '06', 36),
('San Andrés y Providencia', 'SAP', '08', 36),
('Santander', 'SAN', '07', 36),
('Sucre', 'SUC', '05', 36),
('Tolima', 'TOL', '08', 36),
('Valle del Cauca', 'VAC', '02', 36),
('Vaupés', 'VAU', '08', 36),
('Vichada', 'VID', '08', 36);

-- Obtener el ID de Argentina
INSERT INTO departamento (nombre, codigo, prefijo_telefonico, id_pais) VALUES
('Buenos Aires', 'B', '011', 6),
('Catamarca', 'K', '383', 6),
('Chaco', 'H', '362', 6),
('Chubut', 'U', '280', 6),
('Córdoba', 'X', '351', 6),
('Corrientes', 'W', '379', 6),
('Entre Ríos', 'E', '343', 6),
('Formosa', 'P', '370', 6),
('Jujuy', 'Y', '388', 6),
('La Pampa', 'L', '295', 6),
('La Rioja', 'F', '380', 6),
('Mendoza', 'M', '261', 6),
('Misiones', 'N', '375', 6),
('Neuquén', 'Q', '299', 6),
('Río Negro', 'R', '298', 6),
('Salta', 'A', '387', 6),
('San Juan', 'J', '264', 6),
('San Luis', 'D', '266', 6),
('Santa Cruz', 'Z', '290', 6),
('Santa Fe', 'S', '342', 6),
('Santiago del Estero', 'G', '385', 6),
('Tierra del Fuego', 'V', '2901', 6),
('Tucumán', 'T', '381', 6);

INSERT INTO departamento (nombre, codigo, prefijo_telefonico, id_pais) VALUES
('Badakhshan', 'BDS', NULL, 1),
('Badghis', 'BDG', NULL, 1),
('Baghlan', 'BGL', NULL, 1),
('Balkh', 'BAL', NULL, 1),
('Bamyan', 'BAM', NULL, 1),
('Daykundi', 'DAY', NULL, 1),
('Farah', 'FRA', NULL, 1),
('Faryab', 'FYB', NULL, 1),
('Ghazni', 'GHA', NULL, 1),
('Ghor', 'GHO', NULL, 1),
('Helmand', 'HEL', NULL, 1),
('Herat', 'HER', NULL, 1),
('Jowzjan', 'JOW', NULL, 1),
('Kabul', 'KAB', NULL, 1),
('Kandahar', 'KAN', NULL, 1),
('Kapisa', 'KAP', NULL, 1),
('Khost', 'KHO', NULL, 1),
('Kunar', 'KNR', NULL, 1),
('Kunduz', 'KDZ', NULL, 1),
('Laghman', 'LAG', NULL, 1),
('Logar', 'LOG', NULL, 1),
('Nangarhar', 'NAN', NULL, 1),
('Nimroz', 'NIM', NULL, 1),
('Nuristan', 'NUR', NULL, 1),
('Paktia', 'PKA', NULL, 1),
('Paktika', 'PKT', NULL, 1),
('Panjshir', 'PAN', NULL, 1),
('Parwan', 'PAR', NULL, 1),
('Samangan', 'SAM', NULL, 1),
('Sar-e Pol', 'SAR', NULL, 1),
('Takhar', 'TAK', NULL, 1),
('Urozgan', 'URO', NULL, 1),
('Wardak', 'WAR', NULL, 1),
('Zabul', 'ZAB', NULL, 1);

INSERT INTO departamento (nombre, codigo, prefijo_telefonico, id_pais) VALUES
('Berat', 'BR', NULL, 2),
('Dibër', 'DB', NULL, 2),
('Durrës', 'DU', NULL, 2),
('Elbasan', 'EL', NULL, 2),
('Shkodër', 'SH', NULL, 2),
('Fier', 'FR', NULL, 2),
('Korçë', 'KO', NULL, 2),
('Tirana', 'TR', NULL, 2),
('Vlorë', 'VL', NULL, 2),
('Gjirokastër', 'GJ', NULL, 2);

INSERT INTO departamento (nombre, codigo, prefijo_telefonico, id_pais) VALUES
('Adrar', '01', '49', 3),
('Chlef', '02', '35', 3),
('Laghouat', '03', '26', 3),
('Oum El Bouaghi', '04', '32', 3),
('Batna', '05', '33', 3),
('Béjaïa', '06', '34', 3),
('Biskra', '07', '34', 3),
('Bordj Bou Arréridj', '08', '31', 3),
('Bouira', '09', '26', 3),
('Tamanrasset', '10', '29', 3);

INSERT INTO departamento (nombre, codigo, prefijo_telefonico, id_pais) VALUES
('Andorra la Vella', 'ALV', '376', 4),
('Escaldes-Engordany', 'EES', '376', 4),
('Encamp', 'ENC', '376', 4),
('Ordino', 'ORD', '376', 4),
('La Massana', 'LMA', '376', 4),
('Sant Julià de Lòria', 'SJL', '376', 4),
('Canillo', 'CAN', '376', 4),
('El Pas de la Casa', 'EPC', '376', 4),
('Arinsal', 'ARI', '376', 4),
('Soldeu', 'SOL', '376', 4);

INSERT INTO departamento (nombre, codigo, prefijo_telefonico, id_pais) VALUES
('Luanda', 'LU', '222', 5),
('Benguela', 'BG', '244', 5),
('Huambo', 'HU', '244', 5),
('Lunda Norte', 'LN', '244', 5),
('Lunda Sul', 'LS', '244', 5),
('Cuanza Sul', 'CS', '244', 5),
('Malanje', 'MA', '244', 5),
('Namibe', 'NA', '244', 5),
('Moxico', 'MO', '244', 5),
('Zaire', 'ZA', '244', 5);


INSERT INTO departamento (nombre, codigo, prefijo_telefonico, id_pais) VALUES
('Aragatsotn', 'AG', '374', 6),
('Ararat', 'AR', '374', 6),
('Armavir', 'AM', '374', 6),
('Gegharkunik', 'GE', '374', 6),
('Kotayk', 'KT', '374', 6),
('Lori', 'LO', '374', 6),
('Shirak', 'SH', '374', 6),
('Syunik', 'SY', '374', 6),
('Tavush', 'TV', '374', 6),
('Yerevan', 'YE', '374', 6);

INSERT INTO ciudad (nombre, codigo_postal, prefijo_telefonico, id_departamento) VALUES
-- Amazonas
('Leticia', '910001', '8', 1),
('Puerto Nariño', '910012', '8', 1),

-- Antioquia
('Medellín', '050001', '4', 2),
('Envigado', '055420', '4', 2),
('Itagüí', '055410', '4', 2),

-- Arauca
('Arauca', '810001', '7', 3),
('Tame', '810002', '7', 3),

-- Atlántico
('Barranquilla', '080001', '5', 4),
('Soledad', '080002', '5', 4),

-- Bolívar
('Cartagena', '130001', '5', 5),
('Magangué', '130002', '5', 5),

-- Boyacá
('Tunja', '150001', '8', 6),
('Sogamoso', '150002', '8', 6),

-- Caldas
('Manizales', '170001', '6', 7),
('Pereira', '660001', '6', 7),

-- Caquetá
('Florencia', '180001', '8', 8),
('El Paujil', '180002', '8', 8),

-- Casanare
('Yopal', '850001', '7', 9),
('Támara', '850002', '7', 9),

-- Cauca
('Popayán', '190001', '2', 10),
('Santander de Quilichao', '190002', '2', 10),

-- Cesar
('Valledupar', '200001', '5', 11),
('La Paz', '200002', '5', 11),

-- Chocó
('Quibdó', '270001', '4', 12),
('Bahía Solano', '270002', '4', 12),

-- Córdoba
('Montería', '230001', '4', 13),
('Lorica', '230002', '4', 13),

-- Cundinamarca
('Bogotá', '110001', '1', 14),
('Soacha', '250001', '1', 14),

-- Guainía
('Inírida', '940001', '8', 15),
('Barranco Minas', '940002', '8', 15),

-- Guaviare
('San José del Guaviare', '950001', '7', 16),
('Miraflores', '950002', '7', 16),

-- Huila
('Neiva', '410001', '8', 17),
('Pitalito', '410002', '8', 17),

-- La Guajira
('Riohacha', '440001', '5', 18),
('Maicao', '440002', '5', 18),

-- Magdalena
('Santa Marta', '470001', '5', 19),
('Ciénaga', '470002', '5', 19),

-- Meta
('Villavicencio', '500001', '8', 20),
('Granada', '500002', '8', 20),

-- Nariño
('Pasto', '520001', '2', 21),
('Tumaco', '520002', '2', 21),

-- Norte de Santander
('Cúcuta', '540001', '7', 22),
('Ocaña', '540002', '7', 22),

-- Putumayo
('Mocoa', '860001', '6', 23),
('Puerto Asís', '860002', '6', 23),

-- Quindío
('Armenia', '630001', '6', 24),
('Circasia', '630002', '6', 24),

-- Risaralda
('Pereira', '660001', '6', 25),
('Dosquebradas', '660002', '6', 25),

-- San Andrés y Providencia
('San Andrés', '880001', '8', 26),
('Providencia', '880002', '8', 26),

-- Santander
('Bucaramanga', '680001', '7', 27),
('Barrancabermeja', '680002', '7', 27),

-- Sucre
('Sincelejo', '700001', '5', 28),
('Sampués', '700002', '5', 28),

-- Tolima
('Ibagué', '730001', '8', 29),
('Espinal', '730002', '8', 29),

-- Valle del Cauca
('Cali', '760001', '2', 30),
('Palmira', '763001', '2', 30),

-- Vaupés
('Mitú', '970001', '8', 31),
('Caruru', '970002', '8', 31),

-- Vichada
('Puerto Carreño', '990001', '8', 32),
('La Primavera', '990002', '8', 32);

INSERT INTO ciudad (nombre, codigo_postal, prefijo_telefonico, id_departamento) VALUES
-- Buenos Aires
('Buenos Aires', 'C1000', '11', 33),
('La Plata', 'B1900', '221', 33),
('Mar del Plata', 'B7600', '223', 33),

-- Catamarca
('San Fernando del Valle de Catamarca', 'K4700', '383', 34),
('Andalgalá', 'K4710', '383', 34),

-- Chaco
('Resistencia', '3500', '362', 35),
('Barranqueras', '3505', '362', 35),

-- Chubut
('Rawson', '9100', '280', 36),
('Trelew', '9100', '280', 36),

-- Córdoba
('Córdoba', 'X5000', '351', 37),
('Río Cuarto', '5800', '358', 37),
('Villa María', '5900', '353', 37),

-- Corrientes
('Corrientes', '3400', '379', 38),
('Goya', '3450', '379', 38),

-- Entre Ríos
('Paraná', '3100', '343', 39),
('Concordia', '3200', '343', 39),

-- Formosa
('Formosa', '3600', '370', 40),
('Clorinda', '3606', '370', 40),

-- Jujuy
('San Salvador de Jujuy', '4600', '388', 41),
('Palpalá', '4600', '388', 41),

-- La Pampa
('Santa Rosa', '6300', '295', 42),
('General Pico', '6200', '230', 42),

-- La Rioja
('La Rioja', '5300', '380', 43),
('Chilecito', '5330', '380', 43),

-- Mendoza
('Mendoza', '5500', '261', 44),
('San Rafael', '5600', '260', 44),

-- Misiones
('Posadas', '3300', '375', 45),
('Oberá', '3360', '375', 45),

-- Neuquén
('Neuquén', '8300', '299', 46),
('San Martín de los Andes', '8370', '294', 46),

-- Río Negro
('Viedma', '8500', '298', 47),
('Cipolletti', '8324', '299', 47),

-- Salta
('Salta', '4400', '387', 48),
('Orán', '4530', '387', 48),

-- San Juan
('San Juan', '5400', '264', 49),
('Rawson', '5410', '264', 49),

-- San Luis
('San Luis', '5700', '266', 50),
('Merlo', '5885', '266', 50),

-- Santa Cruz
('Río Gallegos', '9400', '296', 51),
('El Calafate', '9405', '296', 51),

-- Santa Fe
('Santa Fe', '3000', '342', 52),
('Rosario', '2000', '341', 52),

-- Santiago del Estero
('Santiago del Estero', '4200', '385', 53),
('La Banda', '4200', '385', 53),

-- Tierra del Fuego
('Ushuaia', '9410', '290', 54),
('Rio Grande', '9420', '290', 54),

-- Tucumán
('San Miguel de Tucumán', '4000', '381', 55),
('Concepción', '4140', '381', 55);


INSERT INTO tipo_usuarios (nombre) VALUES
('Ciclista'),
('Entrenador');

INSERT INTO usuarios (nombre, apellido, correo, contrasena, direccion, edad, genero, telefono, id_tipo_usuario, id_ciudad) VALUES
('Juan', 'Pérez', 'juan.perezz@example.com', 'contrasena123', 'Calle 1 #10-20', 25, 'Masculino', '3012345678', 1, 1),
('María', 'Gómez', 'maria.gomezz@example.com', 'contrasena456', 'Calle 2 #20-30', 22, 'Femenino', '3023456789', 1, 2),
('Carlos', 'Sánchez', 'carlos.sanchez@example.com', 'contrasena789', 'Calle 3 #30-40', 30, 'Masculino', '3034567890', 1, 3),
('Ana', 'Martínez', 'ana.martinez@example.com', 'contrasena012', 'Calle 4 #40-50', 28, 'Femenino', '3045678901', 1, 4),
('Luis', 'Ramírez', 'luis.ramirez@example.com', 'contrasena345', 'Calle 5 #50-60', 27, 'Masculino', '3056789012', 1, 5),
('Patricia', 'Torres', 'patricia.torres@example.com', 'contrasena678', 'Calle 6 #60-70', 35, 'Femenino', '3067890123', 2, 6),
('Jorge', 'Hernández', 'jorge.hernandez@example.com', 'contrasena901', 'Calle 7 #70-80', 40, 'Masculino', '3078901234', 2, 7),
('Sofía', 'Ríos', 'sofia.rios@example.com', 'contrasena234', 'Calle 8 #80-90', 32, 'Femenino', '3089012345', 2, 8),
('Andrés', 'Salazar', 'andres.salazar@example.com', 'contrasena567', 'Calle 9 #90-100', 38, 'Masculino', '3090123456', 2, 9),
('Laura', 'Castillo', 'laura.castillo@example.com', 'contrasena890', 'Calle 10 #100-110', 29, 'Femenino', '3101234567', 2, 10),
('Fernando', 'Mendoza', 'fernando.mendoza@example.com', 'contrasena77', 'Calle 11 #110-120', 22, 'Masculino', '3112345678', 1, 11),
('Isabella', 'López', 'isabella.lopez@example.com', 'contrasena4567', 'Calle 12 #120-130', 24, 'Femenino', '3123456789', 1, 12),
('Diego', 'Hurtado', 'diego.hurtado@example.com', 'contrasena7897', 'Calle 13 #130-140', 26, 'Masculino', '3134567890', 1, 13),
('Camila', 'Córdoba', 'camila.cordoba@example.com', 'contrasena0127', 'Calle 14 #140-150', 23, 'Femenino', '3145678901', 1, 14),
('Felipe', 'Núñez', 'felipe.nunez@example.com', 'contrasena3475', 'Calle 15 #150-160', 29, 'Masculino', '3156789012', 1, 15);

-- Inserción de calificaciones
INSERT INTO calificaciones (id_ciclista, id_entrenador, calificacion, comentario) VALUES
-- Calificaciones para los entrenadores por ciclistas (ejemplo)
(1, 6, 5, 'Excelente entrenador, muy profesional.'),
(2, 7, 4, 'Muy buen entrenador, pero puede mejorar en motivación.'),
(3, 8, 3, 'Entrenador decente, pero la comunicación puede ser mejor.'),
(4, 9, 4, 'Gran conocimiento y apoyo.'),
(5, 10, 5, 'El mejor entrenador que he tenido. Siempre atento.'),
(1, 15, 4, 'Entrenador competente, aunque podría ser más accesible.'),
(2, 14, 5, 'Increíble apoyo y guía, mejoró mucho mi rendimiento.'),
(3, 13, 2, 'No estaba satisfecho con las sesiones. Necesita más práctica.'),
(4, 12, 3, 'Entrenador promedio, tiene potencial.'),
(5, 11, 4, 'Recomiendo mucho a Laura, tiene buenas estrategias.');
