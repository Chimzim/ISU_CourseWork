-- Author: Chimzim Ogbondah Software Engineering 

drop database if exists ogbondah;
create database if not exists ogbondah;

use ogbondah;

-- Creation of Employee Entity
DROP TABLE IF EXISTS `Employees`;
CREATE TABLE `Employees` (
 `snn` int(11) Not NULL UNIQUE,
 `union_mem_no` int(11),
 `phone_num` int(10),
 `name` VARCHAR(50),
 `address` VARCHAR(50),
 `salary` int(11),
 Primary Key(`snn`),
 UNIQUE KEY(`union_mem_no`)
);
-- Creation of Model Entity 
DROP TABLE IF EXISTS `Model`;
CREATE TABLE `Model` (
`model_num` int(11),
`capacity` int(11),
`weight` int(11),
Primary KEY(`model_num`)
);
-- ISA relationship on employee
-- Creating Traffic Control (Employee) Entity
DROP TABLE IF EXISTS `Traffic_Control`;
CREATE TABLE `Traffic_Control` (
`tc_snn` int(11),
`exam_date` VARCHAR(50),
CONSTRAINT `tc_snn` FOREIGN KEY (`tc_snn`) REFERENCES `Employees` (`snn`) ON DELETE CASCADE ON UPDATE CASCADE
);
-- Creating Manager (Employee) Entity 
DROP TABLE IF EXISTS `Manager`;
CREATE TABLE `Manager` (
`m_snn` int(11),
`department` VARCHAR(50),
CONSTRAINT `m_snn` FOREIGN KEY (`m_snn`) REFERENCES `Employees` (`snn`) ON DELETE CASCADE
);
-- Creating Technician (Employee) Entity 
DROP TABLE IF EXISTS `Technician`;
CREATE TABLE `Technician` (
`t_snn` int(11),
`security_clerance` VARCHAR(50),
`US_passport_num` int(11),
`e_model_num` int(11),
Constraint `e_model_num` FOREIGN KEY(`e_model_num`) REFERENCES `Model`(`model_num`) ON DELETE CASCADE,
Constraint `t_snn` FOREIGN KEY (`t_snn`) REFERENCES `Employees` (`snn`) ON DELETE CASCADE
);
-- Creating Dependent Entity 
DROP TABLE IF EXISTS `Dependent`;
CREATE TABLE `Dependent` (
`DOB` VARCHAR(50),
`Relationship` VARCHAR(50),
`name` VARCHAR(50) NOT NULL,
`p_snn` int(11) NOT NULL, 
UNIQUE KEY(`name`),
Primary KEY(`p_snn`, `name`),
CONSTRAINT `p_snn` FOREIGN KEY(`p_snn`) REFERENCES `Employees`(`snn`) ON DELETE CASCADE
);
-- Creation of Test Entity 
DROP TABLE IF EXISTS `Test`;
CREATE TABLE `Test` (
`FAA_num` int(11) Not NULL UNIQUE,
`score` int(11),
`name` VARCHAR(50),
Primary Key (`FAA_num`)
);
-- Creation of Plane Entity 
DROP TABLE IF EXISTS `Plane`;
CREATE TABLE `Plane` (
`reg_num` int(11),
`name` VARCHAR(50),
`p_model_num` int(11) NOT NULL,
Primary Key (`reg_num`),
CONSTRAINT FOREIGN KEY(`p_model_num`) REFERENCES `Model`(`model_num`)
);
-- Creation of the Test_Info Relationship
DROP TABLE IF EXISTS `Test_Info`;
CREATE TABLE `Test_Info` (
`hours` int(11),
`Date` VARCHAR(50),
`score` int(11),
`test_tech_snn` int(11),
`test_reg_num` int(11), 
`test_faa_num` int(11),
Primary Key(`test_reg_num`, `test_faa_num`, `test_tech_snn`),
CONSTRAINT `test_tech_snn` FOREIGN KEY(`test_tech_snn`) REFERENCES `Technician`(`t_snn`),
CONSTRAINT `test_reg_num` FOREIGN KEY(`test_reg_num`) REFERENCES `Plane`(`reg_num`),
CONSTRAINT `test_faa_num` FOREIGN KEY(`test_faa_num`) REFERENCES `Test`(`FAA_num`)
);
-- Creation of Monitor Realtionship
DROP TABLE IF EXISTS `Monitor`;
CREATE TABLE `Monitor`(
`most_recent_date` VARCHAR(50),
`m_faa_num` int(11),
`m_reg_num` int(11),
`m_t_snn` int(11),
Primary Key(`m_faa_num`, `m_reg_num`, `m_t_snn`),
FOREIGN KEY(`m_faa_num`) REFERENCES `Test`(`FAA_num`),
FOREIGN KEY(`m_reg_num`) REFERENCES `Plane`(`reg_num`),
FOREIGN KEY(`m_t_snn`) REFERENCES `Technician`(`t_snn`)
);

