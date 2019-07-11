/*
Navicat MySQL Data Transfer

Source Server         : mydb
Source Server Version : 50714
Source Host           : 127.0.0.1:3306
Source Database       : testdb

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2019-07-11 11:00:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `temporary_resources`
-- ----------------------------
DROP TABLE IF EXISTS `temporary_resources`;
CREATE TABLE `temporary_resources` (
  `media_id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `mdate` datetime DEFAULT NULL,
  `mtype` int(11) DEFAULT NULL,
  `realPath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`media_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of temporary_resources
-- ----------------------------
INSERT INTO `temporary_resources` VALUES ('kuRKf1nv2YdR_YbYg4jnmbXh6Iyoyz6jpSU8LVKhYImtUcgwocyHA0lVfYGwMWTb', '1562813044126', 'image/1562813043940.jpg', '2019-07-11 02:44:04', '1', 'D:/apache-tomcat-8.0.35/webapps/WeChatOfficialAccount/image/1562813043940.jpg');
INSERT INTO `temporary_resources` VALUES ('NiBcVOeTZdWNgB-lWhOTnlBCUMBcjn4Ljr5YUa_U8IGlmAmg2p07mZVKcFEbDNap', '1562813043830', 'image/1562813043657.jpg', '2019-07-11 02:44:04', '1', 'D:/apache-tomcat-8.0.35/webapps/WeChatOfficialAccount/image/1562813043657.jpg');
INSERT INTO `temporary_resources` VALUES ('NiBcVOeTZdWNgB-lWhOTnrTPNppX9Do7HWBHQ6W_kqcOTKHHDHal9C7uPqVXXe0y', '1562812991253', 'image/1562812991064.jpg', '2019-07-11 02:43:11', '1', 'D:/apache-tomcat-8.0.35/webapps/WeChatOfficialAccount/image/1562812991064.jpg');
INSERT INTO `temporary_resources` VALUES ('nXJgtPexojcHldI86hEX_9gO7VZRWY3zb_8rK7DliBSPn8l-9w9ofwykqasW1rwb', '1562812991504', 'image/1562812991302.jpg', '2019-07-11 02:43:12', '1', 'D:/apache-tomcat-8.0.35/webapps/WeChatOfficialAccount/image/1562812991302.jpg');
INSERT INTO `temporary_resources` VALUES ('wfUjFyk3gHuJGV6qA5zflEDdAdkj6U84uW-GpkXfhhUd9PQdoAvJ_QS-x2QtL08_', '1562812992507', 'image/1562812992035.jpg', '2019-07-11 02:43:13', '1', 'D:/apache-tomcat-8.0.35/webapps/WeChatOfficialAccount/image/1562812992035.jpg');
INSERT INTO `temporary_resources` VALUES ('ZJb6xU8iPhHrfo7T-7TFFNXhcEzYEonHO1XQDouwh7_xGSrkvs-yy7-Sa0aGEHsA', '1562813044855', 'image/1562813044575.jpg', '2019-07-11 02:44:05', '1', 'D:/apache-tomcat-8.0.35/webapps/WeChatOfficialAccount/image/1562813044575.jpg');

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
INSERT INTO `t_wx_access_token` VALUES ('23_ZIGVTnwvImQy-kEHXIZCyar6X4BqKM2bQw5DTSPE553Q_7fpli-O1DlORqRyoslT5M5xcnxmYA792Ip3A1f6f-j9OjHbvQ9j2A4y1k6g8vbMZsKzA7iZLpD33iGB7bKPz3ngIy6a3u8UgJ4VAIYaAEASAQ', '7200', '2019-07-09 14:09:09');
INSERT INTO `t_wx_access_token` VALUES ('23_6-4DreOKeKa1RljnovEg-GYhYaY3UVl90IevzidsMLUnNntKqjFUQBabISFFnf6ilVHdh65XUW0XP-2PJNyzwpaOzgmXZiPUHFFctcI3s6g0arrHtlHxM70zdoLAVWaFy_CNb1Z1F-zfCArFFJDhAFALPJ', '7200', '2019-07-09 16:00:02');
INSERT INTO `t_wx_access_token` VALUES ('23_I_tgqe2ky-vU6Asy8LRE8Pr2Hjmz97CqNxDssJG4pRTQEpTD30hyxPiQ26yOlEoodfQsX50ZDfaAUwF0yFBRVVN586qT-7Z8L71UTSZJ8ZMaZeGAi0Xq4IG8ulMx39uxX-O2Ba7_BBVa3ZzsJBPbAAAMES', '7200', '2019-07-09 18:00:02');
INSERT INTO `t_wx_access_token` VALUES ('23_7dIvzqvYjematoflYbem5vlsxZVsKO7omyJib2ZsoTZ4jcC3gwHBgSlFD13ZbcSd3OdhCVVSemrbj-nWa7tDAgUwVb7E0F1gHpsFrKbqpccUP6HFngCM5jn6Ku_Y1gztNjZSpTdWksqj0AdjLSIcADAVCS', '7200', '2019-07-10 09:33:30');
INSERT INTO `t_wx_access_token` VALUES ('23_MZ-b8eJlpcdLFdGgeufSHPCJkrDCvj25yQigBXeO8P3XrcM1EceEsaOIgrilfLko_cW3Mo_FEhSOLc7PStmdhelx-G1oPtxblNrUNT6RnQjAkUnsmqByhyPegJzZRSGz8a_2ipbJGyFVL4AnNHNeAFAZTZ', '7200', '2019-07-10 10:00:02');
INSERT INTO `t_wx_access_token` VALUES ('23_MPg_5xbnR7tugx-8ATuN9csPO0okawk9CwY0qJQkfVOU3DkF5dyBApGn_7QOERdiKsCcN1KRlJDO6mr1EWbCsFCD4pYQrSmaS14cljUzZ-y4Igi1QRm0pQamrqbKll3nJf-IvrlkSmIWOcwuEYIbAEAVPZ', '7200', '2019-07-10 14:58:09');
INSERT INTO `t_wx_access_token` VALUES ('23_JmI5LmK2OkIHoGp4Ke0CAAaYfhOg5Gs1omE0q6NnLz3zLEZVethD2BbvN1ATwAiaAUO251oC8udzZAdpxbeFFy7WuW6_5kE_jgGkRLeLqHs8yp5m1FT81Z1Lb80OKiK8HM_3YBP-TN3QxNA6GJEbAAAKNI', '7200', '2019-07-10 16:00:02');
INSERT INTO `t_wx_access_token` VALUES ('23_hxKJQSxYheUpIQw8-WedxyPEV6Qh1-Orv94t9kyN_RL3gMZR--riWRZfviHCiE3JY2h7l3DsY7fuspmjLcF9uAOH7Ubw3tEQQO5zXZWMiJbmk2KIhOl0RF9LhasBIIaADAWDD', '7200', '2019-07-11 09:24:01');
INSERT INTO `t_wx_access_token` VALUES ('23_KiWQ_ApJ1mB9f079LOLkiT6HUHH_eH1z4WFGrQxL2F1axVHOrYKorxZy4E_z3IiYX9ANctxmLeGTIrJAICN1za8VSwl9rf628nbaI8rPoKbyrRHnSoapFaIHNf237thGEmqMAC6JmNCAnscgWFAhAFACLG', '7200', '2019-07-11 10:00:01');
