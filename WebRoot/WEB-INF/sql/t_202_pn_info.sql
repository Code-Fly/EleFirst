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

 Date: 02/12/2017 17:25:17 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_202_pn_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_202_pn_info`;
CREATE TABLE `t_202_pn_info` (
  `id` varchar(50) NOT NULL,
  `area_id` varchar(50) NOT NULL,
  `concentrator_id` varchar(50) NOT NULL,
  `pn` varchar(50) NOT NULL,
  `name` varchar(1000) NOT NULL,
  `pt` double NOT NULL COMMENT '电压、功率、电量 系数',
  `ct` double NOT NULL COMMENT '电流、功率、电量 系数',
  `voltage_standard` double DEFAULT NULL COMMENT '电压标准值',
  `voltage_upper_limit` double DEFAULT NULL COMMENT '电压上限百分比',
  `voltage_lower_limit` double DEFAULT NULL COMMENT '电压下限百分比',
  `current_standard` double DEFAULT NULL,
  `current_upper_limit` double DEFAULT NULL COMMENT '电流上限百分比',
  `current_lower_limit` double DEFAULT NULL COMMENT '电流下线百分比',
  `load_standard` double DEFAULT NULL COMMENT '负载标准值',
  `load_upper_limit` double DEFAULT NULL COMMENT '负载上限百分比',
  `load_lower_limit` double DEFAULT NULL COMMENT '负载下限百分比',
  `electricity_standard` double DEFAULT NULL COMMENT '电量标准值',
  `electricity_upper_limit` double DEFAULT NULL COMMENT '电量上限百分比',
  `electricity_lower_limit` double DEFAULT NULL COMMENT '电量下限百分比',
  `power_factor_standard` double DEFAULT NULL COMMENT '功率因数标准值',
  `power_factor_upper_limit` double DEFAULT NULL COMMENT '功率因数上限百分比',
  `power_factor_lower_limit` double DEFAULT NULL COMMENT '功率因数下限百分比',
  `create_person` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_person` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

SET FOREIGN_KEY_CHECKS = 1;
