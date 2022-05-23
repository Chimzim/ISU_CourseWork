use threetables;

-- a)	Show the number of employees where the returned column name is numEmployees.
Select COUNT( distinct emp.eid) as numEmployees
from emp;


-- b. List department id, department name of each department that has a manager.
-- Do not list the same department id more than one time.
select dname, did
from dept
where did is not null;


-- c. List employee name and salary for each employee who earns more than 2000000 
--   in ascending order of their salary. Do not list the same employee twice.
-- Note that enames are not unique. 
select ename, salary
from emp
where salary > 2000000
Order by salary;


-- d. Print all the department information (did, dname, budget, managerid) 
--    about the Accounting department and Production department.
--    Do not list the same department more than one time.
-- Per client's requirement, dname is unique for each department (identified by did)
select distinct did, dname, budget, managerid
from dept
where dname = "Accounting" or dname = "Production";

/*
-- e. Print the name and salary of each employee who works in the Accounting department. 
      Do not list the same employee more than one time.

*/
select e.ename, e.salary
from emp e, works w, dept d
where e.eid = w.eid and w.did = d.did and d.dname = "Accounting";

select e.ename, e.salary
from emp e INNER JOIN works w on 
e.eid = w.eid INNER JOIN dept d on w.did = d.did 
where d.dname = "Accounting";


-- f. Print the id and name of each employee who works in both the Production department and 
-- the Maintenance department. Do not list the same employee id more than one time. 
-- distinct is needed in the first query
Select distinct e.eid, e.ename
from emp e INNER JOIN works w on e.eid = w.eid INNER JOIN dept d on w.did = d.did
where d.dname = "Production" and d.dname="Maintenance";

/*
g) Print did and the sum of the salaries of all the employees working for the department 
   that pays over 6000000 in total salary. 
   Order the results in descending order of the sums.
*/
select d.did, sum(e.salary) as sal
from emp e INNER JOIN works w on e.eid = w.eid INNER JOIN dept d on w.did = d.did
group by d.did
having sal > 6000000
order by sal DESC ;
 

   
-- h. Insert a new employee ‘Pak’ into Emp using the following statement.
/*
insert into emp (eid, ename) 
select max(eid)+1, 'Pak'
from emp;
*/

/*
Write SQL queries to find eids of all employees who have not worked in 
any department in three different ways, using the IN, EXISTS, and LEFT JOIN operators. 
These queries give the same result like below.
*/

-- LEFT JOIN

