CREATE TABLE `user_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `user_name` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '姓名',
  `age` int(10) unsigned NOT NULL COMMENT '年龄',
  `balance` decimal(12,2) NOT NULL COMMENT '账户余额',
  `is_vip` tinyint(1) unsigned NOT NULL COMMENT '是否会员：1是 0否',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_name` (`user_name`) USING BTREE,
  KEY `idx_age` (`age`) USING BTREE,
  KEY `idx_vip` (`is_vip`) USING BTREE,
  KEY `idx_age_vip` (`age`,`is_vip`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COMMENT='用户信息表'