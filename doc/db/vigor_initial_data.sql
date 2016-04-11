/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_mysql_m
Source Server Version : 50612
Source Host           : 127.0.0.1:3306
Source Database       : db-vigor

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2016-04-10 17:10:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `vigor_defensor_t_resource_info`
-- ----------------------------
DROP TABLE IF EXISTS `vigor_defensor_t_resource_info`;
CREATE TABLE `vigor_defensor_t_resource_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `name` varchar(128) NOT NULL COMMENT '资源名称',
  `resource_type` varchar(32) NOT NULL COMMENT '资源类型, 包括菜单、页面、操作按钮',
  `resource_url` varchar(256) DEFAULT NULL COMMENT '资源url',
  `target` varchar(32) DEFAULT NULL COMMENT '目标: _blank、_self、_top、_parent、frame',
  `privilege` varchar(128) DEFAULT NULL COMMENT '权限点',
  `icon` varchar(64) DEFAULT NULL COMMENT '图标',
  `parent` varchar(32) DEFAULT NULL COMMENT '上级资源',
  `sort_no` int(11) DEFAULT NULL COMMENT '排序, 递增顺序',
  `is_show` varchar(16) DEFAULT NULL COMMENT '是否显示',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='资源信息表';

-- ----------------------------
-- Records of vigor_defensor_t_resource_info
-- ----------------------------
INSERT INTO `vigor_defensor_t_resource_info` VALUES ('1', '系统管理', 'MENU', null, null, null, 'fa fa-cogs', null, null, 'YES', '2016-04-08 22:32:31', null);
INSERT INTO `vigor_defensor_t_resource_info` VALUES ('2', '资源管理', 'MENU', '/resource', null, null, null, '1', null, 'YES', '2016-04-08 22:33:12', null);
INSERT INTO `vigor_defensor_t_resource_info` VALUES ('3', '角色管理', 'MENU', '/role', null, null, null, '1', null, 'YES', '2016-04-08 22:33:48', null);
INSERT INTO `vigor_defensor_t_resource_info` VALUES ('4', '用户管理', 'MENU', '/user', null, null, null, '1', null, 'YES', '2016-04-08 22:34:13', null);

-- ----------------------------
-- Table structure for `vigor_defensor_t_role_info`
-- ----------------------------
DROP TABLE IF EXISTS `vigor_defensor_t_role_info`;
CREATE TABLE `vigor_defensor_t_role_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `name` varchar(128) NOT NULL COMMENT '角色名',
  `code` varchar(32) DEFAULT NULL COMMENT '角色编码',
  `identity` varchar(32) DEFAULT NULL COMMENT '角色对应身份',
  `creator` varchar(16) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of vigor_defensor_t_role_info
-- ----------------------------
INSERT INTO `vigor_defensor_t_role_info` VALUES ('1', '系统管理员', 'ROOT', '1', null, '2016-04-08 22:34:45', '2016-04-10 11:35:40', null);

-- ----------------------------
-- Table structure for `vigor_defensor_t_role_resource_rec`
-- ----------------------------
DROP TABLE IF EXISTS `vigor_defensor_t_role_resource_rec`;
CREATE TABLE `vigor_defensor_t_role_resource_rec` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `role_id` bigint(20) NOT NULL COMMENT '角色',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '资源',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_3` (`role_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`role_id`) REFERENCES `vigor_defensor_t_role_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='角色资源权限表';

-- ----------------------------
-- Records of vigor_defensor_t_role_resource_rec
-- ----------------------------
INSERT INTO `vigor_defensor_t_role_resource_rec` VALUES ('17', '1', '1');
INSERT INTO `vigor_defensor_t_role_resource_rec` VALUES ('18', '1', '4');
INSERT INTO `vigor_defensor_t_role_resource_rec` VALUES ('19', '1', '3');
INSERT INTO `vigor_defensor_t_role_resource_rec` VALUES ('20', '1', '2');

-- ----------------------------
-- Table structure for `vigor_defensor_t_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `vigor_defensor_t_user_info`;
CREATE TABLE `vigor_defensor_t_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `username` varchar(128) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of vigor_defensor_t_user_info
-- ----------------------------
INSERT INTO `vigor_defensor_t_user_info` VALUES ('1', 'admin', '54d64340c7a7ab14a10b90445be549088e0c7d6dc01de077343aeeb1', '2016-04-08 22:28:11');

-- ----------------------------
-- Table structure for `vigor_defensor_t_user_role_rec`
-- ----------------------------
DROP TABLE IF EXISTS `vigor_defensor_t_user_role_rec`;
CREATE TABLE `vigor_defensor_t_user_role_rec` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `user_id` bigint(20) NOT NULL COMMENT '用户',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_5` (`user_id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`user_id`) REFERENCES `vigor_defensor_t_user_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户角色关系记录表';

-- ----------------------------
-- Records of vigor_defensor_t_user_role_rec
-- ----------------------------
INSERT INTO `vigor_defensor_t_user_role_rec` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for `vigor_system_t_dict_info`
-- ----------------------------
DROP TABLE IF EXISTS `vigor_system_t_dict_info`;
CREATE TABLE `vigor_system_t_dict_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `dict_key` varchar(32) NOT NULL COMMENT '字典键',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='字典信息表';

-- ----------------------------
-- Records of vigor_system_t_dict_info
-- ----------------------------
INSERT INTO `vigor_system_t_dict_info` VALUES ('1', 'ROLE_IDENTITY', '角色身份');

-- ----------------------------
-- Table structure for `vigor_system_t_dict_item_rec`
-- ----------------------------
DROP TABLE IF EXISTS `vigor_system_t_dict_item_rec`;
CREATE TABLE `vigor_system_t_dict_item_rec` (
  `id` bigint(20) NOT NULL COMMENT 'id编号',
  `name` varchar(64) DEFAULT NULL COMMENT '字典项名称',
  `value` varchar(63) NOT NULL COMMENT '字典项值',
  `dict_id` bigint(20) NOT NULL COMMENT '所属字典',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`dict_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`dict_id`) REFERENCES `vigor_system_t_dict_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典项记录表';

-- ----------------------------
-- Records of vigor_system_t_dict_item_rec
-- ----------------------------
INSERT INTO `vigor_system_t_dict_item_rec` VALUES ('1', '管理员', 'ROOT', '1');
