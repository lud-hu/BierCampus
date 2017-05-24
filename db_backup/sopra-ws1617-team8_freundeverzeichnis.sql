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
-- Table structure for table `freundeverzeichnis`
--

DROP TABLE IF EXISTS `freundeverzeichnis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `freundeverzeichnis` (
  `freunde_verzeichnis_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_a_userid` int(11) DEFAULT NULL,
  `user_b_userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`freunde_verzeichnis_id`),
  KEY `ix_freundeverzeichnis_user_a_userid` (`user_a_userid`),
  KEY `ix_freundeverzeichnis_user_b_userid` (`user_b_userid`),
  CONSTRAINT `fk_freundeverzeichnis_user_a_userid` FOREIGN KEY (`user_a_userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `fk_freundeverzeichnis_user_b_userid` FOREIGN KEY (`user_b_userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `freundeverzeichnis`
--

LOCK TABLES `freundeverzeichnis` WRITE;
/*!40000 ALTER TABLE `freundeverzeichnis` DISABLE KEYS */;
INSERT INTO `freundeverzeichnis` VALUES (1,37,38),(2,38,39),(3,39,40),(4,40,41),(5,41,42),(6,42,43),(7,43,44),(8,44,45),(9,45,46),(10,46,47),(11,47,48),(12,48,49),(13,49,50),(14,50,51),(15,51,52),(16,52,53),(17,53,54),(18,54,37),(71,40,38),(73,41,39),(80,37,65),(81,42,38),(83,49,42),(84,37,42),(85,51,42),(86,37,52),(88,37,74),(89,74,39),(91,37,80);
/*!40000 ALTER TABLE `freundeverzeichnis` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-26 21:59:43
