-- Author: Tavanapong and teaching staff of ComS 363

create database if not exists threetables;

use threetables;

-- Table structure for three tables: `emp`, dept, and works
-- Disabling checkking of foreign key constraint; not a good practice
-- SET FOREIGN_KEY_CHECKS=0;

-- Instead, create tables in the correct order.
-- Referenced tables need to be created before the table that has foriegn keys referencing them.

DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `eid` int(11),
  `ename` varchar(50) NOT NULL,
  `salary` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`eid`)
);

--
-- Enter data for table `emp`
--

INSERT INTO `emp` VALUES (100,'John',3000000),(101,'John',2500000),(102,'Dean',1200000),(103,'Steve',2000000),(104,'Dwayne',5000000),(105,'Paul',4000000),(106,'Mark',3500000),(107,'Jacob',3500000),(108,'Adam',2500000),(109,'Jason',2000000),(110,'Chris',2750000),(111,'Kevin',900000),(112,'Antonio',950000),(113,'Alexander',800000),(114,'Brie',500000),(115,'Lita',300000),(116,'Nikki',600000),(117,'Mickey',400000),(118,'Beth',400000),(119,'Kelly',300000);

--
-- Table structure for table `dept`
--

DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `did` int(11),
  `dname` varchar(40) NOT NULL,
  `budget` decimal(10,0) DEFAULT 100000,
  `managerid` int DEFAULT NULL,
  PRIMARY KEY (`did`),
  CONSTRAINT `managerid` FOREIGN KEY (`managerid`) REFERENCES `emp` (`eid`) ON DELETE SET NULL
);

--
-- Insert data for table `dept`
--

INSERT INTO `dept` VALUES (1,'Information Technology',1500000,NULL),(2,'Production',2000000,105),(3,'Administration',2000000,105),(4,'Human Resource',800000,NULL),(5,'Accounting',750000,118),(6,'Maintenance',3000000,105);


--
-- Table structure for table `works`
--

DROP TABLE IF EXISTS `works`;
CREATE TABLE `works` (
  `eid` int(11),
  `did` int(11),
  `pct_time` int(11) DEFAULT 10,
  PRIMARY KEY (`eid`,`did`),
  CONSTRAINT `eid` FOREIGN KEY (`eid`) REFERENCES `emp` (`eid`),
  CONSTRAINT `did` FOREIGN KEY (`did`) REFERENCES `dept` (`did`)
);

--
-- Insert data for table `works`
--

INSERT INTO `works` VALUES (100,4,10),(100,5,40),(100,6,15),(101,1,20),(101,5,20),(102,1,20),(102,5,35),(103,1,30),(103,2,25),(103,6,15),(104,1,10),(104,2,35),(105,2,25),(105,3,15),(105,6,10),(106,1,40),(106,6,20),(107,2,25),(107,6,30),(108,2,25),(108,6,10),(109,2,15),(110,3,15),(111,3,30),(112,3,30),(113,3,10),(114,4,30),(115,4,30),(116,4,20),(117,4,40),(118,5,10),(119,5,30);

