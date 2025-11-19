CREATE TABLE usuario(
	id_usuarios BIGINT AUTO_INCREMENT,
	username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    roles TINYTEXT,
    
    PRIMARY KEY(id_usuarios)

);

CREATE TABLE produtos(
	id_produtos BIGINT AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    quantidade INT NOT NULL,
	
    PRIMARY KEY(id_produtos)
);