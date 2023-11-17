CREATE VIEW vista_tablero AS
SELECT 
	d.n_id_documento,
    d.folio_documento,
	tced.s_desc_etapa,
	tcp.desc_prioridad AS prioridad,
	d.creacion_documento_fecha,
	d.s_asunto,
	f.n_id_num_empleado AS num_empleado,
    f.n_id_inst_firmante AS n_id_inst,
    'Firmante' AS tipo
FROM 
    tab_documentos d
JOIN 
    tab_docs_firmantes f ON d.n_id_documento = f.n_id_documento
LEFT JOIN 
    tab_documento_workflow wf ON d.n_id_documento = wf.id_document
    JOIN 
		tab_cat_etapa_documento tced ON wf.id_etapa_documento = tced.id_etapa_documento 
LEFT JOIN 
	tab_cat_prioridad tcp ON d.n_id_prioridad = tcp.n_id_prioridad
UNION ALL
SELECT 
	d.n_id_documento,
	d.folio_documento,
	tced.s_desc_etapa,
	tcp.desc_prioridad AS prioridad,
	d.creacion_documento_fecha,
	d.s_asunto,
	dest.n_id_num_empleado AS num_empleado,
    dest.n_id_inst_dest AS n_id_inst,
    'Destinatario' AS tipo
FROM 
    tab_documentos d
JOIN 
    tab_doc_destinatarios dest ON d.n_id_documento = dest.n_id_documento
LEFT JOIN 
    tab_documento_workflow wf ON d.n_id_documento = wf.id_document
   	JOIN 
		tab_cat_etapa_documento tced ON wf.id_etapa_documento = tced.id_etapa_documento 
LEFT JOIN 
	tab_cat_prioridad tcp ON d.n_id_prioridad = tcp.n_id_prioridad;
