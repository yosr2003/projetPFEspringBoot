DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `databasepunictrade`.`transfert_BEFORE_INSERT` BEFORE INSERT ON `transfert` FOR EACH ROW
BEGIN
    -- Si date de création est NULL, on met la date actuelle
    IF NEW.datecre IS NULL THEN
        SET NEW.datecre = NOW();
    END IF;

    -- Si date d'échéance est NULL, on met 1 mois après la création
    IF NEW.dateEcheance IS NULL THEN
        SET NEW.dateEcheance = DATE_ADD(NEW.datecre, INTERVAL 1 MONTH);
    END IF;
END$$

DELIMITER ;
