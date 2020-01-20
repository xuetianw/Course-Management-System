CREATE DATABASE  IF NOT EXISTS `employee_directory`;
USE `employee_directory`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  -- `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45),
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `played_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`email`)
);

--
-- Data for table `employee`
--

INSERT INTO `user` VALUES 
	('leslie@luv2code.com', 'Leslie','Andrews', 1),
	('emma@luv2code.com', 'Emma','Baumgarten', 2),
	('avani@luv2code.com', 'Avani','Gupta', 3)

