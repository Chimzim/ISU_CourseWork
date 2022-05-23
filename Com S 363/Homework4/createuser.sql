drop user if exists 'coms363'@'localhost';

-- create a user named coms363 accessible only within this host.
-- the password expired in 120 days

CREATE USER 'coms363'@'localhost' IDENTIFIED BY '363S2022' PASSWORD EXPIRE INTERVAL 120 DAY;

-- Give this user all privileges to the project database. The privileges are 
-- select, insert, update, delete rows, create tables, 
-- alter tables, references, and create indexes as well as create temporary tables and lock tables
GRANT ALL PRIVILEGES ON project.* TO 'coms363'@'localhost';

/*
example only if wanting to create a user that is accessible remotely from other PCs.
drop user if exists 'coms363'@'%';

CREATE USER 'coms363'@'%' IDENTIFIED BY '363S2022' PASSWORD EXPIRE INTERVAL 120 DAY;

-- Give this user all privileges to the project database. The privileges are 
-- select, insert, update, delete rows, create tables, 
-- alter tables, references, and create indexes as well as create temporary tables and lock tables
GRANT ALL PRIVILEGES ON project.* TO 'coms363'@'%';
*/