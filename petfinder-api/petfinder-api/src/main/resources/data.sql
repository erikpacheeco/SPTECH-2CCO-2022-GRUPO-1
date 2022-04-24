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
INSERT INTO 
usuario(nome, email, senha, nivel_acesso, fk_endereco, fk_instituicao)
VALUES
('lucas mesquita', 'lucas.msouza@bandtec.com', 'urubu100', 'sysadm', null, null),
('Mylena Oliveira', 'myau@catdelivery.com', 'urubu200', 'adm', 1, 1),
('Isabell', 'isa.bell@catdelivery.com', 'urubu200', 'petops', 1, 1),
('Ana Maria', 'ana.maria@catdelivery.com', 'urubu200', 'chatops', 3, 1),
('Kayo Fezz', 'kay.o@instb.com', 'urubu200', 'adm', 2, 2),
('Sky Fernandez', 'sky.e@instb.com', 'urubu200', 'petops', 2, 2),
('Peter Geferson', 'peter.g@instb.com', 'urubu200', 'chatops', 3, 2),
('Reya', 'raya@instb.com', 'urubu200', 'chatops', 1, 2),
('Breach', 'breach@gmail.com', 'urubu200', 'user', 1, null),
('Reyna', 'reyna@gmail.com', 'urubu200', 'user', 3, null),
('Cypher de Souza', 'cypher@gmail.com', 'urubu200', 'user', 2, null),
('Yoru', 'yoru@gmail.com', 'urubu200', 'user', 2, null),
('Sage', 'sage@gmail.com', 'urubu200', 'user', 1, null);