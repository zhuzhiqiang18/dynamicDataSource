/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50529
Source Host           : localhost:3306
Source Database       : multi1

Target Server Type    : MYSQL
Target Server Version : 50529
File Encoding         : 65001

Date: 2019-06-12 20:32:10
*/
CREATE DATABASE`multi1` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.25)
# Database: multi1
# Generation Time: 2019-06-13 10:20:48 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table user_1
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_1`;

CREATE TABLE `user_1` (
  `id` bigint(64) unsigned NOT NULL,
  `user_name` varchar(10) DEFAULT NULL,
  `pass_word` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user_1` WRITE;
/*!40000 ALTER TABLE `user_1` DISABLE KEYS */;

INSERT INTO `user_1` (`id`, `user_name`, `pass_word`)
VALUES
	(0,NULL,NULL),
	(3,'1','1');

/*!40000 ALTER TABLE `user_1` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_2
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_2`;

CREATE TABLE `user_2` (
  `id` bigint(64) unsigned NOT NULL,
  `user_name` varchar(10) DEFAULT NULL,
  `pass_word` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table user_3
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_3`;

CREATE TABLE `user_3` (
  `id` bigint(64) unsigned NOT NULL,
  `user_name` varchar(10) DEFAULT NULL,
  `pass_word` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
