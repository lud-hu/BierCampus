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
-- Table structure for table `zutat`
--

DROP TABLE IF EXISTS `zutat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zutat` (
  `zutat_id` int(11) NOT NULL AUTO_INCREMENT,
  `beschreibung` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `bild` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`zutat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zutat`
--

LOCK TABLES `zutat` WRITE;
/*!40000 ALTER TABLE `zutat` DISABLE KEYS */;
INSERT INTO `zutat` VALUES (27,'Keine Getränke ohne Wasser. Deshalb dient beim Brauen auch Wasser als Basis.','Wasser','Wasser.png'),(28,'Malz wird aus Getreide gemacht und dient als Zuckerquelle für die Alkoholbildung. Zunächst muss jedoch in Wasser die im Malz enthaltene Stärke bei mittleren Temperaturen in Zucker umgewandelt werden. Dieser Vorgang dauert ca. eine Stunde.','Malz','Malz.png'),(29,'Das Thermometer erhöht die Temperatur in dem Kessel. Wirf es dazu einfach zu den anderen Zutaten in die Öffnung. Dieses Thermometer erhöht die Temperatur auf 69°C','69°C','Temperatur69.png'),(33,'Hopfen verleiht dem Bier seinen charakteristischen, leicht bittren Geschmack. Außerdem macht er das Bier lange haltbar. Nach der Gewinnung von Bierwürze aus Malz und Wasser wird Hopfen in die kochende Würze gegeben.','Hopfen','Hopfen.png'),(34,'Dieses Thermometer erhöht die Temperatur auf über 100°C und sorgt so für ein wallendes Kochen.','100°C','Temperatur102.png'),(36,'Dieses Thermometer lässt die Temperatur auf Zimmertemperatur herabkühlen..','20°C','Temperatur19.png'),(37,'Die Hefe ist für den Umbau von Zucker in Alkohol zuständig. Beachte jedoch, dass Hefen Lebewesen sind. Lass sie nicht heiß werden.','Hefe','Hefe.png'),(38,'Wasser ist nach wie vor ein elementarer Bestandteil bei dem Brauprozess. Bei Pils ist die Wasserhärte (bzw. Wasserweiche) besonders wichtig.','Wasser','Wasser.png'),(39,'Für Pils wird das sogenannte Pilsner Malz verwendet. Dies wird ausschließlich aus Gerste gewonnen und steckt in zahlreichen hellen Bieren.','Malz','Malz.png'),(40,'Erhöht die Temperatur im Kessel auf 69°C.','69°C','Temperatur69.png'),(42,'Wird benutzt um die festen Bestandteile aus der Maische herauszufiltern. Dieser Prozess wird auch Läutern genannt und ist wichtig um aus Getreidebrei ein Getränk entstehen zu lassen.','Sieb','Sieb.png'),(43,'Erhöht die Temperatur im Kessel auf über 100°C','100°C','Temperatur102.png'),(44,'Bevor man sich durch das Reinheitsgebot auf Hopfen als eine der Grundzutaten im Bier einigte, verwendeten Braumeister auch alle möglichen Arten von heimischen Kräutern um dem Bier seinen Geschmack und die Haltbarkeit zu geben.','Hopfen','Hopfen.png'),(46,'Durch anlegen des Deckels steigt durch freiwerdendes CO2 der Druck im Braukessel','Deckel','Deckel.png'),(48,'Die Temperatur wird auf unter 12°C herabgesenkt.','12°C','Temperatur12.png'),(49,'Hefe ist nach wie vor Hitzeempfindlich. Pass auf!','Hefe','Hefe.png');
/*!40000 ALTER TABLE `zutat` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-26 21:59:45
