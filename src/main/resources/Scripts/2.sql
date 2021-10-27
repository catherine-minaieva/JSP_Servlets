use test;
select p.name, sum(d.salary) as total_cost
FROM projects p
INNER JOIN developers_projects pd ON p.id = pd.project_id
INNER JOIN developers d ON d.id=pd.developer_id
GROUP BY p.name
ORDER BY total_cost DESC
LIMIT 1;