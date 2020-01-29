CREATE SCHEMA `test` DEFAULT CHARACTER SET utf8;

CREATE TABLE `items`
(
    `item_id` int            NOT NULL AUTO_INCREMENT,
    `name`    varchar(255)   NOT NULL,
    `price`   decimal(10, 2) NOT NULL,
    PRIMARY KEY (`item_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 19
  DEFAULT CHARSET = utf8;

CREATE TABLE `users`
(
    `id`       int          NOT NULL AUTO_INCREMENT,
    `name`     varchar(255)  DEFAULT NULL,
    `surname`  varchar(255)  DEFAULT NULL,
    `login`    varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    `token`    varchar(255)  DEFAULT NULL,
    `salt`     varbinary(16) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 41
  DEFAULT CHARSET = utf8;

CREATE TABLE `roles`
(
    `roles_id` int          NOT NULL AUTO_INCREMENT,
    `role`     varchar(255) NOT NULL,
    PRIMARY KEY (`roles_id`),
    UNIQUE KEY `role_UNIQUE` (`role`),
    UNIQUE KEY `roles_id_UNIQUE` (`roles_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

CREATE TABLE `roles_users`
(
    `roles_users_id` int NOT NULL AUTO_INCREMENT,
    `role_id`        int DEFAULT NULL,
    `user_id`        int DEFAULT NULL,
    PRIMARY KEY (`roles_users_id`),
    UNIQUE KEY `roles_users_id_UNIQUE` (`roles_users_id`),
    KEY `roles_users_user_id_idx` (`user_id`),
    KEY `roles_users_role_id_idx` (`role_id`),
    CONSTRAINT `roles_users_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`roles_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `roles_users_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 20
  DEFAULT CHARSET = utf8;

CREATE TABLE `orders`
(
    `order_id` int NOT NULL AUTO_INCREMENT,
    `user_id`  int NOT NULL,
    PRIMARY KEY (`order_id`),
    KEY `orders_users_idx` (`user_id`),
    CONSTRAINT `orders_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 30
  DEFAULT CHARSET = utf8;

CREATE TABLE `orders_items`
(
    `orders_items_id` int NOT NULL AUTO_INCREMENT,
    `order_id`        int NOT NULL,
    `item_id`         int NOT NULL,
    PRIMARY KEY (`orders_items_id`),
    KEY `orders_items_orders_fk_idx` (`order_id`),
    KEY `orders_items_items_fk_idx` (`item_id`),
    CONSTRAINT `orders_items_items_fk` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `orders_items_orders_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 26
  DEFAULT CHARSET = utf8;

CREATE TABLE `buckets`
(
    `bucket_id` int NOT NULL AUTO_INCREMENT,
    `user_id`   int NOT NULL,
    PRIMARY KEY (`bucket_id`),
    KEY `buckets_user_d_idx` (`user_id`),
    CONSTRAINT `buckets_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8;

CREATE TABLE `bucket_items`
(
    `bucket_items_id` int NOT NULL AUTO_INCREMENT,
    `bucket_id`       int NOT NULL,
    `item_id`         int NOT NULL,
    PRIMARY KEY (`bucket_items_id`),
    UNIQUE KEY `bucket_items_id_UNIQUE` (`bucket_items_id`),
    KEY `bucket_items_bucket_id_idx` (`bucket_id`),
    KEY `bucket_items_item_id_idx` (`item_id`),
    CONSTRAINT `bucket_items_bucket_id` FOREIGN KEY (`bucket_id`) REFERENCES `buckets` (`bucket_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `bucket_items_item_id` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 43
  DEFAULT CHARSET = utf8;
