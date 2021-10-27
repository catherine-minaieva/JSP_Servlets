use test;

INSERT INTO `test`.`companies` VALUES (1,'Epam','Kiev'), (2,'Global logic','Kharkiv'), (3, 'SoftServe', 'Lviv'), (4, 'Sigma', 'Kharkiv');
INSERT INTO `test`.`customers` VALUES (1, 'Oftalmica', '3105556987', 'Kharkiv'), (2, 'Nestle', '5556456951', 'Geneva'), (3, 'Porshe', '6761454766', 'Munich'), (4, 'Samsung', '3212213887', 'Suwon'), (5, 'COCA-COLA', '4214516562', 'Atlanta');
INSERT INTO `test`.`developers`VALUES (1,'Olena Karlash',35,'women'),(2,'Oleg Sapev',30,'men'),(3,'Alina Kurieva',24,'women'),(4,'Svetlana Ruh',29,'women'),(5,'Sergey Ilchenko',38,'men'),(6,'Polina Bogodist',20,'women'),(7,'Max Payne',35,'men'),(8,'Olena Semenova',24,'women'),(9,'Raul Velaskes',37,'men'),(10,'Michailo Petrenko',32,'women');
INSERT INTO `test`.`projects` VALUES (1, 'SuperDatabase', 'SQL'), (2, 'New Necker', 'Javascript'), (3, 'Cloud Vision', 'CloudService, C++'), (4, 'MiniFood', 'java'), (5, 'MobileDiagnostic', 'java');
INSERT INTO `test`.`skills`VALUES (1,'Java','Junior'),(2,'Java','Middle'),(3,'Java','Senior'),(4,'C++','Junior'),(5,'C++','Middle'),(6,'C++','Senior'),(7,'C#','Junior'),(8,'C#','Middle'),(9,'C#','Senior'),(10,'JS','Junior'),(11,'JS','Middle'),(12,'JS','Senior');

INSERT INTO `test`.`developers_projects`VALUES (1,1),(2,1),(3,2),(4,2),(5,3),(6,3),(7,4),(8,4),(9,5),(10,5);
INSERT INTO `test`.`companies_projects` VALUES (1,1),(2,2),(3,3),(4,4),(4,5);
INSERT INTO `test`.`customers_projects`VALUES (1,1),(2,4),(3,5),(4,3),(5,2);
INSERT INTO `test`.`developers_skills`VALUES (3,1),(10,2),(5,3),(9,3),(8,4),(4,5),(2,6),(7,8),(6,9),(1,12);





