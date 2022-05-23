use practiceexam2_Q1;
-- 1.a) find the number of distince venues
	select count(*) as num_venues
	from venues;
	
-- 1.b) list all the events that were held on ISU campus and show the vid and sched_date for these events 
	select vid, sched_date
	from events
	where location = "ISU Campus";
	
-- 1.c) Show the number of disinct events for each location 
	select location, count(*) as num_events
	from events
	group by location;

-- 1.d) find all the events that Dean or John has participated in and list all the attributes of the events relation
	select e.vid, e.sched_date, e.location
	from persons p inner join participates pa on p.pid = pa.pid inner join events e on pa.vid = e.vid and pa.sched_date = e.sched_date
	where p.pname = "John" or p.pname = "Dean";
	
-- 1.e) find all events that all three people john, mary and jane particpated in list all event attributes
	select e.vid, e.sched_date, e.location
	from events e, persons p1, persons p2, persons p3, participates ps1, participates ps2, participates ps3
	where p1.pname = "Mary" and p1.pid = ps1.pid and ps1.vid = e.vid and ps1.sched_date = e.sched_date and 
	p2.pname = "John" and p2.pid = ps2.pid and ps2.vid = e.vid and ps2.sched_date = e.sched_date and 
	p3.pname = "Jane" and p3.pid = ps3.pid and ps3.vid and e.vid and ps3.sched_date = e.sched_date;
    
    select e.vid, e.sched_date, e.location 
    from events e inner join participates ps1 on e.vid = ps1.vid and e.sched_date = ps1.sched_date inner join 
    participates ps2 on e.vid = ps2.vid and e.sched_date = ps2.sched_date inner join participates ps3 on e.vid = ps3.vid and 
    e.sched_date = ps3.sched_date inner join persons p1 on p1.pid = ps1.pid inner join persons p2 on p2.pid = ps2.pid 
    inner join persons p3 on p3.pid = ps3.pid
    where p1.pname = "John" and p2.pname = "Mary" and p3.pname = "Jane";
	
-- 1.f) find a person(s) who has not participated in any events. Order the results in descending order of pid
	select p.pname
	from person p left join participates ps on p.pid = ps.pid
	where ps.pid is null
	order by p.pid desc;
	
-- 1.g)  Find the pid and pname of the person who attended at least two different venues.
	select p.pid, p.pname 
	from persons p, participates ps
	where p.pid = ps.pid
	group by p.pid, p.pname
	having count(distinct ps.vid) >= 2;
    
    select p.pid, p.pname
    from persons p inner join participates ps on p.pid = ps.pid
    group by p.pid, p.pname 
    having count(distinct ps.vid) >= 2;
	
-- 1.h) Write a query with a sub-query in the where clause using the exist operator to find the venue that has no events associated with the venue. Show the venue_id and name of the venue.
	select v.venue_id, v.name
	from venues v
	where not exists (
		select *
		from events e
		where v.venue_id = e.vid
	);
	
-- 1.i)  Write a query with a sub-query in the where clause using the in operator to find the venue that has had events located in “Houston, TX”. Show the venue_id and name of the venue
	select v.venue_id, v.name
	from venue v
	where v.venue_id in (
		select e.vid
		from events e 
		where e.location = "Huston, TX"
	);

-- 1.j) Rewrite the query below using join or inner join or a where clause with join conditions without using any of the sub-queries, exists, or in operators.
	select p.pid, p.pname
	from persons p inner join participates ps on p.pid = ps.pid
	where ps.vid = 1;
    
    
    use suppliersparts;
    
    -- return the number of parts in the Parts table
    select count(pid) as numParts
    from parts;
    
    -- return some catalog information 
    select s.sid, s.sname, p.pid, p.pname, c.cost
    from catalog c inner join suppliers s on c.sid = s.sid inner join parts p on c.pid = p.pid
    order by s.sname limit 0, 5;
    
    -- return more catalog stuff
    select distinct s.sid, s.sname, s.address
    from suppliers s inner join catalog c on c.sid = s.sid
    where c.sid is not null;
    
    -- return some more catalog stuff
    select distinct p.pid, p.pname 
    from parts p inner join catalog c on p.pid = c.pid
    where c.pid is not null and p.color = "black";
    
    -- return more catalog stuff
    select distinct s.sid, s.sname
    from suppliers s inner join catalog c1 on s.sid = c1.sid inner join parts p1 on p1.pid = c1.pid 
    inner join catalog c2 on c2.sid = s.sid inner join parts p2 on p2.pid = c2.pid
    where p1.color = "green" and p2.color = "red";
    