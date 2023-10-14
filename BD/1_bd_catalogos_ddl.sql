CREATE TABLE cat_area (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    code VARCHAR(50),
    activo BOOLEAN,
    fh_creacion DATETIME,
    fh_modificacion DATETIME,
    usu_creacion INT,
    usu_modificacion INT
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE cat_tipodocumento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    code VARCHAR(50),
    activo BOOLEAN,
    fh_creacion DATETIME,
    fh_modificacion DATETIME,
    usu_creacion INT,
    usu_modificacion INT
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE cat_accion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    code VARCHAR(50),
    activo BOOLEAN,
    fh_creacion DATETIME,
    fh_modificacion DATETIME,
    usu_creacion INT,
    usu_modificacion INT
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE cat_estado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    code VARCHAR(50),
    activo BOOLEAN,
    fh_creacion DATETIME,
    fh_modificacion DATETIME,
    usu_creacion INT,
    usu_modificacion INT
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE cat_cargo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    code VARCHAR(50),
    activo BOOLEAN,
    fh_creacion DATETIME,
    fh_modificacion DATETIME,
    usu_creacion INT,
    usu_modificacion INT
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE cat_tipoasociacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    code VARCHAR(50),
    activo BOOLEAN,
    fh_creacion DATETIME,
    fh_modificacion DATETIME,
    usu_creacion INT,
    usu_modificacion INT
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE cat_tipouso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    code VARCHAR(50),
    activo BOOLEAN,
    fh_creacion DATETIME,
    fh_modificacion DATETIME,
    usu_creacion INT,
    usu_modificacion INT
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
