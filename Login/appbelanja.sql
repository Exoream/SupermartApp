-- phpMyAdmin SQL Dump
-- version 6.0.0-dev+20230516.e7d1ecbeae
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 22, 2023 at 12:12 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `appbelanja`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `kode` varchar(20) NOT NULL,
  `nama` varchar(45) DEFAULT NULL,
  `harga` bigint(10) DEFAULT NULL,
  `stok` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`kode`, `nama`, `harga`, `stok`) VALUES
('A012', 'Aqua', 5000, 15),
('A013', 'Sabun', 2500, 28),
('A015', 'Rinso', 2000, 10),
('A016', 'Molto', 3000, 10),
('A017', 'Daia', 2500, 8),
('A018', 'Babi', 8000, 20);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`) VALUES
('admin', 'admin'),
('budi', '123'),
('siti', 'siti'),
('123', '123'),
('zulvan', 'zulvan311'),
('hilal', '123'),
('admin', '123');

-- --------------------------------------------------------

--
-- Table structure for table `tr`
--

CREATE TABLE `tr` (
  `notransaksi` varchar(10) NOT NULL,
  `nama` varchar(45) DEFAULT NULL,
  `tanggal` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tr`
--

INSERT INTO `tr` (`notransaksi`, `nama`, `tanggal`) VALUES
('B011', 'Hilal', '2023-05-17'),
('B012', 'Hilal', '2023-05-17'),
('B013', 'Hilal', '2023-05-17'),
('B014', 'Hilal', '2023-05-17'),
('B015', 'Hilal', '2023-05-17'),
('B016', 'Hilal', '2023-05-17'),
('B017', 'Hilal', '2023-05-17'),
('B018', 'Hilal', '2023-05-17'),
('B019', 'Hilal', '2023-05-17'),
('B020', 'Hilal', '2023-05-17'),
('B021', 'Hilal', '2023-05-17'),
('B022', 'Hilal', '2023-05-17'),
('B023', 'Hilal', '2023-05-17'),
('B024', 'Hilal', '2023-05-18'),
('B025', 'Hilal', '2023-05-22'),
('B026', 'Hilal', '2023-05-22');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `no_transaksi` varchar(10) DEFAULT NULL,
  `kode` varchar(20) DEFAULT NULL,
  `nama` varchar(45) DEFAULT NULL,
  `harga` varchar(15) DEFAULT NULL,
  `jumlah` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`no_transaksi`, `kode`, `nama`, `harga`, `jumlah`) VALUES
('B025', 'A018', 'Babi', '8000', '30'),
('B026', 'A017', 'Daia', '2500', '10');

--
-- Triggers `transaksi`
--
DELIMITER $$
CREATE TRIGGER `Stok` AFTER INSERT ON `transaksi` FOR EACH ROW BEGIN
UPDATE barang SET stok=stok-new.jumlah where kode=new.kodeDELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kode`);

--
-- Indexes for table `tr`
--
ALTER TABLE `tr`
  ADD PRIMARY KEY (`notransaksi`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
