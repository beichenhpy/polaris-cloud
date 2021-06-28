CREATE TABLE `article` (
  `id` varchar(20) NOT NULL,
  `content` text,
  `image` varchar(200) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;