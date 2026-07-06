CREATE DATABASE IF NOT EXISTS restaurant_db;
USE restaurant_db;

-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: restaurant_db
-- ------------------------------------------------------
-- Server version	9.4.0

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
-- Table structure for table `food_items`
--

DROP TABLE IF EXISTS `food_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `price` int NOT NULL,
  `category` varchar(50) NOT NULL,
  `type` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_items`
--

LOCK TABLES `food_items` WRITE;
/*!40000 ALTER TABLE `food_items` DISABLE KEYS */;
INSERT INTO `food_items` VALUES (1,'Poha',40,'Breakfast','Veg'),(2,'Aloo Paratha',70,'Breakfast','Veg'),(3,'Paneer Paratha',90,'Breakfast','Veg'),(4,'Idli',50,'Breakfast','Veg'),(5,'Masala Dosa',80,'Breakfast','Veg'),(6,'Plain Dosa',70,'Breakfast','Veg'),(7,'Upma',60,'Breakfast','Veg'),(8,'Veg Sandwich',60,'Breakfast','Veg'),(9,'Omelette',50,'Breakfast','Non-Veg'),(10,'Boiled Eggs',40,'Breakfast','Non-Veg'),(11,'Egg Bhurji',70,'Breakfast','Non-Veg'),(12,'Egg Paratha',80,'Breakfast','Non-Veg'),(13,'Chicken Sandwich',120,'Breakfast','Non-Veg'),(14,'French Fries',90,'Snacks','Veg'),(15,'Veg Pakora',80,'Snacks','Veg'),(16,'Paneer Pakora',120,'Snacks','Veg'),(17,'Samosa',40,'Snacks','Veg'),(18,'Spring Rolls',100,'Snacks','Veg'),(19,'Veg Momos',90,'Snacks','Veg'),(20,'Cheese Balls',130,'Snacks','Veg'),(21,'Chicken Momos',120,'Snacks','Non-Veg'),(22,'Chicken Nuggets',150,'Snacks','Non-Veg'),(23,'Chicken Pakora',140,'Snacks','Non-Veg'),(24,'Chicken Spring Rolls',160,'Snacks','Non-Veg'),(25,'Veg Burger',80,'Fast Food','Veg'),(26,'Cheese Burger',100,'Fast Food','Veg'),(27,'Veg Pizza Small',150,'Fast Food','Veg'),(28,'Veg Pizza Medium',250,'Fast Food','Veg'),(29,'Paneer Pizza',300,'Fast Food','Veg'),(30,'White Sauce Pasta',180,'Fast Food','Veg'),(31,'Red Sauce Pasta',160,'Fast Food','Veg'),(32,'Chicken Burger',120,'Fast Food','Non-Veg'),(33,'Chicken Pizza Small',200,'Fast Food','Non-Veg'),(34,'Chicken Pizza Medium',350,'Fast Food','Non-Veg'),(35,'Chicken Pasta',220,'Fast Food','Non-Veg'),(36,'Dal Tadka',120,'Lunch','Veg'),(37,'Dal Makhani',150,'Lunch','Veg'),(38,'Shahi Paneer',180,'Lunch','Veg'),(39,'Kadai Paneer',170,'Lunch','Veg'),(40,'Mix Veg',130,'Lunch','Veg'),(41,'Jeera Rice',90,'Lunch','Veg'),(42,'Plain Rice',70,'Lunch','Veg'),(43,'Veg Thali',220,'Lunch','Veg'),(44,'Butter Chicken',280,'Lunch','Non-Veg'),(45,'Chicken Curry',240,'Lunch','Non-Veg'),(46,'Chicken Biryani',200,'Lunch','Non-Veg'),(47,'Egg Curry',140,'Lunch','Non-Veg'),(48,'Egg Biryani',160,'Lunch','Non-Veg'),(49,'Non-Veg Thali',300,'Lunch','Non-Veg'),(50,'Paneer Butter Masala',180,'Dinner','Veg'),(51,'Kadai Paneer',170,'Dinner','Veg'),(52,'Shahi Paneer',190,'Dinner','Veg'),(53,'Dal Makhani',150,'Dinner','Veg'),(54,'Mix Veg Curry',140,'Dinner','Veg'),(55,'Malai Kofta',180,'Dinner','Veg'),(56,'Palak Paneer',170,'Dinner','Veg'),(57,'Butter Chicken',220,'Dinner','Non-Veg'),(58,'Chicken Curry',200,'Dinner','Non-Veg'),(59,'Chicken Korma',210,'Dinner','Non-Veg'),(60,'Egg Curry',140,'Dinner','Non-Veg'),(61,'Mutton Curry',280,'Dinner','Non-Veg'),(62,'Fish Curry',240,'Dinner','Non-Veg'),(63,'Veg Biryani',160,'Dinner','Veg'),(64,'Paneer Biryani',180,'Dinner','Veg'),(65,'Chicken Biryani',220,'Dinner','Non-Veg'),(66,'Mutton Biryani',260,'Dinner','Non-Veg'),(67,'Jeera Rice',120,'Dinner','Veg'),(68,'Steam Rice',100,'Dinner','Veg'),(69,'Butter Roti',25,'Dinner','Veg'),(70,'Plain Naan',40,'Dinner','Veg'),(71,'Butter Naan',50,'Dinner','Veg'),(72,'Garlic Naan',60,'Dinner','Veg'),(73,'Tandoori Roti',30,'Dinner','Veg'),(74,'Sweet Lassi',60,'Dinner','Veg'),(75,'Salted Lassi',60,'Dinner','Veg'),(76,'Buttermilk',40,'Dinner','Veg'),(77,'Soft Drink',40,'Dinner','Veg'),(78,'Coca-Cola',40,'Beverages','Veg'),(79,'Pepsi',40,'Beverages','Veg'),(80,'Sprite',40,'Beverages','Veg'),(81,'Fanta',40,'Beverages','Veg'),(82,'Tea',20,'Beverages','Veg'),(83,'Coffee',40,'Beverages','Veg'),(84,'Cappuccino',80,'Beverages','Veg'),(85,'Latte',90,'Beverages','Veg'),(86,'Mango Shake',120,'Beverages','Veg'),(87,'Chocolate Shake',140,'Beverages','Veg'),(88,'Strawberry Shake',130,'Beverages','Veg'),(89,'Banana Shake',100,'Beverages','Veg'),(90,'Orange Juice',90,'Beverages','Veg'),(91,'Mixed Fruit Juice',110,'Beverages','Veg'),(92,'Gulab Jamun',50,'Desserts','Veg'),(93,'Rasgulla',50,'Desserts','Veg'),(94,'Ice Cream',80,'Desserts','Veg'),(95,'Brownie',120,'Desserts','Veg'),(96,'Chocolate Lava Cake',150,'Desserts','Veg');
/*!40000 ALTER TABLE `food_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(100) NOT NULL,
  `total_amount` int NOT NULL,
  `order_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-07-07  0:56:31
