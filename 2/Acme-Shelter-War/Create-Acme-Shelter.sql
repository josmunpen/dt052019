start transaction;

drop database if exists `Acme-Shelter`;
create database `Acme-Shelter`;

use `Acme-Shelter`;

drop user 'acme-user'@'%';
drop user 'acme-manager'@'%';

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete on `Acme-Shelter`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, create temporary tables, lock tables, 
create view, create routine, alter routine, execute, trigger, show view on `Acme-Shelter`.* to 'acme-manager'@'%';



-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Shelter
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `actor_boxes`
--

DROP TABLE IF EXISTS `actor_boxes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_boxes` (
  `actor` int(11) NOT NULL,
  `boxes` int(11) NOT NULL,
  UNIQUE KEY `UK_6n6psqivvjho155qcf9kjvv1h` (`boxes`),
  CONSTRAINT `FK_6n6psqivvjho155qcf9kjvv1h` FOREIGN KEY (`boxes`) REFERENCES `box` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_boxes`
--

LOCK TABLES `actor_boxes` WRITE;
/*!40000 ALTER TABLE `actor_boxes` DISABLE KEYS */;
INSERT INTO `actor_boxes` VALUES (533,570),(533,571),(533,572),(533,573),(534,574),(534,575),(534,576),(534,577),(538,578),(538,579),(538,580),(538,581),(602,582),(602,583),(602,584),(602,585),(603,586),(603,587),(603,588),(603,589),(541,590),(541,591),(541,592),(541,593),(544,594),(544,595),(544,596),(544,597);
/*!40000 ALTER TABLE `actor_boxes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_social_profiles`
--

DROP TABLE IF EXISTS `actor_social_profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_social_profiles` (
  `actor` int(11) NOT NULL,
  `social_profiles` int(11) NOT NULL,
  UNIQUE KEY `UK_4suhrykpl9af1ubs85ycbyt6q` (`social_profiles`),
  CONSTRAINT `FK_4suhrykpl9af1ubs85ycbyt6q` FOREIGN KEY (`social_profiles`) REFERENCES `social_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_social_profiles`
--

LOCK TABLES `actor_social_profiles` WRITE;
/*!40000 ALTER TABLE `actor_social_profiles` DISABLE KEYS */;
INSERT INTO `actor_social_profiles` VALUES (534,535),(534,536),(534,537),(538,539),(538,540),(603,599),(603,600),(602,601);
/*!40000 ALTER TABLE `actor_social_profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expiration_month` int(11) DEFAULT NULL,
  `expiration_year` int(11) DEFAULT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `make_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7ohwsa2usmvu0yxb44je2lge` (`user_account`),
  CONSTRAINT `FK_7ohwsa2usmvu0yxb44je2lge` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (533,0,'Address 1',583,'Me gustan muchísimo los gatos!','admin@',5,2025,'Admin Admin','VISA','Administrator','4083602396731593','+34 676676676','http://www.foto.com','Admin',525);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adopter`
--

DROP TABLE IF EXISTS `adopter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adopter` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expiration_month` int(11) DEFAULT NULL,
  `expiration_year` int(11) DEFAULT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `make_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  `finder` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tmy6vdrtjvn9n35n2x61qcf71` (`finder`),
  KEY `FK_4ps7xpbi6rx0m2l424xgncydf` (`user_account`),
  CONSTRAINT `FK_4ps7xpbi6rx0m2l424xgncydf` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FK_tmy6vdrtjvn9n35n2x61qcf71` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adopter`
--

LOCK TABLES `adopter` WRITE;
/*!40000 ALTER TABLE `adopter` DISABLE KEYS */;
INSERT INTO `adopter` VALUES (602,0,'c/Trigo, nº10, Los Palacios y Villafranca',188,'Adoro las iguanas y los coches','adopter1@adopter1.com',4,2022,'Adopter Juan Antonio','VISA','Juan Antonio','4128837079312553','+34 611111111','http://www.foto.com','De la Cruz',528,568),(603,0,'c/Torre, nº10, Sevilla',188,'Mis hobbies favoritos son la programación y la pintura','adopter2@adopter2.com',4,2022,'Adopter Alba','VISA','Alba','4128837079312553','+34 611111111','http://www.foto.com','Burdallo',529,569);
/*!40000 ALTER TABLE `adopter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `reject_cause` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `adopter` int(11) NOT NULL,
  `pet` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ju3cg17gj4xk28qqdufqeolb3` (`adopter`),
  KEY `FK_8i211hxb3kumf9yv6skd42ksk` (`pet`),
  CONSTRAINT `FK_8i211hxb3kumf9yv6skd42ksk` FOREIGN KEY (`pet`) REFERENCES `pet` (`id`),
  CONSTRAINT `FK_ju3cg17gj4xk28qqdufqeolb3` FOREIGN KEY (`adopter`) REFERENCES `adopter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (611,0,'Tengo otros gatitos y una casa grande.','2019-02-02 21:00:00',NULL,'ACCEPTED',602,604),(612,0,'Tengo varios perros y no me queda mucho espacio, pero mi hija está antojada de un gatito.','2019-02-02 21:00:00','No me convence que quieras un animal por un antojo.','REJECTED',603,604),(613,0,'Tengo un campo donde el galgo podrá estar libre y correr siempre que queira.','2019-01-02 21:00:00',NULL,'PENDING',603,610),(614,0,'Tengo un montón de perros y es de los pocos que me falta','2019-01-02 21:00:00',NULL,'PENDING',603,608),(615,0,'Tengo varios gatos y una casa grande donde Guantes se encontrará como en casa.','2019-01-02 21:00:00',NULL,'PENDING',602,609);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_photos`
--

DROP TABLE IF EXISTS `application_photos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_photos` (
  `application` int(11) NOT NULL,
  `photos` varchar(255) DEFAULT NULL,
  KEY `FK_lfga36u74vsl9uyrehn5tx1hl` (`application`),
  CONSTRAINT `FK_lfga36u74vsl9uyrehn5tx1hl` FOREIGN KEY (`application`) REFERENCES `application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_photos`
--

LOCK TABLES `application_photos` WRITE;
/*!40000 ALTER TABLE `application_photos` DISABLE KEYS */;
INSERT INTO `application_photos` VALUES (611,'https://www.pinehallbrick.com/wp-content/uploads/2016/09/casa-grande_white-mortar-bbfarm-1-.jpg');
/*!40000 ALTER TABLE `application_photos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `box`
--

DROP TABLE IF EXISTS `box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `box` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `predefined` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `box`
--

LOCK TABLES `box` WRITE;
/*!40000 ALTER TABLE `box` DISABLE KEYS */;
INSERT INTO `box` VALUES (570,0,'in box',''),(571,0,'notification box',''),(572,0,'out box',''),(573,0,'trash box',''),(574,0,'in box',''),(575,0,'notification box',''),(576,0,'out box',''),(577,0,'trash box',''),(578,0,'in box',''),(579,0,'notification box',''),(580,0,'out box',''),(581,0,'trash box',''),(582,0,'in box',''),(583,0,'notification box',''),(584,0,'out box',''),(585,0,'trash box',''),(586,0,'in box',''),(587,0,'notification box',''),(588,0,'out box',''),(589,0,'trash box',''),(590,0,'in box',''),(591,0,'notification box',''),(592,0,'out box',''),(593,0,'trash box',''),(594,0,'in box',''),(595,0,'notification box',''),(596,0,'out box',''),(597,0,'trash box','');
/*!40000 ALTER TABLE `box` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `box_messages`
--

DROP TABLE IF EXISTS `box_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `box_messages` (
  `box` int(11) NOT NULL,
  `messages` int(11) NOT NULL,
  KEY `FK_acfjrqu1jeixjmv14c0386o0s` (`messages`),
  KEY `FK_e6boieojekgfg919on0dci4na` (`box`),
  CONSTRAINT `FK_e6boieojekgfg919on0dci4na` FOREIGN KEY (`box`) REFERENCES `box` (`id`),
  CONSTRAINT `FK_acfjrqu1jeixjmv14c0386o0s` FOREIGN KEY (`messages`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `box_messages`
--

LOCK TABLES `box_messages` WRITE;
/*!40000 ALTER TABLE `box_messages` DISABLE KEYS */;
INSERT INTO `box_messages` VALUES (572,598),(590,598);
/*!40000 ALTER TABLE `box_messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `comment_text` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `score` int(11) NOT NULL,
  `adopter` int(11) DEFAULT NULL,
  `pet_owner` int(11) DEFAULT NULL,
  `tip` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bvbadh221brvpv4bj5awlqg7n` (`adopter`),
  KEY `FK_oymgm9whwitro3y4cv9qasbq9` (`pet_owner`),
  KEY `FK_ie6s1urj4o8ccaq6x4pj7b383` (`tip`),
  CONSTRAINT `FK_ie6s1urj4o8ccaq6x4pj7b383` FOREIGN KEY (`tip`) REFERENCES `tip` (`id`),
  CONSTRAINT `FK_bvbadh221brvpv4bj5awlqg7n` FOREIGN KEY (`adopter`) REFERENCES `adopter` (`id`),
  CONSTRAINT `FK_oymgm9whwitro3y4cv9qasbq9` FOREIGN KEY (`pet_owner`) REFERENCES `pet_owner` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (567,1,'¡Muy útil!','2019-02-02 23:00:00',4,602,NULL,566);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum`
--

DROP TABLE IF EXISTS `curriculum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `professional_careers` varchar(255) DEFAULT NULL,
  `qualifications` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum`
--

LOCK TABLES `curriculum` WRITE;
/*!40000 ALTER TABLE `curriculum` DISABLE KEYS */;
INSERT INTO `curriculum` VALUES (542,0,NULL,'Licenciado en veterinaria en la Universidad de Sevilla en 2010.'),(543,0,NULL,'Máster en veterinaria de animales domésticos'),(545,0,NULL,'Licenciado en veterinaria en la Universidad de Sevilla en 2011. Especializado en gatos.');
/*!40000 ALTER TABLE `curriculum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation`
--

DROP TABLE IF EXISTS `customisation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banner_url` varchar(255) DEFAULT NULL,
  `finder_duration` int(11) DEFAULT NULL,
  `flat_rate` int(11) DEFAULT NULL,
  `phone_number_code` varchar(255) DEFAULT NULL,
  `results_number` int(11) DEFAULT NULL,
  `system_name` varchar(255) DEFAULT NULL,
  `vat` int(11) DEFAULT NULL,
  `welcome_message_eng` varchar(255) DEFAULT NULL,
  `welcome_message_esp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation`
--

LOCK TABLES `customisation` WRITE;
/*!40000 ALTER TABLE `customisation` DISABLE KEYS */;
INSERT INTO `customisation` VALUES (532,0,'http://oi64.tinypic.com/23r5owj.jpg',1,10,'+34',10,'Acme Shelter',21,'Welcome to Acme Shelter We\'re pet lovers favourite place!','¡Bienvenidos a Acme Shelter! ¡Somos el mercado de trabajo favorito de los amantes de las mascotas!');
/*!40000 ALTER TABLE `customisation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder`
--

DROP TABLE IF EXISTS `finder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder`
--

LOCK TABLES `finder` WRITE;
/*!40000 ALTER TABLE `finder` DISABLE KEYS */;
INSERT INTO `finder` VALUES (568,0,NULL,'','2019-01-01 00:00:00','',''),(569,0,NULL,'','2019-01-01 00:00:00','','');
/*!40000 ALTER TABLE `finder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder_pets`
--

DROP TABLE IF EXISTS `finder_pets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder_pets` (
  `finder` int(11) NOT NULL,
  `pets` int(11) NOT NULL,
  KEY `FK_ev7pjw03fo2lfg61v2hu46fu9` (`pets`),
  KEY `FK_o3r7qsx5mdqxtq26ic4concd7` (`finder`),
  CONSTRAINT `FK_o3r7qsx5mdqxtq26ic4concd7` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`),
  CONSTRAINT `FK_ev7pjw03fo2lfg61v2hu46fu9` FOREIGN KEY (`pets`) REFERENCES `pet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder_pets`
--

LOCK TABLES `finder_pets` WRITE;
/*!40000 ALTER TABLE `finder_pets` DISABLE KEYS */;
/*!40000 ALTER TABLE `finder_pets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('domain_entity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_moment` date DEFAULT NULL,
  `start_moment` date DEFAULT NULL,
  `actor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES (546,0,'Nació en el refugio.',NULL,'2019-02-16',534),(547,0,'Lo recogimos en el refugio.',NULL,'2019-03-12',534),(548,0,'Lo recogimos en el refugio.',NULL,'2015-03-12',534),(549,0,'Lo encontramos abandonado.',NULL,'2011-03-12',538),(550,0,'Lo encontramos abandonado.',NULL,'2017-03-12',534),(551,0,'Lo encontramos abandonado.',NULL,'2015-03-12',538),(552,0,'Lo encontramos abandonado.',NULL,'2017-03-12',538);
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_check_up`
--

DROP TABLE IF EXISTS `medical_check_up`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medical_check_up` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `state_of_health` varchar(255) DEFAULT NULL,
  `pet` int(11) NOT NULL,
  `veterinarian` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sscuv2lyuu8isu7rmxuw0i8qb` (`pet`),
  KEY `FK_qrkd3xephdwlc4vcx2w7h8rny` (`veterinarian`),
  CONSTRAINT `FK_qrkd3xephdwlc4vcx2w7h8rny` FOREIGN KEY (`veterinarian`) REFERENCES `veterinarian` (`id`),
  CONSTRAINT `FK_sscuv2lyuu8isu7rmxuw0i8qb` FOREIGN KEY (`pet`) REFERENCES `pet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_check_up`
--

LOCK TABLES `medical_check_up` WRITE;
/*!40000 ALTER TABLE `medical_check_up` DISABLE KEYS */;
INSERT INTO `medical_check_up` VALUES (616,0,'Gata sana en general','2019-02-01 23:00:00','GOOD',604,541);
/*!40000 ALTER TABLE `medical_check_up` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` longtext,
  `broadcast` bit(1) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `sender` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (598,0,'Prueba','\0','2018-10-10 00:00:00','HIGH','Prueba','Prueba',533);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_recipients`
--

DROP TABLE IF EXISTS `message_recipients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_recipients` (
  `message` int(11) NOT NULL,
  `recipients` int(11) NOT NULL,
  KEY `FK_1odmg2n3n487tvhuxx5oyyya2` (`message`),
  CONSTRAINT `FK_1odmg2n3n487tvhuxx5oyyya2` FOREIGN KEY (`message`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_recipients`
--

LOCK TABLES `message_recipients` WRITE;
/*!40000 ALTER TABLE `message_recipients` DISABLE KEYS */;
INSERT INTO `message_recipients` VALUES (598,541);
/*!40000 ALTER TABLE `message_recipients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet`
--

DROP TABLE IF EXISTS `pet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pet` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `care_requirements` varchar(255) DEFAULT NULL,
  `diet_requirements` varchar(255) DEFAULT NULL,
  `family_requirements` varchar(255) DEFAULT NULL,
  `identifier` varchar(255) DEFAULT NULL,
  `management_cost` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nature` varchar(255) DEFAULT NULL,
  `pedigree` varchar(255) DEFAULT NULL,
  `pets_requirements` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `pet_owner` int(11) NOT NULL,
  `pet_type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dqhihrr6w0c26g1ibj7ihf2j4` (`pet_owner`),
  KEY `FK_k6k9lnq83xmnkymg7xd4wtxtq` (`pet_type`),
  CONSTRAINT `FK_k6k9lnq83xmnkymg7xd4wtxtq` FOREIGN KEY (`pet_type`) REFERENCES `pet_type` (`id`),
  CONSTRAINT `FK_dqhihrr6w0c26g1ibj7ihf2j4` FOREIGN KEY (`pet_owner`) REFERENCES `pet_owner` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet`
--

LOCK TABLES `pet` WRITE;
/*!40000 ALTER TABLE `pet` DISABLE KEYS */;
INSERT INTO `pet` VALUES (604,0,'c/Aurora, 60, Los Palacios',2,'Cepillado una vez a la semana','No puede comer hierba. Adora el pescado','Es muy sociable','Q1W23',1,'Leia','Tranquila','Tranquila','Se lleva bien con otros animales','FEMALE','LOW',534,559),(605,0,'c/Trigo, 60, Los Palacios',3,NULL,'No puede comer hierba.',NULL,'Q1W22',1,'Garfield','Muy cariñoso.',NULL,'Se lleva bien con otros animales','MALE','MEDIUM',534,559),(606,0,'c/Harina, 60, Los Palacios',4,'Necesita hacer ejercicio una vez al día',NULL,NULL,'Q1W21',4,'Rambo','Activo.',NULL,'Se lleva bien con otros animales','MALE','MEDIUM',538,555),(607,0,'c/Harina, 60, Los Palacios',1,NULL,'Solo puede comer pienso.',NULL,'Q1W25',4,'Luke','Cariñoso','Abuelos y padres bulldogs con pedigree.',NULL,'MALE','LOW',538,556),(608,0,'c/Maicena, 60, El hierro',5,'Requiere cepillado un par de veces en semana','¡Come de todo!',NULL,'Q1W26',75,'Nora','Afable',NULL,'Preferible que esté sola en casa, es territorial.','FEMALE','LOW',534,562),(609,0,'c/Megalodón, nº30, Barcelona',6,'Requiere cepillado una vez al día.','Si come plantas, vomita',NULL,'Q1W36',5,'Guantes','Altivo','Sus padres fueron angoras con pedigrí',NULL,'MALE','MEDIUM',538,565),(610,0,'c/Megalodón, nº30, Barcelona',8,'Adora estar con otros animales.',NULL,'Requiere mucho cariño.','Q2W36',1,'Flaco','Cariñoso',NULL,NULL,'MALE','HIGH',538,562);
/*!40000 ALTER TABLE `pet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet_histories`
--

DROP TABLE IF EXISTS `pet_histories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pet_histories` (
  `pet` int(11) NOT NULL,
  `histories` int(11) NOT NULL,
  UNIQUE KEY `UK_e9oderbpb6cincirwpba8gc5u` (`histories`),
  KEY `FK_8yhb2vhkk5vkw2g6vq2u0an8h` (`pet`),
  CONSTRAINT `FK_8yhb2vhkk5vkw2g6vq2u0an8h` FOREIGN KEY (`pet`) REFERENCES `pet` (`id`),
  CONSTRAINT `FK_e9oderbpb6cincirwpba8gc5u` FOREIGN KEY (`histories`) REFERENCES `history` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet_histories`
--

LOCK TABLES `pet_histories` WRITE;
/*!40000 ALTER TABLE `pet_histories` DISABLE KEYS */;
INSERT INTO `pet_histories` VALUES (604,550),(605,547),(606,551),(607,546),(608,548),(609,552),(610,549);
/*!40000 ALTER TABLE `pet_histories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet_owner`
--

DROP TABLE IF EXISTS `pet_owner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pet_owner` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expiration_month` int(11) DEFAULT NULL,
  `expiration_year` int(11) DEFAULT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `make_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hiryx51mtlv0nvmeijb3gbpcj` (`user_account`),
  CONSTRAINT `FK_hiryx51mtlv0nvmeijb3gbpcj` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet_owner`
--

LOCK TABLES `pet_owner` WRITE;
/*!40000 ALTER TABLE `pet_owner` DISABLE KEYS */;
INSERT INTO `pet_owner` VALUES (534,0,'c/Harina, nº10, Bormujos',188,'Adoro leer y los gatos!','petowner1@petowner1.com',4,2022,'Pet Owner Tomás','VISA','Tomás','4128837079312553','+34 611111111','http://www.foto.com','Cabello',526),(538,0,'c/Este, nº10, Sevilla',188,'Me encanta realizar ejercicio al aire libre con perros','petowner2@petowner2.com',4,2022,'Pet Owner Manuel','VISA','Manuel','4128837079312553','+34 611111111','http://www.foto.com','Recio',527);
/*!40000 ALTER TABLE `pet_owner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet_photos`
--

DROP TABLE IF EXISTS `pet_photos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pet_photos` (
  `pet` int(11) NOT NULL,
  `photos` varchar(255) DEFAULT NULL,
  KEY `FK_scb5f0cegtvwyhs2hl8c9w52g` (`pet`),
  CONSTRAINT `FK_scb5f0cegtvwyhs2hl8c9w52g` FOREIGN KEY (`pet`) REFERENCES `pet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet_photos`
--

LOCK TABLES `pet_photos` WRITE;
/*!40000 ALTER TABLE `pet_photos` DISABLE KEYS */;
INSERT INTO `pet_photos` VALUES (604,'https://upload.wikimedia.org/wikipedia/commons/thumb/e/e4/Persian_in_Cat_Cafe.jpg/245px-Persian_in_Cat_Cafe.jpg'),(605,'https://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/Cat_November_2010-1a.jpg/220px-Cat_November_2010-1a.jpg'),(606,'https://www.petfirst.com/wp-content/uploads/2018/03/Breed-Hero-Doberman-1200x1200.jpg'),(607,'https://archive.content.aah.net.au/files/images/puppy-labrador-grass.jpg'),(608,'https://www.webconsultas.com/sites/default/files/styles/wc_adaptive_image__small/public/temas/westie_p.jpg'),(609,'https://upload.wikimedia.org/wikipedia/commons/thumb/e/e4/Persian_in_Cat_Cafe.jpg/800px-Persian_in_Cat_Cafe.jpg'),(610,'https://www.elmundodelperro.net/fotos/91/3009_DSC_0217.jpg');
/*!40000 ALTER TABLE `pet_photos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet_type`
--

DROP TABLE IF EXISTS `pet_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pet_type` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `final_mode` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `scientific_term` varchar(255) DEFAULT NULL,
  `zone` varchar(255) DEFAULT NULL,
  `parent` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1kkgbpbi4vep7o12gg9923lxl` (`parent`),
  CONSTRAINT `FK_1kkgbpbi4vep7o12gg9923lxl` FOREIGN KEY (`parent`) REFERENCES `pet_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet_type`
--

LOCK TABLES `pet_type` WRITE;
/*!40000 ALTER TABLE `pet_type` DISABLE KEYS */;
INSERT INTO `pet_type` VALUES (553,0,NULL,'','PET','PET',NULL,NULL,NULL),(554,0,NULL,'','DOG','DOG','Canis lupus familiaris',NULL,553),(555,0,NULL,'','Unknown Dog','Perro Desconocido',NULL,NULL,554),(556,0,NULL,'','Bulldog','Bulldog',NULL,NULL,554),(557,0,NULL,'','Bodeguero','Bodeguero',NULL,NULL,554),(558,0,NULL,'','CAT','CAT','Felis silvestris catus',NULL,553),(559,0,NULL,'','Unknown Cat','Gato Desconocido',NULL,NULL,558),(560,0,'Es un perro conocido por su personalidad y color blanco brillante','','West Highland white terrier','West Highland white terrier',NULL,'Escocia',554),(561,0,' Se ha desempeñado durante siglos como perro pastor, y se le considera uno de los perros más antiguos de Gran Bretaña.','','Welsh Pembroke Corgi','Corgi galés de Pembroke',NULL,'Gales',554),(562,0,'Los lebreles? son un conjunto de razas de perros cuya constitución física les hace estar muy bien dotados para la carrera (carrera de galgos), en la que pueden alcanzar una gran velocidad.','','Sighthound','Lebrel (Galgo)',NULL,'Europa',554),(563,0,'El fox terrier? es una raza de perro utilizada antiguamente para hacer salir a los zorros de sus madrigueras para poder ser perseguidos por perros de rastreo.','','Fox terrier','Fox terrier',NULL,'Inglaterra',554),(564,0,'Es fuerte, tiene un sólido sistema inmunológico y se adapta fácilmente a cualquier ambiente.','','European Shorthair','Gato común europeo',NULL,'Europa',558),(565,0,'El Persa es una raza de gato caracterizada por tener una cara ancha y plana y un gran abundante pelaje de variados colores.','','Persian cat','Gato persa',NULL,'Persia',558);
/*!40000 ALTER TABLE `pet_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_profile`
--

DROP TABLE IF EXISTS `social_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `social_profile` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `social_network` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_profile`
--

LOCK TABLES `social_profile` WRITE;
/*!40000 ALTER TABLE `social_profile` DISABLE KEYS */;
INSERT INTO `social_profile` VALUES (535,0,'http://www.twitter.com/tomcablop','tomcablop','Twitter'),(536,0,'http://www.facebook.com/tomascabellolopez','Tomás Cabello López','Facebook'),(537,0,'http://www.instagram.com/tomcab','tomascab','Instagram'),(539,0,'http://www.facebook.com/manuelrecio','Manuel Recio','Facebook'),(540,0,'http://www.instagram.com/manuelrec','manuelrec','Instagram'),(599,0,'http://www.twitter.com/albbur','albbur','Twitter'),(600,0,'http://www.facebook.com/albaburdallo','Alba Burdallo','Facebook'),(601,0,'http://www.facebook.com/juanandelacruz','Juan Antonio De la Cruz','Facebook');
/*!40000 ALTER TABLE `social_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tip`
--

DROP TABLE IF EXISTS `tip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tip` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `veterinarian` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bseswm2ovenb2x54oa1jqavsa` (`veterinarian`),
  CONSTRAINT `FK_bseswm2ovenb2x54oa1jqavsa` FOREIGN KEY (`veterinarian`) REFERENCES `veterinarian` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tip`
--

LOCK TABLES `tip` WRITE;
/*!40000 ALTER TABLE `tip` DISABLE KEYS */;
INSERT INTO `tip` VALUES (566,0,'Para lavar a tu perro se recomienda que sigas los siguientes pasos. 1. Prepara una bañera con agua templada. 2. Añade algo de jabón. 3. Introduce al perro y frota con delicadeza.','2019-02-02 21:00:00','Cómo lavar a tu perro',541);
/*!40000 ALTER TABLE `tip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tip_pet_types`
--

DROP TABLE IF EXISTS `tip_pet_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tip_pet_types` (
  `tip` int(11) NOT NULL,
  `pet_types` int(11) NOT NULL,
  KEY `FK_frca8rmao4covcxdrobsij5sl` (`pet_types`),
  KEY `FK_dnyr3bro8tnogs2omcng4bcmv` (`tip`),
  CONSTRAINT `FK_dnyr3bro8tnogs2omcng4bcmv` FOREIGN KEY (`tip`) REFERENCES `tip` (`id`),
  CONSTRAINT `FK_frca8rmao4covcxdrobsij5sl` FOREIGN KEY (`pet_types`) REFERENCES `pet_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tip_pet_types`
--

LOCK TABLES `tip_pet_types` WRITE;
/*!40000 ALTER TABLE `tip_pet_types` DISABLE KEYS */;
INSERT INTO `tip_pet_types` VALUES (566,554);
/*!40000 ALTER TABLE `tip_pet_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatment`
--

DROP TABLE IF EXISTS `treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `treatment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `illness` varchar(255) DEFAULT NULL,
  `moment` date DEFAULT NULL,
  `treatmentc` varchar(255) DEFAULT NULL,
  `medical_check_up` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_r4eavr64jtqf6lw6o9aye846w` (`medical_check_up`),
  CONSTRAINT `FK_r4eavr64jtqf6lw6o9aye846w` FOREIGN KEY (`medical_check_up`) REFERENCES `medical_check_up` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatment`
--

LOCK TABLES `treatment` WRITE;
/*!40000 ALTER TABLE `treatment` DISABLE KEYS */;
INSERT INTO `treatment` VALUES (617,0,NULL,'Tiene pulgas','2019-02-01','Dos pipetas para pulgas al año',616);
/*!40000 ALTER TABLE `treatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (525,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(526,0,'0015c698fa10895de1efcb72bd8d5140','petowner1'),(527,0,'c77476f189c700ec604de6001c8086f1','petowner2'),(528,0,'6d08d4d1cb2c3eec7cae07cd8b598382','adopter1'),(529,0,'864b9c471763fbdd068b5fa3a4c3f71e','adopter2'),(530,0,'180ed327e7513896840b5a279462c071','veterinarian1'),(531,0,'4b5f2ba011d599d4d89e21cf9805d8eb','veterinarian2');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account_authorities`
--

DROP TABLE IF EXISTS `user_account_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account_authorities` (
  `user_account` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_pao8cwh93fpccb0bx6ilq6gsl` (`user_account`),
  CONSTRAINT `FK_pao8cwh93fpccb0bx6ilq6gsl` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_authorities`
--

LOCK TABLES `user_account_authorities` WRITE;
/*!40000 ALTER TABLE `user_account_authorities` DISABLE KEYS */;
INSERT INTO `user_account_authorities` VALUES (525,'ADMIN'),(526,'PETOWNER'),(527,'PETOWNER'),(528,'ADOPTER'),(529,'ADOPTER'),(530,'VETERINARIAN'),(531,'VETERINARIAN');
/*!40000 ALTER TABLE `user_account_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veterinarian`
--

DROP TABLE IF EXISTS `veterinarian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `veterinarian` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expiration_month` int(11) DEFAULT NULL,
  `expiration_year` int(11) DEFAULT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `make_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ifsy3ypbjecsrp87ncme52djl` (`user_account`),
  CONSTRAINT `FK_ifsy3ypbjecsrp87ncme52djl` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veterinarian`
--

LOCK TABLES `veterinarian` WRITE;
/*!40000 ALTER TABLE `veterinarian` DISABLE KEYS */;
INSERT INTO `veterinarian` VALUES (541,0,'c/Lactosa, nº10, Jaén',188,'Además de los animales, adoro la música clásica','vet1@vet1.com',4,2022,'Veterinarian Antonio','VISA','Antonio','4128837079312553','+34 611111111','http://www.foto.com','Picón',530),(544,0,'c/Cervantes, nº10, Jaén',188,'Me flipa la repostería y estar con mi familia','vet2@vet2.com',4,2022,'Veterinarian José María','VISA','José María','4128837079312553','+34 611111111','http://www.foto.com','García',531);
/*!40000 ALTER TABLE `veterinarian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veterinarian_curriculums`
--

DROP TABLE IF EXISTS `veterinarian_curriculums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `veterinarian_curriculums` (
  `veterinarian` int(11) NOT NULL,
  `curriculums` int(11) NOT NULL,
  UNIQUE KEY `UK_hsipyymsfq0sbl0eg6h6cf8yl` (`curriculums`),
  KEY `FK_4tooloi5huypmnlkrobxmxqhf` (`veterinarian`),
  CONSTRAINT `FK_4tooloi5huypmnlkrobxmxqhf` FOREIGN KEY (`veterinarian`) REFERENCES `veterinarian` (`id`),
  CONSTRAINT `FK_hsipyymsfq0sbl0eg6h6cf8yl` FOREIGN KEY (`curriculums`) REFERENCES `curriculum` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veterinarian_curriculums`
--

LOCK TABLES `veterinarian_curriculums` WRITE;
/*!40000 ALTER TABLE `veterinarian_curriculums` DISABLE KEYS */;
INSERT INTO `veterinarian_curriculums` VALUES (541,542),(541,543),(544,545);
/*!40000 ALTER TABLE `veterinarian_curriculums` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-06 16:51:11
commit;