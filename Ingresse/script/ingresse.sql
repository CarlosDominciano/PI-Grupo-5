CREATE DATABASE ingresse-bd;

USE ingresse;

CREATE TABLE IF NOT EXISTS usuario(
	id_usuario 			INT PRIMARY KEY AUTO_INCREMENT,
    nome         		VARCHAR(45),
    email				VARCHAR(45),
    senha				VARCHAR(45),
    telefone			VARCHAR(15)
);

select * from usuario;

SHOW TABLES;