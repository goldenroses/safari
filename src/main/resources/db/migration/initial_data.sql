CREATE TABLE `place` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cardImage` varchar(255) DEFAULT NULL,
  `content` text,
  `description` text,
  `imageUrl` varchar(255) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `categoryId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8p9b4th8ov6gkjn3bxnvf6hyf` (`categoryId`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `review` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` text DEFAULT NULL,
  `placeId` int(11) DEFAULT NULL,
  `rating` varchar(200) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `profileImageUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci