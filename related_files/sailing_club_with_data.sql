-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Dic 16, 2021 alle 12:04
-- Versione del server: 10.4.21-MariaDB
-- Versione PHP: 8.0.11

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
CREATE DATABASE IF NOT EXISTS `sailing_club` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `sailing_club`;

-- --------------------------------------------------------

--
-- Struttura della tabella `bank_transfer`
--

CREATE TABLE `bank_transfer` (
  `iban` varchar(30) NOT NULL,
  `bank` varchar(32) NOT NULL,
  `id_member` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `boat`
--

CREATE TABLE `boat` (
  `id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `length` double NOT NULL,
  `id_member` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `boat`
--

INSERT INTO `boat` (`id`, `name`, `length`, `id_member`) VALUES
(1, 'teddie\n', 33, 'Alexander\n'),
(2, 'dacia\n', 68, 'Alicia\n'),
(3, 'velma\n', 39, 'Allison\n'),
(4, 'fleurette\n', 65, 'Andrea\n'),
(5, 'adina\n', 27, 'Angela\n'),
(6, 'dacey\n', 15, 'Bradley\n'),
(7, 'denice\n', 10, 'Bryan\n'),
(8, 'avivah\n', 55, 'Christine\n'),
(9, 'avie\n', 10, 'Cody\n'),
(10, 'shandie\n', 32, 'Courtney\n'),
(11, 'vivienne\n', 22, 'Dustin\n'),
(12, 'myranda\n', 11, 'Erica\n'),
(13, 'josephina\n', 25, 'Gregory\n'),
(14, 'ninnetta\n', 45, 'Jesse\n'),
(15, 'shalna\n', 66, 'Jose\n'),
(16, 'maurita\n', 54, 'Katherine\n'),
(17, 'roselia\n', 36, 'Katie\n'),
(18, 'joete\n', 15, 'Kenneth\n'),
(19, 'nelly\n', 44, 'Kristen\n'),
(20, 'odille\n', 31, 'Lindsay\n'),
(21, 'beret\n', 24, 'Lindsey\n'),
(22, 'almeta\n', 48, 'Lisa\n'),
(23, 'gracia\n', 64, 'Mary\n'),
(24, 'jewelle\n', 49, 'Paul\n'),
(25, 'chicky\n', 60, 'Samuel\n'),
(26, 'nessa\n', 57, 'Sara\n'),
(27, 'ellie\n', 48, 'Scott\n'),
(28, 'sher\n', 30, 'Shannon\n'),
(29, 'susannah\n', 52, 'Shawn\n'),
(30, 'bethena\n', 29, 'Travis\n'),
(31, 'clareta\n', 37, 'Tyler\n'),
(32, 'martynne\n', 17, 'Alexander\n'),
(33, 'constancy\n', 63, 'Alicia\n'),
(34, 'erika\n', 13, 'Allison\n'),
(35, 'carlee\n', 44, 'Andrea\n'),
(36, 'jean\n', 21, 'Angela\n'),
(37, 'moina\n', 55, 'Bradley\n'),
(38, 'teddi\n', 57, 'Bryan\n'),
(39, 'farra\n', 10, 'Christine\n'),
(40, 'crissie\n', 55, 'Cody\n'),
(41, 'rosina\n', 49, 'Courtney\n'),
(42, 'belva\n', 20, 'Dustin\n'),
(43, 'arielle\n', 47, 'Erica\n'),
(44, 'maribelle\n', 60, 'Gregory\n'),
(45, 'athene\n', 31, 'Jesse\n'),
(46, 'kelsi\n', 8, 'Jose\n'),
(47, 'breena\n', 13, 'Katherine\n'),
(48, 'starlin\n', 53, 'Katie\n'),
(49, 'erinn\n', 54, 'Kenneth\n'),
(50, 'oona\n', 34, 'Kristen\n'),
(51, 'malvina\n', 18, 'Lindsay\n'),
(52, 'gusta\n', 38, 'Lindsey\n'),
(53, 'berni\n', 70, 'Lisa\n'),
(54, 'aila\n', 19, 'Mary\n'),
(55, 'joelly\n', 14, 'Paul\n'),
(56, 'charmane\n', 48, 'Samuel\n'),
(57, 'roda\n', 43, 'Sara\n'),
(58, 'collen\n', 18, 'Scott\n'),
(59, 'clari\n', 69, 'Shannon\n'),
(60, 'melba\n', 68, 'Shawn\n'),
(61, 'rhodia\n', 54, 'Travis\n'),
(62, 'corette\n', 62, 'Tyler\n'),
(63, 'hertha\n', 34, 'Alexander\n'),
(64, 'marie\n', 51, 'Alicia\n'),
(65, 'jorie\n', 26, 'Allison\n'),
(66, 'adelind\n', 68, 'Andrea\n'),
(67, 'sydney\n', 63, 'Angela\n'),
(68, 'antonia\n', 53, 'Bradley\n'),
(69, 'alicia\n', 49, 'Bryan\n'),
(70, 'annelise\n', 41, 'Christine\n'),
(71, 'meg\n', 59, 'Cody\n'),
(72, 'mallorie\n', 25, 'Courtney\n'),
(73, 'rosalia\n', 21, 'Dustin\n'),
(74, 'mufinella\n', 9, 'Erica\n'),
(75, 'tiffy\n', 25, 'Gregory\n'),
(76, 'rafaela\n', 47, 'Jesse\n'),
(77, 'verile\n', 10, 'Jose\n'),
(78, 'marya\n', 29, 'Katherine\n'),
(79, 'johnette\n', 68, 'Katie\n'),
(80, 'henka\n', 13, 'Kenneth\n'),
(81, 'marianna\n', 24, 'Kristen\n'),
(82, 'aloise\n', 31, 'Lindsay\n'),
(83, 'shay\n', 9, 'Lindsey\n'),
(84, 'marigold\n', 8, 'Lisa\n'),
(85, 'maribeth\n', 62, 'Mary\n'),
(86, 'cheri\n', 42, 'Paul\n'),
(87, 'austin\n', 21, 'Samuel\n'),
(88, 'lou\n', 51, 'Sara\n'),
(89, 'leticia\n', 61, 'Scott\n'),
(90, 'hedwiga\n', 31, 'Shannon\n'),
(91, 'meagan\n', 40, 'Shawn\n'),
(92, 'alexia\n', 53, 'Travis\n'),
(93, 'aeriela\n', 36, 'Tyler\n'),
(94, 'charlena\n', 9, 'Alexander\n'),
(95, 'evangelin\n', 66, 'Alicia\n'),
(96, 'golda\n', 57, 'Allison\n'),
(97, 'gilly\n', 37, 'Andrea\n'),
(98, 'margarita\n', 42, 'Angela\n'),
(99, 'leela\n', 58, 'Bradley\n'),
(100, 'jayme', 54, 'Bryan\n');

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
(1, '2021-01-26', '2022-01-26', 396, 1),
(2, '2020-11-11', '2021-11-11', 816, 2),
(3, '2021-06-15', '2022-06-15', 468, 3),
(4, '2020-06-01', '2021-06-01', 780, 4),
(5, '2020-07-27', '2021-07-27', 324, 5),
(6, '2021-12-14', '2022-12-14', 180, 6),
(7, '2021-01-13', '2022-01-13', 120, 7),
(8, '2020-12-01', '2021-12-01', 660, 8),
(9, '2021-12-27', '2022-12-27', 120, 9),
(10, '2020-04-30', '2021-04-30', 384, 10),
(11, '2020-11-24', '2021-11-24', 264, 11),
(12, '2020-08-15', '2021-08-15', 132, 12),
(13, '2021-09-09', '2022-09-09', 300, 13),
(14, '2020-07-27', '2021-07-27', 540, 14),
(15, '2021-12-22', '2022-12-22', 792, 15),
(16, '2020-09-17', '2021-09-17', 648, 16),
(17, '2021-04-20', '2022-04-20', 432, 17),
(18, '2021-08-05', '2022-08-05', 180, 18),
(19, '2021-01-13', '2022-01-13', 528, 19),
(20, '2020-01-18', '2021-01-17', 372, 20),
(21, '2021-01-10', '2022-01-10', 288, 21),
(22, '2021-04-19', '2022-04-19', 576, 22),
(23, '2020-11-20', '2021-11-20', 768, 23),
(24, '2020-12-25', '2021-12-25', 588, 24),
(25, '2020-11-16', '2021-11-16', 720, 25),
(26, '2020-02-14', '2021-02-13', 684, 26),
(27, '2020-02-05', '2021-02-04', 576, 27),
(28, '2020-12-28', '2021-12-28', 360, 28),
(29, '2020-08-08', '2021-08-08', 624, 29),
(30, '2020-11-26', '2021-11-26', 348, 30),
(31, '2020-03-16', '2021-03-16', 444, 31),
(32, '2020-01-18', '2021-01-17', 204, 32),
(33, '2021-02-20', '2022-02-20', 756, 33),
(34, '2020-11-11', '2021-11-11', 156, 34),
(35, '2021-01-04', '2022-01-04', 528, 35),
(36, '2021-12-01', '2022-12-01', 252, 36),
(37, '2020-10-20', '2021-10-20', 660, 37),
(38, '2021-04-26', '2022-04-26', 684, 38),
(39, '2021-08-22', '2022-08-22', 120, 39),
(40, '2021-09-13', '2022-09-13', 660, 40),
(41, '2020-09-20', '2021-09-20', 588, 41),
(42, '2021-06-28', '2022-06-28', 240, 42),
(43, '2020-01-07', '2021-01-06', 564, 43),
(44, '2020-07-02', '2021-07-02', 720, 44),
(45, '2021-03-03', '2022-03-03', 372, 45),
(46, '2021-02-09', '2022-02-09', 96, 46),
(47, '2020-02-19', '2021-02-18', 156, 47),
(48, '2021-10-28', '2022-10-28', 636, 48),
(49, '2020-03-21', '2021-03-21', 648, 49),
(50, '2020-11-20', '2021-11-20', 408, 50),
(51, '2021-02-23', '2022-02-23', 216, 51),
(52, '2020-02-18', '2021-02-17', 456, 52),
(53, '2021-10-13', '2022-10-13', 840, 53),
(54, '2020-05-01', '2021-05-01', 228, 54),
(55, '2021-11-15', '2022-11-15', 168, 55),
(56, '2021-03-07', '2022-03-07', 576, 56),
(57, '2021-05-04', '2022-05-04', 516, 57),
(58, '2021-10-14', '2022-10-14', 216, 58),
(59, '2021-09-21', '2022-09-21', 828, 59),
(60, '2020-02-04', '2021-02-03', 816, 60),
(61, '2021-07-22', '2022-07-22', 648, 61),
(62, '2021-04-18', '2022-04-18', 744, 62),
(63, '2020-04-08', '2021-04-08', 408, 63),
(64, '2020-10-30', '2021-10-30', 612, 64),
(65, '2020-08-24', '2021-08-24', 312, 65),
(66, '2021-09-22', '2022-09-22', 816, 66),
(67, '2020-10-10', '2021-10-10', 756, 67),
(68, '2020-08-17', '2021-08-17', 636, 68),
(69, '2020-02-04', '2021-02-03', 588, 69),
(70, '2021-10-23', '2022-10-23', 492, 70),
(71, '2020-07-04', '2021-07-04', 708, 71),
(72, '2021-05-06', '2022-05-06', 300, 72),
(73, '2021-11-12', '2022-11-12', 252, 73),
(74, '2021-06-21', '2022-06-21', 108, 74),
(75, '2021-08-12', '2022-08-12', 300, 75),
(76, '2020-03-28', '2021-03-28', 564, 76),
(77, '2020-02-12', '2021-02-11', 120, 77),
(78, '2021-10-12', '2022-10-12', 348, 78),
(79, '2020-06-25', '2021-06-25', 816, 79),
(80, '2020-05-05', '2021-05-05', 156, 80),
(81, '2020-03-10', '2021-03-10', 288, 81),
(82, '2020-06-06', '2021-06-06', 372, 82),
(83, '2020-08-26', '2021-08-26', 108, 83),
(84, '2020-02-01', '2021-01-31', 96, 84),
(85, '2021-11-24', '2022-11-24', 744, 85),
(86, '2021-04-04', '2022-04-04', 504, 86),
(87, '2020-08-05', '2021-08-05', 252, 87),
(88, '2020-09-24', '2021-09-24', 612, 88),
(89, '2020-05-23', '2021-05-23', 732, 89),
(90, '2020-07-26', '2021-07-26', 372, 90),
(91, '2021-03-28', '2022-03-28', 480, 91),
(92, '2020-03-10', '2021-03-10', 636, 92),
(93, '2021-10-25', '2022-10-25', 432, 93),
(94, '2021-10-09', '2022-10-09', 108, 94),
(95, '2021-10-15', '2022-10-15', 792, 95),
(96, '2020-01-26', '2021-01-25', 684, 96),
(97, '2021-05-19', '2022-05-19', 444, 97),
(98, '2020-06-12', '2021-06-12', 504, 98),
(99, '2021-11-26', '2022-11-26', 696, 99),
(100, '2021-10-23', '2022-10-23', 648, 100);

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
('0586908858960269', 746, '2030-12-25', 'Scott\n'),
('1014492092923658', 451, '2024-07-01', 'Mary\n'),
('1117813382948692', 523, '2022-10-12', 'Bradley\n'),
('1436974694698137', 906, '2028-08-25', 'Kristen\n'),
('1551730343871062', 557, '2025-12-30', 'Dustin\n'),
('1597331426379587', 472, '2027-08-02', 'Jesse\n'),
('1966156844777671', 649, '2024-06-23', 'Allison\n'),
('1985536962518406', 528, '2024-08-22', 'Cody\n'),
('2000631841033309', 327, '2024-01-31', 'Shawn\n'),
('2058462608829800', 543, '2027-04-15', 'Sara\n'),
('2225018024591892', 428, '2030-10-28', 'Alicia\n'),
('3119810931687333', 587, '2027-03-13', 'Christine\n'),
('3236402099776729', 717, '2026-02-26', 'Angela\n'),
('3832740042873168', 882, '2026-01-27', 'Andrea\n'),
('3894466334869288', 221, '2030-09-10', 'Travis\n'),
('4060084954902752', 154, '2027-08-20', 'Katie\n'),
('4357218718809664', 993, '2029-11-12', 'Kenneth\n'),
('5749104470476147', 667, '2029-10-15', 'Vanessa\n'),
('6112489553983077', 826, '2026-09-24', 'Shannon\n'),
('6822763517141841', 514, '2027-11-20', 'Lisa\n'),
('6947251878312025', 437, '2029-12-17', 'Erica\n'),
('6969745443586358', 587, '2023-12-18', 'Gregory\n'),
('7008811227450364', 694, '2023-10-29', 'Alexander\n'),
('7084666632071572', 810, '2023-08-31', 'Paul\n'),
('7303929352741826', 665, '2029-10-09', 'Lindsey\n'),
('7597024701159605', 777, '2029-06-24', 'Lindsay\n'),
('7810288320708777', 279, '2023-03-03', 'Katherine\n'),
('7934534059199502', 804, '2025-07-21', 'Tyler\n'),
('8942433252214903', 827, '2025-12-18', 'Jose\n'),
('8985787400381483', 480, '2027-04-27', 'Samuel\n'),
('9448733560566471', 824, '2027-01-22', 'Courtney\n'),
('9568841395736365', 825, '2022-04-13', 'Bryan\n');

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
(1, '2021-10-29', '2022-10-29', 500, 'Alexander\n'),
(2, '2021-07-01', '2022-07-01', 500, 'Alicia\n'),
(3, '2020-07-29', '2021-07-29', 500, 'Allison\n'),
(4, '2020-08-08', '2021-08-08', 500, 'Andrea\n'),
(5, '2020-03-23', '2021-03-23', 500, 'Angela\n'),
(6, '2020-06-17', '2021-06-17', 500, 'Bradley\n'),
(7, '2020-06-20', '2021-06-20', 500, 'Bryan\n'),
(8, '2021-08-06', '2022-08-06', 500, 'Christine\n'),
(9, '2021-03-26', '2022-03-26', 500, 'Cody\n'),
(10, '2020-02-13', '2021-02-12', 500, 'Courtney\n'),
(11, '2020-12-23', '2021-12-23', 500, 'Dustin\n'),
(12, '2020-04-03', '2021-04-03', 500, 'Erica\n'),
(13, '2020-09-26', '2021-09-26', 500, 'Gregory\n'),
(14, '2020-07-18', '2021-07-18', 500, 'Jesse\n'),
(15, '2021-04-23', '2022-04-23', 500, 'Jose\n'),
(16, '2020-06-26', '2021-06-26', 500, 'Katherine\n'),
(17, '2020-02-11', '2021-02-10', 500, 'Katie\n'),
(18, '2021-03-05', '2022-03-05', 500, 'Kenneth\n'),
(19, '2020-11-08', '2021-11-08', 500, 'Kristen\n'),
(20, '2020-08-17', '2021-08-17', 500, 'Lindsay\n'),
(21, '2021-06-18', '2022-06-18', 500, 'Lindsey\n'),
(22, '2021-08-29', '2022-08-29', 500, 'Lisa\n'),
(23, '2021-12-02', '2022-12-02', 500, 'Mary\n'),
(24, '2021-04-22', '2022-04-22', 500, 'Paul\n'),
(25, '2020-01-10', '2021-01-09', 500, 'Samuel\n'),
(26, '2021-07-06', '2022-07-06', 500, 'Sara\n'),
(27, '2020-03-14', '2021-03-14', 500, 'Scott\n'),
(28, '2020-10-26', '2021-10-26', 500, 'Shannon\n'),
(29, '2020-02-15', '2021-02-14', 500, 'Shawn\n'),
(30, '2020-05-24', '2021-05-24', 500, 'Travis\n'),
(31, '2021-02-21', '2022-02-21', 500, 'Tyler\n'),
(32, '2020-08-14', '2021-08-14', 500, 'Vanessa\n');

-- --------------------------------------------------------

--
-- Struttura della tabella `race`
--

CREATE TABLE `race` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `race`
--

INSERT INTO `race` (`id`, `date`, `price`) VALUES
(22, '2022-08-27', 513),
(23, '2023-09-09', 383),
(24, '2022-09-28', 450),
(25, '2023-06-20', 386),
(26, '2025-06-01', 407),
(27, '2023-05-23', 417),
(28, '2025-06-02', 609),
(29, '2022-06-08', 506),
(30, '2023-05-28', 411),
(31, '2025-05-28', 535),
(32, '2024-07-01', 408),
(33, '2025-05-04', 641),
(34, '2025-09-20', 379),
(35, '2024-08-27', 385),
(36, '2022-07-27', 589),
(37, '2024-09-18', 419),
(38, '2025-08-24', 566),
(39, '2025-06-15', 378),
(40, '2023-08-17', 679),
(41, '2023-08-20', 641),
(42, '2022-06-14', 384),
(43, '2022-07-01', 630),
(44, '2022-06-23', 597),
(45, '2022-05-25', 528),
(46, '2025-09-18', 524);

-- --------------------------------------------------------

--
-- Struttura della tabella `race_participation`
--

CREATE TABLE `race_participation` (
  `id_member` varchar(32) NOT NULL,
  `id_race` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
('Alexander\n', 'Justin\n', 'MARTINEZ\n', 'via Toscana 18', '6MT5C36KWZL0PD0M', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Alicia\n', 'Anthony\n', 'ALLEN\n', 'via Toscana 26', 'LZ5JI645QDZHVUIX', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Allison\n', 'Eric\n', 'HERNANDEZ\n', 'via Toscana 28', 'TRRIED945KNUG74K', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Andrea\n', 'Amanda\n', 'WILSON\n', 'via Garibaldi 7', '86J7PJN0R44BDY7A', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Angela\n', 'Matthew\n', 'JONES\n', 'via Garibaldi 3', 'JJRSOMT7DSOASX2K', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Bradley\n', 'Elizabeth\n', 'KING\n', 'via Garibaldi 29', 'MXMR4O1ISSHV676R', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Bryan\n', 'Andrew\n', 'HARRIS\n', 'via Toscana 14', 'DGMR0O5Y3AZ4WL8U', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Christine\n', 'Nicholas\n', 'HALL\n', 'via Garibaldi 25', '7FZG3BDRRWVDHL9Z', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Cody\n', 'Heather\n', 'YOUNG\n', 'via Garibaldi 27', '12EXLUQ0OUS1ESSB', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Courtney\n', 'Nicole\n', 'WALKER\n', 'via Toscana 24', 'QS6O60YNZ98YH4AN', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Dustin\n', 'Christopher\n', 'JOHNSON\n', 'via Garibaldi 1', 'BHN5QVMMYARK1LES', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Erica\n', 'David\n', 'TAYLOR\n', 'via Garibaldi 9', 'NYW15F1AYNJR9SBM', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Gregory\n', 'Daniel\n', 'MOORE\n', 'via Toscana 8', 'N1LG59ZUCDI8G5JN', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Jesse\n', 'Sarah\n', 'ROBINSON\n', 'via Garibaldi 19', 'JQYDIJDJU17OMU16', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Jose\n', 'Jason\n', 'GARCIA\n', 'via Garibaldi 17', '5HVN8V842ZBXWRPE', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Katherine\n', 'Joshua\n', 'MILLER\n', 'via Toscana 6', '6TIXG80LYYACZXXD', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Katie\n', 'William\n', 'CLARK\n', 'via Toscana 20', 'P545XE5N3HHUW9PI', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Kenneth\n', 'Joseph\n', 'WHITE\n', 'via Garibaldi 13', 'V6YV6R8VO5GSOSRV', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Kristen\n', 'Brandon\n', 'THOMPSON\n', 'via Toscana 16', 'S1NBOV7WILQ4EVRX', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Lindsay\n', 'Jonathan\n', 'RODRIGUEZ\n', 'via Garibaldi 21', 'ECS3LV9WII7M98G0', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Lindsey\n', 'Ryan\n', 'MARTIN\n', 'via Garibaldi 15', 'RTOG1YTW0QLLA6QK', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Lisa\n', 'John\n', 'JACKSON\n', 'via Toscana 12', 'RFL29KWO7ZG9ZQWC', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Mary\n', 'James\n', 'ANDERSON\n', 'via Toscana 10', 'QCPG3Z1JKQD764L0', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Paul\n', 'Jessica\n', 'WILLIAMS\n', 'via Toscana 2', 'WQF1D0XM8MZX3HBW', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Samuel\n', 'Adam\n', 'WRIGHT\n', 'via Toscana 30', 'LNE4BJDGO62WD53U', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Sara\n', 'Michael\n', 'SMITH\n', 'via Toscana 0', '7HSR3INZMZV0Q0SG', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Scott\n', 'Jennifer\n', 'DAVIS\n', 'via Garibaldi 5', '25B10DT0L1G64OX5', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Shannon\n', 'Stephanie\n', 'LEWIS\n', 'via Toscana 22', 'ERG6SVMOGOD4XTX1', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Shawn\n', 'Megan\n', 'LOPEZ\n', 'via Garibaldi 31', '90OLZ11UZ3HEZY2K', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Travis\n', 'Robert\n', 'THOMAS\n', 'via Garibaldi 11', 'MXV2X8HOA8HDR8Z9', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Tyler\n', 'Ashley\n', 'BROWN\n', 'via Toscana 4', 'CHSKOPP3O31DHMXW', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Vanessa\n', 'Brian\n', 'LEE\n', 'via Garibaldi 23', 'XU6YDDZ2J5KVXS1V', 'member', '9dd4e461268c8034f5c8564e155c67a6');

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
  ADD PRIMARY KEY (`id_member`,`id_race`),
  ADD KEY `fkRace` (`id_race`),
  ADD KEY `fkMemberRace` (`id_member`);

--
-- Indici per le tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`,`user_type`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `boat`
--
ALTER TABLE `boat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT per la tabella `boat_storage_fee`
--
ALTER TABLE `boat_storage_fee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT per la tabella `membership_fee`
--
ALTER TABLE `membership_fee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT per la tabella `race`
--
ALTER TABLE `race`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

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
  ADD CONSTRAINT `fkMemberRace` FOREIGN KEY (`id_member`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fkRace` FOREIGN KEY (`id_race`) REFERENCES `race` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
