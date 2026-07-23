/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 10.4.32-MariaDB : Database - plesna_skola
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`plesna_skola` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `plesna_skola`;

/*Table structure for table `cas` */

DROP TABLE IF EXISTS `cas`;

CREATE TABLE `cas` (
  `idCas` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  `trajanje` bigint(20) NOT NULL,
  `stilPlesa` varchar(50) NOT NULL,
  PRIMARY KEY (`idCas`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `cas` */

insert  into `cas`(`idCas`,`naziv`,`trajanje`,`stilPlesa`) values 
(1,'moderni balet',90,'balet'),
(2,'klasični balet',120,'balet'),
(3,'breakdance tehnike',60,'breakdance'),
(4,'salsa za početnike',60,'salsa'),
(5,'napredna salsa',90,'salsa'),
(6,'hip-hop osnove',45,'hip-hop'),
(7,'tango argentino',75,'tango'),
(8,'jazz dance',75,'jazz dance'),
(9,'zumba fitness',50,'zumba');

/*Table structure for table `evidencija_casova` */

DROP TABLE IF EXISTS `evidencija_casova`;

CREATE TABLE `evidencija_casova` (
  `idEvidencijaCasova` bigint(20) NOT NULL AUTO_INCREMENT,
  `skolskaGodina` varchar(9) NOT NULL,
  `datumPocetka` date NOT NULL,
  `datumZavrsetka` date NOT NULL,
  `prosecnaOcena` double NOT NULL DEFAULT 0,
  `brojPrisustva` int(11) NOT NULL DEFAULT 0,
  `profesor` bigint(20) NOT NULL,
  `ucenik` bigint(20) NOT NULL,
  `trajanjeEvidencije` int(11) GENERATED ALWAYS AS (to_days(`datumZavrsetka`) - to_days(`datumPocetka`)) STORED,
  PRIMARY KEY (`idEvidencijaCasova`),
  KEY `evidencija_casova_ibfk_1` (`profesor`),
  KEY `evidencija_casova_ibfk_2` (`ucenik`),
  CONSTRAINT `evidencija_casova_ibfk_1` FOREIGN KEY (`profesor`) REFERENCES `profesor` (`idProfesor`) ON UPDATE CASCADE,
  CONSTRAINT `evidencija_casova_ibfk_2` FOREIGN KEY (`ucenik`) REFERENCES `ucenik` (`idUcenik`) ON UPDATE CASCADE,
  CONSTRAINT `chk_skolska_godina_format` CHECK (`skolskaGodina` like '%/%')
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `evidencija_casova` */

insert  into `evidencija_casova`(`idEvidencijaCasova`,`skolskaGodina`,`datumPocetka`,`datumZavrsetka`,`prosecnaOcena`,`brojPrisustva`,`profesor`,`ucenik`) values 
(4,'2024/2025','2024-09-01','2025-06-01',4,3,1,3),
(5,'2024/2025','2024-09-01','2025-06-01',4,1,1,3),
(6,'2023/2024','2023-09-01','2024-06-20',4.666666666,3,1,3),
(7,'2022/2023','2022-08-31','2023-10-13',4,1,1,4),
(14,'2021/2022','2021-01-01','2022-02-02',5,1,5,15),
(15,'2021/2022','2021-01-01','2022-01-02',4,2,1,15),
(16,'2021/2022','2021-09-01','2022-06-21',4.5,2,5,15),
(17,'2022/2023','2022-05-09','2023-05-08',4.5,2,1,3),
(18,'2025/2026','2025-09-01','2026-06-05',5,1,1,3),
(19,'2025/2026','2025-09-01','2026-05-05',5,1,1,14);

/*Table structure for table `plesni_nivo` */

DROP TABLE IF EXISTS `plesni_nivo`;

CREATE TABLE `plesni_nivo` (
  `idPlesniNivo` bigint(20) NOT NULL AUTO_INCREMENT,
  `opis` varchar(250) NOT NULL,
  `nivo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPlesniNivo`),
  UNIQUE KEY `uq_nivo` (`nivo`),
  CONSTRAINT `chk_nivo_range` CHECK (`nivo` in (1,2,3,4,5))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `plesni_nivo` */

insert  into `plesni_nivo`(`idPlesniNivo`,`opis`,`nivo`) values 
(1,'početni nivo',1),
(2,'osnovni nivo',2),
(3,'srednji nivo',3),
(4,'napredni nivo',4),
(5,'profesionalac',5);

/*Table structure for table `pr_s` */

DROP TABLE IF EXISTS `pr_s`;

CREATE TABLE `pr_s` (
  `profesor` bigint(20) NOT NULL,
  `sertifikat` bigint(20) NOT NULL,
  `datumIzdavanja` date NOT NULL,
  PRIMARY KEY (`profesor`,`sertifikat`,`datumIzdavanja`),
  KEY `pr_s_ibfk_2` (`sertifikat`),
  CONSTRAINT `pr_s_ibfk_1` FOREIGN KEY (`profesor`) REFERENCES `profesor` (`idProfesor`) ON UPDATE CASCADE,
  CONSTRAINT `pr_s_ibfk_2` FOREIGN KEY (`sertifikat`) REFERENCES `sertifikat` (`idSertifikat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `pr_s` */

insert  into `pr_s`(`profesor`,`sertifikat`,`datumIzdavanja`) values 
(1,1,'2019-04-12'),
(1,2,'2006-08-19'),
(3,5,'2014-07-07'),
(4,3,'2021-07-15'),
(5,6,'2020-01-16');

/*Table structure for table `profesor` */

DROP TABLE IF EXISTS `profesor`;

CREATE TABLE `profesor` (
  `idProfesor` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `brojTelefona` varchar(50) NOT NULL,
  `korisnickoIme` varchar(50) NOT NULL,
  `sifra` varchar(50) NOT NULL,
  PRIMARY KEY (`idProfesor`),
  CONSTRAINT `chk_sifra` CHECK (char_length(`sifra`) >= 8),
  CONSTRAINT `chk_sifra_duzina` CHECK (char_length(`sifra`) >= 8)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `profesor` */

insert  into `profesor`(`idProfesor`,`ime`,`prezime`,`brojTelefona`,`korisnickoIme`,`sifra`) values 
(1,'Marko','Marković','0641234567','mpetrovic','Ples123!'),
(2,'Ana','Jovanović','0619876543','ajovanovic','Tango@2024'),
(3,'Nikola','Stojanović','0635558889','nstojanovic','Salsa_789'),
(4,'Milica','Ilić','0653332221','milic','Bahata123!'),
(5,'Ivan','Kostić','0604447773','ikostic','HipHop#123');

/*Table structure for table `sertifikat` */

DROP TABLE IF EXISTS `sertifikat`;

CREATE TABLE `sertifikat` (
  `idSertifikat` bigint(20) NOT NULL AUTO_INCREMENT,
  `institucija` varchar(50) NOT NULL,
  `naziv` varchar(50) NOT NULL,
  PRIMARY KEY (`idSertifikat`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sertifikat` */

insert  into `sertifikat`(`idSertifikat`,`institucija`,`naziv`) values 
(1,'Savez sportskog plesa Srbije','Licencirani trener sportskog plesa'),
(2,'Fakultet sporta i fizičkog vaspitanja','Instruktor rekreativnog plesa'),
(3,'Baletska škola „Lujo Davičo“','Sertifikat za klasični balet'),
(4,'Plesna škola „Natalija i Ivica“','Instruktor društvenih plesova'),
(5,'Internacionalna plesna organizacija (IDTA)','Međunarodni sertifikat za plesnog instruktora'),
(6,'Plesni klub „Aurora“','Sertifikat za latino i standardne plesove'),
(7,'Plesna akademija Beograd','Instruktor plesa'),
(8,'Royal Academy of Dance','Diploma klasičnog baleta'),
(9,'Laban/Bartenieff Institute','Sertifikat savremenog plesa');

/*Table structure for table `stavka_evidencije` */

DROP TABLE IF EXISTS `stavka_evidencije`;

CREATE TABLE `stavka_evidencije` (
  `rb` bigint(20) NOT NULL AUTO_INCREMENT,
  `datumPrisustva` date DEFAULT NULL,
  `ocena` int(11) DEFAULT NULL,
  `napomena` varchar(50) DEFAULT NULL,
  `evidencija_casova` bigint(20) NOT NULL,
  `cas` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`rb`,`evidencija_casova`),
  KEY `stavka_evidencije_ibfk_1` (`evidencija_casova`),
  KEY `stavka_evidencije_ibfk_2` (`cas`),
  CONSTRAINT `stavka_evidencije_ibfk_1` FOREIGN KEY (`evidencija_casova`) REFERENCES `evidencija_casova` (`idEvidencijaCasova`) ON UPDATE CASCADE,
  CONSTRAINT `stavka_evidencije_ibfk_2` FOREIGN KEY (`cas`) REFERENCES `cas` (`idCas`) ON UPDATE CASCADE,
  CONSTRAINT `chk_OcenaOpseg` CHECK (`ocena` between 1 and 5)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `stavka_evidencije` */

insert  into `stavka_evidencije`(`rb`,`datumPrisustva`,`ocena`,`napomena`,`evidencija_casova`,`cas`) values 
(6,'2023-11-10',5,'Izvanredan talenat za ritam',6,5),
(7,'2023-11-17',5,'Bez greške',6,5),
(8,'2024-02-20',4,'Dobro se snašla sa novom koreografijom',6,9),
(19,'2024-09-15',4,'Testna napomena',5,1),
(20,'2024-09-28',5,'Izmenjena napomena',4,4),
(21,'2024-10-01',4,'Savladala osnovni korak',4,1),
(23,'2024-11-15',3,'Nova stavka',4,5),
(25,'2021-02-02',5,'Odlicne piruete',14,2),
(29,'2021-09-02',4,'Vrlo dobro savladan poskok',16,1),
(30,'2021-09-15',5,'Odlicna tehnika',16,4),
(32,'2025-10-02',5,'Odlicna koordinacija',18,2),
(33,'2025-09-15',5,'Svaka cast na formi pirueta',19,1),
(34,'2021-05-05',4,'Vrlo dobro drzanje',15,7),
(35,'2021-03-03',4,'Duet odlicno savladan',15,5),
(36,'2022-05-10',5,'Svaka cast',17,2),
(37,'2023-04-04',4,'vrlo dobro drzanje',17,2),
(38,'2022-09-01',4,'Vrlo dobro savladani okreti',7,4);

/*Table structure for table `ucenik` */

DROP TABLE IF EXISTS `ucenik`;

CREATE TABLE `ucenik` (
  `idUcenik` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `brojTelefona` varchar(50) NOT NULL,
  `idPlesniNivo` bigint(10) DEFAULT 1,
  PRIMARY KEY (`idUcenik`),
  KEY `fk_ucenik_plesni_nivo` (`idPlesniNivo`),
  CONSTRAINT `fk_ucenik_plesni_nivo` FOREIGN KEY (`idPlesniNivo`) REFERENCES `plesni_nivo` (`idPlesniNivo`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `ucenik` */

insert  into `ucenik`(`idUcenik`,`ime`,`prezime`,`brojTelefona`,`idPlesniNivo`) values 
(3,'Ana','Anić','0628596748',3),
(4,'Marko','Markovic','0611111111',4),
(14,'Petar','Petrovic','0621234567',5),
(15,'Stefan','Stefanovic','0631234567',4);

/* Trigger structure for table `evidencija_casova` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `trg_BlokirajInsertPrisustva` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `trg_BlokirajInsertPrisustva` BEFORE INSERT ON `evidencija_casova` FOR EACH ROW 
BEGIN
    -- Nova evidencija uvek počinje sa 0 prisustva i 0 proseka
    SET NEW.brojPrisustva = 0;
    SET NEW.prosecnaOcena = 0;
END */$$


DELIMITER ;

/* Trigger structure for table `evidencija_casova` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `proveri_datum_before_insert` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `proveri_datum_before_insert` BEFORE INSERT ON `evidencija_casova` FOR EACH ROW 
BEGIN
    IF NEW.datumZavrsetka < NEW.datumPocetka THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Datum zavrsetka mora biti nakon datuma pocetka!';
    END IF;
END */$$


DELIMITER ;

/* Trigger structure for table `evidencija_casova` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `provjeri_skolsku_godinu_before_insert` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `provjeri_skolsku_godinu_before_insert` BEFORE INSERT ON `evidencija_casova` FOR EACH ROW 
BEGIN
    DECLARE godina_od INT;
    DECLARE godina_do INT;
    
    SET godina_od = CAST(SUBSTRING_INDEX(NEW.skolskaGodina, '/', 1) AS UNSIGNED);
    SET godina_do = CAST(SUBSTRING_INDEX(NEW.skolskaGodina, '/', -1) AS UNSIGNED);
    
    IF YEAR(NEW.datumPocetka) != godina_od THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Godina datuma pocetka mora biti jednaka prvoj godini skolske godine!';
    END IF;
    
    IF YEAR(NEW.datumZavrsetka) != godina_do THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Godina datuma zavrsetka mora biti jednaka drugoj godini skolske godine!';
    END IF;
END */$$


DELIMITER ;

/* Trigger structure for table `evidencija_casova` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `trg_BlokirajIzmenaProseka` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `trg_BlokirajIzmenaProseka` BEFORE UPDATE ON `evidencija_casova` FOR EACH ROW 
BEGIN
    IF COALESCE(@interni_update, 0) = 0 THEN
        IF NEW.prosecnaOcena <> OLD.prosecnaOcena THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'GREŠKA: prosecnaOcena se ne sme menjati ručno.';
        END IF;
        IF NEW.brojPrisustva <> OLD.brojPrisustva THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'GREŠKA: brojPrisustva se ne sme menjati ručno.';
        END IF;
    END IF;
END */$$


DELIMITER ;

/* Trigger structure for table `evidencija_casova` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `provjeri_datum_before_update` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `provjeri_datum_before_update` BEFORE UPDATE ON `evidencija_casova` FOR EACH ROW 
BEGIN
    IF NEW.datumZavrsetka < NEW.datumPocetka THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Datum zavrsetka mora biti nakon datuma pocetka!';
    END IF;
END */$$


DELIMITER ;

/* Trigger structure for table `evidencija_casova` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `provjeri_skolsku_godinu_before_update` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `provjeri_skolsku_godinu_before_update` BEFORE UPDATE ON `evidencija_casova` FOR EACH ROW 
BEGIN
    DECLARE godina_od INT;
    DECLARE godina_do INT;
    
    SET godina_od = CAST(SUBSTRING_INDEX(NEW.skolskaGodina, '/', 1) AS UNSIGNED);
    SET godina_do = CAST(SUBSTRING_INDEX(NEW.skolskaGodina, '/', -1) AS UNSIGNED);
    
    IF YEAR(NEW.datumPocetka) != godina_od THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Godina datuma pocetka mora biti jednaka prvoj godini skolske godine!';
    END IF;
    
    IF YEAR(NEW.datumZavrsetka) != godina_do THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Godina datuma zavrsetka mora biti jednaka drugoj godini skolske godine!';
    END IF;
END */$$


DELIMITER ;

/* Trigger structure for table `stavka_evidencije` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `trg_Stavka_BeforeInsert` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `trg_Stavka_BeforeInsert` BEFORE INSERT ON `stavka_evidencije` FOR EACH ROW 
BEGIN
    DECLARE v_pocetak DATE;
    DECLARE v_kraj DATE;

    SELECT datumPocetka, datumZavrsetka
    INTO v_pocetak, v_kraj
    FROM evidencija_casova
    WHERE idEvidencijaCasova = NEW.evidencija_casova;

    IF NEW.datumPrisustva IS NOT NULL
       AND (NEW.datumPrisustva < v_pocetak OR NEW.datumPrisustva > v_kraj) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'GREŠKA: Datum prisustva mora biti unutar perioda važenja evidencije!';
    END IF;
END */$$


DELIMITER ;

/* Trigger structure for table `stavka_evidencije` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `provjeri_datum_prisustva_before_insert` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `provjeri_datum_prisustva_before_insert` BEFORE INSERT ON `stavka_evidencije` FOR EACH ROW 
BEGIN
    DECLARE dat_pocetka DATE;
    DECLARE dat_zavrsetka DATE;
    
    SELECT datumPocetka, datumZavrsetka 
    INTO dat_pocetka, dat_zavrsetka
    FROM evidencija_casova
    WHERE idEvidencijaCasova = NEW.evidencija_casova;
    
    IF NEW.datumPrisustva < dat_pocetka OR NEW.datumPrisustva > dat_zavrsetka THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Datum prisustva mora biti izmedju datuma pocetka i zavrsetka evidencije!';
    END IF;
END */$$


DELIMITER ;

/* Trigger structure for table `stavka_evidencije` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `trg_Stavka_AfterInsert` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `trg_Stavka_AfterInsert` AFTER INSERT ON `stavka_evidencije` FOR EACH ROW 
BEGIN
    -- Provera datuma (prebačena ovde iz BEFORE — možeš zadržati i poseban BEFORE)
    CALL azuriraj_evidenciju(NEW.evidencija_casova);
END */$$


DELIMITER ;

/* Trigger structure for table `stavka_evidencije` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `trg_Stavka_BeforeUpdate` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `trg_Stavka_BeforeUpdate` BEFORE UPDATE ON `stavka_evidencije` FOR EACH ROW 
BEGIN
    DECLARE v_pocetak DATE;
    DECLARE v_kraj DATE;

    SELECT datumPocetka, datumZavrsetka
    INTO v_pocetak, v_kraj
    FROM evidencija_casova
    WHERE idEvidencijaCasova = NEW.evidencija_casova;

    IF NEW.datumPrisustva IS NOT NULL
       AND (NEW.datumPrisustva < v_pocetak OR NEW.datumPrisustva > v_kraj) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'GREŠKA: Datum prisustva mora biti unutar perioda važenja evidencije!';
    END IF;
END */$$


DELIMITER ;

/* Trigger structure for table `stavka_evidencije` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `provjeri_datum_prisustva_before_update` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `provjeri_datum_prisustva_before_update` BEFORE UPDATE ON `stavka_evidencije` FOR EACH ROW 
BEGIN
    DECLARE dat_pocetka DATE;
    DECLARE dat_zavrsetka DATE;
    
    SELECT datumPocetka, datumZavrsetka 
    INTO dat_pocetka, dat_zavrsetka
    FROM evidencija_casova
    WHERE idEvidencijaCasova = NEW.evidencija_casova;
    
    IF NEW.datumPrisustva < dat_pocetka OR NEW.datumPrisustva > dat_zavrsetka THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Datum prisustva mora biti izmedju datuma pocetka i zavrsetka evidencije!';
    END IF;
END */$$


DELIMITER ;

/* Trigger structure for table `stavka_evidencije` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `trg_Stavka_AfterUpdate` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `trg_Stavka_AfterUpdate` AFTER UPDATE ON `stavka_evidencije` FOR EACH ROW 
BEGIN
    CALL azuriraj_evidenciju(NEW.evidencija_casova);
END */$$


DELIMITER ;

/* Trigger structure for table `stavka_evidencije` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `trg_Stavka_AfterDelete` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `trg_Stavka_AfterDelete` AFTER DELETE ON `stavka_evidencije` FOR EACH ROW 
BEGIN
    CALL azuriraj_evidenciju(OLD.evidencija_casova);
END */$$


DELIMITER ;

/* Trigger structure for table `ucenik` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `trg_Ucenik_BeforeInsert` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `trg_Ucenik_BeforeInsert` BEFORE INSERT ON `ucenik` FOR EACH ROW 
BEGIN
    IF NEW.idPlesniNivo IS NULL OR NEW.idPlesniNivo = 0 THEN
        SET NEW.idPlesniNivo = 1;
    END IF;
END */$$


DELIMITER ;

/* Procedure structure for procedure `azuriraj_evidenciju` */

/*!50003 DROP PROCEDURE IF EXISTS  `azuriraj_evidenciju` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `azuriraj_evidenciju`(IN p_evidencija_id BIGINT)
BEGIN
    DECLARE v_prosek DOUBLE;
    DECLARE v_ucenik BIGINT;

    -- Izračunaj prosek ocena iz stavki (NULL → 0)
    SELECT COALESCE(AVG(ocena), 0)
    INTO v_prosek
    FROM stavka_evidencije
    WHERE evidencija_casova = p_evidencija_id;

    -- Uzmi ID učenika za kasniji UPDATE ucenik
    SELECT ucenik INTO v_ucenik
    FROM evidencija_casova
    WHERE idEvidencijaCasova = p_evidencija_id;

    -- Privremeno dozvoli internu izmenu proseka
    SET @interni_update = 1;

    UPDATE evidencija_casova
    SET
        brojPrisustva = (
            SELECT COUNT(*)
            FROM stavka_evidencije
            WHERE evidencija_casova = p_evidencija_id
        ),
        prosecnaOcena = v_prosek
    WHERE idEvidencijaCasova = p_evidencija_id;

    SET @interni_update = 0;

    -- Sinhronizuj nivo učenika:
    -- Uzimamo prosek SVIH prosecnaOcena tog učenika (ne samo jedne evidencije)
    UPDATE ucenik
    SET idPlesniNivo = GREATEST(1, LEAST(5, ROUND(
        (SELECT AVG(prosecnaOcena)
         FROM evidencija_casova
         WHERE ucenik = v_ucenik
           AND prosecnaOcena > 0)
    )))
    WHERE idUcenik = v_ucenik;
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
