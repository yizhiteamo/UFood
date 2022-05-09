/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : ufood

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 24/04/2022 15:57:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ufood_cart
-- ----------------------------
DROP TABLE IF EXISTS `ufood_cart`;
CREATE TABLE `ufood_cart`  (
  `id` int(0) NOT NULL,
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户表的用户ID',
  `goods_no` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品编号',
  `number` int(0) NULL DEFAULT NULL COMMENT '商品数量',
  `checked` tinyint(1) NULL DEFAULT NULL COMMENT '购物车中的商品是否被选中',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ufood_goods
-- ----------------------------
DROP TABLE IF EXISTS `ufood_goods`;
CREATE TABLE `ufood_goods`  (
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'default' COMMENT '商品名称',
  `goods_no` int(0) NOT NULL AUTO_INCREMENT COMMENT '商品主键，商品编号',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品图片',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除 1为 删除 0为存在',
  `shop_no` int(0) NULL DEFAULT NULL COMMENT '商品所属店铺的编号',
  `introduce` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图文详情 使用富文本格式 ',
  `collected` tinyint(1) NULL DEFAULT 0 COMMENT '商品是否被收藏 1被收藏 0为未收藏',
  `cart` tinyint(1) NULL DEFAULT 0 COMMENT '商品是否在购物车中 1为是 0为否',
  PRIMARY KEY (`goods_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19875 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ufood_icon
-- ----------------------------
DROP TABLE IF EXISTS `ufood_icon`;
CREATE TABLE `ufood_icon`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '导航栏图标名称',
  `icon_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '导航栏图标URL',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ufood_order
-- ----------------------------
DROP TABLE IF EXISTS `ufood_order`;
CREATE TABLE `ufood_order`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `orderTime` datetime(0) NULL DEFAULT NULL COMMENT '订单预定时间',
  `userInfo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单预定的用户信息',
  `orderContent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单内容',
  `createTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '未就餐' COMMENT '订单状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 412 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ufood_retrieve
-- ----------------------------
DROP TABLE IF EXISTS `ufood_retrieve`;
CREATE TABLE `ufood_retrieve`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `uid` int(0) NULL DEFAULT NULL COMMENT '重置用户密码的用户id',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '随机生成的16位验证码',
  `createTime` date NULL DEFAULT NULL COMMENT '创建时间',
  `deadline` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '截至时间',
  `isEffective` int(0) NULL DEFAULT NULL COMMENT '是否有效，若当前时间超出截至时间，则判定当前验证码无效，0代表无效，1代表有效',
  `resetNum` int(0) NULL DEFAULT NULL COMMENT '重置次数，记录当前重置次数',
  `limitNum` int(0) NULL DEFAULT NULL COMMENT '当日限定重置次数, 默认为3次',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ufood_shop
-- ----------------------------
DROP TABLE IF EXISTS `ufood_shop`;
CREATE TABLE `ufood_shop`  (
  `shop_no` int(0) NOT NULL AUTO_INCREMENT COMMENT '商家主键，商家ID',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商家位置',
  `brief` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商家简介',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商家名称',
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商家类别',
  `shop_icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商家图标',
  `popular` tinyint(1) NULL DEFAULT 0 COMMENT '是否为热门店铺 1为是 0为否',
  `createTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '商家创建时间',
  PRIMARY KEY (`shop_no`) USING BTREE,
  INDEX `shopCate_no`(`category`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16591 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ufood_shopcate
-- ----------------------------
DROP TABLE IF EXISTS `ufood_shopcate`;
CREATE TABLE `ufood_shopcate`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '店铺分类',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ufood_swiper
-- ----------------------------
DROP TABLE IF EXISTS `ufood_swiper`;
CREATE TABLE `ufood_swiper`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `swiper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '轮播图路径',
  `goods_no` int(0) NULL DEFAULT NULL COMMENT '轮播图对应的商品编号',
  `index_swiper` tinyint(1) NULL DEFAULT 0 COMMENT '该轮播图是否为首页轮播图 1为是 0为否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ufood_user
-- ----------------------------
DROP TABLE IF EXISTS `ufood_user`;
CREATE TABLE `ufood_user`  (
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'admin' COMMENT '用户名称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '123456' COMMENT '用户密码',
  `uid` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `gender` tinyint(1) NOT NULL DEFAULT 0 COMMENT '用户性别 0为男 1为女',
  `deleted` tinyint(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户地址',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '用户的创建时间',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户邮箱',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `birthday` date NULL DEFAULT NULL COMMENT '用户生日',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
