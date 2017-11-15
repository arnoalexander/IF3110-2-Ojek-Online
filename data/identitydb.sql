-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 15, 2017 at 02:59 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `identitydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `username` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `expired_t` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `isDriver` tinyint(1) NOT NULL,
  `stars` int(11) NOT NULL,
  `nVoters` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`username`, `password`, `token`, `expired_t`, `name`, `email`, `phone`, `isDriver`, `stars`, `nVoters`) VALUES
('dua', 'dua2', 'dua_token', 0, 'Orang Dua', 'dua@dua.com', '082222222222', 0, 0, 0),
('empat', 'empat4', 'empat_token', 0, 'Orang Empat', 'empat@empat.com', '084444444444', 0, 0, 0),
('lima', 'lima5', 'lima_token', 0, 'Orang Lima', 'lima@lima.com', '085555555555', 5, 7, 2),
('satu', 'satu1', 'satu_token', 0, 'Orang Satu', 'satu@satu.com', '081111111111', 1, 5, 1),
('tiga', 'tiga3', 'tiga_token', 0, 'Orang Tiga', 'tiga@tiga.com', '083333333333', 3, 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
