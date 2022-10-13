-- endereco
INSERT INTO 
endereco(rua, num, complemento, bairro, cidade, uf, cep, latitude, longitude)
VALUES
('rua A', '111', null, 'Bairro A', 'Sao Paulo', 'SP', '05143320', null, null),
('rua B', '222', null, 'Bairro B', 'Sao Paulo', 'SP', '24455540', null, null),
('rua C', '333', null, 'Bairro C', 'Sao Paulo', 'SP', '03181050', null, null),
('rua D', '444', null, 'Bairro D', 'Sao Paulo', 'SP', '05845270', null, null);

-- instituicao
INSERT INTO 
instituicao(nome, telefone, termo_adocao, endereco_id)
VALUES 
('Delivery de gatinhos', '11 91111-1111', null, 1),
('Petz', '11 92222-2222', null, 2),
('Cat & Dog', '11 93333-3333', null, 3),
('Instituto Luísa Mell', '11 93333-3333', null, 4);

-- usuario 
INSERT INTO 
usuario(nome, email, senha, nivel_acesso, endereco_id, instituicao_id, logado)
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

-- Pet
INSERT INTO
pet(nome, data_nasc, especie, raca, porte, sexo, descricao, doente, adotado, caminho_imagem, instituicao_id)
VALUES
('Antonio', '2021-05-21', 'Cachorro', 'SRD', 'Pequeno', 'Macho', 'Branquinho, brincalhao e estressado com criança chata', true, false, 'http://localhost:8080/img/pets/antonio.png', 1),
('Antonio Gabriel', '2022-06-12', 'Gato', 'SRD', 'Pequeno', 'Fêmea', 'Nenem que ama, ficar no colo, fiel companheira', true, false, 'http://localhost:8080/img/pets/antonio-gabriel.png', 2),
('Batatinha', '2019-04-20', 'Cachorro', 'SRD', 'Medio', 'Macho', 'Brincalhao e gosta de passear', false, false, 'http://localhost:8080/img/pets/batatinha.png', 3),
('Bingus', '2022-03-20', 'Gato', 'Sphynx', 'Medio', 'Macho', 'Brincalhao e gosta de passear', false, false, 'http://localhost:8080/img/pets/bingus.png', 4),
('Ferdinando', '2019-06-20', 'Gato', 'SRD', 'Pequeno', 'Macho', 'Brincalhao e gosta de passear', true, false, 'http://localhost:8080/img/pets/ferdinando.png', 1),
('Jonas', '2014-08-20', 'Gato', 'SRD', 'Pequeno', 'Macho', 'Brincalhao e gosta de passear', true, false, 'http://localhost:8080/img/pets/jonas.png', 2),
('Juninho', '2020-06-20', 'Cachorro', 'SRD', 'Pequeno', 'Macho', 'Brincalhao e gosta de passear', false, false, 'http://localhost:8080/img/pets/juninho.png', 3),
('Leila', '2020-06-20', 'Gato', 'SRD', 'Pequeno', 'Fêmea', 'Brincalhao e gosta de passear', true, false, 'http://localhost:8080/img/pets/leila.png', 4),
('Minhoca', '2022-02-20', 'Gato', 'SRD', 'Pequeno', 'Fêmea', 'Brincalhao e gosta de passear', false, false, 'http://localhost:8080/img/pets/minhoca.png', 1),
('Patinha', '2020-06-20', 'Gato', 'SRD', 'Pequeno', 'Macho', 'Brincalhao e gosta de passear', false, false, 'http://localhost:8080/img/pets/patinha.png', 1),
('Peteca', '2019-06-20', 'Gato', 'SRD', 'Pequeno', 'Macho', 'Brincalhao e gosta de passear', false, false, 'http://localhost:8080/img/pets/peteca.png', 3),
('Piui', '2022-01-20', 'Cachorro', 'SRD', 'Pequeno', 'Macho', 'Brincalhao e gosta de passear', false, false, 'http://localhost:8080/img/pets/piui.png', 1),
('Roberto', '2019-06-20', 'Gato', 'SRD', 'Pequeno', 'Macho', 'Brincalhao e gosta de passear', true, false, 'http://localhost:8080/img/pets/roberto.png', 1),
('Vanessa', '2019-06-20', 'Gato', 'SRD', 'Pequeno', 'Fêmea', 'Brincalhao e gosta de passear', true, false, 'http://localhost:8080/img/pets/vanessa.png', 2);

-- Premio
INSERT INTO
premio(pet_id, img)
VALUES
(1, 'http://localhost:8080/img/premios/antonio.png'),
(2, 'http://localhost:8080/img/premios/antonio-gabriel.png'),
(1, 'http://localhost:8080/img/premios/antonio.png');


-- Caracteristca
INSERT INTO
caracteristica(caracteristica)
VALUES
('Fofo'),
('Cheiroso'),
('Branco'),
('Calmo'),
('Amigável'),
('Companheiro'),
('Preto'),
('Tranquilo com crianças'),
('Pequeno'),
('Grande'),
('Castrado');

-- PetHasCaracteristica
INSERT INTO
pet_has_caracteristica(caracteristica_id, pet_id)
VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(4, 2);

-- UsuarioHasInteresse
INSERT INTO
usuario_has_interesse(caracteristica_id, usuario_id)
VALUES
(1, 9),
(3, 9),
(4, 10),
(2, 12),
(3, 12),
(1, 13),
(4, 13);

-- Demanda
INSERT INTO
demanda(categoria, data_abertura, data_fechamento, status, usuario_id, instituicao_id, pet_id, colaborador_id)
VALUES
('PAGAMENTO', '2020-01-01', null, 'ABERTO', 9, 1, null, null),
('PAGAMENTO', '2020-01-01', null, 'ABERTO', 10, 1, 1, null),
('PAGAMENTO', '2020-01-01', null, 'EM_ANDAMENTO', 9, 1, null, 2),
('PAGAMENTO', '2020-01-01', null, 'PGTO_REALIZADO_USER', 9, 1, null, 2),
('PAGAMENTO', '2020-01-01', null, 'PGTO_REALIZADO_USER', 10, 1, 2, 2),
('PAGAMENTO', '2020-01-01', null, 'PGTO_REALIZADO_USER', 11, 1, null, 2),
('PAGAMENTO', '2020-01-01', null, 'PGTO_REALIZADO_INST', 11, 1, null, 2),
('PAGAMENTO', '2020-01-01', '2020-01-01', 'CANCELADO', 9, 1, null, 2),
('ADOCAO', '2020-01-01', null, 'ABERTO', 9, 1, null, null),
('ADOCAO', '2022-08-25', null, 'EM_ANDAMENTO', 9, 1, 5, 2),
('ADOCAO', '2022-08-25', null, 'AGUARDANDO_VALIDACAO_DOCUMENTO', 9, 1, 5, 2),
('ADOCAO', '2022-08-25', null, 'DOCUMENTO_INVALIDO', 9, 1, 5, 2),
('ADOCAO', '2022-08-25', null, 'DOCUMENTO_VALIDO', 9, 1, 5, 2),
('ADOCAO', '2020-01-01', '2020-01-01', 'CANCELADO', 9, 1, null, 2),
('ADOCAO', '2020-01-01', '2020-01-01', 'CANCELADO', 9, 1, null, 2),
('ADOCAO', '2022-08-25', '2022-08-25', 'CONCLUIDO', 9, 1, 5, 2),
('ADOCAO', '2022-08-25', '2022-08-25', 'CONCLUIDO', 10, 1, 5, 2),
('ADOCAO', '2022-08-25', '2022-08-25', 'CONCLUIDO', 9, 1, 5, 2),
('PAGAMENTO', '2020-01-01', null, 'PGTO_REALIZADO_INST', 11, 1, null, 2),
('PAGAMENTO', '2020-01-01', null, 'PGTO_REALIZADO_INST', 11, 1, null, 2);

-- demanda histórico
INSERT INTO 
demanda_hist(data, status, demanda_id) VALUES
('2020-01-01', 'ABERTO', 1),
('2020-01-01', 'ABERTO', 2),
('2020-01-01', 'ABERTO', 3),
('2020-01-01', 'EM_ANDAMENTO', 3),
('2020-01-01', 'ABERTO', 4),
('2020-01-01', 'EM_ANDAMENTO', 4),
('2020-01-01', 'PAGAMENTO_REALIZADO_USER', 4),
('2020-01-01', 'ABERTO', 5),
('2020-01-01', 'EM_ANDAMENTO', 5),
('2020-01-01', 'PAGAMENTO_REALIZADO_USER', 5),
('2020-01-01', 'ABERTO', 6),
('2020-01-01', 'EM_ANDAMENTO', 6),
('2020-01-01', 'PAGAMENTO_REALIZADO_USER', 6),
('2020-01-01', 'ABERTO', 7),
('2020-01-01', 'EM_ANDAMENTO', 7),
('2020-01-01', 'PAGAMENTO_REALIZADO_USER', 7),
('2020-01-01', 'PAGAMENTO_REALIZADO_INST', 7),
('2020-01-01', 'ABERTO', 8),
('2020-01-01', 'EM_ANDAMENTO', 8),
('2020-01-01', 'CANCELADO', 8),
('2020-01-01', 'ABERTO', 9),
('2020-01-01', 'ABERTO', 10),
('2020-01-01', 'ABERTO', 11),
('2020-01-01', 'EM_ANDAMENTO', 11),
('2020-01-01', 'AGUARDANDO_VALIDACAO_DOCUMENTO', 11),
('2020-01-01', 'ABERTO', 12),
('2020-01-01', 'EM_ANDAMENTO', 12),
('2020-01-01', 'AGUARDANDO_VALIDACAO_DOCUMENTO', 12),
('2020-01-01', 'DOCUMENTO_INVALIDA', 12),
('2020-01-01', 'ABERTO', 13),
('2020-01-01', 'EM_ANDAMENTO', 13),
('2020-01-01', 'AGUARDANDO_VALIDACAO_DOCUMENTO', 13),
('2020-01-01', 'DOCUMENTO_VALIDO', 13),
('2020-01-01', 'ABERTO', 14),
('2020-01-01', 'EM_ANDAMENTO', 14),
('2020-01-01', 'AGUARDANDO_VALIDACAO_DOCUMENTO', 14),
('2020-01-01', 'DOCUMENTO_INVALIDO', 14),
('2020-01-01', 'CANCELADO', 14),
('2020-01-01', 'ABERTO', 15),
('2020-01-01', 'EM_ANDAMENTO', 15),
('2020-01-01', 'CANCELADO', 15),
('2020-01-01', 'ABERTO', 16),
('2020-01-01', 'EM_ANDAMENTO', 16),
('2020-01-01', 'AGUARDANDO_VALIDACAO_DOCUMENTO', 16),
('2020-01-01', 'DOCUMENTO_VALIDO', 16),
('2020-01-01', 'CONCLUIDO', 16),
('2020-01-01', 'ABERTO', 17),
('2020-01-01', 'EM_ANDAMENTO', 17),
('2020-01-01', 'AGUARDANDO_VALIDACAO_DOCUMENTO', 17),
('2020-01-01', 'DOCUMENTO_VALIDO', 17),
('2020-01-01', 'CONCLUIDO', 17),
('2020-01-01', 'ABERTO', 18),
('2020-01-01', 'EM_ANDAMENTO', 18),
('2020-01-01', 'AGUARDANDO_VALIDACAO_DOCUMENTO', 18),
('2020-01-01', 'DOCUMENTO_VALIDO', 18),
('2020-01-01', 'CONCLUIDO', 18),
('2020-01-01', 'ABERTO', 19),
('2020-01-01', 'EM_ANDAMENTO', 19),
('2020-01-01', 'PAGAMENTO_REALIZADO_USER', 19),
('2020-01-01', 'PAGAMENTO_REALIZADO_INST', 19),
('2020-01-01', 'ABERTO', 20),
('2020-01-01', 'EM_ANDAMENTO', 20),
('2020-01-01', 'PAGAMENTO_REALIZADO_USER', 20),
('2020-01-01', 'PAGAMENTO_REALIZADO_INST', 20);

-- historico demanda

-- mensagem
INSERT INTO mensagem(conteudo, data_envio, tipo, demanda_id, usuario_id) VALUES
('Boa tarde, tudo certo? Eu gostaria de saber mais sobre o Fernandinho!', now(), 'texto', 14, 9),
('Boa tarde, que bom saber! O Fernandinho é um doce de pet', now(), 'texto', 14, 2),
('Mas também é muito bagunceiro, ele está à procura de um cuidador paciente', now(), 'texto', 14, 2),
('Bora dale', now(), 'texto', 14, 2);
