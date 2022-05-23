-- Author: Chimzim Ogbondah Software Engineering 
use fooddb;

/* Question 2) this procedure returns food id, food name and totalAmount from the food table 
 in descending order where the number of meat items in the recipe is 'numMeatIngredients' and 
 the total grams in the recipe is greater than 'totalgrams'	
*/

DROP Procedure if exists findFoodWithMeat;
delimiter //
Create Procedure findFoodWithMeat(IN numMeatIngredients int, totalgrams float)
begin 
Select F.fid, F.fname, SUM(R.amount) as totalMeatAmt
From fooddb.food F INNER JOIN fooddb.recipe R on F.fid = R.fid 
INNER JOIN fooddb.ingredient I on R.iid = I.iid
Where I.category = "Meat" 
Group By F.fid 
Having totalMeatAmt > totalgrams  and COUNT(I.category = "Meat") = numMeatIngredients
Order By F.fid DESC;
end;//
delimiter ;
-- call findFoodWithMeat(2,5);