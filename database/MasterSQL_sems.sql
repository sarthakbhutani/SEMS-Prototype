CREATE DATABASE  IF NOT EXISTS `sems` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sems`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sems
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(100) NOT NULL,
  `course_start_date` date NOT NULL,
  `course_end_date` date NOT NULL,
  `course_price` int NOT NULL,
  `short_description` varchar(100) NOT NULL,
  `long_description` varchar(500) DEFAULT NULL,
  `assesment_date` datetime NOT NULL,
  `instructor_id` int DEFAULT NULL,
  `course_img` varchar(200) DEFAULT NULL,
  `course_category` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `instructor_id_idx` (`instructor_id`),
  CONSTRAINT `instructor_id` FOREIGN KEY (`instructor_id`) REFERENCES `instructors` (`instructor_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `instructors`
--

DROP TABLE IF EXISTS `instructors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instructors` (
  `instructor_id` int NOT NULL,
  `instructor_name` varchar(45) NOT NULL,
  PRIMARY KEY (`instructor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `session_details`
--

DROP TABLE IF EXISTS `session_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `session_details` (
  `course_id` int NOT NULL,
  `session_id` bigint NOT NULL,
  `session_name` varchar(100) DEFAULT NULL,
  `session_start_time` datetime DEFAULT NULL,
  `session_end_time` datetime DEFAULT NULL,
  `instructor_id` int NOT NULL,
  PRIMARY KEY (`course_id`,`session_id`),
  KEY `instructor_id_idx` (`instructor_id`),
  KEY `instructor_id_fk_index` (`instructor_id`) /*!80000 INVISIBLE */,
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE,
  CONSTRAINT `instructor_id_fk_on_instructoes` FOREIGN KEY (`instructor_id`) REFERENCES `instructors` (`instructor_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-11  4:39:22
