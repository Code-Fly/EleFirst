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

 Date: 02/12/2017 17:25:31 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_204_graph_toolbar`
-- ----------------------------
DROP TABLE IF EXISTS `t_204_graph_toolbar`;
CREATE TABLE `t_204_graph_toolbar` (
  `id` varchar(50) NOT NULL,
  `name` varchar(1000) DEFAULT NULL,
  `icon_path` longtext,
  `img_path` longtext,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `display_order` int(11) DEFAULT NULL,
  `create_person` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_person` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_204_graph_toolbar`
-- ----------------------------
BEGIN;
INSERT INTO `t_204_graph_toolbar` VALUES ('0e351dd9-8094-410f-9a3d-0e656f2121f8', '黑色大箭头', 'Content/images/graph/toolbar/custom/small-arrow-single-large-black.png', 'Content/images/graph/toolbar/custom/arrow-single-large-black.png', '25', '60', '1', null, null, null, null), ('49e78d80-4a0c-4997-98b7-662dd9c75ca7', '绿色圆圈', 'Content/images/graph/toolbar/custom/small-round-green.png', 'Content/images/graph/toolbar/custom/round-green.png', '20', '20', '4', null, null, null, null), ('579ea70d-7d9f-49b3-93c8-e7cff10eba62', '红色圆圈', 'Content/images/graph/toolbar/custom/small-round-red.png', 'Content/images/graph/toolbar/custom/round-red.png', '20', '20', '5', null, null, null, null), ('83c5ca39-eed5-4b6b-8553-9d94c130d4f0', '黑色双箭头', 'Content/images/graph/toolbar/custom/small-arrow-double-black.png', 'Content/images/graph/toolbar/custom/arrow-double-black.png', '20', '20', '2', null, null, null, null), ('87ac431f-3c84-4644-b943-9144d42a982b', '黑色圆圈', 'Content/images/graph/toolbar/custom/small-round-black.png', 'Content/images/graph/toolbar/custom/round-black.png', '20', '20', '6', null, null, null, null), ('f5151d36-3bd4-406b-bee4-c326d69f3ad4', '橙色圆圈', 'Content/images/graph/toolbar/custom/small-round-orange.png', 'Content/images/graph/toolbar/custom/round-orange.png', '20', '20', '3', null, null, null, null), ('f94a27f5-88a8-4a63-8935-8485ca9133ad', '三色圆圈', 'Content/images/graph/toolbar/custom/small-round-red-green-orange.png', 'Content/images/graph/toolbar/custom/round-red-green-orange.png', '25', '60', '7', null, null, null, null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
