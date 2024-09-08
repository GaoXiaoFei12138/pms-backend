/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80011 (8.0.11)
 Source Host           : localhost:3306
 Source Schema         : personnel_db

 Target Server Type    : MySQL
 Target Server Version : 80011 (8.0.11)
 File Encoding         : 65001

 Date: 07/09/2024 21:09:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for personnel
-- ----------------------------
DROP TABLE IF EXISTS `personnel`;
CREATE TABLE `personnel`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of personnel
-- ----------------------------
INSERT INTO `personnel` VALUES (16, '/uploads/images/0073FwDlly1ht4tey4icdj30yi22otfv.jpg', '张小于');
INSERT INTO `personnel` VALUES (19, '/uploads/images/0073FwDlly1ht4tey4icdj30yi22otfv.jpg', '12345');
INSERT INTO `personnel` VALUES (20, 'uploads\\images\\0073FwDlly1ht4tey4icdj30yi22otfv.jpg', '12355');
INSERT INTO `personnel` VALUES (21, '/uploads/images/0073FwDlly1ht4tey4icdj30yi22otfv.jpg', 'test1');
INSERT INTO `personnel` VALUES (22, '/uploads/images/0073FwDlly1ht4tey4icdj30yi22otfv.jpg', 'test2');
INSERT INTO `personnel` VALUES (23, '/uploads/images/0073FwDlly1ht4tey4icdj30yi22otfv.jpg', '12345');
INSERT INTO `personnel` VALUES (24, '/uploads/images/0073FwDlly1ht4tey4icdj30yi22otfv.jpg', '12345');
INSERT INTO `personnel` VALUES (25, '/uploads/images/0073FwDlly1ht4tey4icdj30yi22otfv.jpg', '12345');
INSERT INTO `personnel` VALUES (26, '/uploads/images/0073FwDlly1ht4tey4icdj30yi22otfv.jpg', '121334');
INSERT INTO `personnel` VALUES (27, '/uploads/images/0073FwDlly1ht4tey4icdj30yi22otfv.jpg', '121334');
INSERT INTO `personnel` VALUES (28, '/uploads/images/0073FwDlly1ht4tey4icdj30yi22otfv.jpg', '121334');
INSERT INTO `personnel` VALUES (29, '/uploads/images/0073FwDlly1ht4tey4icdj30yi22otfv.jpg', 'gasff');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`, `username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (8, 'MTIzNDU2', 'admin');
INSERT INTO `users` VALUES (10, 'cm9vdA==', 'root');

-- ----------------------------
-- Table structure for validate_code
-- ----------------------------
DROP TABLE IF EXISTS `validate_code`;
CREATE TABLE `validate_code`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of validate_code
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
