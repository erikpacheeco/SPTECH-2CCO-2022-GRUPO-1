USE petfinder;

-- =======================================================
--  ADMIN
-- =======================================================

-- padrinhos

CREATE OR REPLACE VIEW view_padrinhos AS
(SELECT UUID() as id, instituicao_id, count(id) as qtd_padrinhos, data_fechamento as data FROM demanda
WHERE status = 'pgto_realizado_inst' OR status = 'concluido' 
GROUP BY instituicao_id, data_fechamento) ORDER BY data DESC;

CREATE OR REPLACE VIEW view_padrinhos_ultimos_7_dias AS
(select UUID() as id, instituicao_id, sum(qtd_padrinhos) as qtd_padrinhos, data from view_padrinhos 
WHERE data BETWEEN (SELECT DATE_SUB(CURRENT_DATE, INTERVAL 6 DAY)) AND CURRENT_DATE
GROUP BY instituicao_id, data) 
ORDER BY data DESC;

CREATE OR REPLACE VIEW view_padrinhos_ultimos_6_meses AS
select UUID() as id, instituicao_id, sum(qtd_padrinhos) as qtd_padrinhos, YEAR(data) as ano, MONTH(data) as mes FROM view_padrinhos 
WHERE data BETWEEN (SELECT DATE_SUB(CURRENT_DATE(), INTERVAL 5 MONTH)) AND CURRENT_DATE()
GROUP BY instituicao_id, ano, mes ORDER BY ano, mes DESC;

-- premios

CREATE OR REPLACE VIEW view_premios_por_data AS
select UUID() AS id, i.id as instituicao_id, count(pr.id) as qtd_premios, data_envio as data from premio as pr
INNER JOIN pet AS p ON p.id = pr.pet_id
INNER JOIN instituicao AS i ON i.id = p.instituicao_id
GROUP BY i.id, data_envio 
ORDER BY data DESC;

CREATE OR REPLACE VIEW view_premios_ultimos_7_dias AS
(select UUID() as id, instituicao_id, sum(qtd_premios) as qtd_premios, data as data from view_premios_por_data 
WHERE data BETWEEN (SELECT DATE_SUB(CURRENT_DATE, INTERVAL 6 DAY)) AND CURRENT_DATE
GROUP BY instituicao_id, data) 
ORDER BY data DESC;

CREATE OR REPLACE VIEW view_premios_ultimos_6_meses AS
select UUID() as id, instituicao_id, sum(qtd_premios) as qtd_premios, YEAR(data) as ano, MONTH(data) as mes 
FROM view_premios_por_data 
WHERE data BETWEEN (SELECT DATE_SUB(CURRENT_DATE(), INTERVAL 5 MONTH)) AND CURRENT_DATE()
GROUP BY instituicao_id, ano, mes 
ORDER BY ano, mes DESC;

CREATE OR REPLACE VIEW view_premios_por_pet AS
SELECT p.id as pet_id, p.instituicao_id, count(pr.id) as qtd_premios 
FROM premio AS pr 
RIGHT OUTER JOIN pet AS p ON pr.pet_id = p.id
GROUP BY p.id;

-- demandas

CREATE OR REPLACE VIEW view_demandas AS
SELECT UUID() as id, instituicao_id, count(*) as qtd_demandas, categoria, data_fechamento as data FROM demanda 
GROUP BY instituicao_id, categoria, data_fechamento
ORDER BY data DESC;

CREATE OR REPLACE VIEW view_demandas_ultimos_7_dias AS
select UUID() as id, instituicao_id, sum(qtd_demandas) as qtd_demandas, categoria, data as data from view_demandas 
WHERE data BETWEEN (SELECT DATE_SUB(CURRENT_DATE, INTERVAL 6 DAY)) AND CURRENT_DATE
GROUP BY instituicao_id, categoria, data;

CREATE OR REPLACE VIEW view_demandas_ultimos_6_meses AS
select UUID() as id, instituicao_id, sum(qtd_demandas) as qtd_demandas, categoria, YEAR(data) as ano, MONTH(data) as mes FROM view_demandas 
WHERE data BETWEEN (SELECT DATE_SUB(CURRENT_DATE(), INTERVAL 5 MONTH)) AND CURRENT_DATE()
GROUP BY instituicao_id, categoria, ano, mes 
ORDER BY ano, mes DESC;

-- =======================================================
-- CHATOPS
-- =======================================================

-- demanda
CREATE OR REPLACE VIEW view_demanda_colaborador AS
SELECT UUID() as id, count(id) as qtd_demandas, colaborador_id, instituicao_id, categoria, data_fechamento as data from demanda
GROUP BY instituicao_id, colaborador_id, categoria, data
ORDER BY data DESC;

CREATE OR REPLACE VIEW view_demanda_colaborador_ultimos_7_dias AS
select UUID() as id, sum(qtd_demandas) as qtd_demandas, data, categoria, colaborador_id, instituicao_id from view_demanda_colaborador 
WHERE data BETWEEN (SELECT DATE_SUB(CURRENT_DATE(), INTERVAL 6 DAY)) AND CURRENT_DATE()
GROUP BY instituicao_id, colaborador_id, categoria, data
ORDER BY data DESC;

CREATE OR REPLACE VIEW view_demanda_colaborador_ultimos_6_meses AS
select UUID() as id, sum(qtd_demandas) as qtd_demandas, categoria, colaborador_id, YEAR(data) as ano, MONTH(data) as mes from view_demanda_colaborador 
WHERE data BETWEEN (SELECT DATE_SUB(CURRENT_DATE(), INTERVAL 5 MONTH)) AND CURRENT_DATE()
GROUP BY colaborador_id, categoria, data
ORDER BY data DESC;

CREATE OR REPLACE VIEW view_demandas_por_semana AS
SELECT UUID() as id, d.data_fechamento as fechamento, d.instituicao_id, count(d.instituicao_id) as qtd_demandas 
FROM demanda as d
WHERE data_fechamento BETWEEN (SELECT DATE_SUB(CURRENT_DATE(), INTERVAL 6 DAY)) AND CURRENT_DATE()
GROUP BY d.data_fechamento, d.instituicao_id
ORDER BY data_fechamento DESC;

-- =======================================================
-- SYSADMIN
-- =======================================================

-- VISITANTES -> CADASTROS -> CLIENTES

CREATE OR REPLACE VIEW view_visitantes AS
SELECT UUID() as id, count(*) as qtd_visitas, YEAR(data_visita) as ano, MONTH(data_visita) as mes FROM visitantes 
WHERE data_visita BETWEEN (SELECT DATE_SUB(CURRENT_DATE(), INTERVAL 5 MONTH)) AND CURRENT_DATE()
GROUP BY ano, mes
ORDER BY ano, mes DESC;

CREATE OR REPLACE VIEW view_usuarios_cadastrados AS
SELECT UUID() as id, count(*) as qtd_cadastros, data_cadastro as data FROM leads 
GROUP BY data_cadastro
ORDER BY data DESC;

CREATE OR REPLACE VIEW view_clientes AS
SELECT UUID() as id, count(*) as qtd_clientes, YEAR(demanda.data_fechamento) as ano, MONTH(demanda.data_fechamento) as mes FROM usuario 
INNER JOIN demanda ON usuario.id = demanda.usuario_id
WHERE usuario.nivel_acesso = 'user' AND 
demanda.status = 'concluido' AND
demanda.data_fechamento BETWEEN (SELECT DATE_SUB(CURRENT_DATE(), INTERVAL 5 MONTH)) AND CURRENT_DATE()
GROUP BY ano, mes
ORDER BY ano, mes DESC;

CREATE OR REPLACE VIEW view_instituicoes_cadastradas AS
SELECT i.id, i.nome, data_cadastro FROM leads as l
INNER JOIN usuario as u ON u.id = l.usuario_id 
INNER JOIN instituicao as i ON u.instituicao_id = i.id
WHERE tipo = 'adm' AND 
data_cadastro BETWEEN (SELECT DATE_SUB(CURRENT_DATE(), INTERVAL 29 DAY)) AND CURRENT_DATE()
ORDER BY data_cadastro DESC;

CREATE OR REPLACE VIEW view_instituicoes_ativas AS
SELECT i.id, i.nome,
(SELECT lk.data_cadastro FROM leads as lk
INNER JOIN usuario as uk ON uk.id = lk.usuario_id
ORDER BY lk.data_cadastro DESC
LIMIT 1) as data_cadastro
FROM instituicao as i 
WHERE i.id IN (
  SELECT u.instituicao_id FROM leads AS l
  INNER JOIN usuario as u ON u.id = l.usuario_id 
  WHERE l.data_cadastro BETWEEN (SELECT DATE_SUB(CURRENT_DATE(), INTERVAL 29 DAY)) AND CURRENT_DATE()
);