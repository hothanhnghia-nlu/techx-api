/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100432
 Source Host           : localhost:3306
 Source Schema         : techx_nlu

 Target Server Type    : MySQL
 Target Server Version : 100432
 File Encoding         : 65001

 Date: 03/11/2024 23:16:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for addresses
-- ----------------------------
DROP TABLE IF EXISTS `addresses`;
CREATE TABLE `addresses`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ward` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` tinyint NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  `updated_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK1fa36y2oqhao3wgg2rw1pi459`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FK1fa36y2oqhao3wgg2rw1pi459` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of addresses
-- ----------------------------

-- ----------------------------
-- Table structure for carts
-- ----------------------------
DROP TABLE IF EXISTS `carts`;
CREATE TABLE `carts`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `product_id` int NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `status` tinyint NULL DEFAULT NULL,
  `order_date` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKmd2ap4oxo3wvgkf4fnaye532i`(`product_id` ASC) USING BTREE,
  INDEX `FKb5o626f86h46m4s7ms6ginnop`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FKb5o626f86h46m4s7ms6ginnop` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKmd2ap4oxo3wvgkf4fnaye532i` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of carts
-- ----------------------------

-- ----------------------------
-- Table structure for favorites
-- ----------------------------
DROP TABLE IF EXISTS `favorites`;
CREATE TABLE `favorites`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `product_id` int NULL DEFAULT NULL,
  `status` tinyint NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK6sgu5npe8ug4o42bf9j71x20c`(`product_id` ASC) USING BTREE,
  INDEX `FKk7du8b8ewipawnnpg76d55fus`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FK6sgu5npe8ug4o42bf9j71x20c` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKk7du8b8ewipawnnpg76d55fus` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of favorites
-- ----------------------------

-- ----------------------------
-- Table structure for images
-- ----------------------------
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `provider_id` int NULL DEFAULT NULL,
  `product_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UKkpl2gxdm1hx8e2edga1ka2mwu`(`provider_id` ASC) USING BTREE,
  INDEX `FKghwsjbjo7mg3iufxruvq6iu3q`(`product_id` ASC) USING BTREE,
  CONSTRAINT `FKghwsjbjo7mg3iufxruvq6iu3q` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKhhuyhkvj2fh6pqaw6eia44m8v` FOREIGN KEY (`provider_id`) REFERENCES `providers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of images
-- ----------------------------
INSERT INTO `images` VALUES (1, 'Samsung', 'https://upload.wikimedia.org/wikipedia/commons/f/f1/Samsung_logo_blue.png', 1, NULL);
INSERT INTO `images` VALUES (2, 'Apple', 'https://www.shutterstock.com/image-vector/galati-romania-april-29-2023-600nw-2295394661.jpg', 2, NULL);
INSERT INTO `images` VALUES (3, 'OPPO', 'https://upload.wikimedia.org/wikipedia/commons/a/a2/OPPO_LOGO_2019.png', 3, NULL);
INSERT INTO `images` VALUES (4, 'Xiaomi', 'https://seeklogo.com/images/X/xiaomi-english-logo-3878D29746-seeklogo.com.png', 4, NULL);
INSERT INTO `images` VALUES (5, 'Vivo', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRLMyHn5CBLbxjlVQlWpAFgo6Bj0M6P1Y_WIA&s', 5, NULL);
INSERT INTO `images` VALUES (6, 'Realme', 'https://upload.wikimedia.org/wikipedia/commons/9/91/Realme_logo.png', 6, NULL);
INSERT INTO `images` VALUES (7, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-blue-1-1.jpg', NULL, 1);
INSERT INTO `images` VALUES (8, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-tu-nhien-1-1.jpg', NULL, 2);
INSERT INTO `images` VALUES (9, 'iPhone 15 Pro Max', 'https://cdn2.cellphones.com.vn/358x/media/catalog/product/i/p/iphone15-pro-max-titan-trang.jpg', NULL, 3);
INSERT INTO `images` VALUES (10, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-black-1-1.jpg', NULL, 4);
INSERT INTO `images` VALUES (11, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-blue-1-1.jpg', NULL, 5);
INSERT INTO `images` VALUES (12, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-tu-nhien-1-1.jpg', NULL, 6);
INSERT INTO `images` VALUES (13, 'iPhone 15 Pro Max', 'https://cdn2.cellphones.com.vn/358x/media/catalog/product/i/p/iphone15-pro-max-titan-trang.jpg', NULL, 7);
INSERT INTO `images` VALUES (14, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-black-1-1.jpg', NULL, 8);
INSERT INTO `images` VALUES (15, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-blue-1-1.jpg', NULL, 9);
INSERT INTO `images` VALUES (16, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-tu-nhien-1-1.jpg', NULL, 10);
INSERT INTO `images` VALUES (17, 'iPhone 15 Pro Max', 'https://cdn2.cellphones.com.vn/358x/media/catalog/product/i/p/iphone15-pro-max-titan-trang.jpg', NULL, 11);
INSERT INTO `images` VALUES (18, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-black-1-1.jpg', NULL, 12);
INSERT INTO `images` VALUES (19, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-blue-1-1.jpg', NULL, 13);
INSERT INTO `images` VALUES (20, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-tu-nhien-1-1.jpg', NULL, 14);
INSERT INTO `images` VALUES (21, 'iPhone 15 Pro Max', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/i/p/iphone-15-pro-max_4.png', NULL, 15);
INSERT INTO `images` VALUES (22, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-black-1-1.jpg', NULL, 16);
INSERT INTO `images` VALUES (23, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-blue-1-1.jpg', NULL, 17);
INSERT INTO `images` VALUES (24, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-tu-nhien-1-1.jpg', NULL, 18);
INSERT INTO `images` VALUES (25, 'iPhone 15 Pro Max', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/i/p/iphone-15-pro-max_4.png', NULL, 19);
INSERT INTO `images` VALUES (26, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-black-1-1.jpg', NULL, 20);
INSERT INTO `images` VALUES (27, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-blue-1-1.jpg', NULL, 21);
INSERT INTO `images` VALUES (28, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-tu-nhien-1-1.jpg', NULL, 22);
INSERT INTO `images` VALUES (29, 'iPhone 15 Pro Max', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/i/p/iphone-15-pro-max_4.png', NULL, 23);
INSERT INTO `images` VALUES (30, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-black-1-1.jpg', NULL, 24);
INSERT INTO `images` VALUES (31, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-blue-1-1.jpg', NULL, 25);
INSERT INTO `images` VALUES (32, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-tu-nhien-1-1.jpg', NULL, 26);
INSERT INTO `images` VALUES (33, 'iPhone 15 Pro Max', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/i/p/iphone-15-pro-max_4.png', NULL, 27);
INSERT INTO `images` VALUES (34, 'iPhone 15 Pro Max', 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-black-1-1.jpg', NULL, 28);
INSERT INTO `images` VALUES (35, 'iPhone 15 Plus', 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-xanh-la-2.jpg', NULL, 29);
INSERT INTO `images` VALUES (36, 'iPhone 15 Plus', 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-xanh-2.jpg', NULL, 30);
INSERT INTO `images` VALUES (37, 'iPhone 15 Plus', 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-hong-2.jpg', NULL, 31);
INSERT INTO `images` VALUES (38, 'iPhone 15', 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-den-2.jpg', NULL, 32);
INSERT INTO `images` VALUES (39, 'iPhone 15', 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-vang-2.jpg', NULL, 33);
INSERT INTO `images` VALUES (40, 'iPhone 15', 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-xanh-la-2.jpg', NULL, 34);
INSERT INTO `images` VALUES (41, 'iPhone 15', 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-xanh-2.jpg', NULL, 35);
INSERT INTO `images` VALUES (42, 'iPhone 15', 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-hong-2.jpg', NULL, 36);
INSERT INTO `images` VALUES (43, 'iPhone 15', 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-den-2.jpg', NULL, 37);
INSERT INTO `images` VALUES (44, 'iPhone 15', 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-vang-2.jpg', NULL, 38);
INSERT INTO `images` VALUES (45, 'iPhone 15', 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-xanh-la-2.jpg', NULL, 39);
INSERT INTO `images` VALUES (46, 'iPhone 15', 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-xanh-2.jpg', NULL, 40);
INSERT INTO `images` VALUES (47, 'iPhone 15', 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-hong-2.jpg', NULL, 41);
INSERT INTO `images` VALUES (48, 'iPhone 15', 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-den-2.jpg', NULL, 42);
INSERT INTO `images` VALUES (49, 'iPhone 15', 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-vang-2.jpg', NULL, 43);
INSERT INTO `images` VALUES (50, 'iPhone 15', 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-xanh-la-2.jpg', NULL, 44);
INSERT INTO `images` VALUES (51, 'iPhone 15', 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-xanh-2.jpg', NULL, 45);
INSERT INTO `images` VALUES (52, 'iPhone 15', 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-hong-2.jpg', NULL, 46);
INSERT INTO `images` VALUES (53, 'iPhone 15', 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-den-2.jpg', NULL, 47);
INSERT INTO `images` VALUES (54, 'iPhone 15', 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-vang-2.jpg', NULL, 48);
INSERT INTO `images` VALUES (55, 'Samsung Galaxy A15', 'https://cdn.tgdd.vn/2023/12/campaign/B4C9F311-6B6E-41FE-A3CB-A2A0CA4FBBBB-600x620.png', NULL, 49);
INSERT INTO `images` VALUES (56, 'Samsung Galaxy A15', 'https://cdn.tgdd.vn/2023/12/campaign/51A90EBB-A13B-4C5A-B83F-17CA0146506C-600x620.png', NULL, 50);
INSERT INTO `images` VALUES (57, 'Samsung Galaxy A15', 'https://cdn.tgdd.vn/2023/12/campaign/D0C58AB0-D4D1-40F0-AC1A-5103AF2B050F-600x620.png', NULL, 51);
INSERT INTO `images` VALUES (58, 'Samsung Galaxy A15', 'https://cdn.tgdd.vn/2023/12/campaign/B4C9F311-6B6E-41FE-A3CB-A2A0CA4FBBBB-600x620.png', NULL, 52);
INSERT INTO `images` VALUES (59, 'Samsung Galaxy A15', 'https://cdn.tgdd.vn/2023/12/campaign/51A90EBB-A13B-4C5A-B83F-17CA0146506C-600x620.png', NULL, 53);
INSERT INTO `images` VALUES (60, 'Samsung Galaxy A15', 'https://cdn.tgdd.vn/2023/12/campaign/D0C58AB0-D4D1-40F0-AC1A-5103AF2B050F-600x620.png', NULL, 54);
INSERT INTO `images` VALUES (61, 'Samsung Galaxy A15', 'https://cdn.tgdd.vn/2023/12/campaign/B4C9F311-6B6E-41FE-A3CB-A2A0CA4FBBBB-600x620.png', NULL, 55);
INSERT INTO `images` VALUES (62, 'Samsung Galaxy A15', 'https://cdn.tgdd.vn/2023/12/campaign/51A90EBB-A13B-4C5A-B83F-17CA0146506C-600x620.png', NULL, 56);
INSERT INTO `images` VALUES (63, 'Samsung Galaxy A15', 'https://cdn.tgdd.vn/2023/12/campaign/D0C58AB0-D4D1-40F0-AC1A-5103AF2B050F-600x620.png', NULL, 57);
INSERT INTO `images` VALUES (64, 'OPPO Find N3 Flip 5G', 'https://cdn.tgdd.vn/Products/Images/42/317981/oppo-find-n3-flip-pink-1.jpg', NULL, 58);
INSERT INTO `images` VALUES (65, 'OPPO Find N3 Flip 5G', 'https://cdn.tgdd.vn/Products/Images/42/309835/oppo-n3-flip-den-glr-1.jpg', NULL, 59);
INSERT INTO `images` VALUES (66, 'OPPO Find N3 Flip 5G', 'https://cdn.tgdd.vn/Products/Images/42/309835/oppo-n3-flip-vang-1.jpg', NULL, 60);
INSERT INTO `images` VALUES (67, 'Xiaomi Redmi 13C', 'https://cdn.tgdd.vn/Products/Images/42/316771/xiaomi-redmi-13c-xanh-1-1.jpg', NULL, 61);
INSERT INTO `images` VALUES (68, 'Xiaomi Redmi 13C', 'https://cdn.tgdd.vn/Products/Images/42/316771/xiaomi-redmi-13c-xanh-duong-1.jpg', NULL, 62);
INSERT INTO `images` VALUES (69, 'Xiaomi Redmi 13C', 'https://cdn.tgdd.vn/Products/Images/42/316771/xiaomi-redmi-13c-den-1-1.jpg', NULL, 63);
INSERT INTO `images` VALUES (70, 'Vivo V29e 5G', 'https://cdn.tgdd.vn/Products/Images/42/309864/vivo-v29e-den-glr-1.jpg', NULL, 64);
INSERT INTO `images` VALUES (71, 'Vivo V29e 5G', 'https://cdn.tgdd.vn/Products/Images/42/309864/vivo-v29e-xanh-glr-1.jpg', NULL, 65);
INSERT INTO `images` VALUES (72, 'Vivo V29e 5G', 'https://cdn.tgdd.vn/Products/Images/42/309864/vivo-v29e-den-glr-1.jpg', NULL, 66);
INSERT INTO `images` VALUES (73, 'Vivo V29e 5G', 'https://cdn.tgdd.vn/Products/Images/42/309864/vivo-v29e-xanh-glr-1.jpg', NULL, 67);
INSERT INTO `images` VALUES (74, 'OPPO A38', 'https://cdn.tgdd.vn/Products/Images/42/320836/oppo-a38-black-1.jpeg', NULL, 68);
INSERT INTO `images` VALUES (75, 'OPPO A38', 'https://cdn.tgdd.vn/Products/Images/42/320836/oppo-a38-gold-1.jpeg', NULL, 69);
INSERT INTO `images` VALUES (76, 'OPPO A38', 'https://cdn.tgdd.vn/Products/Images/42/320836/oppo-a38-black-1.jpeg', NULL, 70);
INSERT INTO `images` VALUES (77, 'OPPO A38', 'https://cdn.tgdd.vn/Products/Images/42/320836/oppo-a38-gold-1.jpeg', NULL, 71);
INSERT INTO `images` VALUES (78, 'Xiaomi 13T 5G', 'https://cdn.tgdd.vn/Products/Images/42/309814/xiaomi-13t-xanh-duong-1.jpg', NULL, 72);
INSERT INTO `images` VALUES (79, 'Xiaomi 13T 5G', 'https://cdn.tgdd.vn/Products/Images/42/309814/xiaomi-13t-xanh-1.jpg', NULL, 73);
INSERT INTO `images` VALUES (80, 'Xiaomi 13T 5G', 'https://cdn.tgdd.vn/Products/Images/42/309814/xiaomi-13t-den-5.jpg', NULL, 74);
INSERT INTO `images` VALUES (81, 'Xiaomi 13T 5G', 'https://cdn.tgdd.vn/Products/Images/42/309814/xiaomi-13t-xanh-duong-1.jpg', NULL, 75);
INSERT INTO `images` VALUES (82, 'Xiaomi 13T 5G', 'https://cdn.tgdd.vn/Products/Images/42/309814/xiaomi-13t-xanh-1.jpg', NULL, 76);
INSERT INTO `images` VALUES (83, 'Xiaomi 13T 5G', 'https://cdn.tgdd.vn/Products/Images/42/309814/xiaomi-13t-den-5.jpg', NULL, 77);
INSERT INTO `images` VALUES (84, 'iPhone 11', 'https://cdn.tgdd.vn/Products/Images/42/153856/iphone-11-den-1-1-1-org.jpg', NULL, 78);
INSERT INTO `images` VALUES (85, 'iPhone 11', 'https://cdn.tgdd.vn/Products/Images/42/153856/iphone-11-trang-1-2-org.jpg', NULL, 79);
INSERT INTO `images` VALUES (86, 'iPhone 11', 'https://cdn.tgdd.vn/Products/Images/42/153856/iphone-11-den-1-1-1-org.jpg', NULL, 80);
INSERT INTO `images` VALUES (87, 'iPhone 11', 'https://cdn.tgdd.vn/Products/Images/42/153856/iphone-11-trang-1-2-org.jpg', NULL, 81);
INSERT INTO `images` VALUES (88, 'Samsung Galaxy A25 5G', 'https://cdn.tgdd.vn/Products/Images/42/319904/samsung-galaxy-a25-5g-xanh-1.jpg', NULL, 82);
INSERT INTO `images` VALUES (89, 'Samsung Galaxy A25 5G', 'https://cdn.tgdd.vn/Products/Images/42/319904/samsung-galaxy-a25-vang-1.jpg', NULL, 83);
INSERT INTO `images` VALUES (90, 'Samsung Galaxy A25 5G', 'https://cdn.tgdd.vn/Products/Images/42/319904/samsung-galaxy-a25-xanh-den-1.jpg', NULL, 84);
INSERT INTO `images` VALUES (91, 'Samsung Galaxy A25 5G', 'https://cdn.tgdd.vn/Products/Images/42/319904/samsung-galaxy-a25-5g-xanh-1.jpg', NULL, 85);
INSERT INTO `images` VALUES (92, 'Samsung Galaxy A25 5G', 'https://cdn.tgdd.vn/Products/Images/42/319904/samsung-galaxy-a25-vang-1.jpg', NULL, 86);
INSERT INTO `images` VALUES (93, 'Samsung Galaxy A25 5G', 'https://cdn.tgdd.vn/Products/Images/42/319904/samsung-galaxy-a25-xanh-den-1.jpg', NULL, 87);
INSERT INTO `images` VALUES (94, 'Vivo Y36', 'https://cdn.tgdd.vn/Products/Images/42/311120/vivo-y36-xanh-glr-1.jpeg', NULL, 88);
INSERT INTO `images` VALUES (95, 'Vivo Y36', 'https://cdn.tgdd.vn/Products/Images/42/311120/vivo-y36-den-1.jpg', NULL, 89);
INSERT INTO `images` VALUES (96, 'Vivo Y36', 'https://cdn.tgdd.vn/Products/Images/42/307203/vivo-y36-xanh-1-2.jpg', NULL, 90);
INSERT INTO `images` VALUES (97, 'Vivo Y36', 'https://cdn.tgdd.vn/Products/Images/42/307203/vivo-y36-den-1-2.jpg', NULL, 91);
INSERT INTO `images` VALUES (98, 'OPPO A57', 'https://cdn.tgdd.vn/Products/Images/42/285082/oppo-a57-4g-glr-xanh-1.jpg', NULL, 92);
INSERT INTO `images` VALUES (99, 'OPPO A57', 'https://cdn.tgdd.vn/Products/Images/42/285082/oppo-a57-vang-glr-1.jpg', NULL, 93);
INSERT INTO `images` VALUES (100, 'OPPO A57', 'https://cdn.tgdd.vn/Products/Images/42/307203/vivo-y36-den-1-2.jpg', NULL, 94);
INSERT INTO `images` VALUES (101, 'Realme C55', 'https://cdn.tgdd.vn/Products/Images/42/301603/realme-c55-gold-1.jpg', NULL, 95);
INSERT INTO `images` VALUES (102, 'Realme C55', 'https://cdn.tgdd.vn/Products/Images/42/301603/realme-c55-den-1.jpg', NULL, 96);
INSERT INTO `images` VALUES (103, 'Realme C55', 'https://cdn.tgdd.vn/Products/Images/42/301603/realme-c55-gold-1.jpg', NULL, 97);
INSERT INTO `images` VALUES (104, 'Realme C55', 'https://cdn.tgdd.vn/Products/Images/42/301603/realme-c55-den-1.jpg', NULL, 98);
INSERT INTO `images` VALUES (105, 'OPPO Reno10 5G', 'https://cdn.tgdd.vn/Products/Images/42/309722/oppo-reno10-xanh-128gb-1.jpg', NULL, 99);
INSERT INTO `images` VALUES (106, 'OPPO Reno10 5G', 'https://cdn.tgdd.vn/Products/Images/42/309722/oppo-reno10-xanh-128gb-1.jpg', NULL, 100);

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `source` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `ip_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `status` tinyint NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKgqy8beil5y4almtq1tiyofije`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FKgqy8beil5y4almtq1tiyofije` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of logs
-- ----------------------------

-- ----------------------------
-- Table structure for order_details
-- ----------------------------
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NULL DEFAULT NULL,
  `product_id` int NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKjyu2qbqt8gnvno9oe9j2s2ldk`(`order_id` ASC) USING BTREE,
  INDEX `FK4q98utpd73imf4yhttm3w0eax`(`product_id` ASC) USING BTREE,
  CONSTRAINT `FK4q98utpd73imf4yhttm3w0eax` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKjyu2qbqt8gnvno9oe9j2s2ldk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_details
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `address_id` int NULL DEFAULT NULL,
  `total` double NULL DEFAULT NULL,
  `payment_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `note` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `order_date` datetime(6) NULL DEFAULT NULL,
  `payment_date` datetime(6) NULL DEFAULT NULL,
  `status` tinyint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKhlglkvf5i60dv6dn397ethgpt`(`address_id` ASC) USING BTREE,
  INDEX `FK32ql8ubntj5uh44ph9659tiih`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKhlglkvf5i60dv6dn397ethgpt` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `provider_id` int NULL DEFAULT NULL,
  `original_price` double NOT NULL,
  `new_price` double NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  `color` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `screen` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `operating_system` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `camera` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `cpu` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `ram` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `storage` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `battery` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `produced_year` int NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `status` tinyint NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  `updated_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKtltawi3myjt9pi09219eiou1o`(`provider_id` ASC) USING BTREE,
  CONSTRAINT `FKtltawi3myjt9pi09219eiou1o` FOREIGN KEY (`provider_id`) REFERENCES `providers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES (1, 'iPhone 15 Pro Max', 2, 33190000, 34990000, 48, 'Titan xanh', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '256 GB', '4422mAh 20W', 2024, NULL, 2, '2024-11-03 21:22:15.000000', NULL);
INSERT INTO `products` VALUES (2, 'iPhone 15 Pro Max ', 2, 34190000, 34990000, 14, 'Titan tự nhiên', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '256 GB', '4422mAh 20W', 2024, NULL, 1, '2024-11-03 21:22:15.000000', NULL);
INSERT INTO `products` VALUES (3, 'iPhone 15 Pro Max', 2, 34190000, 34990000, 5, 'Titan trắng', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '256 GB', '4422mAh 20W', 2024, NULL, 2, '2024-11-03 21:22:15.000000', NULL);
INSERT INTO `products` VALUES (4, 'iPhone 15 Pro Max', 2, 33190000, 34990000, 102, 'Titan đen', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '256 GB', '4422mAh 20W', 2024, NULL, 1, '2024-11-03 21:22:15.000000', NULL);
INSERT INTO `products` VALUES (5, 'iPhone 15 Pro Max', 2, 39890000, 40990000, 1, 'Titan xanh', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '512 GB', '4422mAh 20W', 2024, NULL, 1, '2024-11-03 21:22:15.000000', NULL);
INSERT INTO `products` VALUES (6, 'iPhone 15 Pro Max', 2, 40890000, 40990000, 88, 'Titan tự nhiên', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '512 GB', '4422mAh 20W', 2024, NULL, 1, '2024-11-03 21:22:15.000000', NULL);
INSERT INTO `products` VALUES (7, 'iPhone 15 Pro Max', 2, 40890000, 40990000, 77, 'Titan trắng', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '512 GB', '4422mAh 20W', 2024, NULL, 1, '2024-11-03 21:22:15.000000', NULL);
INSERT INTO `products` VALUES (8, 'iPhone 15 Pro Max', 2, 39890000, 40990000, 20, 'Titan đen', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '512 GB', '4422mAh 20W', 2024, NULL, 1, '2024-11-03 21:22:15.000000', NULL);
INSERT INTO `products` VALUES (9, 'iPhone 15 Pro Max', 2, 44990000, 46990000, 51, 'Titan xanh', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '1TB', '4422mAh 20W', 2024, NULL, 1, '2024-11-03 21:22:15.000000', NULL);
INSERT INTO `products` VALUES (10, 'iPhone 15 Pro Max', 2, 46990000, 46990000, 18, 'Titan tự nhiên', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '1TB', '4422mAh 20W', 2024, NULL, 1, '2024-11-03 21:22:15.000000', NULL);
INSERT INTO `products` VALUES (11, 'iPhone 15 Pro Max', 2, 46990000, 46990000, 11, 'Titan trắng', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '1TB', '4422mAh 20W', 2024, NULL, 1, '2024-11-03 21:24:00.000000', NULL);
INSERT INTO `products` VALUES (12, 'iPhone 15 Pro Max', 2, 46990000, 46990000, 5, 'Titan đen', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '1TB', '4422mAh 20W', 2024, NULL, 1, '2024-11-03 21:24:00.000000', NULL);
INSERT INTO `products` VALUES (13, 'iPhone 15 Pro', 2, 28290000, 28990000, 20, 'Titan xanh', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '128 GB', '3274mAh 20W', 2024, NULL, 1, '2024-11-03 21:24:00.000000', NULL);
INSERT INTO `products` VALUES (14, 'iPhone 15 Pro', 2, 28290000, 28990000, 99, 'Titan tự nhiên', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '128 GB', '3274mAh 20W', 2024, NULL, 1, '2024-11-03 21:24:00.000000', NULL);
INSERT INTO `products` VALUES (15, 'iPhone 15 Pro', 2, 28290000, 28990000, 88, 'Titan trắng', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '128 GB', '3274mAh 20W', 2024, NULL, 1, '2024-11-03 21:24:00.000000', NULL);
INSERT INTO `products` VALUES (16, 'iPhone 15 Pro', 2, 28290000, 28990000, 76, 'Titan đen', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '128 GB', '3274mAh 20W', 2024, NULL, 1, '2024-11-03 21:24:00.000000', NULL);
INSERT INTO `products` VALUES (17, 'iPhone 15 Pro', 2, 31490000, 31990000, 45, 'Titan xanh', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '256 GB', '3274mAh 20W', 2024, NULL, 1, '2024-11-03 21:24:00.000000', NULL);
INSERT INTO `products` VALUES (18, 'iPhone 15 Pro', 2, 31490000, 31990000, 61, 'Titan tự nhiên', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '256 GB', '3274mAh 20W', 2024, NULL, 1, '2024-11-03 21:24:00.000000', NULL);
INSERT INTO `products` VALUES (19, 'iPhone 15 Pro', 2, 31490000, 31990000, 52, 'Titan trắng', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '256 GB', '3274mAh 20W', 2024, NULL, 1, '2024-11-03 21:24:00.000000', NULL);
INSERT INTO `products` VALUES (20, 'iPhone 15 Pro', 2, 31490000, 31990000, 30, 'Titan đen', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '256 GB', '3274mAh 20W', 2024, NULL, 1, '2024-11-03 21:24:00.000000', NULL);
INSERT INTO `products` VALUES (21, 'iPhone 15 Pro', 2, 35490000, 37990000, 21, 'Titan xanh', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '512 GB', '3274mAh 20W', 2024, NULL, 1, '2024-11-03 21:24:36.000000', NULL);
INSERT INTO `products` VALUES (22, 'iPhone 15 Pro', 2, 35490000, 37990000, 19, 'Titan tự nhiên', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '512 GB', '3274mAh 20W', 2024, NULL, 1, '2024-11-03 21:24:36.000000', NULL);
INSERT INTO `products` VALUES (23, 'iPhone 15 Pro', 2, 35490000, 37990000, 17, 'Titan trắng', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '512 GB', '3274mAh 20W', 2024, NULL, 1, '2024-11-03 21:24:36.000000', NULL);
INSERT INTO `products` VALUES (24, 'iPhone 15 Pro', 2, 35490000, 37990000, 15, 'Titan đen', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '512 GB', '3274mAh 20W', 2024, NULL, 1, '2024-11-03 21:24:36.000000', NULL);
INSERT INTO `products` VALUES (25, 'iPhone 15 Pro', 2, 41990000, 43990000, 11, 'Titan xanh', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '1 TB', '3274mAh 20W', 2024, NULL, 1, '2024-11-03 21:24:36.000000', NULL);
INSERT INTO `products` VALUES (26, 'iPhone 15 Pro', 2, 41990000, 43990000, 10, 'Titan tự nhiên', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '1 TB', '3274mAh 20W', 2024, NULL, 1, '2024-11-03 21:24:36.000000', NULL);
INSERT INTO `products` VALUES (27, 'iPhone 15 Pro', 2, 41990000, 43990000, 14, 'Titan trắng', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '1 TB', '3274mAh 20W', 2024, NULL, 1, '2024-11-03 21:24:36.000000', NULL);
INSERT INTO `products` VALUES (28, 'iPhone 15 Pro', 2, 41990000, 43990000, 14, 'Titan đen', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP, 12 MP', 'Apple A17 Pro 6 nhân', '8 GB', '1 TB', '3274mAh 20W', 2024, NULL, 1, '2024-11-03 21:24:36.000000', NULL);
INSERT INTO `products` VALUES (29, 'iPhone 15 Plus', 2, 25490000, 25990000, 14, 'Xanh lá nhạt', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP', 'Apple A16 Bionic', '6 GB', '128 GB', '4383mAh 20W', 2024, NULL, 1, '2024-11-03 21:24:36.000000', NULL);
INSERT INTO `products` VALUES (30, 'iPhone 15 Plus', 2, 25490000, 25990000, 14, 'Xanh dương nhạt', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP', 'Apple A16 Bionic', '6 GB', '128 GB', '4383mAh 20W', 2024, NULL, 1, '2024-11-03 21:24:36.000000', NULL);
INSERT INTO `products` VALUES (31, 'iPhone 15 Plus', 2, 25490000, 25990000, 15, 'Hồng nhạt', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP', 'Apple A16 Bionic', '6 GB', '128 GB', '4383mAh 20W', 2024, NULL, 1, '2024-11-03 21:25:01.000000', NULL);
INSERT INTO `products` VALUES (32, 'iPhone 15 Plus', 2, 25490000, 25990000, 88, 'Đen', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP', 'Apple A16 Bionic', '6 GB', '128 GB', '4383mAh 20W', 2024, NULL, 1, '2024-11-03 21:25:01.000000', NULL);
INSERT INTO `products` VALUES (33, 'iPhone 15 Plus', 2, 25490000, 25990000, 99, 'Vàng nhạt', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP', 'Apple A16 Bionic', '6 GB', '128 GB', '4383mAh 20W', 2024, NULL, 1, '2024-11-03 21:25:01.000000', NULL);
INSERT INTO `products` VALUES (34, 'iPhone 15 Plus', 2, 28790000, 28990000, 158, 'Xanh lá nhạt', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP', 'Apple A16 Bionic', '6 GB', '256 GB', '4383mAh 20W', 2024, NULL, 1, '2024-11-03 21:25:01.000000', NULL);
INSERT INTO `products` VALUES (35, 'iPhone 15 Plus', 2, 28790000, 28990000, 300, 'Xanh dương nhạt', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP', 'Apple A16 Bionic', '6 GB', '256 GB', '4383mAh 20W', 2024, NULL, 1, '2024-11-03 21:25:01.000000', NULL);
INSERT INTO `products` VALUES (36, 'iPhone 15 Plus', 2, 28790000, 28990000, 45, 'Hồng nhạt', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP', 'Apple A16 Bionic', '6 GB', '256 GB', '4383mAh 20W', 2024, NULL, 1, '2024-11-03 21:25:01.000000', NULL);
INSERT INTO `products` VALUES (37, 'iPhone 15 Plus', 2, 28790000, 28990000, 116, 'Đen', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP', 'Apple A16 Bionic', '6 GB', '256 GB', '4383mAh 20W', 2024, NULL, 1, '2024-11-03 21:25:01.000000', NULL);
INSERT INTO `products` VALUES (38, 'iPhone 15 Plus', 2, 28790000, 28990000, 22, 'Vàng nhạt', 'OLED 6.7\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP', 'Apple A16 Bionic', '6 GB', '256 GB', '4383mAh 20W', 2024, NULL, 1, '2024-11-03 21:25:01.000000', NULL);
INSERT INTO `products` VALUES (39, 'iPhone 15', 2, 21990000, 22990000, 77, 'Xanh lá nhạt', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP', 'Apple A16 Bionic', '6 GB', '128 GB', '3349mAh 20W', 2024, NULL, 1, '2024-11-03 21:25:01.000000', NULL);
INSERT INTO `products` VALUES (40, 'iPhone 15', 2, 21990000, 22990000, 74, 'Xanh dương nhạt', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP', 'Apple A16 Bionic', '6 GB', '128 GB', '3349mAh 20W', 2024, NULL, 1, '2024-11-03 21:25:01.000000', NULL);
INSERT INTO `products` VALUES (41, 'iPhone 15', 2, 21990000, 22990000, 71, 'Hồng nhạt', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP', 'Apple A16 Bionic', '6 GB', '128 GB', '3349mAh 20W', 2024, NULL, 1, '2024-11-03 21:25:54.000000', NULL);
INSERT INTO `products` VALUES (42, 'iPhone 15', 2, 21990000, 22990000, 72, 'Đen', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP', 'Apple A16 Bionic', '6 GB', '128 GB', '3349mAh 20W', 2024, NULL, 1, '2024-11-03 21:25:54.000000', NULL);
INSERT INTO `products` VALUES (43, 'iPhone 15', 2, 21990000, 22990000, 7, 'Vàng nhạt', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP', 'Apple A16 Bionic', '6 GB', '128 GB', '3349mAh 20W', 2024, NULL, 1, '2024-11-03 21:25:54.000000', NULL);
INSERT INTO `products` VALUES (44, 'iPhone 15', 2, 25790000, 25990000, 1, 'Xanh lá nhạt', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP', 'Apple A16 Bionic', '6 GB', '256 GB', '3349mAh 20W', 2024, NULL, 1, '2024-11-03 21:25:54.000000', NULL);
INSERT INTO `products` VALUES (45, 'iPhone 15', 2, 25790000, 25990000, 10, 'Xanh dương nhạt', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP', 'Apple A16 Bionic', '6 GB', '256 GB', '3349mAh 20W', 2024, NULL, 1, '2024-11-03 21:25:54.000000', NULL);
INSERT INTO `products` VALUES (46, 'iPhone 15', 2, 25790000, 25990000, 22, 'Hồng nhạt', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP', 'Apple A16 Bionic', '6 GB', '256 GB', '3349mAh 20W', 2024, NULL, 1, '2024-11-03 21:25:54.000000', NULL);
INSERT INTO `products` VALUES (47, 'iPhone 15', 2, 25790000, 25990000, 25, 'Đen', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP', 'Apple A16 Bionic', '6 GB', '256 GB', '3349mAh 20W', 2024, NULL, 1, '2024-11-03 21:25:54.000000', NULL);
INSERT INTO `products` VALUES (48, 'iPhone 15', 2, 25790000, 25990000, 66, 'Vàng nhạt', 'OLED 6.1\" Super Retina XDR', 'iOS 17', 'Chính 48 MP & Phụ 12 MP', 'Apple A16 Bionic', '6 GB', '256 GB', '3349mAh 20W', 2024, NULL, 1, '2024-11-03 21:25:54.000000', NULL);
INSERT INTO `products` VALUES (49, 'Samsung Galaxy A15', 1, 5590000, 5590000, 55, 'Vàng nhạt', 'Super AMOLED 6.5\" Full HD+', 'Android 13', 'Chính 50 MP & Phụ 5 MP, 2 MP', 'MediaTek Helio G99', '8 GB', '256 GB', '5000mAh 25W', 2023, NULL, 1, '2024-11-03 21:25:54.000000', NULL);
INSERT INTO `products` VALUES (50, 'Samsung Galaxy A15', 1, 5590000, 5590000, 41, 'Xanh nhạt', 'Super AMOLED 6.5\" Full HD+', 'Android 14', 'Chính 50 MP & Phụ 5 MP, 2 MP', 'MediaTek Helio G99', '8 GB', '256 GB', '5000mAh 25W', 2023, NULL, 1, '2024-11-03 21:25:54.000000', NULL);
INSERT INTO `products` VALUES (51, 'Samsung Galaxy A15', 1, 5590000, 5590000, 37, 'Đen', 'Super AMOLED 6.5\" Full HD+', 'Android 14', 'Chính 50 MP & Phụ 5 MP, 2 MP', 'MediaTek Helio G99', '8 GB', '256 GB', '5000mAh 25W', 2023, NULL, 1, '2024-11-03 21:26:21.000000', NULL);
INSERT INTO `products` VALUES (52, 'Samsung Galaxy A15', 1, 4990000, 4990000, 34, 'Vàng nhạt', 'Super AMOLED 6.5\" Full HD+', 'Android 14', 'Chính 50 MP & Phụ 5 MP, 2 MP', 'MediaTek Helio G99', '8 GB', '128 GB', '5000mAh 25W', 2023, NULL, 1, '2024-11-03 21:26:21.000000', NULL);
INSERT INTO `products` VALUES (53, 'Samsung Galaxy A15', 1, 4990000, 4990000, 12, 'Xanh nhạt', 'Super AMOLED 6.5\" Full HD+', 'Android 14', 'Chính 50 MP & Phụ 5 MP, 2 MP', 'MediaTek Helio G99', '8 GB', '128 GB', '5000mAh 25W', 2023, NULL, 1, '2024-11-03 21:26:21.000000', NULL);
INSERT INTO `products` VALUES (54, 'Samsung Galaxy A15', 1, 4990000, 4990000, 28, 'Đen', 'Super AMOLED 6.5\" Full HD+', 'Android 14', 'Chính 50 MP & Phụ 5 MP, 2 MP', 'MediaTek Helio G99', '8 GB', '128 GB', '5000mAh 25W', 2023, NULL, 1, '2024-11-03 21:26:21.000000', NULL);
INSERT INTO `products` VALUES (55, 'Samsung Galaxy A15 5G', 1, 6290000, 6290000, 91, 'Xanh nhạt', 'Super AMOLED 6.5\" Full HD+', 'Android 14', 'Chính 50 MP & Phụ 5 MP, 2 MP', 'MediaTek Dimensity 6100+', '8 GB', '128 GB', '5000mAh 25W', 2023, NULL, 1, '2024-11-03 21:26:21.000000', NULL);
INSERT INTO `products` VALUES (56, 'Samsung Galaxy A15 5G', 1, 6290000, 6290000, 110, 'Xanh đậm', 'Super AMOLED 6.5\" Full HD+', 'Android 14', 'Chính 50 MP & Phụ 5 MP, 2 MP', 'MediaTek Dimensity 6100+', '8 GB', '128 GB', '5000mAh 25W', 2023, NULL, 1, '2024-11-03 21:26:21.000000', NULL);
INSERT INTO `products` VALUES (57, 'Samsung Galaxy A15 5G', 1, 6290000, 6290000, 112, 'Đen', 'Super AMOLED 6.5\" Full HD+', 'Android 14', 'Chính 50 MP & Phụ 5 MP, 2 MP', 'MediaTek Dimensity 6100+', '8 GB', '128 GB', '5000mAh 25W', 2023, NULL, 1, '2024-11-03 21:26:21.000000', NULL);
INSERT INTO `products` VALUES (58, 'OPPO Find N3 Flip 5G', 3, 22990000, 22990000, 205, 'Hồng  ', 'AMOLED Chính 6.8\" & Phụ 3.26\"Full HD+', 'Android 13', 'Chính 50 MP & Phụ 48 MP, 32 MP', 'MediaTek Dimensity 9200 5G 8 nhân', '12 GB', '256 GB', '4300mAh 44W', 2023, NULL, 1, '2024-11-03 21:26:21.000000', NULL);
INSERT INTO `products` VALUES (59, 'OPPO Find N3 Flip 5G', 3, 22990000, 22990000, 11, 'Đen', 'AMOLED Chính 6.8\" & Phụ 3.26\"Full HD+', 'Android 13', 'Chính 50 MP & Phụ 48 MP, 32 MP', 'MediaTek Dimensity 9200 5G 8 nhân', '12 GB', '256 GB', '4300mAh 44W', 2023, NULL, 1, '2024-11-03 21:26:21.000000', NULL);
INSERT INTO `products` VALUES (60, 'OPPO Find N3 Flip 5G', 3, 22990000, 22990000, 14, 'Vàng đồng', 'AMOLED Chính 6.8\" & Phụ 3.26\"Full HD+', 'Android 13', 'Chính 50 MP & Phụ 48 MP, 32 MP', 'MediaTek Dimensity 9200 5G 8 nhân', '12 GB', '256 GB', '4300mAh 44W', 2023, NULL, 1, '2024-11-03 21:26:21.000000', NULL);
INSERT INTO `products` VALUES (61, 'Xiaomi Redmi 13C', 4, 2890000, 3090000, 14, 'Xanh lá  ', 'IPS LCD 6.74\" HD+', 'Android 13', 'Chính 50 MP & Phụ 2 MP', 'MediaTek Helio G85', '4 GB', '128 GB', '5000mAh 18W', 2023, NULL, 1, '2024-11-03 21:26:45.000000', NULL);
INSERT INTO `products` VALUES (62, 'Xiaomi Redmi 13C', 4, 2890000, 3090000, 14, 'Đen - Xanh dương', 'IPS LCD 6.74\" HD+', 'Android 13', 'Chính 50 MP & Phụ 2 MP', 'MediaTek Helio G85', '4 GB', '128 GB', '5000mAh 18W', 2023, NULL, 1, '2024-11-03 21:26:45.000000', NULL);
INSERT INTO `products` VALUES (63, 'Xiaomi Redmi 13C', 4, 2890000, 3090000, 15, 'Đen', 'IPS LCD 6.74\" HD+', 'Android 13', 'Chính 50 MP & Phụ 2 MP', 'MediaTek Helio G85', '4 GB', '128 GB', '5000mAh 18W', 2022, NULL, 1, '2024-11-03 21:26:45.000000', NULL);
INSERT INTO `products` VALUES (64, 'Vivo V29e 5G', 5, 9490000, 9990000, 21, 'Đen', 'AMOLED 6.67\" Full HD+', 'Android 13', 'Chính 64 MP & Phụ 8 MP', 'Snapdragon 695 5G', '12 GB', '256 GB', '4800mAh 44W', 2022, NULL, 1, '2024-11-03 21:26:45.000000', NULL);
INSERT INTO `products` VALUES (65, 'Vivo V29e 5G', 5, 9490000, 9990000, 375, 'Xanh dương nhạt', 'AMOLED 6.67\" Full HD+', 'Android 13', 'Chính 64 MP & Phụ 8 MP', 'Snapdragon 695 5G', '12 GB', '256 GB', '4800mAh 44W', 2022, NULL, 1, '2024-11-03 21:26:45.000000', NULL);
INSERT INTO `products` VALUES (66, 'Vivo V29e 5G', 5, 8690000, 8990000, 42, 'Đen', 'AMOLED 6.67\" Full HD+', 'Android 13', 'Chính 64 MP & Phụ 8 MP', 'Snapdragon 695 5G', '12 GB', '256 GB', '4800mAh 44W', 2022, NULL, 1, '2024-11-03 21:26:45.000000', NULL);
INSERT INTO `products` VALUES (67, 'Vivo V29e 5G', 5, 8690000, 8990000, 269, 'Xanh dương nhạt', 'AMOLED 6.67\" Full HD+', 'Android 13', 'Chính 64 MP & Phụ 8 MP', 'Snapdragon 695 5G', '12 GB', '256 GB', '4800mAh 44W', 2022, NULL, 1, '2024-11-03 21:26:45.000000', NULL);
INSERT INTO `products` VALUES (68, 'OPPO A38', 3, 4490000, 4490000, 165, 'Đen', 'IPS LCD 6.56\" HD+', 'Android 13', 'Chính 50 MP & Phụ 2 MP', 'MediaTek Helio G85', '6 GB', '128 GB', '5000mAh 33W', 2021, NULL, 1, '2024-11-03 21:26:45.000000', NULL);
INSERT INTO `products` VALUES (69, 'OPPO A38', 3, 4490000, 4490000, 270, 'Vàng đồng', 'IPS LCD 6.56\" HD+', 'Android 13', 'Chính 50 MP & Phụ 2 MP', 'MediaTek Helio G85', '6 GB', '128 GB', '5000mAh 33W', 2021, NULL, 1, '2024-11-03 21:26:45.000000', NULL);
INSERT INTO `products` VALUES (70, 'OPPO A38', 3, 4190000, 4490000, 333, 'Đen', 'IPS LCD 6.56\" HD+', 'Android 13', 'Chính 50 MP & Phụ 2 MP', 'MediaTek Helio G85', '4 GB', '128 GB', '5000mAh 33W', 2021, NULL, 1, '2024-11-03 21:26:45.000000', NULL);
INSERT INTO `products` VALUES (71, 'OPPO A38', 3, 4190000, 4490000, 175, 'Vàng đồng', 'IPS LCD 6.56\" HD+', 'Android 13', 'Chính 50 MP & Phụ 2 MP', 'MediaTek Helio G85', '4 GB', '128 GB', '5000mAh 33W', 2021, NULL, 1, '2024-11-03 21:27:10.000000', NULL);
INSERT INTO `products` VALUES (72, 'Xiaomi 13T 5G', 4, 10990000, 11990000, 14, 'Xanh dương  ', 'AMOLED 6.67\" 1.5K', 'Android 13', 'Chính 50 MP & Phụ 50 MP, 12 MP', 'MediaTek Dimensity 8200-Ultra', '8 GB', '256 GB', '5000mAh 67W', 2023, NULL, 1, '2024-11-03 21:27:10.000000', NULL);
INSERT INTO `products` VALUES (73, 'Xiaomi 13T 5G', 4, 10990000, 11990000, 14, 'Xanh lá', 'AMOLED 6.67\" 1.5K', 'Android 13', 'Chính 50 MP & Phụ 50 MP, 12 MP', 'MediaTek Dimensity 8200-Ultra', '8 GB', '256 GB', '5000mAh 67W', 2023, NULL, 1, '2024-11-03 21:27:10.000000', NULL);
INSERT INTO `products` VALUES (74, 'Xiaomi 13T 5G', 4, 10990000, 11990000, 14, 'Đen', 'AMOLED 6.67\" 1.5K', 'Android 13', 'Chính 50 MP & Phụ 50 MP, 12 MP', 'MediaTek Dimensity 8200-Ultra', '8 GB', '256 GB', '5000mAh 67W', 2023, NULL, 1, '2024-11-03 21:27:10.000000', NULL);
INSERT INTO `products` VALUES (75, 'Xiaomi 13T 5G', 4, 11990000, 12990000, 297, 'Xanh dương  ', 'AMOLED 6.67\" 1.5K', 'Android 13', 'Chính 50 MP & Phụ 50 MP, 12 MP', 'MediaTek Dimensity 8200-Ultra', '12 GB', '256 GB', '5000mAh 67W', 2023, NULL, 1, '2024-11-03 21:27:10.000000', NULL);
INSERT INTO `products` VALUES (76, 'Xiaomi 13T 5G', 4, 11990000, 12990000, 55, 'Xanh lá', 'AMOLED 6.67\" 1.5K', 'Android 13', 'Chính 50 MP & Phụ 50 MP, 12 MP', 'MediaTek Dimensity 8200-Ultra', '12 GB', '256 GB', '5000mAh 67W', 2023, NULL, 1, '2024-11-03 21:27:10.000000', NULL);
INSERT INTO `products` VALUES (77, 'Xiaomi 13T 5G', 4, 11990000, 12990000, 68, 'Đen', 'AMOLED 6.67\" 1.5K', 'Android 13', 'Chính 50 MP & Phụ 50 MP, 12 MP', 'MediaTek Dimensity 8200-Ultra', '12 GB', '256 GB', '5000mAh 67W', 2023, NULL, 1, '2024-11-03 21:27:10.000000', NULL);
INSERT INTO `products` VALUES (78, 'iPhone 11', 2, 10990000, 11790000, 71, 'Đen', 'IPS LCD 6.1\" Liquid Retina', 'iOS 15', '2 camera 12 MP', 'Apple A13 Bionic', '4 GB', '64 GB', '3110mAh 18W', 2021, NULL, 1, '2024-11-03 21:27:54.000000', NULL);
INSERT INTO `products` VALUES (79, 'iPhone 11', 2, 10990000, 11790000, 11, 'Trắng', 'IPS LCD 6.1\" Liquid Retina', 'iOS 15', '2 camera 12 MP', 'Apple A13 Bionic', '4 GB', '64 GB', '3110mAh 18W', 2021, NULL, 1, '2024-11-03 21:27:54.000000', NULL);
INSERT INTO `products` VALUES (80, 'iPhone 11', 2, 12490000, 13390000, 1, 'Đen', 'IPS LCD 6.1\" Liquid Retina', 'iOS 15', '2 camera 12 MP', 'Apple A13 Bionic', '4 GB', '128 GB', '3110mAh 18W', 2021, NULL, 1, '2024-11-03 21:27:54.000000', NULL);
INSERT INTO `products` VALUES (81, 'iPhone 11', 2, 12490000, 13390000, 15, 'Trắng', 'IPS LCD 6.1\" Liquid Retina', 'iOS 15', '2 camera 12 MP', 'Apple A13 Bionic', '4 GB', '128 GB', '3110mAh 18W', 2021, NULL, 1, '2024-11-03 21:28:18.000000', NULL);
INSERT INTO `products` VALUES (82, 'Samsung Galaxy A25 5G', 1, 6590000, 6590000, 15, 'Xanh dương', 'Super AMOLED 6.5\" Full HD+', 'Android 14', 'Chính 50 MP & Phụ 5 MP, 2 MP', 'Exynos 1280', '6 GB', '256 GB', '5000mAh 25W', 2025, NULL, 1, '2024-11-03 21:28:18.000000', NULL);
INSERT INTO `products` VALUES (83, 'Samsung Galaxy A25 5G', 1, 6590000, 6590000, 32, 'Vàng', 'Super AMOLED 6.5\" Full HD+', 'Android 14', 'Chính 50 MP & Phụ 5 MP, 2 MP', 'Exynos 1280', '6 GB', '256 GB', '5000mAh 25W', 2025, NULL, 1, '2024-11-03 21:28:18.000000', NULL);
INSERT INTO `products` VALUES (84, 'Samsung Galaxy A25 5G', 1, 6590000, 6590000, 14, 'Đen', 'Super AMOLED 6.5\" Full HD+', 'Android 14', 'Chính 50 MP & Phụ 5 MP, 2 MP', 'Exynos 1280', '6 GB', '256 GB', '5000mAh 25W', 2025, NULL, 1, '2024-11-03 21:28:18.000000', NULL);
INSERT INTO `products` VALUES (85, 'Samsung Galaxy A25 5G', 1, 6990000, 6990000, 15, 'Xanh dương', 'Super AMOLED 6.5\" Full HD+', 'Android 14', 'Chính 50 MP & Phụ 5 MP, 2 MP', 'Exynos 1280', '8 GB', '128 GB', '5000mAh 25W', 2025, NULL, 1, '2024-11-03 21:28:18.000000', NULL);
INSERT INTO `products` VALUES (86, 'Samsung Galaxy A25 5G', 1, 6990000, 6990000, 55, 'Vàng', 'Super AMOLED 6.5\" Full HD+', 'Android 14', 'Chính 50 MP & Phụ 5 MP, 2 MP', 'Exynos 1280', '8 GB', '128 GB', '5000mAh 25W', 2025, NULL, 1, '2024-11-03 21:28:18.000000', NULL);
INSERT INTO `products` VALUES (87, 'Samsung Galaxy A25 5G', 1, 6990000, 6990000, 55, 'Đen', 'Super AMOLED 6.5\" Full HD+', 'Android 14', 'Chính 50 MP & Phụ 5 MP, 2 MP', 'Exynos 1280', '8 GB', '128 GB', '5000mAh 25W', 2022, NULL, 1, '2024-11-03 21:28:18.000000', NULL);
INSERT INTO `products` VALUES (88, 'Vivo Y36', 5, 5190000, 5990000, 55, 'Xanh', 'IPS LCD 6.64\" Full HD+', 'Android 13', 'Chính 50 MP & Phụ 2 MP', 'Snapdragon 680', '8 GB', '128 GB', '5000mAh 44W', 2022, NULL, 1, '2024-11-03 21:28:18.000000', NULL);
INSERT INTO `products` VALUES (89, 'Vivo Y36', 5, 5190000, 5990000, 11, 'Đen', 'IPS LCD 6.64\" Full HD+', 'Android 13', 'Chính 50 MP & Phụ 2 MP', 'Snapdragon 680', '8 GB', '128 GB', '5000mAh 44W', 2022, NULL, 1, '2024-11-03 21:28:18.000000', NULL);
INSERT INTO `products` VALUES (90, 'Vivo Y36', 5, 6190000, 6990000, 22, 'Xanh ngọc', 'IPS LCD 6.64\" Full HD+', 'Android 13', 'Chính 50 MP & Phụ 2 MP', 'Snapdragon 680', '8 GB', '256 GB', '5000mAh 44W', 2022, NULL, 1, '2024-11-03 21:28:18.000000', NULL);
INSERT INTO `products` VALUES (91, 'Vivo Y36', 5, 6190000, 6990000, 44, 'Đen', 'IPS LCD 6.64\" Full HD+', 'Android 13', 'Chính 50 MP & Phụ 2 MP', 'Snapdragon 680', '8 GB', '256 GB', '5000mAh 44W', 2022, NULL, 1, '2024-11-03 21:28:36.000000', NULL);
INSERT INTO `products` VALUES (92, 'OPPO A57', 3, 4390000, 4990000, 20, 'Xanh lá', 'IPS LCD 6.56\" HD+', 'Android 12', 'Chính 13 MP & Phụ 2 MP', 'MediaTek Helio G35', '4 GB', '128 GB', '5000mAh 33W', 2023, NULL, 1, '2024-11-03 21:28:36.000000', NULL);
INSERT INTO `products` VALUES (93, 'OPPO A57', 3, 4390000, 4990000, 22, 'Vàng', 'IPS LCD 6.56\" HD+', 'Android 12', 'Chính 13 MP & Phụ 2 MP', 'MediaTek Helio G35', '4 GB', '128 GB', '5000mAh 33W', 2023, NULL, 1, '2024-11-03 21:28:36.000000', NULL);
INSERT INTO `products` VALUES (94, 'OPPO A57', 3, 4390000, 4990000, 77, 'Đen', 'IPS LCD 6.56\" HD+', 'Android 12', 'Chính 13 MP & Phụ 2 MP', 'MediaTek Helio G35', '4 GB', '128 GB', '5000mAh 33W', 2023, NULL, 1, '2024-11-03 21:28:36.000000', NULL);
INSERT INTO `products` VALUES (95, 'Realme C55', 6, 4190000, 4990000, 78, 'Vàng', 'IPS LCD 6.72\" Full HD+', 'Android 13', 'Chính 64 MP & Phụ 2 MP', 'MediaTek Helio G88', '6 GB', '128 GB', '5000mAh 33W', 2025, NULL, 1, '2024-11-03 21:28:36.000000', NULL);
INSERT INTO `products` VALUES (96, 'Realme C55', 6, 4190000, 4990000, 44, 'Đen', 'IPS LCD 6.72\" Full HD+', 'Android 13', 'Chính 64 MP & Phụ 2 MP', 'MediaTek Helio G88', '6 GB', '128 GB', '5000mAh 33W', 2025, NULL, 1, '2024-11-03 21:28:36.000000', NULL);
INSERT INTO `products` VALUES (97, 'Realme C55', 6, 5290000, 5990000, 15, 'Vàng', 'IPS LCD 6.72\" Full HD+', 'Android 13', 'Chính 64 MP & Phụ 2 MP', 'MediaTek Helio G88', '6 GB', '256 GB', '5000mAh 33W', 2025, NULL, 1, '2024-11-03 21:28:36.000000', NULL);
INSERT INTO `products` VALUES (98, 'Realme C55', 6, 5290000, 5990000, 16, 'Đen', 'IPS LCD 6.72\" Full HD+', 'Android 13', 'Chính 64 MP & Phụ 2 MP', 'MediaTek Helio G88', '6 GB', '256 GB', '5000mAh 33W', 2025, NULL, 1, '2024-11-03 21:28:36.000000', NULL);
INSERT INTO `products` VALUES (99, 'OPPO Reno10 5G', 3, 10290000, 10990000, 10, 'Xanh', 'AMOLED 6.7\" Full HD+', 'Android 13', 'Chính 64 MP & Phụ 32 MP, 8 MP', 'MediaTek Dimensity 7050 5G 8 nhân', '8 GB', '256 GB', '5000mAh 67W', 2023, NULL, 1, '2024-11-03 21:28:36.000000', NULL);
INSERT INTO `products` VALUES (100, 'OPPO Reno10 5G', 3, 9290000, 9990000, 11, 'Xanh', 'AMOLED 6.7\" Full HD+', 'Android 13', 'Chính 64 MP & Phụ 32 MP, 8 MP', 'MediaTek Dimensity 7050 5G 8 nhân', '8 GB', '128 GB', '5000mAh 67W', 2023, NULL, 1, '2024-11-03 21:28:36.000000', NULL);

-- ----------------------------
-- Table structure for providers
-- ----------------------------
DROP TABLE IF EXISTS `providers`;
CREATE TABLE `providers`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` tinyint NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  `updated_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of providers
-- ----------------------------
INSERT INTO `providers` VALUES (1, 'Samsung', 1, '2024-10-11 21:25:02.000000', NULL);
INSERT INTO `providers` VALUES (2, 'Apple', 1, '2024-10-11 21:25:05.000000', NULL);
INSERT INTO `providers` VALUES (3, 'OPPO', 1, '2024-10-11 21:25:08.000000', NULL);
INSERT INTO `providers` VALUES (4, 'Xiaomi', 1, '2024-10-11 21:25:10.000000', NULL);
INSERT INTO `providers` VALUES (5, 'Vivo', 1, '2024-10-11 21:25:12.000000', NULL);
INSERT INTO `providers` VALUES (6, 'Realme', 1, '2024-10-11 21:25:15.000000', NULL);

-- ----------------------------
-- Table structure for reviews
-- ----------------------------
DROP TABLE IF EXISTS `reviews`;
CREATE TABLE `reviews`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `product_id` int NULL DEFAULT NULL,
  `rating` double NULL DEFAULT NULL,
  `comment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `status` tinyint NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  `updated_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKpl51cejpw4gy5swfar8br9ngi`(`product_id` ASC) USING BTREE,
  INDEX `FKcgy7qjc1r99dp117y9en6lxye`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FKcgy7qjc1r99dp117y9en6lxye` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKpl51cejpw4gy5swfar8br9ngi` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of reviews
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role` int NULL DEFAULT NULL,
  `status` tinyint NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  `updated_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
