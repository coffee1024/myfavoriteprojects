/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50522
Source Host           : localhost:3306
Source Database       : tree

Target Server Type    : MYSQL
Target Server Version : 50522
File Encoding         : 65001

Date: 2012-11-23 17:13:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `treenode`
-- ----------------------------
DROP TABLE IF EXISTS `treenode`;
CREATE TABLE `treenode` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `parentid` int(11) DEFAULT NULL,
  `text` char(16) NOT NULL,
  `href` char(32) DEFAULT NULL,
  `leaf` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of treenode
-- ----------------------------
INSERT INTO `treenode` VALUES ('1', '0', '二级root', null, '0');
INSERT INTO `treenode` VALUES ('2', '1', '三级root', '', '0');
INSERT INTO `treenode` VALUES ('3', '1', '三级1', '', '1');
INSERT INTO `treenode` VALUES ('4', '1', '三级2', '', '1');
INSERT INTO `treenode` VALUES ('6', '2', '四级', null, '1');
INSERT INTO `treenode` VALUES ('9', '0', '五级', null, '1');
INSERT INTO `treenode` VALUES ('11', '0', '五级节点', null, '1');
INSERT INTO `treenode` VALUES ('14', '2', '四级2', null, '1');
INSERT INTO `treenode` VALUES ('15', '2', '四级3', null, '1');
INSERT INTO `treenode` VALUES ('17', '2', '新添加文件夹', null, '0');
INSERT INTO `treenode` VALUES ('21', '17', '新添加子节点', null, '1');
INSERT INTO `treenode` VALUES ('22', '17', '新添加子节点', null, '1');
INSERT INTO `treenode` VALUES ('23', '17', '新添加子节点', null, '1');
INSERT INTO `treenode` VALUES ('24', '17', '新添加子节点', null, '1');
INSERT INTO `treenode` VALUES ('25', '17', '新添加子节点', null, '1');
INSERT INTO `treenode` VALUES ('26', '17', '新添加子节点', null, '1');
INSERT INTO `treenode` VALUES ('27', '17', '新添加子节点', null, '1');
INSERT INTO `treenode` VALUES ('28', '17', '新添加子节点', null, '1');
INSERT INTO `treenode` VALUES ('29', '17', '新添加子节点', null, '1');
INSERT INTO `treenode` VALUES ('31', '17', '新添加子节点', null, '1');
INSERT INTO `treenode` VALUES ('32', '17', '新添加子节点', null, '1');
INSERT INTO `treenode` VALUES ('33', '17', '新添加子节点', null, '1');
INSERT INTO `treenode` VALUES ('34', '17', '新添加子节点', null, '1');
INSERT INTO `treenode` VALUES ('35', '17', '新添加文件夹', null, '0');
INSERT INTO `treenode` VALUES ('36', '0', '新添加子节点', null, '1');
INSERT INTO `treenode` VALUES ('38', '35', '新添加子节点', null, '1');
