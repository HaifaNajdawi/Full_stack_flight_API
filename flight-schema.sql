


CREATE TABLE `flight` (
  `id` int NOT NULL AUTO_INCREMENT,
  `flight_number` int NOT NULL,
  `airport` varchar(255) NOT NULL,
  `airline_name` varchar(255) NOT NULL,
  `tail_number` varchar(50) NOT NULL,
  `distance` int NOT NULL,
  `departure_city` varchar(50) NOT NULL,
  `arrival_city` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `departure_time` time NOT NULL,
  `arrival` time NOT NULL,
  PRIMARY KEY (`flight_number`),
  UNIQUE KEY `flight_number_UNIQUE` (`flight_number`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7458 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
