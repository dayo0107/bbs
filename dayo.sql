-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: dayo
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `desc_` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'admin','后台管理','/admin'),(2,'user:view','用户列表','/admin/listUser'),(3,'user:ban','封禁账号','/admin/banUser'),(4,'user:release','解封账号','/admin/releaseUser'),(5,'user:mute','禁言账号','/admin/muteUser'),(6,'post:add','发布主题','/home/addPost'),(7,'post:update','修改主题','/home/updatePost'),(8,'post:show','主题详情及其回复','/home/showPost'),(9,'home','浏览bbs主页','/home'),(10,'reply:add','回复主题','/post/addReply'),(11,'reply:delete','删除回复','/post/deleteReply'),(12,'reply:update','修改回复','/post/updateReply');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `createDate` datetime NOT NULL,
  `lastDate` datetime NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `post_user_id_fk` (`uid`),
  CONSTRAINT `post_user_id_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,2,'2018-09-21 17:26:06','2018-09-29 23:56:13','lpl','世界冠军'),(2,2,'2018-09-21 18:05:45','2018-10-10 00:31:21','lck','kt宇宙战舰，最多四强。'),(4,8,'2018-09-29 23:48:41','2018-10-01 15:17:26','wang5的主题','wang5的内容');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `createDate` datetime NOT NULL,
  `content` varchar(255) NOT NULL,
  `uid` int(11) NOT NULL,
  `index_` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `reply_post_id_fk` (`pid`),
  KEY `reply_user_id_fk` (`uid`),
  CONSTRAINT `reply_post_id_fk` FOREIGN KEY (`pid`) REFERENCES `post` (`id`),
  CONSTRAINT `reply_user_id_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
INSERT INTO `reply` VALUES (1,2,'2018-09-21 11:13:22','百年好合',6,1),(3,2,'2018-09-21 20:19:27','你的微笑编织了每个奇妙',2,3),(4,2,'2018-09-21 20:25:35','打上花火',6,4),(5,2,'2018-09-21 21:15:28','四斤大豆，三根皮带',2,5),(6,1,'2018-09-21 21:17:14','顶一下',2,1),(7,2,'2018-09-21 22:45:30','你好',2,6),(8,2,'2018-09-21 23:32:10','尼尔机械纪元',2,7),(9,1,'2018-09-22 21:45:53','3:0',7,2),(10,2,'2018-09-28 18:04:37','我是李四',7,8),(11,1,'2018-09-29 22:57:08','顶两下',2,3),(12,1,'2018-09-29 23:55:20','顶三下',8,4),(13,1,'2018-09-29 23:55:31','我叫王5',8,5),(14,1,'2018-09-29 23:56:13','来自大广东省',8,6),(15,4,'2018-10-01 15:17:26','你好，我是张三',9,1),(16,2,'2018-10-10 00:31:21','你好',6,8);
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `desc_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin','超级管理员'),(2,'user','普通用户'),(3,'bannedUser','被封禁用户'),(4,'mutedUser','被禁言用户');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,2,6),(14,2,7),(15,2,8),(16,2,9),(17,2,10),(18,3,9),(19,4,8),(20,4,9);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `email` varchar(255) NOT NULL COMMENT '用户邮箱',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '用户激活状态：0表示未激活，1表示激活',
  `code` varchar(255) NOT NULL COMMENT '激活码',
  `salt` varchar(255) NOT NULL COMMENT '盐',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'admin','zhuoanhui@gmail.com','a7d59dfc5332749cb801f86a24f5f590',1,'848aa456-9fa5-4a79-bd51-20d3f32ce879','e5ykFiNwShfCXvBRPr3wXg=='),(6,'dayo0107','zhuoanhui@gmail.com','dab419f60c343bdb996872e5a99dd5da',4,'04a876a7b4f0c9bdec7938e0eba2640b','3DdOqTHsmfem58gNYr2ZZg=='),(7,'li4','zhuoanhui@gmail.com','b44a465a64e8c61abef29dd8f28047f9',2,'4466af42e371208cd9702460b209f761','v+jHhFxt8fDJPVy84kSg8Q=='),(8,'wang5','zhuoanhui@gmail.com','38e135a2b07c176c0297b9d053ee2938',2,'a0c4b43005bd24f61755ba471d06ebb6','X78jRkiOxdgV+AizcyU5lg=='),(9,'zhang3','zhuoanhui@gmail.com','1f8ddc58cb8dbd8549addb851ea2f967',2,'d111bb13ea73bfba40e76115f3b027d6','LR5rbsCI9n5UJeL4j9DrUg==');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `rid` int(11) NOT NULL DEFAULT '2',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,2,1),(2,6,4),(3,7,2),(4,8,2),(5,9,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-19 19:47:48
