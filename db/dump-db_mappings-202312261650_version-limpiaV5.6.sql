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
INSERT INTO `inst_empleado` VALUES (93,'Patricia Catalina','González','Ayala',2,'patycaty12@gmail.com','patricia.gonzalez@tecdmx.org.mx',NULL,'1201','GOAP720822MDFNYT05','GOAP720822','93.jpg',NULL),(149,'Roberto','Sánchez','Pérez',1,'rosanper68@hotmail.com','roberto.sanchez@tecdmx.org.mx',NULL,'1120','SAPR681202HDFNRB05','SAPR681202','149.jpg',NULL),(157,'María Dolores','Del Valle','Espinosa',2,'delvalle.lola@yahoo.com.mx','maria.delvalle@tecdmx.org.mx',NULL,'1021','VAED600811MDFLSL03','VAED600811','157.jpg',NULL),(253,'Francisco Antonio','Hernández','González',1,'fcoant49@hotmail.com','francisco.hernandez@tecdmx.org.mx',NULL,'1107','HEGF491010HDFRNR07','HEGF491010','253.jpg',NULL),(306,'Dora Luz','Serrano','Hernández',2,'tikisluces@hotmail.com','dora.serrano@tecdmx.org.mx',NULL,'2103','SEHD751202MDFRRR05','SEHD751202','306.jpg',NULL),(359,'Osiris','Vázquez','Rangel',1,'dr.osiris.vazquez@gmail.com','osiris.vazquez@tecdmx.org.mx',NULL,'1213','VARO720610HTSZNS02','VARO720610','359.jpg',NULL),(399,'Claudia','Ochoa','Macías',2,'claudiaochoamacias@yahoo.com.mx','claudia.ochoa@tecdmx.org.mx',NULL,'1496','OOMC760516MDFCCL00','OOMC760516','399.jpg',NULL),(427,'María del Pilar','Meza','Robert',2,'pilarmezarobert@hotmail.com','maria.meza@tecdmx.org.mx',NULL,'1306','MERP760109MDFZBL06','MERP760109','427.jpg',NULL),(504,'María Estela','Martínez','Lezama',2,'telamarle@hotmail.com','estela.martinez@tecdmx.org.mx',NULL,'1403','MALE720329MDFRZS02','MALE720329','504.jpg',NULL),(545,'Tomás Juan','Godínez','Torres',1,NULL,'tomas.godinez@tecdmx.org.mx',NULL,'1105','GOTT560307HDFDRM03','GOTT560307','545.jpg',NULL),(611,'María Dolores','Hurtado','Vargas',2,'golondrinahurtado@gmail.com','maria.hurtado@tecdmx.org.mx',NULL,'1250','HUVD710508MMNRRL09','HUVD710508','611.jpg',NULL),(703,'Gabriel','Reyes','Flores',1,'optrareyer@live.com','gabriel.reyes@tecdmx.org.mx',NULL,'1054','REFG740704HMCYLB01','REFG740704','703.jpg',NULL),(712,'Lilián','Herrera','Guzmán',2,'lilibu_27@hotmail.com','lilian.herrera@tecdmx.org.mx',NULL,'1418','HEGL851127MDFRZL08','HEGL851127','712.jpg',NULL),(721,'Blanca Estela','Zamora','Jiménez',2,'beza.j-3@hotmail.com','blanca.zamora@tecdmx.org.mx',NULL,'1350','ZAJB650124MDFMML09','ZAJB650124','721.jpg',NULL),(735,'Julio César','Botello','Torres',1,'juliobot@hotmail.com','julio.botello@tecdmx.org.mx',NULL,'1348','BOTJ770209HDFTRL08','BOTJ770209','735.jpg',NULL),(739,'Luis','Sánchez','Baltazar',1,'luisbal666@gmail.com','luis.sanchez@tecdmx.org.mx',NULL,'1224','SABL660402HMNNLS09','SABL660402','739.jpg',NULL),(764,'Verónica Yolanda','González','Pérez',2,'gpv85@hotmail.com','veronica.gonzalez@tecdmx.org.mx',NULL,'1340','GOPV741001MDFNRR02','GOPV741001','764.jpg',NULL),(784,'Alfredo','Soto','Rodríguez',1,'alfredo.soto78@gmail.com','alfredo.soto@tecdmx.org.mx',NULL,'1404','SORA780108HDFTDL08','SORA780108','784.jpg',NULL),(832,'Judith','Espinoza','Cruz',2,'jdthecz@gmail.com','judith.espinoza@tecdmx.org.mx',NULL,NULL,'EICJ880906MDFSRD03','EICJ880906',NULL,NULL),(850,'Javier','Zuñiga','Álvarez',1,'javier1018@yahoo.com.mx','javier.zuniga@tecdmx.org.mx',NULL,'1020','ZUAJ680127HDFXLV01','ZUAJ680127','850.jpg',NULL),(852,'Víctor Adrián','Rodríguez','Castillo',1,'licvitor@hotmail.com','victor.rodriguez@tecdmx.org.mx',NULL,'1412','ROCV810911HDFDSC02','ROCV810911','852.jpg',NULL),(880,'Luis Antonio','Hong','Romero',1,'hongromero@live.com.mx','luis.hong@tecdmx.org.mx',NULL,NULL,'HORL790708HDFNMS05','HORL790708','880.jpg',NULL),(888,'Samuel','Maldonado','San Germán',1,'samuelmaldonado84@gmail.com','samuel.maldonado@tecdmx.org.mx',NULL,NULL,'MASS750703HDFLNM08','MASS750703','888.jpg',NULL),(906,'Vicente','Bonilla','Hernández',1,'bonillahv@gmail.com','vicente.bonilla@tecdmx.org.mx',NULL,'1182','BOHV790907HMCNRC03','BOHV790907','906.jpg',NULL),(923,'Martha Virginia','Grez','Ramírez',2,'grezmv@hotmail.com','martha.grez@tecdmx.org.mx',NULL,NULL,'GERM821130MDFRMR06','GERM821130','923.jpg',NULL),(927,'Luis Gilberto','Álvarez','Rodríguez',1,'lg_arq@hotmail.com','luis.alvarez@tecdmx.org.mx',NULL,'1240','AARL770227HDFLDS02','AARL770227','927.jpg',NULL),(937,'Martha Leticia','Mercado','Ramírez',2,'marmerram@hotmail.com','martha.mercado@tecdmx.org.mx',NULL,'1210','MERM750820MDFRMR08','MERM750820','937.jpg',NULL),(961,'Francisco Javier','Barrera','Peralta',1,'jabar7@hotmail.com','francisco.barrera@tecdmx.org.mx',NULL,'1284','BAPF601222HDFRRR02','BAPF601222','961.jpg',NULL),(971,'Carlos Esteban','Mejorada','Jiménez',1,'lic.carlosmejorada@hotmail.com','carlos.mejorada@tecdmx.org.mx',NULL,'1499','MEJC850406HDFJMR03','MEJC850406','971.jpg',NULL),(973,'Juan Carlos','Sánchez','León',1,'jcsanle@yahoo.com.mx','juan.sanchez@tecdmx.org.mx',NULL,'1403','SALJ750224HDFNNN03','SALJ750224','973.jpg',NULL),(974,'María Antonieta','González','Mares',2,'magmares@yahoo.com.mx','maria.gonzalez@tecdmx.org.mx',NULL,'1219','GOMA780823MDFNRN05','GOMA780823','974.jpg',NULL),(977,'Raúl','Maldonado','Soto',1,'maldonado.vero@hotmail.com','raul.maldonado@tecdmx.org.mx',NULL,'1317','MASR470407HDFLTL01','MASR470407','977.jpg',NULL),(1008,'Esteban','Lara','Espinosa',1,'laraesteban451@yahoo.com.mx','esteban.lara@tecdmx.org.mx',NULL,'1051','LAEE741127HDFRSS02','LAEE741127','1008.jpg',NULL),(1012,'Noemí','Cruz','Peña',2,NULL,'noemi.cruz@tecdmx.org.mx',NULL,'1218','CUPN691228MDFRXM00','CUPN691228','1012.jpg',NULL),(1016,'María de Lourdes','Alatorre','Meléndez',2,'lulu.alatorre@hotmail.com','maria.alatorre@tecdmx.org.mx',NULL,'1235','AAML741004MMSLLR08','AAML741004','1016.jpg',NULL),(1020,'Talía Guadalupe','Pérez','Hernández',2,'taliats04@gmail.com','talia.perez@tecdmx.org.mx',NULL,'1152','PEHT880304MDFRRL05','PEHT880304','1020.jpg',NULL),(1023,'Juan Carlos','Hernández','Cárdenas',1,'joancarlote@gmail.com','juan.hernandez@tecdmx.org.mx',NULL,'1409','HECJ810313HVZRRN07','HECJ810313','1023.jpg',NULL),(1024,'Kimberly Yamel','Martiñón','Bonilla',2,'kymarbo@gmail.com','kimberly.martinon@tecdmx.org.mx',NULL,'1342','MABK880902MDFRNM00','MABK880902','1024.jpg',NULL),(1026,'Haydeé María','Cruz','González',2,NULL,'haydee.cruz@tecdmx.org.mx',NULL,'1422','CUGH850919MCMRNY26','CUGH850919','1026.jpg',NULL),(1038,'Alfonso','Martínez','López',1,'amarlo_70@hotmail.com','alfonso.martinez@tecdmx.org.mx',NULL,NULL,'MALA701204HDFRPL06','MALA701204','1038.jpg',NULL),(1039,'Agar Leslie','Serrano','Álvarez',2,'agar.lesa@gmail.com','agar.serrano@tecdmx.org.mx',NULL,'1209','SEAA821003MDFRLG04','SEAA821003','1039.jpg',NULL),(1054,'Miguel Ángel','Santos','Morales',1,'angelmike132@gmail.com','miguel.santos@tecdmx.org.mx',NULL,'1100','SAMM790206HDFNRG03','SAMM790206','1054.jpg',NULL),(1055,'Sandra Araceli','Vivanco','Morales',2,'licsandra.vivanco@hotmail.com','sandra.vivanco@tecdmx.org.mx',NULL,'1805','VIMS760607MMNVRN07','VIMS760607','1055.jpg',NULL),(1058,'José Luis','Blancas','Rojas',1,'jlbrvip@hotmail.com','jose.blancas@tecdmx.org.mx',NULL,'1050','BARL861029HDFLJS01','BARL861029','1058.jpg',NULL),(1059,'Andrés Alfredo','Díaz','Gómez',1,'alfredyx@hotmail.com','andres.diaz@tecdmx.org.mx',NULL,'1315','DIGA820526HDFZMN03','DIGA820526','1059.jpg',NULL),(1073,'Diego','Montiel','Urbán',1,'diego.montiel87@gmail.com','diego.montiel@tecdmx.org.mx',NULL,'1344','MOUD870901HDFNRG05','MOUD870901','1073.jpg',NULL),(1082,'Juan Antonio','Delgado','González',1,'antonio.delgonz@gmail.com','juan.delgado@tecdmx.org.mx',NULL,'1331','DEGJ830529HDFLNN07','DEGJ830529','1082.jpg',NULL),(1083,'José de Jesús','Ruíz','Gallegos',1,'joseruizgallegos@outlook.com','jesus.ruiz@tecdmx.org.mx',NULL,'1285','RUGJ841010HDFZLS04','RUGJ841010','1083.jpg',NULL),(1084,'Teresita Haydeé','Ruíz','Alvarado',2,'tere_hayde@hotmail.com','teresita.ruiz@tecdmx.org.mx',NULL,'1221','RUAT771206MDFZLR07','RUAT771206','1084.jpg',NULL),(1101,'Marco Antonio','Guerra','Castillo',1,'marcoguerrac@outlook.com','marco.guerra@tecdmx.org.mx',NULL,'1303','GUCM831129HDFRSR01','GUCM831129','1101.jpg',NULL),(1111,'María Dolores','Corona','López',2,'corona_dol@hotmail.com','maria.corona@tecdmx.org.mx',NULL,'1318','COLD670714MDFRPL06','COLD670714','1111.jpg',NULL),(1114,'Rubén','Aguilar','Ramírez',1,'rubenaguilar10@hotmail.com','ruben.aguilar@tecdmx.org.mx',NULL,'1280','AURR591226HDFGMB02','AURR591226','1114.jpg',NULL),(1116,'Anabell','Arellano','Mendoza',2,'mujeresdecidiendo33@gmail.com','anabell.arellano@tecdmx.org.mx',NULL,'1174','AEMA750613MDFRNN06','AEMA750613','1116.jpg',NULL),(1137,'Yadira','Hernández','Islas',2,'yadirahdz1@hotmail.com','yadira.hernandez@tecdmx.org.mx',NULL,'1225','HEIY890815MMCRSD04','HEIY890815','1137.jpg',NULL),(1139,'Orquídea Mayalli','González','Torres',2,'orquidea.aj@gmail.com','orquidea.gonzalez@tecdmx.org.mx',NULL,'1281','GOTO890707MDFNRR05','GOTO890707','1139.jpg',NULL),(1146,'Miguel Ángel','Medina','Monroy',1,'lc.mamedina@gmail.com','miguel.medina@tecdmx.org.mx',NULL,'1046','MEMM860604HDFDNG00','MEMM860604','1146.jpg',NULL),(1159,'Juan Antonio','Mejía','Ortíz',1,'alephantonio@gmail.com','juan.mejia@tecdmx.org.mx',NULL,'1028','MEOJ790730HDFJRN06','MEOJ790730','1159.jpg',NULL),(1168,'Vania Ivonne','González','Contreras',2,'vaniay50@hotmail.com','vania.gonzalez@tecdmx.org.mx',NULL,NULL,'GOCV740321MDFNNN00','GOCV740321','1168.jpg',NULL),(1170,'Sabina Reyna','Fregoso','Reyes',2,'sabinafregoso@gmail.com','sabina.fregoso@tecdmx.org.mx',NULL,'1130','FERS780829MDFRYB08','FERS780829','1170.jpg',NULL),(1171,'Nancy','García','Huante',2,'nancyg28@hotmail.com','nancy.garcia@tecdmx.org.mx',NULL,'1230','GAHN820928MDFRNN08','GAHN820928','1171.jpg',NULL),(1172,'Otilio Esteban','Hernández','Pérez',1,'otiliohp@gmail.com','otilio.hernandez@tecdmx.org.mx',NULL,'1198','HEPO650328HHGRRT08','HEPO650328','1172.jpg',2),(1175,'Gabriela','Martínez','Miranda',2,'gabyluu26@hotmail.com','gabriela.martinez@tecdmx.org.mx',NULL,'1324','MAMG771226MASRRB00','MAMG771226','1175.jpg',NULL),(1176,'Nayeli Margarita','Rangel','Rivera',2,'yoli2687@hotmail.com','nayeli.rangel@tecdmx.org.mx',NULL,'1432','RARN870826MDFNVY03','RARN870826','1176.jpg',NULL),(1182,'María Argentina','Vázquez','Picazo',2,'keroppi_a19@hotmail.com','argentina.vazquez@tecdmx.org.mx',NULL,'1490','VAPA810319MMNZCR09','VAPA810319','1182.jpg',NULL),(1188,'María Regina','Ávila','Rodríguez',2,'avilare2003@hotmail.com','regina.avila@tecdmx.org.mx',NULL,'1206','AIRR570907MDFVDG06','AIRR570907','1188.jpg',NULL),(1196,'Karla Carina','Chaparro','Blancas',2,'karla.c.chaparro.b@gmail.com','karla.chaparro@tecdmx.org.mx',NULL,'1363','CABK890916MDFHLR06','CABK890916','1196.jpg',NULL),(1197,'Alejandra','Méndez','Soto',2,'art_ams_law@hotmail.com','alejandra.mendez@tecdmx.org.mx',NULL,'1236','MESA920905MDFNTL01','MESA920905','1197.jpg',NULL),(1204,'Jorge Humberto','Espinosa','Cortés',1,'espinosacortesjorge@hotmail.com','jorge.espinosa@tecdmx.org.mx',NULL,NULL,'EICJ800917HDFSRR03','EICJ800917','1204.jpg',NULL),(1235,'Mónica','León','Cruz',2,'motivleon01@gmail.com','monica.leon@tecdmx.org.mx',NULL,'1220','LECM801128MDFNRN02','LECM801128','1235.jpg',NULL),(1239,'José Antonio','Tapia','Bernal',1,'unam1mexico@gmail.com.mx','jose.tapia@tecdmx.org.mx',NULL,'1346','TABA720516HDFPRN03','TABA720516','1239.jpg',NULL),(1242,'Ana Cecilia Luisa','Hernández','Quezadas',2,'anaceci1802@gmail.com','ana.hernandez@tecdmx.org.mx',NULL,NULL,'HEQA750218MDFRZN15','HEQA750218','1242.jpg',NULL),(1247,'Diana','Morales','González',2,'dimogo_25@hotmail.com','diana.morales@tecdmx.org.mx',NULL,'1002','MOGD860413MDFRNN09','MOGD860413','1247.jpg',NULL),(1248,'Selene Lizbeth','González','Medina',2,NULL,'selene.gonzalez@tecdmx.org.mx',NULL,NULL,'GOMS890313MMNNDL03','GOMS890313','1248.jpg',NULL),(1252,'Berenize Victoria','González','Pérez',2,'ix.gonzalezvictoria@gmail.com','berenize.gonzalez@tecdmx.org.mx',NULL,NULL,'GOPB980109MDFNRR14','GOPB980109',NULL,NULL),(1254,'Erik Jesús','Moreno','Vergara',1,'erikmoreno57@hotmail.com','erik.moreno@tecdmx.org.mx',NULL,'1227','MOVE830122HDFRRR04','MOVE830122','1254.jpg',NULL),(1263,'Karla Berenice','Domínguez','Aranda',2,'karla.dom.aranda@Outlook.com','karla.dominguez@tecdmx.org.mx',NULL,'1337','DOAK941119MDFMRR06','DOAK941119','1263.jpg',NULL),(1268,'Carlos Antonio','Neri','Carrillo',1,'cancneri@hotmail.commaestria','carlos.neri@tecdmx.org.mx',NULL,'1321','NECC771121HDFRRR02','NECC771121','1268.jpg',NULL),(1270,'David Ernesto','Salazar','Cobián',1,'de_salazar@hotmail.com','david.salazar@tecdmx.org.mx',NULL,'1057','SACD650727HDFLBV00','SACD650727','1270.jpg',NULL),(1279,'Rodrigo Edmundo','Galán','Martínez',1,'rodrigo.galanmtz1@gmail.com','rodrigo.galan@tecdmx.org.mx',NULL,'1322','GAMR831119HVZLRD03','GAMR831119','1279.jpg',NULL),(1282,'Carlos Emiliano','Ávila de la Paz','Pérez',1,'carlosemiliano@yahoo.com','carlos.avila@tecdmx.org.mx',NULL,'1282','AIPC780407HDFVZR04','AIPC780407','1282.jpg',NULL),(1284,'Itzelt Naghely','Lugo','Vargas',2,'itzelt.lugo@hotmail.com.mx','itzelt.lugo@tecdmx.org.mx',NULL,'1156','LUVI800713MDFGRT08','LUVI800713','1284.jpg',NULL),(1289,'Evelin Maricela','Navarrete','Ramos',2,'evelinnavarrete@outlook.com','evelyn.navarrete@tecdmx.org.mx',NULL,'1168','NARE860831MDFVMV00','NARE860831','1289.jpg',NULL),(1290,'Aida Araceli','Romero','Espinosa',2,'aaromero8@gmail.com','aida.romero@tecdmx.org.mx',NULL,'1255','ROEA650719MDFMSD08','ROEA650719','1290.jpg',NULL),(1292,'Elizabeth del Carmen','Jiménez','Santiago',2,'ely.jisa@gmail.com','elizabeth.jimenez@tecdmx.org.mx',NULL,'1701','JISE720124MVZMNL06','JISE720124','1292.jpg',NULL),(1308,'Armando Azael','Alvarado','Castillo',1,'zullydine_@hotmail.com','armando.alvarado@tecdmx.org.mx',NULL,'1802','AACA820721HDFLSR02','AACA820721','1308.jpg',NULL),(1311,'Nadia Gabriela','García','Morales',2,'nadia_gabriela_morales@hotmail.com','nadia.garcia@tecdmx.org.mx',NULL,'1223','GAMN770122MMCRRD04','GAMN770122','1311.jpg',NULL),(1317,'Fanny Lizeth','Enríquez','Pineda',2,'iusenriquez@hotmail.com','fanny.lizeth@tecdmx.org.mx',NULL,'1311','EIPF920901MDFNNN08','EIPF920901','1317.jpg',NULL),(1323,'Miguel Federico','0','Gutiérrez',1,'mfgutz@outlook.com','miguel.gutierrez@tecdmx.org.mx',NULL,'1260','GUXM610314HDFTXG04','GUXM610314','1323.jpg',NULL),(1335,'Marco Tulio','Miranda','Hernández',1,'matmihdz@msn.com','marco.miranda@tecdmx.org.mx',NULL,'1352','MIHM730126HMSRRR12','MIHM730126','1335.jpg',NULL),(1336,'Adriana','Adam','Peragallo',2,'adrish17@hotmail.com','adriana.adam@tecdmx.org.mx',NULL,'1127','AAPA840918MDFDRD06','AAPA840918','1336.jpg',NULL),(1340,'Edgar','García','Juárez',1,'frontondestructor@hotmail.com','edgar.garcia@tecdmx.org.mx',NULL,NULL,'GAJE780424HMCRRD03','GAJE780424','1340.jpg',NULL),(1343,'Bertha Ivonne','Burgoa','Tirado',2,'ivonneburgoa@gmail.com','bertha.burgoa@tecdmx.org.mx',NULL,'1103','BUTB740909MDFRRR07','BUTB740909','1343.jpg',NULL),(1346,'Perla Violeta','Alfaro','Soriano',2,'pvas250378@gmail.com','perla.alfaro@tecdmx.org.mx',NULL,'1497','AASP780325MDFLRR08','AASP780325','1346.jpg',NULL),(1357,'José Gabriel','Guzmán','Flores',1,'gabrielguzmanflores@gmail.com','jose.guzman@tecdmx.org.mx',NULL,'1143','GUFG870301HDFZLB06','GUFG870301','1357.jpg',NULL),(1366,'Ramiro Dionisio','Sánchez','Valera',1,'rsanchez1409@hotmail.com','ramiro.sanchez@tecdmx.org.mx',NULL,'1026','SAVR550311HPLNLM00','SAVR550311','1366.jpg',NULL),(1372,'José Miguel','Juárez','Castillo',1,'jmigueljuarezcastillo@gmail.com','miguel.juarez@tecdmx.org.mx',NULL,'1287','JUCM821111HDFRSG08','JUCM821111','1372.jpg',NULL),(1375,'Luisa Fernanda','Monterde','García',2,'luisa.femoga@gmail.com','luisa.monterde@tecdmx.org.mx',NULL,'1325','MOGL950221MJCNRS01','MOGL950221','1375.jpg',NULL),(1377,'Andrea','De la O','Flores',2,'delaoflores_andrea@hotmail.com','andrea.delao@tecdmx.org.mx',NULL,'1351','OXFA951023MDFXLN02','OXFA951023','1377.jpg',NULL),(1380,'Orlando','Anaya','González',1,'orlandoanaya@gmail.com','orlando.anaya@tecdmx.org.mx',NULL,'1109','AAGO780202HDFNNR09','AAGO780202','1380.jpg',NULL),(1382,'Erika','Arredondo','Martínez',2,'erik_cruise@yahoo.com.mx','erika.arredondo@tecdmx.org.mx',NULL,'1804','AEME840818MDFRRR03','AEME840818','1382.jpg',NULL),(1385,'Carmen','García','Rodríguez',2,'carmengrdz@hotmail.com','carmen.garcia@tecdmx.org.mx',NULL,NULL,'GARC770501MDFRDR08','GARC770501','1385.jpg',NULL),(1388,'Valleri','Acosta','Bocanegra',2,'valleri_acost@hotmail.com','valleri.acosta@tecdmx.org.mx',NULL,'1362','AOBV811111MDFCCL04','AOBV811111','1388.jpg',NULL),(1389,'Silvia Guadalupe','Solís','Altamirano',2,'silviasolis86@gmail.com','silvia.solis@tecdmx.org.mx',NULL,'1345','SOAS860607MDFLLL00','SOAS860607','1389.jpg',NULL),(1390,'Faustino','Sámano','Flores',1,'faustino_samano@hotmail.com','faustino.samano@tecdmx.org.mx',NULL,'1431','SAFF820827HMCMLS01','SAFF820827','1390.jpg',NULL),(1391,'Ana Laura','Veloz','Sandoval',2,'analauveloz@gmail.com','ana.veloz@tecdmx.org.mx',NULL,NULL,'VESA820802MDFLNN05','VESA820802','1391.jpg',NULL),(1393,'Arturo Ángel','Cortés','Santos',1,'arturoacs.as@gmail.com','arturo.cortes@tecdmx.org.mx',NULL,'1494','COSA870409HDFRNR04','COSA870409','1393.jpg',NULL),(1394,'Miguel Ángel','Quiroz','Velázquez',1,'miguelangelquirozvelazquez@gmail.com','miguel.quiroz@tecdmx.org.mx',NULL,'1423','QUVM800710HDFRLG07','QUVM800710','1394.jpg',NULL),(1399,'Jocelyn','Pérez','Reyes',2,'yoss.perez.reyes@gmail.com','jocelyn.perez@tecdmx.org.mx',NULL,'1123','PERJ870131MDFRYC05','PERJ870131','1399.jpg',NULL),(1402,'Gabriela','Cervantes','Ruíz',2,'gabrielacervantesruiz@gmail.com','gabriela.cervantes@tecdmx.org.mx',NULL,'1165','CERG920621MDFRZB07','CERG920621','1402.jpg',NULL),(1406,'Jorge Luis','Vázquez','Sanluis',1,'jorgeluis34519@gmail.com','jorge.vazquez@tecdmx.org.mx',NULL,NULL,'VASJ860628HTLZNR09','VASJ860628','1406.jpg',NULL),(1407,'Norma Angélica','Guevara','García',2,'angelicaguevara96@yahoo.com.mx','norma.guevara@tecdmx.org.mx',NULL,'1261','GUGN751222MDFVRR06','GUGN751222','1407.jpg',NULL),(1408,'Karla Olivia','Flores','Cortés',2,'karlaflcr@gmail.com','karla.flores@tecdmx.org.mx',NULL,'1203','FOCK770919MTLLRR01','FOCK770919','1408.jpg',NULL),(1416,'Andrea Cristina','Lehn','Angelides',2,'andrealehn@yahoo.com.mx','andrea.lehn@tecdmx.org.mx',NULL,'1145','LEAA721211MNEHNN00','LEAA721211','1416.jpg',NULL),(1429,'Eber Dario','Comonfort','Palacios',1,'eber.comonfort@hotmail.com','eber.comonfort@tecdmx.org.mx',NULL,'1997','COPE871007HDFMLB01','COPE871007','1429.jpg',NULL),(1436,'Norma Elena','Flores','García',2,'norma_flores@live.com.mx','norma.floresg@tecdmx.org.mx',NULL,'1481','FOGN760510MDFLRR05','FOGN760510','1436.jpg',NULL),(1446,'Yesenia','Bravo','Salvador',2,'yesenia.bs@yahoo.com','yesenia.bravo@tecdmx.org.mx',NULL,'1500','BASY930409MOCRLS09','BASY930409','1446.jpg',NULL),(1447,'Diego Antonio','Maldonado','Martínez',1,'dieg_mm25@hotmail.com','diego.maldonado@tecdmx.org.mx',NULL,NULL,'MAMD810807HGTLRG08','MAMD810807','1447.jpg',NULL),(1451,'Hugo César','Romero','Reyes',1,'huceromero@gmail.com','hugo.romero@tecdmx.org.mx',NULL,'2106','RORH901114HDFMYG08','RORH901114',NULL,NULL),(1455,'Diana Verónica','Franco','Salinas',2,'dianaveronicafranco@gmail.com','diana.franco@tecdmx.org.mx',NULL,NULL,'FASD880425MDFRLN06','FASD880425','1455.jpg',NULL),(1458,'Juan Adrián','González','Bruzzone de la Cruz',1,'agb_96@hotmail.com','juan.gonzalez@tecdmx.org.mx',NULL,NULL,'GOCJ820616HDFNRN09','GOCJ820616','1458.jpg',NULL),(1460,'Juan Pablo','Osorio','Sánchez',1,'juan.pablo.osorio@outlook.com','juan.osorio@tecdmx.org.mx',NULL,'1462','OOSJ950127HDFSNN08','OOSJ950127','1460.jpg',NULL),(1462,'María de los Ángeles','Olivares','Hernández',2,NULL,'angeles.olivares@tecdmx.org.mx',NULL,NULL,'OIHA900414MDFLRN03','OIHA900414','1462.jpg',NULL),(1465,'Elsa','Zamarripa','Hernández',2,'elsa.zamarripa@gmail.com','elsa.zamarripa@tecdmx.org.mx',NULL,'1707','ZAHE870916MDFMRL02','ZAHE870916','1465.jpg',NULL),(1466,'Paola Selene','Padilla','Mancilla',2,'p.selenepadilla@gmail.com','paola.padilla@tecdmx.org.mx',NULL,'1274','PAMP880309MJCDNL09','PAMP880309',NULL,NULL),(1469,'Armando','Ambriz','Hernández',1,'armando_ambriz@yahoo.com','armando.ambriz@tecdmx.org.mx',NULL,'1301','AIHA740522HZSMRR01','AIHA740522','1469.jpg',NULL),(1470,'Diego','Ochoa','Ochoa',1,'lic.diego.ochoa23@gmail.com','diego.ochoa@tecdmx.org.mx',NULL,NULL,'OOOD920223HMNCCG05','OOOD920223','1470.jpg',NULL),(1471,'Maricruz','Gutiérrez','Hernández',2,'sacmis.atenea@gmail.com','maricruz.gutierrez@tecdmx.org.mx',NULL,'1320','GUHM760503MDFTRR02','GUHM760503','1471.jpg',NULL),(1475,'David','Jiménez','Hernández',1,'davo15j@hotmail.com','david.jimenez@tecdmx.org.mx',NULL,'1451','JIHD860105HDFMRV04','JIHD860105','1475.jpg',NULL),(1477,'Lucía','Hernández','Chamorro',2,NULL,'lucia.hernandez@tecdmx.org.mx',NULL,'1332','HECL821126MMCRHC08','HECL821126','1477.jpg',NULL),(1478,'Hugo Abelardo','Herrera','Pimentel',1,'snap_fres95@hotmail.com','hugo.herrerap@tecdmx.org.mx',NULL,NULL,'HEPH930102HDFRMG08','HEPH930102','1478.jpg',NULL),(1480,'Luis Martín','Flores','Mejía',1,'luis71mar@live.com.mx','luis.flores@tecdmx.org.mx',NULL,'1313','FOML711216HMCLJS09','FOML711216','1480.jpg',NULL),(1481,'Azucena Margarita','Flores','Navarro',2,'amfn1002@gmail.com','azucena.flores@tecdmx.org.mx',NULL,'1302','FONA900409MDFLVZ08','FONA900409','1481.jpg',NULL),(1484,'Dulce María','Landecho','Estiubarte',2,'candy_merie@hotmail.com','dulce.landecho@tecdmx.org.mx',NULL,NULL,'LAED830521MDFNSL07','LAED830521','1484.jpg',NULL),(1485,'Carmen Luz','Fernández','Domínguez',2,'fernandezcl2002@yahoo.com','carmen.fernandez@tecdmx.org.mx',NULL,NULL,'FEDC610222MDFRMR08','FEDC610222','1485.jpg',NULL),(1486,'Bryan','Sánchez','Cruz',1,'bryan_cb12@hotmail.com','bryan.sanchez@tecdmx.org.mx',NULL,'1415','SACB950321HGRNRR01','SACB950321','1486.jpg',NULL),(1495,'Nataly Vanessa','Guerrero','Galicia',2,'nefta.gal@gmail.com','nataly.guerrero@tecdmx.org.mx',NULL,'1319','GUGN960919MDFRLT09','GUGN960919','1495.jpg',NULL),(1504,'Frida Marcia','Horta','Suárez',2,'frimasua@yahoo.com','frida.horta@tecdmx.org.mx',NULL,'1498','HOSF781211MDFRRR02','HOSF781211','1504.jpg',NULL),(1505,'América Berenice','Jiménez','Andrade',2,'edybe2000@gmail.com','america.jimenez@tecdmx.org.mx',NULL,'1214','JIAA791005MDFMNM05','JIAA791005','1505.jpg',NULL),(1506,'Paola Virginia','Simental','Franco',2,'paolavsf@gmail.com','virginia.simental@tecdmx.org.mx',NULL,'2102','SIFP800313MDFMRL08','SIFP800313','1506.jpg',NULL),(1507,'Juan Fidel','González','Guzmán',1,'toros_de_basan_@hotmail.com','fidel.gonzalez@tecdmx.org.mx',NULL,NULL,'GOGJ820929HGTNZN02','GOGJ820929','1507.jpg',NULL),(1509,'Néstor Rafael','Gómez','Ángeles',1,'nestor_ap1@hotmail.com','nestor.gomez@tecdmx.org.mx',NULL,NULL,'GOAN870818HMCMNS08','GOAN870818','1509.jpg',NULL),(1510,'Paolo César','Ávila','Coeto',1,'p.avila@hotmail.es','paolo.avila@tecdmx.org.mx',NULL,NULL,'AICP950916HDFVTL08','AICP950916','1510.jpg',NULL),(1511,'Mariana Lucía','Pinto','Landeros',2,'malupila.20@gmail.com','mariana.pinto@tecdmx.org.mx',NULL,NULL,'PILM961120MCHNNR00','PILM961120','1511.jpg',NULL),(1513,'Elizabeth','Valderrama','López',2,'elizabethvalderrama27@gmail.com','elizabeth.valderrama@tecdmx.org.mx',NULL,'1405','VALE761027MDFLPL08','VALE761027','1513.jpg',NULL),(1515,'Marco Antonio','Ambriz','Carreón',1,'marcoambrizc@hotmail.com','marco.ambriz@tecdmx.org.mx',NULL,'1158','AICM720413HMCMRR09','AICM720413','1515.jpg',NULL),(1518,'Alfredo','Bravo','Serrano',1,'alfredo130482@gmail.com','alfredo.bravo@tecdmx.org.mx',NULL,'1053','BASA820413HDFRRL07','BASA820413','1518.jpg',NULL),(1521,'María Elena','Franco','Salinas',2,'coordramajuridica@gmail.com','elena.franco@tecdmx.org.mx',NULL,NULL,'FASE761001MDFRLL05','FASE761001','1521.jpg',NULL),(1523,'Talina','Castillo','Solano',2,'castsol@yahoo.com','talina.castillo@tecdmx.org.mx',NULL,'1427','CAST790504MDFSLL05','CAST790504','1523.jpg',NULL),(1525,'Elvira Susana','Guevara','Orteaga',2,'susana_guevara@hotmail.com','susana.guevara@tecdmx.org.mx',NULL,'1117','GUOE760913MMCVRL14','GUOE760913','1525.jpg',NULL),(1526,'Ricardo','Zozaya','González',1,'zozaya3@gmail.com','ricardo.zozaya@tecdmx.org.mx',NULL,'1001','ZOGR880529HDFZNC04','ZOGR880529','1526.jpg',NULL),(1527,'Alexia Yoselin','Bernal','Vargas',2,'alexia_ty@hotmail.com','alexia.bernal@tecdmx.org.mx',NULL,NULL,'BEVA020313MDFRRLA4','BEVA020313',NULL,NULL),(1529,'Paulina','Sánchez','Ramírez',2,'pau.sanchez08@hotmail.com','paulina.sanchez@tecdmx.org.mx',NULL,NULL,'SARP920408MDFNML09','SARP920408','1529.jpg',NULL),(1530,'Álvaro','Melchor','Martínez',1,'almelchor@hotmail.com','alvaro.melchor@tecdmx.org.mx',NULL,NULL,'MEMA690104HDFLRL09','MEMA690104','1530.jpg',NULL),(1531,'Juan Martín','Vázquez','Gualito',1,NULL,'martin.vazquez@tecdmx.org.mx',NULL,'1430','VAGJ880104HQTZLN02','VAGJ880104','1531.jpg',NULL),(1533,'Aldrin','León','Zenteno',1,'lezeavirgo@hotmail.com','aldrin.leon@tecdmx.org.mx',NULL,NULL,'LEZA890909HTLNNL07','LEZA890909','1533.jpg',NULL),(1534,'Edgar','Malagón','Martínez',1,'edgar.malagonn@gmail.com','edgar.malagon@tecdmx.org.mx',NULL,'1803','MXME821116HDFLRD06','MXME821116','1534.jpg',NULL),(1535,'Julio César','Jacinto','Alcocer',1,'cesarjalcocer@gmail.com','julio.jacinto@tecdmx.org.mx',NULL,NULL,'JAAJ840229HDFCLL02','JAAJ840229','1535.jpg',NULL),(1536,'José Mauricio','Hernández','Espinosa',1,NULL,'mauricio.hernandez@tecdmx.org.mx',NULL,NULL,'HEEM761213HDFRSR08','HEEM761213',NULL,NULL),(1538,'Christian Alberto','Ruíz','Sánchez',1,'cars_2016@icloud.com','christian.ruiz@tecdmx.org.mx',NULL,'1706','RUSC781121HDFZNH01','RUSC781121','1538.jpg',NULL),(1540,'Karla Patricia','Fraustro','Vázquez',2,'karlafrausto@hotmail.com','karla.frausto@tecdmx.org.mx',NULL,NULL,'FAVK850701MMNRZR09','FAVK850701','1540.jpg',NULL),(1541,'José Inés','Ávila','Sánchez',1,'joseines_as@yahoo.com.mx','ines.avila@tecdmx.org.mx',NULL,NULL,'AISI830409HDFVNN06','AISI830409','1541.jpg',NULL),(1542,'Paola Lizeth','Miranda','González',2,NULL,'paola.miranda@tecdmx.org.mx',NULL,'1147','MIGP881026MDFRNL08','MIGP881026','1542.jpg',NULL),(1543,'Aldo Jovani','Saldaña','Martínez',1,'acionlegal8@gmail.com','aldo.saldana@tecdmx.org.mx',NULL,NULL,'SAMA850727HDFLRL02','SAMA850727','1543.jpg',NULL),(1544,'Elías','Vargas','Uriostegui',1,'eliasv.u@hotmail.com','elias.vargas@tecdmx.org.mx',NULL,'1341','VAUE930615HDFRRL00','VAUE930615','1544.jpg',NULL),(1548,'Karla Evelyn','Huerta','Hernández',2,'kehh19@hotmail.com','karla.huerta@tecdmx.org.mx',NULL,NULL,'HUHK840207MDFRRR03','HUHK840207','1548.jpg',NULL),(1549,'Armando','Salas','Cruz',1,NULL,'armando.salas@tecdmx.org.mx',NULL,'1265','SACA800116HDFLRR01','SACA800116',NULL,NULL),(1551,'Carlos Alberto','Rosales','Mejía',1,NULL,'carlos.rosales@tecdmx.org.mx',NULL,NULL,'ROMC851103HDFSJR00','ROMC851103',NULL,NULL),(1552,'Madeleine','Flores','García',2,NULL,'madeleine.flores@tecdmx.org.mx',NULL,NULL,'FOGM970913MDFLRD03','FOGM970913',NULL,NULL),(1553,'Juvencio','Tovar','Juárez',1,NULL,'juvencio.tovar@tecdmx.org.mx',NULL,NULL,'TOJJ920806HMCVRV02','TOJJ920806','1553.jpg',NULL),(1556,'Luis Eduardo','Gasca','Velázquez',1,NULL,'eduardo.gasca@tecdmx.org.mx',NULL,NULL,'GAVL850123HDFSLS07','GAVL850123','1556.jpg',NULL),(1559,'Nelly Elizabeth','Mayani','Viveros',2,NULL,'nelly.mayani@tecdmx.org.mx',NULL,NULL,'MAVN530207MDFYVL00','MAVN530207',NULL,NULL),(1560,'María Magdalena','Roque','Morales',2,NULL,'magdalena.roque@tecdmx.org.mx',NULL,'1222','ROMM730722MDFQRG05','ROMM730722','1560.jpg',NULL),(1561,'Daniela Fernanda','Arellano','López',2,'arellanodaniiela@gmail.com','daniela.arellano@tecdmx.org.mx',NULL,'1150','AELD940610MDFRPN07','AELD940610',NULL,NULL),(1563,'Karla Ishmell','León','Escamilla',2,NULL,'karla.leon@tecdmx.org.mx',NULL,'1025','LEEK840305MMCNSR01','LEEK840305',NULL,NULL),(1567,'Yolanda','Sánchez','Cervantes',2,'yolsace.2405@gmail.com','yolanda.sanchez@tecdmx.org.mx',NULL,'1211','SACY690524MDFNRL02','SACY690524','1567.jpg',NULL),(1568,'Luis Antonio','Ruelas','Ventura',1,'wb.antonioruelas@gmail.com','antonio.ruelas@tecdmx.org.mx',NULL,'1273','RUVL930411HDFLNS03','RUVL930411','1568.jpg',NULL),(1570,'María del Rosario Osiris','Rodríguez','Pérez',2,'rousrdz02@gmail.com','rosario.rodriguez@tecdmx.org.mx',NULL,'1190','ROPR600810MDFDRS02','ROPR600810','1570.jpg',NULL),(1571,'Luis','de la Peña','Ponce de León',1,'luisppl_scjn@hotmail.com','luis.delapena@tecdmx.org.mx',NULL,NULL,'PEPL780811HSPXNS00','PEPL780811',NULL,NULL),(1572,'Thelma','Avilez','Kerlegand',2,'thelma_avilez_kerlegand@hotmail.com','thelma.avilez@tecdmx.org.mx',NULL,'1132','AIKT751030MDFVRH09','AIKT751030','1572.jpg',NULL),(1575,'Mario Alberto','Guzmán','Ramírez',1,'licmarioguzman@live.com.mx','mario.guzman@tecdmx.org.mx',NULL,'1493','GURM791129HJCZMR05','GURM791129','1575.jpg',NULL),(1576,'Fernando','Carrillo','Uribe',1,'fercaruby@gmail.com','fernando.carrillo@tecdmx.org.mx',NULL,NULL,'CAUF860715HDFRRR08','CAUF860715','1576.jpg',NULL),(1577,'Gloria Luz','Duarte','Valerio',2,'duarlu_05@hotmail.com','gloria.duarte@tecdmx.org.mx',NULL,'1361','DUVG810529MZSRLL07','DUVG810529','1577.jpg',NULL),(1578,'Edgar','Perales','Aguilar',1,'dr.edgarperales@gmail.com','edgar.perales@tecdmx.org.mx',NULL,NULL,'PEAE850720HDFRGD00','PEAE850720','1578.jpg',NULL),(1579,'Héctor Ceferino','Tejeda','González',1,'hector_tego84@hotmail.com','hector.tejeda@tecdmx.org.mx',NULL,NULL,'TEGH840805HDFJNC04','TEGH840805','1579.jpg',NULL),(1580,'Luis Fernando','Torres','Espínola',1,'fernando.torres.spinola@gmail.com','luis.torres@tecdmx.org.mx',NULL,'1270','TOEL960620HDFRSS06','TOEL960620','1580.jpg',NULL),(1581,'Amancio Gabriel','Estrada','Leal',1,NULL,'amancio.estrada@tecdmx.org.mx',NULL,NULL,'EALA940613HDFSLM09','EALA940613','1581.jpg',NULL),(1582,'Gilberto','Gómez','Flores',1,NULL,'gilberto.gomez@tecdmx.org.mx',NULL,NULL,'GOFG740620HMNMLL06','GOFG740620','1582.jpg',NULL),(1583,'Jorge Alberto','Díaz','Stringel',1,'stringel69@gmail.com','jorge.diaz@tecdmx.org.mx',NULL,'1111','DISJ690531HDFZTR05','DISJ690531','1583.jpg',NULL),(1584,'Adamnan','Hernández','Gatica',1,'adamnan66@gmail.com','adamnan.hernandez@tecdmx.org.mx',NULL,NULL,'HEGA841202HDFRTD08','HEGA841202','1584.jpg',NULL),(1585,'Ana Delia','Sánchez','Gutiérrez',2,'ana_delia_sanchez@hotmail.com','ana.sanchez@tecdmx.org.mx',NULL,NULL,'SAGA871018MDFNTN04','SAGA871018','1585.jpg',NULL),(1586,'Isaí','Fararoni','Ramírez',1,'isai@fararoni.net','isai.fararoni@tecdmx.org.mx',NULL,'1194','FARI741006HCSRMS07','FARI741006',NULL,NULL),(1588,'Marcela Lorena','Castillo','Varela',2,'marcela.castillo226@gmail.com','marcela.castillo@tecdmx.org.mx',NULL,NULL,'CAVM800622MHGSRR04','CAVM800622',NULL,NULL),(1589,'Daniela Yazmín','Martínez','Ortega',2,'dannymtz0806@gmail.com','daniela.martinez@tecdmx.org.mx',NULL,NULL,'MAOD010608MMCRRNA5','MAOD010608',NULL,NULL),(1590,'Aarón Moisés','Sánchez','Contreras',1,'s.car.f@hotmail.com','aaron.sanchez@tecdmx.org.mx',NULL,'1108','SACA960615HDFNNR03','SACA960615',NULL,NULL),(1592,'Fernando','Cortés','Figueroa',1,'fcortes@espaciocontable.com','fernando.cortes@tecdmx.org.mx',NULL,NULL,'COFF721228HDFRGR09','COFF721228',NULL,NULL),(1593,'René','Navarrete','Tenco',1,'renavarretemx@gmail.com','rene.navarrete@tecdmx.org.mx',NULL,'1199','NATR971112HVZVNN01','NATR971112',NULL,NULL),(1594,'Jennifer Aylin','Hernández','Nava',2,'jay_hn@outlook.com','jennifer.hernandez@tecdmx.org.mx',NULL,NULL,'HENJ000908MMCRVNA3','HENJ000908',NULL,NULL),(1595,'Vania Alí','Bello','Cortés',2,'vaniabello95@hotmail.com','vania.bello@tecdmx.org.mx',NULL,NULL,'BECV950211MQRLRN01','BECV950211',NULL,NULL),(1596,'Alejandro','Ahumada','Astiazarán',1,'aahumada@hotmail.com','alejandro.ahumada@tecdmx.org.mx',NULL,NULL,'AUAA891007HSRHSL08','AUAA891007',NULL,NULL),(1597,'Héctor','Romero','Bolaños',1,'hectorobo@gmail.com','hector.romero@tecdmx.org.mx',NULL,NULL,'ROBH710601HDFMLC07','ROBH710601',NULL,NULL),(1598,'José Carlos','Jasso','Bonilla',1,'carlosjasso207@gmail.com','carlos.jasso@tecdmx.org.mx',NULL,NULL,'JABC940328HMCSNR06','JABC940328',NULL,NULL),(1599,'Jorge Alberto','Ayala','Fragoza',1,'jorgeeaiala@gmail.com','jorge.ayala@tecdmx.org.mx',NULL,NULL,'AAFJ850728HDFYRR01','AAFJ850728',NULL,NULL),(1600,'Sergio Paulo','González','Garrido',1,'sergiopaulogarrido@outlook.es','sergio.gonzalez@tecdmx.org.mx',NULL,NULL,'GOGS971019HDFNRR08','GOGS971019',NULL,NULL),(6314,'Graciela Eunice','Illescas','Acosta',2,'greyz9769@gmail.com','graciela.illescas@tecdmx.org.mx','2721418102',NULL,'IEAG970706MVZLCR07','IEAG9707064Z9',NULL,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inst_empleado_puesto`
--

LOCK TABLES `inst_empleado_puesto` WRITE;
/*!40000 ALTER TABLE `inst_empleado_puesto` DISABLE KEYS */;
INSERT INTO `inst_empleado_puesto` VALUES (1,93,19,85,'2007-02-22',NULL),(2,149,23,69,'2017-04-01',NULL),(3,157,28,69,'2017-01-01',NULL),(4,253,23,56,'2015-11-01',NULL),(5,306,10,56,'2019-06-01',NULL),(6,359,14,103,'2019-11-01',NULL),(7,399,23,58,'2009-09-16',NULL),(8,427,13,69,'2006-02-16',NULL),(9,504,17,85,'2010-07-01',NULL),(10,545,23,43,'2007-03-16',NULL),(11,611,23,21,'2007-10-16',NULL),(12,703,23,85,'2018-04-16',NULL),(13,712,20,130,'2014-11-01',NULL),(14,721,17,83,'2010-02-16',NULL),(15,735,61,97,'2016-07-01',NULL),(16,739,23,122,'2010-06-15',NULL),(17,764,10,90,'2010-08-16',NULL),(18,784,13,123,'2017-05-01',NULL),(19,832,24,3,'2023-07-16',NULL),(20,850,24,3,'2012-02-01',NULL),(21,852,24,123,'2018-01-01',NULL),(22,880,13,123,'2020-01-01',NULL),(23,888,23,85,'2018-10-01',NULL),(24,906,29,122,'2013-06-01',NULL),(25,923,23,58,'2021-07-16',NULL),(26,927,15,13,'2017-05-01',NULL),(27,937,1,62,'2017-04-26',NULL),(28,961,2,68,'2014-05-22',NULL),(29,971,23,70,'2014-10-16',NULL),(30,973,1,62,'2014-10-16',NULL),(31,974,18,102,'2014-10-16',NULL),(32,977,17,89,'2014-10-16',NULL),(33,1008,23,85,'2018-09-01',NULL),(34,1012,23,58,'2014-11-16',NULL),(35,1016,2,84,'2014-11-16',NULL),(36,1020,70,68,'2014-11-16',NULL),(37,1023,61,101,'2018-03-16',NULL),(38,1024,17,88,'2014-11-16',NULL),(39,1026,23,48,'2022-08-16',NULL),(40,1038,24,123,'2022-06-16',NULL),(41,1039,1,134,'2014-12-01',NULL),(42,1054,23,44,'2015-01-16',NULL),(43,1055,23,133,'2015-01-16',NULL),(44,1058,23,58,'2015-01-16',NULL),(45,1059,18,101,'2015-01-16',NULL),(46,1073,18,101,'2015-02-16',NULL),(47,1082,13,58,'2019-06-16',NULL),(48,1083,2,58,'2015-03-16',NULL),(49,1084,2,85,'2015-03-16',NULL),(50,1101,2,58,'2015-07-01',NULL),(51,1111,10,115,'2015-08-01',NULL),(52,1114,2,25,'2015-09-16',NULL),(53,1116,23,48,'2015-10-01',NULL),(54,1137,24,68,'2016-01-16',NULL),(55,1139,2,43,'2016-02-01',NULL),(56,1146,23,58,'2016-04-01',NULL),(57,1159,67,126,'2016-05-01',NULL),(58,1168,61,101,'2016-05-16',NULL),(59,1170,23,44,'2016-05-16',NULL),(60,1171,29,58,'2016-05-16',NULL),(61,1172,23,44,'2023-06-16',NULL),(62,1175,22,102,'2016-06-01',NULL),(63,1176,72,58,'2016-06-01',NULL),(64,1182,21,124,'2016-07-01',NULL),(65,1188,23,85,'2018-10-01',NULL),(66,1196,24,127,'2020-01-01',NULL),(67,1197,2,68,'2016-11-16',NULL),(68,1204,24,24,'2021-01-16',NULL),(69,1235,29,67,'2019-01-01',NULL),(70,1239,17,96,'2017-05-01',NULL),(71,1242,2,68,'2017-05-16',NULL),(72,1247,72,58,'2017-06-01',NULL),(73,1248,16,129,'2023-05-01',NULL),(74,1252,70,76,'2023-10-16',NULL),(75,1254,23,58,'2019-01-16',NULL),(76,1263,17,88,'2017-06-16',NULL),(77,1268,16,102,'2017-07-01',NULL),(78,1270,24,84,'2017-07-01',NULL),(79,1279,16,101,'2019-02-01',NULL),(80,1282,2,68,'2017-09-01',NULL),(81,1284,77,90,'2018-11-16',NULL),(82,1289,76,65,'2023-03-01',NULL),(83,1290,28,58,'2017-11-01',NULL),(84,1292,65,85,'2017-11-16',NULL),(85,1308,23,50,'2018-01-01',NULL),(86,1311,57,55,'2018-01-01',NULL),(87,1317,21,56,'2018-01-16',NULL),(88,1323,23,123,'2018-01-16',NULL),(89,1335,18,131,'2018-02-16',NULL),(90,1336,16,101,'2018-03-01',NULL),(91,1340,15,86,'2018-03-16',NULL),(92,1343,23,64,'2018-04-01',NULL),(93,1346,23,58,'2018-04-16',NULL),(94,1357,62,58,'2018-06-01',NULL),(95,1366,24,21,'2018-07-16',NULL),(96,1372,2,90,'2018-09-16',NULL),(97,1375,23,87,'2018-10-01',NULL),(98,1377,17,83,'2018-10-01',NULL),(99,1380,23,48,'2018-10-01',NULL),(100,1382,61,76,'2018-10-16',NULL),(101,1385,77,58,'2018-11-01',NULL),(102,1388,10,96,'2018-12-01',NULL),(103,1389,17,88,'2018-12-01',NULL),(104,1390,21,66,'2018-12-16',NULL),(105,1391,19,95,'2019-01-01',NULL),(106,1393,22,100,'2019-01-01',NULL),(107,1394,23,48,'2019-01-01',NULL),(108,1399,23,18,'2019-01-01',NULL),(109,1402,21,85,'2019-01-01',NULL),(110,1406,67,84,'2019-01-16',NULL),(111,1407,17,83,'2019-01-16',NULL),(112,1408,47,58,'2019-01-16',NULL),(113,1416,62,122,'2019-02-16',NULL),(114,1429,23,50,'2019-04-16',NULL),(115,1436,23,44,'2019-05-16',NULL),(116,1446,22,75,'2019-06-01',NULL),(117,1447,61,58,'2023-06-16',NULL),(118,1451,14,102,'2019-07-01',NULL),(119,1455,10,122,'2019-07-16',NULL),(120,1458,17,88,'2019-08-01',NULL),(121,1460,14,102,'2019-08-16',NULL),(122,1462,61,84,'2019-09-16',NULL),(123,1465,65,56,'2019-10-16',NULL),(124,1466,15,98,'2019-10-16',NULL),(125,1469,1,63,'2019-10-24',NULL),(126,1470,13,56,'2019-11-01',NULL),(127,1471,15,56,'2019-11-16',NULL),(128,1475,14,102,'2020-01-01',NULL),(129,1477,14,102,'2020-01-01',NULL),(130,1478,10,56,'2020-01-01',NULL),(131,1480,23,91,'2020-01-01',NULL),(132,1481,13,88,'2020-01-01',NULL),(133,1484,77,66,'2020-01-16',NULL),(134,1485,10,56,'2020-02-01',NULL),(135,1486,24,21,'2020-02-01',NULL),(136,1495,24,88,'2020-10-16',NULL),(137,1504,17,58,'2020-12-01',NULL),(138,1505,2,58,'2020-12-01',NULL),(139,1506,61,101,'2021-01-01',NULL),(140,1507,10,56,'2021-01-01',NULL),(141,1509,10,85,'2021-01-01',NULL),(142,1510,10,85,'2021-01-01',NULL),(143,1511,10,85,'2021-01-01',NULL),(144,1513,23,104,'2021-01-16',NULL),(145,1515,77,122,'2021-02-01',NULL),(146,1518,23,68,'2021-02-01',NULL),(147,1521,10,10,'2021-02-16',NULL),(148,1523,23,122,'2021-03-01',NULL),(149,1525,23,48,'2021-03-16',NULL),(150,1526,47,122,'2021-03-16',NULL),(151,1527,13,76,'2021-03-16',NULL),(152,1529,10,69,'2021-04-01',NULL),(153,1530,10,55,'2021-04-01',NULL),(154,1531,21,96,'2021-04-01',NULL),(155,1533,13,69,'2021-05-01',NULL),(156,1534,61,97,'2021-05-01',NULL),(157,1535,61,11,'2021-05-01',NULL),(158,1536,21,85,'2021-06-16',NULL),(159,1538,65,125,'2021-06-16',NULL),(160,1540,10,122,'2021-07-01',NULL),(161,1541,17,95,'2021-09-16',NULL),(162,1542,57,67,'2021-09-16',NULL),(163,1543,10,10,'2021-10-01',NULL),(164,1544,23,65,'2021-10-01',NULL),(165,1548,70,58,'2021-11-01',NULL),(166,1549,15,58,'2022-01-01',NULL),(167,1551,15,88,'2022-01-16',NULL),(168,1552,23,23,'2022-02-01',NULL),(169,1553,23,68,'2022-02-01',NULL),(170,1556,62,78,'2022-02-16',NULL),(171,1559,13,80,'2022-04-01',NULL),(172,1560,23,84,'2022-05-16',NULL),(173,1561,15,85,'2022-06-01',NULL),(174,1563,13,80,'2022-06-01',NULL),(175,1567,23,50,'2022-10-17',NULL),(176,1568,15,135,'2022-11-01',NULL),(177,1570,23,58,'2023-02-01',NULL),(178,1571,15,11,'2023-02-01',NULL),(179,1572,15,85,'2023-02-01',NULL),(180,1575,22,102,'2023-02-16',NULL),(181,1576,57,54,'2023-03-16',NULL),(182,1577,10,96,'2023-03-16',NULL),(183,1578,23,58,'2023-04-01',NULL),(184,1579,13,58,'2023-04-16',NULL),(185,1580,15,135,'2023-04-16',NULL),(186,1581,23,18,'2023-05-01',NULL),(187,1582,23,58,'2023-05-01',NULL),(188,1583,57,61,'2023-05-16',NULL),(189,1584,23,79,'2023-06-16',NULL),(190,1585,15,135,'2023-06-16',NULL),(191,1586,72,122,'2023-07-01',NULL),(192,1588,13,76,'2023-07-16',NULL),(193,1589,15,21,'2023-08-01',NULL),(194,1590,23,65,'2023-08-03',NULL),(195,1592,23,122,'2023-08-16',NULL),(196,1593,72,58,'2023-09-01',NULL),(197,1594,15,21,'2023-09-01',NULL),(198,1595,13,80,'2023-09-16',NULL),(199,1596,65,85,'2023-10-01',NULL),(200,1597,15,13,'2023-10-16',NULL),(201,1598,23,17,'2023-10-16',NULL),(202,1599,23,16,'2023-11-01',NULL),(203,1600,23,17,'2023-12-01',NULL),(204,6314,72,64,'2023-10-21',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=1526 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inst_titular_u_adscripcion`
--

LOCK TABLES `inst_titular_u_adscripcion` WRITE;
/*!40000 ALTER TABLE `inst_titular_u_adscripcion` DISABLE KEYS */;
INSERT INTO `inst_titular_u_adscripcion` VALUES (937,1,27,'2017-04-26',NULL),(973,1,30,'2014-10-16',NULL),(1026,23,39,'2022-08-16',NULL),(1039,1,41,'2014-12-01',NULL),(1055,23,43,'2015-01-16',NULL),(1116,23,53,'2015-10-01',NULL),(1170,23,59,'2016-05-16',NULL),(1172,23,61,'2023-06-16',NULL),(1308,23,85,'2018-01-01',NULL),(1380,23,99,'2018-10-01',NULL),(1394,23,107,'2019-01-01',NULL),(1429,23,114,'2019-04-16',NULL),(1436,23,115,'2019-05-16',NULL),(1469,1,125,'2019-10-24',NULL),(1480,23,131,'2020-01-01',NULL),(1513,23,144,'2021-01-16',NULL),(1525,23,149,'2021-03-16',NULL);
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
  `n_id_transaccion` int DEFAULT NULL,
  `n_id_num_empleado` int DEFAULT NULL,
  `id_instruccion_doc` int DEFAULT NULL,
  `fecha_notificacion` datetime DEFAULT NULL,
  `fecha_lectura` datetime DEFAULT NULL,
  `fecha_acuse` datetime DEFAULT NULL,
  `id_firma_aplicada` int DEFAULT NULL,
  PRIMARY KEY (`s_hash_documento`,`n_id_usuario`),
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
INSERT INTO `pki_usuarios_cert` VALUES (1,'275106190557734483187066766829379881448953492022','IEAG970706MVZLCR07','IEAG9707064Z9','3d561ef6a6a4f8670e3f31918e11263cc9fecc7308827dc149c518e0c2a00c33'),(2,'275106190557734483187066766866272528487140635704','FARI741006HCSRMS07','FARI7410066X8','9c6a501ea8af2846d8be245318bbbbf97fd7c9e4c0c475750d1ad915e97f5b11');
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
INSERT INTO `pki_x509_ac_autorizadas` VALUES ('11111',NULL,NULL,NULL,NULL,'11111',NULL),('275106190556405255191281850864241509582310027318','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','AUTORIDAD CERTIFICADORA','INTERMEDIO',NULL,'275106190556405255191281850864241509582310027318',NULL),('275106190556405255191281850864241509582310029616','MIII0zCCBrugAwIBAgIUMDAwMDAwMDAwMDAwMDAwMDExOTAwDQYJKoZIhvcNAQENBQAwggENMQswCQYDVQQGEwJNWDENMAsGA1UECBMEQ0RNWDETMBEGA1UEBxMKQ1VBVUhURU1PQzEYMBYGA1UEChMPQkFOQ08gREUgTUVYSUNPMRAwDgYDVQQLEwdERElTUElNMSUwIwYDVQQDExxBR0VOQ0lBIFJFR0lTVFJBRE9SQSBDRU5UUkFMMRIwEAYGdYhdjzUfEwZDMTI5MzExETAPBgZ1iF2PNRETBTA2MDAwMRcwFQYGdYhdjzUTEws1IERFIE1BWU8gMjEkMCIGCSqGSIb3DQEJAhMVREFOSUVMQSBWQUxERVogUk9NRVJPMSEwHwYJKoZIhvcNAQkBFhJpZXNAYmFueGljby5vcmcubXgwHhcNMjMwNTIzMjIyNDEwWhcNMzEwNTIzMjIyNDEwWjCCAZUxNTAzBgNVBAMMLEFDIERFTCBTRVJWSUNJTyBERSBBRE1JTklTVFJBQ0lPTiBUUklCVVRBUklBMS4wLAYDVQQKDCVTRVJWSUNJTyBERSBBRE1JTklTVFJBQ0lPTiBUUklCVVRBUklBMRowGAYDVQQLDBFTQVQtSUVTIEF1dGhvcml0eTEyMDAGCSqGSIb3DQEJARYjc2VydmljaW9zYWxjb250cmlidXllbnRlQHNhdC5nb2IubXgxJjAkBgNVBAkMHUF2LiBIaWRhbGdvIDc3LCBDb2wuIEd1ZXJyZXJvMQ4wDAYDVQQRDAUwNjMwMDELMAkGA1UEBhMCTVgxDTALBgNVBAgMBENETVgxEzARBgNVBAcMCkNVQVVIVEVNT0MxFTATBgNVBC0TDFNBVDk3MDcwMU5OMzFcMFoGCSqGSIb3DQEJAhNNcmVzcG9uc2FibGU6IEFETUlOSVNUUkFDSU9OIENFTlRSQUwgREUgU0VSVklDSU9TIFRSSUJVVEFSSU9TIEFMIENPTlRSSUJVWUVOVEUwggIiMA0GCSqGSIb3DQEBAQUAA4ICDwAwggIKAoICAQDKi27wIYk/01fVT0j27xDdrH96OhIEJ/U/SivLEGqKE3hfdUnat4Tg/7NgL8juIRaTOATd9xzX5FYEN9bzL24NVYkSQyZU3EMWUTGc8agx5QopVZWrypIsjp1tMX79ILARYUKyNBA9FdKp6DRkvYDNKZLDYajR/6fiuPbqhxqMuDhA5cyOfHth65Su6fnSMg/NGt2hhY/x7o+flKxJyhU6uENeyR4baHToknJEyWa8UxrIixiyFupFx0UgdQzHaaVcQ2YRf1T5lSnb8t5JTUgSNl13N/CjOMjxFOlJvCqHf7IeQYqZ9H7tc6Pcxgh6rpyniGj1d45d7c0xzTLZ/EYQH5dzy7IrW5ynFWSDPXNhGnx+Vpr8ZyH09cqiimd3PsWzWbjz7KrAixJmR1ui3/wiODwi6T9DsOrNSzDxmiC2syAcGOlb1kakdKNWAGuP2BFBrmWaOrD7IQq5i9VqVz1VrF/Zq7WZrtH7tcHCe1NxeRLUlo97nQTTl0Q38HtasA3xatyi2tW3ReUwCiSZiZL/UUljHLZpoZiOGsXG3f7LvQhgi7JNbQ3dlHNu+XwLkWhALgiP75u7MOw8RkPlpbdlY97T+9oGFe8VDOE2/z7M//XsHJGsSlP30XKbaVB6TG2ilOMrt0xSah/WYM/qZWp915KvpugnhCD/6J6Sk2b6qQIDAQABo4IBnTCCAZkwggFSBgNVHSMEggFJMIIBRYAUBTwvbInCjBuKUgEf8Bq0iXOUNE6hggEVpIIBETCCAQ0xCzAJBgNVBAYTAk1YMQ0wCwYDVQQIEwRDRE1YMRMwEQYDVQQHEwpDVUFVSFRFTU9DMRgwFgYDVQQKEw9CQU5DTyBERSBNRVhJQ08xEDAOBgNVBAsTB0RESVNQSU0xJTAjBgNVBAMTHEFHRU5DSUEgUkVHSVNUUkFET1JBIENFTlRSQUwxEjAQBgZ1iF2PNR8TBkMxMjkzMTERMA8GBnWIXY81ERMFMDYwMDAxFzAVBgZ1iF2PNRMTCzUgREUgTUFZTyAyMSQwIgYJKoZIhvcNAQkCExVEQU5JRUxBIFZBTERFWiBST01FUk8xITAfBgkqhkiG9w0BCQEWEmllc0BiYW54aWNvLm9yZy5teIIUMDAwMDAwMDAwMDAwMDAwMDAwMDUwHQYDVR0OBBYEFFzNs++2/8FZJh5bMxQlg63KVvpCMBIGA1UdEwEB/wQIMAYBAf8CAQAwDgYDVR0PAQH/BAQDAgH2MA0GCSqGSIb3DQEBDQUAA4ICAQAMQL+EGhXtacWht6WOiBw06LqvAmAujOHWegpB8UQdma5tThmKz9TAMTGoZv2gGQ2HVlAqyJ6vZWyCBXyyh9WtpZNAP0aKJLR8lulBFN1KK1OSCOMReuFq0MDadlQCsDXs3tkiUkI4tLGLuqBEddOrZKv5S+35DAdbSHo9cg5Xa1C3xib++yMJsKSHeqxzHnS+VlcYDJ0C09arLchcoArq3aODx/6/viXaDEKJln9egfUgGOYGNSSqLkdgAfTh4zwYBSiGZA7rcwPb2JPxYbZnZDt3EN+HjT3k8j41IV/htA2qVGHca+y0TMGjU+Wdd+M5xTKKSa8fLgTD4DFJAlz6UxMiye764czulWdL1n72U0mh4G9QSujj71iSmEasmYfVApdhXg2eZ03AydG35hrY3QZdYalxCEPakdpKO4w+I9EKH/LvgqWHeWGN0aocOIqHaj6+geAV3H0TQGhZjx/zBSNkeYrwAxfcEO92M8ZXytGGWdvUhFkYat85gI7YrUmgoMY6LhRBELZyZj/lT78H9P1TOf1bZf+/ujacZRW2obUQpuwjDID2/PKe1/4vdzTC7Xk4NbQAv7nw1zBI1cpJIECgT0DqfT34ZktojZomuXSv5npWKWTq4FHjh0Wfc5QSJ+Ujai/RoM4eDrbZ1raXzcmMwRSnnTXhSKdO5nAz5A==','AC DEL SERVICIO DE ADMINISTRACION TRIBUTARIA','INTERMEDIO',NULL,'275106190556405255191281850864241509582310029616',NULL),('275106190557734646082654485476256902098969441081','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','Servicio OCSP SAT','OCSP','http://www.sat.gob.mx/ocsp','275106190556405255191281850864241509582310027318',NULL),('275106190557734646082654485476256902099002995253','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','OCSP SAT','OCSP','http://www.sat.gob.mx/ocsp','275106190556405255191281850864241509582310029616',NULL);
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
INSERT INTO `pki_x509_registrados` VALUES ('275106190557734483187066766829379881448953492022','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','b40b606e0c7d20f5a09aea57a9c1d932c651630a8254e9e0a5be6c546d0d70d2','275106190556405255191281850864241509582310027318','GRACIELA EUNICE ILLESCAS ACOSTA','IEAG9707064Z9','IEAG970706MVZLCR07','Graciela Eunice','Illescas','Acosta','5f5c19f4a8f7f71ea0ac4e910f4ff1968803fb32e3b2b8208690ff02efbbde0c',NULL,'2020-02-05 13:27:49','2024-02-05 13:28:29'),('275106190557734483187066766866272528487140635704','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','d5b7e7a74014eeee38c06d903feee5d21dd80c202d12893aba3124d902b2fb7d','275106190556405255191281850864241509582310029616','ISAI FARARONI RAMIREZ','FARI7410066X8','FARI741006HCSRMS07','Otilio Esteban','Hernández','Pérez','80cdc24d2791969f71e46fbf70132ae14232a608e3b50ce3a880f210c7e77a80',NULL,'2023-06-09 11:30:57','2027-06-09 11:31:37');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seg_org_roles_usuarios`
--

LOCK TABLES `seg_org_roles_usuarios` WRITE;
/*!40000 ALTER TABLE `seg_org_roles_usuarios` DISABLE KEYS */;
INSERT INTO `seg_org_roles_usuarios` VALUES (1,1,1,22,NULL),(2,1,2,10,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seg_org_usuario_estado_usuario`
--

LOCK TABLES `seg_org_usuario_estado_usuario` WRITE;
/*!40000 ALTER TABLE `seg_org_usuario_estado_usuario` DISABLE KEYS */;
INSERT INTO `seg_org_usuario_estado_usuario` VALUES (1,1,5,NULL,'2023-12-07 08:00:12',NULL),(2,2,5,NULL,'2023-12-22 14:11:41',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seg_org_usuarios`
--

LOCK TABLES `seg_org_usuarios` WRITE;
/*!40000 ALTER TABLE `seg_org_usuarios` DISABLE KEYS */;
INSERT INTO `seg_org_usuarios` VALUES (1,'graciela.illescas','$2a$10$pM2m2E9XbuZjCj4yKBXn7.yrNeF3tNx8CrpG17O1VF8kqBdMOPEnu',NULL,'graciela.illescas@tecdmx.org.mx',5,NULL),(2,'otilio.hernandez','$2a$10$JOtkEwefN2WByH9NSwFBNutf84Y./mcTyAPT4.LzaSLAomsL5xnTu',NULL,'otilio.hernandez@tecdmx.org.mx',5,NULL);
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
INSERT INTO `tab_cat_etapa_documento` VALUES (1,'Creado'),(2,'Enviado'),(3,'En Firma'),(4,'Rechazado'),(5,'Terminado');
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_cat_tipo_documento`
--

LOCK TABLES `tab_cat_tipo_documento` WRITE;
/*!40000 ALTER TABLE `tab_cat_tipo_documento` DISABLE KEYS */;
INSERT INTO `tab_cat_tipo_documento` VALUES (1,72,'Acuerdo de radicación'),(2,72,'Acuerdo de recepción'),(3,72,'Acuerdo de turno'),(4,72,'Oficio'),(5,72,'Acuerdo plenario'),(6,72,'Minuta'),(7,23,'Sentencia'),(8,23,'Minuta');
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
  `secuencia` int DEFAULT NULL,
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
  `n_en_orden` int DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_expedientes`
--

LOCK TABLES `tab_expedientes` WRITE;
/*!40000 ALTER TABLE `tab_expedientes` DISABLE KEYS */;
INSERT INTO `tab_expedientes` VALUES (1,'123/2023','123/2023',1);
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
-- Temporary view structure for view `vista_part1`
--

DROP TABLE IF EXISTS `vista_part1`;
/*!50001 DROP VIEW IF EXISTS `vista_part1`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vista_part1` AS SELECT 
 1 AS `n_id_documento`,
 1 AS `folio_documento`,
 1 AS `n_id_prioridad`,
 1 AS `creacion_documento_fecha`,
 1 AS `s_asunto`,
 1 AS `ult_actualizacion`,
 1 AS `empleado_id`,
 1 AS `rol`,
 1 AS `id_etapa_documento`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vista_tablero`
--

DROP TABLE IF EXISTS `vista_tablero`;
/*!50001 DROP VIEW IF EXISTS `vista_tablero`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vista_tablero` AS SELECT 
 1 AS `n_id_documento`,
 1 AS `folio_documento`,
 1 AS `s_desc_etapa`,
 1 AS `prioridad`,
 1 AS `creacion_documento_fecha`,
 1 AS `s_asunto`,
 1 AS `num_empleado`,
 1 AS `tipo`,
 1 AS `ult_actualizacion`,
 1 AS `n_id_inst`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'db_mappings'
--

--
-- Final view structure for view `vista_part1`
--

/*!50001 DROP VIEW IF EXISTS `vista_part1`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vista_part1` AS select `d`.`n_id_documento` AS `n_id_documento`,`d`.`folio_documento` AS `folio_documento`,`d`.`n_id_prioridad` AS `n_id_prioridad`,`d`.`creacion_documento_fecha` AS `creacion_documento_fecha`,`d`.`s_asunto` AS `s_asunto`,`w`.`ult_actualizacion` AS `ult_actualizacion`,(case when `d`.`n_id_num_empleado_creador` not in (select `tab_docs_firmantes`.`n_id_num_empleado` from `tab_docs_firmantes` where (`tab_docs_firmantes`.`n_id_documento` = `d`.`n_id_documento`) union select `tab_doc_destinatarios`.`n_id_num_empleado` from `tab_doc_destinatarios` where (`tab_doc_destinatarios`.`n_id_documento` = `d`.`n_id_documento`)) then `d`.`n_id_num_empleado_creador` end) AS `empleado_id`,'creador' AS `rol`,`w`.`id_etapa_documento` AS `id_etapa_documento` from (`tab_documentos` `d` left join `tab_documento_workflow` `w` on((`d`.`n_id_documento` = `w`.`id_document`))) where ((`w`.`ult_actualizacion` = (select max(`tab_documento_workflow`.`ult_actualizacion`) from `tab_documento_workflow` where (`tab_documento_workflow`.`id_document` = `d`.`n_id_documento`))) and `d`.`n_id_num_empleado_creador` in (select `tab_docs_firmantes`.`n_id_num_empleado` from `tab_docs_firmantes` where (`tab_docs_firmantes`.`n_id_documento` = `d`.`n_id_documento`)) is false and `d`.`n_id_num_empleado_creador` in (select `tab_doc_destinatarios`.`n_id_num_empleado` from `tab_doc_destinatarios` where (`tab_doc_destinatarios`.`n_id_documento` = `d`.`n_id_documento`)) is false) union select `d`.`n_id_documento` AS `n_id_documento`,`d`.`folio_documento` AS `folio_documento`,`d`.`n_id_prioridad` AS `n_id_prioridad`,`d`.`creacion_documento_fecha` AS `creacion_documento_fecha`,`d`.`s_asunto` AS `s_asunto`,`w`.`ult_actualizacion` AS `ult_actualizacion`,`f`.`n_id_num_empleado` AS `empleado_id`,if((`d`.`n_id_num_empleado_creador` = `f`.`n_id_num_empleado`),'creador-firmante','firmante') AS `rol`,`w`.`id_etapa_documento` AS `id_etapa_documento` from ((`tab_documentos` `d` join `tab_docs_firmantes` `f` on((`d`.`n_id_documento` = `f`.`n_id_documento`))) left join `tab_documento_workflow` `w` on((`d`.`n_id_documento` = `w`.`id_document`))) where ((`w`.`ult_actualizacion` = (select max(`tab_documento_workflow`.`ult_actualizacion`) from `tab_documento_workflow` where (`tab_documento_workflow`.`id_document` = `d`.`n_id_documento`))) and (`f`.`n_id_num_empleado` is not null)) union select `d`.`n_id_documento` AS `n_id_documento`,`d`.`folio_documento` AS `folio_documento`,`d`.`n_id_prioridad` AS `n_id_prioridad`,`d`.`creacion_documento_fecha` AS `creacion_documento_fecha`,`d`.`s_asunto` AS `s_asunto`,`w`.`ult_actualizacion` AS `ult_actualizacion`,`dest`.`n_id_num_empleado` AS `empleado_id`,if((`d`.`n_id_num_empleado_creador` = `dest`.`n_id_num_empleado`),'creador-destinatario','destinatario') AS `rol`,`w`.`id_etapa_documento` AS `id_etapa_documento` from ((`tab_documentos` `d` join `tab_doc_destinatarios` `dest` on((`d`.`n_id_documento` = `dest`.`n_id_documento`))) left join `tab_documento_workflow` `w` on((`d`.`n_id_documento` = `w`.`id_document`))) where ((`w`.`ult_actualizacion` = (select max(`tab_documento_workflow`.`ult_actualizacion`) from `tab_documento_workflow` where (`tab_documento_workflow`.`id_document` = `d`.`n_id_documento`))) and (`dest`.`n_id_num_empleado` is not null)) order by `n_id_documento`,`empleado_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vista_tablero`
--

/*!50001 DROP VIEW IF EXISTS `vista_tablero`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vista_tablero` AS select `v1`.`n_id_documento` AS `n_id_documento`,`v1`.`folio_documento` AS `folio_documento`,`tced`.`s_desc_etapa` AS `s_desc_etapa`,`tcp`.`desc_prioridad` AS `prioridad`,`v1`.`creacion_documento_fecha` AS `creacion_documento_fecha`,`v1`.`s_asunto` AS `s_asunto`,`v1`.`empleado_id` AS `num_empleado`,`v1`.`rol` AS `tipo`,`v1`.`ult_actualizacion` AS `ult_actualizacion`,`tdf`.`n_id_inst_firmante` AS `n_id_inst` from (((`vista_part1` `v1` join `tab_cat_etapa_documento` `tced` on((`tced`.`id_etapa_documento` = `v1`.`id_etapa_documento`))) join `tab_cat_prioridad` `tcp` on((`tcp`.`n_id_prioridad` = `v1`.`n_id_prioridad`))) join `tab_docs_firmantes` `tdf` on((`tdf`.`n_id_num_empleado` = `v1`.`empleado_id`))) where ((`v1`.`rol` <> 'firmante') or exists(select 1 from (`pki_documento_firmantes` `docfirmantesPKI` join `tab_documentos_adjuntos` `docadjuntosTAB` on((`docfirmantesPKI`.`s_hash_documento` = `docadjuntosTAB`.`documento_hash`))) where ((`docadjuntosTAB`.`id_document` = `v1`.`n_id_documento`) and (`docfirmantesPKI`.`n_id_num_empleado` = `v1`.`empleado_id`)))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-26 16:50:09
