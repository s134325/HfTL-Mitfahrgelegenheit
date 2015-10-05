-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mize
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mize` ;

-- -----------------------------------------------------
-- Schema mize
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mize` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
SHOW WARNINGS;
USE `mize` ;

-- -----------------------------------------------------
-- Table `mize`.`location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mize`.`location` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `mize`.`location` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NOT NULL,
  `street_number` VARCHAR(10) NOT NULL,
  `zip_code` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `country` ENUM('GERMANY') NOT NULL,
  `latitude` DOUBLE NOT NULL,
  `longitude` DOUBLE NOT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `mize`.`trip`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mize`.`trip` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `mize`.`trip` (
  `uuid` VARCHAR(45) NOT NULL,
  `location_id_from` INT(11) NOT NULL,
  `location_id_to` INT(11) NOT NULL,
  `start_time` TIMESTAMP NOT NULL,
  `free_seats` INT(11) NOT NULL DEFAULT 1,
  `description` VARCHAR(500) NULL,
  `price` DOUBLE NOT NULL,
  `active` TINYINT(1) NOT NULL,
  `update_time` TIMESTAMP NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`uuid`),
  INDEX `location_id_from_idx` (`location_id_from` ASC),
  INDEX `location_id_to_idx` (`location_id_to` ASC),
  CONSTRAINT `location_id_from`
    FOREIGN KEY (`location_id_from`)
    REFERENCES `mize`.`location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `location_id_to`
    FOREIGN KEY (`location_id_to`)
    REFERENCES `mize`.`location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `mize`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mize`.`user` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `mize`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `uuid` VARCHAR(45) NOT NULL,
  `register_number` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NULL,
  `mail` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `gender` ENUM('MALE','FEMALE') NULL,
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `mize`.`vehicle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mize`.`vehicle` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `mize`.`vehicle` (
  `uuid` VARCHAR(45) NOT NULL,
  `make` VARCHAR(45) NOT NULL,
  `model` VARCHAR(45) NOT NULL,
  `seats` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`uuid`),
  INDEX `vehicle_user_id_idx` (`user_id` ASC),
  CONSTRAINT `vehicle_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mize`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `mize`.`user_trip`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mize`.`user_trip` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `mize`.`user_trip` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `trip_id` INT(11) NOT NULL,
  `type` ENUM('OWNER', 'PASSENGER') NOT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `user_trip_user_id_idx` (`user_id` ASC),
  INDEX `user_trip_trip_id_idx` (`trip_id` ASC),
  CONSTRAINT `user_trip_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mize`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_trip_trip_id`
    FOREIGN KEY (`trip_id`)
    REFERENCES `mize`.`trip` (`location_id_from`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
