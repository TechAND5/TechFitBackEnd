-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: techfit
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dia`
--

DROP TABLE IF EXISTS `dia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dia` (
  `dId` int(11) NOT NULL AUTO_INCREMENT,
  `diaSemana` varchar(10) NOT NULL,
  `fechaActual` date NOT NULL,
  `estado` int(11) NOT NULL,
  `habito` int(11) NOT NULL,
  `usuario` int(11) NOT NULL,
  PRIMARY KEY (`dId`),
  UNIQUE KEY `dId_UNIQUE` (`dId`),
  KEY `fk_dia_usuario_idx` (`usuario`),
  KEY `fk_dia_habito_idx` (`habito`),
  CONSTRAINT `fk_dia_habito` FOREIGN KEY (`habito`) REFERENCES `habito` (`hId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_dia_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`uId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dia`
--

LOCK TABLES `dia` WRITE;
/*!40000 ALTER TABLE `dia` DISABLE KEYS */;
/*!40000 ALTER TABLE `dia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habito`
--

DROP TABLE IF EXISTS `habito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `habito` (
  `hId` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `usuario` int(11) NOT NULL,
  `fechaI` date NOT NULL,
  `progreso` int(11) NOT NULL,
  `estado` int(11) NOT NULL,
  PRIMARY KEY (`hId`),
  UNIQUE KEY `hId_UNIQUE` (`hId`),
  KEY `fk_habito_usuario_idx` (`usuario`),
  CONSTRAINT `fk_habito_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`uId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habito`
--

LOCK TABLES `habito` WRITE;
/*!40000 ALTER TABLE `habito` DISABLE KEYS */;
/*!40000 ALTER TABLE `habito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `uId` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(18) NOT NULL,
  `pasword` varchar(45) NOT NULL,
  `nickname` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  PRIMARY KEY (`uId`),
  UNIQUE KEY `uId_UNIQUE` (`uId`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'vero@e.es','1234','Veron','Veronica','Veronica'),(4,'joan@e.es','1234','Joan','joan','joan'),(5,'rosa@e.es','1234','Rosa','Ro','Rosa'),(6,'diana@e.es','1234','Diana','Di','Diana'),(7,'marta@e.es','1234','Marta','Ma','Marta'),(8,'arismar@e.es','1234','Ari','Aris','Sampaio'),(9,'aitorr@e.es','1234','Ai','Aits','Crespo');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-31 16:04:20
