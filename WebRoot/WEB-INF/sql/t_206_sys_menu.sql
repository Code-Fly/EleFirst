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

 Date: 02/12/2017 17:25:43 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_206_sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_206_sys_menu`;
CREATE TABLE `t_206_sys_menu` (
  `MenuID` varchar(36) NOT NULL,
  `MenuCode` varchar(100) DEFAULT NULL,
  `ParentCode` varchar(100) DEFAULT NULL,
  `MenuName` varchar(200) DEFAULT NULL,
  `URL` varchar(200) DEFAULT NULL,
  `IconClass` varchar(50) DEFAULT NULL,
  `IconURL` varchar(200) DEFAULT NULL,
  `MenuSeq` int(10) DEFAULT NULL,
  `Description` varchar(2048) DEFAULT NULL,
  `IsEnable` char(1) DEFAULT NULL,
  `CreatePerson` varchar(20) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  `UpdatePerson` varchar(20) DEFAULT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`MenuID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_206_sys_menu`
-- ----------------------------
BEGIN;
INSERT INTO `t_206_sys_menu` VALUES ('0', '0', '', '无上级菜单', '/psi/purchase', 'icon-application_home', '', '0', '', 'Y', 'admin', '2016-10-13 18:41:09', 'admin', '2016-10-13 18:41:22'), ('1', '01', '0', '用电能耗', '/psi/purchase', 'icon-application_home', '', '1', '', 'Y', 'admin', '2016-10-13 18:41:09', 'admin', '2016-10-13 18:41:22'), ('10', '10', '02', '电量分析', 'view/poweranalysis/electricity/main.jsp', 'icon-table', null, '22', null, 'Y', 'admin', '2016-10-13 18:41:09', 'admin', null), ('11', '11', '02', '分时用电分析', 'view/poweranalysis/electricityByTime/main.jsp', 'icon-table', null, '23', null, 'Y', 'admin', '2016-10-13 18:41:09', 'admin', null), ('12', '12', '02', '用电对比分析', 'view/poweranalysis/comparison/main.jsp', 'icon-table', null, '24', null, 'Y', 'admin', '2016-10-13 18:41:09', 'admin', null), ('13', '13', '03', '配用接线图', 'view/diagram/main.jsp', 'icon-chart_organisation', null, '30', null, 'Y', 'admin', '2016-10-13 18:41:09', 'admin', null), ('14', '14', '04', '表计档案管理', 'view/system/deviceinfo/main.jsp', 'icon-calculator', null, '40', null, 'Y', 'admin', '2016-10-13 18:41:09', 'admin', null), ('15', '15', '04', '企业档案管理', 'view/system/areainfo/main.jsp', 'icon-building', null, '41', null, 'Y', 'admin', '2016-10-13 18:41:09', 'admin', null), ('16', '16', '04', '菜单管理', 'view/system/menu/main.jsp', 'icon-application_cascade', null, '42', null, 'Y', 'admin', '2016-10-13 18:41:09', null, null), ('2', '02', '0', '用电分析', '/psi/purchase', 'icon-application_home', '', '2', '', 'Y', 'admin', '2016-10-13 18:41:09', 'admin', '2016-10-13 18:41:22'), ('3', '03', '0', '配用接线图', '/psi/purchase', 'icon-application_home', '', '3', '', 'Y', 'admin', '2016-10-13 18:41:09', 'admin', '2016-10-13 18:41:22'), ('4', '04', '0', '系统管理', '/psi/purchase', 'icon-application_home', '', '4', '', 'Y', 'admin', '2016-10-13 18:41:09', 'admin', '2016-10-13 18:41:22'), ('5', '05', '01', '实时用电数据', 'view/powerdetail/realtime/main.jsp', 'icon-table', '', '10', '', 'Y', 'admin', '2016-10-13 18:41:09', 'admin', '2016-10-13 18:41:22'), ('6', '06', '01', '日用电数据', 'view/powerdetail/daily/main.jsp', 'icon-table', null, '11', null, 'Y', 'admin', '2016-10-13 18:41:09', 'admin', null), ('7', '07', '01', '周用电数据', 'view/powerdetail/weekly/main.jsp', 'icon-table', null, '12', null, 'Y', 'admin', '2016-10-13 18:41:09', 'admin', null), ('8', '08', '01', '月用电数据', 'view/powerdetail/monthly/main.jsp', 'icon-table', null, '13', null, 'Y', 'admin', '2016-10-13 18:41:09', 'admin', null), ('9', '09', '02', '负荷分析', 'view/poweranalysis/load/main.jsp', 'icon-table', null, '21', null, 'Y', 'admin', '2016-10-13 18:41:09', 'admin', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
