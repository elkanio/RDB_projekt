-- Adminer 4.3.1 MySQL dump

SET NAMES utf8mb4;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

CREATE DATABASE `publictransport` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_cs_0900_as_cs  ;
USE `publictransport`;

DROP TABLE IF EXISTS `autobus`;
CREATE TABLE `autobus` (
  `spz` varchar(10) NOT NULL,
  `znacka` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`spz`),
  KEY `znacka` (`znacka`),
  CONSTRAINT `autobus_ibfk_1` FOREIGN KEY (`znacka`) REFERENCES `znacka` (`znacka`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_cs_0900_as_cs  ;

INSERT INTO `autobus` (`spz`, `znacka`) VALUES
('1A10003',	'MAN'),
('1A10001',	'Mercedes'),
('1A10004',	'VOLVO');

DROP TABLE IF EXISTS `jizda`;
CREATE TABLE `jizda` (
  `linka` varchar(50) NOT NULL,
  `spz` varchar(10) DEFAULT NULL,
  `cislo_rp` varchar(50) DEFAULT NULL,
  `cas` timestamp(6) NOT NULL,
  PRIMARY KEY (`linka`,`cas`),
  KEY `spz` (`spz`),
  KEY `cislo_rp` (`cislo_rp`),
  CONSTRAINT `jizda_ibfk_2` FOREIGN KEY (`spz`) REFERENCES `autobus` (`spz`) ON UPDATE CASCADE,
  CONSTRAINT `jizda_ibfk_3` FOREIGN KEY (`cislo_rp`) REFERENCES `ridic` (`cislo_rp`) ON UPDATE CASCADE,
  CONSTRAINT `jizda_ibfk_4` FOREIGN KEY (`linka`) REFERENCES `trasy` (`linka`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_cs_0900_as_cs  ;

INSERT INTO `jizda` (`linka`, `spz`, `cislo_rp`, `cas`) VALUES
('4987485',	'1A10003',	'6693785631',	'2019-04-16 20:14:57');

DROP TABLE IF EXISTS `jizdenka`;
CREATE TABLE `jizdenka` (
  `linka` varchar(50) NOT NULL DEFAULT '',
  `email` varchar(50) DEFAULT '',
  `cas` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `cislo` int(11) NOT NULL,
  PRIMARY KEY (`cislo`),
  KEY `linka` (`linka`),
  KEY `email` (`email`),
  CONSTRAINT `jizdenka_ibfk_1` FOREIGN KEY (`linka`) REFERENCES `jizda` (`linka`) ON UPDATE CASCADE,
  CONSTRAINT `jizdenka_ibfk_2` FOREIGN KEY (`email`) REFERENCES `klient` (`email`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_cs_0900_as_cs  ;


DROP TABLE IF EXISTS `klient`;
CREATE TABLE `klient` (
  `email` varchar(50) NOT NULL,
  `jmeno` varchar(50) NOT NULL DEFAULT '',
  `prijmeni` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_cs_0900_as_cs  ;

INSERT INTO `klient` (`email`, `jmeno`, `prijmeni`) VALUES
('dsds@gmail.com',	'sd',	'fdf'),
('lukaskania@gmail.com',	'Lukáš',	'Kania');

DROP TABLE IF EXISTS `kontakt`;
CREATE TABLE `kontakt` (
  `hodnota` varchar(50) NOT NULL DEFAULT '',
  `typ` varchar(50) DEFAULT NULL,
  `cislo_rp` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`hodnota`),
  KEY `typ` (`typ`),
  KEY `cislo_rp` (`cislo_rp`),
  CONSTRAINT `kontakt_ibfk_1` FOREIGN KEY (`typ`) REFERENCES `typkontaktu` (`typ`) ON UPDATE CASCADE,
  CONSTRAINT `kontakt_ibfk_2` FOREIGN KEY (`cislo_rp`) REFERENCES `ridic` (`cislo_rp`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_cs_0900_as_cs  ;


DROP TABLE IF EXISTS `lokalita`;
CREATE TABLE `lokalita` (
  `nazev` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`nazev`)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_cs_0900_as_cs  ;

INSERT INTO `lokalita` (`nazev`) VALUES
('Ceska Lipa'),
('Liberec'),
('Mlada Boleslav'),
('Praha');

DROP TABLE IF EXISTS `mezizastavka`;
CREATE TABLE `mezizastavka` (
  `nazev` varchar(50) NOT NULL,
  `linka` varchar(50) NOT NULL,
  PRIMARY KEY (`nazev`,`linka`),
  KEY `linka` (`linka`),
  CONSTRAINT `mezizastavka_ibfk_1` FOREIGN KEY (`nazev`) REFERENCES `lokalita` (`nazev`) ON UPDATE CASCADE,
  CONSTRAINT `mezizastavka_ibfk_2` FOREIGN KEY (`linka`) REFERENCES `trasy` (`linka`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_cs_0900_as_cs  ;


DROP TABLE IF EXISTS `ridic`;
CREATE TABLE `ridic` (
  `cislo_rp` varchar(50) NOT NULL DEFAULT '',
  `jmeno` varchar(50) NOT NULL DEFAULT '',
  `prijmeni` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`cislo_rp`)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_cs_0900_as_cs  ;

INSERT INTO `ridic` (`cislo_rp`, `jmeno`, `prijmeni`) VALUES
('6548949841',	'Tomas ',	'Janatka'),
('6693785631',	'Lukáš',	'Kania'),
('9687433336',	'Marek ',	'Svoboda');

DROP TABLE IF EXISTS `trasy`;
CREATE TABLE `trasy` (
  `linka` varchar(50) NOT NULL,
  `odkud` varchar(50) DEFAULT NULL,
  `kam` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`linka`),
  KEY `odkud` (`odkud`),
  KEY `kam` (`kam`),
  CONSTRAINT `trasy_ibfk_1` FOREIGN KEY (`odkud`) REFERENCES `lokalita` (`nazev`) ON UPDATE CASCADE,
  CONSTRAINT `trasy_ibfk_2` FOREIGN KEY (`kam`) REFERENCES `lokalita` (`nazev`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_cs_0900_as_cs  ;

INSERT INTO `trasy` (`linka`, `odkud`, `kam`) VALUES
('4987485',	'Mlada Boleslav',	'Praha'),
('49874856165',	'Liberec',	'Praha'),
('4987485777',	'Ceska Lipa',	'Praha');

DROP TABLE IF EXISTS `typkontaktu`;
CREATE TABLE `typkontaktu` (
  `typ` varchar(50) NOT NULL,
  PRIMARY KEY (`typ`)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_cs_0900_as_cs  ;


DROP TABLE IF EXISTS `znacka`;
CREATE TABLE `znacka` (
  `znacka` varchar(50) NOT NULL,
  PRIMARY KEY (`znacka`)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_cs_0900_as_cs  ;

INSERT INTO `znacka` (`znacka`) VALUES
('MAN'),
('Mercedes'),
('VOLVO');

-- 2019-04-25 20:14:53