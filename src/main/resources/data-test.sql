-- Tabla cliente
INSERT INTO cliente (ruc, email, especializacion, licencia, nombre)
VALUES
    ('98354231024', 'cliente1@ejemplo.com', 'Farmacia', 'LIC-001', 'Juan Perez'),
    ('98354231025', 'cliente2@ejemplo.com', 'Clínica', 'LIC-002', 'Ana Lopez');

-- Tabla encuesta_entrega
INSERT INTO encuesta_entrega (id, comentarios, estado_producto, facilidad_contacto, fecha_encuesta, id_pedido, nombre_cliente, profesionalismo_personal, puntualidad_entrega)
VALUES
    (1, 'Buena entrega', 5, 4, '2024-01-01 00:00:00', 'PED-001', 'Juan Perez', 5, 5),
    (2, 'Entrega tardía', 3, 2, '2024-01-02 00:00:00', 'PED-002', 'Ana Lopez', 3, 2);

-- Tabla encuesta_recojo
INSERT INTO encuesta_recojo (id, amabilidad_personal, claridad_instrucciones, comentarios, estado_producto, fecha_encuesta, id_pedido, nombre_cliente, puntualidad_recojo)
VALUES
    (1, 5, 4, 'Proceso claro', 5, '2024-01-03 00:00:00', 'REC-001', 'Juan Perez', 5),
    (2, 3, 3, 'Instrucciones confusas', 4, '2024-01-04 00:00:00', 'REC-002', 'Ana Lopez', 3);

-- Tabla laboratorio
INSERT INTO laboratorio (ruc, direccion, nrm_registro_sanitario, telefono)
VALUES
    ('12345678901', 'Av. Ejemplo 123', 'NRM-001', '987654321'),
    ('12345678902', 'Calle Ejemplo 456', 'NRM-002', '123456789');

-- Tabla medicamento
INSERT INTO medicamento (id, cantidad, laboratorio, nombre)
VALUES
    (1, 100, 'Laboratorio A', 'Medicamento A'),
    (2, 200, 'Laboratorio B', 'Medicamento B');

-- Tabla metodo_analitico
INSERT INTO metodo_analitico (id_metodo, descripcion, documentacion_url, fecha_validacion, fiabilidad, nombre, precision)
VALUES
    (1, 'Método para análisis de pureza', 'http://example.com/doc1', '2024-01-01 00:00:00', 'Alta', 'Método A', 'Alta'),
    (2, 'Método para análisis de toxicidad', 'http://example.com/doc2', '2024-01-02 00:00:00', 'Media', 'Método B', 'Media');

-- Tabla pago
INSERT INTO pago (id_pago, estado, fecha_pago, metodo_pago, monto_total)
VALUES
    (1, 'COMPLETADO', '2024-01-01', 'CREDITO', 100.0),
    (2, 'PENDIENTE', '2024-01-02', 'TRANSFERENCIA', 150.0);

-- Tabla producto
INSERT INTO producto (id_producto, fecha_venta, nombre, stock)
VALUES
    (1, '2024-01-01', 'Producto A', 'EN_STOCK'),
    (2, '2024-01-02', 'Producto B', 'AGOTADO');

-- Tabla catalogo_precio
INSERT INTO catalogo_precio (id_precio, fecha_efectiva, precio, producto_id_producto)
VALUES
    (1, '2024-01-01', 50.0, 1),
    (2, '2024-01-02', 75.0, 2);

-- Tabla categoria_producto
INSERT INTO categoria_producto (id_categoria, nombre_categoria, producto_id_producto)
VALUES
    (1, 'Categoria 1', 1),
    (2, 'Categoria 2', 2);

-- Tabla control_calidad
INSERT INTO control_calidad (id_control, estado, fecha, resultado, cliente_ruc, producto_id_producto)
VALUES
    (1, 'APROBADO', '2024-01-01', 'Resultado A', '98354231024', 1),
    (2, 'EN_PRUEBAS', '2024-01-02', 'Resultado B', '98354231025', 2);

-- Tabla control_calidad_metodo_analitico
INSERT INTO control_calidad_metodo_analitico (control_calidad_id, metodo_analitico_id)
VALUES
    (1, 1),
    (2, 2);

-- Tabla entrega_muestra
INSERT INTO entrega_muestra (id_entrega, created_at, estado, fecha, lugar, updated_at, cliente_ruc, producto_id_producto)
VALUES
    (1, '2024-01-01 08:00:00', 'PENDIENTE', '2024-01-01', 'Ubicación A', '2024-01-01 10:00:00', '98354231024', 1),
    (2, '2024-01-02 08:00:00', 'ENTREGADO', '2024-01-02', 'Ubicación B', '2024-01-02 12:00:00', '98354231025', 2);

-- Tabla formula_desarrollada
INSERT INTO formula_desarrollada (id_formula, beneficios, diferencias, fecha_desarrollo, ingredientes_clave, nombre, producto_id)
VALUES
    (1, 'Beneficio A', 'Diferencia A', '2024-01-01 00:00:00', 'Ingrediente X', 'Fórmula A', 1),
    (2, 'Beneficio B', 'Diferencia B', '2024-01-02 00:00:00', 'Ingrediente Y', 'Fórmula B', 2);

-- Tabla no_conformidad
INSERT INTO no_conformidad (id_no_conformidad, acciones_correctivas, causa_raiz, critico, descripcion_problema, fecha_deteccion, resultado_seguimiento, entrega_muestra_id)
VALUES
    (1, 'Acción A', 'Causa A', TRUE, 'Problema A', '2024-01-01 00:00:00', 'Seguimiento A', 1),
    (2, 'Acción B', 'Causa B', FALSE, 'Problema B', '2024-01-02 00:00:00', 'Seguimiento B', 2);

-- Tabla regulacion
INSERT INTO regulacion (id_regulacion, estado_auditoria, fecha_auditoria, nombre_regulacion, producto_id_producto)
VALUES
    (1, 'COMPLETO', '2024-01-01', 'Regulación A', 1),
    (2, 'PENDIENTE', '2024-01-02', 'Regulación B', 2);

-- Tabla reporte
INSERT INTO reporte (id, enviado, estado_reporte, fecha_creacion)
VALUES
    (1, TRUE, 'APROBADO', '2024-01-01 00:00:00'),
    (2, FALSE, 'PENDIENTE', '2024-01-02 00:00:00');

-- Tabla reporte_auditoria_interna
INSERT INTO reporte_auditoria_interna (impacto, inconformidades, recomendaciones, id)
VALUES
    ('ALTO', 'Inconformidad A', 'Recomendación A', 1),
    ('MEDIO', 'Inconformidad B', 'Recomendación B', 2);

-- Tabla reporte_cumplimiento_regulatorio
INSERT INTO reporte_cumplimiento_regulatorio (acciones_correctivas, contenido_auditoria, id, producto_id)
VALUES
    ('Acción Correctiva A', 'Contenido A', 1, 1),
    ('Acción Correctiva B', 'Contenido B', 2, 2);

-- Tabla roles
INSERT INTO roles (id, name)
VALUES
    (1, 'REPRESENTANTE');

-- Tabla representante
INSERT INTO representante (dni, contraseña, email, intentos, nombre, sede_asignada, laboratorio_ruc, role_id)
VALUES
    ('12345678', '$2a$10$ToSSvVMYPdBtTA71ZcrcPOxSUQiGYh5pLroAeOEt3MTidutlyWl/C', 'representante1@ejemplo.com', 0, 'Representante A', 'Sede A', '12345678901', 1),
    ('87654321', '$2a$10$EjRlBJ83z14Z6DDfCglKOe2LomJxRT/uPZimRDgeTE/zU0FcpdJtG', 'representante2@ejemplo.com', 1, 'Representante B', 'Sede B', '12345678902', 1);

-- Tabla entrevista
INSERT INTO entrevista (id_entrevista, fecha, hora, lugar_sede, cliente_ruc, representante_dni)
VALUES
    (1, '2024-01-01', '10:00:00', 'Sede A', '98354231024', '12345678'),
    (2, '2024-01-02', '11:00:00', 'Sede B', '98354231025', '87654321');

-- Tabla login_attempt
INSERT INTO login_attempt (id, attempt_time, device, ip_address, is_suspicious, location, representante_id)
VALUES
    (1, '2024-01-01 10:00:00', 'PC', '192.168.1.1', FALSE, 'Ubicación A', '12345678'),
    (2, '2024-01-02 11:00:00', 'Laptop', '192.168.1.2', TRUE, 'Ubicación B', '87654321');

-- Tabla venta
INSERT INTO venta (id_venta, fecha, hora, pago_id_pago)
VALUES
    (1, '2024-01-01', '10:00:00', 1),
    (2, '2024-01-02', '11:00:00', 2);

-- Tabla detalle_venta
INSERT INTO detalle_venta (id_detalle_venta, cantidad, precio, cliente_ruc, producto_id_producto, id_venta)
VALUES
    (1, 2, 50.0, '98354231024', 1, 1),
    (2, 1, 75.0, '98354231025', 2, 2);

-- Tabla orden
INSERT INTO orden (id, estado, fecha_actualizacion, problema, cliente_ruc, id_detalle_venta)
VALUES
    (1, 'ENTREGADO', '2024-01-02 00:00:00', 'Problema A', '98354231024', 1),
    (2, 'PENDIENTE', '2024-01-03 00:00:00', 'Problema B', '98354231025', 2);