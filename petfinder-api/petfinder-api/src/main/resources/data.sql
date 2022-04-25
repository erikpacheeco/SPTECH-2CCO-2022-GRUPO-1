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

-- usuario 
INSERT INTO 
usuario(nome, email, senha, nivel_acesso, fk_endereco, fk_instituicao, logado)
VALUES
('lucas mesquita', 'lucas.msouza@bandtec.com', 'urubu100', 'sysadm', null, null, false),
('Mylena Oliveira', 'myau@catdelivery.com', 'urubu200', 'adm', 1, 1, false),
('Isabell', 'isa.bell@catdelivery.com', 'urubu200', 'petops', 1, 1, false),
('Ana Maria', 'ana.maria@catdelivery.com', 'urubu200', 'chatops', 3, 1, false),
('Kayo Fezz', 'kay.o@instb.com', 'urubu200', 'adm', 2, 2, false),
('Sky Fernandez', 'sky.e@instb.com', 'urubu200', 'petops', 2, 2, false),
('Peter Geferson', 'peter.g@instb.com', 'urubu200', 'chatops', 3, 2, false),
('Reya', 'raya@instb.com', 'urubu200', 'chatops', 1, 2, false),
('Breach', 'breach@gmail.com', 'urubu200', 'user', 1, null, false),
('Reyna', 'reyna@gmail.com', 'urubu200', 'user', 3, null, false),
('Cypher de Souza', 'cypher@gmail.com', 'urubu200', 'user', 2, null, false),
('Yoru', 'yoru@gmail.com', 'urubu200', 'user', 2, null, false),
('Sage', 'sage@gmail.com', 'urubu200', 'user', 1, null, false);

-- Demanda
INSERT INTO
demanda(categoria, data_abertura, data_fechamento, status, fk_usuario, fk_instituicao, fk_pet)
VALUES
('pagamento', '2020/01/01', null, 'aberto', 9, 1, null),
('adocao', '2020/01/01', null, 'aberto', 10, 1, null),
('resgate', '2020/01/01', null, 'aberto', 9, 1, null);

-- Pet
INSERT INTO
pet(nome, data_nasc, especie, raca, porte, sexo, descricao, em_adocao, fk_instituicao)
VALUES
('Antônio', '2020/02/28', 'Cachorro', 'Shits-zu', 'Pequeno', 'Macho', 'Branquinho, brincalhão e estressado com criança chata', true, 1),
('Mel', '2027/04/12', 'Cachorro', 'Maltês', 'Pequeno', 'Fêmea', 'Nénem que ama, ficar no colo, fiel companheira', true, 1);

-- Premio
INSERT INTO
premio(img, fk_pet)
VALUES
('Fofo, peludo, bochechuco', 1);

-- Caracteristca
INSERT INTO
caracteristica(caracteristicas)
VALUES
('Fofo'),
('Cheiroso'),
('Branco'),
('Calmo');