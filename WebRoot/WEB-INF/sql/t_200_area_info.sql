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

 Date: 02/12/2017 17:25:01 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_200_area_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_200_area_info`;
CREATE TABLE `t_200_area_info` (
  `id` varchar(50) NOT NULL,
  `area_id` varchar(50) DEFAULT NULL,
  `name` varchar(1000) DEFAULT NULL COMMENT '企业名称',
  `abbreviation` varchar(1000) DEFAULT NULL COMMENT '企业简称',
  `icp` varchar(1000) DEFAULT NULL COMMENT 'ICP备案号',
  `establishment_date` datetime DEFAULT NULL COMMENT '成立时间',
  `nature` longtext COMMENT '企业性质',
  `registered_capital` varchar(1000) DEFAULT NULL COMMENT '注册资金',
  `bussiness_industry` longtext COMMENT '主营行业',
  `number_of_employees` varchar(1000) DEFAULT NULL COMMENT '人数',
  `stock_code` varchar(1000) DEFAULT NULL COMMENT '股票代码',
  `area` varchar(1000) DEFAULT NULL COMMENT '面积',
  `contract_capacity` varchar(1000) DEFAULT NULL COMMENT '合同容量',
  `operation_capacity` varchar(1000) DEFAULT NULL COMMENT '运行容量',
  `account_number` varchar(1000) DEFAULT NULL COMMENT '用电户号',
  `voltage_level` varchar(1000) DEFAULT NULL COMMENT '电压等级',
  `executive_name` varchar(1000) DEFAULT NULL COMMENT '用电主管',
  `executive_tel` varchar(1000) DEFAULT NULL COMMENT '用电主管联系电话',
  `person_in_charge_name` varchar(1000) DEFAULT NULL COMMENT '用电负责人',
  `person_in_charge_tel` varchar(1000) DEFAULT NULL COMMENT '用电负责人联系电话',
  `transformers` longtext COMMENT '变压器信息',
  `create_person` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_person` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
