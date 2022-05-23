-- Author: Chimzim Ogbondah Software Engineering 
use project;

/* Question 4) this this Sql query holds all the procedures required in Question 4
*/


/* Question 4a) This query returns a list of 'K' users and displays their
 screen name, subCategory and number of followers 
in decseninding order based on the given 'politicalPartyName'
*/
DROP Procedure if exists influentialUsers;
delimiter //
Create Procedure influentialUsers(IN k int, politicalPartyName VARCHAR(80))
begin 
Select u.screen_name, u.subcategory, u.numFollowers
From project.users u 
Where u.subcategory = politicalPartyName
Order By u.numFollowers DESC LIMIT 0,k;
end;//
delimiter ;
-- call influentialUsers(5,"GOP");

/* Question 4b) this query returns a list of 'K' tweets and displays their 
tweet text, retweet count, screen name and category in descending order based on the number of retweets
*/
DROP Procedure if exists influentialTweet;
delimiter //
Create Procedure influentialTweet(IN k int, tweetMonth int, tweetYear int)
begin 
Select t.texts, t.retweetCt, t.user_screen_name, u.category
From project.users u INNER JOIN project.tweets t on t.user_screen_name = u.screen_name
Where t.post_month = tweetMonth and t.post_year = tweetYear
Order By t.retweetCt DESC LIMIT 0,k;
end;//
delimiter ;
-- call influentialTweet(5,2,2016);


/* Question 4c) this query returns a list of 'K' mentioned users and displays their 
mention name, users screen name, users state, and a list of screen names who mentioned the user
in decsending order based on political party, tweet month and tweet year
*/
DROP Procedure if exists mostMentioned;
delimiter //
Create Procedure mostMentioned(IN k int, politicalPartyName VARCHAR(80), tweetMonth int, tweetYear int)
begin 
Select m.screen_name as mentionedUser, group_concat(distinct u.screen_name, u.state, t.user_screen_name) as postingUser
From project.mentions m INNER JOIN project.tweets t on m.tid = t.tid 
INNER JOIN project.users u on t.user_screen_name = u.screen_name
Where u.subcategory = politicalPartyName and t.post_month = tweetMonth and t.post_year = tweetYear
group by m.screen_name
Order By COUNT(m.screen_name) DESC LIMIT 0,k;
end;//
delimiter ;
-- call mostMentioned(5, "Democrat",1,2016);