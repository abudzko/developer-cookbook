CREATE TABLE `spring_session_attributes` (
  `session_id` char(36) NOT NULL,
  `attribute_name` varchar(200) NOT NULL,
  `attribute_bytes` blob DEFAULT NULL,
  PRIMARY KEY (`session_id`,`attribute_name`),
  KEY `SPRING_SESSION_ATTRIBUTES_IX1` (`session_id`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`session_id`) REFERENCES `spring_session` (`session_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

CREATE TABLE `spring_session` (
  `session_id` char(36) NOT NULL,
  `creation_time` bigint(20) NOT NULL,
  `last_access_time` bigint(20) NOT NULL,
  `max_inactive_interval` int(11) NOT NULL,
  `principal_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`session_id`),
  KEY `SPRING_SESSION_IX1` (`last_access_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32;
