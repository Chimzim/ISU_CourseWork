-- Author: Chimzim Ogbondah Software Engineering 
use fooddb;

-- Question 1a) this returns the total number of food items in the food table
Select COUNT(fid) as numFoods
From food;

-- Question 1b) this returns all the information about a food and ingredients in acending order based on Food id
Select F.fid, F.fname, I.iid, I.iname, I.caloriepergram, I.category
From food F INNER JOIN Recipe R on F.fid = R.fid INNER JOIN ingredient I on I.iid = R.iid
order by F.fid;

-- Question 1c) this returns all food items that have both chicken and green onions as ingredients
Select F.fid, F.fname
From food F INNER JOIN Recipe R1 on F.fid = R1.fid INNER JOIN Recipe R2 on R2.fid = F.fid
INNER JOIN ingredient I1 on R1.iid = I1.iid INNER JOIN ingredient I2 on R2.iid = I2.iid 
Where I1.iname = "Chicken" and I2.iname = "Green Onion" and 
R1.fid = R2.fid and R1.iid = I1.iid and R2.iid = I2.iid;

-- Question 1d) this adds an new Ingredient "Cheese" into the Ingredients table 
-- then returns all ingredients not used in a Recipe
insert into ingredient  
select max(iid)+1, 'Cheese', 2.0, 'Protein'  
from ingredient; 
 
 Select I.iid, I.iname
 From ingredient I LEFT JOIN Recipe R on I.iid = R.iid
 Where R.iid is null;
 
 -- Question 1e) this returns the food id the name the count of ingredients and the sum of the recipe amount 
 Select F.fid, F.fname, COUNT(I.iid) as numIngredients, SUM(R.amount) as totalAmt
 From food F INNER JOIN Recipe R on F.fid = R.fid INNER JOIN
 ingredient I on R.iid = I.iid
 GROUP BY F.fid
 Order by numIngredients DESC;