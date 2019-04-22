CREATE DATABASE IF NOT EXISTS sigeredb;
USE sigeredb;

DROP TABLE IF EXISTS `sigeredb`.`users`;
CREATE TABLE `sigeredb`.`users` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`username`));