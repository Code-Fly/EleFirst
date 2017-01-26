/*
Navicat MySQL Data Transfer

Source Server         : 116.62.102.138
Source Server Version : 50621
Source Host           : 116.62.102.138:3306
Source Database       : power

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-01-26 12:46:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_003_type_one_data_fn25`
-- ----------------------------
DROP TABLE IF EXISTS `t_003_type_one_data_fn25`;
CREATE TABLE `t_003_type_one_data_fn25` (
  `id` varchar(50) NOT NULL,
  `area_id` varchar(50) DEFAULT NULL COMMENT '区域id',
  `concentrator_id` varchar(24) NOT NULL COMMENT '集中器地址',
  `pn` varchar(12) NOT NULL COMMENT '测量点标识',
  `clientOperationTime` varchar(19) DEFAULT NULL,
  `totalActivePower` varchar(12) DEFAULT NULL COMMENT '当前总有功功率',
  `A_ActivePower` varchar(12) DEFAULT NULL COMMENT '当前 A 相有功功率',
  `B_ActivePower` varchar(12) DEFAULT NULL COMMENT '当前 B 相有功功率',
  `C_ActivePower` varchar(12) DEFAULT NULL COMMENT '当前 C 相有功功率',
  `totalReactivePower` varchar(12) DEFAULT NULL COMMENT '当前总无功功率',
  `A_ReactivePower` varchar(12) DEFAULT NULL COMMENT '当前 A 相无功功率',
  `B_ReactivePower` varchar(12) DEFAULT NULL COMMENT '当前 B 相无功功率',
  `C_ReactivePower` varchar(12) DEFAULT NULL COMMENT '当前 C 相无功功率',
  `totalPowerFactor` varchar(12) DEFAULT NULL COMMENT '当前总功率因数',
  `A_PowerFactor` varchar(12) DEFAULT NULL COMMENT '当前A 相功率因数',
  `B_PowerFactor` varchar(12) DEFAULT NULL COMMENT '当前B 相功率因数',
  `C_PowerFactor` varchar(12) DEFAULT NULL COMMENT '当前C 相功率因数',
  `A_Voltage` varchar(12) DEFAULT NULL COMMENT '当前A 相电压',
  `B_Voltage` varchar(12) DEFAULT NULL COMMENT '当前B 相电压',
  `C_Voltage` varchar(12) DEFAULT NULL COMMENT '当前C 相电压',
  `A_Current` varchar(12) DEFAULT NULL COMMENT '当前A 相电流',
  `B_Current` varchar(12) DEFAULT NULL COMMENT '当前B 相电流',
  `C_Current` varchar(12) DEFAULT NULL COMMENT '当前C 相电流',
  `Zero_Sequence_Current` varchar(12) DEFAULT NULL,
  `totalApparentPower` varchar(12) DEFAULT NULL,
  `A_ApparentPower` varchar(12) DEFAULT NULL,
  `B_ApparentPower` varchar(12) DEFAULT NULL,
  `C_ApparentPower` varchar(12) DEFAULT NULL,
  `sendTime` varchar(19) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;