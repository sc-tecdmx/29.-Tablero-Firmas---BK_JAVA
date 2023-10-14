INSERT INTO firma_electronica.cat_area
(nombre, code, activo, fh_creacion, fh_modificacion, usu_creacion, usu_modificacion)
VALUES
	('Recursos Humanos', 'RH', 1, null, null, -23, -23),
	('Recursos Materiales', 'RM', 1, null, null, -23, -23);

INSERT INTO firma_electronica.cat_tipodocumento
(nombre, code, activo, fh_creacion, fh_modificacion, usu_creacion, usu_modificacion)
VALUES
	('Minuta', 'MINUTA', 1, null, null, -23, -23),
	('Acuerdo', 'ACUERDO', 1, null, null, -23, -23);

INSERT INTO firma_electronica.cat_accion
(nombre, code, activo, fh_creacion, fh_modificacion, usu_creacion, usu_modificacion)
VALUES
	('Firmar', 'FIRMAR', 1, null, null, -23, -23),
	('Rubricar', 'RUBRICAR', 1, null, null, -23, -23),
	('Revisar', 'REVISAR', 1, null, null, -23, -23),
	('Publicar', 'PUBLICAR', 1, null, null, -23, -23);

INSERT INTO firma_electronica.cat_estado
(nombre, code, activo, fh_creacion, fh_modificacion, usu_creacion, usu_modificacion)
VALUES
	('Completado', 'COMPLETADO', 1, null, null, -23, -23),
	('En proceso', 'EN_PROCESO', 1, null, null, -23, -23),
	('Expirado', 'EXPIRADO', 1, null, null, -23, -23),
	('Inválido', 'INVALIDO', 1, null, null, -23, -23);

INSERT INTO firma_electronica.cat_cargo
(nombre, code, activo, fh_creacion, fh_modificacion, usu_creacion, usu_modificacion)
VALUES
	('Administrativo', 'ADMINISTRATIVO', 1, null, null, -23, -23),
	('Jefe de Departamento', 'JEFE_DEPTO', 1, null, null, -23, -23),
	('Titular', 'TITULAR', 1, null, null, -23, -23),
	('Personal Operativo', 'PERSONAL_OP', 1, null, null, -23, -23);

INSERT INTO firma_electronica.cat_tipoasociacion
(nombre, code, activo, fh_creacion, fh_modificacion, usu_creacion, usu_modificacion)
VALUES
	('Firmante', 'FIRMANTE', 1, null, null, -23, -23),
	('Destinatario', 'DESTINATARIO', 1, null, null, -23, -23);

INSERT INTO firma_electronica.cat_tipouso
(nombre, code, activo, fh_creacion, fh_modificacion, usu_creacion, usu_modificacion)
VALUES
	('Comunicación', 'COMUNICACION', 1, null, null, -23, -23);
