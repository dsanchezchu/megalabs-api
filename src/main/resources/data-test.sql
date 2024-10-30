-- Insertar en la tabla laboratorio
INSERT INTO laboratorio (RUC, NRM_Registro_Sanitario, Direccion, Telefono)
VALUES ('12345678901', 'NRM-001', 'Av. Ejemplo 123', '987654321')
ON CONFLICT (RUC) DO NOTHING;

-- Insertar en la tabla cliente
INSERT INTO cliente (RUC, Nombre, Licencia, Especializacion)
VALUES
    ('98354231024', 'Juan Perez', 'LIC-001', 'Farmacia'),
    ('98354231025', 'Ana Lopez', 'LIC-002', 'Clínica')
ON CONFLICT (RUC) DO NOTHING;

-- Insertar en la tabla representante
INSERT INTO representante (DNI, Nombre, Sede_Asignada, Laboratorio_RUC, Contraseña, Intentos, Email)
VALUES
    ('12345678', 'Maria Gomez', 'Sede Central', '12345678901', 'hashed_password_123', 0, 'maria.gomez@example.com'),
    ('87654321', 'Carlos Ramirez', 'Sede Norte', '12345678901', 'hashed_password_123', 0, 'carlos.ramirez@example.com')
ON CONFLICT (DNI) DO NOTHING;

-- Insertar en la tabla producto
INSERT INTO producto (ID_Producto, Fecha_Venta, Nombre, Stock)
VALUES
    (1, '2024-09-27', 'Producto A', 'EN_STOCK'),
    (2, '2024-09-27', 'Producto B', 'EN_STOCK')
ON CONFLICT (ID_Producto) DO NOTHING;

-- Insertar en la tabla formula_desarrollada relacionada con los productos existentes
INSERT INTO formula_desarrollada (nombre, beneficios, ingredientes_clave, diferencias, fecha_desarrollo, producto_id)
VALUES
    ('Fórmula Mejorada A', 'Mejora la absorción y efectividad del Producto A', 'Ingrediente X, Ingrediente Y', 'Nueva presentación y mejor biodisponibilidad', '2024-09-25', 1),
    ('Fórmula Mejorada B', 'Reduce los efectos secundarios del Producto B', 'Ingrediente A, Ingrediente B', 'Reducción de dosis sin perder efectividad', '2024-09-25', 2);


-- Insertar en la tabla categoria_producto
INSERT INTO categoria_producto (ID_Categoria, Nombre_Categoria, Producto_ID_Producto)
VALUES
    (1, 'Categoria 1', 1),
    (2, 'Categoria 2', 2)
ON CONFLICT (ID_Categoria) DO NOTHING;

-- Insertar en la tabla catalogo_precio
INSERT INTO catalogo_precio (Precio, Fecha_Efectiva, Producto_ID_Producto)
VALUES
    (50.00, '2024-09-27', 1),
    (75.00, '2024-09-27', 2)
ON CONFLICT (Producto_ID_Producto, Fecha_Efectiva) DO NOTHING;

-- Insertar en la tabla control_calidad
INSERT INTO control_calidad (Fecha, Resultado, Estado, Producto_ID_Producto, cliente_ruc)
VALUES
    ('2024-09-27', 'Eficiente', 'APROBADO', 1, '98354231024'),
    ('2024-09-27', 'Eficiente', 'EN_PRUEBAS', 2, '98354231025')
ON CONFLICT (Producto_ID_Producto, Fecha) DO NOTHING;

-- Insertar en la tabla entrega_muestra
INSERT INTO entrega_muestra (Fecha, Lugar, Estado, created_at, updated_at, Cliente_RUC, Producto_ID_Producto)
VALUES
    ('2024-09-27', 'Farmacia Ejemplo', 'ENTREGADO', '2024-09-25 14:32:00', '2024-09-27 09:00:00', '98354231024', 1)
ON CONFLICT (Cliente_RUC, Producto_ID_Producto, Fecha) DO NOTHING;

-- Insertar en la tabla no_conformidad asociada a una entrega de muestra
INSERT INTO no_conformidad (descripcion_problema, fecha_deteccion, causa_raiz, acciones_correctivas, resultado_seguimiento, critico, entrega_muestra_id)
VALUES
    ('Producto dañado durante el transporte', '2024-09-28', 'Transporte inadecuado', 'Mejorar el embalaje', 'Se han implementado cambios en el embalaje', TRUE, 1),
    ('Defecto en la formulación', '2024-09-29', 'Error en el proceso de producción', 'Ajustar los parámetros de producción', 'Se ajustaron los parámetros de producción y se evitó el defecto', FALSE, 2);

-- Insertar en la tabla pago
INSERT INTO pago (Monto_Total, Fecha_Pago, Metodo_Pago, Estado)
VALUES
    (100.00, '2024-09-27', 'CREDITO', 'COMPLETADO'),
    (150.00, '2024-09-27', 'TRANSFERENCIA', 'PENDIENTE')
ON CONFLICT (Fecha_Pago, Monto_Total) DO NOTHING;

-- Insertar en la tabla venta
INSERT INTO venta (Fecha, Hora, Pago_Id_Pago)
VALUES
    ('2024-09-27', '12:00:00', 1),
    ('2024-09-27', '13:00:00', 2)
ON CONFLICT (Fecha, Hora, Pago_Id_Pago) DO NOTHING;

-- Insertar en la tabla detalle_venta
INSERT INTO detalle_venta (ID_Detalle_Venta, ID_Venta, Producto_ID_Producto, Cliente_RUC, Cantidad, Precio)
VALUES
    (1, 1, 1, '98354231024', 2, 50.00),
    (2, 2, 2, '98354231025', 1, 75.00)
ON CONFLICT (ID_Detalle_Venta) DO NOTHING;

-- Insertar en la tabla regulacion
INSERT INTO regulacion (Nombre_Regulacion, Fecha_Auditoria, Estado_Auditoria, Producto_ID_Producto)
VALUES
    ('Regulacion A', '2024-09-27', 'COMPLETO', 1),
    ('Regulacion B', '2024-09-27', 'PENDIENTE', 2)
ON CONFLICT (Nombre_Regulacion, Producto_ID_Producto) DO NOTHING;

-- Insertar en la tabla entrevista
INSERT INTO entrevista (fecha, hora, lugar_sede, representante_dni, cliente_ruc)
VALUES
    ('2024-09-27', '12:00:00', 'Oficina Ejemplo', '12345678', '98354231024'),
    ('2024-09-27', '13:00:00', 'Oficina Ejemplo', '87654321', '98354231025')
ON CONFLICT (fecha, hora, representante_dni, cliente_ruc) DO NOTHING;
