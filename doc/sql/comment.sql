CREATE TABLE `comment` (
  `id` varchar(36) NOT NULL,
  `content` varchar(100) DEFAULT NULL,
  `post_user_name` varchar(20) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `article_id` varchar(36) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;