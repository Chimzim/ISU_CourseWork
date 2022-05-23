use suppliersparts;
CREATE PROCEDURE ShowNumReservations()
SELECT S.sid, S.sname, COUNT(*)
FROM Sailors S, Reserves R
WHERE S.sid = R.sid
GROUP BY S.sid, S.sname;

-- this procedure returns the total number of parts in the parts table
DROP procedure if exists numParts;
CREATE PROCEDURE numParts()
Select COUNT(p.pid) as numParts
From Parts p;
call numParts();

-- this procedure returns all the suppliers who don't supply any parts
DROP procedure if exists suppliersWithoutParts;
Create procedure suppliersWithoutParts()
select S.sid, S.sname
From suppliers S left join Catalog C on c.sid = s.sid
where c.pid is null;
call suppliersWithoutParts();

-- this procedure returns all the suppliers who supply red and green parts
DROP procedure if exists redGreenSuppliers;
Create procedure redGreenSuppliers()
select distinct S.sid, S.sname
From suppliers S inner join Catalog C on s.sid = c.sid inner join Parts p on p.pid = c.pid
where p.color = "green" or p.color = "red";
call redGreenSuppliers();

-- this procedure returns suppliers who supply the given color 
Drop procedure if exists whoSupplyColor;
