/*
MySQL Backup
Source Server Version: 5.5.25
Source Database: guessprice
Date: 3/31/2014 20:20:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `pricesetup`
-- ----------------------------
DROP TABLE IF EXISTS `pricesetup`;
CREATE TABLE `pricesetup` (
  `priceSetupId` int(11) NOT NULL AUTO_INCREMENT,
  `setPrice` decimal(18,2) DEFAULT NULL,
  `userId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  PRIMARY KEY (`priceSetupId`),
  UNIQUE KEY `price_setup_idx` (`priceSetupId`) USING BTREE,
  KEY `productId` (`productId`),
  KEY `userId` (`userId`),
  CONSTRAINT `pricesetup_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  CONSTRAINT `pricesetup_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `product` (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `productId` int(11) NOT NULL AUTO_INCREMENT,
  `productName` varchar(200) NOT NULL,
  `productDescription` varchar(200) DEFAULT NULL,
  `productLine` varchar(200) NOT NULL,
  `marketPrice` decimal(18,2) DEFAULT NULL,
  `highPrice` decimal(18,2) DEFAULT NULL,
  `lowPrice` decimal(18,2) DEFAULT NULL,
  PRIMARY KEY (`productId`),
  KEY `product_id_idx` (`productId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT 'user table',
  `userName` varchar(200) NOT NULL,
  `password` varchar(200) DEFAULT NULL,
  `region` varchar(20) NOT NULL,
  `role` int(2) NOT NULL,
  PRIMARY KEY (`userId`),
  KEY `user_id_idx` (`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `pricesetup` VALUES ('1','78.00','2','1'), ('2','60.00','2','2'), ('3','80.00','3','2'), ('4','90.00','3','3'), ('5','95.00','5','3'), ('6','75.00','1','4'), ('7','85.00','6','5');
INSERT INTO `product` VALUES ('1','PC','','ZL','100.00','200.00','50.00'), ('2','PAD','','ZM','100.00','200.00','50.00'), ('3','PHONE','','ZM','100.00','200.00','50.00'), ('4','SERVER','','XA','100.00','200.00','50.00'), ('5','STORAGE','','XP','100.00','200.00','50.00');
INSERT INTO `user` VALUES ('1','ADMIN','ADMIN','WW','0'), ('2','USER1','USER1','AMS','1'), ('3','USER2','USER2','EMEA','1'), ('4','USER3','USER3','EMEA','1'), ('5','USER4','USER4','APJ','1'), ('6','USER5','USER5','AMS','1');
