-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 10, 2018 at 11:22 PM
-- Server version: 10.1.29-MariaDB
-- PHP Version: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `firmacurierat`
--

-- --------------------------------------------------------

--
-- Table structure for table `colet`
--

CREATE TABLE `colet` (
  `Id` float NOT NULL,
  `Lungime` float NOT NULL,
  `Latime` float NOT NULL,
  `Inaltime` float NOT NULL,
  `Tip` text NOT NULL,
  `Greutate` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `colet`
--

INSERT INTO `colet` (`Id`, `Lungime`, `Latime`, `Inaltime`, `Tip`, `Greutate`) VALUES
(1, 22.5, 33.5, 104.3, 'Saltea', 33),
(2, 25.3, 1.1, 0.1, 'card', 2);

-- --------------------------------------------------------

--
-- Table structure for table `curier`
--

CREATE TABLE `curier` (
  `Id` int(11) NOT NULL,
  `Nume` text NOT NULL,
  `Prenume` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `curier`
--

INSERT INTO `curier` (`Id`, `Nume`, `Prenume`) VALUES
(1, 'Munteanu', 'Carnat'),
(2, 'Panait', 'Covrig');

-- --------------------------------------------------------

--
-- Table structure for table `destinatar`
--

CREATE TABLE `destinatar` (
  `Id` int(11) NOT NULL,
  `Nume` text NOT NULL,
  `Prenume` text NOT NULL,
  `Adresa` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `destinatar`
--

INSERT INTO `destinatar` (`Id`, `Nume`, `Prenume`, `Adresa`) VALUES
(1, 'Parjoala', 'Maria', 'Strada Garii nr Forii'),
(2, 'Valentin', 'Suleiman', 'Mnb');

-- --------------------------------------------------------

--
-- Table structure for table `expeditor`
--

CREATE TABLE `expeditor` (
  `Id` int(11) NOT NULL,
  `Nume` text NOT NULL,
  `Prenume` text NOT NULL,
  `Adresa` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `expeditor`
--

INSERT INTO `expeditor` (`Id`, `Nume`, `Prenume`, `Adresa`) VALUES
(1, 'Ganea', 'Martin', 'Strada A1 bloc A4 scara 2'),
(2, 'Munte', 'Fuck', 'Strada vvv1 bloc A5 scara 1');

-- --------------------------------------------------------

--
-- Table structure for table `livrare`
--

CREATE TABLE `livrare` (
  `IdExpeditor` int(11) NOT NULL,
  `IdDestinatar` int(11) NOT NULL,
  `IdColet` int(11) NOT NULL,
  `IdCurier` int(11) NOT NULL,
  `DataExpediere` date NOT NULL,
  `DataRidicare` date NOT NULL,
  `Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `livrare`
--

INSERT INTO `livrare` (`IdExpeditor`, `IdDestinatar`, `IdColet`, `IdCurier`, `DataExpediere`, `DataRidicare`, `Id`) VALUES
(1, 2, 1, 0, '0000-00-00', '0000-00-00', 18),
(2, 1, 1, 0, '2018-01-11', '0000-00-00', 19);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `Id` int(11) NOT NULL,
  `Username` text NOT NULL,
  `Password` text NOT NULL,
  `AccessLevel` int(11) NOT NULL,
  `ImageUrl` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`Id`, `Username`, `Password`, `AccessLevel`, `ImageUrl`) VALUES
(1, 'admin', 'pass', 5, 'https://en.gravatar.com/userimage/40775503/770f323695918e2ba179ec51e2b70e8b.jpeg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `colet`
--
ALTER TABLE `colet`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `curier`
--
ALTER TABLE `curier`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `destinatar`
--
ALTER TABLE `destinatar`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `expeditor`
--
ALTER TABLE `expeditor`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `livrare`
--
ALTER TABLE `livrare`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `colet`
--
ALTER TABLE `colet`
  MODIFY `Id` float NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `curier`
--
ALTER TABLE `curier`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `destinatar`
--
ALTER TABLE `destinatar`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `expeditor`
--
ALTER TABLE `expeditor`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `livrare`
--
ALTER TABLE `livrare`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
