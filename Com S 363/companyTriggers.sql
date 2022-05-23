-- Author: ComS 363 Teaching staff
/*
  The two triggers enforce total participation on both entity sets of the Works_On relationship set. 
 
  The first trigger inserts a row with a default employee and work hours to Works_ON
  The second trigger inserts a row with a default project and work hours to a new employee to Works_ON.
  We need another trigger to ensure the total participation of Department in the Works_for relationship set.
  Before running this script, run CompanyDDL.sql and CompanyDML.sql first.
*/
use company;
drop trigger if exists assign_employee_to_project;

-- delimiter statement is used to set the default delimiter to //
-- we need to close the delimiter.
-- After a new project is inserted, we need to ensure that each project has an employee works on it.
-- So, we add a default employee to the new project.
-- NEW represents a new row that was just inserted into Projects
-- Event is AFTER INSERT ON
-- ACTION: add a default employee to the new project in Works_on table
-- In this example, we assume that the default_essn exists.
-- Otherwise, you could do a query to select one of the employees 
-- in the employees table 
-- IMPORTANT; tells MySQL that the colon ; is no longer the statement delimiter
delimiter //  
CREATE TRIGGER assign_employee_to_project AFTER INSERT ON Projects
FOR EACH ROW 
BEGIN
	DECLARE default_hours INTEGER;
	DECLARE default_essn CHAR(11);
	
	set default_hours =10;
	set default_essn = '666-66-6600';
	INSERT INTO Works_on 
	VALUES(default_essn, NEW.pno, default_hours);
END;//
-- IMPORTANT: change the delimiter back to ; so MySQL can take the statements in between
-- the delimiters to execute.
delimiter ;

-- after a new employee is insert, the default project id and hours are added into 
-- works_on to ensure total participation of employees in the works_on relationship set.

drop trigger if exists assign_project_to_employee;

delimiter //
CREATE TRIGGER assign_project_to_employee AFTER INSERT ON Employees
FOR EACH ROW 
BEGIN
	DECLARE default_jid INTEGER;
	DECLARE default_hours integer;
	
	set default_jid = 1;
	set default_hours = 20;
	
	INSERT INTO Works_on 
	VALUES(NEW.ssn,default_jid,default_hours);
END;//
delimiter ;

-- other trigger examples not related to the constraints in the ER diagram in Figure 2 of CP-WK3-T-RDB.pdf.
-- since check constraint is ignored in mysql below 8.016, use a trigger to prevent insertion of a negative project number
-- the check happens before the pno of the new row is added to the database.
drop trigger if exists check_pno_before_insert;
-- change the statement delimiter from ; to // so we can use ; to separate statements inside the trigger
delimiter //
CREATE TRIGGER check_pno_before_insert BEFORE INSERT ON Projects 
FOR EACH ROW 
BEGIN 
	IF NEW.pno< 0 THEN 
		SET NEW.pno=0; 
	END IF; 
END;//
delimiter ;
-- the above line sets the delimiter back so that it can execute other SQL statements

-- check that the new value of project number is not zero. If so, show the error message.
drop trigger if exists check_pno_before_update;
delimiter //
CREATE TRIGGER check_pno_before_update BEFORE UPDATE ON Projects 
FOR EACH ROW 
BEGIN 
	IF NEW.pno< 0 THEN 
		-- SIGNAL works for MySQL server 5.5
		 SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'pno cannot be negative'; 
	END IF; 
END;//
delimiter ;