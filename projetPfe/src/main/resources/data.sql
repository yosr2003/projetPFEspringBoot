DELETE FROM transfert WHERE refTransfert = 'A4500';
INSERT INTO transfert (refTransfert,typeTransfert , montantTransfert , deviseTransfert ,etat , natureTransfert , frais) VALUES ('A4500','FINANCIER','400','EUR','TRAITEMENT','COURANT',1400);

INSERT INTO `databasepunictrade`.`taux_change` (`id` , `devise` , `cours_vente`) VALUES('784' , 'AED' , 8.593);
INSERT INTO `databasepunictrade`.`taux_change` (`id` , `devise` , `cours_vente`) VALUES ('48' , 'BHD' , 8.373);
INSERT INTO `databasepunictrade`.`taux_change` (`id` , `devise` , `cours_vente`) VALUES ('124' , 'CAD' , 2.201);
INSERT INTO `databasepunictrade`.`taux_change` (`id` , `devise` , `cours_vente`) VALUES ('756' , 'CHF' , 35.763);
INSERT INTO `databasepunictrade`.`taux_change` (`id` , `devise` , `cours_vente`) VALUES ('156' , 'CNY' , 0.435);
INSERT INTO `databasepunictrade`.`taux_change` (`id` , `devise` , `cours_vente`) VALUES ('208' , 'DKK' , 45.855);
INSERT INTO `databasepunictrade`.`taux_change` (`id` , `devise` , `cours_vente`) VALUES ('978' , 'EUR' , 3.398);
INSERT INTO `databasepunictrade`.`taux_change` (`id` , `devise` , `cours_vente`) VALUES ('826' , 'GBP' , 4.082);
INSERT INTO `databasepunictrade`.`taux_change` (`id` , `devise` , `cours_vente`) VALUES ('392' , 'JPY' , 21.098);
INSERT INTO `databasepunictrade`.`taux_change` (`id` , `devise` , `cours_vente`) VALUES ('414' , 'KWD' , 10.245);
INSERT INTO `databasepunictrade`.`taux_change` (`id` , `devise` , `cours_vente`) VALUES ('578' , 'NOK' , 29.988);
INSERT INTO `databasepunictrade`.`taux_change` (`id` , `devise` , `cours_vente`) VALUES ('634' , 'QAR' , 8.660);
INSERT INTO `databasepunictrade`.`taux_change` (`id` , `devise` , `cours_vente`) VALUES ('682' , 'SAR' , 8.439);
INSERT INTO `databasepunictrade`.`taux_change` (`id` , `devise` , `cours_vente`) VALUES ('752' , 'SEK' , 3.125);
INSERT INTO `databasepunictrade`.`taux_change` (`id` , `devise` , `cours_vente`) VALUES ('840' , 'USD' ,3.156);
