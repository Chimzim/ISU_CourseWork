drop procedure if exists numParts;
-- change the default statement delimiter from ; to //
delimiter //
use suppliersparts;
create procedure numParts()
begin
	select count(*)
	from parts;
    -- you cannot do use dbname in a stored procedure
    -- there can be multiple SQL statements in a stored procedure;
    -- you can declare variables and set the values.
    -- you can put create table, drop table, insert, delete, truncate
    -- you can call another stored procedure
    -- you can set a system variable like below that allows maximum recursion to be 255
    -- SET max_sp_recursion_depth=255;
    -- you can do 
    -- if then 
    -- else
    -- end if
    -- you can do while loop
end;//
-- change the default delimiter back
delimiter ;
call numParts();

