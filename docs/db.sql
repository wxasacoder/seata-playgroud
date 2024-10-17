-- 模拟下单的相关表
CREATE TABLE `account_tbl` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `user_id` bigint DEFAULT NULL,
                               `money` int DEFAULT '0',
                               `lock_money` int NOT NULL DEFAULT '0',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;


CREATE TABLE `order_tbl` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `user_id` bigint DEFAULT NULL,
                             `commodity_code` varchar(255) DEFAULT NULL,
                             `count` int DEFAULT '0',
                             `money` int DEFAULT '0',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `storage_tbl` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `commodity_code` varchar(255) DEFAULT NULL,
                               `count` int DEFAULT '0',
                               `sold` int NOT NULL DEFAULT '0',
                               `frozen` int NOT NULL DEFAULT '0',
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `commodity_code` (`commodity_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3

-- 注意此处0.3.0+ 增加唯一索引 ux_undo_log
-- 使用AT需要
CREATE TABLE `undo_log` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `branch_id` bigint(20) NOT NULL,
                            `xid` varchar(100) NOT NULL,
                            `context` varchar(128) NOT NULL,
                            `rollback_info` longblob NOT NULL,
                            `log_status` int(11) NOT NULL,
                            `log_created` datetime NOT NULL,
                            `log_modified` datetime NOT NULL,
                            `ext` varchar(100) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 使用tcc需要
CREATE TABLE IF NOT EXISTS `tcc_fence_log`
(
    `xid`           VARCHAR(128)  NOT NULL COMMENT 'global id',
    `branch_id`     BIGINT        NOT NULL COMMENT 'branch id',
    `action_name`   VARCHAR(64)   NOT NULL COMMENT 'action name',
    `status`        TINYINT       NOT NULL COMMENT 'status(tried:1;committed:2;rollbacked:3;suspended:4)',
    `gmt_create`    DATETIME(3)   NOT NULL COMMENT 'create time',
    `gmt_modified`  DATETIME(3)   NOT NULL COMMENT 'update time',
    PRIMARY KEY (`xid`, `branch_id`),
    KEY `idx_gmt_modified` (`gmt_modified`),
    KEY `idx_status` (`status`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;