-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Dic 17, 2021 alle 15:41
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
(101, 'teddie', 9, 'Alexander'),
(102, 'dacia', 57, 'Alicia'),
(103, 'velma', 64, 'Allison'),
(104, 'fleurette', 36, 'Andrea'),
(105, 'adina', 68, 'Angela'),
(106, 'dacey', 60, 'Anna'),
(107, 'denice', 26, 'Bradley'),
(108, 'avivah', 14, 'Bryan'),
(109, 'avie', 37, 'Chad'),
(110, 'shandie', 49, 'Christine'),
(111, 'vivienne', 18, 'Cody'),
(112, 'myranda', 36, 'Courtney'),
(113, 'josephina', 28, 'Derek'),
(114, 'ninnetta', 33, 'Dustin'),
(115, 'shalna', 21, 'Edward'),
(116, 'maurita', 67, 'Erica'),
(117, 'roselia', 38, 'Gregory'),
(118, 'joete', 33, 'Jared'),
(119, 'nelly', 64, 'Jenna'),
(120, 'odille', 47, 'Jesse'),
(121, 'beret', 69, 'Jose'),
(122, 'almeta', 54, 'Julie'),
(123, 'gracia', 23, 'Katherine'),
(124, 'jewelle', 21, 'Kathryn'),
(125, 'chicky', 28, 'Katie'),
(126, 'nessa', 64, 'Kenneth'),
(127, 'ellie', 57, 'Kristen'),
(128, 'sher', 45, 'Kristin'),
(129, 'susannah', 60, 'Krystal'),
(130, 'bethena', 44, 'Lindsay'),
(131, 'clareta', 31, 'Lindsey'),
(132, 'martynne', 36, 'Lisa'),
(133, 'constancy', 47, 'Maria'),
(134, 'erika', 42, 'Mary'),
(135, 'carlee', 68, 'Paul'),
(136, 'jean', 59, 'Samuel'),
(137, 'moina', 25, 'Sara'),
(138, 'teddi', 58, 'Scott'),
(139, 'farra', 18, 'Shannon'),
(140, 'crissie', 8, 'Shawn'),
(141, 'rosina', 34, 'Tara'),
(142, 'belva', 10, 'Travis'),
(143, 'arielle', 28, 'Tyler'),
(144, 'maribelle', 13, 'Alexander'),
(145, 'athene', 37, 'Alicia'),
(146, 'kelsi', 19, 'Allison'),
(147, 'breena', 12, 'Andrea'),
(148, 'starlin', 29, 'Angela'),
(149, 'erinn', 50, 'Anna'),
(150, 'oona', 18, 'Bradley'),
(151, 'malvina', 48, 'Bryan'),
(152, 'gusta', 52, 'Chad'),
(153, 'berni', 45, 'Christine'),
(154, 'aila', 10, 'Cody'),
(155, 'joelly', 46, 'Courtney'),
(156, 'charmane', 22, 'Derek'),
(157, 'roda', 8, 'Dustin'),
(158, 'collen', 8, 'Edward'),
(159, 'clari', 22, 'Erica'),
(160, 'melba', 63, 'Gregory'),
(161, 'rhodia', 51, 'Jared'),
(162, 'corette', 57, 'Jenna'),
(163, 'hertha', 66, 'Jesse'),
(164, 'marie', 22, 'Jose'),
(165, 'jorie', 64, 'Julie'),
(166, 'adelind', 13, 'Katherine'),
(167, 'sydney', 11, 'Kathryn'),
(168, 'antonia', 70, 'Katie'),
(169, 'alicia', 27, 'Kenneth'),
(170, 'annelise', 52, 'Kristen'),
(171, 'meg', 8, 'Kristin'),
(172, 'mallorie', 67, 'Krystal'),
(173, 'rosalia', 18, 'Lindsay'),
(174, 'mufinella', 70, 'Lindsey'),
(175, 'tiffy', 48, 'Lisa'),
(176, 'rafaela', 34, 'Maria'),
(177, 'verile', 63, 'Mary'),
(178, 'marya', 30, 'Paul'),
(179, 'johnette', 54, 'Samuel'),
(180, 'henka', 62, 'Sara'),
(181, 'marianna', 43, 'Scott'),
(182, 'aloise', 65, 'Shannon'),
(183, 'shay', 49, 'Shawn'),
(184, 'marigold', 69, 'Tara'),
(185, 'maribeth', 70, 'Travis'),
(186, 'cheri', 14, 'Tyler'),
(187, 'austin', 48, 'Alexander'),
(188, 'lou', 42, 'Alicia'),
(189, 'leticia', 30, 'Allison'),
(190, 'hedwiga', 69, 'Andrea'),
(191, 'meagan', 45, 'Angela'),
(192, 'alexia', 26, 'Anna'),
(193, 'aeriela', 47, 'Bradley'),
(194, 'charlena', 64, 'Bryan'),
(195, 'evangelin', 48, 'Chad'),
(196, 'golda', 23, 'Christine'),
(197, 'gilly', 49, 'Cody'),
(198, 'margarita', 48, 'Courtney'),
(199, 'leela', 57, 'Derek'),
(200, 'jayme', 36, 'Dustin');

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
('Anna', 'Amber', 'ROBERTS', 'via Garibaldi 42', 'XKRQ0APUVMZVA2D3', 'employee', '9dd4e461268c8034f5c8564e155c67a6'),
('Bradley', 'Elizabeth', 'KING', 'via Garibaldi 29', '9VOTJ6FXF1FRV2CN', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Bryan', 'Andrew', 'HARRIS', 'via Toscana 14', '0FERJWAZZU3URHE6', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Chad', 'Timothy', 'BAKER', 'via Garibaldi 36', '5WH5LS6QPD4F7XMV', 'employee', '9dd4e461268c8034f5c8564e155c67a6'),
('Christine', 'Nicholas', 'HALL', 'via Garibaldi 25', '8G0FHPACOY03C5ME', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Cody', 'Heather', 'YOUNG', 'via Garibaldi 27', 'YSFC0Z5POURXT2JT', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Courtney', 'Nicole', 'WALKER', 'via Toscana 24', '7O1KPL7MWKA38XYH', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Derek', 'Kevin', 'SCOTT', 'via Toscana 33', '5ESW7DD9ZKYW9EL6', 'employee', '9dd4e461268c8034f5c8564e155c67a6'),
('Dustin', 'Christopher', 'JOHNSON', 'via Garibaldi 1', 'HRLHEPA5BJMB873Z', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Edward', 'Brittany', 'TURNER', 'via Toscana 43', 'NHC4QGMGXF6RBEUG', 'employee', '9dd4e461268c8034f5c8564e155c67a6'),
('Erica', 'David', 'TAYLOR', 'via Garibaldi 9', 'MENMVHNENEMN81F2', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Gregory', 'Daniel', 'MOORE', 'via Toscana 8', 'D2UFPF7DD52XYZAC', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Jared', 'Lauren', 'PEREZ', 'via Toscana 41', 'GE9CNGWU2A4EJRK5', 'employee', '9dd4e461268c8034f5c8564e155c67a6'),
('Jenna', 'Christina', 'GONZALEZ', 'via Toscana 37', 'NID8YTIC0PQVLF6V', 'employee', '9dd4e461268c8034f5c8564e155c67a6'),
('Jesse', 'Sarah', 'ROBINSON', 'via Garibaldi 19', 'WN38E11EF5YLCYAJ', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Jose', 'Jason', 'GARCIA', 'via Garibaldi 17', 'IX3W8EH9BAA3JQT3', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Julie', 'Danielle', 'PHILLIPS', 'via Garibaldi 44', 'BZGHAG2Q1YETCIU7', 'employee', '9dd4e461268c8034f5c8564e155c67a6'),
('Katherine', 'Joshua', 'MILLER', 'via Toscana 6', 'YWP4LYDBL80MQZ4W', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Kathryn', 'Steven', 'GREEN', 'via Garibaldi 34', 'LZKTKND3ZCWIJ77G', 'employee', '9dd4e461268c8034f5c8564e155c67a6'),
('Katie', 'William', 'CLARK', 'via Toscana 20', 'E3SPDF83NRCY2QWF', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Kenneth', 'Joseph', 'WHITE', 'via Garibaldi 13', '6EOVJJ1AJ6RB4HV0', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Kristen', 'Brandon', 'THOMPSON', 'via Toscana 16', 'N932C7M6UEHT657R', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Kristin', 'Thomas', 'ADAMS', 'via Toscana 35', 'E6OKE6XMXXBBIA18', 'employee', '9dd4e461268c8034f5c8564e155c67a6'),
('Krystal', 'Laura', 'MITCHELL', 'via Garibaldi 40', 'KA6U0UXSTG6NBFX2', 'employee', '9dd4e461268c8034f5c8564e155c67a6'),
('Lindsay', 'Jonathan', 'RODRIGUEZ', 'via Garibaldi 21', 'TVCPN3MN1KZP1ZGU', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Lindsey', 'Ryan', 'MARTIN', 'via Garibaldi 15', '4VGOAB9SKWVGCXGF', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Lisa', 'John', 'JACKSON', 'via Toscana 12', 'UQLOGB7P3EBGLRF1', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Maria', 'Rachel', 'CARTER', 'via Toscana 39', '2CVES0OGLLJRIMQ1', 'employee', '9dd4e461268c8034f5c8564e155c67a6'),
('Mary', 'James', 'ANDERSON', 'via Toscana 10', 'X7YB3S3WH64PWFH5', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Paul', 'Jessica', 'WILLIAMS', 'via Toscana 2', '1KR7QZI82RJS1KLE', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Samuel', 'Adam', 'WRIGHT', 'via Toscana 30', 'LTNNY5UL2X53TB5Z', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Sara', 'Michael', 'SMITH', 'via Toscana 0', 'NKEZC0G1V4P7WJKM', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Scott', 'Jennifer', 'DAVIS', 'via Garibaldi 5', 'HX9RJUYSOLKVCA71', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Shannon', 'Stephanie', 'LEWIS', 'via Toscana 22', 'SO6MV2FL7HIWLIA0', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Shawn', 'Megan', 'LOPEZ', 'via Garibaldi 31', '4A2KKKWFEFBB4VRG', 'member', '9dd4e461268c8034f5c8564e155c67a6'),
('Tara', 'Kyle', 'NELSON', 'via Garibaldi 38', 'PPDCTDBLRT6I967X', 'employee', '9dd4e461268c8034f5c8564e155c67a6'),
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=201;

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
