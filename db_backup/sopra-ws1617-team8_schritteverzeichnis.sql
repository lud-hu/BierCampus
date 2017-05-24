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
-- Table structure for table `schritteverzeichnis`
--

DROP TABLE IF EXISTS `schritteverzeichnis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schritteverzeichnis` (
  `schritt_verzeichnis_id` int(11) NOT NULL AUTO_INCREMENT,
  `schritt_id` int(11) DEFAULT NULL,
  `level_level_id` int(11) DEFAULT NULL,
  `zutat_zutat_id` int(11) DEFAULT NULL,
  `quiz_frage_fragen_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`schritt_verzeichnis_id`),
  KEY `ix_schritteverzeichnis_level_level_id` (`level_level_id`),
  KEY `ix_schritteverzeichnis_zutat_zutat_id` (`zutat_zutat_id`),
  KEY `ix_schritteverzeichnis_quiz_frage_fragen_id` (`quiz_frage_fragen_id`),
  CONSTRAINT `fk_schritteverzeichnis_level_level_id` FOREIGN KEY (`level_level_id`) REFERENCES `level` (`level_id`),
  CONSTRAINT `fk_schritteverzeichnis_quiz_frage_fragen_id` FOREIGN KEY (`quiz_frage_fragen_id`) REFERENCES `quizfrage` (`fragen_id`),
  CONSTRAINT `fk_schritteverzeichnis_zutat_zutat_id` FOREIGN KEY (`zutat_zutat_id`) REFERENCES `zutat` (`zutat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=277 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schritteverzeichnis`
--

LOCK TABLES `schritteverzeichnis` WRITE;
/*!40000 ALTER TABLE `schritteverzeichnis` DISABLE KEYS */;
INSERT INTO `schritteverzeichnis` VALUES (167,1,1,27,NULL),(168,2,1,28,NULL),(169,3,1,29,NULL),(170,4,1,NULL,15),(171,5,1,NULL,16),(172,6,1,34,NULL),(173,7,1,33,NULL),(174,8,1,NULL,17),(176,9,1,36,NULL),(177,10,1,37,NULL),(178,11,1,NULL,18),(262,1,2,38,NULL),(263,2,2,NULL,20),(264,3,2,39,NULL),(265,4,2,40,NULL),(266,5,2,NULL,21),(267,6,2,NULL,24),(268,7,2,42,NULL),(269,8,2,43,NULL),(270,9,2,44,NULL),(271,10,2,NULL,22),(272,11,2,NULL,23),(273,12,2,48,NULL),(274,13,2,49,NULL),(275,14,2,46,NULL),(276,15,2,NULL,25);
/*!40000 ALTER TABLE `schritteverzeichnis` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-26 21:59:46
