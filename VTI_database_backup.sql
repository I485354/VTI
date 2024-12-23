-- MySQL dump 10.13  Distrib 8.0.39, for Win64 (x86_64)
--
-- Host: localhost    Database: VTI_database
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounting_entry`
--

DROP TABLE IF EXISTS `accounting_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounting_entry` (
  `entry_id` bigint NOT NULL AUTO_INCREMENT,
  `invoice_id` int NOT NULL,
  `entry_date` datetime(6) DEFAULT NULL,
  `debit_amount` double NOT NULL,
  `credit_amount` double NOT NULL,
  `descriptions` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`entry_id`),
  KEY `FK_AccountingEntryInvoice` (`invoice_id`),
  CONSTRAINT `FK_AccountingEntryInvoice` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`invoice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounting_entry`
--

LOCK TABLES `accounting_entry` WRITE;
/*!40000 ALTER TABLE `accounting_entry` DISABLE KEYS */;
INSERT INTO `accounting_entry` VALUES (1,1,'2024-11-10 00:00:00.000000',500,0,'Betaling ontvangen voor factuur 1001'),(2,2,'2024-11-12 00:00:00.000000',750,0,'Betaling ontvangen voor factuur 1002');
/*!40000 ALTER TABLE `accounting_entry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car` (
  `car_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `plate_number` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `chasi_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`car_id`),
  UNIQUE KEY `plate_number` (`plate_number`),
  UNIQUE KEY `chasi_number` (`chasi_number`),
  KEY `FK_VehicleCustomer` (`customer_id`),
  CONSTRAINT `FK_VehicleCustomer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (1,1,'AB-123-CD','Toyota','Corolla',2015,'1HGCM82633A123456'),(2,2,'EF-456-GH','Volkswagen','Golf',2018,'WVWZZZ1KZAW123456');
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `company` varchar(255) DEFAULT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `customer_number` int NOT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `customer_number` (`customer_number`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'John Doe','Doe Enterprises','Nieuwe straat 1','john.doe@example.com','0612345678',1),(2,'Jane Smith','Smith Motors','456 Elm Avenue','jane.smith@example.com','0687654321',2),(3,'Emily Johnson','Johnson Co.','789 Pine Rd','emily@example.com','345-678-9012',3),(4,'Furkan Fidan','sf comp','Scholeksterstraat 29','sello.furkan70@gmail.com','0624116745',4),(5,'Furkan Fidan','ff cmpny','Scholeksterstraat 29','sello.furkan70@gmail.com','0624116745',5),(17,'Selattin Fidan','ff cmpny','Scholeksterstraat, 29','sello.furkan70@gmail.com','0624116746',6),(20,'Selattin Fidan','','Scholeksterstraat, 29','sello.furkan70@gmail.com','0624116745',7),(21,'Selattin Fidan','ff cmpny','Scholeksterstraat, 29','sello.furkan70@gmail.com','0624116745',8),(22,'nieuwe klant','sf comp','mees straat 12','admin@gmail.com','0624116745',9),(23,'plus 1','plus','gt straat 29','plus@gmail.com','0612345678',10),(24,'mat','Matter Cars','Materstreet','mat@autos.nl','0612345678',11),(25,'Furkan Fidan','sf','Scholeksterstraat 29','sello.furkan70@gmail.com','0624116745',12),(26,'John Doe','Doe Enterprises','123 Main Street','john.doe@example.com','0612345678',13),(27,'John Doe','Doe Enterprises','123 Main Street','john.doe@example.com','0612345678',14),(28,'John Doe','Doe Enterprises','123 Main Street','john.doe@example.com','0612345678',15),(29,'John Doe','Doe Enterprises','123 Main Street','john.doe@example.com','0612345678',16),(30,'John Doe','Doe Enterprises','123 Main Street','john.doe@example.com','0612345678',17),(31,'kees smit','kesselsmits','kessel straat 10','kees@smits.com','0624115678',18);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `invoice_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `car_id` int DEFAULT NULL,
  `invoice_date` datetime(6) DEFAULT NULL,
  `due_date` datetime(6) DEFAULT NULL,
  `total_amount` double NOT NULL,
  `total_btw` double NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `invoice_number` int NOT NULL,
  `deleted` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`invoice_id`),
  UNIQUE KEY `invoice_number` (`invoice_number`),
  KEY `FK_CustomerInvoice` (`customer_id`),
  KEY `FK_InvoiceCar` (`car_id`),
  CONSTRAINT `FK_CustomerInvoice` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `FK_InvoiceCar` FOREIGN KEY (`car_id`) REFERENCES `car` (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,1,1,'2024-11-01 00:00:00.000000','2024-11-15 00:00:00.000000',500,105,'Betaald',1,'No'),(2,2,2,'2024-11-05 00:00:00.000000','2024-11-20 00:00:00.000000',750,157.5,'Betaald',2,'No'),(3,2,2,'2024-12-10 00:00:00.000000','2025-01-10 00:00:00.000000',121,10.5,'Betaald',3,NULL),(4,2,2,'2024-12-10 00:00:00.000000','2025-01-10 00:00:00.000000',90.75,15.75,'Betaald',4,NULL),(5,2,2,'2024-12-10 00:00:00.000000','2025-01-10 00:00:00.000000',1391.5,10.5,'Betaald',5,NULL),(6,1,1,'2024-12-10 00:00:00.000000','2025-01-10 00:00:00.000000',60.5,10.5,'Betaald',6,NULL),(7,1,1,'2024-12-10 00:00:00.000000','2025-01-10 00:00:00.000000',60.5,10.5,'Betaald',7,NULL),(8,1,1,'2024-12-10 00:00:00.000000','2025-01-10 00:00:00.000000',423.5,73.5,'Betaald',8,NULL),(9,4,NULL,'2024-12-10 00:00:00.000000','2025-01-10 00:00:00.000000',60.5,10.5,'Betaald',9,NULL),(10,1,1,'2024-12-10 00:00:00.000000','2025-01-10 00:00:00.000000',60.5,10.5,'Betaald',10,NULL),(11,1,1,'2024-12-10 21:13:13.864000','2024-12-10 21:13:13.864000',100,21,'Betaald',11,NULL),(12,1,1,'2024-12-10 00:00:00.000000','2025-01-10 00:00:00.000000',60.5,10.5,'Betaald',12,NULL),(13,1,1,'2024-12-10 00:00:00.000000','2025-01-10 00:00:00.000000',121,10.5,'Betaald',13,NULL),(14,1,1,'2024-12-10 00:00:00.000000','2025-01-10 00:00:00.000000',121,10.5,'Betaald',14,NULL),(15,1,1,'2024-12-10 00:00:00.000000','2025-01-10 00:00:00.000000',60.5,10.5,'Open',15,NULL),(16,4,NULL,'2024-12-10 00:00:00.000000','2025-01-10 00:00:00.000000',60.5,10.5,'Betaald',16,NULL),(17,3,NULL,'2024-12-10 00:00:00.000000','2025-01-10 00:00:00.000000',242,42,'Betaald',17,NULL),(18,1,1,'2024-12-19 00:46:55.373000','2024-12-19 00:46:55.373000',500,21,'Open',18,NULL);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_item`
--

DROP TABLE IF EXISTS `invoice_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_item` (
  `invoice_item_id` int NOT NULL AUTO_INCREMENT,
  `invoice_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  `unit_price` double NOT NULL,
  `btw` double NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`invoice_item_id`),
  KEY `FK_InvoiceItemInvoice` (`invoice_id`),
  KEY `FK_InvoiceItemProduct` (`product_id`),
  CONSTRAINT `FK_InvoiceItemInvoice` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`invoice_id`),
  CONSTRAINT `FK_InvoiceItemProduct` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_item`
--

LOCK TABLES `invoice_item` WRITE;
/*!40000 ALTER TABLE `invoice_item` DISABLE KEYS */;
INSERT INTO `invoice_item` VALUES (1,1,1,2,25,5.25,55.25),(2,1,2,1,50,10.5,60.5),(3,2,1,4,25,10.5,110.5);
/*!40000 ALTER TABLE `invoice_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `invoice_id` int NOT NULL,
  `payment_date` datetime(6) DEFAULT NULL,
  `amount` double NOT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `FK_PaymentInvoice` (`invoice_id`),
  CONSTRAINT `FK_PaymentInvoice` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`invoice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,1,'2024-11-10 00:00:00.000000',500,'PIN'),(2,2,'2024-11-12 00:00:00.000000',750,'Contant');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `btw` int NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Olie Filter','Olie Filter vervangen',25,100,21),(2,'Remblokken','Set van remblokken',50,50,21),(3,'Olie verversen','Dienst voor olie verversen',50,100,21),(4,'Remschijven','Set van remschijven',75,50,21),(5,'APK Diesel','Apk diesel auto',50,1,21),(6,'APK Benzine','Apk benzine auto',50,1,21),(7,'band wisselen ','1 band wisselen ',25,1,21),(8,'olie vullen','motorolie verversen',20,1,9),(9,'klein beurt ','kleine beurt',250,1,21);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin123','Admin'),(2,'monteur','mech2024','Monteur');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-21  1:14:23
