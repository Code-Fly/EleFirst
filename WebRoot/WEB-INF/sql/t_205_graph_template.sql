/*
 Navicat Premium Data Transfer

 Source Server         : 116.62.102.138
 Source Server Type    : MySQL
 Source Server Version : 50621
 Source Host           : 116.62.102.138
 Source Database       : power

 Target Server Type    : MySQL
 Target Server Version : 50621
 File Encoding         : utf-8

 Date: 02/12/2017 17:25:37 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_205_graph_template`
-- ----------------------------
DROP TABLE IF EXISTS `t_205_graph_template`;
CREATE TABLE `t_205_graph_template` (
  `id` varchar(50) NOT NULL,
  `name` varchar(1000) DEFAULT NULL,
  `content` longtext,
  `config` longtext,
  `create_person` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_person` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
