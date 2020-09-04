CREATE TABLE `t_demo` (
  `id` bigint NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `pwd` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='示例表'