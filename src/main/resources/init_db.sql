CREATE SCHEMA `test` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `test`.`items` (
  `item_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`item_id`));

INSERT INTO `test`.`items` (`name`, `price`) VALUES ('Mi band 4', '700');
INSERT INTO `test`.`items` (`name`, `price`) VALUES ('Mi band 5', '1200');
