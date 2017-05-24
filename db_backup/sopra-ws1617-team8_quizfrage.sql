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
-- Table structure for table `quizfrage`
--

DROP TABLE IF EXISTS `quizfrage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quizfrage` (
  `fragen_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `frage` varchar(255) DEFAULT NULL,
  `falsche_antwort1` varchar(255) DEFAULT NULL,
  `falsche_antwort2` varchar(255) DEFAULT NULL,
  `falsche_antwort3` varchar(255) DEFAULT NULL,
  `richtige_antwort` varchar(255) DEFAULT NULL,
  `aufloesung` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fragen_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quizfrage`
--

LOCK TABLES `quizfrage` WRITE;
/*!40000 ALTER TABLE `quizfrage` DISABLE KEYS */;
INSERT INTO `quizfrage` VALUES (15,'Wartezeit','Wie lange müsstest du nun warten?','3 Tage','Bis im Kessel die ersten Pflanzen gewachsen sind.','24 Stunden','1 Stunde','Richtig, damit aus Wasser und Malz Maische entsteht, müssen beide Zutaten eine Stunde lang bei ca. 70°C gehalten werden.'),(16,'Getreidesorten','Welches Getreide darf NICHT ins deutsche Bier?','Gerste','Weizen','Roggen','Mais','Richtig. Laut dem Reinheitsgebot von 1516 dürfte sogar nur Gerste ins Bier. Weizen und Roggen waren den Bäckern vorbehalten.'),(17,'2. Zeitsprung','Wie lange muss nun gewartet werden?','1 Minute','24 Stunden','2 Tage','1 Stunde','Richtig. Der Prozess des sogenannten Hopfenkochens dauert meist ca. 1 Stunde.'),(18,'3. Wartezeit','Wie lange muss das Bier nun Gären?','Bis der Durst zu groß wird.','Bis zum nächsten Vollmond.','2-3 Stunden.','Je nach gewünschter Biersorte 2-3 Wochen.','Richtg. Durst und Vollmond sind natürlich Quatsch und 2-3 Stunden auch viel zu kurz. Meistens benötigt die Hefe 8-12 Stunden um überhaupt erstmal in die Gänge zu kommen.'),(20,'Pils - Wasserhärte','Was ist bei dem Brauen von Pils bei der Wasserauswahl besonders zu beachten?','Dass es genügend Kalk enthält.','Dass es bereits mit Kohlensäure versetzt wurde.','Dass es salzig ist.','Dass das Wasser weich genug ist.','Bei Pils achten Brauereien besonders gut darauf nur weiches Wasser zu verwenden, da es geschmacklich eine große Rolle spielt.'),(21,'1. Wartezeit','Wie lange muss der Braumeister nun warten bis Maische entstanden ist?','5 Minuten','10 Minuten','2 Stunden','1 Stunde','Richtig. Abermals muss man eine gute Stunde warten, bis die Stärke des Malzes in Zucker umgewandelt wurde.'),(22,'Hopfenfrage','Mit welchen Pflanzen ist Hopfen am nähesten verwandt?','Tomaten','Nadelbäume','Birnen','Cannabis','Hopfen gehört genau wie Cannabis zur Familie der Hanfgewächse.'),(23,'2. Wartezeit','Wie lange soll die Bierwürze nun vor sich hin kochen?','2 Tage','7 Stunden','5 Stunden','1 Stunde','Abermals genügt eine Stunde Hopfenkochen völlig aus um die gewünschten Ergebnisse zu erzielen.'),(24,'Pils als Untergäriges Bier','Was bedeutet untergäriges Bier?','Es muss unter der Erde gebraut werden.','Es sollte in einem noch gärenden Zustand konsumiert werden.','Bei der Gärung muss permanent verrührt werden.','Die Hefe gärt nicht oben auf dem Bier sondern unter der Oberfläche.','Richtig. Da bei untergärigen Bieren die Hefe bei kälteren Temperaturen (8-12°C) zugegeben wird, schwimmt sie nicht auf der Oberfläche sondern darunter.'),(25,'Deckelfrage','Warum wird der Kessel verschlossen?','Das Prinzip gleicht dem eines Schnellkochtopfes.','Damit es weniger Sauerei gibt.','Um das Bier vor Licht zu schützen.','Damit sich Kohlensäure im Bier lösen kann.','Durch den höheren Druck und das durch die Gärung austretende CO2 entsteht Kohlensäure. Doch Vorsicht ist geboten. Wird der Deckel zu früh geschlossen, kann das System explodieren.');
/*!40000 ALTER TABLE `quizfrage` ENABLE KEYS */;
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
