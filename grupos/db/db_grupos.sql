-- Conectarse a la base de datos
\c db_grupos;

-- Crear tipos ENUM personalizados para intensidad y estado
CREATE TYPE intensidad_enum AS ENUM ('Baja', 'Media', 'Alta');
CREATE TYPE estado_actividad_enum AS ENUM ('Activo', 'Inactivo');
CREATE TYPE estado_grupo_enum AS ENUM ('Activo', 'Inactivo', 'Cerrado');
CREATE TYPE estado_inscripcion_enum AS ENUM ('Confirmada', 'Pendiente', 'Cancelada');
CREATE TYPE nivel_enum AS ENUM ('Principiante', 'Intermedio', 'Avanzado');

-- Tabla para actividades
CREATE TABLE actividades (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL, -- Nombre de la actividad
    descripcion TEXT, -- Descripción breve de la actividad
    duracion INT, -- Duración típica en minutos
    intensidad intensidad_enum, -- Nivel de intensidad
    equipamiento_requerido VARCHAR(255), -- Equipamiento necesario
    descripcion_detallada TEXT, -- Descripción amplia de la actividad
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Fecha de creación
    activo estado_actividad_enum DEFAULT 'Activo' -- Estado de la actividad
);

-- Tabla para grupos
CREATE TABLE grupos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL, -- Nombre del grupo
    cantidad_maxima INT NOT NULL, -- Cantidad máxima de participantes
    descripcion TEXT, -- Descripción del grupo
    tipo_grupo VARCHAR(50), -- Tipo de grupo (ej. competitivo, recreativo)
    nivel nivel_enum, -- Nivel requerido para unirse
    frecuencia VARCHAR(50), -- Frecuencia de actividades (ej. semanal, quincenal)
    horario VARCHAR(50), -- Horario de actividades (ej. sábados a las 10:00 AM)
    lugar VARCHAR(100), -- Ubicación principal
    id_creador INT, -- ID del usuario que creó el grupo (debe ser consultado a través del microservicio de usuarios)
    fecha_inicio DATE, -- Fecha de inicio de actividades
    activo estado_grupo_enum DEFAULT 'Activo', -- Estado del grupo
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Fecha de creación del grupo
);

-- Tabla para inscripciones
CREATE TABLE inscripciones (
    id SERIAL PRIMARY KEY,
    id_usuario INT, -- ID del usuario que se inscribe (ciclista o entrenador, de otro microservicio)
    id_grupo INT, -- Grupo en el que se inscribe
    id_actividad INT, -- Actividad en la que participa
    fecha_inscripcion TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Fecha de inscripción
    fecha_actividad DATE, -- Fecha de la actividad
    estado_inscripcion estado_inscripcion_enum DEFAULT 'Pendiente', -- Estado de la inscripción
    notas TEXT, -- Notas adicionales sobre la inscripción
    fecha_cancelacion DATE NULL, -- Fecha en que se canceló la inscripción (si aplica)
    id_entrenador INT, -- ID del entrenador asignado (debe ser consultado a través del microservicio de usuarios)
    FOREIGN KEY (id_grupo) REFERENCES grupos(id), -- Relación con la tabla grupos
    FOREIGN KEY (id_actividad) REFERENCES actividades(id) -- Relación con la tabla actividades
    -- Las relaciones con usuarios deben manejarse a través de la API, no en la base de datos
);

-- Insertar datos en la tabla de actividades
INSERT INTO actividades (nombre, descripcion, duracion, intensidad, equipamiento_requerido, descripcion_detallada, activo) VALUES
('Ciclismo de Ruta', 'Actividad de ciclismo en carretera.', 120, 'Alta', 'Bicicleta de ruta, casco, gafas de sol', 'Ciclismo de ruta es una modalidad que se practica en carretera, ideal para quienes buscan velocidad y resistencia.', 'Activo'),
('Ciclismo de Montaña', 'Aventura sobre senderos y caminos de tierra.', 90, 'Alta', 'Bicicleta de montaña, casco, guantes', 'Este tipo de ciclismo se realiza en terrenos montañosos, con subidas y bajadas técnicas.', 'Activo'),
('Ciclismo Urbano', 'Ciclismo en la ciudad, ideal para el transporte diario.', 60, 'Baja', 'Bicicleta urbana, casco', 'Una excelente forma de moverse por la ciudad evitando el tráfico.', 'Activo'),
('Ciclismo de Gravedad', 'Descensos en bicicleta por senderos empinados.', 60, 'Alta', 'Bicicleta de gravedad, casco integral', 'Este tipo de ciclismo se enfoca en descensos rápidos y técnicos, con saltos y obstáculos.', 'Activo'),
('Ciclismo de Entrenamiento', 'Sesiones de entrenamiento específicas.', 120, 'Media', 'Bicicleta de ruta o montaña, pulsómetro', 'Entrenamiento estructurado para mejorar la resistencia y la técnica.', 'Activo'),
('Ciclismo de Tour', 'Recorridos de larga distancia en grupo.', 240, 'Alta', 'Bicicleta de ruta, alforjas', 'Ciclismo en grupo durante varios días, explorando nuevas rutas.', 'Activo'),
('Ciclismo de Competencia', 'Preparación para eventos y carreras.', 150, 'Alta', 'Bicicleta de ruta, casco, ropa de competición', 'Entrenamiento intensivo para mejorar el rendimiento en competiciones.', 'Activo'),
('Ciclismo Recreativo', 'Paseos en bicicleta para disfrutar.', 80, 'Baja', 'Bicicleta de paseo, casco', 'Ideal para disfrutar del aire libre y pasar tiempo en familia.', 'Activo'),
('Ciclismo Indoor', 'Clases de ciclismo en bicicleta estática.', 45, 'Media', 'Bicicleta estática, toalla, agua', 'Entrenamiento en grupo dentro de un estudio, guiado por un instructor.', 'Activo'),
('Ciclismo de Aventura', 'Recorridos por paisajes variados.', 180, 'Media', 'Bicicleta de montaña, mochila, equipo de camping', 'Aventuras en bicicleta en diferentes terrenos, combinando ciclismo y exploración.', 'Activo');

-- Insertar datos en la tabla de grupos
INSERT INTO grupos (nombre, cantidad_maxima, descripcion, tipo_grupo, nivel, frecuencia, horario, lugar, id_creador, fecha_inicio, activo) VALUES
('Grupo de Ciclismo A', 10, 'Grupo para ciclistas principiantes que desean aprender.', 'Recreativo', 'Principiante', 'Semanal', 'Sábados a las 10:00 AM', 'Parque Central', 6, '2024-10-01', 'Activo'),
('Grupo de Ciclismo B', 15, 'Entrenamiento para ciclistas intermedios.', 'Competitivo', 'Intermedio', 'Quincenal', 'Domingos a las 8:00 AM', 'Montañas de la Ciudad', 7, '2024-10-05', 'Activo'),
('Grupo de Ciclismo C', 12, 'Ciclismo avanzado, se requiere experiencia.', 'Competitivo', 'Avanzado', 'Semanal', 'Viernes a las 6:00 PM', 'Ruta del Lago', 8, '2024-10-10', 'Activo'),
('Grupo de Ciclismo D', 8, 'Paseos recreativos en bicicleta para familias.', 'Recreativo', 'Principiante', 'Semanal', 'Sábados a las 4:00 PM', 'Plaza Principal', 9, '2024-10-15', 'Activo'),
('Grupo de Ciclismo E', 20, 'Entrenamiento en grupo para competiciones.', 'Competitivo', 'Intermedio', 'Quincenal', 'Lunes y Jueves a las 7:00 AM', 'Centro Deportivo', 10, '2024-10-20', 'Activo');

-- Insertar datos en la tabla de inscripciones
INSERT INTO inscripciones (id_usuario, id_grupo, id_actividad, fecha_actividad, estado_inscripcion, notas, id_entrenador) VALUES
(1, 1, 1, '2024-10-15', 'Confirmada', 'Primera inscripción en grupo.', 6),
(2, 2, 2, '2024-10-16', 'Pendiente', 'Interesado en el grupo de intermedios.', 7),
(3, 3, 3, '2024-10-17', 'Confirmada', 'Preparación para el evento.', 8),
(4, 4, 4, '2024-10-18', 'Pendiente', 'Espero poder asistir.', 9),
(5, 5, 5, '2024-10-19', 'Confirmada', 'Entrenamiento para competencia.', 10),
(11, 1, 6, '2024-10-20', 'Pendiente', 'Quiero unirme a la actividad de ruta.', 6),
(12, 2, 7, '2024-10-21', 'Confirmada', 'Listo para el ciclismo de montaña.', 7),
(13, 3, 8, '2024-10-22', 'Pendiente', 'Esperando confirmación del grupo.', 8),
(14, 4, 9, '2024-10-23', 'Confirmada', 'Deseando empezar con el ciclismo urbano.', 9),
(15, 5, 10, '2024-10-24', 'Pendiente', 'Listo para el tour de larga distancia.', 10);
