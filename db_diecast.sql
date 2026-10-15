-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 15, 2026 at 12:20 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_diecast`
--

-- --------------------------------------------------------

--
-- Table structure for table `koleksi_diecast`
--

CREATE TABLE `koleksi_diecast` (
  `id_koleksi` varchar(10) NOT NULL,
  `jenis_diecast` varchar(20) NOT NULL,
  `nama_model` varchar(100) NOT NULL,
  `tahun_rilis` int NOT NULL,
  `is_treasure_hunt` tinyint(1) DEFAULT '0',
  `nomor_box` int DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `koleksi_diecast`
--

INSERT INTO `koleksi_diecast` (`id_koleksi`, `jenis_diecast`, `nama_model`, `tahun_rilis`, `is_treasure_hunt`, `nomor_box`) VALUES
('HW-01', 'HotWheels', 'Nissan Skyline GT-R R34', 2023, 1, 0),
('HW-02', 'HotWheels', 'Nissan fairlady', 2024, 0, 0),
('MG-01', 'MiniGT', 'Porsche 911 GT3 RS', 2024, 0, 601);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `koleksi_diecast`
--
ALTER TABLE `koleksi_diecast`
  ADD PRIMARY KEY (`id_koleksi`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
