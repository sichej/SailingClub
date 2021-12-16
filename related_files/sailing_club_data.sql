-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Dic 16, 2021 alle 16:37
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
(1, 'teddie\n', 58, 'Alexander'),
(2, 'dacia\n', 53, 'Alicia'),
(3, 'velma\n', 64, 'Allison'),
(4, 'fleurette\n', 32, 'Andrea'),
(5, 'adina\n', 58, 'Angela'),
(6, 'dacey\n', 25, 'Bradley'),
(7, 'denice\n', 17, 'Bryan'),
(8, 'avivah\n', 30, 'Christine'),
(9, 'avie\n', 33, 'Cody'),
(10, 'shandie\n', 10, 'Courtney'),
(11, 'vivienne\n', 51, 'Dustin'),
(12, 'myranda\n', 70, 'Erica'),
(13, 'josephina\n', 30, 'Gregory'),
(14, 'ninnetta\n', 23, 'Jesse'),
(15, 'shalna\n', 62, 'Jose'),
(16, 'maurita\n', 62, 'Katherine'),
(17, 'roselia\n', 14, 'Katie'),
(18, 'joete\n', 56, 'Kenneth'),
(19, 'nelly\n', 21, 'Kristen'),
(20, 'odille\n', 44, 'Lindsay'),
(21, 'beret\n', 67, 'Lindsey'),
(22, 'almeta\n', 66, 'Lisa'),
(23, 'gracia\n', 67, 'Mary'),
(24, 'jewelle\n', 28, 'Paul'),
(25, 'chicky\n', 28, 'Samuel'),
(26, 'nessa\n', 47, 'Sara'),
(27, 'ellie\n', 12, 'Scott'),
(28, 'sher\n', 35, 'Shannon'),
(29, 'susannah\n', 44, 'Shawn'),
(30, 'bethena\n', 52, 'Travis'),
(31, 'clareta\n', 44, 'Tyler'),
(32, 'martynne\n', 21, 'Alexander'),
(33, 'constancy\n', 10, 'Alicia'),
(34, 'erika\n', 56, 'Allison'),
(35, 'carlee\n', 63, 'Andrea'),
(36, 'jean\n', 58, 'Angela'),
(37, 'moina\n', 49, 'Bradley'),
(38, 'teddi\n', 68, 'Bryan'),
(39, 'farra\n', 65, 'Christine'),
(40, 'crissie\n', 56, 'Cody'),
(41, 'rosina\n', 26, 'Courtney'),
(42, 'belva\n', 12, 'Dustin'),
(43, 'arielle\n', 20, 'Erica'),
(44, 'maribelle\n', 51, 'Gregory'),
(45, 'athene\n', 21, 'Jesse'),
(46, 'kelsi\n', 14, 'Jose'),
(47, 'breena\n', 70, 'Katherine'),
(48, 'starlin\n', 42, 'Katie'),
(49, 'erinn\n', 50, 'Kenneth'),
(50, 'oona\n', 21, 'Kristen'),
(51, 'malvina\n', 16, 'Lindsay'),
(52, 'gusta\n', 31, 'Lindsey'),
(53, 'berni\n', 45, 'Lisa'),
(54, 'aila\n', 18, 'Mary'),
(55, 'joelly\n', 47, 'Paul'),
(56, 'charmane\n', 26, 'Samuel'),
(57, 'roda\n', 44, 'Sara'),
(58, 'collen\n', 32, 'Scott'),
(59, 'clari\n', 70, 'Shannon'),
(60, 'melba\n', 50, 'Shawn'),
(61, 'rhodia\n', 42, 'Travis'),
(62, 'corette\n', 18, 'Tyler'),
(63, 'hertha\n', 34, 'Alexander'),
(64, 'marie\n', 12, 'Alicia'),
(65, 'jorie\n', 58, 'Allison'),
(66, 'adelind\n', 18, 'Andrea'),
(67, 'sydney\n', 45, 'Angela'),
(68, 'antonia\n', 55, 'Bradley'),
(69, 'alicia\n', 38, 'Bryan'),
(70, 'annelise\n', 24, 'Christine'),
(71, 'meg\n', 12, 'Cody'),
(72, 'mallorie\n', 58, 'Courtney'),
(73, 'rosalia\n', 67, 'Dustin'),
(74, 'mufinella\n', 63, 'Erica'),
(75, 'tiffy\n', 24, 'Gregory'),
(76, 'rafaela\n', 56, 'Jesse'),
(77, 'verile\n', 23, 'Jose'),
(78, 'marya\n', 51, 'Katherine'),
(79, 'johnette\n', 8, 'Katie'),
(80, 'henka\n', 45, 'Kenneth'),
(81, 'marianna\n', 62, 'Kristen'),
(82, 'aloise\n', 54, 'Lindsay'),
(83, 'shay\n', 66, 'Lindsey'),
(84, 'marigold\n', 44, 'Lisa'),
(85, 'maribeth\n', 54, 'Mary'),
(86, 'cheri\n', 38, 'Paul'),
(87, 'austin\n', 20, 'Samuel'),
(88, 'lou\n', 50, 'Sara'),
(89, 'leticia\n', 21, 'Scott'),
(90, 'hedwiga\n', 62, 'Shannon'),
(91, 'meagan\n', 36, 'Shawn'),
(92, 'alexia\n', 55, 'Travis'),
(93, 'aeriela\n', 53, 'Tyler'),
(94, 'charlena\n', 22, 'Alexander'),
(95, 'evangelin\n', 19, 'Alicia'),
(96, 'golda\n', 37, 'Allison'),
(97, 'gilly\n', 41, 'Andrea'),
(98, 'margarita\n', 28, 'Angela'),
(99, 'leela\n', 70, 'Bradley'),
(100, 'jayme', 55, 'Bryan');

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
(1, '2020-08-21', '2021-08-21', 696, 1),
(2, '2020-03-16', '2021-03-16', 636, 2),
(3, '2021-08-15', '2022-08-15', 768, 3),
(4, '2020-02-02', '2021-02-01', 384, 4),
(5, '2021-04-25', '2022-04-25', 696, 5),
(6, '2020-06-20', '2021-06-20', 300, 6),
(7, '2021-06-16', '2022-06-16', 204, 7),
(8, '2020-02-25', '2021-02-24', 360, 8),
(9, '2021-09-16', '2022-09-16', 396, 9),
(10, '2020-06-18', '2021-06-18', 120, 10),
(11, '2020-05-08', '2021-05-08', 612, 11),
(12, '2020-07-02', '2021-07-02', 840, 12),
(13, '2020-08-05', '2021-08-05', 360, 13),
(14, '2020-09-04', '2021-09-04', 276, 14),
(15, '2020-06-04', '2021-06-04', 744, 15),
(16, '2021-05-14', '2022-05-14', 744, 16),
(17, '2021-03-29', '2022-03-29', 168, 17),
(18, '2021-05-11', '2022-05-11', 672, 18),
(19, '2020-08-13', '2021-08-13', 252, 19),
(20, '2021-07-18', '2022-07-18', 528, 20),
(21, '2021-09-07', '2022-09-07', 804, 21),
(22, '2021-07-22', '2022-07-22', 792, 22),
(23, '2020-10-29', '2021-10-29', 804, 23),
(24, '2020-05-16', '2021-05-16', 336, 24),
(25, '2020-09-10', '2021-09-10', 336, 25),
(26, '2021-08-25', '2022-08-25', 564, 26),
(27, '2020-04-27', '2021-04-27', 144, 27),
(28, '2021-01-28', '2022-01-28', 420, 28),
(29, '2021-10-13', '2022-10-13', 528, 29),
(30, '2021-05-21', '2022-05-21', 624, 30),
(31, '2020-09-21', '2021-09-21', 528, 31),
(32, '2021-07-16', '2022-07-16', 252, 32),
(33, '2020-06-23', '2021-06-23', 120, 33),
(34, '2020-06-16', '2021-06-16', 672, 34),
(35, '2020-02-29', '2021-02-28', 756, 35),
(36, '2021-12-16', '2022-12-16', 696, 36),
(37, '2020-06-27', '2021-06-27', 588, 37),
(38, '2020-03-20', '2021-03-20', 816, 38),
(39, '2021-04-06', '2022-04-06', 780, 39),
(40, '2021-11-26', '2022-11-26', 672, 40),
(41, '2021-07-16', '2022-07-16', 312, 41),
(42, '2020-06-10', '2021-06-10', 144, 42),
(43, '2021-12-15', '2022-12-15', 240, 43),
(44, '2021-06-12', '2022-06-12', 612, 44),
(45, '2020-12-09', '2021-12-09', 252, 45),
(46, '2020-10-18', '2021-10-18', 168, 46),
(47, '2021-04-26', '2022-04-26', 840, 47),
(48, '2020-02-07', '2021-02-06', 504, 48),
(49, '2021-12-18', '2022-12-18', 600, 49),
(50, '2021-07-15', '2022-07-15', 252, 50),
(51, '2020-04-05', '2021-04-05', 192, 51),
(52, '2020-07-06', '2021-07-06', 372, 52),
(53, '2020-04-02', '2021-04-02', 540, 53),
(54, '2020-05-27', '2021-05-27', 216, 54),
(55, '2021-02-09', '2022-02-09', 564, 55),
(56, '2021-02-04', '2022-02-04', 312, 56),
(57, '2021-04-17', '2022-04-17', 528, 57),
(58, '2020-05-13', '2021-05-13', 384, 58),
(59, '2020-08-12', '2021-08-12', 840, 59),
(60, '2020-09-01', '2021-09-01', 600, 60),
(61, '2021-06-02', '2022-06-02', 504, 61),
(62, '2021-07-11', '2022-07-11', 216, 62),
(63, '2020-02-08', '2021-02-07', 408, 63),
(64, '2020-12-28', '2021-12-28', 144, 64),
(65, '2020-04-16', '2021-04-16', 696, 65),
(66, '2020-02-15', '2021-02-14', 216, 66),
(67, '2021-03-26', '2022-03-26', 540, 67),
(68, '2021-01-28', '2022-01-28', 660, 68),
(69, '2021-05-19', '2022-05-19', 456, 69),
(70, '2021-07-02', '2022-07-02', 288, 70),
(71, '2020-07-30', '2021-07-30', 144, 71),
(72, '2020-01-06', '2021-01-05', 696, 72),
(73, '2020-08-14', '2021-08-14', 804, 73),
(74, '2021-08-30', '2022-08-30', 756, 74),
(75, '2021-09-07', '2022-09-07', 288, 75),
(76, '2021-01-29', '2022-01-29', 672, 76),
(77, '2021-10-04', '2022-10-04', 276, 77),
(78, '2021-09-04', '2022-09-04', 612, 78),
(79, '2021-02-12', '2022-02-12', 96, 79),
(80, '2021-10-16', '2022-10-16', 540, 80),
(81, '2020-03-05', '2021-03-05', 744, 81),
(82, '2020-10-26', '2021-10-26', 648, 82),
(83, '2020-06-15', '2021-06-15', 792, 83),
(84, '2021-08-01', '2022-08-01', 528, 84),
(85, '2020-11-28', '2021-11-28', 648, 85),
(86, '2021-01-15', '2022-01-15', 456, 86),
(87, '2021-03-15', '2022-03-15', 240, 87),
(88, '2020-05-22', '2021-05-22', 600, 88),
(89, '2021-09-08', '2022-09-08', 252, 89),
(90, '2021-10-27', '2022-10-27', 744, 90),
(91, '2020-10-13', '2021-10-13', 432, 91),
(92, '2021-10-23', '2022-10-23', 660, 92),
(93, '2020-02-01', '2021-01-31', 636, 93),
(94, '2021-04-03', '2022-04-03', 264, 94),
(95, '2021-11-28', '2022-11-28', 228, 95),
(96, '2020-11-22', '2021-11-22', 444, 96),
(97, '2021-06-15', '2022-06-15', 492, 97),
(98, '2021-11-02', '2022-11-02', 336, 98),
(99, '2021-01-13', '2022-01-13', 840, 99),
(100, '2020-02-03', '2021-02-02', 660, 100);

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
('0207504236664378', 761, '2022-01-20', 'Allison'),
('0217356724678911', 475, '2024-12-07', 'Bryan'),
('0462019509636618', 987, '2023-01-17', 'Andrea'),
('0499605176624339', 390, '2026-07-05', 'Lindsay'),
('0613642339855838', 450, '2023-10-25', 'Travis'),
('0873474227866954', 903, '2030-08-06', 'Jose'),
('0977226247586027', 954, '2027-03-26', 'Shannon'),
('0995187146890534', 737, '2024-11-21', 'Dustin'),
('1854603305609239', 118, '2024-08-16', 'Lisa'),
('2070439932245820', 655, '2023-09-01', 'Jesse'),
('2088500699744214', 726, '2024-01-18', 'Lindsey'),
('2093969946257171', 971, '2025-12-24', 'Katherine'),
('2995754383912906', 523, '2026-02-25', 'Mary'),
('4673849524137302', 554, '2023-09-19', 'Alicia'),
('4738515612440910', 842, '2024-10-25', 'Christine'),
('4911526681154687', 834, '2022-03-01', 'Bradley'),
('5794236998962956', 628, '2027-05-09', 'Alexander'),
('6203177042229619', 696, '2027-08-03', 'Samuel'),
('6369593183903493', 737, '2029-06-21', 'Courtney'),
('6461972883240160', 564, '2024-06-06', 'Katie'),
('6535629545948518', 189, '2023-03-30', 'Kenneth'),
('6559091378501159', 974, '2023-12-18', 'Erica'),
('6595741818148609', 164, '2025-01-04', 'Tyler'),
('6671538628111120', 576, '2029-04-15', 'Gregory'),
('7005421873115132', 171, '2025-05-04', 'Scott'),
('7604026766770838', 791, '2027-04-03', 'Kristen'),
('7627211542741920', 510, '2030-05-19', 'Paul'),
('7858375380641010', 221, '2030-08-15', 'Cody'),
('7908620919049083', 127, '2026-02-20', 'Angela'),
('8789149448967794', 565, '2027-02-10', 'Vanessa'),
('9022519609043812', 961, '2026-09-04', 'Sara'),
('9029946851321922', 602, '2027-11-02', 'Shawn');

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
(1, '2020-03-26', '2021-03-26', 500, 'Alexander'),
(2, '2021-02-28', '2022-02-28', 500, 'Alicia'),
(3, '2021-08-23', '2022-08-23', 500, 'Allison'),
(4, '2020-08-09', '2021-08-09', 500, 'Andrea'),
(5, '2021-06-03', '2022-06-03', 500, 'Angela'),
(6, '2020-02-07', '2021-02-06', 500, 'Bradley'),
(7, '2020-05-29', '2021-05-29', 500, 'Bryan'),
(8, '2021-04-26', '2022-04-26', 500, 'Christine'),
(9, '2020-07-25', '2021-07-25', 500, 'Cody'),
(10, '2020-07-13', '2021-07-13', 500, 'Courtney'),
(11, '2021-05-18', '2022-05-18', 500, 'Dustin'),
(12, '2020-09-11', '2021-09-11', 500, 'Erica'),
(13, '2021-05-09', '2022-05-09', 500, 'Gregory'),
(14, '2021-04-08', '2022-04-08', 500, 'Jesse'),
(15, '2020-10-26', '2021-10-26', 500, 'Jose'),
(16, '2021-05-10', '2022-05-10', 500, 'Katherine'),
(17, '2020-02-13', '2021-02-12', 500, 'Katie'),
(18, '2021-01-25', '2022-01-25', 500, 'Kenneth'),
(19, '2020-03-30', '2021-03-30', 500, 'Kristen'),
(20, '2021-09-27', '2022-09-27', 500, 'Lindsay'),
(21, '2021-06-04', '2022-06-04', 500, 'Lindsey'),
(22, '2021-06-15', '2022-06-15', 500, 'Lisa'),
(23, '2021-02-12', '2022-02-12', 500, 'Mary'),
(24, '2021-01-10', '2022-01-10', 500, 'Paul'),
(25, '2020-06-12', '2021-06-12', 500, 'Samuel'),
(26, '2021-05-26', '2022-05-26', 500, 'Sara'),
(27, '2020-01-08', '2021-01-07', 500, 'Scott'),
(28, '2020-02-04', '2021-02-03', 500, 'Shannon'),
(29, '2020-09-17', '2021-09-17', 500, 'Shawn'),
(30, '2021-04-26', '2022-04-26', 500, 'Travis'),
(31, '2020-08-22', '2021-08-22', 500, 'Tyler'),
(32, '2020-06-06', '2021-06-06', 500, 'Vanessa');

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
(1, '2024-09-22', 446),
(2, '2024-05-05', 442),
(3, '2022-09-12', 470),
(4, '2024-08-04', 333),
(5, '2023-09-07', 349),
(6, '2023-05-05', 453),
(7, '2024-07-24', 530),
(8, '2024-06-16', 465),
(9, '2025-08-21', 426),
(10, '2023-06-17', 517),
(11, '2024-09-16', 442),
(12, '2024-07-09', 566),
(13, '2023-08-17', 380),
(14, '2023-09-07', 597),
(15, '2023-05-13', 584),
(16, '2025-05-12', 625),
(17, '2025-09-01', 690),
(18, '2024-05-16', 444),
(19, '2023-06-13', 418),
(20, '2023-09-15', 497),
(21, '2022-09-06', 420),
(22, '2023-05-14', 578),
(23, '2023-08-27', 538),
(24, '2022-09-18', 667),
(25, '2023-06-09', 440);

-- --------------------------------------------------------

--
-- Struttura della tabella `race_participation`
--

CREATE TABLE `race_participation` (
  `id_member` varchar(32) NOT NULL,
  `id_race` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `race_participation`
--

INSERT INTO `race_participation` (`id_member`, `id_race`) VALUES
('Alexander', 1),
('Alexander', 2);

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
('Alexander', 'Justin', 'MARTINEZ', 'via Toscana 18', '5NPBJRHG5CPY5450', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Alicia', 'Anthony', 'ALLEN', 'via Toscana 26', 'CYOXIGLNGQXGNGO4', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Allison', 'Eric', 'HERNANDEZ', 'via Toscana 28', '468BQA3SIGVHXNSG', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Andrea', 'Amanda', 'WILSON', 'via Garibaldi 7', '2GY6LG6B41LOC5XD', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Angela', 'Matthew', 'JONES', 'via Garibaldi 3', '3UDPJCFTSGTU6WJT', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Bradley', 'Elizabeth', 'KING', 'via Garibaldi 29', '9VOTJ6FXF1FRV2CN', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Bryan', 'Andrew', 'HARRIS', 'via Toscana 14', '0FERJWAZZU3URHE6', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Christine', 'Nicholas', 'HALL', 'via Garibaldi 25', '8G0FHPACOY03C5ME', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Cody', 'Heather', 'YOUNG', 'via Garibaldi 27', 'YSFC0Z5POURXT2JT', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Courtney', 'Nicole', 'WALKER', 'via Toscana 24', '7O1KPL7MWKA38XYH', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Dustin', 'Christopher', 'JOHNSON', 'via Garibaldi 1', 'HRLHEPA5BJMB873Z', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Erica', 'David', 'TAYLOR', 'via Garibaldi 9', 'MENMVHNENEMN81F2', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Gregory', 'Daniel', 'MOORE', 'via Toscana 8', 'D2UFPF7DD52XYZAC', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Jesse', 'Sarah', 'ROBINSON', 'via Garibaldi 19', 'WN38E11EF5YLCYAJ', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Jose', 'Jason', 'GARCIA', 'via Garibaldi 17', 'IX3W8EH9BAA3JQT3', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Katherine', 'Joshua', 'MILLER', 'via Toscana 6', 'YWP4LYDBL80MQZ4W', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Katie', 'William', 'CLARK', 'via Toscana 20', 'E3SPDF83NRCY2QWF', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Kenneth', 'Joseph', 'WHITE', 'via Garibaldi 13', '6EOVJJ1AJ6RB4HV0', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Kristen', 'Brandon', 'THOMPSON', 'via Toscana 16', 'N932C7M6UEHT657R', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Lindsay', 'Jonathan', 'RODRIGUEZ', 'via Garibaldi 21', 'TVCPN3MN1KZP1ZGU', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Lindsey', 'Ryan', 'MARTIN', 'via Garibaldi 15', '4VGOAB9SKWVGCXGF', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Lisa', 'John', 'JACKSON', 'via Toscana 12', 'UQLOGB7P3EBGLRF1', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Mary', 'James', 'ANDERSON', 'via Toscana 10', 'X7YB3S3WH64PWFH5', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Paul', 'Jessica', 'WILLIAMS', 'via Toscana 2', '1KR7QZI82RJS1KLE', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Samuel', 'Adam', 'WRIGHT', 'via Toscana 30', 'LTNNY5UL2X53TB5Z', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Sara', 'Michael', 'SMITH', 'via Toscana 0', 'NKEZC0G1V4P7WJKM', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Scott', 'Jennifer', 'DAVIS', 'via Garibaldi 5', 'HX9RJUYSOLKVCA71', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Shannon', 'Stephanie', 'LEWIS', 'via Toscana 22', 'SO6MV2FL7HIWLIA0', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Shawn', 'Megan', 'LOPEZ', 'via Garibaldi 31', '4A2KKKWFEFBB4VRG', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Travis', 'Robert', 'THOMAS', 'via Garibaldi 11', 'FUGC25YLR61WY8FD', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Tyler', 'Ashley', 'BROWN', 'via Toscana 4', 'WMD9SMJTPIAFOY1Q', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Vanessa', 'Brian', 'LEE', 'via Garibaldi 23', 'RGCVCSFLZ50UO331', 'member', '9dd4e461268c8034f5c8564e155c67a6');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

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
