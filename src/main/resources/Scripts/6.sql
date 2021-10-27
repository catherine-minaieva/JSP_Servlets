use test;
SELECT p.name, avg(d.salary)
FROM developers d, projects p, developers_projects dp
where d.id=dp.developer_id and p.id=dp.project_id and p.id in
(SELECT id FROM projects where cost in
(select min(cost) from projects));
