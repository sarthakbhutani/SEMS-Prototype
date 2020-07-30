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
  `courses_img` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `instructor_id_idx` (`instructor_id`),
  CONSTRAINT `instructor_id` FOREIGN KEY (`instructor_id`) REFERENCES `instructors` (`instructor_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'Practical Guide to Web Development','2020-06-25','2020-07-01',1500,'Get a Full Understanding of the Web','Get a Full Understanding of the Web','2020-06-30 00:00:00',1,'https://sems-prototype.s3.ap-south-1.amazonaws.com/course-photos/html5.jpg'),(2,'Web Development From Scratch','2020-06-22','2020-06-30',1200,'Techniques, principles and methodologies used','Techniques, principles and methodologies used','2020-06-29 00:00:00',2,'https://sems-prototype.s3.ap-south-1.amazonaws.com/course-photos/html5.jpg'),(3,'Learn HTML5 Programming','2020-06-25','2020-06-30',500,'A beginner’s guide to learn HTML5','A beginner’s guide to learn HTML5','2020-06-29 00:00:00',3,'https://sems-prototype.s3.ap-south-1.amazonaws.com/course-photos/html5.jpg'),(4,'Web Design for Beginners','2020-06-18','2020-06-21',800,'Web Design for Beginners: Real World Coding in','Web Design for Beginners: Real World Coding in','2020-06-20 00:00:00',4,'https://sems-prototype.s3.ap-south-1.amazonaws.com/course-photos/html5.jpg');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `instructors`
--

LOCK TABLES `instructors` WRITE;
/*!40000 ALTER TABLE `instructors` DISABLE KEYS */;
INSERT INTO `instructors` VALUES (1,'Ruchi Pareek'),(2,'Priyanka Chaudhary'),(3,'Brad Traversy'),(4,'Rakesh Jain');
/*!40000 ALTER TABLE `instructors` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Dumping data for table `session_details`
--

LOCK TABLES `session_details` WRITE;
/*!40000 ALTER TABLE `session_details` DISABLE KEYS */;
INSERT INTO `session_details` VALUES (1,1,'class 1','2020-06-25 10:30:00','2020-06-25 12:30:00',1),(1,2,'class 2','2020-06-26 10:30:00','2020-06-26 12:30:00',1),(1,3,'class 3','2020-06-27 10:30:00','2020-06-27 12:30:00',1),(1,4,'class 4','2020-06-28 10:30:00','2020-06-28 12:30:00',1),(1,5,'class 5','2020-06-29 10:30:00','2020-06-29 12:30:00',1),(2,1,'class 1','2020-06-22 11:30:00','2020-06-22 01:30:00',2),(2,2,'class 2','2020-06-24 11:30:00','2020-06-24 01:30:00',2),(2,3,'class 3','2020-06-25 11:30:00','2020-06-25 01:30:00',2),(3,1,'class 1','2020-06-25 01:30:00','2020-06-25 03:30:00',3),(3,2,'class 2','2020-06-27 01:30:00','2020-06-27 03:30:00',3),(3,3,'class 3','2020-06-29 01:30:00','2020-06-29 03:30:00',3),(3,4,'class 4','2020-06-30 01:30:00','2020-06-30 03:30:00',3),(4,1,'class 1','2020-06-18 01:30:00','2020-06-18 03:30:00',4),(4,2,'class 2','2020-06-20 01:30:00','2020-06-20 03:30:00',4);
/*!40000 ALTER TABLE `session_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-30 21:18:24
