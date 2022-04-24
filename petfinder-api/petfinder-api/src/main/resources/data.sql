-- endereco
INSERT INTO 
endereco(rua, num, complemento, bairro, cidade, uf, cep, latitude, longitude)
VALUES
('rua A', '111', null, 'Bairro A', 'Sao Paulo', 'SP', '11111111', null, null),
('rua B', '222', null, 'Bairro B', 'Sao Paulo', 'SP', '22222222', null, null),
('rua C', '333', null, 'Bairro C', 'Sao Paulo', 'SP', '33333333', null, null);

-- instituicao
INSERT INTO 
instituicao(nome, telefone, chave_pix, termo_adocao, fk_endereco)
VALUES 
('instituicao A', '11 91111-1111', 'poadskfafioasjdf', null, 1),
('instituicao B', '11 92222-2222', 'sdhflaksdfnsdfas', null, 2),
('instituicao C', '11 93333-3333', 'sdofihasldkfjans', null, 3),
('instituicao D', '11 93333-3333', 'sdofihasldkfjans', null, null);


-- Demanda
--INSERT INTO
--demanda(categoria, data_abertura, data_fechamento, status, fk_usuario, fk_instituicao, fk_pet)
--VALUES
--('ADOÇÃO', now(), now(), 'ABERTO', null, null, null);


-- usuiario 
-- INSERT INTO 
-- usuario(nome)
-- VALUES
-- ('erik pacheco'),
-- ('lucas mesquita');