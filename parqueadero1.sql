-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-11-2025 a las 14:47:30
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `parqueadero1`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuadrante`
--

CREATE TABLE `cuadrante` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `tipo_vehiculo` enum('moto','carro','cicla','camioneta') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cuadrante`
--

INSERT INTO `cuadrante` (`id`, `nombre`, `tipo_vehiculo`) VALUES
(1, 'cuadrante 1', 'moto'),
(2, 'Cuadrante 2', 'carro'),
(3, 'Cuadrante 3', 'camioneta'),
(4, 'Cuadrante 4', 'cicla');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_factura`
--

CREATE TABLE `detalle_factura` (
  `id` int(11) NOT NULL,
  `id_factura` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT 1,
  `precio_unitario` decimal(10,2) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_factura`
--

INSERT INTO `detalle_factura` (`id`, `id_factura`, `cantidad`, `precio_unitario`, `subtotal`) VALUES
(1, 1, 1, 1200.00, 1200.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_vehiculo`
--

CREATE TABLE `estado_vehiculo` (
  `id` int(11) NOT NULL,
  `id_reserva` int(11) NOT NULL,
  `observaciones_entrada` text DEFAULT NULL,
  `observaciones_salida` text DEFAULT NULL,
  `estado_general` varchar(100) DEFAULT NULL,
  `fecha_ingreso` datetime DEFAULT NULL,
  `fecha_salida` datetime DEFAULT NULL,
  `foto_entrada` varchar(255) DEFAULT NULL,
  `foto_salida` varchar(255) DEFAULT NULL,
  `registrado_por` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estado_vehiculo`
--

INSERT INTO `estado_vehiculo` (`id`, `id_reserva`, `observaciones_entrada`, `observaciones_salida`, `estado_general`, `fecha_ingreso`, `fecha_salida`, `foto_entrada`, `foto_salida`, `registrado_por`) VALUES
(2, 1, 'q', 'w', 'e', '2025-10-01 14:58:38', '2025-10-02 14:58:38', NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id` int(11) NOT NULL,
  `id_reserva` int(11) NOT NULL,
  `fecha_emision` datetime NOT NULL DEFAULT current_timestamp(),
  `total` decimal(10,2) NOT NULL,
  `forma_pago` enum('Efectivo','Transferencia','Tarjeta','') NOT NULL,
  `observaciones` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`id`, `id_reserva`, `fecha_emision`, `total`, `forma_pago`, `observaciones`) VALUES
(1, 1, '2025-10-07 16:40:38', 100.00, 'Efectivo', 'd');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lugar`
--

CREATE TABLE `lugar` (
  `id` int(11) NOT NULL,
  `numero` varchar(10) NOT NULL,
  `id_cuadrante` int(11) NOT NULL,
  `disponible` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `lugar`
--

INSERT INTO `lugar` (`id`, `numero`, `id_cuadrante`, `disponible`) VALUES
(1, 'A1', 1, 1),
(2, 'A2', 1, 1),
(3, 'A3', 1, 1),
(4, 'A4', 1, 1),
(5, 'A5', 1, 1),
(6, 'A6', 1, 1),
(7, 'A7', 1, 1),
(8, 'A8', 1, 1),
(9, 'A9', 1, 1),
(10, 'A10', 1, 1),
(11, 'A11', 1, 1),
(12, 'A12', 1, 1),
(13, 'B1', 2, 1),
(14, 'B2', 2, 1),
(15, 'B3', 2, 1),
(16, 'B4', 2, 1),
(17, 'B5', 2, 1),
(18, 'B6', 2, 1),
(19, 'B7', 2, 1),
(20, 'B8', 2, 1),
(21, 'B9', 2, 1),
(22, 'B10', 2, 1),
(23, 'B11', 2, 1),
(24, 'B12', 2, 1),
(25, 'C1', 3, 1),
(26, 'C2', 3, 1),
(27, 'C3', 3, 1),
(28, 'C4', 3, 1),
(29, 'C5', 3, 1),
(30, 'C6', 3, 1),
(31, 'C7', 3, 1),
(32, 'C8', 3, 1),
(33, 'C9', 3, 1),
(34, 'C10', 3, 1),
(35, 'C11', 3, 1),
(36, 'C12', 3, 1),
(37, 'D1', 4, 1),
(38, 'D2', 4, 1),
(39, 'D3', 4, 1),
(40, 'D4', 4, 1),
(41, 'D5', 4, 1),
(42, 'D6', 4, 1),
(43, 'D7', 4, 1),
(44, 'D8', 4, 1),
(45, 'D9', 4, 1),
(46, 'D10', 4, 1),
(47, 'D11', 4, 1),
(48, 'D12', 4, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_vehiculo` int(11) NOT NULL,
  `id_lugar` int(11) NOT NULL,
  `fecha_entrada` date NOT NULL,
  `hora_entrada` time NOT NULL,
  `fecha_salida` date DEFAULT NULL,
  `hora_salida` time DEFAULT NULL,
  `estado` enum('pendiente','cancelada','finalizada','Activa','Confirmada') DEFAULT 'pendiente'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`id`, `id_usuario`, `id_vehiculo`, `id_lugar`, `fecha_entrada`, `hora_entrada`, `fecha_salida`, `hora_salida`, `estado`) VALUES
(1, 1, 1, 1, '2025-10-07', '02:14:15', '2025-10-03', '21:17:40', 'finalizada'),
(2, 1, 2, 10, '2025-10-15', '18:15:36', '2025-10-17', '19:15:36', 'cancelada'),
(3, 1, 2, 1, '2025-10-29', '16:08:00', '2025-10-29', '19:08:00', 'cancelada'),
(4, 1, 2, 1, '2025-11-04', '20:21:00', '2025-11-04', '20:21:00', 'cancelada'),
(5, 1, 2, 1, '2025-11-11', '18:15:00', '2025-11-12', '18:15:00', 'Confirmada'),
(6, 1, 5, 1, '2025-11-04', '16:44:00', '2025-11-04', '18:44:00', 'cancelada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id`, `nombre`) VALUES
(1, 'Cliente'),
(2, 'Administrador'),
(3, 'Operador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `cedula` varchar(20) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `contrasena` varchar(100) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `id_rol` int(11) NOT NULL DEFAULT 1,
  `estado` enum('Activo','Inactivo','','') NOT NULL DEFAULT 'Activo'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `cedula`, `nombre`, `apellido`, `contrasena`, `telefono`, `correo`, `id_rol`, `estado`) VALUES
(1, '1', 'juan', 'sanches', '1', '12', 'd@gmail.com', 1, 'Activo'),
(2, '2', 'johan', 'la puta del sena', '2', '132', 'nami@gmail.com', 2, 'Activo'),
(3, '3', 'j', 'd', '3', '23', 'na@gmail.com', 3, 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_vehiculo`
--

CREATE TABLE `usuario_vehiculo` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_vehiculo` int(11) NOT NULL,
  `registrado_por` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario_vehiculo`
--

INSERT INTO `usuario_vehiculo` (`id`, `id_usuario`, `id_vehiculo`, `registrado_por`) VALUES
(1, 1, 1, 1),
(2, 1, 2, 1),
(3, 1, 3, 1),
(4, 1, 4, 1),
(5, 1, 5, 1),
(6, 1, 6, 1),
(7, 1, 7, 1),
(8, 1, 8, 1),
(9, 1, 10, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE `vehiculo` (
  `id` int(11) NOT NULL,
  `numero_placa` varchar(20) NOT NULL,
  `marca_vehiculo` varchar(50) NOT NULL,
  `modelo_vehiculo` varchar(50) NOT NULL,
  `tipo_vehiculo` enum('moto','carro','cicla','camioneta') NOT NULL,
  `color` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `vehiculo`
--

INSERT INTO `vehiculo` (`id`, `numero_placa`, `marca_vehiculo`, `modelo_vehiculo`, `tipo_vehiculo`, `color`) VALUES
(1, 'BAA-03H', 'Mercedes', 'MS', 'carro', 'blanco'),
(2, 'd4', 'f5', 'f3', 'moto', 'f55'),
(3, 's34', '54s', 'd3', 'camioneta', 'fy6'),
(4, 'das', 'das', 'sadc', 'cicla', 'das'),
(5, 'da', 'da', 'da', 'moto', 'da'),
(6, 'd', 'd', 'd', 'moto', 'd'),
(7, 'dk', 'jg', 'jhg', 'cicla', 'gh'),
(8, 'juan', 'juan', 'juan', 'camioneta', 'rojo'),
(10, 'juan2', 'juan', 'juan', 'cicla', 'verde');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cuadrante`
--
ALTER TABLE `cuadrante`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `detalle_factura`
--
ALTER TABLE `detalle_factura`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_factura` (`id_factura`);

--
-- Indices de la tabla `estado_vehiculo`
--
ALTER TABLE `estado_vehiculo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_reserva` (`id_reserva`),
  ADD KEY `fk_estado_vehiculo_registrado` (`registrado_por`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_reserva` (`id_reserva`);

--
-- Indices de la tabla `lugar`
--
ALTER TABLE `lugar`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_cuadrante` (`id_cuadrante`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_vehiculo` (`id_vehiculo`),
  ADD KEY `id_lugar` (`id_lugar`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cedula` (`cedula`),
  ADD UNIQUE KEY `correo` (`correo`),
  ADD KEY `id_rol` (`id_rol`);

--
-- Indices de la tabla `usuario_vehiculo`
--
ALTER TABLE `usuario_vehiculo`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_usuario` (`id_usuario`,`id_vehiculo`),
  ADD KEY `id_vehiculo` (`id_vehiculo`),
  ADD KEY `fk_usuario_vehiculo_registrado` (`registrado_por`);

--
-- Indices de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `numero_placa` (`numero_placa`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cuadrante`
--
ALTER TABLE `cuadrante`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `detalle_factura`
--
ALTER TABLE `detalle_factura`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `estado_vehiculo`
--
ALTER TABLE `estado_vehiculo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `lugar`
--
ALTER TABLE `lugar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario_vehiculo`
--
ALTER TABLE `usuario_vehiculo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle_factura`
--
ALTER TABLE `detalle_factura`
  ADD CONSTRAINT `detalle_factura_ibfk_1` FOREIGN KEY (`id_factura`) REFERENCES `factura` (`id`);

--
-- Filtros para la tabla `estado_vehiculo`
--
ALTER TABLE `estado_vehiculo`
  ADD CONSTRAINT `estado_vehiculo_ibfk_1` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id`),
  ADD CONSTRAINT `fk_estado_vehiculo_registrado` FOREIGN KEY (`registrado_por`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id`);

--
-- Filtros para la tabla `lugar`
--
ALTER TABLE `lugar`
  ADD CONSTRAINT `lugar_ibfk_1` FOREIGN KEY (`id_cuadrante`) REFERENCES `cuadrante` (`id`);

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculo` (`id`),
  ADD CONSTRAINT `reserva_ibfk_3` FOREIGN KEY (`id_lugar`) REFERENCES `lugar` (`id`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id`);

--
-- Filtros para la tabla `usuario_vehiculo`
--
ALTER TABLE `usuario_vehiculo`
  ADD CONSTRAINT `fk_usuario_vehiculo_registrado` FOREIGN KEY (`registrado_por`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `usuario_vehiculo_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `usuario_vehiculo_ibfk_2` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculo` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
