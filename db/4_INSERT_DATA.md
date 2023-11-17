Ejecutar los siguientes servicios:
- Seguridad/Sistema-Modulos-Menu => create-menu
- Seguridad/Empleados => create-empleado
- Seguridad/Autenticación => cambiar-contraseña
- Seguridad/Autenticación => login
- Probar servicios que devuelven info de usuario
    - Seguridad/Autenticación/user-info
    - Seguridad/Sistema-Modulos-Menu/get-menu-by-usuario
- PKI/Poblar-datos/agregar-ac-autorizadas
    - 1. Intermedio -> AC-Sat1106.crt
    - 2. OCSP -> ocsp.ac5_sat.cer

- PKI/Poblar-datos/agregar-cert-usuario --NOTA: Agregar el certificado con el token del usuario que le corresponde, si no se va a asociar incorrectamente.

- Tablero/Firma-e/alta-documento: --NOTA: Agregar con el token del Super Administrador.

/**------------------------------------------- */
/**Consultas básicas para comprobar datos */
SELECT * from inst_empleado;
SELECT * from seg_org_usuarios;

SELECT * from tab_documentos;
SELECT * from tab_documentos_adjuntos;
SELECT * from tab_docs_firmantes;
SELECT * from tab_doc_destinatarios;

SELECT * from pki_documento pd;
SELECT * from pki_documento_firmantes;
SELECT * from pki_documento_destinos;

SELECT * from pki_x509_tsp;
SELECT * from pki_x509_ocsp;

SELECT * from pki_usuarios_cert;
