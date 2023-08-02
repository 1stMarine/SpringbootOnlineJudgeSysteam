-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: localhost    Database: onlinejudge
-- ------------------------------------------------------
-- Server version	8.0.32-0ubuntu0.20.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `oj_blog_comments`
--

DROP TABLE IF EXISTS `oj_blog_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oj_blog_comments` (
  `id` bigint NOT NULL,
  `blog_uid` bigint DEFAULT NULL,
  `comments_uid` bigint DEFAULT NULL,
  `bid` bigint DEFAULT NULL,
  `time` longtext,
  `context` longtext,
  `reply` longtext,
  `like_count` int DEFAULT NULL,
  `star` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oj_blog_comment_oj_user_id_fk` (`blog_uid`),
  KEY `oj_blog_comment_oj_user_id_fk2` (`comments_uid`),
  KEY `oj_blog_comments_oj_blog_list_id_fk` (`bid`),
  CONSTRAINT `oj_blog_comment_oj_user_id_fk` FOREIGN KEY (`blog_uid`) REFERENCES `oj_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `oj_blog_comment_oj_user_id_fk2` FOREIGN KEY (`comments_uid`) REFERENCES `oj_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `oj_blog_comments_oj_blog_list_id_fk` FOREIGN KEY (`bid`) REFERENCES `oj_blog_list` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oj_blog_comments`
--

LOCK TABLES `oj_blog_comments` WRITE;
/*!40000 ALTER TABLE `oj_blog_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `oj_blog_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oj_blog_list`
--

DROP TABLE IF EXISTS `oj_blog_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oj_blog_list` (
  `id` bigint NOT NULL,
  `uid` bigint DEFAULT NULL,
  `type` longtext,
  `time` longtext,
  `title` longtext NOT NULL,
  `blog_context` longtext,
  `face_image` longtext,
  `tag` longtext,
  `admin_tags` longtext,
  `like_count` int DEFAULT NULL,
  `star` int DEFAULT NULL,
  `comment` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oj_blog_list_oj_user_id_fk` (`uid`),
  CONSTRAINT `oj_blog_list_oj_user_id_fk` FOREIGN KEY (`uid`) REFERENCES `oj_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oj_blog_list`
--

LOCK TABLES `oj_blog_list` WRITE;
/*!40000 ALTER TABLE `oj_blog_list` DISABLE KEYS */;
INSERT INTO `oj_blog_list` VALUES (436044048835416064,293616896,'#technology','2023-07-17 22:47:51','磐镭6650xt','下山了，显卡还不错\n```java\nSysteam.out.println(\"ddd\");\n```\n![Description](http://www.nextstepcode.club:443/online-image-bed/2023-07-17/df6c067e-1c17-445c-9003-6f05007ebda2.JPG)\n','http://www.nextstepcode.club:443/online-image-bed/2023-07-17/df6c067e-1c17-445c-9003-6f05007ebda2.JPG','[\"显卡\"]','[{\"message\":\"精选\",\"type\":\"\"},{\"message\":\"推荐\",\"type\":\"warning\"},{\"message\":\"官方\",\"type\":\"success\"}]',1,1,0);
/*!40000 ALTER TABLE `oj_blog_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oj_blog_user_like`
--

DROP TABLE IF EXISTS `oj_blog_user_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oj_blog_user_like` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bid` bigint DEFAULT NULL,
  `uid` bigint DEFAULT NULL,
  `state` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oj_blog_user_like_oj_blog_list_id_fk` (`bid`),
  KEY `oj_blog_user_like_oj_user_id_fk` (`uid`),
  CONSTRAINT `oj_blog_user_like_oj_blog_list_id_fk` FOREIGN KEY (`bid`) REFERENCES `oj_blog_list` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `oj_blog_user_like_oj_user_id_fk` FOREIGN KEY (`uid`) REFERENCES `oj_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oj_blog_user_like`
--

LOCK TABLES `oj_blog_user_like` WRITE;
/*!40000 ALTER TABLE `oj_blog_user_like` DISABLE KEYS */;
INSERT INTO `oj_blog_user_like` VALUES (24,436044048835416064,282718464,1);
/*!40000 ALTER TABLE `oj_blog_user_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oj_blog_user_star`
--

DROP TABLE IF EXISTS `oj_blog_user_star`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oj_blog_user_star` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bid` bigint DEFAULT NULL,
  `uid` bigint DEFAULT NULL,
  `state` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oj_blog_user_star_oj_blog_list_id_fk` (`bid`),
  KEY `oj_blog_user_star_oj_user_id_fk` (`uid`),
  CONSTRAINT `oj_blog_user_star_oj_blog_list_id_fk` FOREIGN KEY (`bid`) REFERENCES `oj_blog_list` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `oj_blog_user_star_oj_user_id_fk` FOREIGN KEY (`uid`) REFERENCES `oj_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oj_blog_user_star`
--

LOCK TABLES `oj_blog_user_star` WRITE;
/*!40000 ALTER TABLE `oj_blog_user_star` DISABLE KEYS */;
INSERT INTO `oj_blog_user_star` VALUES (4,436044048835416064,282718464,1);
/*!40000 ALTER TABLE `oj_blog_user_star` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oj_comments_user_like`
--

DROP TABLE IF EXISTS `oj_comments_user_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oj_comments_user_like` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint DEFAULT NULL,
  `uid` bigint DEFAULT NULL,
  `state` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oj_comments_user_like_oj_blog_comments_id_fk` (`cid`),
  KEY `oj_comments_user_like_oj_user_id_fk` (`uid`),
  CONSTRAINT `oj_comments_user_like_oj_blog_comments_id_fk` FOREIGN KEY (`cid`) REFERENCES `oj_blog_comments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `oj_comments_user_like_oj_user_id_fk` FOREIGN KEY (`uid`) REFERENCES `oj_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oj_comments_user_like`
--

LOCK TABLES `oj_comments_user_like` WRITE;
/*!40000 ALTER TABLE `oj_comments_user_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `oj_comments_user_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oj_match_list`
--

DROP TABLE IF EXISTS `oj_match_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oj_match_list` (
  `id` bigint NOT NULL,
  `match_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `match_description` longtext COLLATE utf8mb3_unicode_ci,
  `create_time` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `start_time` varchar(30) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `end_time` varchar(30) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `persistent_time` int DEFAULT NULL,
  `participation_count` int DEFAULT NULL,
  `match_type` text COLLATE utf8mb3_unicode_ci,
  `img_url` varchar(2000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `state` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oj_match_list`
--

LOCK TABLES `oj_match_list` WRITE;
/*!40000 ALTER TABLE `oj_match_list` DISABLE KEYS */;
INSERT INTO `oj_match_list` VALUES (412415295496523776,'5.13 602实验室周赛','# 602实验室算法竞赛周赛\n\n欢迎参加602实验室算法竞赛周赛！这是一个激动人心的编程竞赛，为算法爱好者提供了展示自己才华和挑战技能的机会。\n\n## 比赛概述\n\n- **时间**：每周一次，持续约2小时。\n- **地点**：线上举行。\n\n## 参赛条件\n\n任何对算法和编程感兴趣的人都可以参加602实验室算法竞赛周赛。不限制国籍、年龄或专业背景。\n\n## 比赛流程\n\n1. 报名：在比赛前注册一个602实验室算法竞赛账户（如果没有）。\n2. 每周比赛前向组织者报名参赛。\n3. 比赛开始后，登陆账户，接收竞赛题目。\n4. 在规定的时间内解决尽可能多的问题。\n5. 提交代码：在规定时间结束前提交解决问题的代码。\n6. 评测：提交后，系统会自动评测你的代码，得出相应的结果。\n7. 结果和排名：比赛结束后，公布排名和结果，并颁发奖品给表现优秀的选手。\n\n## 奖励\n\n- 表现优秀的选手有机会获得奖金和荣誉称号。\n- 参与竞赛可以锻炼个人编程技能、学习新的算法知识，并拓展人际网络。\n\n## 联系我们\n\n如果您有任何问题或疑问，请联系我们：[contest@example.com](mailto:cenkaiwei@nextstepcode.club)\n\n欢迎加入602实验室算法竞赛周赛，展示你的才华和激情！','2023-05-13- 17:55','2023-05-13 19:30','2023-05-13 20:30',60,17,'602周赛','http://www.nextstepcode.club:443/online-image-bed/2023-07-21/82abc25c-7f79-4c95-bf11-e78b667390a2.jpg','已结束');
/*!40000 ALTER TABLE `oj_match_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oj_match_question`
--

DROP TABLE IF EXISTS `oj_match_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oj_match_question` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `qid` bigint DEFAULT NULL,
  `mid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oj_match_question_oj_match_list_id_fk` (`mid`),
  KEY `oj_match_question_oj_question_id_fk` (`qid`),
  CONSTRAINT `oj_match_question_oj_match_list_id_fk` FOREIGN KEY (`mid`) REFERENCES `oj_match_list` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `oj_match_question_oj_question_id_fk` FOREIGN KEY (`qid`) REFERENCES `oj_question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oj_match_question`
--

LOCK TABLES `oj_match_question` WRITE;
/*!40000 ALTER TABLE `oj_match_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `oj_match_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oj_match_record`
--

DROP TABLE IF EXISTS `oj_match_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oj_match_record` (
  `id` bigint NOT NULL,
  `uid` bigint DEFAULT NULL,
  `qid` bigint DEFAULT NULL,
  `mid` bigint DEFAULT NULL,
  `question_name` varchar(30) DEFAULT NULL,
  `time` double DEFAULT NULL,
  `memeory` double DEFAULT NULL,
  `language` varchar(20) DEFAULT NULL,
  `code` varchar(10000) DEFAULT NULL,
  `submit_time` varchar(20) DEFAULT NULL,
  `title` varchar(20) DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `test_sample` varchar(1000) DEFAULT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oj_match_record_oj_match_list_id_fk` (`mid`),
  KEY `oj_match_record_oj_question_id_fk` (`qid`),
  KEY `oj_match_record_oj_user_id_fk` (`uid`),
  CONSTRAINT `oj_match_record_oj_match_list_id_fk` FOREIGN KEY (`mid`) REFERENCES `oj_match_list` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `oj_match_record_oj_question_id_fk` FOREIGN KEY (`qid`) REFERENCES `oj_question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `oj_match_record_oj_user_id_fk` FOREIGN KEY (`uid`) REFERENCES `oj_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oj_match_record`
--

LOCK TABLES `oj_match_record` WRITE;
/*!40000 ALTER TABLE `oj_match_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `oj_match_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oj_match_result`
--

DROP TABLE IF EXISTS `oj_match_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oj_match_result` (
  `id` bigint NOT NULL,
  `mid` bigint DEFAULT NULL,
  `uid` bigint DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `results` text,
  `total_score` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `match_result_mid` (`mid`),
  KEY `oj_match_result_oj_user_id_fk` (`uid`),
  CONSTRAINT `oj_match_result_oj_match_list_id_fk` FOREIGN KEY (`mid`) REFERENCES `oj_match_list` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `oj_match_result_oj_user_id_fk` FOREIGN KEY (`uid`) REFERENCES `oj_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oj_match_result`
--

LOCK TABLES `oj_match_result` WRITE;
/*!40000 ALTER TABLE `oj_match_result` DISABLE KEYS */;
INSERT INTO `oj_match_result` VALUES (3242432,412415295496523776,293738752,'韩','{\"412407939186429952\":\"Empty\",\"412410948867985408\":\"WrongAnswer\",\"412413242619596800\":\"Compile Error\"}',0),(3421314,412415295496523776,412440889215029248,'su','{\"412407939186429952\":\"Empty\",\"412410948867985408\":\"Accept\",\"412413242619596800\":\"Accept\"}',2),(412439045197664256,412415295496523776,293716352,'xl6','{\"412407939186429952\":\"Empty\",\"412410948867985408\":\"Accept\",\"412413242619596800\":\"Accept\"}',2),(412439045218635776,412415295496523776,412415953972891648,'万道mian','{\"412407939186429952\":\"WrongAnswer\",\"412410948867985408\":\"Compile Error\",\"412413242619596800\":\"Empty\"}',0),(412439045235412992,412415295496523776,282718464,'管理员','{\"412407939186429952\":\"Empty\",\"412410948867985408\":\"Accept\",\"412413242619596800\":\"Empty\"}',1),(412439045256384512,412415295496523776,293723648,'wangkai','{\"412407939186429952\":\"Empty\",\"412410948867985408\":\"WrongAnswer\",\"412413242619596800\":\"Compile Error\"}',0),(412439045277356032,412415295496523776,412422321597452288,'素白衫','{\"412407939186429952\":\"Empty\",\"412410948867985408\":\"WrongAnswer\",\"412413242619596800\":\"Empty\"}',0),(412439045302521856,412415295496523776,293721344,'FluffySnow','{\"412407939186429952\":\"Empty\",\"412410948867985408\":\"WrongAnswer\",\"412413242619596800\":\"Compile Error\"}',0),(412439045327687680,412415295496523776,293719040,'陈子森','{\"412407939186429952\":\"WrongAnswer\",\"412410948867985408\":\"Accept\",\"412413242619596800\":\"WrongAnswer\"}',1),(412439045352853504,412415295496523776,293698176,'马明宇','{\"412407939186429952\":\"WrongAnswer\",\"412410948867985408\":\"Accept\",\"412413242619596800\":\"Compile Error\"}',1),(412439045373825024,412415295496523776,412432194913570816,'一叶神影','{\"412407939186429952\":\"Compile Error\",\"412410948867985408\":\"Accept\",\"412413242619596800\":\"Accept\"}',2),(412439045394796544,412415295496523776,293706240,'杨志聪','{\"412407939186429952\":\"WrongAnswer\",\"412410948867985408\":\"Accept\",\"412413242619596800\":\"Empty\"}',1),(412439045415768064,412415295496523776,412433001557921792,'alancheng','{\"412407939186429952\":\"Empty\",\"412410948867985408\":\"Empty\",\"412413242619596800\":\"WrongAnswer\"}',0),(412439045440933888,412415295496523776,412438175991074816,'宋宋宋','{\"412407939186429952\":\"Compile Error\",\"412410948867985408\":\"Accept\",\"412413242619596800\":\"Accept\"}',2),(412439045491265536,412415295496523776,412418092191322112,'今天ac了吗','{\"412407939186429952\":\"Empty\",\"412410948867985408\":\"Accept\",\"412413242619596800\":\"Compile Error\"}',1),(412439045512237056,412415295496523776,412418127507361792,'龘靐sky','{\"412407939186429952\":\"WrongAnswer\",\"412410948867985408\":\"Accept\",\"412413242619596800\":\"Accept\"}',2),(412439045533208576,412415295496523776,412438584772136960,'哎呀没时间了','{\"412407939186429952\":\"Empty\",\"412410948867985408\":\"Empty\",\"412413242619596800\":\"Compile Error\"}',0),(412439045554180096,412415295496523776,293724672,'LIUBIN','{\"412407939186429952\":\"Compile Error\",\"412410948867985408\":\"Accept\",\"412413242619596800\":\"WrongAnswer\"}',1),(412439045575151616,412415295496523776,412438666892414976,'2mux','{\"412407939186429952\":\"Empty\",\"412410948867985408\":\"Accept\",\"412413242619596800\":\"Compile Error\"}',1);
/*!40000 ALTER TABLE `oj_match_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oj_match_user`
--

DROP TABLE IF EXISTS `oj_match_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oj_match_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `mid` bigint DEFAULT NULL,
  `uid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fff_match` (`mid`),
  KEY `oj_match_user_oj_user_id_fk` (`uid`),
  CONSTRAINT `oj_match_user_oj_match_list_id_fk` FOREIGN KEY (`mid`) REFERENCES `oj_match_list` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `oj_match_user_oj_user_id_fk` FOREIGN KEY (`uid`) REFERENCES `oj_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=54655 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oj_match_user`
--

LOCK TABLES `oj_match_user` WRITE;
/*!40000 ALTER TABLE `oj_match_user` DISABLE KEYS */;
INSERT INTO `oj_match_user` VALUES (70,412415295496523776,293716352),(71,412415295496523776,412415953972891648),(72,412415295496523776,282718464),(73,412415295496523776,293723648),(74,412415295496523776,412422321597452288),(75,412415295496523776,293721344),(76,412415295496523776,293719040),(77,412415295496523776,293698176),(78,412415295496523776,412432194913570816),(79,412415295496523776,293706240),(80,412415295496523776,412433001557921792),(81,412415295496523776,412438175991074816),(82,412415295496523776,412418092191322112),(83,412415295496523776,412418127507361792),(84,412415295496523776,412438584772136960),(85,412415295496523776,293724672),(86,412415295496523776,412438666892414976),(32324,412415295496523776,412440889215029248),(54654,412415295496523776,293738752);
/*!40000 ALTER TABLE `oj_match_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oj_question`
--

DROP TABLE IF EXISTS `oj_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oj_question` (
  `id` bigint NOT NULL,
  `question_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `input_style` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `output_style` varchar(10000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `data_range` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `input_sample` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `output_sample` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `difficulty` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `time_limit` int DEFAULT NULL,
  `memory_limit` int DEFAULT NULL,
  `description` varchar(7000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `total_pass` int DEFAULT NULL,
  `total_attempt` int DEFAULT NULL,
  `resource` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `tag` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `pass_rate` double DEFAULT NULL,
  `private_state` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oj_question`
--

LOCK TABLES `oj_question` WRITE;
/*!40000 ALTER TABLE `oj_question` DISABLE KEYS */;
INSERT INTO `oj_question` VALUES (440300575494639616,'A+B','输入包含一系列的a和b对，通过空格隔开。一对a和b占一行。','对于输入的每对a和b，你需要依次输出a、b的和。如对于输入中的第二对a和b，在输出中它们的和应该也在第二行','不会超过int大小','[\"1 5\",\"10 20\"]','[\"6\",\"30\"]','简单',1,131072,'你的任务是计算a+b。这是为了acm初学者专门设计的题目。你肯定发现还有其他题目跟这道题的标题类似，这些问题也都是专门为初学者提供的。',2,3,'网络','[\"基础\"]',0,0),(440302398993469440,'0-1背包问题','第一行输入物品的个数n和背包容量C。\n\n第二行输入每个物品的价值v[i].\n\n第三行输入每个物品的重量w[i]','第一行输出最大价值。','','[\"4 7\\n9 10 7 4\\n3 5 2 1\"]','[\"20\"]','中等',1,131072,'给定n种物品和一背包。物品i的重量是wi，其价值为vi，背包的容量为C。问应如何选择装入背包的物品，使得装入背包中物品的总价值最大?',1,2,'网络','[\"动态规划\",\"回溯算法\"]',0,0),(440302721887768576,'百万富翁问题','无输入','输出百万富翁和陌生人各自交出的钱数，单位是元。','','[\"\"]','[\"\"]','简单',1,32768,'一个百万富翁遇到一个陌生人，陌生人找他谈了一个换钱的计划。该计划如下：我每天给你10万元，你第一天给我1分钱，第二天2分钱，第三天4分钱……这样交换30天后，百万富翁交出了多少钱？陌生人交出了多少钱？（注意一个是万元，一个是分）',0,0,'名校复试机考真题-哈尔滨工业大学 ','[\"算法\"]',0,0),(440302917812097024,'统计单词','输入包括1行字符串，以“.”结束，字符串中包含多个单词，单词之间以一个或多个空格隔开。','可能有多组测试数据，对于每组数据输出字符串中每个单词包含的字母的个数。','没有数据要求','[\"hello how are you.\"]','[\"5 3 3 3\"]','中等',1,131072,'编一个程序，读入用户输入的，以“.”结尾的一行文字，统计一共有多少个单词，并分别输出每个单词含有多少个字符。（凡是以一个或多个空格隔开的部分就为一个单词',0,0,'名校复试机考真题-华中科技大学 洛谷','[\"真题\",\"字符串\"]',0,1),(440306122071609344,'函数求值','输入N，求F(N)的值，1= N <= 10^100(10的100次方)若F(N)很大，则求F(N)mod20123的值','','1 <= N <= 10^100(10的100次方)','[\"10\\n10\"]','[\"3\\n3\"]','中等',1,131072,'给定正整数N，函数F(N)表示小于等于N的自然数中1和2的个数之和，例如：1,2,3,4,5,6,7,8,9,10序列中1和2的个数之和为3，因此 F(10)=3',0,0,'名校复试机考真题-清华大学 洛谷 ','[\"\"]',0,0),(440308971534946304,'加密','输入有多组数据。每组数据一行，包含一个字符串（长度 <= 1000），为需要加密的字符串。','对应每组数据，输出加密后的字符串。','长度<=1000','[\"I am a student.\",\"I love you.\"]','[\"student. a am I\",\"you. love I\"]','中等',1,32768,'602实验室里面有些秘密文件（英文的），我们要把它进行加密。对每一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。句子中单词以空格符隔开。为简单起见，标点符号和普通字母一样处理。',0,0,'剑指Offer 洛谷 ','[\"字符串\"]',0,0),(440358116777922560,'邮票','无',' 输出一行，表示题目所求。','','[]','[]','中等',1,131072,'某人有8 角的邮票5 张，1 元的邮票4 张，1 元8 角的邮票6 张，用这些邮票中的一张或若干张可以得到多少中不同的邮资？',0,0,'名校复试机考真题-北京理工大学 洛谷 ','[]',0,0),(440383445575798784,'Very Good!','无需输入','\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\nVery    Good!\n\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*','','[\"无需输入\"]','[\"**************************\\nVery    Good!\\n**************************\\n\"]','简单',1,131072,'请参照本章例题，编写一个C程序，输出以下信息：\n\n\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\nVery    Good!\n\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\n\n别光打印Very Good!\n\n另注意，星号的行和Very Good的行之间并没有额外的空行。',0,3,'','[\"基础\"]',0,0),(440639447839674368,'最长公共子序列','为保障统计正确，需在输入字符数组x和y的第一个字符设置标示，该字符不参与统计\n\n第一行输入序列x。\n\n第二行输入序列y。','第一行输出最大子串长度。\n\n第二行输出最大子串。','','[\"@thiscbook\\n#tisnook\"]','[\"6\\ntisook\"]','困难',1,131072,'若给定序列X={x1,x2,…,xm}，则另一序列Z={z1,z2,…,zk}，是X的子序列是指存在一个严格递增下标序列{i1,i2,…,ik}使得对于所有j=1,2,…,k有：zj=xij。例如，序列Z={B，C，D，B}是序列X={A，B，C，B，D，A，B}的子序列，相应的递增下标序列为{2，3，5，7}。\n\n给定2个序列X和Y，当另一序列Z既是X的子序列又是Y的子序列时，称Z是序列X和Y的公共子序列。\n\n给定2个序列X={x1,x2,…,xm}和Y={y1,y2,…,yn}，找出X和Y的最长公共子序列。',0,0,'网络','[\"算法\"]',0,0),(440640301460230144,'文字排版','输入第一行是一个整数N，表示该段要求达到的宽度，1大于等于N大于等于80。该段文章由一个或多个单词组成，单词由ASCII码值为33到126（包含33和126）的字符组成，单词与单词之间用空格隔开（可能超过一个）。单词长度不会超过段落要求达到的宽度。一段文字所有单词的总长度不会超过10000个字符，任何一行都不会超过100个字符，任何一个单词都在同一行内。','对于每个段落，找出使其难看程度最小的排版形式并输出句子：“Minimal badness is B.”,B是指按可能的最好排版形式会发生的难看程度值。注意排版后文本行数任意，多余的空格也可删除。样例输入','','[\"28\\nThis is the example you  are\\nactually considering.\"]','[\"Minimal badness is 12.\"]','噩梦',1,131072,'写电子邮件是有趣的，但不幸的是经常写不好看，主要是因为所有的行不一样长，你的上司想要发排版精美的电子邮件，你的任务是为他编写一个电子邮件排版程序。\n\n完成这个任务最简单的办法是在太短的行中的单词之间插入空格，但这并不是最好的方法，考虑如下例子：\n\n****************************\n\nThis is the example you  are\n\nactually considering.\n\n假设我们想将第二行变得和第一行一样长，靠简单地插入空格则我们将得到如下结果：\n\n****************************\n\nThis is the example you  are\n\nactually        considering.\n\n \n\n但这太难看了，因为在第二行中有一个非常大的空白，如果将第一行的单词“are”移到下一行我们将得到较好的结果：\n\n****************************\n\nThis  is  the  example   you\n\nare  actually   considering.\n\n \n\n当然，这必须对难看程度进行量化。因此我们必须给出单词之间的空格的难看程度，一个包含N个空格符的空白段，其难看程度值为(n-1)2，程序的目的是使难看程度的总和最小化。例如，第一个例子的难看程度是1+7*7=50，而第二个例子的难看程度仅为1+1+1+4+1+4=12。\n\n输出时，每一行的开头和结尾处都必须是一个单词，即每行开头和结尾处不能有空白。唯一例外的是该行仅有一个单词组成的情况，对于这种情况你可将单词放在该行开头处输出，此时如果该单词比该行应有的长度短则我们指定它的最坏程度为500，当然在这种情况下，该行的实际长度即为该单词的长度。',0,0,'网络','[\"动态规划 \"]',0,0),(440640372809535488,'排序','输入的第一行包括一个整数n(1《=n《=100)。 接下来的一行包括n个整数。v','可能有多组测试数据，对于每组数据，将排序后的n个整数输出，每个数后面都有一个空格。每组测试数据的结果占一行。','n(1《=n《=100)','[\"5\n5 4 3 1 2\"]','[\"1 2 3 4 5 \"]','简单',1,131072,'对输入的n个数进行排序并输出。',0,0,'算法笔记 数据结构高分笔记 洛谷 ','[\"排序\"]',0,1),(440640412714143744,'密码','China','加密后的China','','[\"China\"]','[\"Glmre\"]','中等',1,10,'要将\"China\"译成密码，译码规律是：用原来字母后面的第4个字母代替原来的字母．例如，字母\"A\"后面第4个字母是\"E\"．\"E\"代替\"A\"。因此，\"China\"应译为\"Glmre\"。请编一程序，用赋初值的方法使cl、c2、c3、c4、c5五个变量的值分别为，C、h、i、n、a，经过运算，使c1、c2、c3、c4、c5分别变为G、l、m、r、e，并输出。',0,0,'','[\"基础\",\"C语言\"]',0,0),(440640466023747584,'有重复元素的排列问题','输入的第1 行是元素个数n，1大于等于n大于等于500。接下来的1 行\n\n是待排列的n 个元素。','程序运行结束时，将计算出的n 个元素的所有不同排列输出。\n\n最后1 行中的数是排列总数。','','[\"4\\naacc\"]','[\"aacc\\nacac\\nacca\\ncaac\\ncaca\\nccaa\\n6\"]','中等',1,131072,'设R=r1, r2 ,…, rn是要进行排列的n 个元素。其中元素 r1, r2 ,…, rn 可能相同。试设计\n\n一个算法，列出R 的所有不同排列。\n设R=r1, r2 ,…, rn是要进行排列的n 个元素。其中元素 r1, r2 ,…, rn 可能相同。试设计\n\n一个算法，列出R 的所有不同排列。\n\n\n',0,0,'网络','[\"算法\",\"复杂算法\"]',0,0),(440640530028826624,'任务调度','输入包含多组测试数据。每组第一行输入一个整数n（n 大于 100000），表示有n个任务。接下来n行，每行第一个表示前序任务，括号中的任务为若干个后序任务，表示只有在前序任务完成的情况下，后序任务才能开始。若后序为NULL则表示无后继任务。','输出调度方式，输出如果有多种适合的调度方式，请输出字典序最小的一种。','输入方式已说明','[\"4\\nTask0(Task1,Task2)\\nTask1(Task3)\\nTask2(NULL)\\nTask3(NULL)\\n\"]','[\"Task0 Task1 Task2 Task3\"]','困难',1,10,'读入任务调度序列，输出n个任务适合的一种调度方式。',0,0,'名校复试机考真题-中国科学技术大学','[\"真题\"]',0,0),(440640587125886976,'Hello World!','无需输入','Hello World!','','[\"\"]','[\"Hello World!\"]','简单',1,131072,'输出Hello World!',0,3,'C语言练习题','[\"基础\",\"C语言\"]',0,0),(440640629157007360,'圆圈游戏','There are several test cases, each test case firstly contains four integersn, m, S, T\nS is the XOR sum of students\' positions when Tracy wakes up. Another 3 integers n, m, T are described as the above. Then n integers will follow in the next line, which represent the positions of students initially. \nThe input will finish with the end of file.',' each test case, output the number of possible periods of time that Tracy had slept and matched the above restrictions. Please output zero if no time matched.',' 0 &lt; n &lt;= 100000, 0 &lt; m &lt;= 50, 0 &lt;= S &lt; 2^50 , 0 &lt; T &lt;= 10^16 ,and the student position is in the range [0, 2^m] ','[\"3 3 7 5\\n0 2 7\\n5 3 7 5\\n1 2 3 4 5\\n4 4 0 10\\n1 3 5 7\\n6 5 18 100\\n22 10 18 20 2 10\"]','[\"0\\n4\\n50\"]','噩梦',1,131072,'You must have some knowledge with circle games in ACM problems, such as Joseph Ring problem. Today we are going to introduce a new circle game described as follows. There is a circle which contains\npoints, numbered from 0 to M-1. At first, n students stand in different points of the circle. It is possible that more than one student stand in the same point. For each second, each student moves one \nstep forward in clockwise direction. The following picture gives us an example of that M equals to 8 and n equals to 3. Initially, the first student S1 stands in the point 0, the second student S2 stands \nin the point 2 and the third student stands in the point 7. After 2 seconds, they will change their positions as the right part of the picture.',0,0,'网络','[\"英语\",\"基础\"]',0,0),(440640691727634432,'钓鱼','输入第一行为一个整数n，第二行为一个整数h，第三行为n个用空格隔开的整数，表示Fi（i=1,2,…,n），第四行为n个用空格隔开的整数，表示di（i=1,2,…,n），第五行为n-1个用空格隔开的整数，表示ti（i=1,2,…,n-1）','输出一个整数，表示约翰最多能钓到的鱼的数量。','','[\"2\\n1\\n10 1\\n2 5 \\n2\"]','[\"31\"]','困难',1,10,'约翰是个垂钓谜，星期天他决定外出钓鱼h小时（1≤h≤16），约翰家附近共有n个池塘（2≤n≤25），这些池塘分布在一条直线上，约翰将这些池塘按离家的距离编上号，依次为L1,L2,…,Ln，约翰家门外就是第一个池塘，所以他到第一个池塘是不用花时间的，约翰可以任选若干个池塘垂钓，并且在每个池塘他都可以呆上任意长的时间，但呆的时间必须为5分钟的倍数，（5分钟为一个单位时间），已知从池塘Li到池塘Li+1要化去约翰ti个单位时间，每个池塘的上鱼率预先也是已知的，池塘Li在第一个单位时间内能钓到的鱼为Fi（0≤Fi≤100），并且每过一个单位时间在单位时间内能钓到的鱼将减少一个常数di（0≤di≤100），现在请你编一个程序计算约翰最多能钓到多少鱼。',0,0,'网络','[\"动态规划\"]',0,0),(440640773759832064,'查找元素(线性表)','第一行 原始数列：2 3 4 5 6 7 8 9 第二行 需要找的数：1','找到则输出数在数列中的位置，第一个位置输出1依次类推若没有找到输出0','','[\"2 3 4 5 6 7 8 9\\n5\"]','[\"4\"]','简单',1,10,'（线性表）试编写一个算法，在带表头结点的单链表中寻找一个数。若找到，则函数返回该数在列表中的地址(i [1~n])；若找不到，则函数返回0',0,0,'','[\"数据结构\"]',0,0),(440640832538808320,'找回文数','无','输出所有符合条件的回文数，','','[\"没有输入\"]','[\"2 3 4 5 6 7 8 9 11 22 33 44 55 66 77 88 99 101 111 121 131 141 151 161 171 181 191 202 212 222 232 242 252 262 272 282 292 303 313 323 333 343 353 363 373 383 393 404 414 424 434 444 454 464 474 484 494 505 515 525 535 545 555 565 575 585 595 606 616 626 636 646 656 666 676 686 696 707 717 727 737 747 757 767 777 787 797 808 818 828 838 848 858 868 878 888 898 909 919 929 939 949 959 969 979 989 999 1001 1111 1221 1331 1441 1551 1661 1771 1881 1991 2002 2112 2222 2332 2442 2552 2662 2772 2882 2992 3003 3113 3223 3333 3443 3553 3663 3773 3883 3993 4004 4114 4224 4334 4444 4554 4664 4774 4884 4994 5005 5115 5225 5335 5445 5555 5665 5775 5885 5995 6006 6116 6226 6336 6446 6556 6666 6776 6886 6996 7007 7117 7227 7337 7447 7557 7667 7777 7887 7997 8008 8118 8228 8338 8448 8558 8668 8778 8888 8998 9009 9119 9229 9339 9449 9559 9669 9779 9889 9999\"]','困难',1,131072,'找出10000以内的回文数。从左向右读与从右向左读是同一个数的数为回文数，如19391是回文数。',0,0,'网络','[\"入门-中级\"]',0,0),(440640901556080640,' 文件排版 format','输入文件第一行是一个整数N，表示排版后文章每行要求达到的宽度，1 大于等于 N 大于等于 80。后面是给出的一段文章，该段文章由一个或多个单词组成，单词由ASCII码值为33到126（包含33和126）的字符组成，单词与单词之间用空格隔开（可能超过一个）。单词长度不会超过段落要求达到的宽度。一段文字所有单词的总长度不会超过10000个字符，任何一行都不会超过100个字符，任何一个单词都在同一行内。输出','对于每个段落，找出使其难看程度最小的排版形式并输出句子：“Minimal badness is B.”,B是指按可能的最好排版形式会发生的难看程度值。注意排版后文本行数任意，多余的空格也可删除。','见输入输出。','[\"28\\nThis is the example you  are\\nactually considering.\"]','[\"Minimal badness is 12.\"]','噩梦',1,131072,'写电子邮件是有趣的，但不幸的是经常写不好看，主要是因为所有的行不一样长，你的上司想要发排版精美的电子邮件，你的任务是为他编写一个电子邮件排版程序。完成这个任务最简单的办法是在太短的行中的单词之间插入空格，但这并不是最好的方法，考虑如下例子：\\n\nThis is the example you  are\\n\nactually considering.\\n\n假设我们想将第二行变得和第一行一样长，靠简单地插入空格则我们将得到如下结果:\\n\nThis is the example you  are\\n\nactually        considering.\\n\n但这太难看了，因为在第二行中有一个非常大的空白，如果将第一行的单词“are”移到下一行我们将得到较好的结果：\\n\nThis  is  the  example   you\\n\nare  actually   considering.\\n\n当然，这必须对难看程度进行量化。因此我们必须给出单词之间的空格的难看程度，一个包含N个空格符的空白段，其难看程度值为(n-1)2，程序的目的是使难看程度的总和最小化。例如，第一个例子的难看程度是1+7*7=50，而第二个例子的难看程度仅为1+1+1+4+1+4=12。输出时，每一行的开头和结尾处都必须是一个单词，即每行开头和结尾处不能有空白。唯一例外的是该行仅有一个单词组成的情况，此时如果该单词比该行应有的长度短则我们指定它的难看程度为500。',0,0,'','[\"NOIP\",\"NOIP-模拟题\"]',0,0),(440640941439717376,'斐波那契数列','所求项数','数据项的值','','[\"10\"]','[\"34\"]','简单',1,131072,'斐波那契数列0,1,1,2,3,5,8,13,21,34,55……从第三项起，每一项都是紧挨着的前两项的和。写出计算斐波那契数列任意一个数据项的递归程序。',0,0,'基本算法-递归算法 一本通 一本通2018-第四章-递推算法 洛谷 ','[\"递归\"]',0,1),(440641192322011136,' 换零钱','','','','[\"0\"]','[\"11\"]','简单',1,131072,'某人想将手中的一张面值 100 元的人民币换成 5 元、 1 元和 0.5 元面值的票子，但要求换正好 100 张，且每种票子至少一张。问：有几种换法？提示： 用三重循环。想一想如何减少循环的次数。5 元、 1 元和 0.5 元最多各需要几张。记得要求正好换 100 张，总价值100元。循环变量代表某个币种的张数。',0,0,'网络','[\"基础\",\"美剧\"]',0,0),(440641233480716288,'试卷批分','','','','[\"读题\"]','[\"请输出正确答案\"]','噩梦',1,131072,'【问题描述：】 某学校进行了一次英语考试，共有10道是非题，每题为10分，解答用1表示“是”，用0表示“非”的方式。但老师批完卷后，发现漏批了一张试卷，而且标准答案也丢失了，手头只剩下了3张标有分数的试卷。\\n\n试卷一：\\n\n① ② ③ ④ ⑤ ⑥ ⑦ ⑧ ⑨ ⑩\\n\n0 0 1 0 1 0 0 1 0 0 得分：70\\n\n试卷二：\\n\n① ② ③ ④ ⑤ ⑥ ⑦ ⑧ ⑨ ⑩\\n\n0 1 1 1 0 1 0 1 1 1 得分：50\\n\n试卷三：\\n\n① ② ③ ④ ⑤ ⑥ ⑦ ⑧ ⑨ ⑩\\n\n0 1 1 1 0 0 0 1 0 1 得分：30\\n\n待批试卷：\\n\n① ② ③ ④ ⑤ ⑥ ⑦ ⑧ ⑨ ⑩\\n\n0 0 1 1 1 0 0 1 1 1 得分：？\\n\n【问题求解：】 请编一程序依据这三张试卷，算出漏批的那张试卷的分数。',0,0,'','[\"回溯\",\"深搜\"]',0,0),(440641311394107392,'对称平方数1','无任何输入数据','输出具有题目要求的性质的数。如果输出数据不止一组，各组数据之间以回车隔开','','[\"\"]','[\"\"]','中等',1,131072,'打印所有不超过256，其平方具有对称性质的数。如2，11就是这样的数，因为2*2=4，11*11=121。',0,0,'名校复试机考真题-清华大学 ','[\"真题\"]',0,0),(440642695376015360,'DNA','输入包含多组测试数据。第一个整数N（N大于等于15）,N表示组数，每组数据包含两个整数a,b。a表示一个单位的DNA串的行数，a为奇数且 3大于等于a大于等于39。b表示重复度(1大于等于b大于等于20)。','输出DNA的形状，每组输出间有一空行。','','[\"c\"]','[\"X X\\n X\\nX X\\n\\nX   X\\n X X\\n  X\\n X X\\nX   X\\n X X\\n  X\\n X X\\nX   X\\n X X\\n  X\\n X X\\nX   X\\n X X\\n  X\\n X X\\nX   X\"]','中等',1,131072,'小强从小就喜欢生命科学，他总是好奇花草鸟兽从哪里来的。终于， 小强上中学了，接触到了神圣的名词--DNA.它有一个双螺旋的结构。这让一根筋的小强抓破头皮，“要是能画出来就好了” 小强喊道。现在就请你帮助他吧',0,0,'吉首大学软件学院 ','[\"基础\",\"C语言\"]',0,0);
/*!40000 ALTER TABLE `oj_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oj_question_sample`
--

DROP TABLE IF EXISTS `oj_question_sample`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oj_question_sample` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `qid` bigint DEFAULT NULL,
  `question_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `sample_input` varchar(10000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `sample_output` varchar(10000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oj_question_sample_oj_question_id_fk` (`qid`),
  CONSTRAINT `oj_question_sample_oj_question_id_fk` FOREIGN KEY (`qid`) REFERENCES `oj_question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oj_question_sample`
--

LOCK TABLES `oj_question_sample` WRITE;
/*!40000 ALTER TABLE `oj_question_sample` DISABLE KEYS */;
INSERT INTO `oj_question_sample` VALUES (38,440300575494639616,'A+B','[\"-1 1\",\"1 -1\",\"1949 15390\",\"22139 30520\",\"16185 15229\",\"-4362 7115\",\"-23038 -16881\",\"10403 18008\",\"12170 17903\",\"511 12424\",\"7029 23302\",\"12156 31754\",\"28220 -12039\",\"2488 14555\",\"27261 25192\",\"13919 8388\",\"8810 16362\",\"4417 11300\",\"18808 6010\",\"11176 25583\",\"20055 16469\",\"21992 5367\",\"20775 1648\",\"26423 18538\",\"18921 1815\",\"14271 9628\",\"29491 16596\",\"242 8455\",\"19752 23186\",\"6174 7313\",\"19624 26114\",\"14145 23821\",\"2756 8510\",\"18462 30979\",\"2165 31661\",\"26000 9396\",\"23181 25126\",\"1823 32601\",\"20973 5720\",\"2769 17836\",\"29689 5481\",\"4418 3497\",\"22345 20957\",\"24567 28566\",\"14497 17594\",\"15856 16449\",\"23588 6633\",\"32380 16521\",\"3780 31712\",\"11858 26143\",\"23549 17022\",\"5852 8590\",\"19395 2358\",\"3030 17785\",\"15545 28784\",\"14104 29476\",\"11939 3628\",\"22192 12343\",\"4382 20969\",\"8500 2494\",\"27279 24517\",\"17495 26739\",\"27481 9957\",\"12729 31296\",\"16723 14103\",\"11780 21760\",\"16249 6582\",\"22068 13958\",\"11745 12751\",\"3894 1504\",\"22001 26497\",\"5045 31196\",\"7888 8874\",\"5169 2252\",\"19994 22720\",\"7505 1415\",\"16600 12796\",\"14458 3504\",\"12463 2494\",\"31392 11579\",\"22871 20013\",\"30044 23340\",\"4687 797\",\"15935 13218\",\"12495 27703\",\"103 4885\",\"17919 8787\",\"19535 30428\",\"3430 6185\",\"6635 18162\",\"8101 21033\",\"12533 27897\",\"15717 21927\",\"27522 23679\",\"18062 4923\",\"1752 28801\",\"12729 20584\",\"30339 16941\",\"6121 21627\",\"28070 23788\",\"10357 23734\",\"-4752 -29600\"]','[\"0\",\"0\",\"17339\",\"52659\",\"31414\",\"2753\",\"-39919\",\"28411\",\"30073\",\"12935\",\"30331\",\"43910\",\"16181\",\"17043\",\"52453\",\"22307\",\"25172\",\"15717\",\"24818\",\"36759\",\"36524\",\"27359\",\"22423\",\"44961\",\"20736\",\"23899\",\"46087\",\"8697\",\"42938\",\"13487\",\"45738\",\"37966\",\"11266\",\"49441\",\"33826\",\"35396\",\"48307\",\"34424\",\"26693\",\"20605\",\"35170\",\"7915\",\"43302\",\"53133\",\"32091\",\"32305\",\"30221\",\"48901\",\"35492\",\"38001\",\"40571\",\"14442\",\"21753\",\"20815\",\"44329\",\"43580\",\"15567\",\"34535\",\"25351\",\"10994\",\"51796\",\"44234\",\"37438\",\"44025\",\"30826\",\"33540\",\"22831\",\"36026\",\"24496\",\"5398\",\"48498\",\"36241\",\"16762\",\"7421\",\"42714\",\"8920\",\"29396\",\"17962\",\"14957\",\"42971\",\"42884\",\"53384\",\"5484\",\"29153\",\"40198\",\"4988\",\"26706\",\"49963\",\"9615\",\"24797\",\"29134\",\"40430\",\"37644\",\"51201\",\"22985\",\"30553\",\"33313\",\"47280\",\"27748\",\"51858\",\"34091\",\"-34352\"]'),(39,440302398993469440,'0-1背包问题','[\"4 7\\n9 10 7 4\\n3 5 2 1\"]','[\"20\"]'),(40,440302721887768576,'百万富翁问题','[\"\"]','[\"10737418 3000000\"]'),(41,440302917812097024,'统计单词','[\"This is a test input.\",\"Here is very very very very very very very very very very very very very very very very very very long sentence, but the length of last word is four.\",\"Include your world.\",\"That is my hope.\"]','[\"4 2 1 4 5\",\"4 2 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 9 3 3 6 2 4 4 2 4\",\"7 4 5\",\"4 2 2 4\"]'),(44,440306122071609344,'函数求值','[\"10\",\"11\",\"12\",\"9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999\",\"10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000\",\"1\",\"2\",\"3\",\"99\",\"100\"]','[\"3\",\"5\",\"7\",\"15337\",\"15338\",\"1\",\"2\",\"3\",\"42\",\"43\"]'),(45,440308971534946304,'加密','[\"F\",\"MXT SH J ZD JOP LBUN SY\",\"M FVZK V CB H RSFMYCKPJ LNTEM N DS B\",\"M\",\"B F N OA O BU LWOGQYDWTU AL F QEEBTVKQ E AIN K D B WPT\"]','[\"F\",\"SY LBUN JOP ZD J SH MXT\",\"B DS N LNTEM RSFMYCKPJ H CB V FVZK M\",\"M\",\"WPT B D K AIN E QEEBTVKQ F AL LWOGQYDWTU BU O OA N F B\"]'),(46,440358116777922560,'邮票','[\"\"]','[\"82\"]'),(55,440383445575798784,'Very Good!','[\"0\"]','[\"**************************\\nVery    Good!\\n**************************\\n\"]'),(56,440639447839674368,'最长公共子序列','[\"@thiscbook\\n#tisnook\"]','[\"6\\ntisook\"]'),(57,440640301460230144,'文字排版','[\"28\\nThis is the example you  are\\nactually considering.\"]','[\"Minimal badness is 12.\"]'),(58,440640372809535488,'排序','[\"5\\n5 4 3 1 2\",\"8\\n9180 19654 439 13765 15144 14280 1858 17845\",\"96\\n21520 31485 22787 28349 26015 17020 27208 28392 1902 15199 5362 7731 7670 29642 15335 27641 21237 18197 875 2175 32368 12996 8771 388 8436 30649 19994 3353 21282 23464 18213 30387 20075 7884 18179 25215 29463 31192 22247 23285 28556 14385 20728 23728 27853 16986 4344 21629 25665 11439 6180 17330 694 7767 10332 32449 15525 9332 6788 14860 30647 17820 29738 22779 28041 27021 105 13132 11180 26908 28685 17579 21745 23151 17655 31363 2019 28012 3268 5139 3732 27761 11945 18628 15261 13638 20641 10426 6240 15952 6091 27561 18576 31751 18445 8682\",\"85\\n15018 1790 31694 24423 16433 17923 6962 2596 19075 6072 9043 3136 15849 29841 31460 376 6082 12993 29790 13101 31097 13658 20632 24749 24501 5814 20375 23732 22991 21015 17319 9267 21897 12426 23210 21071 21245 32017 4529 5549 5331 17408 1737 2797 8375 8616 7448 8308 9226 25651 10024 26479 4312 1231 2946 30228 1803 15375 6015 16171 24030 13709 29787 718 24980 9941 29323 15469 6505 19838 8016 12821 10697 6298 22669 2289 9920 337 21206 785 27314 22216 17865 19050 17740\",\"25\\n12961 27934 5818 156 30984 28414 11034 10682 11821 5197 27185 26534 27159 18887 12125 916 32569 19938 24011 14413 20550 32596 1258 5331 14406\",\"13\\n10528 29256 14570 16934 1211 268 25372 3666 21802 12582 11739 24372 2157\",\"97\\n12102 15929 24266 17606 27638 6760 21698 32665 12433 2975 26947 6624 10652 23327 5151 29048 11665 30026 189 29307 27954 10412 24847 28260 32284 22073 27843 28276 29599 30421 24766 1365 31181 6682 4806 25598 9937 16981 28030 13782 30339 16849 26776 2076 9273 32089 20806 16518 22239 14328 30449 26408 21322 19459 4142 18328 10441 30041 27630 11600 3069 4330 17140 16211 21483 30070 15757 16148 19792 18839 379 11923 22580 31490 23602 8649 962 15114 23879 16198 7255 9318 29989 13337 24537 28211 27650 7920 19530 20541 17681 13952 15517 31866 25081 12071 18376\",\"44\\n31367 9168 5658 11319 15519 1560 8399 30137 10361 22959 4066 23866 32010 16128 10786 22044 18623 4372 2952 17733 29297 6835 5576 32618 20118 9933 20262 31885 5300 28697 9099 25796 18716 13308 15320 23101 28372 27820 24442 9343 5589 30357 5183 8434\",\"54\\n9468 28775 7330 9001 2530 21247 31552 17765 29995 30314 20330 17123 28415 4417 18475 22029 12657 31069 6086 3252 25950 32049 14255 3073 9880 26461 11739 20654 4932 24705 14137 16245 27265 9039 31617 6601 32331 14053 11820 10949 31902 9049 16579 16482 21470 21653 2653 17644 13942 8037 2148 3255 448 7374\",\"47\\n11278 3109 20386 27733 26988 7813 18513 17688 8175 7384 15106 10816 9737 8726 15330 28690 3193 4949 5564 2048 18809 28209 28363 25130 9273 22129 12236 2086 29837 9960 24918 21756 2786 2456 29550 28277 21623 23326 9047 25494 4042 23882 29607 5223 9552 32013 27296\"]','[\"1 2 3 4 5 \",\"439 1858 9180 13765 14280 15144 17845 19654 \",\"105 388 694 875 1902 2019 2175 3268 3353 3732 4344 5139 5362 6091 6180 6240 6788 7670 7731 7767 7884 8436 8682 8771 9332 10332 10426 11180 11439 11945 12996 13132 13638 14385 14860 15199 15261 15335 15525 15952 16986 17020 17330 17579 17655 17820 18179 18197 18213 18445 18576 18628 19994 20075 20641 20728 21237 21282 21520 21629 21745 22247 22779 22787 23151 23285 23464 23728 25215 25665 26015 26908 27021 27208 27561 27641 27761 27853 28012 28041 28349 28392 28556 28685 29463 29642 29738 30387 30647 30649 31192 31363 31485 31751 32368 32449 \",\"337 376 718 785 1231 1737 1790 1803 2289 2596 2797 2946 3136 4312 4529 5331 5549 5814 6015 6072 6082 6298 6505 6962 7448 8016 8308 8375 8616 9043 9226 9267 9920 9941 10024 10697 12426 12821 12993 13101 13658 13709 15018 15375 15469 15849 16171 16433 17319 17408 17740 17865 17923 19050 19075 19838 20375 20632 21015 21071 21206 21245 21897 22216 22669 22991 23210 23732 24030 24423 24501 24749 24980 25651 26479 27314 29323 29787 29790 29841 30228 31097 31460 31694 32017 \",\"156 916 1258 5197 5331 5818 10682 11034 11821 12125 12961 14406 14413 18887 19938 20550 24011 26534 27159 27185 27934 28414 30984 32569 32596 \",\"268 1211 2157 3666 10528 11739 12582 14570 16934 21802 24372 25372 29256 \",\"189 379 962 1365 2076 2975 3069 4142 4330 4806 5151 6624 6682 6760 7255 7920 8649 9273 9318 9937 10412 10441 10652 11600 11665 11923 12071 12102 12433 13337 13782 13952 14328 15114 15517 15757 15929 16148 16198 16211 16518 16849 16981 17140 17606 17681 18328 18376 18839 19459 19530 19792 20541 20806 21322 21483 21698 22073 22239 22580 23327 23602 23879 24266 24537 24766 24847 25081 25598 26408 26776 26947 27630 27638 27650 27843 27954 28030 28211 28260 28276 29048 29307 29599 29989 30026 30041 30070 30339 30421 30449 31181 31490 31866 32089 32284 32665 \",\"1560 2952 4066 4372 5183 5300 5576 5589 5658 6835 8399 8434 9099 9168 9343 9933 10361 10786 11319 13308 15320 15519 16128 17733 18623 18716 20118 20262 22044 22959 23101 23866 24442 25796 27820 28372 28697 29297 30137 30357 31367 31885 32010 32618 \",\"448 2148 2530 2653 3073 3252 3255 4417 4932 6086 6601 7330 7374 8037 9001 9039 9049 9468 9880 10949 11739 11820 12657 13942 14053 14137 14255 16245 16482 16579 17123 17644 17765 18475 20330 20654 21247 21470 21653 22029 24705 25950 26461 27265 28415 28775 29995 30314 31069 31552 31617 31902 32049 32331 \",\"2048 2086 2456 2786 3109 3193 4042 4949 5223 5564 7384 7813 8175 8726 9047 9273 9552 9737 9960 10816 11278 12236 15106 15330 17688 18513 18809 20386 21623 21756 22129 23326 23882 24918 25130 25494 26988 27296 27733 28209 28277 28363 28690 29550 29607 29837 32013 \",\"897 1994 2031 2151 2233 2748 2821 3177 3494 4074 4153 4731 4791 4897 5490 6010 7066 7316 7506 7574 7621 7783 7996 8123 8316 8423 9542 9634 9900 10252 10345 10372 10423 10514 10946 11497 11841 12184 13407 13793 14188 14730 16589 17554 17714 18078 19031 19541 19726 19770 19815 20376 20420 21406 22789 22910 23196 23260 23365 23471 24535 24775 24793 24861 24920 25017 25243 26258 26389 26907 27191 27382 27511 27656 27894 28038 28223 29081 29164 29630 30404 30771 30824 31315 31375 31689 31737 31764 31914 32567 \"]'),(59,440640412714143744,'密码','[\"China\"]','[\"Glmre\"]'),(60,440640466023747584,'有重复元素的排列问题','[\"4\\naacc\"]','[\"aacc\\nacac\\nacca\\ncaac\\ncaca\\nccaa\\n6\"]'),(61,440640530028826624,'任务调度','[\"4\\nTask0(Task1,Task2)\\nTask1(Task3)\\nTask2(NULL)\\nTask3(NULL)\\n\"]','[\"Task0 Task1 Task2 Task3\"]'),(62,440640587125886976,'Hello World!','[\"0\"]','[\"Hello World!\"]'),(63,440640629157007360,'圆圈游戏','[]','[]'),(64,440640691727634432,'钓鱼','[\"2\\n1\\n10 1\\n2 5 \\n2\"]','[\"31\"]'),(65,440640773759832064,'查找元素(线性表)','[\"2 3 4 5 6 7 8 9\\n5\"]','[\"4\"]'),(66,440640832538808320,'找回文数','[\"0\"]','[\"2 3 4 5 6 7 8 9 11 22 33 44 55 66 77 88 99 101 111 121 131 141 151 161 171 181 191 202 212 222 232 242 252 262 272 282 292 303 313 323 333 343 353 363 373 383 393 404 414 424 434 444 454 464 474 484 494 505 515 525 535 545 555 565 575 585 595 606 616 626 636 646 656 666 676 686 696 707 717 727 737 747 757 767 777 787 797 808 818 828 838 848 858 868 878 888 898 909 919 929 939 949 959 969 979 989 999 1001 1111 1221 1331 1441 1551 1661 1771 1881 1991 2002 2112 2222 2332 2442 2552 2662 2772 2882 2992 3003 3113 3223 3333 3443 3553 3663 3773 3883 3993 4004 4114 4224 4334 4444 4554 4664 4774 4884 4994 5005 5115 5225 5335 5445 5555 5665 5775 5885 5995 6006 6116 6226 6336 6446 6556 6666 6776 6886 6996 7007 7117 7227 7337 7447 7557 7667 7777 7887 7997 8008 8118 8228 8338 8448 8558 8668 8778 8888 8998 9009 9119 9229 9339 9449 9559 9669 9779 9889 9999\"]'),(67,440640901556080640,' 文件排版 format','[\"28\\nThis is the example you  are\\nactually considering.\"]','[\"Minimal badness is 12.\"]'),(68,440640941439717376,'斐波那契数列','[\"25\",\"33\",\"6\",\"10\",\"15\"]','[\"46368\",\"2178309\",\"5\",\"34\",\"377\"]'),(69,440641192322011136,' 换零钱','[\"0\"]','[\"11\"]'),(70,440641233480716288,'试卷批分','[\"0\"]','[\"60\"]'),(71,440641311394107392,'对称平方数1','[\"0\"]','[\"0\\n1\\n2\\n3\\n11\\n22\\n26\\n101\\n111\\n121\\n202\\n212\\n\"]'),(72,440642695376015360,'DNA','[\"2\\n3 1\\n5 4\"]','[\"X X\\n X\\nX X\\n\\nX   X\\n X X\\n  X\\n X X\\nX   X\\n X X\\n  X\\n X X\\nX   X\\n X X\\n  X\\n X X\\nX   X\\n X X\\n  X\\n X X\\nX   X\"]');
/*!40000 ALTER TABLE `oj_question_sample` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oj_submit_record`
--

DROP TABLE IF EXISTS `oj_submit_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oj_submit_record` (
  `id` bigint NOT NULL,
  `uid` bigint DEFAULT NULL,
  `qid` bigint DEFAULT NULL,
  `question_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `time` double DEFAULT NULL,
  `memeory` double DEFAULT NULL,
  `language` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `code` varchar(10000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `submit_time` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `title` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `message` longtext COLLATE utf8mb3_unicode_ci,
  `test_sample` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `user_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oj_submit_record_oj_question_id_fk` (`qid`),
  KEY `oj_submit_record_oj_user_id_fk` (`uid`),
  CONSTRAINT `oj_submit_record_oj_question_id_fk` FOREIGN KEY (`qid`) REFERENCES `oj_question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `oj_submit_record_oj_user_id_fk` FOREIGN KEY (`uid`) REFERENCES `oj_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oj_submit_record`
--

LOCK TABLES `oj_submit_record` WRITE;
/*!40000 ALTER TABLE `oj_submit_record` DISABLE KEYS */;
INSERT INTO `oj_submit_record` VALUES (440301623839952896,282718464,440300575494639616,'A+B',0,3,'Cpp_c','#include<iostream>\n\nusing namespace std;\n\nint main(){\n    int a,b;\n    cin >> a >> b;\n    cout << a + b;\n}','2023-07-29- 16:45:54','Accept','Accept','{}','管理员'),(440383704565682176,282718464,440383445575798784,'Very Good!',0,0,'python','print(\"**************************\")\nprint(\"Very Good!\")\nprint(\"**************************\")','2023-07-29- 22:12:05','WrongAnswer','答案错误,出现在第 1 个测试样例','{\"input\":\"0\",\"output\":\"**************************\nVery    Good!\n**************************\n\",\"userOutput\":\"**************************Very Good!**************************\"}','管理员'),(440383788317544448,282718464,440383445575798784,'Very Good!',0,0,'python','print(\"**************************\\n\")\nprint(\"Very Good!\\n\")\nprint(\"**************************\")','2023-07-29- 22:12:25','WrongAnswer','答案错误,出现在第 1 个测试样例','{\"input\":\"0\",\"output\":\"**************************\nVery    Good!\n**************************\n\",\"userOutput\":\"**************************Very Good!**************************\"}','管理员'),(440384471435448320,282718464,440383445575798784,'Very Good!',0,0,'python','print(\"**************************\",end=\'\\n\')\nprint(\"Very Good!\",end=\'\\n\')\nprint(\"**************************\")','2023-07-29- 22:15:08','WrongAnswer','答案错误,出现在第 1 个测试样例','{\"input\":\"0\",\"output\":\"**************************\nVery    Good!\n**************************\n\",\"userOutput\":\"**************************Very Good!**************************\"}','管理员'),(441003977228816384,293616896,440640587125886976,'Hello World!',0,0,'java','public static void main (String[] args) {\nSystem.out.println(\"Hello World!\");\n}','2023-07-31- 15:16:40','Compile Error','Main.java:1: error: class, interface, or enum expectedpublic static void main (String[] args) {              ^Main.java:3: error: class, interface, or enum expected}^2 errors','{}','试用账号'),(441004040441171968,293616896,440640587125886976,'Hello World!',0,0,'java','System.out.println(\"Hello World!\");','2023-07-31- 15:16:58','Compile Error','Main.java:1: error: class, interface, or enum expectedSystem.out.println(\"Hello World!\");^1 error','{}','试用账号'),(441004143637827584,293616896,440640587125886976,'Hello World!',0,0,'java','Hello World!','2023-07-31- 15:17:25','Compile Error','Main.java:1: error: class, interface, or enum expectedHello World!^1 error','{}','试用账号'),(441271064937500672,293616896,440300575494639616,'A+B',0,3,'Cpp_c','#include<bits/stdc++.h>\r\nusing namespace std;\r\n\r\nint main()\r\n{\r\n  int a, b, c;\r\n  cin >> a >> b;\r\n  c = a + b;\r\n  cout << c;\r\n}','2023-08-01- 08:58:04','Accept','Accept','{}','试用账号'),(441271116917510144,293616896,440300575494639616,'A+B',0,0,'Cpp_c','#include<bits/stdc++.h>\r\nusing namespace std;\r\n\r\nint main()\r\n{\r\n  int a, b, c;\r\n  cin >> a >> b;\r\n  c = a + b;\r\n  cout << a;\r\n}','2023-08-01- 08:58:17','WrongAnswer','答案错误,出现在第 1 个测试样例','{\"input\":\"-1 1\",\"output\":\"0\",\"userOutput\":\"-1\"}','试用账号'),(441356696770711552,293616896,440302398993469440,'0-1背包问题',0,0,'Cpp_c','#include <iostream>\r\n#include <algorithm>\r\n#include <cstring>\r\nusing namespace std;\r\n \r\nconst int N = 1010;\r\n \r\nint n, m;\r\nint w[N], v[N];\r\nint f[N][N];\r\n \r\nint main()\r\n{\r\n    cin >> n >> m;\r\n \r\n    for(int i = 1; i <= n; i++)\r\n        cin >> w[i] >> v[i];\r\n \r\n    for(int i = 1;i <= n;i++)\r\n        for(int j = 0;j <= m;j++)\r\n        {\r\n            f[i][j] = f[i-1][j]; //不选当前物品\r\n            if(j >= w[i]) //剩余空间大于当前物品的大小\r\n                f[i][j] = max(f[i][j], f[i-1][j-w[i]]+v[i]); //选当前物品\r\n        }\r\n \r\n    cout<<f[n][m]<<endl;\r\n \r\n    return 0;\r\n}','2023-08-01- 14:38:22','WrongAnswer','答案错误,出现在第 1 个测试样例','{\"input\":\"4 7\n9 10 7 4\n3 5 2 1\",\"output\":\"20\",\"userOutput\":\"6\"}','试用账号'),(441356963297759232,293616896,440302398993469440,'0-1背包问题',0,3,'Cpp_c','#include <iostream>\r\n#include <algorithm>\r\n#include <cstring>\r\nusing namespace std;\r\n \r\nconst int N = 1010;\r\n \r\nint n, m;\r\nint w[N], v[N];\r\nint f[N][N];\r\n \r\nint main()\r\n{\r\n    cin >> n >> m;\r\n \r\n    for(int i = 1; i <= n; i++)\r\n        cin >> v[i];\r\n        \r\n    for(int i = 1; i <= n; i++)\r\n        cin >> w[i];\r\n \r\n    for(int i = 1;i <= n;i++)\r\n        for(int j = 0;j <= m;j++)\r\n        {\r\n            f[i][j] = f[i-1][j]; //不选当前物品\r\n            if(j >= w[i]) //剩余空间大于当前物品的大小\r\n                f[i][j] = max(f[i][j], f[i-1][j-w[i]]+v[i]); //选当前物品\r\n        }\r\n \r\n    cout<<f[n][m]<<endl;\r\n \r\n    return 0;\r\n}','2023-08-01- 14:39:26','Accept','Accept','{}','试用账号');
/*!40000 ALTER TABLE `oj_submit_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oj_user`
--

DROP TABLE IF EXISTS `oj_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oj_user` (
  `id` bigint NOT NULL,
  `nick_name` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `experience` int DEFAULT NULL,
  `level` int DEFAULT NULL,
  `rank` int DEFAULT NULL,
  `location` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `school` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `tag` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `gender` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `easy_resolve` int DEFAULT NULL,
  `meddle_resolve` int DEFAULT NULL,
  `hard_resolve` int DEFAULT NULL,
  `nightmare_resolve` int DEFAULT NULL,
  `role` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `url` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `sign` longtext COLLATE utf8mb3_unicode_ci,
  `fans` bigint DEFAULT NULL,
  `subscribe` bigint DEFAULT NULL,
  `ban` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oj_user`
--

LOCK TABLES `oj_user` WRITE;
/*!40000 ALTER TABLE `oj_user` DISABLE KEYS */;
INSERT INTO `oj_user` VALUES (282718464,'管理员','admin','21232f297a57a5a743894a0e4a801fc3',16400,16,11,'北京','清华大学','[\"HTML\",\"CSS\"]','男',4,3,0,0,'admin','https://ts4.cn.mm.bing.net/th?id=OIP-C.sfK-E4aMZY03QPQAd0VqlAAAAA&w=249&h=250&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2','人生苦短，我学java',0,1,0),(293616896,'试用账号','user','25d55ad283aa400af464c76d713c07ad',1400,2,0,'悲剧','xxx大学','[\"试用账号\"]','保密',3,2,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,1,0,0),(293698176,'马明宇','gdgrdfsf@qq.com','3070d7d59ed08a8691e8da7750b0245d',0,0,0,NULL,NULL,NULL,'保密',0,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(293706240,'杨志聪','1849410396@qq.com','ff6a6c047c9a3b40b8b565f0578612ab',0,0,0,NULL,NULL,NULL,'保密',0,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(293716352,'xl6','15878861169@163.com','f50a0d285465d2cc0e1b7f2437808b52',0,0,0,NULL,NULL,NULL,'保密',0,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(293719040,'陈子森','3486428621@qq.com','57417c92c788de83b426f64d29275505',600,1,0,NULL,NULL,NULL,'保密',4,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(293721344,'FluffySnow','1090099483@qq.com','8f732e4ada35e2b1b20a908b4c8a8f74',0,0,0,NULL,NULL,NULL,'保密',0,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(293722880,'wangkai','2274433623@qq。com','37070b4b2deb983ccd81e784a25cacbb',0,0,0,NULL,NULL,NULL,'保密',0,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(293723648,'wangkai','2274433623@qq.com','37070b4b2deb983ccd81e784a25cacbb',0,0,0,NULL,NULL,NULL,'保密',0,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(293724672,'LIUBIN','3485209434@qq.com','da32c2cad7ae7c3659739b6631807256',0,0,0,NULL,NULL,NULL,'保密',0,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(293738752,'韩玉婷','2152174805@qq.com','d57f5f4921c3a57c2e2af7901fc008c5',0,0,0,NULL,NULL,NULL,'保密',0,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(412369463887925248,'888@qq.com','888@qq.com','25d55ad283aa400af464c76d713c07ad',300,1,0,'龙江路','河池学院','[\"HTML\",\"CSS\"]','保密',2,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(412415953972891648,'万道mian','shimianyou@163.com','53cf5d2fcd36a6875eb6966da105d74e',300,1,0,NULL,NULL,'[]','保密',3,0,0,0,'普通用户','https://s1.ax1x.com/2023/06/20/pC85hDJ.jpg',NULL,0,0,0),(412418092191322112,'今天ac了吗','2303501753@qq.com','e4579b27525a9f8fb383a08a1c1c50cc',0,0,0,NULL,NULL,NULL,'保密',0,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(412418127507361792,'龘靐sky','m15677670735@163.com','25d55ad283aa400af464c76d713c07ad',0,0,0,NULL,NULL,NULL,'保密',0,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(412422321597452288,'素白衫','18830896647@163.com','13357db4b9cb7b8164ea712e143f3a2a',0,0,0,NULL,NULL,NULL,'保密',0,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(412432194913570816,'一叶神影','2234878474@qq.com','fb1032448d6df253511d19f816348226',0,0,0,NULL,NULL,NULL,'保密',0,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(412433001557921792,'alancheng','alancheng324@163.com','5cdfb3a654b6e73e5a8f9ef4f3dd1086',0,0,0,NULL,NULL,NULL,'保密',0,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(412438175991074816,'meng','2167190671@qq.com','e09af618a6903ff25793d5dd823baef7',100,1,0,NULL,NULL,'[]','保密',1,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(412438584772136960,'哎呀没时间了','chenjumeimei@163.com','0bd11a2a2c16a28dcd5d4a51350ec4b0',400,1,0,NULL,NULL,'[]','保密',2,1,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(412438666892414976,'2mux','1461157240@qq.com','25d55ad283aa400af464c76d713c07ad',0,0,0,NULL,NULL,NULL,'保密',0,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(412440889215029248,'suk46','suk46@qq.com','25d55ad283aa400af464c76d713c07ad',0,0,0,NULL,NULL,NULL,'保密',0,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(421173725816295424,'111','289887507@qq.com','2b2c804ada208d6e03af8de4265cbafe',0,0,0,NULL,NULL,NULL,'保密',0,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(425481877072252928,'demo','2688547859@qq.com','25d55ad283aa400af464c76d713c07ad',0,0,0,NULL,NULL,NULL,'保密',0,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(428788321590841344,'12217998','12217998@qq.com','14ed1a22176d3805f01deeab4c7aae03',0,0,0,NULL,NULL,NULL,'保密',0,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0),(438137526478311424,'kkdd','760303601@qq.com','8ddcff3a80f4189ca1c9d4d902c3c909',0,0,0,NULL,NULL,NULL,'保密',0,0,0,0,'普通用户','https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',NULL,0,0,0);
/*!40000 ALTER TABLE `oj_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oj_user_day_submit`
--

DROP TABLE IF EXISTS `oj_user_day_submit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oj_user_day_submit` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uid` bigint DEFAULT NULL,
  `submit_date` varchar(30) DEFAULT NULL,
  `submit` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oj_user_day_submit_oj_user_id_fk` (`uid`),
  CONSTRAINT `oj_user_day_submit_oj_user_id_fk` FOREIGN KEY (`uid`) REFERENCES `oj_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oj_user_day_submit`
--

LOCK TABLES `oj_user_day_submit` WRITE;
/*!40000 ALTER TABLE `oj_user_day_submit` DISABLE KEYS */;
INSERT INTO `oj_user_day_submit` VALUES (9,282718464,'2023-5-13',6),(10,282718464,'2023-5-12',0),(11,293616896,'2023-5-12',2),(12,293616896,'2023-5-13',4),(13,293719040,'2023-5-13',6),(14,412369463887925248,'2023-5-13',2),(15,412415953972891648,'2023-5-13',3),(16,412438584772136960,'2023-5-13',3),(17,412438175991074816,'2023-5-13',1),(18,282718464,'2023-7-25',1),(19,293616896,'2023-7-25',2);
/*!40000 ALTER TABLE `oj_user_day_submit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oj_user_month_submit`
--

DROP TABLE IF EXISTS `oj_user_month_submit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oj_user_month_submit` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uid` bigint DEFAULT NULL,
  `year` int DEFAULT NULL,
  `month` int DEFAULT NULL,
  `count` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oj_user_month_submit_oj_user_id_fk` (`uid`),
  CONSTRAINT `oj_user_month_submit_oj_user_id_fk` FOREIGN KEY (`uid`) REFERENCES `oj_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oj_user_month_submit`
--

LOCK TABLES `oj_user_month_submit` WRITE;
/*!40000 ALTER TABLE `oj_user_month_submit` DISABLE KEYS */;
INSERT INTO `oj_user_month_submit` VALUES (13,282718464,2023,5,6),(14,293616896,2023,5,6),(15,293719040,2023,5,6),(16,412369463887925248,2023,5,2),(17,412415953972891648,2023,5,3),(18,412438584772136960,2023,5,3),(19,412438175991074816,2023,5,1),(20,282718464,2023,7,1),(21,293616896,2023,7,2);
/*!40000 ALTER TABLE `oj_user_month_submit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oj_user_resolve`
--

DROP TABLE IF EXISTS `oj_user_resolve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oj_user_resolve` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uid` bigint DEFAULT NULL,
  `qid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oj_user_resolve_oj_user_id_fk` (`uid`),
  KEY `oj_user_resolve_oj_question_id_fk` (`qid`),
  CONSTRAINT `oj_user_resolve_oj_question_id_fk` FOREIGN KEY (`qid`) REFERENCES `oj_question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `oj_user_resolve_oj_user_id_fk` FOREIGN KEY (`uid`) REFERENCES `oj_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oj_user_resolve`
--

LOCK TABLES `oj_user_resolve` WRITE;
/*!40000 ALTER TABLE `oj_user_resolve` DISABLE KEYS */;
INSERT INTO `oj_user_resolve` VALUES (34,282718464,440300575494639616),(35,293616896,440300575494639616),(36,293616896,440302398993469440);
/*!40000 ALTER TABLE `oj_user_resolve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oj_user_subscribe`
--

DROP TABLE IF EXISTS `oj_user_subscribe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oj_user_subscribe` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uid` bigint DEFAULT NULL,
  `fans_uid` bigint DEFAULT NULL,
  `state` int DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oj_user_subscribe_oj_user_id_fk` (`uid`),
  KEY `oj_user_subscribe_oj_user_id_fk2` (`fans_uid`),
  CONSTRAINT `oj_user_subscribe_oj_user_id_fk` FOREIGN KEY (`uid`) REFERENCES `oj_user` (`id`),
  CONSTRAINT `oj_user_subscribe_oj_user_id_fk2` FOREIGN KEY (`fans_uid`) REFERENCES `oj_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oj_user_subscribe`
--

LOCK TABLES `oj_user_subscribe` WRITE;
/*!40000 ALTER TABLE `oj_user_subscribe` DISABLE KEYS */;
INSERT INTO `oj_user_subscribe` VALUES (1,293616896,282718464,1,'2023-07-20 15:44:49');
/*!40000 ALTER TABLE `oj_user_subscribe` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-02 11:17:16
