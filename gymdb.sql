-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-06-2025 a las 17:56:00
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gymdb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `class_session`
--

CREATE TABLE `class_session` (
  `id` bigint(20) NOT NULL,
  `capacity` int(11) NOT NULL,
  `date_time` datetime(6) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `intensity_level` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `recurring_weekdays` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `class_session`
--

INSERT INTO `class_session` (`id`, `capacity`, `date_time`, `description`, `image_url`, `intensity_level`, `name`, `recurring_weekdays`) VALUES
(1, 10, '2025-06-23 16:00:00.000000', 'Combina pasos de baile latino (salsa, merengue, reggaetón…) con movimientos aeróbicos; una forma divertida de trabajar el cardiovascular y coordinar el cuerpo.', '/images/zumba.png', 3, 'Zumba', b'0'),
(85, 10, '2025-06-23 10:00:00.000000', 'Sesión enfocada en la flexibilidad, la respiración y el equilibrio. Ideal para mejorar la movilidad articular y reducir el estrés.', '/images/yoga.png', 2, 'Yoga', b'0'),
(86, 10, '2025-06-23 12:00:00.000000', 'Trabajo de fortalecimiento del “core” (zona abdominal y lumbar) y mejora de la postura, usando colchonetas y, opcionalmente, implementos como pelotas o bandas elásticas.', '/images/pilates.png', 3, 'Pilates', b'0'),
(87, 5, '2025-06-23 18:00:00.000000', 'Clase de ciclismo en bicicleta estática, con variaciones de resistencia e intensidad al ritmo de la música, perfecta para el cardio y la quema de calorías.', '/images/spinning.png', 5, 'Spinning', b'0'),
(88, 10, '2025-06-23 19:00:00.000000', 'Entrenamiento Intervalado de Alta Intensidad: Serie de ejercicios cortos y muy intensos seguidos de breves periodos de recuperación; maximiza el gasto calórico en poco tiempo y mejora la resistencia.', '/images/hiit.png', 5, 'HIIT ', b'0'),
(89, 8, '2025-06-23 11:00:00.000000', 'Clase que mezcla técnicas de boxeo (sin contacto) con ejercicios funcionales. Mejora la coordinación, fuerza, resistencia y libera tensiones.', '/images/boxeo.png', 4, 'Boxeo', b'0'),
(90, 8, '2025-06-24 11:00:00.000000', 'Clase que mezcla técnicas de boxeo (sin contacto) con ejercicios funcionales. Mejora la coordinación, fuerza, resistencia y libera tensiones.', '/images/boxeo.png', 4, 'Boxeo', b'0'),
(91, 8, '2025-06-25 11:00:00.000000', 'Clase que mezcla técnicas de boxeo (sin contacto) con ejercicios funcionales. Mejora la coordinación, fuerza, resistencia y libera tensiones.', '/images/boxeo.png', 4, 'Boxeo', b'0'),
(92, 8, '2025-06-26 11:00:00.000000', 'Clase que mezcla técnicas de boxeo (sin contacto) con ejercicios funcionales. Mejora la coordinación, fuerza, resistencia y libera tensiones.', '/images/boxeo.png', 4, 'Boxeo', b'0'),
(93, 8, '2025-06-27 11:00:00.000000', 'Clase que mezcla técnicas de boxeo (sin contacto) con ejercicios funcionales. Mejora la coordinación, fuerza, resistencia y libera tensiones.', '/images/boxeo.png', 4, 'Boxeo', b'0'),
(94, 8, '2025-06-30 11:00:00.000000', 'Clase que mezcla técnicas de boxeo (sin contacto) con ejercicios funcionales. Mejora la coordinación, fuerza, resistencia y libera tensiones.', '/images/boxeo.png', 4, 'Boxeo', b'0'),
(95, 8, '2025-07-01 11:00:00.000000', 'Clase que mezcla técnicas de boxeo (sin contacto) con ejercicios funcionales. Mejora la coordinación, fuerza, resistencia y libera tensiones.', '/images/boxeo.png', 4, 'Boxeo', b'0'),
(96, 8, '2025-07-02 11:00:00.000000', 'Clase que mezcla técnicas de boxeo (sin contacto) con ejercicios funcionales. Mejora la coordinación, fuerza, resistencia y libera tensiones.', '/images/boxeo.png', 4, 'Boxeo', b'0'),
(97, 8, '2025-07-03 11:00:00.000000', 'Clase que mezcla técnicas de boxeo (sin contacto) con ejercicios funcionales. Mejora la coordinación, fuerza, resistencia y libera tensiones.', '/images/boxeo.png', 4, 'Boxeo', b'0'),
(98, 8, '2025-07-04 11:00:00.000000', 'Clase que mezcla técnicas de boxeo (sin contacto) con ejercicios funcionales. Mejora la coordinación, fuerza, resistencia y libera tensiones.', '/images/boxeo.png', 4, 'Boxeo', b'0'),
(99, 10, '2025-06-24 10:00:00.000000', 'Sesión enfocada en la flexibilidad, la respiración y el equilibrio. Ideal para mejorar la movilidad articular y reducir el estrés.', '/images/yoga.png', 2, 'Yoga', b'0'),
(100, 10, '2025-06-25 10:00:00.000000', 'Sesión enfocada en la flexibilidad, la respiración y el equilibrio. Ideal para mejorar la movilidad articular y reducir el estrés.', '/images/yoga.png', 2, 'Yoga', b'0'),
(101, 10, '2025-06-26 10:00:00.000000', 'Sesión enfocada en la flexibilidad, la respiración y el equilibrio. Ideal para mejorar la movilidad articular y reducir el estrés.', '/images/yoga.png', 2, 'Yoga', b'0'),
(102, 10, '2025-06-27 10:00:00.000000', 'Sesión enfocada en la flexibilidad, la respiración y el equilibrio. Ideal para mejorar la movilidad articular y reducir el estrés.', '/images/yoga.png', 2, 'Yoga', b'0'),
(103, 10, '2025-06-30 10:00:00.000000', 'Sesión enfocada en la flexibilidad, la respiración y el equilibrio. Ideal para mejorar la movilidad articular y reducir el estrés.', '/images/yoga.png', 2, 'Yoga', b'0'),
(104, 10, '2025-07-01 10:00:00.000000', 'Sesión enfocada en la flexibilidad, la respiración y el equilibrio. Ideal para mejorar la movilidad articular y reducir el estrés.', '/images/yoga.png', 2, 'Yoga', b'0'),
(105, 10, '2025-07-02 10:00:00.000000', 'Sesión enfocada en la flexibilidad, la respiración y el equilibrio. Ideal para mejorar la movilidad articular y reducir el estrés.', '/images/yoga.png', 2, 'Yoga', b'0'),
(106, 10, '2025-07-03 10:00:00.000000', 'Sesión enfocada en la flexibilidad, la respiración y el equilibrio. Ideal para mejorar la movilidad articular y reducir el estrés.', '/images/yoga.png', 2, 'Yoga', b'0'),
(107, 10, '2025-07-04 10:00:00.000000', 'Sesión enfocada en la flexibilidad, la respiración y el equilibrio. Ideal para mejorar la movilidad articular y reducir el estrés.', '/images/yoga.png', 2, 'Yoga', b'0'),
(108, 10, '2025-06-24 16:00:00.000000', 'Combina pasos de baile latino (salsa, merengue, reggaetón…) con movimientos aeróbicos; una forma divertida de trabajar el cardiovascular y coordinar el cuerpo.', '/images/zumba.png', 3, 'Zumba', b'0'),
(109, 10, '2025-06-25 16:00:00.000000', 'Combina pasos de baile latino (salsa, merengue, reggaetón…) con movimientos aeróbicos; una forma divertida de trabajar el cardiovascular y coordinar el cuerpo.', '/images/zumba.png', 3, 'Zumba', b'0'),
(110, 10, '2025-06-26 16:00:00.000000', 'Combina pasos de baile latino (salsa, merengue, reggaetón…) con movimientos aeróbicos; una forma divertida de trabajar el cardiovascular y coordinar el cuerpo.', '/images/zumba.png', 3, 'Zumba', b'0'),
(111, 10, '2025-06-27 16:00:00.000000', 'Combina pasos de baile latino (salsa, merengue, reggaetón…) con movimientos aeróbicos; una forma divertida de trabajar el cardiovascular y coordinar el cuerpo.', '/images/zumba.png', 3, 'Zumba', b'0'),
(112, 10, '2025-06-30 16:00:00.000000', 'Combina pasos de baile latino (salsa, merengue, reggaetón…) con movimientos aeróbicos; una forma divertida de trabajar el cardiovascular y coordinar el cuerpo.', '/images/zumba.png', 3, 'Zumba', b'0'),
(113, 10, '2025-07-01 16:00:00.000000', 'Combina pasos de baile latino (salsa, merengue, reggaetón…) con movimientos aeróbicos; una forma divertida de trabajar el cardiovascular y coordinar el cuerpo.', '/images/zumba.png', 3, 'Zumba', b'0'),
(114, 10, '2025-07-02 16:00:00.000000', 'Combina pasos de baile latino (salsa, merengue, reggaetón…) con movimientos aeróbicos; una forma divertida de trabajar el cardiovascular y coordinar el cuerpo.', '/images/zumba.png', 3, 'Zumba', b'0'),
(115, 10, '2025-07-03 16:00:00.000000', 'Combina pasos de baile latino (salsa, merengue, reggaetón…) con movimientos aeróbicos; una forma divertida de trabajar el cardiovascular y coordinar el cuerpo.', '/images/zumba.png', 3, 'Zumba', b'0'),
(116, 10, '2025-07-04 16:00:00.000000', 'Combina pasos de baile latino (salsa, merengue, reggaetón…) con movimientos aeróbicos; una forma divertida de trabajar el cardiovascular y coordinar el cuerpo.', '/images/zumba.png', 3, 'Zumba', b'0'),
(117, 10, '2025-06-24 12:00:00.000000', 'Trabajo de fortalecimiento del “core” (zona abdominal y lumbar) y mejora de la postura, usando colchonetas y, opcionalmente, implementos como pelotas o bandas elásticas.', '/images/pilates.png', 3, 'Pilates', b'0'),
(118, 10, '2025-06-25 12:00:00.000000', 'Trabajo de fortalecimiento del “core” (zona abdominal y lumbar) y mejora de la postura, usando colchonetas y, opcionalmente, implementos como pelotas o bandas elásticas.', '/images/pilates.png', 3, 'Pilates', b'0'),
(119, 10, '2025-06-26 12:00:00.000000', 'Trabajo de fortalecimiento del “core” (zona abdominal y lumbar) y mejora de la postura, usando colchonetas y, opcionalmente, implementos como pelotas o bandas elásticas.', '/images/pilates.png', 3, 'Pilates', b'0'),
(120, 10, '2025-06-27 12:00:00.000000', 'Trabajo de fortalecimiento del “core” (zona abdominal y lumbar) y mejora de la postura, usando colchonetas y, opcionalmente, implementos como pelotas o bandas elásticas.', '/images/pilates.png', 3, 'Pilates', b'0'),
(121, 10, '2025-06-30 12:00:00.000000', 'Trabajo de fortalecimiento del “core” (zona abdominal y lumbar) y mejora de la postura, usando colchonetas y, opcionalmente, implementos como pelotas o bandas elásticas.', '/images/pilates.png', 3, 'Pilates', b'0'),
(122, 10, '2025-07-01 12:00:00.000000', 'Trabajo de fortalecimiento del “core” (zona abdominal y lumbar) y mejora de la postura, usando colchonetas y, opcionalmente, implementos como pelotas o bandas elásticas.', '/images/pilates.png', 3, 'Pilates', b'0'),
(123, 10, '2025-07-02 12:00:00.000000', 'Trabajo de fortalecimiento del “core” (zona abdominal y lumbar) y mejora de la postura, usando colchonetas y, opcionalmente, implementos como pelotas o bandas elásticas.', '/images/pilates.png', 3, 'Pilates', b'0'),
(124, 10, '2025-07-03 12:00:00.000000', 'Trabajo de fortalecimiento del “core” (zona abdominal y lumbar) y mejora de la postura, usando colchonetas y, opcionalmente, implementos como pelotas o bandas elásticas.', '/images/pilates.png', 3, 'Pilates', b'0'),
(125, 10, '2025-07-04 12:00:00.000000', 'Trabajo de fortalecimiento del “core” (zona abdominal y lumbar) y mejora de la postura, usando colchonetas y, opcionalmente, implementos como pelotas o bandas elásticas.', '/images/pilates.png', 3, 'Pilates', b'0'),
(126, 10, '2025-06-24 19:00:00.000000', 'Entrenamiento Intervalado de Alta Intensidad: Serie de ejercicios cortos y muy intensos seguidos de breves periodos de recuperación; maximiza el gasto calórico en poco tiempo y mejora la resistencia.', '/images/hiit.png', 5, 'HIIT ', b'0'),
(127, 10, '2025-06-25 19:00:00.000000', 'Entrenamiento Intervalado de Alta Intensidad: Serie de ejercicios cortos y muy intensos seguidos de breves periodos de recuperación; maximiza el gasto calórico en poco tiempo y mejora la resistencia.', '/images/hiit.png', 5, 'HIIT ', b'0'),
(128, 10, '2025-06-26 19:00:00.000000', 'Entrenamiento Intervalado de Alta Intensidad: Serie de ejercicios cortos y muy intensos seguidos de breves periodos de recuperación; maximiza el gasto calórico en poco tiempo y mejora la resistencia.', '/images/hiit.png', 5, 'HIIT ', b'0'),
(129, 10, '2025-06-27 19:00:00.000000', 'Entrenamiento Intervalado de Alta Intensidad: Serie de ejercicios cortos y muy intensos seguidos de breves periodos de recuperación; maximiza el gasto calórico en poco tiempo y mejora la resistencia.', '/images/hiit.png', 5, 'HIIT ', b'0'),
(130, 10, '2025-06-30 19:00:00.000000', 'Entrenamiento Intervalado de Alta Intensidad: Serie de ejercicios cortos y muy intensos seguidos de breves periodos de recuperación; maximiza el gasto calórico en poco tiempo y mejora la resistencia.', '/images/hiit.png', 5, 'HIIT ', b'0'),
(131, 10, '2025-07-01 19:00:00.000000', 'Entrenamiento Intervalado de Alta Intensidad: Serie de ejercicios cortos y muy intensos seguidos de breves periodos de recuperación; maximiza el gasto calórico en poco tiempo y mejora la resistencia.', '/images/hiit.png', 5, 'HIIT ', b'0'),
(132, 10, '2025-07-02 19:00:00.000000', 'Entrenamiento Intervalado de Alta Intensidad: Serie de ejercicios cortos y muy intensos seguidos de breves periodos de recuperación; maximiza el gasto calórico en poco tiempo y mejora la resistencia.', '/images/hiit.png', 5, 'HIIT ', b'0'),
(133, 10, '2025-07-03 19:00:00.000000', 'Entrenamiento Intervalado de Alta Intensidad: Serie de ejercicios cortos y muy intensos seguidos de breves periodos de recuperación; maximiza el gasto calórico en poco tiempo y mejora la resistencia.', '/images/hiit.png', 5, 'HIIT ', b'0'),
(134, 10, '2025-07-04 19:00:00.000000', 'Entrenamiento Intervalado de Alta Intensidad: Serie de ejercicios cortos y muy intensos seguidos de breves periodos de recuperación; maximiza el gasto calórico en poco tiempo y mejora la resistencia.', '/images/hiit.png', 5, 'HIIT ', b'0'),
(135, 5, '2025-06-24 18:00:00.000000', 'Clase de ciclismo en bicicleta estática, con variaciones de resistencia e intensidad al ritmo de la música, perfecta para el cardio y la quema de calorías.', '/images/spinning.png', 5, 'Spinning', b'0'),
(136, 5, '2025-06-25 18:00:00.000000', 'Clase de ciclismo en bicicleta estática, con variaciones de resistencia e intensidad al ritmo de la música, perfecta para el cardio y la quema de calorías.', '/images/spinning.png', 5, 'Spinning', b'0'),
(137, 5, '2025-06-26 18:00:00.000000', 'Clase de ciclismo en bicicleta estática, con variaciones de resistencia e intensidad al ritmo de la música, perfecta para el cardio y la quema de calorías.', '/images/spinning.png', 5, 'Spinning', b'0'),
(138, 5, '2025-06-27 18:00:00.000000', 'Clase de ciclismo en bicicleta estática, con variaciones de resistencia e intensidad al ritmo de la música, perfecta para el cardio y la quema de calorías.', '/images/spinning.png', 5, 'Spinning', b'0'),
(139, 5, '2025-06-30 18:00:00.000000', 'Clase de ciclismo en bicicleta estática, con variaciones de resistencia e intensidad al ritmo de la música, perfecta para el cardio y la quema de calorías.', '/images/spinning.png', 5, 'Spinning', b'0'),
(140, 5, '2025-07-01 18:00:00.000000', 'Clase de ciclismo en bicicleta estática, con variaciones de resistencia e intensidad al ritmo de la música, perfecta para el cardio y la quema de calorías.', '/images/spinning.png', 5, 'Spinning', b'0'),
(141, 5, '2025-07-02 18:00:00.000000', 'Clase de ciclismo en bicicleta estática, con variaciones de resistencia e intensidad al ritmo de la música, perfecta para el cardio y la quema de calorías.', '/images/spinning.png', 5, 'Spinning', b'0'),
(142, 5, '2025-07-03 18:00:00.000000', 'Clase de ciclismo en bicicleta estática, con variaciones de resistencia e intensidad al ritmo de la música, perfecta para el cardio y la quema de calorías.', '/images/spinning.png', 5, 'Spinning', b'0'),
(143, 5, '2025-07-04 18:00:00.000000', 'Clase de ciclismo en bicicleta estática, con variaciones de resistencia e intensidad al ritmo de la música, perfecta para el cardio y la quema de calorías.', '/images/spinning.png', 5, 'Spinning', b'0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservation`
--

CREATE TABLE `reservation` (
  `id` bigint(20) NOT NULL,
  `booked_at` datetime(6) NOT NULL,
  `class_session_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reservation`
--

INSERT INTO `reservation` (`id`, `booked_at`, `class_session_id`, `user_id`) VALUES
(6, '2025-06-23 17:37:56.000000', 90, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','CUSTOMER') DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `password`, `role`, `username`) VALUES
(1, '$2a$10$7IyJNwgesHeRlGraMPtJDeqWtkZpyuyIsDAm4zh7vncAQ0dFfO4eO', 'CUSTOMER', 'dario'),
(2, '$2a$10$MISnFAkKhb9HIFXCjYvkIekSMpcDF0eI/fumIyEaTP1Uy1pe299Ka', 'CUSTOMER', 'user'),
(52, '$2a$10$eJPzwSnsi9Hc6R2IAdUjouNF133bi/B6u4jGqaY04jFal.nDjYSMe', 'ADMIN', 'admin'),
(102, '$2a$10$B66FwEEmknpc55wtN8L5yOERdJboQ18nlfsbniGbHbYnqJ6aOaXeS', 'CUSTOMER', 'roberto');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_seq`
--

CREATE TABLE `user_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user_seq`
--

INSERT INTO `user_seq` (`next_val`) VALUES
(201);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `class_session`
--
ALTER TABLE `class_session`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKsxebdyalsquk50m840j7vov45` (`user_id`,`class_session_id`),
  ADD KEY `FK2mya47x4o9j9ltx65n1rjbka1` (`class_session_id`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `class_session`
--
ALTER TABLE `class_session`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=144;

--
-- AUTO_INCREMENT de la tabla `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK2mya47x4o9j9ltx65n1rjbka1` FOREIGN KEY (`class_session_id`) REFERENCES `class_session` (`id`),
  ADD CONSTRAINT `FKm4oimk0l1757o9pwavorj6ljg` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
