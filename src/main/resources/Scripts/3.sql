use test;
select sum(d.salary)
from developers d, skills s, developers_skills ds
where s.language='Java' and d.id = ds.developer_id and s.id =ds.skill_id;