-- test.tree definition

CREATE TABLE `tree` (
  `id` varchar(20) NOT NULL,
  `parent_id` varchar(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO tree values ('0','-1','一级目录-1');
INSERT INTO tree values ('1','-1','一级目录-2');
INSERT INTO tree values ('2','0','二级目录-1');
INSERT INTO tree values ('3','0','二级目录-1');
INSERT INTO tree values ('4','1','二级目录-2');
INSERT INTO tree values ('5','1','二级目录-2');
INSERT INTO tree values ('6','1','二级目录-2');