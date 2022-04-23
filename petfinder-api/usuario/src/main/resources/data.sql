-- endereco
INSERT INTO
endereco(rua, num, complemento, bairro, cidade, uf, cep, latitude, longitude)
VALUES
('rua benedita lima teixeira', '145', null, 'Jardim Brasilia', 'Sao Paulo', 'SP', '05845270', null, null);

-- instituicao
INSERT INTO
instituicao(nome, telefone, chave_pix, termo_adocao, endereco)
VALUES
('delivery de gatinhos', '11 95456-3499', 'aosidhfpaoius2341q', null, 1);

-- usuario
INSERT INTO
usuario(nome, email, senha, nivel_acesso, fk_endereco, fk_instituicao)
VALUES
('Rafaela', 'rafaamanciosoares@gmail.com', 'rafa123', null, 1, 1);
