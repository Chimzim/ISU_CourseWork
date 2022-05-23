DROP Procedure if exists project.findUserPostingHashtag;
delimiter //
Create Procedure project.findUserPostingHashtag(IN hashtag VARCHAR(80), tweetMonth int,tweetYear int, tweetState VARCHAR(80))
begin 
Select count(h.tid) as tweet_count, t.user_screen_name, u.category
From project.users u INNER JOIN project.tweets t on t.user_screen_name = u.screen_name inner join 
project.hashtags h on h.tid = t.tid
Where t.post_month = tweetMonth and t.post_year = tweetYear and u.state = tweetState and h.name = hashtag
Group by t.user_screen_name
Order By count(h.tid) DESC LIMIT 0,5;
end;//
delimiter ;

-- call findUserPostingHashtag("GOPDebate", 2, 2016, "North Carolina");