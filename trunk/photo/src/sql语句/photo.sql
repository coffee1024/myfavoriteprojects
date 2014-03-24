/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : photo

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2014-03-24 11:58:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ftp_user`
-- ----------------------------
DROP TABLE IF EXISTS `ftp_user`;
CREATE TABLE `ftp_user` (
  `userid` varchar(64) NOT NULL,
  `userpassword` varchar(128) NOT NULL,
  `homedirectory` varchar(128) NOT NULL,
  `enableflag` tinyint(1) NOT NULL DEFAULT '1',
  `writepermission` tinyint(1) NOT NULL DEFAULT '0',
  `idletime` int(11) NOT NULL DEFAULT '0',
  `uploadrate` int(11) NOT NULL DEFAULT '0',
  `maxloginnumber` int(11) NOT NULL DEFAULT '0',
  `downloadrate` int(11) NOT NULL DEFAULT '0',
  `maxloginperip` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ftp_user
-- ----------------------------

-- ----------------------------
-- Table structure for `p_photo_file`
-- ----------------------------
DROP TABLE IF EXISTS `p_photo_file`;
CREATE TABLE `p_photo_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `create_user_login_name` varchar(255) DEFAULT NULL,
  `create_user_nick_name` varchar(255) DEFAULT NULL,
  `ext_name` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `recommend` int(11) DEFAULT NULL,
  `source_file_length` bigint(20) DEFAULT NULL,
  `source_file_name` varchar(255) DEFAULT NULL,
  `source_file_path` varchar(255) DEFAULT NULL,
  `thumbnail_file_path` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `is_original` bit(1) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `upload_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_photo_file
-- ----------------------------

-- ----------------------------
-- Table structure for `p_user`
-- ----------------------------
DROP TABLE IF EXISTS `p_user`;
CREATE TABLE `p_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  `expense` double DEFAULT NULL,
  `login_name` varchar(255) NOT NULL,
  `nick_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `register_date` datetime DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  `salt` varchar(255) NOT NULL,
  `score` double DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `post_code` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `tele_phone` varchar(255) DEFAULT NULL,
  `true_name` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `star` double DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_user
-- ----------------------------
