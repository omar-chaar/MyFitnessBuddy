-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 13, 2021 at 08:33 AM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id15060887_myfitnessbuddy`
--

-- --------------------------------------------------------

--
-- Table structure for table `burnedCalories`
--

CREATE TABLE `burnedCalories` (
  `id` int(11) NOT NULL,
  `burnedCalories` decimal(6,0) NOT NULL,
  `added_at` datetime NOT NULL DEFAULT current_timestamp(),
  `date` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `burnedCalories`
--

INSERT INTO `burnedCalories` (`id`, `burnedCalories`, `added_at`, `date`) VALUES
(1, 1500, '2021-01-06 07:55:35', '2021-01-06'),
(1, 2500, '2021-01-07 03:17:28', '2021-01-07'),
(1, 3200, '2021-01-08 07:23:10', '2021-01-08'),
(1, 4000, '2021-01-09 07:22:38', '2021-01-09'),
(1, 1500, '2021-01-10 07:55:35', '2021-01-10'),
(1, 3000, '2021-01-11 07:18:36', '2021-01-11'),
(1, 3200, '2021-01-12 07:23:10', '2021-01-12'),
(1, 1500, '2021-01-13 03:15:11', '2021-01-13'),
(4, 2500, '2021-01-13 08:21:34', '2021-01-13');

-- --------------------------------------------------------

--
-- Table structure for table `calories`
--

CREATE TABLE `calories` (
  `id` int(11) NOT NULL,
  `calories` decimal(6,0) NOT NULL,
  `added_at` datetime NOT NULL DEFAULT current_timestamp(),
  `date` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `calories`
--

INSERT INTO `calories` (`id`, `calories`, `added_at`, `date`) VALUES
(1, 1900, '2021-01-06 03:17:28', '2021-01-06'),
(1, 2500, '2021-01-07 03:09:29', '2021-01-07'),
(1, 2000, '2021-01-08 03:11:41', '2021-01-08'),
(1, 1500, '2021-01-09 07:18:36', '2021-01-09'),
(1, 3500, '2021-01-10 07:22:38', '2021-01-10'),
(1, 1500, '2021-01-11 03:15:11', '2021-01-11'),
(1, 1440, '2021-01-12 07:23:10', '2021-01-12'),
(1, 1245, '2021-01-13 07:55:35', '2021-01-13'),
(4, 2000, '2021-01-13 08:21:34', '2021-01-13');

-- --------------------------------------------------------

--
-- Table structure for table `profile`
--

CREATE TABLE `profile` (
  `age` decimal(3,0) NOT NULL,
  `sex` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `height` decimal(3,0) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `profile`
--

INSERT INTO `profile` (`age`, `sex`, `height`, `id`) VALUES
(22, 'M', 85, 1),
(22, 'M', 180, 4);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `created_at`) VALUES
(1, 'omar', '$2y$10$prMrrxtCmBh/4v3wBgty1.w2JPGqYkEQvbJQa399Ih.Y45loCjBEe', '2021-01-05 11:27:25'),
(2, 'Gabriel', '$2y$10$Crx5W8ffeo/3QiN0lMr0VOR54F.ea/cHUXyIdiNAkjSEjVco/B4Ni', '2021-01-11 20:29:47'),
(4, 'alex', '$2y$10$q8Z/jFCpgCgx.K3cGenmFOy4tp/Uk9Qzn.u69nwvLy.jdT0.qUw5S', '2021-01-13 08:19:50');

-- --------------------------------------------------------

--
-- Table structure for table `weight`
--

CREATE TABLE `weight` (
  `id` int(11) NOT NULL,
  `weight` decimal(3,0) NOT NULL,
  `added_at` datetime NOT NULL DEFAULT current_timestamp(),
  `date` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `weight`
--

INSERT INTO `weight` (`id`, `weight`, `added_at`, `date`) VALUES
(1, 86, '2021-01-06 07:23:10', '2021-01-06'),
(1, 83, '2021-01-07 02:57:57', '2021-01-07'),
(1, 84, '2021-01-08 18:12:02', '2021-01-08'),
(1, 85, '2021-01-09 02:55:39', '2021-01-09'),
(1, 82, '2021-01-10 03:06:28', '2021-01-10'),
(1, 83, '2021-01-11 03:09:29', '2021-01-11'),
(1, 80, '2021-01-12 03:11:41', '2021-01-12'),
(1, 83, '2021-01-13 03:15:11', '2021-01-13'),
(1, 82, '2021-01-13 03:17:28', '2021-01-13'),
(1, 86, '2021-01-13 07:18:36', '2021-01-13'),
(1, 83, '2021-01-13 07:22:38', '2021-01-13'),
(1, 85, '2021-01-13 07:55:35', '2021-01-13'),
(4, 85, '2021-01-13 08:20:41', '2021-01-13'),
(4, 85, '2021-01-13 08:21:34', '2021-01-13');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `burnedCalories`
--
ALTER TABLE `burnedCalories`
  ADD PRIMARY KEY (`id`,`added_at`);

--
-- Indexes for table `calories`
--
ALTER TABLE `calories`
  ADD PRIMARY KEY (`id`,`added_at`);

--
-- Indexes for table `profile`
--
ALTER TABLE `profile`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `weight`
--
ALTER TABLE `weight`
  ADD PRIMARY KEY (`id`,`added_at`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `burnedCalories`
--
ALTER TABLE `burnedCalories`
  ADD CONSTRAINT `FKtoBurnedCalories` FOREIGN KEY (`id`) REFERENCES `users` (`id`);

--
-- Constraints for table `calories`
--
ALTER TABLE `calories`
  ADD CONSTRAINT `FKtoCalories` FOREIGN KEY (`id`) REFERENCES `users` (`id`);

--
-- Constraints for table `profile`
--
ALTER TABLE `profile`
  ADD CONSTRAINT `FKtoProfile` FOREIGN KEY (`id`) REFERENCES `users` (`id`);

--
-- Constraints for table `weight`
--
ALTER TABLE `weight`
  ADD CONSTRAINT `FKtoWeight` FOREIGN KEY (`id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
