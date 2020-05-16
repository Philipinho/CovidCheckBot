CREATE TABLE IF NOT EXISTS `reports` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `username` varchar(20) NULL,
  `name` varchar(255) NULL,
  `message` varchar(5000) DEFAULT NULL,
  `platform` varchar(50) NOT NULL,
  `time_saved` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `username` varchar(20) NULL,
  `name` varchar(255) NULL,
  `platform` varchar(50) NOT NULL,
  `state_reply` varchar(255) NOT NULL,
  `time_saved` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;