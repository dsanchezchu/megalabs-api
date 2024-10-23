-- Insertar en la tabla laboratorio (solo un laboratorio permitido)
INSERT INTO laboratorio (RUC, NRM_Registro_Sanitario, Direccion, Telefono)
VALUES ('12345678901', 'NRM-001', 'Av. Ejemplo 123', '987654321')
ON CONFLICT (RUC) DO NOTHING;

-- Insertar en la tabla cliente
INSERT INTO cliente (RUC, Nombre, Licencia, Especializacion)
VALUES
    ('98354231024', 'Juan Perez', 'LIC-001', 'Farmacia'),
    ('98354231025', 'Ana Lopez', 'LIC-002', 'Clínica');

-- Insertar en la tabla representante
INSERT INTO representante (DNI, Nombre, Sede_Asignada, Laboratorio_RUC, Contraseña, Intentos, Email)
VALUES
    ('12345678', 'Maria Gomez', 'Sede Central', '12345678901', 'password123', 0, 'maria.gomez@example.com'),
    ('87654321', 'Carlos Ramirez', 'Sede Norte', '12345678901', 'password123', 0, 'carlos.ramirez@example.com');

-- Insertar en la tabla producto
INSERT INTO producto (ID_Producto, Fecha_Venta, Nombre, Stock)
VALUES
    (1, '2024-09-27', 'Producto A', 'EN_STOCK'),
    (2, '2024-09-27', 'Producto B', 'EN_STOCK');

-- Insertar en la tabla categoria_producto
INSERT INTO categoria_producto (ID_Categoria, Nombre_Categoria, Producto_ID_Producto)
VALUES
    (1, 'Categoria 1', 1),
    (2, 'Categoria 2', 2);

-- Insertar en la tabla catalogo_precio
INSERT INTO catalogo_precio (Precio, Fecha_Efectiva, Producto_ID_Producto)
VALUES
    (50.00, '2024-09-27', 1),
    (75.00, '2024-09-27', 2);

-- Insertar en la tabla control_calidad
INSERT INTO control_calidad (Fecha, Resultado, Estado, Producto_ID_Producto, cliente_ruc)
VALUES
    ('2024-09-27', 'Eficiente', 'APROBADO', 1, '98354231024'),
    ('2024-09-27', 'Eficiente', 'EN_PRUEBAS', 2, '98354231025' );

-- Insertar en la tabla entrega_muestra
INSERT INTO entrega_muestra (Fecha, Lugar, Estado, created_at, updated_at, Cliente_RUC, Producto_ID_Producto)
VALUES
    ('2024-09-27', 'Farmacia Ejemplo', 'ENTREGADO', '2024-09-25 14:32:00', '2024-09-27 09:00:00', '98354231024', 1);

-- Insertar en la tabla pago
INSERT INTO pago (Monto_Total, Fecha_Pago, Metodo_Pago, Estado)
VALUES
    (100.00, '2024-09-27', 'CREDITO', 'COMPLETADO'),
    (150.00, '2024-09-27', 'TRANSFERENCIA', 'PENDIENTE');

-- Insertar en la tabla venta
INSERT INTO venta (Fecha, Hora, Pago_Id_Pago)
VALUES
    ('2024-09-27', '12:00:00', 1), -- Usando el Id_Pago correspondiente
    ('2024-09-27', '13:00:00', 2); -- Usando otro Id_Pago correspondiente

-- Insertar en la tabla detalle_venta
INSERT INTO detalle_venta (ID_Detalle_Venta, ID_Venta, Producto_ID_Producto, Cliente_RUC, Cantidad, Precio)
VALUES
    (1, 1, 1, '98354231024', 2, 50.00), -- Usando IDs correspondientes
    (2, 2, 2, '98354231025', 1, 75.00); -- Usando otros IDs correspondientes

-- Insertar en la tabla regulacion
INSERT INTO regulacion (Nombre_Regulacion, Fecha_Auditoria, Estado_Auditoria, Producto_ID_Producto)
VALUES
    ('Regulacion A', '2024-09-27', 'COMPLETO', 1),
    ('Regulacion B', '2024-09-27', 'PENDIENTE', 2);

-- Insertar en la tabla entrevista
INSERT INTO entrevista (fecha, hora, lugar_sede, representante_dni, cliente_ruc)
VALUES
    ('2024-09-27', '12:00:00', 'Oficina Ejemplo', '12345678', '98354231024'),
    ('2024-09-27', '13:00:00', 'Oficina Ejemplo', '87654321', '98354231025');
