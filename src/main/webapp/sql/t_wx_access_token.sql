/*
Navicat MySQL Data Transfer

Source Server         : mydb
Source Server Version : 50714
Source Host           : 127.0.0.1:3306
Source Database       : testdb

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2019-06-27 10:33:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_wx_access_token`
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_access_token`;
CREATE TABLE `t_wx_access_token` (
  `access_token` varchar(5000) DEFAULT NULL,
  `expires_in` varchar(500) DEFAULT NULL,
  `create_time` varchar(50) NOT NULL,
  PRIMARY KEY (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_wx_access_token
-- ----------------------------
INSERT INTO `t_wx_access_token` VALUES ('16_fU_BxCurU8VpUupq7VfW-2AzdqnBAcEW2kMOkfNx0uSabVsdS7zFWZavYEmviOfhJT-_y8fj5atzzOFpzAr6bsBQaXT2teZNSB1BXqrR_NFQXz-EvqaNV7d5mWtCNj2sY1K5w6qPb2cwNzf0UJJjAAACRR', '7200', '2018-12-13 15:39:52');
INSERT INTO `t_wx_access_token` VALUES ('16_ApztF3IWhhzQwbWterBHdR8qhTHUb_vt59bGn_Cbbza6f_Oy1gMLhvhd13JpQOfJdgtrwYGAvBmL-geqAx6O_46pYHkjY7GtZ-HpQQsF6NgDUmuO4EoEaGggwzAIeizE_Tut0QNQsF_f8g48QQTeAHAMMP', '7200', '2018-12-13 15:40:16');
INSERT INTO `t_wx_access_token` VALUES ('16_wSn5N7SsnbkHMFGLg8UxDWVBbKAi0dYqSKMkwBSEnN1QjpkYroS20UQkGCkQAcPYlDoYRByZn6l3giIWH7Otvr2f1EvkmEJMVoJDjxmx-L2FpO_HQIeCUcqAtqI4JjyvzHbSygO1RBWiPTM0MZFcACAGTZ', '7200', '2018-12-13 15:40:46');
INSERT INTO `t_wx_access_token` VALUES ('22_dlSc9PQlbL0Tp6bpe5S21OQM8kwrVWrj4mbSeut2ly21eRmV0-fJPV6BjnHi_gd81JywQ5511cai6p6v1UPGJVoNHxR_LNqDODHepp0auCOudMHzmMNzFwib7hwXWNeAAAFEV', '7200', '2019-06-27 09:58:39');
INSERT INTO `t_wx_access_token` VALUES ('22_fHscuU1W9BbhdLRPM6j3AY9kgUeeffH5NS6llh__kV5x_kSoMOaAjTSQmLIg6dlTbP0VivEDgVBjgdCJoxKUHxEUEan4tylAS8pAmu0-OrJeifldZyNu1Bvt-xav4sk65wPfDwghZf497cqIVCPgAFADKX', '7200', '2019-06-27 10:00:07');
