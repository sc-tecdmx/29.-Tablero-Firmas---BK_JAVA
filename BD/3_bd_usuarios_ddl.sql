CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellidop VARCHAR(50),
    apellidom VARCHAR(50),
    correo VARCHAR(100) UNIQUE,
    id_cargo INT,
    activo BOOLEAN,
    fh_creacion DATETIME,
    fh_modificacion DATETIME,
    usu_creacion INT,
    usu_modificacion INT,
    CONSTRAINT fk_doc_id_cargo FOREIGN KEY (id_cargo) REFERENCES cat_cargo(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;