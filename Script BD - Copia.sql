CREATE DATABASE bd_integrador;

USE bd_integrador;

CREATE TABLE usuario(
id_usuario BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
cpf VARCHAR(15) NOT NULL UNIQUE,
tipo VARCHAR(20) NOT NULL,
senha VARCHAR(30) NOT NULL,
PRIMARY KEY (id_usuario)
);

CREATE TABLE produto(
id_produto BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
preco DOUBLE NOT NULL,
descricao VARCHAR(300) NOT NULL,
id_usuario BIGINT NOT NULL,
PRIMARY KEY(id_produto),
FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE pedido(
id_pedido BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
quantidade INT NOT NULL,
multa DOUBLE,
status VARCHAR(50) NOT NULL,
id_usuario BIGINT NOT NULL,
PRIMARY KEY(id_pedido),
FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario) 
);

CREATE TABLE pedido_produto(
id_pedido_produto BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
id_pedido BIGINT NOT NULL,
id_produto BIGINT NOT NULL,
PRIMARY KEY(id_pedido_produto),
FOREIGN KEY(id_pedido) REFERENCES pedido(id_pedido),
FOREIGN KEY(id_produto) REFERENCES produto(id_produto)
);

CREATE TABLE favorita_usuario(
id_favorita_usuario BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
id_usuario1 BIGINT NOT NULL,
id_usuario2 BIGINT NOT NULL,
PRIMARY KEY(id_favorita_usuario),
FOREIGN KEY(id_usuario1) REFERENCES usuario(id_usuario),
FOREIGN KEY(id_usuario2) REFERENCES usuario(id_usuario)
);

CREATE TABLE avaliacao(
id_avaliacao BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
nota DOUBLE NOT NULL,
comentario VARCHAR(200),
id_usuario BIGINT NOT NULL,
id_produto BIGINT NOT NULL,
PRIMARY KEY(id_avaliacao),
FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario),
FOREIGN KEY(id_produto) REFERENCES produto(id_produto)
);

CREATE TABLE conversa(
id_conversa BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
mensagem VARCHAR(300) NOT NULL,
id_usuario BIGINT NOT NULL,
id_produto BIGINT NOT NULL,
PRIMARY KEY(id_conversa),
FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario),
FOREIGN KEY(id_produto) REFERENCES produto(id_produto)
);