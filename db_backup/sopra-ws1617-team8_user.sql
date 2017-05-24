-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 132.187.8.167    Database: sopra-ws1617-team8
-- ------------------------------------------------------
-- Server version	5.7.17

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `passwort` varchar(255) NOT NULL,
  `erfahrung` int(11) DEFAULT '0',
  `promille` decimal(2,1) DEFAULT '0.0',
  `gewaehltes_abzeichen_abzeichen_id` int(11) DEFAULT NULL,
  `bestandenes_level_level_id` int(11) DEFAULT NULL,
  `rang_rang_id` int(11) DEFAULT NULL,
  `last_login` datetime(6) DEFAULT '1970-01-01 00:00:01.000000',
  PRIMARY KEY (`userid`),
  KEY `ix_user_gewaehltes_abzeichen_abzeichen_id` (`gewaehltes_abzeichen_abzeichen_id`),
  KEY `ix_user_bestandenes_level_level_id` (`bestandenes_level_level_id`),
  KEY `ix_user_rang_rang_id` (`rang_rang_id`),
  CONSTRAINT `fk_user_bestandenes_level_level_id` FOREIGN KEY (`bestandenes_level_level_id`) REFERENCES `level` (`level_id`),
  CONSTRAINT `fk_user_gewaehltes_abzeichen_abzeichen_id` FOREIGN KEY (`gewaehltes_abzeichen_abzeichen_id`) REFERENCES `abzeichen` (`abzeichen_id`),
  CONSTRAINT `fk_user_rang_rang_id` FOREIGN KEY (`rang_rang_id`) REFERENCES `rang` (`rang_id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (37,'Harald','harald@wvv.de','123456',1,1.1,1,1,1,'2017-03-25 10:34:15.156000'),(38,'Hans','hansi@hinterseer.at','123456',80,0.0,1,1,1,'2017-03-25 09:01:55.218000'),(39,'Erwin','erwin@web.de','123456',226,8.9,3,4,2,'2017-03-26 08:15:47.007000'),(40,'Egon','egon@web.de','123456',75,5.6,2,3,1,'1970-01-01 00:00:01.000000'),(41,'Beercules','Beer@cules.net','123456',135,3.0,2,3,1,'2017-03-23 11:01:15.601000'),(42,'Donald','donald@whitehouse.gov','123456',35,9.2,2,3,1,'2017-03-23 13:50:00.790000'),(43,'Petra','Petra@web.de','123456',11,1.0,1,1,1,'1970-01-01 00:00:01.000000'),(44,'Sieglinde','Sieglinde@web.de','123456',15,0.2,1,2,1,'1970-01-01 00:00:01.000000'),(45,'Angela','Angela@web.de','123456',8,0.3,2,3,1,'1970-01-01 00:00:01.000000'),(46,'Horst','Horst@web.de','123456',45,0.0,1,1,1,'2017-03-24 11:15:26.944000'),(47,'Eva','Eva@web.de','123456',12,0.0,2,2,1,'2017-03-20 13:27:54.836000'),(48,'Benjamin','Benjamin@web.de','123456',14,6.2,2,1,1,'1970-01-01 00:00:01.000000'),(49,'Silvester','Silvester@web.de','123456',13,3.2,2,2,1,'1970-01-01 00:00:01.000000'),(50,'Winnibald','Winnibald@web.de','123456',25,1.0,2,2,1,'1970-01-01 00:00:01.000000'),(51,'Wunibald','Wunibald@web.de','123456',10,2.0,1,1,1,'2017-03-19 14:28:53.241000'),(52,'Peter','Peter@web.de','123456',116,0.0,1,1,1,'2017-03-24 10:07:22.194000'),(53,'Friedrich','Friedrich@web.de','123456',6,0.2,2,2,1,'1970-01-01 00:00:01.000000'),(54,'Amadeus','Amadeus@web.de','123456',16,0.0,1,1,1,'2017-03-26 08:07:05.415000'),(65,'Mary','mary@bloody.com','123456',7,0.0,1,1,NULL,'2017-03-22 21:19:11.069000'),(74,'Casper','melchior@balthasar.de','Miau',203,8.4,4,4,NULL,'2017-03-24 21:45:16.434000'),(75,'Johannes','j@hannes.de','123456',20,2.0,NULL,2,NULL,'2017-03-25 09:59:04.489000'),(80,'Günther','günther.jauch@aol.com','ThomasGottschalk',12,1.1,1,1,NULL,'2017-03-25 12:40:33.425000'),(81,'John','john@doe.com','123456',10,1.0,NULL,1,NULL,'2017-03-26 08:09:19.372000');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-26 21:59:44
