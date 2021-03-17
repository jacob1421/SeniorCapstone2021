-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: chocanon_db
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `providers`
--

DROP TABLE IF EXISTS `providers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `providers` (
  `provider_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(12) NOT NULL,
  `last_name` varchar(12) NOT NULL,
  `street_address` varchar(25) NOT NULL,
  `city` varchar(14) NOT NULL,
  `state` char(2) NOT NULL,
  `zip_code` varchar(5) NOT NULL,
  `provider_email` varchar(50) NOT NULL,
  `provider_number` int NOT NULL,
  `provider_type_id` int NOT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`provider_id`),
  UNIQUE KEY `provider_number_UNIQUE` (`provider_number`)
) ENGINE=InnoDB AUTO_INCREMENT=252 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `providers`
--

LOCK TABLES `providers` WRITE;
/*!40000 ALTER TABLE `providers` DISABLE KEYS */;
INSERT INTO `providers` VALUES (1,'Mary','Eaton','622 Kathy Square Apt. 632','Davidhaven','SD','01688','zmiranda@campbell.com',695618294,1,0),(2,'Rebecca','Williams','7716 King Pines','Perkinsborough','KS','88145','samanthaalvarez@gmail.com',386654596,8,1),(3,'William','Martinez','067 Troy Lodge Suite 324','Leemouth','VA','58890','griffinashley@yahoo.com',877624177,9,0),(4,'Yvette','Carson','73135 Vazquez Springs','Sheppardport','NV','97607','teresa66@lewis.com',802212424,7,0),(5,'Tony','Tucker','928 King Mills','Nicholasshire','GA','61929','jmiller@hotmail.com',877701199,1,0),(6,'Tyrone','Rose','8338 Matthew Plains','North Penny','WA','53862','tammy76@jennings.com',804409502,4,0),(7,'David','Cochran','273 Jennings Ferry','Danielborough','SC','10652','dustin58@hotmail.com',450011131,5,0),(8,'Patrick','Kelly','6236 Burns Park Suite 954','Longview','OK','30880','hhester@moses.com',329178239,8,0),(9,'Andrea','Moyer','2882 Curtis Point','Sylvialand','OH','73392','malonedavid@cole.com',600078965,3,0),(10,'Gavin','Jones','25960 Molly Loaf Apt. 533','East Desiree','IA','32440','cruzlisa@hotmail.com',350302202,6,0);
/*!40000 ALTER TABLE `providers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-17  3:00:20
