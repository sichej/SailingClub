-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Gen 29, 2022 alle 13:35
-- Versione del server: 10.4.22-MariaDB
-- Versione PHP: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sailing_club`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `bank_transfer`
--

CREATE TABLE `bank_transfer` (
  `iban` varchar(30) NOT NULL,
  `bank` varchar(32) NOT NULL,
  `id_member` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `bank_transfer`
--

INSERT INTO `bank_transfer` (`iban`, `bank`, `id_member`) VALUES
('IT69X0300203280748853977885', 'Intesa san paolo', 'edo'),
('IT92N0300203280851278978589', 'Credit agricole', 'edo');

-- --------------------------------------------------------

--
-- Struttura della tabella `boat`
--

CREATE TABLE `boat` (
  `id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `length` double NOT NULL,
  `id_member` varchar(32) NOT NULL,
  `picture` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `boat`
--

INSERT INTO `boat` (`id`, `name`, `length`, `id_member`, `picture`) VALUES
(12, 'Luna rossa (prada)', 22, 'edo', 'lunarossa.jpg'),
(13, 'Amerigo vespucci', 153.5, 'edo', 'amerigovespucci.jpg'),
(14, 'asdfghjkl', 535, 'edo', 'generic.jpg'),
(15, 'asdfghjklò', 456, 'edo', 'generic.jpg'),
(16, 'wsdfgh66', 988, 'edo', 'generic.jpg'),
(18, 'ASDFGH', 444, 'edo', 'generic.jpg'),
(19, 'sadfghj', 32, 'edo', 'generic.jpg'),
(21, 'jdiismdiamdismmi', 988, 'edo', 'generic.jpg'),
(22, 'uu8888', 988, 'edo', 'generic.jpg'),
(32, 'barca a remi', 3, 'edo', 'barcaaremi.jpg');

-- --------------------------------------------------------

--
-- Struttura della tabella `boat_storage_fee`
--

CREATE TABLE `boat_storage_fee` (
  `id` int(11) NOT NULL,
  `payment_date` date NOT NULL,
  `expiration_date` date NOT NULL,
  `amount` double NOT NULL,
  `id_boat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `boat_storage_fee`
--

INSERT INTO `boat_storage_fee` (`id`, `payment_date`, `expiration_date`, `amount`, `id_boat`) VALUES
(10, '2022-01-28', '2031-01-28', 132, 12),
(11, '2022-01-28', '2024-01-21', 88, 13),
(12, '2022-01-03', '2022-01-28', 90, 14),
(13, '2022-01-02', '2022-01-27', 567, 15),
(14, '2022-01-17', '2022-01-28', 5678, 16),
(16, '2022-01-26', '2022-01-31', 67, 18),
(17, '2022-01-02', '2022-01-31', 78, 19),
(19, '2022-01-02', '2022-01-31', 78, 21),
(20, '2022-01-03', '2022-01-29', 9090, 22),
(25, '2022-01-27', '2023-01-27', 19.5, 32);

-- --------------------------------------------------------

--
-- Struttura della tabella `credit_card`
--

CREATE TABLE `credit_card` (
  `card_number` varchar(16) NOT NULL,
  `cvv` int(11) NOT NULL,
  `expiration_date` date NOT NULL,
  `id_member` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `credit_card`
--

INSERT INTO `credit_card` (`card_number`, `cvv`, `expiration_date`, `id_member`) VALUES
('4151839906197223', 354, '2026-01-09', 'edo'),
('5061997095036317', 123, '2026-01-21', 'edo');

-- --------------------------------------------------------

--
-- Struttura della tabella `membership_fee`
--

CREATE TABLE `membership_fee` (
  `id` int(11) NOT NULL,
  `payment_date` date NOT NULL,
  `expiration_date` date NOT NULL,
  `price` double NOT NULL,
  `id_member` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `membership_fee`
--

INSERT INTO `membership_fee` (`id`, `payment_date`, `expiration_date`, `price`, `id_member`) VALUES
(1, '2022-01-03', '2022-01-04', 599.99, 'Berto'),
(2, '2022-01-29', '2037-01-17', 599.99, 'edo');

-- --------------------------------------------------------

--
-- Struttura della tabella `race`
--

CREATE TABLE `race` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `price` double NOT NULL,
  `name` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `race`
--

INSERT INTO `race` (`id`, `date`, `price`, `name`) VALUES
(1, '2016-06-09', 200, 'coppa del mondo'),
(2, '2022-01-11', 200, 'coppa america'),
(3, '2024-10-24', 200, 'coppa asia'),
(4, '2016-01-07', 123, 'gara che è finita ');

-- --------------------------------------------------------

--
-- Struttura della tabella `race_participation`
--

CREATE TABLE `race_participation` (
  `id_member` varchar(32) NOT NULL,
  `id_race` int(11) NOT NULL,
  `id_boat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `race_participation`
--

INSERT INTO `race_participation` (`id_member`, `id_race`, `id_boat`) VALUES
('edo', 1, 14),
('edo', 3, 13);

-- --------------------------------------------------------

--
-- Struttura della tabella `user`
--

CREATE TABLE `user` (
  `username` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `surname` varchar(32) NOT NULL,
  `address` varchar(32) NOT NULL,
  `fiscal_code` varchar(16) NOT NULL,
  `user_type` varchar(16) NOT NULL,
  `password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `user`
--

INSERT INTO `user` (`username`, `name`, `surname`, `address`, `fiscal_code`, `user_type`, `password`) VALUES
('Berto', 'Andrea', 'Bertogalli', 'Wall street, 3', 'BRTNDR00L30G337G', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('edo', 'edoardo', 'sichelli', 'via gentileschi', 'QWERTYUIOPLKJHGF', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('emp', 'pippo', 'pluto', 'gardaland', 'dnwu', 'employee', '9dd4e461268c8034f5c8564e155c67a6');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `bank_transfer`
--
ALTER TABLE `bank_transfer`
  ADD PRIMARY KEY (`iban`),
  ADD KEY `fkMemberBankTransfer` (`id_member`);

--
-- Indici per le tabelle `boat`
--
ALTER TABLE `boat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkMember` (`id_member`);

--
-- Indici per le tabelle `boat_storage_fee`
--
ALTER TABLE `boat_storage_fee`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkBoatFee` (`id_boat`);

--
-- Indici per le tabelle `credit_card`
--
ALTER TABLE `credit_card`
  ADD PRIMARY KEY (`card_number`),
  ADD KEY `fkMemberCreditCard` (`id_member`);

--
-- Indici per le tabelle `membership_fee`
--
ALTER TABLE `membership_fee`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkMemberFee` (`id_member`);

--
-- Indici per le tabelle `race`
--
ALTER TABLE `race`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `race_participation`
--
ALTER TABLE `race_participation`
  ADD PRIMARY KEY (`id_member`,`id_race`,`id_boat`) USING BTREE,
  ADD KEY `fk_race` (`id_race`,`id_member`,`id_boat`) USING BTREE;

--
-- Indici per le tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`) USING BTREE;

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `boat`
--
ALTER TABLE `boat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT per la tabella `boat_storage_fee`
--
ALTER TABLE `boat_storage_fee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT per la tabella `membership_fee`
--
ALTER TABLE `membership_fee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `race`
--
ALTER TABLE `race`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `bank_transfer`
--
ALTER TABLE `bank_transfer`
  ADD CONSTRAINT `fkMemberBank` FOREIGN KEY (`id_member`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `boat`
--
ALTER TABLE `boat`
  ADD CONSTRAINT `fkMember` FOREIGN KEY (`id_member`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `boat_storage_fee`
--
ALTER TABLE `boat_storage_fee`
  ADD CONSTRAINT `fkBoatFee` FOREIGN KEY (`id_boat`) REFERENCES `boat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `credit_card`
--
ALTER TABLE `credit_card`
  ADD CONSTRAINT `fkMemberCreditCard` FOREIGN KEY (`id_member`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `membership_fee`
--
ALTER TABLE `membership_fee`
  ADD CONSTRAINT `fkMemberFee` FOREIGN KEY (`id_member`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `race_participation`
--
ALTER TABLE `race_participation`
  ADD CONSTRAINT `fk_member_race` FOREIGN KEY (`id_member`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `fk_race` FOREIGN KEY (`id_race`) REFERENCES `race` (`id`),
  ADD CONSTRAINT `fk_race_boat` FOREIGN KEY (`id_boat`) REFERENCES `boat` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
