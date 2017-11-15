-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 15, 2017 at 03:00 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `webservicedb`
--

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `username` varchar(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `isDriver` tinyint(1) NOT NULL DEFAULT '0',
  `stars` int(255) UNSIGNED NOT NULL DEFAULT '0',
  `nVoters` int(255) UNSIGNED NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`username`, `name`, `email`, `phone`, `isDriver`, `stars`, `nVoters`) VALUES
('dua', 'Orang Dua', 'dua@dua.com', '082222222222', 0, 0, 0),
('empat', 'Orang Empat', 'empat@empat.com', '084444444444', 0, 0, 0),
('lima', 'Orang Lima', 'lima@lima.com', '085555555555', 1, 7, 2),
('satu', 'Orang Satu', 'satu@satu.com', '081111111111', 1, 5, 1),
('tiga', 'Orang Tiga', 'tiga@tiga.com', '083333333333', 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `order_history`
--

CREATE TABLE `order_history` (
  `orderID` int(11) NOT NULL,
  `driverUsername` varchar(20) NOT NULL,
  `passengerUsername` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `pickingPoint` varchar(255) NOT NULL,
  `destination` varchar(255) NOT NULL,
  `rating` tinyint(4) NOT NULL,
  `comment` varchar(1024) NOT NULL,
  `isHide` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_history`
--

INSERT INTO `order_history` (`orderID`, `driverUsername`, `passengerUsername`, `date`, `pickingPoint`, `destination`, `rating`, `comment`, `isHide`) VALUES
(1001, 'satu', 'dua', '2017-11-07', 'Cisitu', 'Plesiran', 5, 'Bagus', 0),
(1002, 'lima', 'empat', '2017-11-08', 'Tubis', 'Cisitu', 4, 'Kurang cepat.', 0),
(1003, 'lima', 'tiga', '2017-11-06', 'Kanayakan', 'Plesiran', 3, 'Helmnya agak bau.', 0);

-- --------------------------------------------------------

--
-- Table structure for table `preffered_locations`
--

CREATE TABLE `preffered_locations` (
  `prefLocID` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `location` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preffered_locations`
--

INSERT INTO `preffered_locations` (`prefLocID`, `username`, `location`) VALUES
(22, 'satu', 'Cisitu'),
(23, 'lima', 'Plesiran'),
(24, 'tiga', 'Kanayakan'),
(25, 'lima', 'Cisitu');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `order_history`
--
ALTER TABLE `order_history`
  ADD PRIMARY KEY (`orderID`);

--
-- Indexes for table `preffered_locations`
--
ALTER TABLE `preffered_locations`
  ADD PRIMARY KEY (`prefLocID`),
  ADD KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `order_history`
--
ALTER TABLE `order_history`
  MODIFY `orderID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1004;
--
-- AUTO_INCREMENT for table `preffered_locations`
--
ALTER TABLE `preffered_locations`
  MODIFY `prefLocID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
