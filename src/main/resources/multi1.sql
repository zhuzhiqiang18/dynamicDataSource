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
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(64) unsigned NOT NULL,
  `user_name` varchar(10) DEFAULT NULL,
  `pass_word` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
