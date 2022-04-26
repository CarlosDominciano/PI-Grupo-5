CREATE DATABASE ingresse-bd;

USE ingresse;

CREATE TABLE usuario(
	id_usuario 			INT PRIMARY KEY AUTO_INCREMENT,
    nome         		VARCHAR(45),
    email				VARCHAR(45),
    senha				VARCHAR(45),
    telefone			VARCHAR(15)
);

select * from usuario;

CREATE TABLE filial(
	id_filial 			INT PRIMARY KEY AUTO_INCREMENT,
    nome_filial      	VARCHAR(45),
    email_corporativo	VARCHAR(45),
    senha				VARCHAR(45),
    cnpj     			VARCHAR(15)
);

SHOW TABLES;