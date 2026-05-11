-- Create Subject Table (for course categories)
CREATE TABLE IF NOT EXISTS `subject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `parent_id` bigint(20) DEFAULT 0,
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create Comment Table (for course reviews)
CREATE TABLE IF NOT EXISTS `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `content` text NOT NULL,
  `rating` int(11) DEFAULT 5,
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;