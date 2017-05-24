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
-- Table structure for table `play_evolutions`
--

DROP TABLE IF EXISTS `play_evolutions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `play_evolutions` (
  `id` int(11) NOT NULL,
  `hash` varchar(255) NOT NULL,
  `applied_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `apply_script` mediumtext,
  `revert_script` mediumtext,
  `state` varchar(255) DEFAULT NULL,
  `last_problem` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `play_evolutions`
--

LOCK TABLES `play_evolutions` WRITE;
/*!40000 ALTER TABLE `play_evolutions` DISABLE KEYS */;
INSERT INTO `play_evolutions` VALUES (1,'e9f2657135e567639e550f7bdffa2dfccdb397ab','2017-03-19 13:26:36','create table abzeichen (\nabzeichen_id                  integer auto_increment not null,\nname                          varchar(255),\nbeschreibung                  varchar(255),\nbild                          varchar(255),\nconstraint pk_abzeichen primary key (abzeichen_id)\n);\n\ncreate table freundeverzeichnis (\nfreunde_verzeichnis_id        integer auto_increment not null,\nuser_a_userid                 integer,\nuser_b_userid                 integer,\nconstraint pk_freundeverzeichnis primary key (freunde_verzeichnis_id)\n);\n\ncreate table level (\nlevel_id                      integer auto_increment not null,\nlevel_name                    varchar(255),\nerhaltene_efahrung            integer,\nerhaltenes_abzeichen_abzeichen_id integer,\nconstraint uq_level_erhaltenes_abzeichen_abzeichen_id unique (erhaltenes_abzeichen_abzeichen_id),\nconstraint pk_level primary key (level_id)\n);\n\ncreate table nachrichten (\nnachrichten_id                integer auto_increment not null,\nabsender                      integer,\nempfaenger                    integer,\ninhalt                        varchar(255),\ngelesen                       tinyint(1) default 0,\nconstraint pk_nachrichten primary key (nachrichten_id)\n);\n\ncreate table quizfrage (\nfragen_id                     integer auto_increment not null,\nname                          varchar(255),\nfrage                         varchar(255),\nfalsche_antwort1              varchar(255),\nfalsche_antwort2              varchar(255),\nfalsche_antwort3              varchar(255),\nrichtige_antwort              varchar(255),\naufloesung                    varchar(255),\nconstraint pk_quizfrage primary key (fragen_id)\n);\n\ncreate table rang (\nrang_id                       integer auto_increment not null,\nbezeichnung                   varchar(255),\nxp_grenze                     integer,\nconstraint pk_rang primary key (rang_id)\n);\n\ncreate table schritteverzeichnis (\nschritt_verzeichnis_id        integer auto_increment not null,\nschritt_id                    integer,\nlevel_level_id                integer,\nzutat_zutat_id                integer,\nquiz_frage_fragen_id          integer,\nconstraint pk_schritteverzeichnis primary key (schritt_verzeichnis_id)\n);\n\ncreate table user (\nuserid                        integer auto_increment not null,\nname                          varchar(255),\nemail                         varchar(255),\npasswort                      varchar(255),\nerfahrung                     integer,\npromille                      decimal(2,1),\ngewaehltes_abzeichen_abzeichen_id integer,\nbestandenes_level_level_id    integer,\nrang_rang_id                  integer,\nlast_login                    datetime(6),\nconstraint pk_user primary key (userid)\n);\n\ncreate table zutat (\nzutat_id                      integer auto_increment not null,\nbeschreibung                  varchar(255),\nname                          varchar(255),\nbild                          varchar(255),\nconstraint pk_zutat primary key (zutat_id)\n);\n\nalter table freundeverzeichnis add constraint fk_freundeverzeichnis_user_a_userid foreign key (user_a_userid) references user (userid) on delete restrict on update restrict;\ncreate index ix_freundeverzeichnis_user_a_userid on freundeverzeichnis (user_a_userid);\n\nalter table freundeverzeichnis add constraint fk_freundeverzeichnis_user_b_userid foreign key (user_b_userid) references user (userid) on delete restrict on update restrict;\ncreate index ix_freundeverzeichnis_user_b_userid on freundeverzeichnis (user_b_userid);\n\nalter table level add constraint fk_level_erhaltenes_abzeichen_abzeichen_id foreign key (erhaltenes_abzeichen_abzeichen_id) references abzeichen (abzeichen_id) on delete restrict on update restrict;\n\nalter table schritteverzeichnis add constraint fk_schritteverzeichnis_level_level_id foreign key (level_level_id) references level (level_id) on delete restrict on update restrict;\ncreate index ix_schritteverzeichnis_level_level_id on schritteverzeichnis (level_level_id);\n\nalter table schritteverzeichnis add constraint fk_schritteverzeichnis_zutat_zutat_id foreign key (zutat_zutat_id) references zutat (zutat_id) on delete restrict on update restrict;\ncreate index ix_schritteverzeichnis_zutat_zutat_id on schritteverzeichnis (zutat_zutat_id);\n\nalter table schritteverzeichnis add constraint fk_schritteverzeichnis_quiz_frage_fragen_id foreign key (quiz_frage_fragen_id) references quizfrage (fragen_id) on delete restrict on update restrict;\ncreate index ix_schritteverzeichnis_quiz_frage_fragen_id on schritteverzeichnis (quiz_frage_fragen_id);\n\nalter table user add constraint fk_user_gewaehltes_abzeichen_abzeichen_id foreign key (gewaehltes_abzeichen_abzeichen_id) references abzeichen (abzeichen_id) on delete restrict on update restrict;\ncreate index ix_user_gewaehltes_abzeichen_abzeichen_id on user (gewaehltes_abzeichen_abzeichen_id);\n\nalter table user add constraint fk_user_bestandenes_level_level_id foreign key (bestandenes_level_level_id) references level (level_id) on delete restrict on update restrict;\ncreate index ix_user_bestandenes_level_level_id on user (bestandenes_level_level_id);\n\nalter table user add constraint fk_user_rang_rang_id foreign key (rang_rang_id) references rang (rang_id) on delete restrict on update restrict;\ncreate index ix_user_rang_rang_id on user (rang_rang_id);','alter table freundeverzeichnis drop foreign key fk_freundeverzeichnis_user_a_userid;\ndrop index ix_freundeverzeichnis_user_a_userid on freundeverzeichnis;\n\nalter table freundeverzeichnis drop foreign key fk_freundeverzeichnis_user_b_userid;\ndrop index ix_freundeverzeichnis_user_b_userid on freundeverzeichnis;\n\nalter table level drop foreign key fk_level_erhaltenes_abzeichen_abzeichen_id;\n\nalter table schritteverzeichnis drop foreign key fk_schritteverzeichnis_level_level_id;\ndrop index ix_schritteverzeichnis_level_level_id on schritteverzeichnis;\n\nalter table schritteverzeichnis drop foreign key fk_schritteverzeichnis_zutat_zutat_id;\ndrop index ix_schritteverzeichnis_zutat_zutat_id on schritteverzeichnis;\n\nalter table schritteverzeichnis drop foreign key fk_schritteverzeichnis_quiz_frage_fragen_id;\ndrop index ix_schritteverzeichnis_quiz_frage_fragen_id on schritteverzeichnis;\n\nalter table user drop foreign key fk_user_gewaehltes_abzeichen_abzeichen_id;\ndrop index ix_user_gewaehltes_abzeichen_abzeichen_id on user;\n\nalter table user drop foreign key fk_user_bestandenes_level_level_id;\ndrop index ix_user_bestandenes_level_level_id on user;\n\nalter table user drop foreign key fk_user_rang_rang_id;\ndrop index ix_user_rang_rang_id on user;\n\ndrop table if exists abzeichen;\n\ndrop table if exists freundeverzeichnis;\n\ndrop table if exists level;\n\ndrop table if exists nachrichten;\n\ndrop table if exists quizfrage;\n\ndrop table if exists rang;\n\ndrop table if exists schritteverzeichnis;\n\ndrop table if exists user;\n\ndrop table if exists zutat;','applied','');
/*!40000 ALTER TABLE `play_evolutions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-26 21:59:40
