/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50559
Source Host           : localhost:3306
Source Database       : student

Target Server Type    : MYSQL
Target Server Version : 50559
File Encoding         : 65001

Date: 2020-06-15 02:56:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cid` int(11) NOT NULL COMMENT '课程号',
  `cname` varchar(30) NOT NULL,
  `t_no` int(11) NOT NULL,
  `credit` int(11) NOT NULL COMMENT '学分',
  `chour` int(11) NOT NULL COMMENT '学时',
  PRIMARY KEY (`cid`),
  KEY `t1` (`t_no`),
  CONSTRAINT `t1` FOREIGN KEY (`t_no`) REFERENCES `teacher` (`t_no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('111', '11', '11', '1', '20');
INSERT INTO `course` VALUES ('112', '22', '11', '1', '20');
INSERT INTO `course` VALUES ('113', '33', '22', '1', '20');

-- ----------------------------
-- Table structure for `score`
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `sid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  `score` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`sid`,`cid`),
  KEY `Cou` (`cid`),
  CONSTRAINT `cou` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stu` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('110', '111', '21.00');
INSERT INTO `score` VALUES ('110', '112', '20.00');
INSERT INTO `score` VALUES ('111', '111', '56.00');
INSERT INTO `score` VALUES ('111', '112', null);
INSERT INTO `score` VALUES ('220', '111', '0.00');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `sid` int(11) NOT NULL COMMENT '学号.如2017301245',
  `sname` varchar(30) NOT NULL,
  `ssex` enum('男','女') NOT NULL COMMENT '性别',
  `spwd` varchar(15) NOT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('110', 'zzc', '男', '123');
INSERT INTO `student` VALUES ('111', '郑智', '男', '111');
INSERT INTO `student` VALUES ('220', 'zc', '男', '110');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `t_no` int(11) NOT NULL,
  `t_name` varchar(10) NOT NULL,
  `t_pwd` varchar(10) NOT NULL,
  PRIMARY KEY (`t_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('11', '1', '1');
INSERT INTO `teacher` VALUES ('22', '2', '2');
INSERT INTO `teacher` VALUES ('33', '齐天', '3');

-- ----------------------------
-- View structure for `avg`
-- ----------------------------
DROP VIEW IF EXISTS `avg`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `avg` AS select `course`.`cid` AS `cid`,`course`.`cname` AS `cname`,`teacher`.`t_no` AS `t_no`,avg(`score`.`score`) AS `avg`,count(0) AS `all` from ((`course` join `score` on((`score`.`cid` = `course`.`cid`))) join `teacher` on((`course`.`t_no` = `teacher`.`t_no`))) group by `course`.`t_no`,`course`.`cid`,`course`.`cname`,`teacher`.`t_no` ;

-- ----------------------------
-- View structure for `grade`
-- ----------------------------
DROP VIEW IF EXISTS `grade`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `grade` AS select `course`.`cid` AS `cid`,`course`.`cname` AS `cname`,`student`.`sid` AS `sid`,`student`.`sname` AS `sname`,`teacher`.`t_no` AS `t_no`,`teacher`.`t_name` AS `t_name`,`score`.`score` AS `score` from (((`course` join `score` on((`score`.`cid` = `course`.`cid`))) join `student` on((`score`.`sid` = `student`.`sid`))) join `teacher` on((`course`.`t_no` = `teacher`.`t_no`))) order by `course`.`cid` ;
