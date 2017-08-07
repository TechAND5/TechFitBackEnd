-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: techfit
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
-- Dumping data for table `dia`
--

LOCK TABLES `dia` WRITE;
/*!40000 ALTER TABLE `dia` DISABLE KEYS */;
INSERT INTO `dia` VALUES (1,'lunes','2017-07-31',1,1),(2,'martes','2017-08-01',1,1),(3,'miercoles','2017-08-02',0,1),(4,'jueves','2017-08-03',1,1),(5,'viernes','2017-08-04',0,1),(6,'sabado','2017-08-05',0,2),(7,'domingo','2017-08-06',1,2),(8,'lunes','2017-08-07',2,2),(9,'martes','2017-08-09',1,2);
/*!40000 ALTER TABLE `dia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `habito`
--

LOCK TABLES `habito` WRITE;
/*!40000 ALTER TABLE `habito` DISABLE KEYS */;
INSERT INTO `habito` VALUES (1,'habito1','comer sano','2017-08-02','2017-10-31',1,1,1),(2,'habito2','correr','2017-08-02','2017-10-31',25,2,2),(3,'habito3','nadar','2017-08-03','2017-10-31',26,3,3),(4,'habito4','yoga','2017-08-04','2017-10-31',27,4,3),(5,'habito5','guitarra','2017-08-05','2017-10-31',28,5,5),(6,'habito6','patinar','2017-08-06','2017-10-31',29,6,5),(7,'habito7','pintar','2017-08-07','2017-10-31',30,7,7),(8,'habito8','beber agua','2017-08-08','2017-10-31',40,8,8),(9,'habito9','comer sandia','2017-08-02','2017-08-02',60,9,1),(10,'habito10','ducharse','2017-08-02','2017-08-05',50,10,1),(11,'habito11','free diving','2017-09-17','2017-09-27',0,0,1);
/*!40000 ALTER TABLE `habito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'diana@es.com','dianacom','Diana','Diana','Ramon'),(2,'vero@es.com','verocom','Vero','Veronica','Gaufin'),(3,'rosa@es.com','rosacom','Rosa','Rosa','Martinez'),(4,'andrea@es.com','andreacom','Andrea','Andrea','Maldonado'),(5,'carlos@es.com','carloscom','Carlos','Carlos','Andrade'),(6,'carolina@es.com','carolinacom','Carolina','Carolina','Castro'),(7,'Maria@es.com','mariacom','Maria','Maria','Alvarado'),(8,'Ines@es.com','inescom','Ines','Ines','Perez');
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

-- Dump completed on 2017-08-07 14:15:57
