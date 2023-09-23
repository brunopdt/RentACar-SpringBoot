-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema rentacar
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema rentacar
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rentacar` DEFAULT CHARACTER SET utf8 ;
USE `rentacar` ;

-- -----------------------------------------------------
-- Table `rentacar`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentacar`.`Usuario` (
  `login` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NULL,
  PRIMARY KEY (`login`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentacar`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentacar`.`Cliente` (
  `rg` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `profissao` VARCHAR(45) NOT NULL,
  `entidade_empregadora` VARCHAR(45) NOT NULL,
  `usuariologin` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `rg_UNIQUE` (`rg` ASC) VISIBLE,
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE,
  PRIMARY KEY (`cpf`),
  INDEX `fk_Cliente_Usuario1_idx` (`usuariologin` ASC) VISIBLE,
  CONSTRAINT `fk_Cliente_Usuario1`
    FOREIGN KEY (`usuariologin`)
    REFERENCES `rentacar`.`Usuario` (`login`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentacar`.`Veiculo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentacar`.`Veiculo` (
  `placa` VARCHAR(7) NOT NULL,
  `matricula` VARCHAR(45) NOT NULL,
  `marca` VARCHAR(20) NOT NULL,
  `modelo` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`matricula`),
  UNIQUE INDEX `placa_UNIQUE` (`placa` ASC) VISIBLE,
  UNIQUE INDEX `matricula_UNIQUE` (`matricula` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentacar`.`Rendimentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentacar`.`Rendimentos` (
  `id_rendimentos` INT NOT NULL AUTO_INCREMENT,
  `valor` DOUBLE NULL,
  `Cliente_cpf` VARCHAR(11) NOT NULL,
  PRIMARY KEY (`id_rendimentos`),
  UNIQUE INDEX `id_rendimentos_UNIQUE` (`id_rendimentos` ASC) VISIBLE,
  UNIQUE INDEX `cliente_cpf_UNIQUE` (`valor` ASC) VISIBLE,
  INDEX `fk_Rendimentos_Cliente_idx` (`Cliente_cpf` ASC) VISIBLE,
  CONSTRAINT `fk_Rendimentos_Cliente`
    FOREIGN KEY (`Cliente_cpf`)
    REFERENCES `rentacar`.`Cliente` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentacar`.`Agente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentacar`.`Agente` (
  `cnpj` VARCHAR(14) NOT NULL,
  `nome_fantasia` VARCHAR(45) NULL,
  `tipo` SET("EMPRESA", "BANCO") NULL,
  `usuariologin` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cnpj`),
  UNIQUE INDEX `cnpj_UNIQUE` (`cnpj` ASC) VISIBLE,
  INDEX `fk_Agente_Usuario1_idx` (`usuariologin` ASC) VISIBLE,
  CONSTRAINT `fk_Agente_Usuario1`
    FOREIGN KEY (`usuariologin`)
    REFERENCES `rentacar`.`Usuario` (`login`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentacar`.`Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentacar`.`Pedido` (
  `id_pedido` INT NOT NULL AUTO_INCREMENT,
  `status` SET("EM_ANALISE", "APROVADO", "REPROVADO") NOT NULL,
  `veiculo_matricula` VARCHAR(45) NOT NULL,
  `data_inicio` DATE NOT NULL,
  `data_final` DATE NOT NULL,
  `Cliente_cpf` VARCHAR(11) NOT NULL,
  `Agente_cnpj` VARCHAR(14),
  PRIMARY KEY (`id_pedido`),
  UNIQUE INDEX `id_pedido_UNIQUE` (`id_pedido` ASC) VISIBLE,
  INDEX `fk_Pedido_Veiculo1_idx` (`Veiculo_matricula` ASC) VISIBLE,
  INDEX `fk_Pedido_Cliente1_idx` (`Cliente_cpf` ASC) VISIBLE,
  INDEX `fk_Pedido_Agente1_idx` (`Agente_cnpj` ASC) VISIBLE,
  CONSTRAINT `fk_Pedido_Veiculo1`
    FOREIGN KEY (`Veiculo_matricula`)
    REFERENCES `rentacar`.`Veiculo` (`matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_Cliente1`
    FOREIGN KEY (`Cliente_cpf`)
    REFERENCES `rentacar`.`Cliente` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_Agente1`
    FOREIGN KEY (`Agente_cnpj`)
    REFERENCES `rentacar`.`Agente` (`cnpj`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentacar`.`Contrato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentacar`.`Contrato` (
  `id` INT NOT NULL,
  `pedido_id_pedido` INT NOT NULL,
  `valor` DOUBLE NOT NULL,
  `proprietario_veiculo` SET("EMPRESA", "CLIENTE", "BANCO") NOT NULL,
  `credito_emitido` DOUBLE NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_Contrato_Pedido1_idx` (`Pedido_id_pedido` ASC) VISIBLE,
  CONSTRAINT `fk_Contrato_Pedido1`
    FOREIGN KEY (`Pedido_id_pedido`)
    REFERENCES `rentacar`.`Pedido` (`id_pedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;