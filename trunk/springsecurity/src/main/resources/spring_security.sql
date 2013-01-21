/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50522
Source Host           : localhost:3306
Source Database       : spring_security

Target Server Type    : MYSQL
Target Server Version : 50522
File Encoding         : 65001

Date: 2013-01-21 17:41:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `authorities`
-- ----------------------------
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `USERNAME` varchar(10) NOT NULL,
  `AUTHORITY` varchar(10) NOT NULL,
  KEY `FK_USERNAME_AUTHORITY` (`USERNAME`),
  CONSTRAINT `FK_USERNAME_AUTHORITY` FOREIGN KEY (`USERNAME`) REFERENCES `users` (`USERNAME`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authorities
-- ----------------------------

-- ----------------------------
-- Table structure for `b_user`
-- ----------------------------
DROP TABLE IF EXISTS `b_user`;
CREATE TABLE `b_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(32) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_user
-- ----------------------------

-- ----------------------------
-- Table structure for `b_userrole`
-- ----------------------------
DROP TABLE IF EXISTS `b_userrole`;
CREATE TABLE `b_userrole` (
  `ID` int(11) NOT NULL,
  `USERID` int(11) NOT NULL,
  `ROLE` varchar(15) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_USERID_USERROLE` (`USERID`),
  CONSTRAINT `FK_USERID_USERROLE` FOREIGN KEY (`USERID`) REFERENCES `b_user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_userrole
-- ----------------------------

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `USERNAME` varchar(10) NOT NULL,
  `PASSWORD` varchar(32) NOT NULL,
  `ENABLED` tinyint(1) NOT NULL,
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
