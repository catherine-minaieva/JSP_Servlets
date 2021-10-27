DROP DATABASE IF EXISTS test;

CREATE DATABASE IF NOT EXISTS test;

use test;

CREATE TABLE `companies` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `head_office` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `customers` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `tax_code` varchar(45) NOT NULL,
  `head_office` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `developers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `gender` enum('men','women') NOT NULL,
   PRIMARY KEY (`id`)
  );

CREATE TABLE `projects` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `base_technology` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `skills` (
  `id` int NOT NULL AUTO_INCREMENT,
  `language` enum('Java','C++','C#','JS') NOT NULL,
  `level` enum('Junior','Middle','Senior') NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `developers_skills` (
  `developer_id` int NOT NULL,
  `skill_id` int NOT NULL,
  PRIMARY KEY (`developer_id`,`skill_id`),
  FOREIGN KEY (`developer_id`) REFERENCES `developers` (`id`),
  FOREIGN KEY (`skill_id`) REFERENCES `skills` (`id`));

CREATE TABLE `developers_projects` (
  `developer_id` int NOT NULL,
  `project_id` int NOT NULL,
  PRIMARY KEY (`developer_id`,`project_id`),
  FOREIGN KEY (`developer_id`) REFERENCES `developers` (`id`),
    FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`));

CREATE TABLE `companies_projects` (
  `company_id` int NOT NULL,
  `project_id` int NOT NULL,
  PRIMARY KEY (`company_id`,`project_id`),
  KEY `project_id` (`project_id`),
  FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`),
  FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`));

CREATE TABLE `customers_projects` (
  `customer_id` int NOT NULL,
  `project_id` int NOT NULL,
  PRIMARY KEY (`customer_id`,`project_id`),
  KEY `project_id` (`project_id`),
  FOREIGN KEY (`customer_id`) REFERENCES `customers`(`id`),
  FOREIGN KEY (`project_id`) REFERENCES `projects`(`id`));