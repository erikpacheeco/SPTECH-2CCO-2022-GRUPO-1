DROP DATABASE petfinder;
CREATE DATABASE petfinder DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE petfinder;

-- -----------------------------------------------------
-- Table endereco
-- -----------------------------------------------------
CREATE TABLE endereco(
  id INT PRIMARY KEY AUTO_INCREMENT,
  rua VARCHAR(100) NOT NULL,
  num VARCHAR(6) NOT NULL,
  complemento VARCHAR(100) NULL,
  bairro VARCHAR(100) NOT NULL,
  cidade VARCHAR(45) NOT NULL,
  uf CHAR(2) NOT NULL,
  cep CHAR(8) NOT NULL,
  latitude VARCHAR(11) NULL,
  longitude VARCHAR(11) NULL
);

-- -----------------------------------------------------
-- Table instituicao
-- -----------------------------------------------------
CREATE TABLE instituicao (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(60) NOT NULL,
  telefone VARCHAR(13) NOT NULL,
  chave_pix TEXT NULL,
  termo_adocao TEXT NULL,
  endereco_id INT NOT NULL,
  FOREIGN KEY(endereco_id) REFERENCES endereco(id)
);

-- -- -----------------------------------------------------
-- -- Table caracteristica
-- -- -----------------------------------------------------
CREATE TABLE caracteristica(
  id INT PRIMARY KEY AUTO_INCREMENT,
  caracteristica VARCHAR(20) NOT NULL
);

-- -- -----------------------------------------------------
-- -- Table pet
-- -- -----------------------------------------------------
CREATE TABLE pet (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(30) NOT NULL,
  data_nasc DATE NOT NULL,
  especie VARCHAR(30) NOT NULL,
  raca VARCHAR(30) NOT NULL,
  porte VARCHAR(30) NOT NULL,
  caminho_imagem TEXT NOT NULL,
  sexo VARCHAR(6) NOT NULL,
  descricao TEXT NOT NULL,
  doente TINYINT NULL,
  adotado TINYINT NOT NULL,
  instituicao_id INT NOT NULL,
  FOREIGN KEY (instituicao_id) REFERENCES instituicao(id)
);

-- -- -----------------------------------------------------
-- -- Table pet_has_caracteristica
-- -- -----------------------------------------------------
CREATE TABLE pet_has_caracteristica (
  id INT PRIMARY KEY AUTO_INCREMENT,
  caracteristica_id INT NOT NULL,
  pet_id INT NOT NULL,
  FOREIGN KEY (pet_id) REFERENCES pet(id)
);

-- -----------------------------------------------------
-- Table usuario
-- -----------------------------------------------------
CREATE TABLE usuario (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(60) NOT NULL,
  email VARCHAR(40) NOT NULL,
  senha VARCHAR(16) NOT NULL,
  nivel_acesso ENUM("sysadm", "adm", "petops", "chatops", "user") NOT NULL,
  instituicao_id INT,
  endereco_id INT,
  logado TINYINT DEFAULT 0,
  FOREIGN KEY (instituicao_id) REFERENCES instituicao(id),
  FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

-- -----------------------------------------------------
-- Table demanda
-- -----------------------------------------------------
CREATE TABLE demanda (
  id INT PRIMARY KEY AUTO_INCREMENT,
  categoria ENUM("adocao", "pagamento", "resgate") NOT NULL,
  data_abertura DATETIME NOT NULL,
  data_fechamento DATETIME NULL,
  status ENUM("aberto", "em_andamento", "concluido", "cancelado", "aguardando_validacao_documento", "documento_valido", "pgto_realizado_user", "pgto_realizado_inst") NOT NULL,
  usuario_id INT NOT NULL,
  instituicao_id INT NOT NULL,
  colaborador_id INT,
  pet_id INT,
  FOREIGN KEY (usuario_id) REFERENCES usuario(id),
  FOREIGN KEY (instituicao_id) REFERENCES instituicao(id),
  FOREIGN KEY (colaborador_id) REFERENCES usuario(id),
  FOREIGN KEY (pet_id) REFERENCES pet(id)
);

-- -----------------------------------------------------
-- Table demanda_hist
-- -----------------------------------------------------
CREATE TABLE demanda_hist (
  id INT PRIMARY KEY AUTO_INCREMENT,
  data DATETIME DEFAULT NOW(),
  status ENUM("aberto", "em_andamento", "concluido", "cancelado", "aguardando_validacao_documento", "documento_valido", "pgto_realizado_user", "pagamento_realizado_user", "pgto_realizado_inst", "pagamento_realizado_inst") NOT NULL,
  demanda_id INT NOT NULL
);

-- -----------------------------------------------------
-- Table mensagem
-- -----------------------------------------------------
CREATE TABLE mensagem (
  id INT PRIMARY KEY AUTO_INCREMENT,
  conteudo TEXT NOT NULL,
  tipo ENUM("texto", "doc", "img") NOT NULL,
  data_envio DATETIME NOT NULL,
  usuario_id INT NOT NULL,
  demanda_id INT NOT NULL,
  FOREIGN KEY (usuario_id) REFERENCES usuario(id),
  FOREIGN KEY (demanda_id) REFERENCES demanda(id)
);

-- -----------------------------------------------------
-- Table tags_has_usuarios
-- -----------------------------------------------------
CREATE TABLE usuario_has_interesse (
  id INT PRIMARY KEY AUTO_INCREMENT,
  caracteristica_id INT NOT NULL,
  usuario_id INT NOT NULL,
  FOREIGN KEY(caracteristica_id) REFERENCES caracteristica(id),
  FOREIGN KEY(usuario_id) REFERENCES usuario(id)
);

-- -----------------------------------------------------
-- Table demanda_has_pet
-- -----------------------------------------------------
CREATE TABLE demanda_has_pet (
  id INT PRIMARY KEY AUTO_INCREMENT,
  ativo TINYINT NOT NULL,
  demanda_id INT NOT NULL,
  pet_id INT NOT NULL,
  FOREIGN KEY (demanda_id) REFERENCES demanda(id),
  FOREIGN KEY (pet_id) REFERENCES pet(id)
);

-- -----------------------------------------------------
-- Table premio
-- -----------------------------------------------------
CREATE TABLE premio (
  id INT PRIMARY KEY AUTO_INCREMENT,
  img TEXT,
  data_envio DATE,
  pet_id INT NOT NULL,
  FOREIGN KEY (pet_id) REFERENCES pet(id)
);

-- -----------------------------------------------------
-- Table visitantes
-- -----------------------------------------------------
CREATE TABLE visitantes (
  id INT PRIMARY KEY AUTO_INCREMENT,
  data_visita DATE
);

-- -----------------------------------------------------
-- Table leads
-- -----------------------------------------------------
CREATE TABLE leads (
  id INT PRIMARY KEY AUTO_INCREMENT,
  usuario_id INT NOT NULL,
  tipo VARCHAR(45) NOT NULL,
  data_cadastro DATE
);

-- -----------------------------------------------------
-- Table clientes
-- -----------------------------------------------------
CREATE TABLE clientes (
  id INT PRIMARY KEY AUTO_INCREMENT,
  usuario_id INT NOT NULL,
  tipo VARCHAR(45) NOT NULL,
  data_cliente DATE
)


