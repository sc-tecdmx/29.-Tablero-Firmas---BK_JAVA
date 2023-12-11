-- MySQL dump 10.13  Distrib 8.2.0, for macos13.5 (arm64)
--
-- Host: localhost    Database: db_mappings
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `inst_cat_areas`
--

DROP TABLE IF EXISTS `inst_cat_areas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inst_cat_areas` (
  `n_id_cat_area` int NOT NULL AUTO_INCREMENT,
  `n_id_u_adscripcion` int DEFAULT NULL,
  `s_desc_area` varchar(100) DEFAULT NULL,
  `s_abrev_area` varchar(15) DEFAULT NULL,
  `n_id_cat_area_padre` int DEFAULT NULL,
  PRIMARY KEY (`n_id_cat_area`),
  UNIQUE KEY `s_abrev_area` (`s_abrev_area`),
  KEY `n_id_u_adscripcion` (`n_id_u_adscripcion`),
  KEY `n_id_cat_area_padre` (`n_id_cat_area_padre`),
  CONSTRAINT `inst_cat_areas_ibfk_1` FOREIGN KEY (`n_id_u_adscripcion`) REFERENCES `inst_u_adscripcion` (`n_id_u_adscripcion`),
  CONSTRAINT `inst_cat_areas_ibfk_2` FOREIGN KEY (`n_id_cat_area_padre`) REFERENCES `inst_cat_areas` (`n_id_cat_area`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inst_cat_areas`
--

LOCK TABLES `inst_cat_areas` WRITE;
/*!40000 ALTER TABLE `inst_cat_areas` DISABLE KEYS */;
INSERT INTO `inst_cat_areas` VALUES (1,1,'Pleno del Tribunal','PT',NULL),(2,2,'Contraloría Interna','CI',1),(3,2,'Responsabilidades y Atención a Quejas','RyAQ',2),(4,2,'Departamento de Responsabilidades','DR',3),(5,2,'Departamento de Quejas y Denuncias','DQyD',3),(6,2,'Auditoría, Control y Evaluación','ACyE',2),(7,2,'Departamento de Auditoría','DA',6),(8,2,'Departamento de Evaluación','DdE',6),(9,3,'Comisión de Controversias Laborales y Administrativas','CCLyA',1),(10,4,'Defensoría Pública de Participación Ciudadana y de Procesos Democráticos','DPPCyPD',1),(11,4,'Asesoría, Gestión y Seguimiento','AGyS',10),(12,4,'Oficina de Defensoría de Derechos Político Electorales y de Enlace Ciudadano','ODDPEyEC',10),(13,5,'Ponencia del Magistrado Armando Ambriz Hernández','PMAAH',1),(14,5,'Secretaría de Estudio y Cuenta Coordinación de Ponencia','PMAAH_SECCP',13),(15,6,'Ponencia de la Magistrada Martha Leticia Mercado Ramírez','PMMLMR',1),(16,6,'Secretaría de Estudio y Cuenta Coordinación de Ponencia','PMMLMR_SECCP',15),(17,7,'Ponencia del Magistrado Juan Carlos Sánchez León','PMJCSL',1),(18,7,'Secretaría de Estudio y Cuenta Coordinación de Ponencia','PMJCSL_SECCP',17),(19,8,'Ponencia de la Magistratura Electoral Vacante A','PMEVA',1),(20,8,'Secretaría de Estudio y Cuenta','PMEVA_SEC',19),(21,9,'Ponencia de la Magistratura Electoral Vacante B','PMEVB',1),(22,9,'Secretaría de Estudio y Cuenta','PMEVB_SEC',21),(23,10,'Presidencia','PRES',1),(24,11,'Secretaría General','SG',1),(25,11,'Secretaría Técnica de la Secretaría General','STSG',24),(26,11,'Oficina de Actuarios','OA',24),(27,11,'Oficialía de Partes y Archivo Jurisdiccional','OPyAJ',24),(28,11,'Departamento de Oficialía de Partes','DOP',27),(29,12,'Coordinación de Archivo','CA',1),(30,12,'Archivos y Documentación','AyD',29),(31,12,'Departamento de Archivos','DepdA',30),(32,12,'Departamento de Documentación','DD',30),(33,13,'Secretaría Administrativa','SA',1),(34,13,'Departamento de Control y Seguimiento','DCyS',33),(35,13,'Recursos Humanos','RRHH',33),(36,13,'Departamento de Registro y Control de Personal','DRyCP',35),(37,13,'Departamento de Prestaciones','DP',35),(38,13,'Departamento de Pagos a Personal','DPP',35),(39,13,'Departamento de Servicio Médico','DSM',35),(40,13,'Planeación y Recursos Financieros','PyRF',33),(41,13,'Departamento de Caja y Tesorería','DCyT',40),(42,13,'Contabilidad y Control','CyC',40),(43,13,'Planeación y Presupuesto','PyP',40),(44,13,'Recursos Materiales y Servicios Generales','RMySG',33),(45,13,'Recursos Materiales y Servicios','RMyS',44),(46,13,'Departamento de Adquisiciones','DdA',44),(47,14,'Coordinación de Transparencia y Datos Personales','CTyDP',1),(48,14,'Transparencia y Acceso a la Información Pública','TyAIP',47),(49,14,'Departamento de Administración de Datos Personales','DADP',48),(50,14,'Departamento de Transparencia','DT',48),(51,15,'Dirección General Jurídica','DGJ',1),(52,15,'Contencioso y Consultivo','CoyCo',51),(53,15,'Departamento de lo Contencioso','DepdCoso',52),(54,15,'Departamento de lo Consultivo','DepdCovo',52),(55,15,'Contratos y Normatividad','CyN',51),(56,15,'Departamento de Contratos y Normatividad','DCyN',55),(57,16,'Coordinación de Comunicación Social y Relaciones Públicas','CCSyRP',1),(58,16,'Comunicación Social','CS',57),(59,16,'Departamento de Comunicación Social','DCS',58),(60,16,'Departamento de Información','DI',58),(61,17,'Unidad Especializada de Procedimientos Sancionadores','UEPS',1),(62,18,'Coordinación de Difusión y Publicación','CDyP',1),(63,18,'Difusión','D',62),(64,18,'Departamento de Publicación','DepPub',63),(65,19,'Coordinación de Vinculación y Relaciones Internacionales','CVyRI',1),(66,19,'Vinculación y Enlace','VyE',65),(67,20,'Coordinación de Derechos Humanos y Género','CDHyG',1),(68,20,'Subdirección de Derechos Humanos y Género','SDDHHyG',67),(69,20,'Departamento de Derechos Humanos y Género','DDDHHyG',68),(70,21,'Instituto de Formación y Capacitación','IFyC',1),(71,21,'Departamento de Formación y Capacitación','DFyC',70),(72,22,'Unidad de Servicios Informáticos','USI',1),(73,22,'Servicios Informaticos','SI',72),(74,22,'Departamento de Desarrollo de Sistemas','DDS',73),(75,22,'Departamento de Tecnologías Web y Multimedia','DTWyM',73),(76,22,'Departamento de Infraestructura y Servicios de Cómputo','DIySC',73),(77,23,'Unidad de Estadística y Jurisprudencia','UEyJ',1),(78,23,'Jurisprudencia','J',77),(79,23,'Estadística','E',77),(80,23,'Departamento de Estadística','DE',79);
/*!40000 ALTER TABLE `inst_cat_areas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inst_cat_puestos`
--

DROP TABLE IF EXISTS `inst_cat_puestos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inst_cat_puestos` (
  `n_id_puesto` int NOT NULL AUTO_INCREMENT,
  `s_desc_nombramiento` varchar(100) DEFAULT NULL,
  `n_tipo_usuario` varchar(1) DEFAULT NULL COMMENT 'J - Jurisdiccional, A - Administrativo',
  PRIMARY KEY (`n_id_puesto`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inst_cat_puestos`
--

LOCK TABLES `inst_cat_puestos` WRITE;
/*!40000 ALTER TABLE `inst_cat_puestos` DISABLE KEYS */;
INSERT INTO `inst_cat_puestos` VALUES (1,'Abogada/o','J'),(2,'Actuaria/o A','J'),(3,'Actuaria/o B','J'),(4,'Actuaria/o C','J'),(5,'Actuaria/o D','J'),(6,'Actuaria/o E','J'),(7,'Actuaria/o F','J'),(8,'Asesor/a A','J'),(9,'Asesor/a B','J'),(10,'Asesor/a C','J'),(11,'Asesor/a D','J'),(12,'Asesor/a E','J'),(13,'Asesor/a F','J'),(14,'Asesor/a G','J'),(15,'Asesor/a Principal','J'),(16,'Asistente A','A'),(17,'Asistente B','A'),(18,'Asistente C','A'),(19,'Asistente D','A'),(20,'Auditor/a','A'),(21,'Auxiliar A','A'),(22,'Auxiliar B','A'),(23,'Auxiliar C','A'),(24,'Auxiliar D','A'),(25,'Chofer A','A'),(26,'Chofer B','A'),(27,'Chofer C','A'),(28,'Chofer D','A'),(29,'Chofer E','A'),(30,'Chofer F','A'),(31,'Chofer G','A'),(32,'Contralor/a General','J'),(33,'Contralor/a General A','J'),(34,'Contralor/a General B','J'),(35,'Contralor/a General C','J'),(36,'Coordinador/a ','J'),(37,'Coordinador/a A','J'),(38,'Coordinador/a B','J'),(39,'Coordinador/a C','J'),(40,'Coordinador/a D','J'),(41,'Coordinador/a DD','J'),(42,'Coordinador/a E','J'),(43,'Director/a A','A'),(44,'Director/a B','A'),(45,'Director/a C','A'),(46,'Director/a D','A'),(47,'Director/a E','A'),(48,'Director/a F','A'),(49,'Director/a G','A'),(50,'Director/a H','A'),(51,'Director/a HH','A'),(52,'Director/a I','A'),(53,'Jefa/e de Departamento','A'),(54,'Jefa/e de Departamento A','A'),(55,'Jefa/e de Departamento B','A'),(56,'Jefa/e de Departamento C','A'),(57,'Jefa/e de Departamento D','A'),(58,'Jefa/e de Departamento E','A'),(59,'Jefa/e de Departamento F','A'),(60,'Jefa/e de Departamento G','A'),(61,'Jefa/e de Departamento H','A'),(62,'Magistrada/o Electoral','J'),(63,'Presidente del Tribunal','J'),(64,'Profesionista Técnica/o A','A'),(65,'Profesionista Técnica/o B','A'),(66,'Profesionista Técnica/o C','A'),(67,'Profesionista Técnica/o D','A'),(68,'Profesionista Técnica/o E','A'),(69,'Profesionista Técnica/o F','A'),(70,'Profesionista Técnica/o G','A'),(71,'Profesionista Técnica/o H','A'),(72,'Profesor/a - Investigador/a A','A'),(73,'Profesor/a - Investigador/a B','A'),(74,'Profesor/a - Investigador/a C','A'),(75,'Secretario/a de Estudio y Cuenta A','J'),(76,'Secretario/a A','J'),(77,'Secretario/a B','J'),(78,'Secretario/a C','J'),(79,'Secretario/a D','J'),(80,'Secretario/a E','J'),(81,'Secretario/a Ejecutiva/o','J'),(82,'Secretario/a Ejecutiva/o A','J'),(83,'Secretario/a Ejecutiva/o B','J'),(84,'Secretario/a Ejecutiva/o C','J'),(85,'Secretario/a Ejecutiva/o D','J'),(86,'Secretario/a Ejecutiva/o E','J'),(87,'Secretario/a Ejecutiva/o F','J'),(88,'Secretario/a Ejecutiva/o G','J'),(89,'Secretario/a Ejecutiva/o H','J'),(90,'Secretario/a F','J'),(91,'Secretario/a Administrativo','J'),(92,'Secretario/a Administrativo A','J'),(93,'Secretario/a Administrativo B','J'),(94,'Secretario/a Administrativo C','J'),(95,'Secretario/a Auxiliar','J'),(96,'Secretario/a Auxiliar A','J'),(97,'Secretario/a Auxiliar B','J'),(98,'Secretario/a Auxiliar C','J'),(99,'Secretario/a de Estudio y Cuenta','J'),(100,'Secretario/a de Estudio y Cuenta A','J'),(101,'Secretario/a de Estudio y Cuenta B','J'),(102,'Secretario/a de Estudio y Cuenta C','J'),(103,'Secretario/a de Estudio y Cuenta Coordinador/a B','J'),(104,'Secretario/a General','J'),(105,'Secretario/a General A','J'),(106,'Secretario/a General B','J'),(107,'Secretario/a General C','J'),(108,'Secretario/a Particular A','J'),(109,'Secretario/a Particular B','J'),(110,'Secretario/a Particular C','J'),(111,'Secretario/a Privado A','J'),(112,'Secretario/a Privado B','J'),(113,'Secretario/a Privado C','J'),(114,'Secretario/a Técnica/o A','J'),(115,'Secretario/a Técnica/o B','J'),(116,'Secretario/a Técnica/o C','J'),(117,'Secretario/a Técnica/o D','J'),(118,'Secretario/a Técnica/o de la Secretaría General A','J'),(119,'Secretario/a Técnica/o de la Secretaría General B','J'),(120,'Secretario/a Técnica/o de la Secretaría General C','J'),(121,'Secretario/a Técnica/o de la Secretaría General D','J'),(122,'Subdirector/a de Área A','A'),(123,'Subdirector/a de Área B','A'),(124,'Subdirector/a de Área C','A'),(125,'Subdirector/a de Área D','A'),(126,'Subdirector/a de Área E','A'),(127,'Subdirector/a de Área F','A'),(128,'Subdirector/a de Área G','A'),(129,'Secretario/a de Estudio y Cuenta D','J'),(130,'Secretario/a de Estudio y Cuenta G','J'),(131,'Secretario/a de Estudio y Cuenta H','J'),(132,'Defensor/a Ciudadano/a','J'),(133,'Contralor/a Interno','A'),(134,'Investigador/a C','A'),(135,'Profesionista Técnica/o','A');
/*!40000 ALTER TABLE `inst_cat_puestos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inst_cat_sexo`
--

DROP TABLE IF EXISTS `inst_cat_sexo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inst_cat_sexo` (
  `id_sexo` int NOT NULL AUTO_INCREMENT,
  `sexo` varchar(1) DEFAULT NULL,
  `sexo_desc` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_sexo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inst_cat_sexo`
--

LOCK TABLES `inst_cat_sexo` WRITE;
/*!40000 ALTER TABLE `inst_cat_sexo` DISABLE KEYS */;
INSERT INTO `inst_cat_sexo` VALUES (1,'H','Hombre'),(2,'M','Mujer');
/*!40000 ALTER TABLE `inst_cat_sexo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inst_empleado`
--

DROP TABLE IF EXISTS `inst_empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inst_empleado` (
  `n_id_num_empleado` int NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `apellido1` varchar(50) DEFAULT NULL,
  `apellido2` varchar(50) DEFAULT NULL,
  `id_sexo` int DEFAULT NULL,
  `s_email_pers` varchar(256) DEFAULT NULL,
  `s_email_inst` varchar(256) DEFAULT NULL,
  `tel_pers` varchar(10) DEFAULT NULL,
  `tel_inst` varchar(10) DEFAULT NULL,
  `curp` varchar(18) DEFAULT NULL,
  `rfc` varchar(13) DEFAULT NULL,
  `path_fotografia` varchar(256) DEFAULT NULL,
  `n_id_usuario` int DEFAULT NULL,
  PRIMARY KEY (`n_id_num_empleado`),
  UNIQUE KEY `s_email_pers` (`s_email_pers`),
  UNIQUE KEY `s_email_inst` (`s_email_inst`),
  KEY `id_sexo` (`id_sexo`),
  KEY `n_id_usuario` (`n_id_usuario`),
  CONSTRAINT `inst_empleado_ibfk_1` FOREIGN KEY (`id_sexo`) REFERENCES `inst_cat_sexo` (`id_sexo`),
  CONSTRAINT `inst_empleado_ibfk_2` FOREIGN KEY (`n_id_usuario`) REFERENCES `seg_org_usuarios` (`n_id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inst_empleado`
--

LOCK TABLES `inst_empleado` WRITE;
/*!40000 ALTER TABLE `inst_empleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `inst_empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inst_empleado_puesto`
--

DROP TABLE IF EXISTS `inst_empleado_puesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inst_empleado_puesto` (
  `n_id_empleado_puesto` int NOT NULL AUTO_INCREMENT,
  `n_id_num_empleado` int DEFAULT NULL,
  `n_id_cat_area` int DEFAULT NULL,
  `n_id_puesto` int DEFAULT NULL,
  `fecha_alta` date DEFAULT NULL,
  `fecha_conclusion` date DEFAULT NULL,
  PRIMARY KEY (`n_id_empleado_puesto`),
  KEY `n_id_num_empleado` (`n_id_num_empleado`),
  KEY `n_id_cat_area` (`n_id_cat_area`),
  KEY `n_id_puesto` (`n_id_puesto`),
  CONSTRAINT `inst_empleado_puesto_ibfk_1` FOREIGN KEY (`n_id_num_empleado`) REFERENCES `inst_empleado` (`n_id_num_empleado`),
  CONSTRAINT `inst_empleado_puesto_ibfk_2` FOREIGN KEY (`n_id_cat_area`) REFERENCES `inst_cat_areas` (`n_id_cat_area`),
  CONSTRAINT `inst_empleado_puesto_ibfk_3` FOREIGN KEY (`n_id_puesto`) REFERENCES `inst_cat_puestos` (`n_id_puesto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inst_empleado_puesto`
--

LOCK TABLES `inst_empleado_puesto` WRITE;
/*!40000 ALTER TABLE `inst_empleado_puesto` DISABLE KEYS */;
/*!40000 ALTER TABLE `inst_empleado_puesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inst_log_empleado`
--

DROP TABLE IF EXISTS `inst_log_empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inst_log_empleado` (
  `n_id_log_empleado` int NOT NULL AUTO_INCREMENT,
  `n_id_num_empleado` int DEFAULT NULL,
  `bitacora` varchar(1024) DEFAULT NULL COMMENT 'Registrar aquí todos los campos en la información del empleado en formato JSON',
  `n_session_id` int DEFAULT NULL,
  PRIMARY KEY (`n_id_log_empleado`),
  KEY `n_id_num_empleado` (`n_id_num_empleado`),
  KEY `n_session_id` (`n_session_id`),
  CONSTRAINT `inst_log_empleado_ibfk_1` FOREIGN KEY (`n_id_num_empleado`) REFERENCES `inst_empleado` (`n_id_num_empleado`),
  CONSTRAINT `inst_log_empleado_ibfk_2` FOREIGN KEY (`n_session_id`) REFERENCES `seg_org_log_sesion` (`n_session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inst_log_empleado`
--

LOCK TABLES `inst_log_empleado` WRITE;
/*!40000 ALTER TABLE `inst_log_empleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `inst_log_empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inst_titular_u_adscripcion`
--

DROP TABLE IF EXISTS `inst_titular_u_adscripcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inst_titular_u_adscripcion` (
  `n_id_titular_area` int NOT NULL AUTO_INCREMENT,
  `n_id_u_adscripcion` int DEFAULT NULL,
  `n_id_empleado_puesto` int DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_conclusion` date DEFAULT NULL,
  PRIMARY KEY (`n_id_titular_area`),
  KEY `n_id_u_adscripcion` (`n_id_u_adscripcion`),
  KEY `n_id_empleado_puesto` (`n_id_empleado_puesto`),
  CONSTRAINT `inst_titular_u_adscripcion_ibfk_1` FOREIGN KEY (`n_id_u_adscripcion`) REFERENCES `inst_u_adscripcion` (`n_id_u_adscripcion`),
  CONSTRAINT `inst_titular_u_adscripcion_ibfk_2` FOREIGN KEY (`n_id_empleado_puesto`) REFERENCES `inst_empleado_puesto` (`n_id_empleado_puesto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inst_titular_u_adscripcion`
--

LOCK TABLES `inst_titular_u_adscripcion` WRITE;
/*!40000 ALTER TABLE `inst_titular_u_adscripcion` DISABLE KEYS */;
/*!40000 ALTER TABLE `inst_titular_u_adscripcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inst_u_adscripcion`
--

DROP TABLE IF EXISTS `inst_u_adscripcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inst_u_adscripcion` (
  `n_id_u_adscripcion` int NOT NULL AUTO_INCREMENT,
  `s_desc_unidad` varchar(255) DEFAULT NULL,
  `s_abrev_unidad` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`n_id_u_adscripcion`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inst_u_adscripcion`
--

LOCK TABLES `inst_u_adscripcion` WRITE;
/*!40000 ALTER TABLE `inst_u_adscripcion` DISABLE KEYS */;
INSERT INTO `inst_u_adscripcion` VALUES (1,'Pleno del Tribunal','PT'),(2,'Contraloría Interna','CI'),(3,'Comisión de Controversias Laborales y Administrativas','CCLyA'),(4,'Defensoría Pública de Participación Ciudadana y de Procesos Democráticos','DPPCyPD'),(5,'Ponencia del Magistrado Armando Ambriz Hernández','PMAAH'),(6,'Ponencia de la Magistrada Martha Leticia Mercado Ramírez','PMMLMR'),(7,'Ponencia del Magistrado Juan Carlos Sánchez León','PMJCSL'),(8,'Ponencia de la Magistratura Electoral Vacante A','PMEVA'),(9,'Ponencia de la Magistratura Electoral Vacante B','PMEVB'),(10,'Presidencia','PRES'),(11,'Secretaría General','SG'),(12,'Coordinación de Archivo','CA'),(13,'Secretaría Administrativa','SA'),(14,'Coordinación de Transparencia y Datos Personales','CTyDP'),(15,'Dirección General Jurídica','DGJ'),(16,'Coordinación de Comunicación Social y Relaciones Públicas','CCSyRP'),(17,'Unidad Especializada de Procedimientos Sancionadores','UEPS'),(18,'Coordinación de Difusión y Publicación','CDyP'),(19,'Coordinación de Vinculación y Relaciones Internacionales','CVyRI'),(20,'Coordinación de Derechos Humanos y Género','CDHyG'),(21,'Instituto de Formación y Capacitación','IFyC'),(22,'Unidad de Servicios Informáticos','USI'),(23,'Unidad de Estadística y Jurisprudencia','UEyJ');
/*!40000 ALTER TABLE `inst_u_adscripcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jel_persona_jel`
--

DROP TABLE IF EXISTS `jel_persona_jel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jel_persona_jel` (
  `s_curp` varchar(20) NOT NULL,
  `id_persona_jel` int DEFAULT NULL,
  `nombre` varchar(40) DEFAULT NULL,
  `apellido1` varchar(40) DEFAULT NULL,
  `apellido2` varchar(40) DEFAULT NULL,
  `s_rfc` varchar(12) DEFAULT NULL,
  `genero` varchar(14) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `tipo_identificacion` int DEFAULT NULL,
  PRIMARY KEY (`s_curp`),
  KEY `id_persona_jel` (`id_persona_jel`),
  CONSTRAINT `jel_persona_jel_ibfk_1` FOREIGN KEY (`id_persona_jel`) REFERENCES `seg_org_usuarios` (`n_id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jel_persona_jel`
--

LOCK TABLES `jel_persona_jel` WRITE;
/*!40000 ALTER TABLE `jel_persona_jel` DISABLE KEYS */;
/*!40000 ALTER TABLE `jel_persona_jel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jel_renapo_curps`
--

DROP TABLE IF EXISTS `jel_renapo_curps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jel_renapo_curps` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha_consulta` datetime DEFAULT NULL,
  `respuesta_renapo` varchar(12) DEFAULT NULL,
  `respuesta_firmada` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jel_renapo_curps`
--

LOCK TABLES `jel_renapo_curps` WRITE;
/*!40000 ALTER TABLE `jel_renapo_curps` DISABLE KEYS */;
/*!40000 ALTER TABLE `jel_renapo_curps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jel_seg_modulos`
--

DROP TABLE IF EXISTS `jel_seg_modulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jel_seg_modulos` (
  `n_id_modulo` int NOT NULL AUTO_INCREMENT,
  `n_id_nivel` int DEFAULT NULL,
  `desc_modulo` varchar(20) DEFAULT NULL,
  `n_id_modulo_padre` int DEFAULT NULL,
  `menu` varchar(1) DEFAULT NULL COMMENT 'S- Si, forma parte del menú',
  `menu_desc` varchar(20) DEFAULT NULL,
  `menu_url` varchar(40) DEFAULT NULL,
  `menu_pos` int DEFAULT NULL COMMENT 'Sirve para presentar la posición del menú',
  PRIMARY KEY (`n_id_modulo`),
  KEY `n_id_nivel` (`n_id_nivel`),
  KEY `n_id_modulo_padre` (`n_id_modulo_padre`),
  CONSTRAINT `jel_seg_modulos_ibfk_1` FOREIGN KEY (`n_id_nivel`) REFERENCES `seg_cat_nivel_modulo` (`n_id_nivel`),
  CONSTRAINT `jel_seg_modulos_ibfk_2` FOREIGN KEY (`n_id_modulo_padre`) REFERENCES `jel_seg_modulos` (`n_id_modulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jel_seg_modulos`
--

LOCK TABLES `jel_seg_modulos` WRITE;
/*!40000 ALTER TABLE `jel_seg_modulos` DISABLE KEYS */;
/*!40000 ALTER TABLE `jel_seg_modulos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jel_seg_roles`
--

DROP TABLE IF EXISTS `jel_seg_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jel_seg_roles` (
  `n_id_rol` int NOT NULL AUTO_INCREMENT,
  `s_etiqueta_rol` varchar(15) DEFAULT NULL,
  `s_descripcion` varchar(40) DEFAULT NULL,
  `n_id_rol_padre` int DEFAULT NULL,
  `n_rec_activo` int DEFAULT NULL,
  `n_session_id_borrado` int DEFAULT NULL COMMENT 'TODO. Vincularlo a la tabla de sesiones',
  PRIMARY KEY (`n_id_rol`),
  KEY `n_id_rol_padre` (`n_id_rol_padre`),
  CONSTRAINT `jel_seg_roles_ibfk_1` FOREIGN KEY (`n_id_rol_padre`) REFERENCES `jel_seg_roles` (`n_id_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jel_seg_roles`
--

LOCK TABLES `jel_seg_roles` WRITE;
/*!40000 ALTER TABLE `jel_seg_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `jel_seg_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jel_seg_roles_modulos`
--

DROP TABLE IF EXISTS `jel_seg_roles_modulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jel_seg_roles_modulos` (
  `n_id_rol` int NOT NULL,
  `n_id_modulo` int NOT NULL,
  `create` varchar(1) DEFAULT NULL COMMENT 'S- Si, N-No, Null-No',
  `read` varchar(1) DEFAULT NULL,
  `update` varchar(1) DEFAULT NULL,
  `delete` varchar(1) DEFAULT NULL,
  `n_session_id` int DEFAULT NULL COMMENT 'Guardar la sesión que modificó el registro',
  PRIMARY KEY (`n_id_rol`,`n_id_modulo`),
  KEY `n_id_modulo` (`n_id_modulo`),
  CONSTRAINT `jel_seg_roles_modulos_ibfk_1` FOREIGN KEY (`n_id_rol`) REFERENCES `jel_seg_roles` (`n_id_rol`),
  CONSTRAINT `jel_seg_roles_modulos_ibfk_2` FOREIGN KEY (`n_id_modulo`) REFERENCES `jel_seg_modulos` (`n_id_modulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jel_seg_roles_modulos`
--

LOCK TABLES `jel_seg_roles_modulos` WRITE;
/*!40000 ALTER TABLE `jel_seg_roles_modulos` DISABLE KEYS */;
/*!40000 ALTER TABLE `jel_seg_roles_modulos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jel_seg_roles_usuarios`
--

DROP TABLE IF EXISTS `jel_seg_roles_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jel_seg_roles_usuarios` (
  `n_id_rol` int NOT NULL,
  `n_id_usuario` int NOT NULL,
  PRIMARY KEY (`n_id_rol`,`n_id_usuario`),
  KEY `n_id_usuario` (`n_id_usuario`),
  CONSTRAINT `jel_seg_roles_usuarios_ibfk_1` FOREIGN KEY (`n_id_rol`) REFERENCES `jel_seg_roles` (`n_id_rol`),
  CONSTRAINT `jel_seg_roles_usuarios_ibfk_2` FOREIGN KEY (`n_id_usuario`) REFERENCES `jel_seg_usuarios` (`n_id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jel_seg_roles_usuarios`
--

LOCK TABLES `jel_seg_roles_usuarios` WRITE;
/*!40000 ALTER TABLE `jel_seg_roles_usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `jel_seg_roles_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jel_seg_usuarios`
--

DROP TABLE IF EXISTS `jel_seg_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jel_seg_usuarios` (
  `n_id_usuario` int NOT NULL AUTO_INCREMENT,
  `s_usuario` varchar(20) DEFAULT NULL,
  `s_contrasenia` varchar(255) DEFAULT NULL,
  `s_desc_usuario` varchar(100) DEFAULT NULL,
  `s_email` varchar(256) DEFAULT NULL,
  `n_id_estado_usuario` int DEFAULT NULL,
  `s_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`n_id_usuario`),
  KEY `n_id_estado_usuario` (`n_id_estado_usuario`),
  CONSTRAINT `jel_seg_usuarios_ibfk_1` FOREIGN KEY (`n_id_estado_usuario`) REFERENCES `seg_cat_estado_usuario` (`n_id_estado_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jel_seg_usuarios`
--

LOCK TABLES `jel_seg_usuarios` WRITE;
/*!40000 ALTER TABLE `jel_seg_usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `jel_seg_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pki_cat_firma_aplicada`
--

DROP TABLE IF EXISTS `pki_cat_firma_aplicada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pki_cat_firma_aplicada` (
  `id_firma_aplicada` int NOT NULL AUTO_INCREMENT,
  `desc_firma_aplicada` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_firma_aplicada`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pki_cat_firma_aplicada`
--

LOCK TABLES `pki_cat_firma_aplicada` WRITE;
/*!40000 ALTER TABLE `pki_cat_firma_aplicada` DISABLE KEYS */;
INSERT INTO `pki_cat_firma_aplicada` VALUES (1,'Firmado'),(2,'Rechazado');
/*!40000 ALTER TABLE `pki_cat_firma_aplicada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pki_cat_instruccion_doc`
--

DROP TABLE IF EXISTS `pki_cat_instruccion_doc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pki_cat_instruccion_doc` (
  `id_instruccion_doc` int NOT NULL AUTO_INCREMENT,
  `desc_instruccion_doc` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_instruccion_doc`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pki_cat_instruccion_doc`
--

LOCK TABLES `pki_cat_instruccion_doc` WRITE;
/*!40000 ALTER TABLE `pki_cat_instruccion_doc` DISABLE KEYS */;
INSERT INTO `pki_cat_instruccion_doc` VALUES (1,'Firmar'),(2,'Rubricar'),(3,'Visto bueno');
/*!40000 ALTER TABLE `pki_cat_instruccion_doc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pki_cat_tipo_firma`
--

DROP TABLE IF EXISTS `pki_cat_tipo_firma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pki_cat_tipo_firma` (
  `id_tipo_firma` int NOT NULL AUTO_INCREMENT,
  `desc_tipo_firma` varchar(20) DEFAULT NULL COMMENT 'Graciela, validar la longitud de esta etiqueta',
  PRIMARY KEY (`id_tipo_firma`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pki_cat_tipo_firma`
--

LOCK TABLES `pki_cat_tipo_firma` WRITE;
/*!40000 ALTER TABLE `pki_cat_tipo_firma` DISABLE KEYS */;
INSERT INTO `pki_cat_tipo_firma` VALUES (1,'Simple'),(2,'Múltiple');
/*!40000 ALTER TABLE `pki_cat_tipo_firma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pki_documento`
--

DROP TABLE IF EXISTS `pki_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pki_documento` (
  `s_hash_documento` varchar(64) NOT NULL,
  `n_id_num_empleado_creador` int DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `n_id_num_empleado_envio` int DEFAULT NULL,
  `fecha_envio` datetime DEFAULT NULL,
  `s_algoritmo` varchar(100) DEFAULT NULL,
  `status_documento` varchar(20) DEFAULT NULL,
  `n_en_orden` int DEFAULT NULL,
  `terminado` int DEFAULT NULL,
  PRIMARY KEY (`s_hash_documento`),
  KEY `n_id_num_empleado_creador` (`n_id_num_empleado_creador`),
  KEY `n_id_num_empleado_envio` (`n_id_num_empleado_envio`),
  CONSTRAINT `pki_documento_ibfk_1` FOREIGN KEY (`n_id_num_empleado_creador`) REFERENCES `inst_empleado` (`n_id_num_empleado`),
  CONSTRAINT `pki_documento_ibfk_2` FOREIGN KEY (`n_id_num_empleado_envio`) REFERENCES `inst_empleado` (`n_id_num_empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pki_documento`
--

LOCK TABLES `pki_documento` WRITE;
/*!40000 ALTER TABLE `pki_documento` DISABLE KEYS */;
/*!40000 ALTER TABLE `pki_documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pki_documento_destinos`
--

DROP TABLE IF EXISTS `pki_documento_destinos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pki_documento_destinos` (
  `s_hash_documento` varchar(64) NOT NULL,
  `n_id_usuario` int NOT NULL,
  `n_id_transaccion` int NOT NULL,
  `n_id_num_empleado` int DEFAULT NULL,
  `id_instruccion_doc` int DEFAULT NULL,
  `fecha_notificacion` datetime DEFAULT NULL,
  `fecha_lectura` datetime DEFAULT NULL,
  `fecha_acuse` datetime DEFAULT NULL,
  `id_firma_aplicada` int DEFAULT NULL,
  PRIMARY KEY (`s_hash_documento`,`n_id_usuario`,`n_id_transaccion`),
  KEY `n_id_usuario` (`n_id_usuario`),
  KEY `n_id_transaccion` (`n_id_transaccion`),
  KEY `n_id_num_empleado` (`n_id_num_empleado`),
  KEY `id_instruccion_doc` (`id_instruccion_doc`),
  KEY `id_firma_aplicada` (`id_firma_aplicada`),
  CONSTRAINT `pki_documento_destinos_ibfk_1` FOREIGN KEY (`s_hash_documento`) REFERENCES `pki_documento` (`s_hash_documento`),
  CONSTRAINT `pki_documento_destinos_ibfk_2` FOREIGN KEY (`n_id_usuario`) REFERENCES `seg_org_usuarios` (`n_id_usuario`),
  CONSTRAINT `pki_documento_destinos_ibfk_3` FOREIGN KEY (`n_id_transaccion`) REFERENCES `pki_transaccion` (`n_id_transaccion`),
  CONSTRAINT `pki_documento_destinos_ibfk_4` FOREIGN KEY (`n_id_num_empleado`) REFERENCES `inst_empleado` (`n_id_num_empleado`),
  CONSTRAINT `pki_documento_destinos_ibfk_5` FOREIGN KEY (`id_instruccion_doc`) REFERENCES `pki_cat_instruccion_doc` (`id_instruccion_doc`),
  CONSTRAINT `pki_documento_destinos_ibfk_6` FOREIGN KEY (`id_firma_aplicada`) REFERENCES `pki_cat_firma_aplicada` (`id_firma_aplicada`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pki_documento_destinos`
--

LOCK TABLES `pki_documento_destinos` WRITE;
/*!40000 ALTER TABLE `pki_documento_destinos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pki_documento_destinos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pki_documento_firmantes`
--

DROP TABLE IF EXISTS `pki_documento_firmantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pki_documento_firmantes` (
  `s_hash_documento` varchar(64) NOT NULL,
  `n_id_usuario` int NOT NULL COMMENT 'El id usuario será parte de la llave, ya que pueden firmar externos',
  `n_id_transaccion` int DEFAULT NULL,
  `n_id_num_empleado` int DEFAULT NULL,
  `secuencia` int DEFAULT NULL COMMENT ' Posición de la secuencia',
  `fecha_limite` datetime DEFAULT NULL,
  `fecha_firma` datetime DEFAULT NULL,
  `id_tipo_firma` int DEFAULT NULL,
  `id_firma_aplicada` int DEFAULT NULL,
  `s_cadena_firma` varchar(1000) DEFAULT NULL COMMENT 'Greys, confirmar para que se requiere este campo',
  PRIMARY KEY (`s_hash_documento`,`n_id_usuario`),
  KEY `n_id_usuario` (`n_id_usuario`),
  KEY `n_id_transaccion` (`n_id_transaccion`),
  KEY `n_id_num_empleado` (`n_id_num_empleado`),
  KEY `id_tipo_firma` (`id_tipo_firma`),
  KEY `id_firma_aplicada` (`id_firma_aplicada`),
  CONSTRAINT `pki_documento_firmantes_ibfk_1` FOREIGN KEY (`s_hash_documento`) REFERENCES `pki_documento` (`s_hash_documento`),
  CONSTRAINT `pki_documento_firmantes_ibfk_2` FOREIGN KEY (`n_id_usuario`) REFERENCES `seg_org_usuarios` (`n_id_usuario`),
  CONSTRAINT `pki_documento_firmantes_ibfk_3` FOREIGN KEY (`n_id_transaccion`) REFERENCES `pki_transaccion` (`n_id_transaccion`),
  CONSTRAINT `pki_documento_firmantes_ibfk_4` FOREIGN KEY (`n_id_num_empleado`) REFERENCES `inst_empleado` (`n_id_num_empleado`),
  CONSTRAINT `pki_documento_firmantes_ibfk_5` FOREIGN KEY (`id_tipo_firma`) REFERENCES `pki_cat_tipo_firma` (`id_tipo_firma`),
  CONSTRAINT `pki_documento_firmantes_ibfk_6` FOREIGN KEY (`id_firma_aplicada`) REFERENCES `pki_cat_firma_aplicada` (`id_firma_aplicada`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pki_documento_firmantes`
--

LOCK TABLES `pki_documento_firmantes` WRITE;
/*!40000 ALTER TABLE `pki_documento_firmantes` DISABLE KEYS */;
/*!40000 ALTER TABLE `pki_documento_firmantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pki_log_usuarios_cert`
--

DROP TABLE IF EXISTS `pki_log_usuarios_cert`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pki_log_usuarios_cert` (
  `id_log_usuarios_cert` int NOT NULL AUTO_INCREMENT,
  `s_curp` varchar(20) DEFAULT NULL,
  `s_x509_serial_number` varchar(48) DEFAULT NULL,
  `s_bitacora` varchar(1024) DEFAULT NULL,
  `s_sha256_registro` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id_log_usuarios_cert`),
  KEY `s_x509_serial_number` (`s_x509_serial_number`),
  CONSTRAINT `pki_log_usuarios_cert_ibfk_1` FOREIGN KEY (`s_x509_serial_number`) REFERENCES `pki_x509_registrados` (`s_x509_serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pki_log_usuarios_cert`
--

LOCK TABLES `pki_log_usuarios_cert` WRITE;
/*!40000 ALTER TABLE `pki_log_usuarios_cert` DISABLE KEYS */;
/*!40000 ALTER TABLE `pki_log_usuarios_cert` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pki_transaccion`
--

DROP TABLE IF EXISTS `pki_transaccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pki_transaccion` (
  `n_id_transaccion` int NOT NULL AUTO_INCREMENT,
  `s_request_uuid_filehash` varchar(32) DEFAULT NULL,
  `s_x509_serial_number` varchar(48) NOT NULL,
  `s_uuid_ocsp` varchar(36) DEFAULT NULL,
  `s_uuid_tsp` varchar(36) DEFAULT NULL,
  `s_cadena_firma` varchar(512) DEFAULT NULL,
  `s_request_uuid_filename` varchar(255) DEFAULT NULL,
  `s_clob_json_request` text,
  `n_id_transaccion_block` int DEFAULT NULL,
  PRIMARY KEY (`n_id_transaccion`),
  KEY `s_x509_serial_number` (`s_x509_serial_number`),
  KEY `s_uuid_ocsp` (`s_uuid_ocsp`),
  KEY `s_uuid_tsp` (`s_uuid_tsp`),
  KEY `n_id_transaccion_block` (`n_id_transaccion_block`),
  CONSTRAINT `pki_transaccion_ibfk_1` FOREIGN KEY (`s_x509_serial_number`) REFERENCES `pki_x509_registrados` (`s_x509_serial_number`),
  CONSTRAINT `pki_transaccion_ibfk_2` FOREIGN KEY (`s_uuid_ocsp`) REFERENCES `pki_x509_ocsp` (`s_uuid_ocsp`),
  CONSTRAINT `pki_transaccion_ibfk_3` FOREIGN KEY (`s_uuid_tsp`) REFERENCES `pki_x509_tsp` (`s_uuid_tsp`),
  CONSTRAINT `pki_transaccion_ibfk_4` FOREIGN KEY (`n_id_transaccion_block`) REFERENCES `pki_transaccion` (`n_id_transaccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pki_transaccion`
--

LOCK TABLES `pki_transaccion` WRITE;
/*!40000 ALTER TABLE `pki_transaccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `pki_transaccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pki_usuarios_cert`
--

DROP TABLE IF EXISTS `pki_usuarios_cert`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pki_usuarios_cert` (
  `n_id_usuario_firma` int NOT NULL,
  `s_x509_serial_number` varchar(48) NOT NULL,
  `s_curp` varchar(20) DEFAULT NULL,
  `s_rfc` varchar(14) DEFAULT NULL,
  `s_sha256_registro` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`n_id_usuario_firma`,`s_x509_serial_number`),
  KEY `s_x509_serial_number` (`s_x509_serial_number`),
  CONSTRAINT `pki_usuarios_cert_ibfk_1` FOREIGN KEY (`n_id_usuario_firma`) REFERENCES `seg_org_usuarios` (`n_id_usuario`),
  CONSTRAINT `pki_usuarios_cert_ibfk_2` FOREIGN KEY (`s_x509_serial_number`) REFERENCES `pki_x509_registrados` (`s_x509_serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pki_usuarios_cert`
--

LOCK TABLES `pki_usuarios_cert` WRITE;
/*!40000 ALTER TABLE `pki_usuarios_cert` DISABLE KEYS */;
/*!40000 ALTER TABLE `pki_usuarios_cert` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pki_x509_ac_autorizadas`
--

DROP TABLE IF EXISTS `pki_x509_ac_autorizadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pki_x509_ac_autorizadas` (
  `s_x509_emisor_serial` varchar(48) NOT NULL,
  `s_x509_ac_der_b64` varchar(5125) DEFAULT NULL,
  `s_x509_emisor_autoridad` varchar(256) DEFAULT NULL,
  `s_tipo_certificado` varchar(10) DEFAULT NULL COMMENT 'Tipo de certificado (OCSP, INTERMEDIO, RAIZ)',
  `s_url` varchar(255) DEFAULT NULL,
  `s_x509_emisor_serial_parent` varchar(48) DEFAULT NULL,
  `x509emisor_serial_parent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`s_x509_emisor_serial`),
  KEY `s_x509_emisor_serial_parent` (`s_x509_emisor_serial_parent`),
  KEY `FKbxewbql3f7a21rhh2hab5c3l1` (`x509emisor_serial_parent`),
  CONSTRAINT `FKbxewbql3f7a21rhh2hab5c3l1` FOREIGN KEY (`x509emisor_serial_parent`) REFERENCES `pki_x509_ac_autorizadas` (`s_x509_emisor_serial`),
  CONSTRAINT `pki_x509_ac_autorizadas_ibfk_1` FOREIGN KEY (`s_x509_emisor_serial_parent`) REFERENCES `pki_x509_ac_autorizadas` (`s_x509_emisor_serial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pki_x509_ac_autorizadas`
--

LOCK TABLES `pki_x509_ac_autorizadas` WRITE;
/*!40000 ALTER TABLE `pki_x509_ac_autorizadas` DISABLE KEYS */;
INSERT INTO `pki_x509_ac_autorizadas` VALUES ('11111',NULL,NULL,NULL,NULL,'11111',NULL);
/*!40000 ALTER TABLE `pki_x509_ac_autorizadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pki_x509_documento_certificado`
--

DROP TABLE IF EXISTS `pki_x509_documento_certificado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pki_x509_documento_certificado` (
  `n_id_documento_certificado` int NOT NULL AUTO_INCREMENT,
  `n_id_certificado_x509` varchar(20) DEFAULT NULL,
  `s_path_documento` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`n_id_documento_certificado`),
  KEY `n_id_certificado_x509` (`n_id_certificado_x509`),
  CONSTRAINT `pki_x509_documento_certificado_ibfk_1` FOREIGN KEY (`n_id_certificado_x509`) REFERENCES `pki_x509_registrados` (`s_x509_serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pki_x509_documento_certificado`
--

LOCK TABLES `pki_x509_documento_certificado` WRITE;
/*!40000 ALTER TABLE `pki_x509_documento_certificado` DISABLE KEYS */;
/*!40000 ALTER TABLE `pki_x509_documento_certificado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pki_x509_jel_autorizacion`
--

DROP TABLE IF EXISTS `pki_x509_jel_autorizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pki_x509_jel_autorizacion` (
  `n_id_jel_autorizacion` int NOT NULL AUTO_INCREMENT,
  `n_id_certificado_x509` varchar(20) DEFAULT NULL,
  `s_expediente` varchar(80) DEFAULT NULL,
  `s_revocado` varchar(80) DEFAULT NULL,
  `s_token_vigencia` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`n_id_jel_autorizacion`),
  KEY `n_id_certificado_x509` (`n_id_certificado_x509`),
  CONSTRAINT `pki_x509_jel_autorizacion_ibfk_1` FOREIGN KEY (`n_id_certificado_x509`) REFERENCES `pki_x509_registrados` (`s_x509_serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pki_x509_jel_autorizacion`
--

LOCK TABLES `pki_x509_jel_autorizacion` WRITE;
/*!40000 ALTER TABLE `pki_x509_jel_autorizacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `pki_x509_jel_autorizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pki_x509_ocsp`
--

DROP TABLE IF EXISTS `pki_x509_ocsp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pki_x509_ocsp` (
  `s_uuid_ocsp` varchar(36) NOT NULL,
  `s_x509_serial_number` varchar(48) DEFAULT NULL,
  `s_ocsp_response_der_b64` varchar(4096) DEFAULT NULL,
  `s_ocsp_response_path` varchar(255) DEFAULT NULL,
  `s_x509_serial_responder` varchar(48) DEFAULT NULL,
  `d_fecha_response` datetime DEFAULT NULL,
  `s_ocsp_indicador` varchar(8) DEFAULT NULL,
  `s_uuid_ocsp_block` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`s_uuid_ocsp`),
  KEY `s_x509_serial_number` (`s_x509_serial_number`),
  KEY `s_uuid_ocsp_block` (`s_uuid_ocsp_block`),
  CONSTRAINT `pki_x509_ocsp_ibfk_1` FOREIGN KEY (`s_x509_serial_number`) REFERENCES `pki_x509_registrados` (`s_x509_serial_number`),
  CONSTRAINT `pki_x509_ocsp_ibfk_2` FOREIGN KEY (`s_uuid_ocsp_block`) REFERENCES `pki_x509_ocsp` (`s_uuid_ocsp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pki_x509_ocsp`
--

LOCK TABLES `pki_x509_ocsp` WRITE;
/*!40000 ALTER TABLE `pki_x509_ocsp` DISABLE KEYS */;
/*!40000 ALTER TABLE `pki_x509_ocsp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pki_x509_registrados`
--

DROP TABLE IF EXISTS `pki_x509_registrados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pki_x509_registrados` (
  `s_x509_serial_number` varchar(48) NOT NULL,
  `s_x509_der_b64` varchar(5125) DEFAULT NULL,
  `s_x509_sha256_cert` varchar(64) DEFAULT NULL,
  `s_x509_emisor_serial` varchar(48) DEFAULT NULL,
  `s_x509_subject` varchar(512) DEFAULT NULL,
  `s_x509_rfc` varchar(13) DEFAULT NULL,
  `s_x509_curp` varchar(18) DEFAULT NULL,
  `s_x509_nombre` varchar(50) NOT NULL,
  `s_x509_apellido1` varchar(50) NOT NULL,
  `s_x509_apellido2` varchar(50) DEFAULT NULL,
  `s_sha256_registro` varchar(64) DEFAULT NULL,
  `s_token_vigencia` varchar(64) DEFAULT NULL,
  `d_fecha_registro` datetime DEFAULT NULL,
  `d_fecha_revocacion` datetime DEFAULT NULL,
  PRIMARY KEY (`s_x509_serial_number`),
  KEY `s_x509_emisor_serial` (`s_x509_emisor_serial`),
  CONSTRAINT `pki_x509_registrados_ibfk_1` FOREIGN KEY (`s_x509_emisor_serial`) REFERENCES `pki_x509_ac_autorizadas` (`s_x509_emisor_serial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pki_x509_registrados`
--

LOCK TABLES `pki_x509_registrados` WRITE;
/*!40000 ALTER TABLE `pki_x509_registrados` DISABLE KEYS */;
/*!40000 ALTER TABLE `pki_x509_registrados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pki_x509_tsp`
--

DROP TABLE IF EXISTS `pki_x509_tsp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pki_x509_tsp` (
  `s_uuid_tsp` varchar(36) NOT NULL,
  `s_x509_serial_number` varchar(48) DEFAULT NULL,
  `s_tsp_response_der_b64` varchar(5125) DEFAULT NULL,
  `s_tsp_response_path` varchar(255) DEFAULT NULL,
  `s_x509_serial_stamper` varchar(48) DEFAULT NULL,
  `d_fecha_response` datetime DEFAULT NULL,
  `s_tsp_indicador` varchar(8) DEFAULT NULL,
  `s_uuid_tsp_block` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`s_uuid_tsp`),
  KEY `s_x509_serial_number` (`s_x509_serial_number`),
  KEY `s_uuid_tsp_block` (`s_uuid_tsp_block`),
  CONSTRAINT `pki_x509_tsp_ibfk_1` FOREIGN KEY (`s_x509_serial_number`) REFERENCES `pki_x509_registrados` (`s_x509_serial_number`),
  CONSTRAINT `pki_x509_tsp_ibfk_2` FOREIGN KEY (`s_uuid_tsp_block`) REFERENCES `pki_x509_tsp` (`s_uuid_tsp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pki_x509_tsp`
--

LOCK TABLES `pki_x509_tsp` WRITE;
/*!40000 ALTER TABLE `pki_x509_tsp` DISABLE KEYS */;
/*!40000 ALTER TABLE `pki_x509_tsp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seg_cat_estado_usuario`
--

DROP TABLE IF EXISTS `seg_cat_estado_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seg_cat_estado_usuario` (
  `n_id_estado_usuario` int NOT NULL AUTO_INCREMENT,
  `s_descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`n_id_estado_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seg_cat_estado_usuario`
--

LOCK TABLES `seg_cat_estado_usuario` WRITE;
/*!40000 ALTER TABLE `seg_cat_estado_usuario` DISABLE KEYS */;
INSERT INTO `seg_cat_estado_usuario` VALUES (1,'Pendiente'),(2,'Proceso'),(3,'Inactiva'),(4,'Verificada'),(5,'Activa'),(6,'Suspendida'),(7,'Eliminada'),(8,'Revocada');
/*!40000 ALTER TABLE `seg_cat_estado_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seg_cat_nivel_modulo`
--

DROP TABLE IF EXISTS `seg_cat_nivel_modulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seg_cat_nivel_modulo` (
  `n_id_nivel` int NOT NULL AUTO_INCREMENT,
  `desc_nivel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`n_id_nivel`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seg_cat_nivel_modulo`
--

LOCK TABLES `seg_cat_nivel_modulo` WRITE;
/*!40000 ALTER TABLE `seg_cat_nivel_modulo` DISABLE KEYS */;
INSERT INTO `seg_cat_nivel_modulo` VALUES (1,'Aplicación'),(2,'Módulo'),(3,'Sub Módulo'),(4,'Pantalla');
/*!40000 ALTER TABLE `seg_cat_nivel_modulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seg_log_sistema`
--

DROP TABLE IF EXISTS `seg_log_sistema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seg_log_sistema` (
  `id_log_sistema` int NOT NULL AUTO_INCREMENT,
  `n_id_usuario_org` int DEFAULT NULL COMMENT ' Solo para usuarios de organización',
  `n_id_usuario_jel` int DEFAULT NULL COMMENT ' Solo para usuarios JEL',
  `d_sistema` datetime DEFAULT NULL,
  `bitacora` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id_log_sistema`),
  KEY `n_id_usuario_org` (`n_id_usuario_org`),
  KEY `n_id_usuario_jel` (`n_id_usuario_jel`),
  CONSTRAINT `seg_log_sistema_ibfk_1` FOREIGN KEY (`n_id_usuario_org`) REFERENCES `seg_org_usuarios` (`n_id_usuario`),
  CONSTRAINT `seg_log_sistema_ibfk_2` FOREIGN KEY (`n_id_usuario_jel`) REFERENCES `jel_seg_usuarios` (`n_id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seg_log_sistema`
--

LOCK TABLES `seg_log_sistema` WRITE;
/*!40000 ALTER TABLE `seg_log_sistema` DISABLE KEYS */;
/*!40000 ALTER TABLE `seg_log_sistema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seg_log_usuario`
--

DROP TABLE IF EXISTS `seg_log_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seg_log_usuario` (
  `id_log_usuario` int NOT NULL AUTO_INCREMENT,
  `n_id_usuario` int DEFAULT NULL,
  `d_sistema` datetime DEFAULT NULL,
  `n_session_id` int DEFAULT NULL COMMENT 'Guardar la sesión que modificó el registro',
  `bitacora` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id_log_usuario`),
  KEY `n_id_usuario` (`n_id_usuario`),
  CONSTRAINT `seg_log_usuario_ibfk_1` FOREIGN KEY (`n_id_usuario`) REFERENCES `seg_org_usuarios` (`n_id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seg_log_usuario`
--

LOCK TABLES `seg_log_usuario` WRITE;
/*!40000 ALTER TABLE `seg_log_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `seg_log_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seg_org_log_sesion`
--

DROP TABLE IF EXISTS `seg_org_log_sesion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seg_org_log_sesion` (
  `n_session_id` int NOT NULL AUTO_INCREMENT,
  `n_id_usuario` int DEFAULT NULL,
  `d_fecha_inicio` timestamp NULL DEFAULT NULL,
  `d_fecha_fin` timestamp NULL DEFAULT NULL COMMENT 'El usuario realizo un logout',
  `n_end_sesion` int DEFAULT NULL COMMENT 'Fin de la sesión en milisegundos',
  `chain_n_session_id` int DEFAULT NULL,
  PRIMARY KEY (`n_session_id`),
  KEY `n_id_usuario` (`n_id_usuario`),
  KEY `chain_n_session_id` (`chain_n_session_id`),
  CONSTRAINT `seg_org_log_sesion_ibfk_1` FOREIGN KEY (`n_id_usuario`) REFERENCES `seg_org_usuarios` (`n_id_usuario`),
  CONSTRAINT `seg_org_log_sesion_ibfk_2` FOREIGN KEY (`chain_n_session_id`) REFERENCES `seg_org_log_sesion` (`n_session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seg_org_log_sesion`
--

LOCK TABLES `seg_org_log_sesion` WRITE;
/*!40000 ALTER TABLE `seg_org_log_sesion` DISABLE KEYS */;
/*!40000 ALTER TABLE `seg_org_log_sesion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seg_org_modulos`
--

DROP TABLE IF EXISTS `seg_org_modulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seg_org_modulos` (
  `n_id_modulo` int NOT NULL AUTO_INCREMENT,
  `n_id_nivel` int DEFAULT NULL,
  `desc_modulo` varchar(100) DEFAULT NULL,
  `n_id_modulo_padre` int DEFAULT NULL,
  `menu` varchar(1) DEFAULT NULL COMMENT 'S- Si, forma parte del menú',
  `menu_desc` varchar(60) DEFAULT NULL,
  `menu_url` varchar(60) DEFAULT NULL,
  `menu_pos` int DEFAULT NULL COMMENT 'Sirve para presentar la posición del menú',
  PRIMARY KEY (`n_id_modulo`),
  KEY `n_id_nivel` (`n_id_nivel`),
  KEY `n_id_modulo_padre` (`n_id_modulo_padre`),
  CONSTRAINT `seg_org_modulos_ibfk_1` FOREIGN KEY (`n_id_nivel`) REFERENCES `seg_cat_nivel_modulo` (`n_id_nivel`),
  CONSTRAINT `seg_org_modulos_ibfk_2` FOREIGN KEY (`n_id_modulo_padre`) REFERENCES `seg_org_modulos` (`n_id_modulo`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seg_org_modulos`
--

LOCK TABLES `seg_org_modulos` WRITE;
/*!40000 ALTER TABLE `seg_org_modulos` DISABLE KEYS */;
INSERT INTO `seg_org_modulos` VALUES (-1,1,'No parent',NULL,'N',NULL,NULL,0),(1,1,'Sistema de Firma Electronica',-1,'N',NULL,NULL,1),(2,2,'Configuración',1,'S',NULL,'/configuracion',1),(3,4,'Privacidad y seguridad',2,'S',NULL,'/configuracion/privacidad-seguridad',1),(4,4,'Apariencia',2,'S',NULL,'/configuracion/apariencia',2),(5,2,'Documentos',1,'S',NULL,'/documentos',2),(6,3,'Mis Documentos',5,'S',NULL,'/documentos/mis-documentos',1),(7,4,'Seguimiento',6,'S',NULL,'/documentos/mis-documentos/seguimiento',1),(8,4,'Informe Panorámico',6,'S',NULL,'/documentos/mis-documentos/informe-panoramico',2),(9,4,'Faltantes',6,'S',NULL,'/documentos/mis-documentos/faltantes',3),(10,4,'Completados',6,'S',NULL,'/documentos/mis-documentos/completados',4),(11,4,'Firma',6,'S',NULL,'/documentos/mis-documentos/firma',5);
/*!40000 ALTER TABLE `seg_org_modulos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seg_org_roles`
--

DROP TABLE IF EXISTS `seg_org_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seg_org_roles` (
  `n_id_rol` int NOT NULL AUTO_INCREMENT,
  `s_etiqueta_rol` varchar(15) DEFAULT NULL,
  `s_descripcion` varchar(40) DEFAULT NULL,
  `n_id_rol_padre` int DEFAULT NULL,
  `n_rec_activo` int DEFAULT NULL,
  `n_session_id` int DEFAULT NULL,
  PRIMARY KEY (`n_id_rol`),
  UNIQUE KEY `s_etiqueta_rol` (`s_etiqueta_rol`),
  KEY `n_id_rol_padre` (`n_id_rol_padre`),
  KEY `n_session_id` (`n_session_id`),
  CONSTRAINT `seg_org_roles_ibfk_1` FOREIGN KEY (`n_id_rol_padre`) REFERENCES `seg_org_roles` (`n_id_rol`),
  CONSTRAINT `seg_org_roles_ibfk_2` FOREIGN KEY (`n_session_id`) REFERENCES `seg_org_log_sesion` (`n_session_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seg_org_roles`
--

LOCK TABLES `seg_org_roles` WRITE;
/*!40000 ALTER TABLE `seg_org_roles` DISABLE KEYS */;
INSERT INTO `seg_org_roles` VALUES (1,'SA','Super administrador',1,1,NULL),(2,'AG','Administrador General',1,1,NULL),(3,'USID','USI Directorio',1,1,NULL),(4,'COM','Comunicación',1,1,NULL);
/*!40000 ALTER TABLE `seg_org_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seg_org_roles_modulos`
--

DROP TABLE IF EXISTS `seg_org_roles_modulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seg_org_roles_modulos` (
  `n_id_rol` int NOT NULL,
  `n_id_modulo` int NOT NULL,
  `crear` varchar(1) DEFAULT NULL COMMENT 'S- Si, N-No, Null-No',
  `leer` varchar(1) DEFAULT NULL,
  `editar` varchar(1) DEFAULT NULL,
  `eliminar` varchar(1) DEFAULT NULL,
  `publico` varchar(1) DEFAULT NULL COMMENT 'S- Si, se mostrará al publico sin autenticar',
  `n_session_id` int DEFAULT NULL COMMENT 'Guardar la sesión que modificó el registro',
  PRIMARY KEY (`n_id_rol`,`n_id_modulo`),
  KEY `n_id_modulo` (`n_id_modulo`),
  CONSTRAINT `seg_org_roles_modulos_ibfk_1` FOREIGN KEY (`n_id_rol`) REFERENCES `seg_org_roles` (`n_id_rol`),
  CONSTRAINT `seg_org_roles_modulos_ibfk_2` FOREIGN KEY (`n_id_modulo`) REFERENCES `seg_org_modulos` (`n_id_modulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seg_org_roles_modulos`
--

LOCK TABLES `seg_org_roles_modulos` WRITE;
/*!40000 ALTER TABLE `seg_org_roles_modulos` DISABLE KEYS */;
INSERT INTO `seg_org_roles_modulos` VALUES (1,1,'S','S','S','S','N',NULL),(1,2,'S','S','N','S','N',NULL),(1,3,'S','S','N','S','N',NULL),(1,4,'S','S','N','S','N',NULL),(1,5,'S','S','N','S','N',NULL),(1,6,'S','S','N','S','N',NULL),(1,7,'S','S','N','S','N',NULL),(1,8,'S','S','N','S','N',NULL),(1,9,'S','S','N','S','N',NULL),(1,10,'S','S','N','S','N',NULL),(1,11,'S','S','N','S','N',NULL),(3,1,'S','S','N','S','N',NULL),(3,5,'S','S','N','S','N',NULL),(3,6,'S','S','N','S','N',NULL),(3,11,'S','S','N','S','N',NULL);
/*!40000 ALTER TABLE `seg_org_roles_modulos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seg_org_roles_usuarios`
--

DROP TABLE IF EXISTS `seg_org_roles_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seg_org_roles_usuarios` (
  `n_id_rol_usuario` int NOT NULL AUTO_INCREMENT,
  `n_id_rol` int DEFAULT NULL,
  `n_id_usuario` int DEFAULT NULL,
  `n_id_u_adscripcion` int DEFAULT NULL,
  `n_session_id` int DEFAULT NULL,
  PRIMARY KEY (`n_id_rol_usuario`),
  KEY `n_id_rol` (`n_id_rol`),
  KEY `n_id_usuario` (`n_id_usuario`),
  KEY `n_id_u_adscripcion` (`n_id_u_adscripcion`),
  KEY `n_session_id` (`n_session_id`),
  CONSTRAINT `seg_org_roles_usuarios_ibfk_1` FOREIGN KEY (`n_id_rol`) REFERENCES `seg_org_roles` (`n_id_rol`),
  CONSTRAINT `seg_org_roles_usuarios_ibfk_2` FOREIGN KEY (`n_id_usuario`) REFERENCES `seg_org_usuarios` (`n_id_usuario`),
  CONSTRAINT `seg_org_roles_usuarios_ibfk_3` FOREIGN KEY (`n_id_u_adscripcion`) REFERENCES `inst_u_adscripcion` (`n_id_u_adscripcion`),
  CONSTRAINT `seg_org_roles_usuarios_ibfk_4` FOREIGN KEY (`n_session_id`) REFERENCES `seg_org_log_sesion` (`n_session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seg_org_roles_usuarios`
--

LOCK TABLES `seg_org_roles_usuarios` WRITE;
/*!40000 ALTER TABLE `seg_org_roles_usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `seg_org_roles_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seg_org_usuario_estado_usuario`
--

DROP TABLE IF EXISTS `seg_org_usuario_estado_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seg_org_usuario_estado_usuario` (
  `n_id_usuario_status` int NOT NULL AUTO_INCREMENT,
  `n_id_usuario` int DEFAULT NULL,
  `n_id_estado_usuario` int DEFAULT NULL,
  `fingerprint_dispositivo` varchar(255) DEFAULT NULL,
  `d_fecha_status` datetime DEFAULT NULL,
  `n_session_id` int DEFAULT NULL,
  PRIMARY KEY (`n_id_usuario_status`),
  KEY `n_id_usuario` (`n_id_usuario`),
  KEY `n_id_estado_usuario` (`n_id_estado_usuario`),
  KEY `n_session_id` (`n_session_id`),
  CONSTRAINT `seg_org_usuario_estado_usuario_ibfk_1` FOREIGN KEY (`n_id_usuario`) REFERENCES `seg_org_usuarios` (`n_id_usuario`),
  CONSTRAINT `seg_org_usuario_estado_usuario_ibfk_2` FOREIGN KEY (`n_id_estado_usuario`) REFERENCES `seg_cat_estado_usuario` (`n_id_estado_usuario`),
  CONSTRAINT `seg_org_usuario_estado_usuario_ibfk_3` FOREIGN KEY (`n_session_id`) REFERENCES `seg_org_log_sesion` (`n_session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seg_org_usuario_estado_usuario`
--

LOCK TABLES `seg_org_usuario_estado_usuario` WRITE;
/*!40000 ALTER TABLE `seg_org_usuario_estado_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `seg_org_usuario_estado_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seg_org_usuarios`
--

DROP TABLE IF EXISTS `seg_org_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seg_org_usuarios` (
  `n_id_usuario` int NOT NULL AUTO_INCREMENT,
  `s_usuario` varchar(20) DEFAULT NULL,
  `s_contrasenia` varchar(255) DEFAULT NULL,
  `s_desc_usuario` varchar(100) DEFAULT NULL,
  `s_email` varchar(256) DEFAULT NULL,
  `n_id_estado_usuario` int DEFAULT NULL,
  `s_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`n_id_usuario`),
  UNIQUE KEY `s_email` (`s_email`),
  KEY `n_id_estado_usuario` (`n_id_estado_usuario`),
  CONSTRAINT `seg_org_usuarios_ibfk_1` FOREIGN KEY (`n_id_estado_usuario`) REFERENCES `seg_cat_estado_usuario` (`n_id_estado_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seg_org_usuarios`
--

LOCK TABLES `seg_org_usuarios` WRITE;
/*!40000 ALTER TABLE `seg_org_usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `seg_org_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_cat_destino_documento`
--

DROP TABLE IF EXISTS `tab_cat_destino_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_cat_destino_documento` (
  `n_id_tipo_destino` int NOT NULL AUTO_INCREMENT,
  `desc_destino_documento` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`n_id_tipo_destino`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_cat_destino_documento`
--

LOCK TABLES `tab_cat_destino_documento` WRITE;
/*!40000 ALTER TABLE `tab_cat_destino_documento` DISABLE KEYS */;
INSERT INTO `tab_cat_destino_documento` VALUES (1,'Interno'),(2,'Externo');
/*!40000 ALTER TABLE `tab_cat_destino_documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_cat_doc_config`
--

DROP TABLE IF EXISTS `tab_cat_doc_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_cat_doc_config` (
  `n_id_doc_config` int NOT NULL AUTO_INCREMENT,
  `s_atributo` varchar(12) DEFAULT NULL,
  `s_valor` varchar(127) DEFAULT NULL,
  PRIMARY KEY (`n_id_doc_config`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_cat_doc_config`
--

LOCK TABLES `tab_cat_doc_config` WRITE;
/*!40000 ALTER TABLE `tab_cat_doc_config` DISABLE KEYS */;
INSERT INTO `tab_cat_doc_config` VALUES (1,'FIRM','Firmar en orden'),(2,'MODCAP','Mantener modo captura'),(3,'GNUMOF','Generar numero de oficio');
/*!40000 ALTER TABLE `tab_cat_doc_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_cat_etapa_documento`
--

DROP TABLE IF EXISTS `tab_cat_etapa_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_cat_etapa_documento` (
  `id_etapa_documento` int NOT NULL AUTO_INCREMENT,
  `s_desc_etapa` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id_etapa_documento`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_cat_etapa_documento`
--

LOCK TABLES `tab_cat_etapa_documento` WRITE;
/*!40000 ALTER TABLE `tab_cat_etapa_documento` DISABLE KEYS */;
INSERT INTO `tab_cat_etapa_documento` VALUES (1,'Creado'),(2,'Enviado'),(4,'En Firma'),(5,'Rechazado'),(6,'Terminado');
/*!40000 ALTER TABLE `tab_cat_etapa_documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_cat_inst_dest`
--

DROP TABLE IF EXISTS `tab_cat_inst_dest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_cat_inst_dest` (
  `n_id_inst_dest` int NOT NULL AUTO_INCREMENT,
  `desc_inst_dest` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`n_id_inst_dest`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_cat_inst_dest`
--

LOCK TABLES `tab_cat_inst_dest` WRITE;
/*!40000 ALTER TABLE `tab_cat_inst_dest` DISABLE KEYS */;
INSERT INTO `tab_cat_inst_dest` VALUES (1,'Atención'),(2,'Conocimiento');
/*!40000 ALTER TABLE `tab_cat_inst_dest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_cat_inst_firmantes`
--

DROP TABLE IF EXISTS `tab_cat_inst_firmantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_cat_inst_firmantes` (
  `n_id_inst_firmante` int NOT NULL AUTO_INCREMENT,
  `desc_instr_firmante` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`n_id_inst_firmante`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_cat_inst_firmantes`
--

LOCK TABLES `tab_cat_inst_firmantes` WRITE;
/*!40000 ALTER TABLE `tab_cat_inst_firmantes` DISABLE KEYS */;
INSERT INTO `tab_cat_inst_firmantes` VALUES (1,'Firma'),(2,'Rubrica'),(3,'Acuse');
/*!40000 ALTER TABLE `tab_cat_inst_firmantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_cat_prioridad`
--

DROP TABLE IF EXISTS `tab_cat_prioridad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_cat_prioridad` (
  `n_id_prioridad` int NOT NULL AUTO_INCREMENT,
  `desc_prioridad` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`n_id_prioridad`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_cat_prioridad`
--

LOCK TABLES `tab_cat_prioridad` WRITE;
/*!40000 ALTER TABLE `tab_cat_prioridad` DISABLE KEYS */;
INSERT INTO `tab_cat_prioridad` VALUES (1,'Alta'),(2,'Media'),(3,'Baja');
/*!40000 ALTER TABLE `tab_cat_prioridad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_cat_tipo_documento`
--

DROP TABLE IF EXISTS `tab_cat_tipo_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_cat_tipo_documento` (
  `n_id_tipo_documento` int NOT NULL AUTO_INCREMENT,
  `n_id_cat_area` int DEFAULT NULL,
  `desc_tipo_documento` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`n_id_tipo_documento`),
  KEY `n_id_cat_area` (`n_id_cat_area`),
  CONSTRAINT `tab_cat_tipo_documento_ibfk_1` FOREIGN KEY (`n_id_cat_area`) REFERENCES `inst_cat_areas` (`n_id_cat_area`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_cat_tipo_documento`
--

LOCK TABLES `tab_cat_tipo_documento` WRITE;
/*!40000 ALTER TABLE `tab_cat_tipo_documento` DISABLE KEYS */;
INSERT INTO `tab_cat_tipo_documento` VALUES (1,72,'Acuerdo de radicación'),(2,72,'Acuerdo de recepción'),(3,72,'Acuerdo de turno'),(4,72,'Oficio'),(5,72,'Acuerdo plenario'),(6,72,'Minuta');
/*!40000 ALTER TABLE `tab_cat_tipo_documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_cat_tipo_notificacion`
--

DROP TABLE IF EXISTS `tab_cat_tipo_notificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_cat_tipo_notificacion` (
  `n_id_tipo_notif` int NOT NULL AUTO_INCREMENT,
  `desc_tipo` varchar(30) DEFAULT NULL,
  `icon_tipo_notif` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`n_id_tipo_notif`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_cat_tipo_notificacion`
--

LOCK TABLES `tab_cat_tipo_notificacion` WRITE;
/*!40000 ALTER TABLE `tab_cat_tipo_notificacion` DISABLE KEYS */;
INSERT INTO `tab_cat_tipo_notificacion` VALUES (1,'tipo notificacion 1','icono_notificacion1');
/*!40000 ALTER TABLE `tab_cat_tipo_notificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_doc_config`
--

DROP TABLE IF EXISTS `tab_doc_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_doc_config` (
  `n_id_documento` int NOT NULL,
  `n_id_doc_config` int NOT NULL,
  PRIMARY KEY (`n_id_documento`,`n_id_doc_config`),
  KEY `n_id_doc_config` (`n_id_doc_config`),
  CONSTRAINT `tab_doc_config_ibfk_1` FOREIGN KEY (`n_id_documento`) REFERENCES `tab_documentos` (`n_id_documento`),
  CONSTRAINT `tab_doc_config_ibfk_2` FOREIGN KEY (`n_id_doc_config`) REFERENCES `tab_cat_doc_config` (`n_id_doc_config`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_doc_config`
--

LOCK TABLES `tab_doc_config` WRITE;
/*!40000 ALTER TABLE `tab_doc_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `tab_doc_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_doc_destinatarios`
--

DROP TABLE IF EXISTS `tab_doc_destinatarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_doc_destinatarios` (
  `n_id_documento` int NOT NULL,
  `n_id_num_empleado` int NOT NULL,
  `n_id_inst_dest` int DEFAULT NULL,
  PRIMARY KEY (`n_id_documento`,`n_id_num_empleado`),
  KEY `n_id_num_empleado` (`n_id_num_empleado`),
  KEY `n_id_inst_dest` (`n_id_inst_dest`),
  CONSTRAINT `tab_doc_destinatarios_ibfk_1` FOREIGN KEY (`n_id_documento`) REFERENCES `tab_documentos` (`n_id_documento`),
  CONSTRAINT `tab_doc_destinatarios_ibfk_2` FOREIGN KEY (`n_id_num_empleado`) REFERENCES `inst_empleado` (`n_id_num_empleado`),
  CONSTRAINT `tab_doc_destinatarios_ibfk_3` FOREIGN KEY (`n_id_inst_dest`) REFERENCES `tab_cat_inst_dest` (`n_id_inst_dest`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_doc_destinatarios`
--

LOCK TABLES `tab_doc_destinatarios` WRITE;
/*!40000 ALTER TABLE `tab_doc_destinatarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `tab_doc_destinatarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_doc_grupo_firmas_personas`
--

DROP TABLE IF EXISTS `tab_doc_grupo_firmas_personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_doc_grupo_firmas_personas` (
  `n_id_grupo_personas` int NOT NULL,
  `n_id_num_empleado` int NOT NULL,
  `n_id_inst_firmante` int DEFAULT NULL,
  `n_id_inst_destinatario` int DEFAULT NULL,
  PRIMARY KEY (`n_id_grupo_personas`,`n_id_num_empleado`),
  KEY `n_id_num_empleado` (`n_id_num_empleado`),
  KEY `n_id_inst_firmante` (`n_id_inst_firmante`),
  KEY `n_id_inst_destinatario` (`n_id_inst_destinatario`),
  CONSTRAINT `tab_doc_grupo_firmas_personas_ibfk_1` FOREIGN KEY (`n_id_grupo_personas`) REFERENCES `tab_doc_grupos_firmas` (`n_id_grupo_firmas`),
  CONSTRAINT `tab_doc_grupo_firmas_personas_ibfk_2` FOREIGN KEY (`n_id_num_empleado`) REFERENCES `inst_empleado` (`n_id_num_empleado`),
  CONSTRAINT `tab_doc_grupo_firmas_personas_ibfk_3` FOREIGN KEY (`n_id_inst_firmante`) REFERENCES `tab_cat_inst_firmantes` (`n_id_inst_firmante`),
  CONSTRAINT `tab_doc_grupo_firmas_personas_ibfk_4` FOREIGN KEY (`n_id_inst_destinatario`) REFERENCES `tab_cat_inst_dest` (`n_id_inst_dest`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_doc_grupo_firmas_personas`
--

LOCK TABLES `tab_doc_grupo_firmas_personas` WRITE;
/*!40000 ALTER TABLE `tab_doc_grupo_firmas_personas` DISABLE KEYS */;
/*!40000 ALTER TABLE `tab_doc_grupo_firmas_personas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_doc_grupos_firmas`
--

DROP TABLE IF EXISTS `tab_doc_grupos_firmas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_doc_grupos_firmas` (
  `n_id_grupo_firmas` int NOT NULL AUTO_INCREMENT,
  `n_id_cat_area` int DEFAULT NULL,
  `c_tipo_grupo` varchar(20) DEFAULT NULL,
  `s_nombre_gpo_firmante` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`n_id_grupo_firmas`),
  KEY `n_id_cat_area` (`n_id_cat_area`),
  CONSTRAINT `tab_doc_grupos_firmas_ibfk_1` FOREIGN KEY (`n_id_cat_area`) REFERENCES `inst_cat_areas` (`n_id_cat_area`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_doc_grupos_firmas`
--

LOCK TABLES `tab_doc_grupos_firmas` WRITE;
/*!40000 ALTER TABLE `tab_doc_grupos_firmas` DISABLE KEYS */;
/*!40000 ALTER TABLE `tab_doc_grupos_firmas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_docs_firmantes`
--

DROP TABLE IF EXISTS `tab_docs_firmantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_docs_firmantes` (
  `n_id_documento` int NOT NULL,
  `n_id_num_empleado` int NOT NULL,
  `n_id_inst_firmante` int DEFAULT NULL,
  PRIMARY KEY (`n_id_documento`,`n_id_num_empleado`),
  KEY `n_id_num_empleado` (`n_id_num_empleado`),
  KEY `n_id_inst_firmante` (`n_id_inst_firmante`),
  CONSTRAINT `tab_docs_firmantes_ibfk_1` FOREIGN KEY (`n_id_documento`) REFERENCES `tab_documentos` (`n_id_documento`),
  CONSTRAINT `tab_docs_firmantes_ibfk_2` FOREIGN KEY (`n_id_num_empleado`) REFERENCES `inst_empleado` (`n_id_num_empleado`),
  CONSTRAINT `tab_docs_firmantes_ibfk_3` FOREIGN KEY (`n_id_inst_firmante`) REFERENCES `tab_cat_inst_firmantes` (`n_id_inst_firmante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_docs_firmantes`
--

LOCK TABLES `tab_docs_firmantes` WRITE;
/*!40000 ALTER TABLE `tab_docs_firmantes` DISABLE KEYS */;
/*!40000 ALTER TABLE `tab_docs_firmantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_documento_workflow`
--

DROP TABLE IF EXISTS `tab_documento_workflow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_documento_workflow` (
  `id_documento_workflow` int NOT NULL AUTO_INCREMENT,
  `id_etapa_documento` int DEFAULT NULL,
  `id_document` int DEFAULT NULL,
  `ult_actualizacion` datetime DEFAULT NULL,
  `workflow_fecha` datetime DEFAULT NULL,
  `workflow_n_id_num_empleado` int DEFAULT NULL,
  PRIMARY KEY (`id_documento_workflow`),
  KEY `id_etapa_documento` (`id_etapa_documento`),
  KEY `id_document` (`id_document`),
  KEY `workflow_n_id_num_empleado` (`workflow_n_id_num_empleado`),
  CONSTRAINT `tab_documento_workflow_ibfk_1` FOREIGN KEY (`id_etapa_documento`) REFERENCES `tab_cat_etapa_documento` (`id_etapa_documento`),
  CONSTRAINT `tab_documento_workflow_ibfk_2` FOREIGN KEY (`id_document`) REFERENCES `tab_documentos` (`n_id_documento`),
  CONSTRAINT `tab_documento_workflow_ibfk_3` FOREIGN KEY (`workflow_n_id_num_empleado`) REFERENCES `inst_empleado` (`n_id_num_empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_documento_workflow`
--

LOCK TABLES `tab_documento_workflow` WRITE;
/*!40000 ALTER TABLE `tab_documento_workflow` DISABLE KEYS */;
/*!40000 ALTER TABLE `tab_documento_workflow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_documentos`
--

DROP TABLE IF EXISTS `tab_documentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_documentos` (
  `n_id_documento` int NOT NULL AUTO_INCREMENT,
  `chain_id_document` int DEFAULT NULL,
  `n_id_tipo_destino` int DEFAULT NULL,
  `n_id_tipo_documento` int DEFAULT NULL,
  `folio_documento` int DEFAULT NULL,
  `folio_especial` varchar(50) DEFAULT NULL,
  `creacion_documento_fecha` datetime DEFAULT NULL,
  `n_id_num_empleado_creador` int DEFAULT NULL,
  `n_id_usuario_creador` int DEFAULT NULL COMMENT 'Creador del documento.',
  `n_num_expediente` int DEFAULT NULL COMMENT 'El nombre se almacenará en la tabla tab_expedientes',
  `n_id_prioridad` int DEFAULT NULL,
  `s_asunto` varchar(255) DEFAULT NULL,
  `s_notas` varchar(1000) DEFAULT NULL,
  `s_contenido` varchar(2048) DEFAULT NULL,
  `d_fecha_limite_firma` datetime DEFAULT NULL,
  `s_hash_documento` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`n_id_documento`),
  KEY `chain_id_document` (`chain_id_document`),
  KEY `n_id_tipo_destino` (`n_id_tipo_destino`),
  KEY `n_id_tipo_documento` (`n_id_tipo_documento`),
  KEY `n_id_num_empleado_creador` (`n_id_num_empleado_creador`),
  KEY `n_id_usuario_creador` (`n_id_usuario_creador`),
  KEY `n_num_expediente` (`n_num_expediente`),
  KEY `n_id_prioridad` (`n_id_prioridad`),
  KEY `s_hash_documento` (`s_hash_documento`),
  CONSTRAINT `tab_documentos_ibfk_1` FOREIGN KEY (`chain_id_document`) REFERENCES `tab_documentos` (`n_id_documento`),
  CONSTRAINT `tab_documentos_ibfk_2` FOREIGN KEY (`n_id_tipo_destino`) REFERENCES `tab_cat_destino_documento` (`n_id_tipo_destino`),
  CONSTRAINT `tab_documentos_ibfk_3` FOREIGN KEY (`n_id_tipo_documento`) REFERENCES `tab_cat_tipo_documento` (`n_id_tipo_documento`),
  CONSTRAINT `tab_documentos_ibfk_4` FOREIGN KEY (`n_id_num_empleado_creador`) REFERENCES `inst_empleado` (`n_id_num_empleado`),
  CONSTRAINT `tab_documentos_ibfk_5` FOREIGN KEY (`n_id_usuario_creador`) REFERENCES `seg_org_usuarios` (`n_id_usuario`),
  CONSTRAINT `tab_documentos_ibfk_6` FOREIGN KEY (`n_num_expediente`) REFERENCES `tab_expedientes` (`n_num_expediente`),
  CONSTRAINT `tab_documentos_ibfk_7` FOREIGN KEY (`n_id_prioridad`) REFERENCES `tab_cat_prioridad` (`n_id_prioridad`),
  CONSTRAINT `tab_documentos_ibfk_8` FOREIGN KEY (`s_hash_documento`) REFERENCES `pki_documento` (`s_hash_documento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_documentos`
--

LOCK TABLES `tab_documentos` WRITE;
/*!40000 ALTER TABLE `tab_documentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `tab_documentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_documentos_adjuntos`
--

DROP TABLE IF EXISTS `tab_documentos_adjuntos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_documentos_adjuntos` (
  `id_documento_adjunto` int NOT NULL AUTO_INCREMENT,
  `id_document` int DEFAULT NULL,
  `documento_path` varchar(255) DEFAULT NULL,
  `documento_hash` varchar(64) DEFAULT NULL,
  `documento_filetype` varchar(20) DEFAULT NULL,
  `documento_base64` longtext,
  `fecha_carga` datetime DEFAULT NULL,
  PRIMARY KEY (`id_documento_adjunto`),
  KEY `id_document` (`id_document`),
  CONSTRAINT `tab_documentos_adjuntos_ibfk_1` FOREIGN KEY (`id_document`) REFERENCES `tab_documentos` (`n_id_documento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_documentos_adjuntos`
--

LOCK TABLES `tab_documentos_adjuntos` WRITE;
/*!40000 ALTER TABLE `tab_documentos_adjuntos` DISABLE KEYS */;
/*!40000 ALTER TABLE `tab_documentos_adjuntos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_expedientes`
--

DROP TABLE IF EXISTS `tab_expedientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_expedientes` (
  `n_num_expediente` int NOT NULL AUTO_INCREMENT,
  `s_num_expediente` varchar(100) DEFAULT NULL,
  `s_descripcion` varchar(255) DEFAULT NULL,
  `n_id_usuario_creador` int DEFAULT NULL COMMENT 'Creador del Registro.',
  PRIMARY KEY (`n_num_expediente`),
  UNIQUE KEY `s_num_expediente` (`s_num_expediente`),
  KEY `n_id_usuario_creador` (`n_id_usuario_creador`),
  CONSTRAINT `tab_expedientes_ibfk_1` FOREIGN KEY (`n_id_usuario_creador`) REFERENCES `seg_org_usuarios` (`n_id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_expedientes`
--

LOCK TABLES `tab_expedientes` WRITE;
/*!40000 ALTER TABLE `tab_expedientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `tab_expedientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_notificaciones`
--

DROP TABLE IF EXISTS `tab_notificaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_notificaciones` (
  `n_id_notificacion` int NOT NULL AUTO_INCREMENT,
  `id_document` int DEFAULT NULL,
  `documento_path` varchar(255) DEFAULT NULL,
  `message` varchar(30) DEFAULT NULL,
  `n_id_tipo_notif` int DEFAULT NULL,
  PRIMARY KEY (`n_id_notificacion`),
  KEY `id_document` (`id_document`),
  KEY `n_id_tipo_notif` (`n_id_tipo_notif`),
  CONSTRAINT `tab_notificaciones_ibfk_1` FOREIGN KEY (`id_document`) REFERENCES `tab_documentos` (`n_id_documento`),
  CONSTRAINT `tab_notificaciones_ibfk_2` FOREIGN KEY (`n_id_tipo_notif`) REFERENCES `tab_cat_tipo_notificacion` (`n_id_tipo_notif`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_notificaciones`
--

LOCK TABLES `tab_notificaciones` WRITE;
/*!40000 ALTER TABLE `tab_notificaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `tab_notificaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vista_tablero`
--

DROP TABLE IF EXISTS `vista_tablero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vista_tablero` (
  `n_id_documento` int NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `s_asunto` varchar(255) DEFAULT NULL,
  `creacion_documento_fecha` datetime(6) DEFAULT NULL,
  `s_desc_etapa` varchar(255) DEFAULT NULL,
  `folio_documento` varchar(255) DEFAULT NULL,
  `n_id_inst` int DEFAULT NULL,
  `num_empleado` int DEFAULT NULL,
  `prioridad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`n_id_documento`,`tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vista_tablero`
--

LOCK TABLES `vista_tablero` WRITE;
/*!40000 ALTER TABLE `vista_tablero` DISABLE KEYS */;
/*!40000 ALTER TABLE `vista_tablero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'db_mappings'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-06 21:01:09
