/*
 Navicat Premium Data Transfer

 Source Server         : MYSQL
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : gym

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 13/01/2020 16:02:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for equip
-- ----------------------------
DROP TABLE IF EXISTS `equip`;
CREATE TABLE `equip`  (
  `EquipID` int(11) NOT NULL AUTO_INCREMENT,
  `EquipType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `EquipPrice` int(11) NOT NULL,
  `EquipState` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`EquipID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of equip
-- ----------------------------
INSERT INTO `equip` VALUES (1, '篮球', 133, b'0');
INSERT INTO `equip` VALUES (2, '乒乓球', 2, b'0');
INSERT INTO `equip` VALUES (3, '雪球', 2, b'0');
INSERT INTO `equip` VALUES (4, '足球', 12, b'0');

-- ----------------------------
-- Table structure for equiptype
-- ----------------------------
DROP TABLE IF EXISTS `equiptype`;
CREATE TABLE `equiptype`  (
  `EquipTypeID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `EquipTypeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`EquipTypeID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of equiptype
-- ----------------------------
INSERT INTO `equiptype` VALUES (1, '篮球');
INSERT INTO `equiptype` VALUES (2, '乒乓球');
INSERT INTO `equiptype` VALUES (3, '气球');
INSERT INTO `equiptype` VALUES (4, '足球');
INSERT INTO `equiptype` VALUES (5, '羽毛球');
INSERT INTO `equiptype` VALUES (6, '雪球');

-- ----------------------------
-- Table structure for event_apply
-- ----------------------------
DROP TABLE IF EXISTS `event_apply`;
CREATE TABLE `event_apply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `game_time` datetime(0) NOT NULL,
  `type_id` int(11) NOT NULL COMMENT '比赛类型',
  `organizer` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '举办方',
  `applicant` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请人',
  `equipment_id` int(11) NULL DEFAULT NULL,
  `equ_num` int(11) NULL DEFAULT NULL,
  `filed_id` int(11) NULL DEFAULT NULL,
  `fil_num` int(11) NULL DEFAULT NULL,
  `apply_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `apply_status` int(11) NOT NULL DEFAULT 0 COMMENT '申请状态 0：未审核  1：审核通过 2：审核不通过',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `type_id`(`type_id`) USING BTREE,
  CONSTRAINT `event_apply_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `event_type` (`type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for event_record
-- ----------------------------
DROP TABLE IF EXISTS `event_record`;
CREATE TABLE `event_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `result` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `game_time` datetime(0) NOT NULL,
  `organizer` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `type_id`(`type_id`) USING BTREE,
  CONSTRAINT `event_record_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `event_type` (`type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for event_type
-- ----------------------------
DROP TABLE IF EXISTS `event_type`;
CREATE TABLE `event_type`  (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of event_type
-- ----------------------------
INSERT INTO `event_type` VALUES (1, '篮球');
INSERT INTO `event_type` VALUES (2, '羽毛球');
INSERT INTO `event_type` VALUES (3, '乒乓球');
INSERT INTO `event_type` VALUES (4, '足球');
INSERT INTO `event_type` VALUES (5, '排球');
INSERT INTO `event_type` VALUES (6, '网球');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '权限分配', '只有系统管理员可拥有，进行权限管理');
INSERT INTO `permission` VALUES (2, '公告发布', '体育馆管理员可拥有，发布公告');
INSERT INTO `permission` VALUES (3, '赛事管理', '赛事管理员拥有');
INSERT INTO `permission` VALUES (4, '器材管理', '器材管理员拥有');
INSERT INTO `permission` VALUES (5, '场地管理', '场地管理员拥有');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '普通用户', '普通用户');
INSERT INTO `role` VALUES (2, '系统管理员', '管理权限');
INSERT INTO `role` VALUES (3, '赛事管理员', '管理赛事');
INSERT INTO `role` VALUES (4, '器材管理员', '管理器材');
INSERT INTO `role` VALUES (5, '场地管理员', '管理场地');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `permission_id`(`permission_id`) USING BTREE,
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (5, 4, 2);
INSERT INTO `role_permission` VALUES (6, 4, 4);
INSERT INTO `role_permission` VALUES (13, 2, 1);
INSERT INTO `role_permission` VALUES (14, 2, 2);
INSERT INTO `role_permission` VALUES (38, 5, 2);
INSERT INTO `role_permission` VALUES (39, 5, 5);
INSERT INTO `role_permission` VALUES (40, 3, 2);
INSERT INTO `role_permission` VALUES (41, 3, 3);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_account` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_password` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isdel` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '111111', '系统管理员', '123123', '12312312311', 0);
INSERT INTO `user` VALUES (2, '222222', '赛事管理员', '123456', '12312312311', 0);
INSERT INTO `user` VALUES (3, '333333', '场地管理员', '123456', '12312312311', 0);
INSERT INTO `user` VALUES (4, '444444', '器材管理员', '123456', '12312312311', 0);
INSERT INTO `user` VALUES (5, '555555', '路人A', '123456', '12312312311', 1);
INSERT INTO `user` VALUES (6, '666666', '路人甲', '123456', '12312312311', 0);
INSERT INTO `user` VALUES (7, '123123', '托尼', '123123', '12312312311', 0);
INSERT INTO `user` VALUES (8, '412496580', '老铁啊', '123123', '12312312311', 0);
INSERT INTO `user` VALUES (9, '777777', 'abcabc', '123123', '12312312311', 0);
INSERT INTO `user` VALUES (10, '159159', 'username', '123123', '12312312312', 0);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (4, 4, 4);
INSERT INTO `user_role` VALUES (12, 1, 2);
INSERT INTO `user_role` VALUES (14, 7, 1);
INSERT INTO `user_role` VALUES (15, 3, 5);
INSERT INTO `user_role` VALUES (16, 5, 1);
INSERT INTO `user_role` VALUES (17, 2, 3);
INSERT INTO `user_role` VALUES (18, 10, 2);

SET FOREIGN_KEY_CHECKS = 1;
