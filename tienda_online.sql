/*!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-11.4.2-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: tienda_online
-- ------------------------------------------------------
-- Server version	11.4.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `carrito`
--

DROP TABLE IF EXISTS `carrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carrito` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) NOT NULL,
  `producto_id` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `producto_id` (`producto_id`),
  CONSTRAINT `carrito_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `carrito_ibfk_2` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrito`
--

LOCK TABLES `carrito` WRITE;
/*!40000 ALTER TABLE `carrito` DISABLE KEYS */;
/*!40000 ALTER TABLE `carrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ofertas`
--

DROP TABLE IF EXISTS `ofertas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ofertas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `tipo_descuento` enum('porcentaje','monto_fijo') NOT NULL,
  `valor_descuento` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ofertas`
--

LOCK TABLES `ofertas` WRITE;
/*!40000 ALTER TABLE `ofertas` DISABLE KEYS */;
INSERT INTO `ofertas` VALUES
(1,'Oferta Cerveza',NULL,'porcentaje',10.00),
(2,'Oferta Vino',NULL,'monto_fijo',5.00),
(3,'Oferta Whisky',NULL,'porcentaje',15.00),
(4,'Oferta Vodka',NULL,'porcentaje',20.00),
(5,'Oferta Ron',NULL,'monto_fijo',3.00),
(6,'Oferta Tequila',NULL,'porcentaje',12.00);
/*!40000 ALTER TABLE `ofertas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `imagen_nombre` varchar(255) DEFAULT NULL,
  `tipo_bebida_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tipo_bebida` (`tipo_bebida_id`),
  CONSTRAINT `fk_tipo_bebida` FOREIGN KEY (`tipo_bebida_id`) REFERENCES `tipo_bebida` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES
(1,'Cerveza Artesanal de Rio Gallegos','Cerveza',5.06,'Cerveza artesanal unica.','cerveza-artesanal.jpg',1),
(2,'Vino Tinto Malbec','Vino',15.00,'Elegante vino tinto Malbec argentino.','botella-vino.jpg',NULL),
(3,'Whisky Escoces','Whisky',40.00,'Clasico whisky escoces de calidad.','vino-blanco.jpg',2),
(4,'Vodka Premium','Vodka',25.00,'Premium vodka con suavidad excepcional.','botella-vodka.jpg',2),
(5,'Ron','blanca',20.00,'Ron con notas tropicales.','vino-blanco.jpg',2),
(6,'Tequila Reposado','Tequila',30.00,'Tequila reposado, autentico sabor.','vino-blanco.jpg',2),
(7,'Ginebra London Dry','Ginebra',22.00,'Ginebra seca perfecta para cocteles.','vino-blanco.jpg',2),
(8,'Brandy Reservado','Brandy',35.00,'Brandy reservado para momentos especiales.','botella-brandy.jpg',2),
(9,'Champagne Brut','Champagne',50.00,'Champagne brut, celebracion garantizada.','botella-champagne.jpg',4),
(10,'Sidra Natural','Sidra',12.00,'Sidra natural con frescura unica.','botella-sidra.jpg',4),
(11,'Vino Blanco','Vino',150.00,'Fresco y natural','vino-blanco.jpg',NULL);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos_ofertas`
--

DROP TABLE IF EXISTS `productos_ofertas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos_ofertas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_producto` int(11) NOT NULL,
  `id_oferta` int(11) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_producto_idx` (`id_producto`),
  KEY `fk_id_oferta_idx` (`id_oferta`),
  CONSTRAINT `productos_ofertas_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `productos_ofertas_ibfk_2` FOREIGN KEY (`id_oferta`) REFERENCES `ofertas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos_ofertas`
--

LOCK TABLES `productos_ofertas` WRITE;
/*!40000 ALTER TABLE `productos_ofertas` DISABLE KEYS */;
INSERT INTO `productos_ofertas` VALUES
(1,1,1,'2024-07-10','2024-07-31'),
(2,2,2,'2024-07-10','2024-07-31'),
(3,3,3,'2024-07-10','2024-07-31'),
(4,4,4,'2024-07-10','2024-07-31'),
(5,5,5,'2024-07-10','2024-07-31'),
(6,6,6,'2024-07-10','2024-07-31');
/*!40000 ALTER TABLE `productos_ofertas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_bebida`
--

DROP TABLE IF EXISTS `tipo_bebida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_bebida` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_bebida`
--

LOCK TABLES `tipo_bebida` WRITE;
/*!40000 ALTER TABLE `tipo_bebida` DISABLE KEYS */;
INSERT INTO `tipo_bebida` VALUES
(1,'Cerveza','cerveza'),
(2,'Bebidas blancas','blancas'),
(3,'Licores','licores'),
(4,'Vinos','vinos');
/*!40000 ALTER TABLE `tipo_bebida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(50) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `rol` enum('admin','cliente') NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_usuario` (`nombre_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES
(1,'admin','admin010203','admin','nicobutter@gmail.com','1980-11-17'),
(3,'Nicolas','123456','cliente','nicobutter@gmail.com','2024-08-06'),
(4,'Leticia','123456','cliente','nicobutter@gmail.com','2024-07-26'),
(5,'Ethan','123456','cliente','nicobutter@gmail.com','2024-07-26'),
(6,'Viggo','123456','cliente','nicobutter@gmail.com','2024-07-13'),
(7,'Franco','123456','cliente','nicobutter@gmail.com','2024-07-24'),
(12,'Natalia','123456','cliente','nicobutter@gmail.com','2024-07-16');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2024-07-12 21:25:00
