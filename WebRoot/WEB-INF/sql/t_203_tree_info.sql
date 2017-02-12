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

 Date: 02/12/2017 17:25:25 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_203_tree_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_203_tree_info`;
CREATE TABLE `t_203_tree_info` (
  `id` varchar(50) NOT NULL,
  `pid` varchar(50) NOT NULL,
  `tree_id` varchar(50) NOT NULL,
  `area_id` varchar(50) NOT NULL,
  `icon_cls` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `attributes` longtext,
  `name` varchar(1000) NOT NULL,
  `create_person` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_person` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
