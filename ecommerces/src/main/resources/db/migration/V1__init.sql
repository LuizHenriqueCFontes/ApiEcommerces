CREATE TABLE usuario(
	id_usuarios BIGINT AUTO_INCREMENT,
    email VARCHAR(255),
    password VARCHAR(155),
    
    PRIMARY KEY(id_usuarios)
);

CREATE TABLE produtos(
	id_produtos BIGINT AUTO_INCREMENT,
    nome VARCHAR(255),
    categoria VARCHAR(95),
    quantidade INT,
    
    PRIMARY KEY(id_produtos)
);