-- test.tree definition

CREATE TABLE `tree` (
  `id` int NOT NULL,
  `parent_id` int DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO tree values (1,0,'一级目录-1');
INSERT INTO tree values (2,1,'二级目录-0');
INSERT INTO tree values (3,1,'二级目录-1');
INSERT INTO tree values (4,1,'二级目录-1');
INSERT INTO tree values (5,2,'二级目录-2');
INSERT INTO tree values (6,2,'二级目录-2');
INSERT INTO tree values (7,2,'二级目录-2');