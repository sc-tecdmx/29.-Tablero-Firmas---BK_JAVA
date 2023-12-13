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
    'Firmante' AS tipo,
    wf_last.ult_actualizacion  -- Aquí es donde necesitas corregir el alias
FROM 
    tab_documentos d
JOIN 
    tab_docs_firmantes f ON d.n_id_documento = f.n_id_documento
LEFT JOIN (
    SELECT 
        w1.id_document,
        w1.id_etapa_documento,
        w1.ult_actualizacion
    FROM 
        tab_documento_workflow w1
    INNER JOIN (
        SELECT 
            id_document,
            MAX(ult_actualizacion) as max_ult_actualizacion
        FROM 
            tab_documento_workflow
        GROUP BY 
            id_document
    ) w2 ON w1.id_document = w2.id_document AND w1.ult_actualizacion = w2.max_ult_actualizacion
) wf_last ON wf_last.id_document = d.n_id_documento
LEFT JOIN 
    tab_cat_etapa_documento tced ON wf_last.id_etapa_documento = tced.id_etapa_documento 
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
    'Destinatario' AS tipo,
    wf_last.ult_actualizacion  -- Y también aquí
FROM 
    tab_documentos d
JOIN 
    tab_doc_destinatarios dest ON d.n_id_documento = dest.n_id_documento
LEFT JOIN (
    SELECT 
        w1.id_document,
        w1.id_etapa_documento,
        w1.ult_actualizacion
    FROM 
        tab_documento_workflow w1
    INNER JOIN (
        SELECT 
            id_document,
            MAX(ult_actualizacion) as max_ult_actualizacion
        FROM 
            tab_documento_workflow
        GROUP BY 
            id_document
    ) w2 ON w1.id_document = w2.id_document AND w1.ult_actualizacion = w2.max_ult_actualizacion
) wf_last ON wf_last.id_document = d.n_id_documento
LEFT JOIN 
    tab_cat_etapa_documento tced ON wf_last.id_etapa_documento = tced.id_etapa_documento 
LEFT JOIN 
    tab_cat_prioridad tcp ON d.n_id_prioridad = tcp.n_id_prioridad;
