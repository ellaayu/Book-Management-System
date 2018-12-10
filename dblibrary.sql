-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- 생성 시간: 18-12-10 14:38
-- 서버 버전: 10.1.36-MariaDB
-- PHP 버전: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 데이터베이스: `dblibrary`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `book`
--

CREATE TABLE `book` (
  `id_book` varchar(11) NOT NULL,
  `name_book` text NOT NULL,
  `writer_book` text NOT NULL,
  `year_book` text NOT NULL,
  `publisher_book` text NOT NULL,
  `date_come_book` date NOT NULL,
  `id_card` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 테이블의 덤프 데이터 `book`
--

INSERT INTO `book` (`id_book`, `name_book`, `writer_book`, `year_book`, `publisher_book`, `date_come_book`, `id_card`) VALUES
('01', 'Information is Beautiful', 'David McCandless', '2009', 'HarperCollins', '2017-01-21', '001'),
('02', 'The Information: A History, a Theory, a Flood', 'James Gleick', '2011', 'Pantheon Books (US), HarperCollins (UK)', '2018-11-11', '002'),
('03', 'Isaac', 'James', '2013', 'Publishme', '2012-04-04', '001'),
('04', 'The Organization of Information (Library and Information Science Text Series) ', 'Arlene G. Taylor', '2013', ' Libraries Unlimited', '2014-12-01', ''),
('05', 'How Librarians and Cybrarians Can Save Us All', ' Marilyn Johnson ', '2010', 'Harper Tim', '2010-11-11', '');

-- --------------------------------------------------------

--
-- 테이블 구조 `borrow`
--

CREATE TABLE `borrow` (
  `id_borrow` int(11) NOT NULL,
  `user_name` text NOT NULL,
  `book_name` text NOT NULL,
  `date_out` date NOT NULL,
  `date_back` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 테이블의 덤프 데이터 `borrow`
--

INSERT INTO `borrow` (`id_borrow`, `user_name`, `book_name`, `date_out`, `date_back`) VALUES
(2, 'Lita', 'The Information: A History, a Theory, a Flood', '2016-07-31', '2016-08-12'),
(1, 'Ella', 'Information is Beautiful', '2012-12-14', '2012-12-15'),
(2, 'Lita', 'Isaac', '2012-12-16', '2012-12-18'),
(1, 'Ella', 'The Organization of Information (Library and Information Science Text Series) ', '2017-12-12', '2017-12-15'),
(2, 'Lita', 'The Organization of Information (Library and Information Science Text Series) ', '2011-12-12', '2011-12-14'),
(1, 'Ella', 'How Librarians and Cybrarians Can Save Us All', '2016-09-09', '2016-09-19'),
(1, 'Ella', 'How Librarians and Cybrarians Can Save Us All', '2012-12-23', '2013-01-04');

-- --------------------------------------------------------

--
-- 테이블 구조 `user`
--

CREATE TABLE `user` (
  `id_card` varchar(10) NOT NULL,
  `name_user` text NOT NULL,
  `status_user` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 테이블의 덤프 데이터 `user`
--

INSERT INTO `user` (`id_card`, `name_user`, `status_user`) VALUES
('001', 'Ella', 'Student'),
('002', 'Lita', 'Professor');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
