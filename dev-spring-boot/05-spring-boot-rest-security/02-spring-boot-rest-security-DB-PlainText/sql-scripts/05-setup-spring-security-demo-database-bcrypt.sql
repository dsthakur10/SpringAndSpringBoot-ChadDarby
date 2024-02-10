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
('devendra','{bcrypt}$2a$10$yDR4hRwXX1w.RaEKsaYBr.umno8AheX/gZrR95GAN2LSZZRIDe2Iq',1),
('thakur','{bcrypt}$2a$10$KgrDJ.dX66ujWImjfSxuvuDdVnQvnmO1pp/at1q9YPZu.JYnVcyKG',1),
('premsingh','{bcrypt}$2a$10$6pAn2eSh8msU2vup0g.OnuY7D4EOHe33kD.zyYx2vJgOEE0HR..D6',1);


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
('devendra','ROLE_EMPLOYEE'),
('thakur','ROLE_EMPLOYEE'),
('thakur','ROLE_MANAGER'),
('premsingh','ROLE_EMPLOYEE'),
('premsingh','ROLE_MANAGER'),
('premsingh','ROLE_ADMIN');