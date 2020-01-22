CREATE DATABASE  IF NOT EXISTS `tic-tac-toe`;
USE `tic-tac-toe`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) UNIQUE,
  `password` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `played_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

--
-- Data for table `employee`
--


    
    
INSERT INTO `user`
VALUES
	(1, 'admin@gmail.com','p','Sam','sam' , 1)

