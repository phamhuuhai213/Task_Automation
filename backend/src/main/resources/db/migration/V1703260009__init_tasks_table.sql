CREATE TABLE `tasks` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text,
  `status` enum('TODO','IN_PROGRESS','REVIEW','DONE','BLOCKED') DEFAULT 'TODO',
  `priority` enum('HIGH','MEDIUM','LOW') NOT NULL,
  `importance` enum('HARD','NORMAL','EASY') NOT NULL,
  `deadline` datetime NOT NULL,
  `is_notified` tinyint(1) DEFAULT '0',
  `blocked_at` datetime DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tasks_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
