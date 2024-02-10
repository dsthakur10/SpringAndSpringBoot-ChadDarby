USE `employee_directory`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--

INSERT INTO `users` 
VALUES 
('dev','{bcrypt}$2a$10$iM4FrtIJVW4SMgGDI1qfzu9fbIap7dmcyJapwGH.0nbuQzWio26c.',1),
('prem','{bcrypt}$2a$10$5nL2BBFddDb0tGg9wPlje.Eik4MSSBKakz0pH3Ei4jREy5YBBkaZW',1),
('asha','{bcrypt}$2a$10$qkviy0ZtNATKmzjVCFEXGu4gFIDxBrO8mE8w1ropenuL7zvbcdewG',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities4_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities4_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('dev','ROLE_EMPLOYEE'),
('prem','ROLE_EMPLOYEE'),
('prem','ROLE_MANAGER'),
('asha','ROLE_EMPLOYEE'),
('asha','ROLE_MANAGER'),
('asha','ROLE_ADMIN');