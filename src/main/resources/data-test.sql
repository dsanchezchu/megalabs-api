-- Insertar en la tabla laboratorio
INSERT INTO laboratorio (RUC, NRM_Registro_Sanitario, Direccion, Telefono)
VALUES ('12345678901', 'NRM-001', 'Av. Ejemplo 123', '987654321')
ON CONFLICT (RUC) DO NOTHING;

-- Insertar en la tabla cliente
INSERT INTO cliente (RUC, Nombre, Licencia, Especializacion)
VALUES ('98354231024', 'Juan Perez', 'LIC-001', 'Farmacia')
ON CONFLICT (RUC) DO NOTHING;

-- Insertar en la tabla representante
INSERT INTO representante (DNI, Nombre, Sede_Asignada, Laboratorio_RUC, Contraseña)
VALUES ('12345678', 'Maria Gomez', 'Sede Central', '12345678901', 'admin')
ON CONFLICT (DNI) DO NOTHING;

-- Insertar en la tabla producto
INSERT INTO producto (ID_Producto, Fecha_Venta, Nombre, Stock)
VALUES (1, '2024-09-27', 'Producto A', 'EN_STOCK')
ON CONFLICT (ID_Producto) DO NOTHING;

-- Insertar en la tabla categoria_producto
INSERT INTO categoria_Producto (ID_Categoria, Nombre_Categoria, Producto_ID_Producto)
VALUES (1, 'Categoria 1', 1)
ON CONFLICT (ID_Categoria) DO NOTHING;

-- Insertar en la tabla catalogo_precio
INSERT INTO catalogo_Precio (Precio, Fecha_Efectiva, Producto_ID_Producto)
VALUES (50.00, '2024-09-27', 1)
ON CONFLICT (Producto_ID_Producto) DO NOTHING;

-- Insertar en la tabla control_calidad
INSERT INTO control_Calidad (Fecha, Resultado, Estado, Producto_ID_Producto)
VALUES ('2024-09-27', 'Eficiente', 'APROBADO', 1)
ON CONFLICT (Producto_ID_Producto) DO NOTHING;

-- Insertar en la tabla entrega_muestra
INSERT INTO entrega_Muestra (Fecha, Lugar, Estado, Cliente_RUC, Producto_ID_Producto)
VALUES ('2024-09-27', 'Farmacia Ejemplo', 'ENTREGADO', '98354231024', 1)
ON CONFLICT (Cliente_RUC, Producto_ID_Producto) DO NOTHING;

-- Insertar en la tabla pago
INSERT INTO pago (Monto_Total, Fecha_Pago, Metodo_Pago, Estado)
VALUES (100.00, '2024-09-27', 'CREDITO', 'COMPLETADO')
ON CONFLICT DO NOTHING;

-- Insertar en la tabla venta
INSERT INTO venta (Fecha, Hora, Pago_Id_Pago)
VALUES ('2024-09-27', '12:00:00', 1) -- Usando el Id_Pago correspondiente
ON CONFLICT DO NOTHING;

-- Insertar en la tabla detalle_venta
INSERT INTO detalle_Venta (ID_Detalle_Venta, ID_Venta, Producto_ID_Producto, Cliente_RUC, Cantidad, Precio)
VALUES (1, 1, 1, '98354231024', 2, 50.00) -- Usando IDs correspondientes
ON CONFLICT (ID_Detalle_Venta) DO NOTHING;

-- Insertar en la tabla regulacion
INSERT INTO regulacion (Nombre_Regulacion, Fecha_Auditoria, Estado_Auditoria, Producto_ID_Producto)
VALUES ('Regulacion A', '2024-09-27', 'COMPLETO', 1)
ON CONFLICT (Producto_ID_Producto) DO NOTHING;

-- Insertar en la tabla entrevista
INSERT INTO entrevista (fecha, hora, lugar_sede, representante_dni, cliente_ruc)
VALUES ('2024-09-27', '12:00:00', 'Oficina Ejemplo', '12345678', '98354231024')
ON CONFLICT (representante_dni, cliente_ruc) DO NOTHING;
