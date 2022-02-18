-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Feb 18, 2022 alle 12:13
-- Versione del server: 10.4.22-MariaDB
-- Versione PHP: 8.1.2

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
('123456123', 'Monte dei paschi di siena', 'edo'),
('1234567', 'Banca Mediolanum', 'user_for_test'),
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
(1, 'testboat', 14, 'user_for_test', 'generic.jpg'),
(12, 'Luna rossa (prada)', 22, 'edo', 'lunarossa.jpg'),
(13, 'Amerigo vespucci', 153.5, 'edo', 'amerigovespucci.jpg'),
(14, 'Alyssa', 535.33333, 'edo', 'generic.jpg'),
(15, 'gorilla', 455, 'Berto', 'generic.jpg'),
(16, 'Yatch sirena', 988.2345, 'edo', 'generic.jpg'),
(18, 'Speedy ', 444, 'edo', 'generic.jpg'),
(19, 'Zakintos yatch', 32, 'edo', 'generic.jpg'),
(21, 'Regina', 988, 'edo', 'generic.jpg'),
(32, 'barca a remi', 3, 'edo', 'barcaaremi.jpg'),
(44, 'Magic yatch', 12, 'edo', '');

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
(10, '2022-02-06', '2036-01-28', 132, 12),
(11, '2022-02-04', '2027-01-21', 88, 13),
(12, '2022-01-03', '2030-01-25', 90.444444, 14),
(13, '2022-01-02', '2022-01-11', 567, 15),
(14, '2022-02-06', '2023-01-28', 5678, 16),
(16, '2022-02-05', '2024-01-31', 67, 18),
(17, '2022-01-02', '2022-01-31', 78, 19),
(19, '2022-01-02', '2022-01-31', 78, 21),
(20, '2022-02-18', '2024-01-29', 9090, 1),
(25, '2022-01-27', '2023-01-27', 19.5, 32),
(37, '2022-02-08', '2023-02-08', 246, 44);

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
('123456', 455, '2022-02-23', 'Berto'),
('1234567', 123, '2022-02-17', 'edo'),
('4151839906197223', 354, '2026-01-09', 'edo'),
('5061997095036317', 123, '2026-01-21', 'user_for_test');

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
(2, '2022-02-07', '2024-01-14', 15, 'edo'),
(15, '2022-02-18', '2024-02-26', 89, 'user_for_test');

-- --------------------------------------------------------

--
-- Struttura della tabella `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `id_member` varchar(32) NOT NULL,
  `text` varchar(128) NOT NULL,
  `date_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `notification`
--

INSERT INTO `notification` (`id`, `id_member`, `text`, `date_time`) VALUES
(20, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nLuna rossa (prada)', '2022-02-07 10:59:31'),
(21, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nAmerigo vespucci', '2022-02-07 10:59:32'),
(22, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nLuna rossa (prada)', '2022-02-07 10:59:32'),
(23, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nAmerigo vespucci', '2022-02-07 10:59:32'),
(24, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nbarca rinominata', '2022-02-07 10:59:33'),
(25, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nASDFGH', '2022-02-07 10:59:33'),
(26, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nASDFGH', '2022-02-07 10:59:34'),
(27, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nsadfghj', '2022-02-07 10:59:34'),
(28, 'edo', 'REMINDER:\nYou have to pay the storage fee for\njdiismdiamdismmi', '2022-02-07 10:59:34'),
(29, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nuu8888', '2022-02-07 10:59:35'),
(30, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nbarca a remi', '2022-02-07 10:59:35'),
(31, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nuu8888', '2022-02-07 10:59:35'),
(32, 'edo', 'REMINDER:\nYou have to pay the storage fee for\njdiismdiamdismmi', '2022-02-07 10:59:36'),
(33, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nsadfghj', '2022-02-07 10:59:36'),
(34, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nASDFGH', '2022-02-07 10:59:36'),
(35, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nwsdfgh66', '2022-02-07 10:59:36'),
(36, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nbarca rinominata', '2022-02-07 10:59:37'),
(37, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nAmerigo vespucci', '2022-02-07 10:59:37'),
(38, 'Berto', 'REMINDER:\nYou have to pay the annual membership fee', '2022-02-07 11:23:36'),
(39, 'Berto', 'REMINDER:\nYou have to pay the annual membership fee', '2022-02-07 11:23:42'),
(40, 'Berto', 'REMINDER:\nYou have to pay the annual membership fee', '2022-02-07 11:23:43'),
(41, 'edo', 'REMINDER:\nYou have to pay the annual membership fee', '2022-02-07 11:37:32'),
(42, 'edo', 'REMINDER:\nYou have to pay the annual membership fee', '2022-02-07 11:37:33'),
(43, 'edo', 'REMINDER:\nYou have to pay the annual membership fee', '2022-02-07 11:37:33'),
(44, 'edo', 'REMINDER:\nYou have to pay the annual membership fee', '2022-02-07 11:37:33'),
(45, 'edo', 'REMINDER:\nYou have to pay the annual membership fee', '2022-02-07 11:37:34'),
(46, 'edo', 'REMINDER:\nYou have to pay the annual membership fee', '2022-02-07 11:37:34'),
(47, 'edo', 'REMINDER:\nYou have to pay the annual membership fee', '2022-02-07 11:37:34'),
(48, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nbarca rinominata', '2022-02-07 11:37:37'),
(49, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nAmerigo vespucci', '2022-02-07 11:37:38'),
(51, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nLuna rossa (prada)', '2022-02-08 10:49:02'),
(52, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nAmerigo vespucci', '2022-02-08 10:49:02'),
(53, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nAmerigo vespucci', '2022-02-08 10:49:03'),
(54, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nAmerigo vespucci', '2022-02-08 10:49:03'),
(55, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nAmerigo vespucci', '2022-02-08 10:49:04'),
(56, 'edo', 'REMINDER:\nYou have to pay the storage fee for\nAmerigo vespucci', '2022-02-08 10:49:04');

-- --------------------------------------------------------

--
-- Struttura della tabella `payment`
--

CREATE TABLE `payment` (
  `amount` float(23,2) NOT NULL,
  `member_id` varchar(32) NOT NULL,
  `method` varchar(32) NOT NULL,
  `details` varchar(128) NOT NULL,
  `date` date NOT NULL,
  `purpose` varchar(256) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `payment`
--

INSERT INTO `payment` (`amount`, `member_id`, `method`, `details`, `date`, `purpose`, `id`) VALUES
(132.00, 'edo', 'C. card', '41518399********', '2022-02-06', 'Payment for storage fee of:\nLuna rossa (prada)', 1),
(9090.00, 'edo', 'C. card', '41518399********', '2022-02-06', 'Payment for storage fee of:\nuu8888', 2),
(5678.00, 'edo', 'C. card', '41518399********', '2022-02-06', 'Payment for storage fee of:\nwsdfgh66', 3),
(50.46, 'edo', 'C. card', '41518399********', '2022-02-06', 'Payment for the subscription to:\nnuovagara mod', 4),
(1234455.00, 'edo', 'C. card', '41518399********', '2022-02-06', 'Payment for the subscription to:\nnuv', 5),
(599.99, 'edo', 'C. card', '41518399********', '2022-02-06', 'Payment for annual fee of:\nedoardo sichelli', 6),
(599.99, 'edo', 'Banca Mediolanum', '123***567', '2022-02-06', 'Payment for annual fee of:\nedoardo sichelli', 7),
(15.00, 'edo', 'C. card', '1234567', '2022-02-07', 'Payment for annual fee of:\nedoardo sichelli', 11),
(50.46, 'edo', 'C. card', '1234567', '2022-02-08', 'Payment for the subscription to:\nnuovagara mod', 12),
(51.00, 'edo', 'C. card', '1234567', '2022-02-08', 'Payment for the subscription to:\nsss', 13);

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
(5, '2022-03-13', 61, 'coppa europa mod'),
(6, '2022-05-04', 518, 'coppa oceania'),
(8, '2022-03-17', 50.4637, 'nuovagara mod'),
(10, '2022-02-10', 51, 'sss'),
(11, '2022-02-23', 50, 'dd'),
(12, '2022-02-20', 1234455, 'nuv'),
(13, '2022-02-17', 60, 'ciaone'),
(16, '2022-02-18', 14, 'TestRace');

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
('Berto', 1, 15),
('edo', 1, 14),
('edo', 6, 13),
('edo', 8, 44),
('edo', 10, 18),
('edo', 11, 12),
('edo', 12, 12),
('user_for_test', 16, 1);

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
('emp', 'pippo', 'pluto', 'gardaland', 'dnwu', 'employee', '9dd4e461268c8034f5c8564e155c67a6'),
('user_for_test', 'l14', 'l', 'g', '45', 'member', '9dd4e461268c8034f5c8564e155c67a6');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `bank_transfer`
--
ALTER TABLE `bank_transfer`
  ADD PRIMARY KEY (`iban`,`id_member`) USING BTREE,
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
  ADD PRIMARY KEY (`card_number`,`id_member`) USING BTREE,
  ADD KEY `fkMemberCreditCard` (`id_member`);

--
-- Indici per le tabelle `membership_fee`
--
ALTER TABLE `membership_fee`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkMemberFee` (`id_member`);

--
-- Indici per le tabelle `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`,`id_member`),
  ADD KEY `fk_notif_member` (`id_member`);

--
-- Indici per le tabelle `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`,`member_id`) USING BTREE,
  ADD KEY `fk_owner` (`member_id`);

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
  ADD KEY `fk_race` (`id_race`,`id_member`,`id_boat`) USING BTREE,
  ADD KEY `fk_race_boat` (`id_boat`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT per la tabella `boat_storage_fee`
--
ALTER TABLE `boat_storage_fee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT per la tabella `membership_fee`
--
ALTER TABLE `membership_fee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT per la tabella `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT per la tabella `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT per la tabella `race`
--
ALTER TABLE `race`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

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
-- Limiti per la tabella `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `fk_notif_member` FOREIGN KEY (`id_member`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `fk_owner` FOREIGN KEY (`member_id`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `race_participation`
--
ALTER TABLE `race_participation`
  ADD CONSTRAINT `fk_member_race` FOREIGN KEY (`id_member`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_race` FOREIGN KEY (`id_race`) REFERENCES `race` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_race_boat` FOREIGN KEY (`id_boat`) REFERENCES `boat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
