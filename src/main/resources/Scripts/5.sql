use test;
select * from projects where cost in
(select min(cost) from projects);