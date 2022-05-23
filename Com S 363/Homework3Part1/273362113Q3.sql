-- Author: Chimzim Ogbondah Software Engineering 
use fooddb;

Drop Trigger if exists check_amount;
delimiter //  
Create Trigger check_amount After insert ON fooddb.Recipe 
FOR EACH ROW
Begin 
	IF New.amount < 0 THEN
		Signal SQLSTATE '45000' SET message_text = "amount must be positive";
	end IF;
END;//
delimiter ;
-- insert into recipe values (-1, 26, 32);