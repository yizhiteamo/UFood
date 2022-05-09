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

 Date: 24/04/2022 15:58:04
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
-- Records of ufood_goods
-- ----------------------------
INSERT INTO `ufood_goods` VALUES ('【泰安门】蔬菜火腿汉堡', 19860, 16.00, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Vegetable ham burger-1639557 400x400.jpg', 0, 16580, '<p><img data-src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/burger-01 695x487.jpg\" alt=\"\\\" src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/burger-01 695x487.jpg\" width=\"100%\" height=\"auto\"><img data-src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/burger-02 695x384.jpg\" alt=\"\\\" src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/burger-02 695x384.jpg\" width=\"100%\" height=\"auto\"><img data-src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/burger-03 695x640.jpg\" alt=\"\\\" src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/burger-03 695x640.jpg\" width=\"100%\" height=\"auto\"></p>', 0, 0);
INSERT INTO `ufood_goods` VALUES ('【泰安门】罗勒叶意大利香肠披萨', 19861, 43.00, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Basil leaf pepperoni pizza -1260968 400x400.jpg', 0, 16580, '<p><img data-src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/pizza-01-695x1075.jpg\" alt=\"\\\" src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/pizza-01-695x1075.jpg\" width=\"100%\" height=\"auto\"><img data-src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/pizza-02-695x1075.jpg\" alt=\"\\\" src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/pizza-02-695x1075.jpg\" width=\"100%\" height=\"auto\"><img data-src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/pizza-03-695x1075.jpg\" alt=\"\\\" src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/pizza-03-695x1075.jpg\" width=\"100%\" height=\"auto\"><img data-src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/pizza-04-695x1075.jpg\" alt=\"\\\" src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/pizza-04-695x1075.jpg\" width=\"100%\" height=\"auto\"></p>', 0, 0);
INSERT INTO `ufood_goods` VALUES ('【上海紫外线餐厅】红莓牛奶朱古力心蛋糕', 19862, 23.00, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Raspberry chocolate cake -806363 400x400.jpg', 0, 16581, '<p>红莓牛奶朱古力心蛋糕</p>', 0, 0);
INSERT INTO `ufood_goods` VALUES ('【火宫殿】宫殿臭豆腐', 19863, 27.00, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Stinky Tofu-400x400.jpg', 0, 16583, '<p><img data-src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Stinky Tofu-695x384.jpg\" alt=\"\\\" src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Stinky Tofu-695x384.jpg\" width=\"100%\" height=\"auto\"><p>1958年4月12日，毛主席视察火宫殿，并品尝了火宫殿的湘菜和小吃，当吃到火宫殿臭豆腐的时候，毛主席说：火宫殿的臭豆腐闻起来臭，吃起来香……火宫殿臭豆腐以“闻起来臭，吃起来香”而闻名于世。上至国家领导人，下至黎民百姓对火宫殿臭豆腐无不称道，从而刮起一阵百余年的“火宫殿臭豆腐”美食之风。成为长沙火宫殿臭豆腐独特文化现象。\r\n   追根溯源，自清同治年间至今，火宫殿臭豆腐置根于长沙已近200年，其臭豆腐卤水配方及严格的工艺流程仍沿袭姜氏祖传，历经代代传承，始终保持着火宫殿臭豆腐原汁原味的本色。</p></p>', 0, 0);
INSERT INTO `ufood_goods` VALUES ('【火宫殿】姊妹团子', 19864, 39.00, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Steamed Glutinous Rice Ball-400x400.jpg', 0, 16583, '<p><img data-src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Steamed Glutinous Rice Ball-695x384.jpg\" alt=\"\\\" src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Steamed Glutinous Rice Ball-695x384.jpg\" width=\"100%\" height=\"auto\"><p>清末民初，火宫殿附近的铜铺街有一铜匠叫江立仁。每逢农历夏至，江用糯米粉做成糖肉两种口味的夏至坨（即糯米团子），比一般的夏至坨更好吃，很受邻里称赞。江育有两女儿，姐姐江秀美、妹妹江秀英，长大后在火宫殿开了家团子店。\r\n   她俩选用上等糯米磨成湿粉，分别包入由鲜肉、香菇、芝麻仁、白糖组成馅料，做成一咸一甜的糯米团子。两姊妹不仅美丽聪慧，且心灵手巧，做起团子有如玩杂耍，让人眼花缭乱，引起很多人围观和品尝，生意火红。日子一久，人们不经意把俩姊妹做的糯米团子叫成“姊妹团子”，流传至今，成为著名湘风小吃。</p></p>', 0, 0);
INSERT INTO `ufood_goods` VALUES ('【火宫殿】龙脂猪血', 19865, 45.00, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Coagulated Pig Blood in Sesame-400x400.jpg', 0, 16583, '<p><img data-src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Coagulated Pig Blood in Sesame-695x384.jpg\" alt=\"\\\" src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Coagulated Pig Blood in Sesame-695x384.jpg\" width=\"100%\" height=\"auto\"><p> 清同治年间，火宫殿内卖猪血的摊担有几家，但只有胡家麻油猪血摊生意最好。胡家一代接一代在火宫殿经营麻油猪血，从收取血浆、上火烫血、入碗调味共6道工序，各道工序都有其严格操作规程。猪血入碗调味时佐以小磨麻油、葱花即可食用。其特点，鲜香爽口，有如龙脂凤肝之细嫩。\r\n  民国时期，一文人墨客吃完猪血后对店主建议改麻油猪血为龙脂猪血。从此，龙脂猪血因其名称优雅，产品质量过硬而闻名，成为著名湘风小吃</p></p>', 0, 0);
INSERT INTO `ufood_goods` VALUES ('【火宫殿】肉丝馓子', 19866, 44.00, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Deep-fried Noodles with Shredded Meat - 400x400.jpg', 0, 16583, '<p><img data-src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Deep-fried Noodles with Shredded Meat - 695x487.jpg\" alt=\"\\\" src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Deep-fried Noodles with Shredded Meat - 695x487.jpg\" width=\"100%\" height=\"auto\"><p>馓子在我国有千余年历史，宋代大文豪苏轼曾为一位做馓子的少妇写了一首诗：“纤手搓来玉色匀，碧油煎出嫩黄深，夜来春睡知轻重，压扁佳人缠臂金。”旧时，馓子在长沙叫“焦盐馓子”。\r\n火宫殿肉丝馓子是上世纪40年代在火宫殿经营馓子的张桂生所创新。他选用上等面粉，用八十目萝筛过筛，和面要九搓八压，拉丝要柔顺如棉，油炸时的火候掌握得当，才能让炸出的馓子松脆。馓子经滚烫肉汤冲沸后，更加落口消溶，回味无穷。\r\n<p>特点：酥脆芳香    落口消溶</p></p></p>', 0, 0);
INSERT INTO `ufood_goods` VALUES ('【火宫殿】红烧猪脚', 19867, 42.00, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Braised Pig’s Feet in Brown Sauce - 400x400.jpg', 0, 16583, '<p><img data-src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Braised Pig’s Feet in Brown Sauce-695x384.jpg\" alt=\"\\\" src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Braised Pig’s Feet in Brown Sauce-695x384.jpg\" width=\"100%\" height=\"auto\"><p>  清末，火宫殿大门边有一家沈姓卖红烧猪脚的摊主，生意尚好。上世纪30年代，沈家后人娶了个漂亮能干的媳妇，继承婆家祖业，把红烧猪脚做的色泽红润、糯烂鲜香。因媳妇姓邓，家中排行第六，人们都称她为“邓六摩登”。1941年邓在火宫殿坪内开了一家“邓春香”小店，主营红烧猪脚。从此，邓春香的红烧猪脚红极长沙城，店内每天高朋满座，应接不暇。1956年公私合营后，邓师傅带了几个徒弟，把这门技艺传承下来并保持到今天。\r\n<p>产品特色：色泽红亮、肥而不腻、味浓鲜香</p></p></p>', 0, 0);
INSERT INTO `ufood_goods` VALUES ('【乔尔·卢布松美食坊】和牛煮双份鸭肝配晚收波特酒汁和牛肉清汤', 19868, 66.00, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/和牛煮双份鸭肝配晚收波特酒汁和牛肉清汤 - 400x400.jpg', 0, 16582, '<p><img data-src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/和牛煮双份鸭肝配晚收波特酒汁和牛肉清汤-695x278.jpg\" alt=\"\\\" src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/和牛煮双份鸭肝配晚收波特酒汁和牛肉清汤-695x278.jpg\" width=\"100%\" height=\"auto\"><p>精选上等和牛肉烹制，配搭双份鹅肝及罗西尼酱，佐以蔬菜色拉及黑松露调整口味，也获得健康上的平衡。摆盘也十分考究，中间淋上葡萄酒汁及牛肉酱，周围放上加入生姜、姜黄及牛肉清汤的鹅肝饺子。整道菜品色泽丰富，口感上佳，是来Joël Robuchon不可错过的经典美食。</p></p>', 0, 0);
INSERT INTO `ufood_goods` VALUES ('【乔尔·卢布松美食坊】鱼子酱搭配龙虾冻及椰菜泥', 19869, 65.00, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/鱼子酱搭配龙虾冻及椰菜泥-400x400.jpg', 0, 16582, '<p><img data-src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/鱼子酱搭配龙虾冻及椰菜泥 -695x695.jpg\" alt=\"\\\" src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/鱼子酱搭配龙虾冻及椰菜泥 -695x695.jpg\" width=\"100%\" height=\"auto\"><p>卢布松先生经典菜式，精致口感的灵感搭配。龙虾冻覆以鱼子酱以及薄层椰菜泥，质地丰腴而爽口，必须从上至下一齐入口，方能充分享受层次丰富的口感，细品这道菜肴的精致美味</p></p>', 0, 0);
INSERT INTO `ufood_goods` VALUES ('【怡园酒庄】德寧珍藏起泡酒（德宁珍藏起泡酒）\r\n', 19870, 398.00, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/grapewine-2912108-400x400.jpg', 0, 16585, '<p><img data-src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/grapewine-2912108-695x1101.jpg\" alt=\"\\\" src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/grapewine-2912108-695x1101.jpg\" width=\"100%\" height=\"auto\"><p>葡萄年份：2012年</p><p>葡萄产区：山西</p><p>葡萄品种：100%霞多丽</p><p>发酵：葡萄比通常要早采收以保持天然的酸度和新鲜感。在不锈钢罐和中性橡木桶中进行酒精发酵。采用传统工艺酿造，在瓶内二次发酵前经过轻微过滤。2013年4月瓶内二次发酵完成，酒泥接触直到2019年3月，并通过手工转瓶和去酒泥。</p><p>陈酿：少量基酒在橡木桶陈酿大约3个月。</p><p>酿酒师品酒笔记：</p><p>颜色：浅绿色带轻微的禾秆黄.</p><p>香气：新鲜的凤梨和令人愉悦的烤面包和蜂蜜气息.</p><p>口感：奶油般饱满质感，果味甜美、有丰富的余味.</p><p>怡园德寧起泡酒 简介</p><p>怡园酒庄少庄主陈芳的小女儿德寧Angelina\r\n\r\n就好像她人生中的阳光，充满明亮、活泼和快乐！\r\n\r\n这也是她以“Angelina”来命名怡园酒庄这款\r\n\r\n起泡酒系列的由来。怡园德寧起泡酒捕捉了小女孩\r\n\r\n对人生的喜悦、期盼和追寻，如同她的小德寧。\r\n\r\n怡园德寧起泡酒，以传统工艺酿造（瓶内二次发酵）。</p></p>', 0, 0);
INSERT INTO `ufood_goods` VALUES ('【茶豆坊】班戟 \r\n|  港式甜品', 19871, 20.00, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/班戟-400x400.jpg', 0, 16584, '<p><img data-src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/班戟detail02-695x695.jpg\" alt=\"\\\" src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/班戟detail02-695x695.jpg\" width=\"100%\" height=\"auto\"><img data-src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/班戟detail03-695x461.jpg\" alt=\"\\\" src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/班戟detail03-695x461.jpg\" width=\"100%\" height=\"auto\"><img data-src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/班戟 - 695x461.jpg\" alt=\"\\\" src=\"https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/班戟 - 695x461.jpg\" width=\"100%\" height=\"auto\"></p>', 0, 0);

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
-- Records of ufood_icon
-- ----------------------------
INSERT INTO `ufood_icon` VALUES (1, '中餐', 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/中餐.png');
INSERT INTO `ufood_icon` VALUES (2, '西餐', 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/西餐.png');
INSERT INTO `ufood_icon` VALUES (3, '甜点', 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/甜点 面包 蛋糕 水果 甜点.png');
INSERT INTO `ufood_icon` VALUES (4, '饮品', 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/饮品.png');

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
-- Records of ufood_order
-- ----------------------------
INSERT INTO `ufood_order` VALUES (330, '2022-04-20 17:00:00', 'Ralts', '【火宫殿】肉丝馓子', '2022-04-22 21:43:00', '已就餐');
INSERT INTO `ufood_order` VALUES (333, '2022-04-25 17:00:00', 'Ralts', '【火宫殿】肉丝馓子', '2022-04-22 21:19:22', '未就餐');
INSERT INTO `ufood_order` VALUES (350, '2022-04-23 17:00:00', 'Ralts', '【火宫殿】肉丝馓子', '2022-04-22 21:19:54', '未就餐');
INSERT INTO `ufood_order` VALUES (370, '2022-05-01 18:00:00', 'Ralts', '【泰安门】蔬菜火腿汉堡,【泰安门】罗勒叶意大利香肠披萨', '2022-04-22 21:37:47', '未就餐');
INSERT INTO `ufood_order` VALUES (380, '2022-05-02 09:00:00', 'Ralts', '【火宫殿】宫殿臭豆腐', '2022-04-22 13:20:58', '未就餐');
INSERT INTO `ufood_order` VALUES (390, '2022-05-09 16:00:00', 'Ralts', '【火宫殿】宫殿臭豆腐', '2022-04-22 13:21:01', '未就餐');
INSERT INTO `ufood_order` VALUES (410, '2022-04-23 08:00:00', 'Ralts', '【泰安门】蔬菜火腿汉堡', '2022-04-22 13:21:04', '未就餐');
INSERT INTO `ufood_order` VALUES (412, '2022-04-23 14:00:00', 'Ralts', '【泰安门】蔬菜火腿汉堡', '2022-04-23 13:42:28', '已退款');

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
-- Records of ufood_retrieve
-- ----------------------------
INSERT INTO `ufood_retrieve` VALUES (34, 25, '887147', '2022-04-19', '2022-04-19 07:12:51.243', 1, 2, 3);

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
-- Records of ufood_shop
-- ----------------------------
INSERT INTO `ufood_shop` VALUES (16580, '上海市长宁区镇宁路465弄161号', '泰安门 Taian Table是一家位于上海市长宁区镇宁路465弄161号愚园里1号楼101室的餐厅。', '泰安门', '西餐', 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Taian Table.png', 1, '2022-03-19 16:20:00');
INSERT INTO `ufood_shop` VALUES (16581, '上海市黄浦区中山东一路18号外滩十八号6层', 'Ultraviolet by Paul Pairet主做创意法式西餐，餐厅只在每个营业日提供一顿晚餐，并且只有一桌10人桌。所有顾客均须提前通过官网预约，并在用餐当晚在外滩18号集合，大家可以一边享用餐前小点、一边与将要与自己同桌用餐的陌生人相互熟识。专程的大巴会带领顾客们穿过上海最有韵味的老城区到达目的地。', '上海紫外线餐厅', '甜点', 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/ultraviolet.png', 1, '2022-03-19 16:20:00');
INSERT INTO `ufood_shop` VALUES (16582, '上海市黄浦区中山东一路18号3层', '美食坊灵感来自于卢布松先生在日本的游历，并结合他心仪的西班牙小食吧概念而成。上海乔尔 • 卢布松美食坊拥有标志性的开放式厨房围绕着1.8米的铁板烧料理台，共有32个紧邻开放式厨房的席位，以及16个围绕在周围的餐桌席位。食客们可以清楚观赏到一道道乔尔 • 卢布松经典美食在主厨Francky Semblat和他的团队手中诞生。', '乔尔·卢布松美食坊', '西餐', 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Joel Lubzon Food Place.png', 1, '2022-03-19 16:20:00');
INSERT INTO `ufood_shop` VALUES (16583, '湖南省长沙市天心区坡子街127号', '火宫殿同时也是一家驰名中外的“中华老字号”企业，在这里可以吃到各种长沙和湖南小吃，比如长沙臭豆腐、正宗红烧肉、糍粑等等。火宫殿作为美食城在长沙有5处，1家总店，4家分店。', '火宫殿', '中餐', 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/huogongdian.png', 1, '2022-03-19 16:20:00');
INSERT INTO `ufood_shop` VALUES (16584, '深圳市南山区沙河街道华夏街社区侨城西街9号世界花园', '茶豆坊源于中国·台湾，历经50多年的甜品沉淀，现回归中国大陆。\r\n品牌自创办以来，坚持纯手工，现做、现卖，创始人先后拜师台湾甜品及茶饮大师潜心学艺，只为打造原滋原味的台湾味道。', '茶豆坊', '甜点', 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/tea-bean.png', 1, '2022-03-19 16:20:00');
INSERT INTO `ufood_shop` VALUES (16585, '福建省福州市鼓楼区华林路11号西湖宾馆贵宾楼1楼', ' 怡园酒庄（Grace Vineyard），中国精品酒庄的拓荒者（China\'s premier family-owned winery），美好生活的倡导者（The pathway to a better life）。共享怡然生活（Live with Grace）。', '怡园酒庄', '饮品', 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Grace Vineyard.png', 1, '2022-03-19 16:20:00');

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
-- Records of ufood_shopcate
-- ----------------------------
INSERT INTO `ufood_shopcate` VALUES (1, '中餐');
INSERT INTO `ufood_shopcate` VALUES (2, '西餐');
INSERT INTO `ufood_shopcate` VALUES (3, '甜点');
INSERT INTO `ufood_shopcate` VALUES (4, '饮品');

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
-- Records of ufood_swiper
-- ----------------------------
INSERT INTO `ufood_swiper` VALUES (1, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Vegetable ham burger 750x340.jpg', 19860, 1);
INSERT INTO `ufood_swiper` VALUES (2, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Basil leaf pepperoni pizza 750x340.jpg', 19861, 1);
INSERT INTO `ufood_swiper` VALUES (3, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Raspberry chocolate cake 750x340.jpg', 19862, 0);
INSERT INTO `ufood_swiper` VALUES (4, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Coagulated Pig Blood in Sesame -750x340.jpg', 19865, 0);
INSERT INTO `ufood_swiper` VALUES (5, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Stinky Tofu-750x340.jpg', 19863, 1);
INSERT INTO `ufood_swiper` VALUES (6, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Steamed Glutinous Rice Ball-750x340.jpg', 19864, 0);
INSERT INTO `ufood_swiper` VALUES (7, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Deep-fried Noodles with Shredded Meat-750x340.jpg', 19866, 0);
INSERT INTO `ufood_swiper` VALUES (8, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/Braised Pig’s Feet in Brown Sauce - 750x340.jpg', 19867, 0);
INSERT INTO `ufood_swiper` VALUES (9, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/和牛煮双份鸭肝配晚收波特酒汁和牛肉清汤 - 750x340.jpg', 19868, 0);
INSERT INTO `ufood_swiper` VALUES (10, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/鱼子酱搭配龙虾冻及椰菜泥 - 750x340.jpg', 19869, 0);
INSERT INTO `ufood_swiper` VALUES (11, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/grapewine-2912108-750x340.jpg', 19870, 0);
INSERT INTO `ufood_swiper` VALUES (12, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/班戟swiper02-750x340.jpg', 19871, 0);
INSERT INTO `ufood_swiper` VALUES (13, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/班戟swiper03-750x340.jpg', 19871, 0);
INSERT INTO `ufood_swiper` VALUES (14, 'https://ufood-1307352632.cos.ap-chengdu.myqcloud.com/ufood/班戟 -750x340.jpg', 19871, 0);

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

-- ----------------------------
-- Records of ufood_user
-- ----------------------------
INSERT INTO `ufood_user` VALUES ('yizhiteamo', '8df6a26791bfc578bd75cb286a6000ec', 20, 0, 0, '13515093992', NULL, '2022-04-10 12:14:24', '541361418@qq.com', NULL, NULL);
INSERT INTO `ufood_user` VALUES ('Ralts', '8df6a26791bfc578bd75cb286a6000ec', 25, 0, 0, '13515093992', '成都市成洛大道2025号 ', '2022-04-19 18:09:18', '3214831679@qq.com', NULL, NULL);
INSERT INTO `ufood_user` VALUES ('Airalin', '8df6a26791bfc578bd75cb286a6000ec', 41, 0, 0, NULL, NULL, '2022-04-19 20:44:43', '541361418@qq.com', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
