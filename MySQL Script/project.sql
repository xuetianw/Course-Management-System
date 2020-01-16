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
  `played time` int(11) DEFAULT NULL,
  PRIMARY KEY (`email`)
);

--
-- Data for table `employee`
--

INSERT INTO `user` VALUES 
	('Leslie','Andrews','leslie@luv2code.com', 1),
	('Emma','Baumgarten','emma@luv2code.com', 2),
	('Avani','Gupta','avani@luv2code.com', 3)

