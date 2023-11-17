--Lo divido en grupos para que se entienda la asociación.
INSERT INTO seg_org_modulos
(n_id_modulo, n_id_nivel, desc_modulo, n_id_modulo_padre, menu, menu_desc, menu_url, menu_pos)
VALUES
(-1, 1, 'No parent', NULL, 'N', NULL, NULL, 0),
(1, 1, 'Sistema de Firma Elecrónica', -1, 'N', NULL, NULL, 1);

INSERT INTO seg_org_modulos /*Se puede omitir el uso de menu_desc*/
(n_id_modulo, n_id_nivel, desc_modulo, n_id_modulo_padre, menu, menu_desc, menu_url, menu_pos)
VALUES
(2, 2, 'Configuración', 1, 'S', 'Configuración', '/configuracion', 1),
(3, 4, 'Privacidad y seguridad', 2, 'S', 'Privacidad y seguridad', '/configuracion/privacidad-seguridad', 1),
(4, 4, 'Apariencia', 2, 'S', 'Apariencia', '/configuracion/apariencia', 2);

INSERT INTO seg_org_modulos
(n_id_modulo, n_id_nivel, desc_modulo, n_id_modulo_padre, menu, menu_desc, menu_url, menu_pos)
VALUES
(5, 2, 'Documentos', 1, 'S', 'Documentos', '/documentos', 2),
(6, 3, 'Mis Documentos', 5, 'S', 'Mis Documentos', '/documentos/mis-documentos', 1),
(7, 4, 'Seguimiento', 6, 'S', 'Seguimiento', '/documentos/seguimiento', 1),
(8, 4, 'Informe Panorámico', 6, 'S', 'Informe Panorámico', '/documentos/seguimiento/informe-panoramico', 2),
(9, 4, 'Faltantes', 6, 'S', 'Faltantes', '/documentos/seguimiento/faltantes', 3),
(10, 4, 'Completados', 6, 'S', 'Completados', '/documentos/seguimiento/completados', 4);


-----------------------
/*Sistema de Firma Elecrónica*/
INSERT INTO seg_org_roles_modulos(n_id_rol, n_id_modulo, 
`create`, `read`, `update`, `delete`, publico, n_session_id)
VALUES(1, 1, 'S', 'S', 'S', 'S', 'N', NULL); 
/*Configuración*/
INSERT INTO seg_org_roles_modulos(n_id_rol, n_id_modulo, 
`create`, `read`, `update`, `delete`, publico, n_session_id)
VALUES(1, 2, 'S', 'S', 'S', 'S', 'N', NULL);
/*Privacidad y seguridad*/
INSERT INTO seg_org_roles_modulos(n_id_rol, n_id_modulo, 
`create`, `read`, `update`, `delete`, publico, n_session_id)
VALUES(1, 3, 'S', 'S', 'S', 'S', 'N', NULL);
/*Apariencia*/
INSERT INTO seg_org_roles_modulos(n_id_rol, n_id_modulo, 
`create`, `read`, `update`, `delete`, publico, n_session_id)
VALUES(1, 4, 'S', 'S', 'S', 'S', 'N', NULL);
/*Documentos*/
INSERT INTO seg_org_roles_modulos(n_id_rol, n_id_modulo, 
`create`, `read`, `update`, `delete`, publico, n_session_id)
VALUES(1, 5, 'S', 'S', 'S', 'S', 'N', NULL);
/*Mis Documentos*/
INSERT INTO seg_org_roles_modulos(n_id_rol, n_id_modulo, 
`create`, `read`, `update`, `delete`, publico, n_session_id)
VALUES(1, 6, 'S', 'S', 'S', 'S', 'N', NULL);
/*Seguimiento*/
INSERT INTO seg_org_roles_modulos(n_id_rol, n_id_modulo, 
`create`, `read`, `update`, `delete`, publico, n_session_id)
VALUES(1, 7, 'S', 'S', 'S', 'S', 'N', NULL);
/*Informe Panorámico*/
INSERT INTO seg_org_roles_modulos(n_id_rol, n_id_modulo, 
`create`, `read`, `update`, `delete`, publico, n_session_id)
VALUES(1, 8, 'S', 'S', 'S', 'S', 'N', NULL);
/*Faltantes*/
INSERT INTO seg_org_roles_modulos(n_id_rol, n_id_modulo, 
`create`, `read`, `update`, `delete`, publico, n_session_id)
VALUES(1, 9, 'S', 'S', 'S', 'S', 'N', NULL);
/*Completados*/
INSERT INTO seg_org_roles_modulos(n_id_rol, n_id_modulo, 
`create`, `read`, `update`, `delete`, publico, n_session_id)
VALUES(1, 10, 'S', 'S', 'S', 'S', 'N', NULL);
-----------------

-----------------------------------------
-------No catálogos
INSERT INTO inst_empleado (n_id_num_empleado, nombre, apellido1, apellido2, id_sexo, s_email_pers, s_email_inst, tel_pers, tel_inst, curp, rfc, path_fotografia, n_id_usuario) VALUES
(1172, 'Otilio Esteban', 'Hernández', 'Pérez', 1, 'otiliohp@gmail.com', 'otilio.hernandez@tecdmx.org.mx', NULL, NULL, 'HEPO650328HHGRRT08', NULL, NULL, 1);

INSERT INTO inst_empleado_puesto (n_id_empleado_puesto, n_id_num_empleado, n_id_cat_area, n_id_puesto, fecha_alta, fecha_conclusion) VALUES
(1, 1172, 1, 43, NULL, NULL);

INSERT INTO inst_titular_u_adscripcion (n_id_titular_area, n_id_u_adscripcion, n_id_empleado_puesto, fecha_inicio, fecha_conclusion) VALUES
(1, 27, 1, NULL, NULL);

INSERT INTO seg_org_usuarios (n_id_usuario, s_usuario, s_contrasenia, s_desc_usuario, s_email, n_id_estado_usuario, s_token) VALUES
(1, 'otilio.hernandez', '$2y$11$kPY9mXi5JwKxQeaAsNLMUuzJj.ht.Pl7i95jCLc4lR1KsHt65fupW', NULL, 'otilio.hernandez@tecdmx.org.mx', 1, NULL);

INSERT INTO seg_org_usuario_estado_usuario(n_id_usuario_status, n_id_usuario, n_id_estado_usuario, 
fingerprint_dispositivo, d_fecha_status, n_session_id)VALUES
(1, 1, 1, NULL, '2023-10-21 13:31:15', NULL);

INSERT INTO seg_org_roles_usuarios(n_id_rol, n_id_usuario, n_id_u_adscripcion, n_session_id)VALUES
(1, 1, 27, NULL);



----------------------------------
----------------------------------
/*-----------------------------------------------------------------------------------*/
tab_doc_config
tab_doc_destinatarios
tab_docs_firmantes
tab_documentos
tab_documentos_adjuntos
tab_documento_workflow
tab_expedientes
tab_notificaciones
tab_configuracion