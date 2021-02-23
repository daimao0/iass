/*
 Navicat Premium Data Transfer

 Source Server         : aiit-测试服务器
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : 192.168.191.28:3306
 Source Schema         : qudaotong_sys

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 23/02/2021 17:07:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '后台人员用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '后台人员密码',
  `nick_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '后台人员昵称',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '后台人员手机号码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '后台人员邮箱',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `modify_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '账号创建时间',
  `last_update_time` datetime NULL DEFAULT NULL COMMENT '上次修改的时间',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '上次登录的时间',
  `authority` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限，多个权限用‘,’隔开',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态：0->禁用，1->可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统管理员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES (1, 'daimao', '$2a$10$E9O8c6jRLRoAg6vD5lIISegZfzhOQWyK5IRJrejXcdk21yQpqV2fu', '呆毛', '1733820210', 'daimao2817@gmail.com', '闫晨阳', '闫晨阳', '2021-01-18 10:19:51', '2021-01-18 10:19:54', NULL, 'ROLE_ADMIN', 1);

-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限表id',
  `authority_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_authority
-- ----------------------------
INSERT INTO `sys_authority` VALUES (1, '超级管理员权限', '2021-02-09 13:31:37');
INSERT INTO `sys_authority` VALUES (2, '普通用户权限', '2021-02-09 17:45:32');

-- ----------------------------
-- Table structure for sys_company
-- ----------------------------
DROP TABLE IF EXISTS `sys_company`;
CREATE TABLE `sys_company`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '公司表id',
  `company_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司名',
  `create_time` datetime NULL DEFAULT NULL COMMENT '公司注册app的时间',
  `stats` tinyint(1) NULL DEFAULT NULL COMMENT '公司状态：是否还可以使用本app',
  `update_time` datetime NULL DEFAULT NULL COMMENT '公司更新时间',
  `purchasing_time` datetime NULL DEFAULT NULL COMMENT '购买时间',
  `expiration_time` datetime NULL DEFAULT NULL COMMENT '购买截止日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10003 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公司表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_company
-- ----------------------------
INSERT INTO `sys_company` VALUES (10001, '北京大学信息技术高等研究院', '2021-02-05 17:09:24', 1, '2021-02-05 17:09:32', '2021-02-05 17:09:37', '2121-02-05 17:09:42');
INSERT INTO `sys_company` VALUES (10002, '杭州钱江电气集团股份有限公司', '2021-02-05 17:09:28', 1, '2021-02-05 17:09:35', '2021-02-05 17:09:40', '2121-02-05 17:09:45');

-- ----------------------------
-- Table structure for sys_member
-- ----------------------------
DROP TABLE IF EXISTS `sys_member`;
CREATE TABLE `sys_member`  (
  `id` bigint(20) NOT NULL COMMENT '用户id',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `status` int(1) NULL DEFAULT 1 COMMENT '帐号状态：0->禁用，1->启用',
  `create_time` datetime NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_phone`(`phone`) USING BTREE COMMENT '手机号唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_member
-- ----------------------------
INSERT INTO `sys_member` VALUES (1, '19846121130', '$2a$10$446sjUyLqQqqghV0P/xkOem9JaKF5eBnUwwO6ldP0NP1HbyiDkIZS', 1, '2021-01-05 16:57:34');
INSERT INTO `sys_member` VALUES (10035, '17338200219', NULL, 1, '2021-02-23 10:13:14');

-- ----------------------------
-- Table structure for sys_member_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_member_role`;
CREATE TABLE `sys_member_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色表啊' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_member_role
-- ----------------------------
INSERT INTO `sys_member_role` VALUES (1, 1, 1);
INSERT INTO `sys_member_role` VALUES (6, 10011, 2);
INSERT INTO `sys_member_role` VALUES (7, 10035, 2);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色表id',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_role_name`(`role_name`) USING BTREE COMMENT '角色名称做唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', '2021-02-09 13:31:42');
INSERT INTO `sys_role` VALUES (2, '普通用户', '2021-02-09 14:24:27');

-- ----------------------------
-- Table structure for sys_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_authority`;
CREATE TABLE `sys_role_authority`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色权限表',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `authority_id` int(11) NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_authority
-- ----------------------------
INSERT INTO `sys_role_authority` VALUES (1, 1, 1);
INSERT INTO `sys_role_authority` VALUES (2, 2, 2);

SET FOREIGN_KEY_CHECKS = 1;
