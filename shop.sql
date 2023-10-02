/*
 Navicat Premium Data Transfer

 Source Server         : ldl
 Source Server Type    : MySQL
 Source Server Version : 50554
 Source Host           : localhost:3306
 Source Schema         : shop

 Target Server Type    : MySQL
 Target Server Version : 50554
 File Encoding         : 65001

 Date: 22/09/2023 20:51:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shouhuoren` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `isdefault` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_address
-- ----------------------------
INSERT INTO `t_address` VALUES (1, 'wyb', '0805', '河南', 2, '是');
INSERT INTO `t_address` VALUES (2, '1', '1', '重庆', 9, '否');
INSERT INTO `t_address` VALUES (4, 'xz', '1005', '重庆', 13, '否');
INSERT INTO `t_address` VALUES (18, '罗冬丽', '15884930268', '四川省成都市', 16, '是');
INSERT INTO `t_address` VALUES (21, '王一博', '1005', '河南洛阳', 17, '否');
INSERT INTO `t_address` VALUES (22, '王一博', '1005', '重庆', 17, '否');
INSERT INTO `t_address` VALUES (25, '罗冬丽', '13219843250', '四川省广安市', 16, '否');
INSERT INTO `t_address` VALUES (26, '王一博', '13219843250', '重庆洪崖洞', 17, '否');
INSERT INTO `t_address` VALUES (27, '罗冬丽', '13219843250', '四川省彭州市', 16, '否');

-- ----------------------------
-- Table structure for t_goods_info
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_info`;
CREATE TABLE `t_goods_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `goods_description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `goods_pic` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `goods_price` double(20, 2) DEFAULT NULL,
  `goods_stock` int(11) DEFAULT NULL,
  `goods_price_off` double(20, 2) DEFAULT NULL,
  `goods_discount` double(11, 2) DEFAULT NULL,
  `goods_fatherid` int(11) DEFAULT NULL,
  `goods_parentid` int(11) DEFAULT NULL,
  `isdelete` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_goods_info
-- ----------------------------
INSERT INTO `t_goods_info` VALUES (1, '铜锣烧aa', '回头客铜锣烧160g红豆味西式糕点手撕小口袋面包早餐 好好吃哦！aaaaaaaaaaaa', 'tongluoshaotj.png', 6.50, 8, 90.00, 0.50, 7, 1, '否');
INSERT INTO `t_goods_info` VALUES (2, '瑞士卷蛋糕', '达利园 瑞士卷蛋糕（芒果味）240g/袋（12枚）', 'ruishi.jpg', 10.90, 7, 5.45, 0.50, 7, 1, '否');
INSERT INTO `t_goods_info` VALUES (3, '奥利奥饼干', '奥利奥巧克力味夹心 12*3片促销装 325g+65g', 'aoliao.jpg', 19.75, 208, 15.80, 0.80, 9, 2, '否');
INSERT INTO `t_goods_info` VALUES (4, '太平饼干', '亿滋 饼干太平梳打奶盐味100g办公室休闲零食茶点', 'taiping.jpg', 3.30, 23, 3.30, 1.00, 9, 2, '否');
INSERT INTO `t_goods_info` VALUES (5, '乐事薯片', '百事食品 乐事薯片美国经典原味145克 膨化休闲零食', 'leshi.jpg', 9.90, 67, 9.90, 1.00, 10, 2, '否');
INSERT INTO `t_goods_info` VALUES (6, '蒸奶香蛋糕', '港荣 蒸奶香蛋糕 1kg/箱 整箱装', 'zhengnaixiang.jpg', 38.20, 1100, 38.20, 1.00, 7, 1, '否');
INSERT INTO `t_goods_info` VALUES (7, '蔓越莓西饼', '巴拿米 蔓越莓西饼 170g/袋', 'xibing.jpg', 10.90, 278, 10.90, 1.00, 8, 1, '否');
INSERT INTO `t_goods_info` VALUES (8, '凤梨酥', '百草味 凤梨酥 300g/盒', 'fenglisu.jpg', 14.50, 111, 14.50, 1.00, 8, 1, '否');
INSERT INTO `t_goods_info` VALUES (9, '麻薯', '良品铺子 手造麻薯 抹茶味 150g/袋 X 2', 'mashu.jpg', 12.50, 323, 12.50, 1.00, 8, 1, '否');
INSERT INTO `t_goods_info` VALUES (10, '核桃饼', '好吃点 香脆核桃饼 108g/袋', 'haochidian.jpg', 4.40, 434, 4.40, 1.00, 9, 2, '否');
INSERT INTO `t_goods_info` VALUES (11, '威化饼', '嘉顿 柠檬威化 200g/袋', 'jiadun.jpg', 7.50, 4333, 7.50, 1.00, 9, 2, '否');
INSERT INTO `t_goods_info` VALUES (12, '大波浪薯片', '乐事 大波浪薯片 香脆烤鸡翅味 70g/袋', 'dalangshupian.jpg', 3.90, 344, 3.90, 1.00, 10, 2, '否');
INSERT INTO `t_goods_info` VALUES (14, '热狗', 'Smithfield 双汇史密斯 美式香肠 热狗肠火腿肠烤肠香肠 熏肠 396克*3袋', 'regou.jpg', 79.90, 50, 79.90, 1.00, 12, 3, '否');
INSERT INTO `t_goods_info` VALUES (15, '牛肉干', '淘豆 五香牛肉干100g/袋', 'niurougan.jpg', 19.90, 100, 19.90, 1.00, 11, 3, '否');
INSERT INTO `t_goods_info` VALUES (16, '猪肉脯', '百草味 精制猪肉脯 200g/袋', 'zhurugan.jpg', 17.90, 5000, 14.30, 0.80, 12, 3, '否');
INSERT INTO `t_goods_info` VALUES (23, '梅尼耶干蛋糕', '盼盼 梅尼耶干蛋糕1000g箱装 奶香味干蛋糕饼干 正品茶点零食 美味早餐下午茶', '5.jpg', 49.90, 1000, 49.90, 1.00, 7, 1, '否');
INSERT INTO `t_goods_info` VALUES (24, '好丽友', '好丽友 派 巧克力味涂饰蛋类芯饼 680g/盒 20枚', 'haoliyou.jpg', 26.50, 500, 26.50, 1.00, 7, 1, '否');
INSERT INTO `t_goods_info` VALUES (25, '乐事青瓜薯片', '乐事 无限薯片（清新清爽翡翠黄瓜味）104g/筒 X 3', 'leshiqinggua.jpg', 19.50, 123, 19.50, 1.00, 10, 2, '否');
INSERT INTO `t_goods_info` VALUES (26, '可比克薯片', '可比克 薯片 烧烤味 45g/罐', 'kebike.jpg', 4.20, 356, 4.20, 1.00, 10, 2, '否');
INSERT INTO `t_goods_info` VALUES (27, '电脑', '非常好用', '7.jpg', 5000.00, 4, 4000.00, 0.80, 0, 17, '是');
INSERT INTO `t_goods_info` VALUES (28, '冰箱', '非常好看，哈哈哈哈', '8.jpg', 7000.00, 0, 6300.00, 0.90, 0, 17, '否');
INSERT INTO `t_goods_info` VALUES (29, '甜品', '好看。。。。。', '4.jpg', 10000.00, 0, 5000.00, 0.50, 0, 16, '否');

-- ----------------------------
-- Table structure for t_goods_type
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_type`;
CREATE TABLE `t_goods_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gtype_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gtype_parentid` int(11) DEFAULT NULL,
  `gtype_pic` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_goods_type
-- ----------------------------
INSERT INTO `t_goods_type` VALUES (1, '蛋糕类', 0, 'cake.png');
INSERT INTO `t_goods_type` VALUES (2, '饼干类', 0, 'cookies.png');
INSERT INTO `t_goods_type` VALUES (3, '熟食类', 0, 'meat.png');
INSERT INTO `t_goods_type` VALUES (4, '素食类', 0, 'bamboo.png');
INSERT INTO `t_goods_type` VALUES (5, '坚果类', 0, 'nut.png');
INSERT INTO `t_goods_type` VALUES (6, '糖果类', 0, 'candy.png');
INSERT INTO `t_goods_type` VALUES (7, '蛋糕', 1, NULL);
INSERT INTO `t_goods_type` VALUES (8, '点心', 1, NULL);
INSERT INTO `t_goods_type` VALUES (9, '饼干', 2, NULL);
INSERT INTO `t_goods_type` VALUES (10, '薯片', 2, NULL);
INSERT INTO `t_goods_type` VALUES (11, '牛肉干', 3, NULL);
INSERT INTO `t_goods_type` VALUES (12, '猪肉干', 3, NULL);
INSERT INTO `t_goods_type` VALUES (16, '洗漱用品', 0, NULL);

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `o_sendtype` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `o_paytype` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `o_paycount` double(20, 0) DEFAULT NULL,
  `o_orderdate` datetime DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `o_shperson` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `o_shphone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `o_shaddress` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (1, '韵达', '支付宝', 2, '2023-09-20 19:32:12', 16, '罗冬丽', '13219843250', '四川省成都市武侯区');
INSERT INTO `t_order` VALUES (2, '申通', '微信', 1, '2023-09-20 19:57:51', 16, '罗冬丽', '13219843250', '四川省成都市武侯区');
INSERT INTO `t_order` VALUES (3, '韵达', '支付宝', 1, '2023-09-20 20:00:36', 16, '罗冬丽', '13219843250', '四川省成都市武侯区');
INSERT INTO `t_order` VALUES (4, '圆通', '银联', 1, '2023-09-20 20:06:00', 16, '罗冬丽', '13219843250', '四川省成都市');
INSERT INTO `t_order` VALUES (5, '韵达', '支付宝', 1, '2023-09-20 20:30:43', 16, '罗冬丽', '13219843250', '四川省成都市');
INSERT INTO `t_order` VALUES (6, '韵达', '支付宝', 1, '2023-09-20 20:30:55', 16, '罗冬丽', '13219843250', '四川省成都市');
INSERT INTO `t_order` VALUES (7, '圆通', '银联', 1, '2023-09-20 20:31:01', 16, '罗冬丽', '13219843250', '四川省成都市武侯区');
INSERT INTO `t_order` VALUES (8, '韵达', '支付宝', 2, '2023-09-20 20:37:55', 16, '罗冬丽', '13219843250', '四川省成都市');
INSERT INTO `t_order` VALUES (9, '韵达', '银联', 2, '2023-09-20 20:39:22', 16, '罗冬丽', '15884930268', '四川省成都市');
INSERT INTO `t_order` VALUES (10, '韵达', '支付宝', 2, '2023-09-20 20:39:33', 16, '罗冬丽', '13219843250', '四川省成都市武侯区');
INSERT INTO `t_order` VALUES (13, '圆通', '银联', 2, '2023-09-20 21:29:38', 17, '王一博', '1005', '重庆');
INSERT INTO `t_order` VALUES (20, '', '', 1, '2023-09-21 22:56:46', 16, '罗冬丽', '13219843250', '四川省成都市');
INSERT INTO `t_order` VALUES (21, '', '', 1, '2023-09-21 22:56:55', 16, '罗冬丽', '13219843250', '四川省成都市');
INSERT INTO `t_order` VALUES (22, '韵达', '支付宝', 1, '2023-09-21 23:02:58', 16, '罗冬丽', '13219843250', '四川省成都市');
INSERT INTO `t_order` VALUES (23, '申通', '微信', 1, '2023-09-21 23:08:13', 16, '罗冬丽', '13219843250', '四川省广安市');
INSERT INTO `t_order` VALUES (24, '申通', '微信', 1, '2023-09-21 23:15:36', 16, '罗冬丽', '13219843250', '四川省成都市');
INSERT INTO `t_order` VALUES (25, '申通', '微信', 1, '2023-09-21 23:22:34', 16, '罗冬丽', '13219843250', '四川省成都市');
INSERT INTO `t_order` VALUES (26, '申通', '微信', 2, '2023-09-21 23:24:57', 16, '罗冬丽', '15884930268', '四川省成都市');
INSERT INTO `t_order` VALUES (27, '申通', '微信', 1, '2023-09-21 23:31:40', 16, '罗冬丽', '15884930268', '四川省成都市');
INSERT INTO `t_order` VALUES (28, '韵达', '支付宝', 2, '2023-09-21 23:42:06', 16, '罗冬丽', '15884930268', '四川省成都市');
INSERT INTO `t_order` VALUES (29, '申通', '微信', 1, '2023-09-21 23:49:38', 16, '罗冬丽', '15884930268', '四川省成都市');
INSERT INTO `t_order` VALUES (30, '申通', '微信', 1, '2023-09-21 23:50:33', 16, '罗冬丽', '15884930268', '四川省成都市');
INSERT INTO `t_order` VALUES (31, '申通', '微信', 1, '2023-09-21 23:53:00', 16, '罗冬丽', '15884930268', '四川省成都市');
INSERT INTO `t_order` VALUES (32, '圆通', '银联', 1, '2023-09-22 00:05:31', 16, '罗冬丽', '15884930268', '四川省成都市');
INSERT INTO `t_order` VALUES (33, '韵达', '支付宝', 1, '2023-09-22 00:07:12', 16, '罗冬丽', '13219843250', '四川省广安市');
INSERT INTO `t_order` VALUES (34, '顺丰', '支付宝', 1, '2023-09-22 00:08:33', 16, '罗冬丽', '13219843250', '四川省广安市');
INSERT INTO `t_order` VALUES (35, '韵达', '支付宝', 2, '2023-09-22 00:16:17', 16, '罗冬丽', '15884930268', '四川省成都市');
INSERT INTO `t_order` VALUES (36, '申通', '微信', 1, '2023-09-22 00:18:21', 16, '罗冬丽', '13219843250', '四川省广安市');
INSERT INTO `t_order` VALUES (37, '韵达', '支付宝', 1, '2023-09-22 00:22:19', 16, '罗冬丽', '15884930268', '四川省成都市');
INSERT INTO `t_order` VALUES (38, '申通', '微信', 1, '2023-09-22 00:24:05', 16, '罗冬丽', '15884930268', '四川省成都市');
INSERT INTO `t_order` VALUES (39, '申通', '微信', 2, '2023-09-22 00:24:34', 16, '罗冬丽', '13219843250', '四川省广安市');
INSERT INTO `t_order` VALUES (40, '', '', 1, '2023-09-22 00:28:25', 16, '罗冬丽', '15884930268', '四川省成都市');
INSERT INTO `t_order` VALUES (41, '韵达', '支付宝', 2, '2023-09-22 09:01:19', 16, '罗冬丽', '15884930268', '四川省成都市');

-- ----------------------------
-- Table structure for t_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_order_detail`;
CREATE TABLE `t_order_detail`  (
  `goods_date` datetime DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `o_orderid` int(11) DEFAULT NULL,
  `goodsid` int(11) DEFAULT NULL,
  `goodsname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `goodsprice` double(20, 2) DEFAULT NULL,
  `goods_description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `goodsnum` int(20) DEFAULT NULL,
  `goodspic` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `goods_total_price` double(20, 2) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_ORDER`(`o_orderid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_order_detail
-- ----------------------------
INSERT INTO `t_order_detail` VALUES ('2023-09-20 19:32:12', 1, 1, 1, '铜锣烧aa', 90.00, '回头客铜锣烧160g红豆味西式糕点手撕小口袋面包早餐 好好吃哦！aaaaaaaaaaaa', 2, 'tongluoshaotj.png', 180.00);
INSERT INTO `t_order_detail` VALUES ('2023-09-20 19:57:51', 2, 2, 2, '瑞士卷蛋糕', 5.45, '达利园 瑞士卷蛋糕（芒果味）240g/袋（12枚）', 1, 'ruishi.jpg', 5.45);
INSERT INTO `t_order_detail` VALUES ('2023-09-20 20:00:36', 3, 3, 3, '奥利奥饼干', 15.80, '奥利奥巧克力味夹心 12*3片促销装 325g+65g', 1, 'aoliao.jpg', 15.80);
INSERT INTO `t_order_detail` VALUES ('2023-09-20 20:06:00', 4, 4, 2, '瑞士卷蛋糕', 5.45, '达利园 瑞士卷蛋糕（芒果味）240g/袋（12枚）', 1, 'ruishi.jpg', 5.45);
INSERT INTO `t_order_detail` VALUES ('2023-09-20 20:30:43', 5, 5, 5, '乐事薯片', 9.90, '百事食品 乐事薯片美国经典原味145克 膨化休闲零食', 1, 'leshi.jpg', 9.90);
INSERT INTO `t_order_detail` VALUES ('2023-09-20 20:30:55', 6, 6, 5, '乐事薯片', 9.90, '百事食品 乐事薯片美国经典原味145克 膨化休闲零食', 1, 'leshi.jpg', 9.90);
INSERT INTO `t_order_detail` VALUES ('2023-09-20 20:31:01', 7, 7, 5, '乐事薯片', 9.90, '百事食品 乐事薯片美国经典原味145克 膨化休闲零食', 1, 'leshi.jpg', 9.90);
INSERT INTO `t_order_detail` VALUES ('2023-09-20 20:37:55', 8, 8, 1, '铜锣烧aa', 90.00, '回头客铜锣烧160g红豆味西式糕点手撕小口袋面包早餐 好好吃哦！aaaaaaaaaaaa', 2, 'tongluoshaotj.png', 180.00);
INSERT INTO `t_order_detail` VALUES ('2023-09-20 20:39:22', 9, 9, 1, '铜锣烧aa', 90.00, '回头客铜锣烧160g红豆味西式糕点手撕小口袋面包早餐 好好吃哦！aaaaaaaaaaaa', 2, 'tongluoshaotj.png', 180.00);
INSERT INTO `t_order_detail` VALUES ('2023-09-20 20:39:33', 10, 10, 1, '铜锣烧aa', 90.00, '回头客铜锣烧160g红豆味西式糕点手撕小口袋面包早餐 好好吃哦！aaaaaaaaaaaa', 2, 'tongluoshaotj.png', 180.00);
INSERT INTO `t_order_detail` VALUES ('2023-09-20 21:29:38', 13, 13, 3, '奥利奥饼干', 15.80, '奥利奥巧克力味夹心 12*3片促销装 325g+65g', 2, 'aoliao.jpg', 31.60);
INSERT INTO `t_order_detail` VALUES ('2023-09-21 22:56:46', 20, 20, 2, '瑞士卷蛋糕', 5.45, '达利园 瑞士卷蛋糕（芒果味）240g/袋（12枚）', 1, 'ruishi.jpg', 5.45);
INSERT INTO `t_order_detail` VALUES ('2023-09-21 22:56:55', 21, 21, 2, '瑞士卷蛋糕', 5.45, '达利园 瑞士卷蛋糕（芒果味）240g/袋（12枚）', 1, 'ruishi.jpg', 5.45);
INSERT INTO `t_order_detail` VALUES ('2023-09-21 23:02:58', 22, 22, 6, '蒸奶香蛋糕', 38.20, '港荣 蒸奶香蛋糕 1kg/箱 整箱装', 1, 'zhengnaixiang.jpg', 38.20);
INSERT INTO `t_order_detail` VALUES ('2023-09-21 23:08:13', 23, 23, 15, '牛肉干', 19.90, '淘豆 五香牛肉干100g/袋', 1, 'niurougan.jpg', 19.90);
INSERT INTO `t_order_detail` VALUES ('2023-09-21 23:15:36', 24, 24, 1, '铜锣烧aa', 90.00, '回头客铜锣烧160g红豆味西式糕点手撕小口袋面包早餐 好好吃哦！aaaaaaaaaaaa', 1, 'tongluoshaotj.png', 90.00);
INSERT INTO `t_order_detail` VALUES ('2023-09-21 23:22:34', 25, 25, 24, '好丽友', 26.50, '好丽友 派 巧克力味涂饰蛋类芯饼 680g/盒 20枚', 1, 'haoliyou.jpg', 26.50);
INSERT INTO `t_order_detail` VALUES ('2023-09-21 23:24:57', 26, 26, 3, '奥利奥饼干', 15.80, '奥利奥巧克力味夹心 12*3片促销装 325g+65g', 2, 'aoliao.jpg', 31.60);
INSERT INTO `t_order_detail` VALUES ('2023-09-21 23:31:40', 27, 27, 3, '奥利奥饼干', 15.80, '奥利奥巧克力味夹心 12*3片促销装 325g+65g', 1, 'aoliao.jpg', 15.80);
INSERT INTO `t_order_detail` VALUES ('2023-09-21 23:42:06', 28, 28, 4, '太平饼干', 3.30, '亿滋 饼干太平梳打奶盐味100g办公室休闲零食茶点', 2, 'taiping.jpg', 6.60);
INSERT INTO `t_order_detail` VALUES ('2023-09-21 23:49:38', 29, 29, 3, '奥利奥饼干', 15.80, '奥利奥巧克力味夹心 12*3片促销装 325g+65g', 1, 'aoliao.jpg', 15.80);
INSERT INTO `t_order_detail` VALUES ('2023-09-21 23:50:33', 30, 30, 1, '铜锣烧aa', 90.00, '回头客铜锣烧160g红豆味西式糕点手撕小口袋面包早餐 好好吃哦！aaaaaaaaaaaa', 1, 'tongluoshaotj.png', 90.00);
INSERT INTO `t_order_detail` VALUES ('2023-09-21 23:53:00', 31, 31, 3, '奥利奥饼干', 15.80, '奥利奥巧克力味夹心 12*3片促销装 325g+65g', 1, 'aoliao.jpg', 15.80);
INSERT INTO `t_order_detail` VALUES ('2023-09-22 00:05:31', 32, 32, 1, '铜锣烧aa', 90.00, '回头客铜锣烧160g红豆味西式糕点手撕小口袋面包早餐 好好吃哦！aaaaaaaaaaaa', 1, 'tongluoshaotj.png', 90.00);
INSERT INTO `t_order_detail` VALUES ('2023-09-22 00:07:12', 33, 33, 25, '乐事青瓜薯片', 19.50, '乐事 无限薯片（清新清爽翡翠黄瓜味）104g/筒 X 3', 1, 'leshiqinggua.jpg', 19.50);
INSERT INTO `t_order_detail` VALUES ('2023-09-22 00:08:33', 34, 34, 7, '蔓越莓西饼', 10.90, '巴拿米 蔓越莓西饼 170g/袋', 1, 'xibing.jpg', 10.90);
INSERT INTO `t_order_detail` VALUES ('2023-09-22 00:16:17', 35, 35, 24, '好丽友', 26.50, '好丽友 派 巧克力味涂饰蛋类芯饼 680g/盒 20枚', 2, 'haoliyou.jpg', 53.00);
INSERT INTO `t_order_detail` VALUES ('2023-09-22 00:18:21', 36, 36, 2, '瑞士卷蛋糕', 5.45, '达利园 瑞士卷蛋糕（芒果味）240g/袋（12枚）', 1, 'ruishi.jpg', 5.45);
INSERT INTO `t_order_detail` VALUES ('2023-09-22 00:22:19', 37, 37, 23, '梅尼耶干蛋糕', 49.90, '盼盼 梅尼耶干蛋糕1000g箱装 奶香味干蛋糕饼干 正品茶点零食 美味早餐下午茶', 1, '5.jpg', 49.90);
INSERT INTO `t_order_detail` VALUES ('2023-09-22 00:24:05', 38, 38, 1, '铜锣烧aa', 90.00, '回头客铜锣烧160g红豆味西式糕点手撕小口袋面包早餐 好好吃哦！aaaaaaaaaaaa', 1, 'tongluoshaotj.png', 90.00);
INSERT INTO `t_order_detail` VALUES ('2023-09-22 00:24:34', 39, 39, 15, '牛肉干', 19.90, '淘豆 五香牛肉干100g/袋', 2, 'niurougan.jpg', 39.80);
INSERT INTO `t_order_detail` VALUES ('2023-09-22 00:28:25', 40, 40, 23, '梅尼耶干蛋糕', 49.90, '盼盼 梅尼耶干蛋糕1000g箱装 奶香味干蛋糕饼干 正品茶点零食 美味早餐下午茶', 1, '5.jpg', 49.90);
INSERT INTO `t_order_detail` VALUES ('2023-09-22 09:01:19', 41, 41, 1, '铜锣烧aa', 90.00, '回头客铜锣烧160g红豆味西式糕点手撕小口袋面包早餐 好好吃哦！aaaaaaaaaaaa', 2, 'tongluoshaotj.png', 180.00);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_admin` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (2, 'wyb', '888888', '男', '13219843250', '是');
INSERT INTO `t_user` VALUES (9, '1', '1', '女', '1', '否');
INSERT INTO `t_user` VALUES (10, 'yzl', 'yzl', '男', '15884930268', '否');
INSERT INTO `t_user` VALUES (13, 'xz', 'xz', '男', '1005', '是');
INSERT INTO `t_user` VALUES (16, '罗冬丽', '666666', '女', '15884930268', '是');
INSERT INTO `t_user` VALUES (17, '王一博', '888888', '男', '1005', '是');

SET FOREIGN_KEY_CHECKS = 1;
