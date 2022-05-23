-- Author: Chimzim Ogbondah Software Engineering 

use ogbondah;

-- Inserting rows into the ogbondah database
SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE Traffic_Control;
TRUNCATE TABLE Monitor;
TRUNCATE TABLE Manager;
TRUNCATE TABLE Test_Info;
TRUNCATE TABLE Test;
TRUNCATE TABLE Dependent;
TRUNCATE TABLE Technician;
TRUNCATE TABLE Model;
TRUNCATE TABLE Plane;
TRUNCATE TABLE Employees;
SET FOREIGN_KEY_CHECKS=1;
-- insert 4 Model numbers 1 2 3 4 into models table
insert into `Model` values(747, 500, 20000),(777,550,20000),(252,550,20000),(948,500,20000);
-- insert Dan, Daniel, Mason, Rhyne as employees
insert into `Employees` values (1111, 12, 123456789, "Rhyne", "123 fake dr", 190000);
insert into `Employees` values (1112, 13, 123456788, "Dan", "1234 fake rd", 117000);
insert into `Employees` values (1113, 14, 123456787, "Daniel", "12 fake ave", 115000);
insert into `Employees` values (1114, 15, 123456786, "Mason", "12345 fake ct", 1130000);
insert into `Employees` values (1115, 16, 123456785, "Madison", "12345 testStreet dr", 119);
insert into `Employees` values (1116, 17, 123456784, "Victoria", "12345 testStreet rd", 118);
insert into `Employees` values (1117, 18, 123456783, "Carter", "12345 testStreet ave", 117);
insert into `Employees` values (1119, 19, 123456782, "Austin", "12345 testStreet ct", 110);
insert into `Employees` values (1118, 20, 123456781, "Ronan", "123 notReal dr", 1700000);
insert into `Employees` values (1101, 21, 123456780, "Doug", "1234 notReal rd", 1200000);
insert into `Employees` values (1102, 22, 123456790, "Yusuf", "12 notReal ave", 1300000);
insert into `Employees` values (1103, 23, 123456780, "Chris", "12345 notReal ct", 1100000);
	-- insert into `Technician` values
	insert into `Technician` values(1111, "Max", 987654321, 747);
    insert into `Technician` values(1115, "Low", 987654322, 777);
    insert into `Technician` values(1116, "Max", 987654323, 252);
    insert into `Technician` values(1117, "Low", 987654324, 948);
	-- insert into `Traffic Control` values
	insert into `Traffic_Control` values (1112, "02-12-1999");
	insert into `Traffic_Control` values (1113, "12-25-2000");
    insert into `Traffic_Control` values (1118, "02-17-1999");
    insert into `Traffic_Control` values (1119, "11-25-2000");
    -- insert into 'Manager' values
    insert into `Manager` values (1114, "IFF");
    insert into `Manager` values (1101, "Wings");
    insert into `Manager` values (1102, "Take off");
    insert into `Manager` values (1103, "Order fulfillment");
-- inset theo, adomale, joe and jack as their dependents
insert into `Dependent` values ("01-01-2019", "Child", "Theo", 1111);
insert into `Dependent` values ("02-01-2019", "Child", "Adomale", 1112);
insert into `Dependent` values ("03-01-2019", "Child", "Joe", 1113);
insert into `Dependent` values ("04-01-2019", "Child", "Jack", 1114);
-- insert boeing 747, airbus 777, airbus 252, boeing 948 into plane
insert into `Plane` values (1, "boeing 747", 747);
insert into `Plane` values (2, "airbus 777", 777);
insert into `Plane` values (3, "airbus 252", 252);
insert into `Plane` values (4, "boeing 948", 948);
-- insert 4 tests
insert into `Test` values (11, 94, "Wheel Test");
insert into `Test` values (21, 99, "Wing Test");
insert into `Test` values (31, 96, "Safety Test");
insert into `Test` values (41, 82, "Engine Test");
-- insert 4 test info values
insert into `Test_Info` values (20, "01-01-2022", 94, 1111, 1, 11);
insert into `Test_Info` values (23, "02-01-2022", 99, 1115, 2, 21);
insert into `Test_Info` values (16, "02-11-2022", 96, 1116, 3, 31);
insert into `Test_Info` values (8, "02-16-2022", 82, 1117, 4, 41);
-- insert 4 monitor values
insert into `Monitor` values ("02-18-2022", 11, 1, 1111);
insert into `Monitor` values ("02-18-2022", 21, 2, 1115);
insert into `Monitor` values ("02-18-2022", 31, 3, 1116);
insert into `Monitor` values ("02-18-2022", 41, 4, 1117);