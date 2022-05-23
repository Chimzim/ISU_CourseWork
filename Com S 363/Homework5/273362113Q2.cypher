// 2a.) Return 5 tweets showing the Tweet text, screen name of tweet, category of the user and retweet count, ordering them in descending order 
MATCH (t:Tweet)<-[:POSTED]-(u:User) 
Return t.textbody as tweetText, u.screen_name as screenname, u.sub_category as party, t.retweet_count as retweetCount order by t.retweet_count desc limit 5;


// 2b.)List five unique hashtags posted by the users who lived in one of these states, Ohio and 
// Alaska. Show the names of the hashtags in ascending order. 
MATCH (u:User)-[:POSTED]->(t:Tweet)<-[:TAGGED]-(h:Hashtag) 
where u.location = "Ohio" or u.location = "Alaska" 
return distinct h.name as hashtagName order by h.name limit 5;


// 2c.) Find five users in the GOP party with the most number of followers. Show the user’s 
// screen name, party, and the number of followers of these users in descending order of the number 
// of followers.  
MATCH (u:User) where u.sub_category = "GOP" 
return u.screen_name as screenname, u.sub_category as party, u.followers as numFollowers order by u.followers desc limit 5;

// 2.d)
MATCH (postu:User)-[:POSTED]->(t:Tweet)-[:MENTIONED]->(mentionu:User) 
where postu.sub_category = "GOP"
with mentionu,count(mentionu) as number, collect(distinct postu.screen_name) as listMentioningUsers
return mentionu.screen_name as mentionUser, listMentioningUsers order by number desc limit 5;


//2.e)Find at most five users who used a given hashtag “DemDebate” in their tweets the most. These 
// users must live in North Carolina. Show the user’s screen name and the number of tweets using the hashtag in 
// descending order of the number of tweets. 
MATCH (u:User)-[:POSTED]->(t:Tweet)<-[:TAGGED]-(h:Hashtag) 
with u, t 
where u.location = "North Carolina" and h.name="DEMDEBATE" 
return u.screen_name, count(t.id) as countTweet order by count(t.id) desc limit 5;

// 2.f)List four hashtag names in descending order of the number of states the hashtag was used in 
// tweets posted by the users who lived in those states. List the hashtag name, the total number of states, and the 
// list of the distinct state names. Do not include the state whose name is na or empty
MATCH (u:User)-[:POSTED]->(t:Tweet)<-[:TAGGED]-(h:Hashtag) 
with u, t, h  
where not (u.location = "na" or u.location = "") 
return h.name as hashtag, count(distinct u.location) as numStates, collect(distinct u.location) as stateList order by count(distinct u.location) desc limit 4;

// 2.g) Show the names of the states in descending order of the number of users living in that state. Do 
// not list the state that the name is empty or “na”. 
MATCH (u:User) 
where not (u.location = "na" or  u.location =  "") 
return u.location as state, count(u.location) as numUsers 
order by count(u.location) desc; 