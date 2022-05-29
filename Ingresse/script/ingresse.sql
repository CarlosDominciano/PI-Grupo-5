CREATE DATABASE ingresse-bd;

USE ingresse;

CREATE TABLE filial(
	idFilial INT PRIMARY KEY AUTO_INCREMENT,
	email_corporativo varchar(45),
	senha varchar(45),
	cnpj varchar(45)
);

create table lancamento_futuro(
	idLancamento INT PRIMARY KEY AUTO_INCREMENT,
	nomeFilme varchar(45),
	dataFilme char(10),
	dias_para_lancamento int
);

create table totem(
	idTotem INT PRIMARY KEY AUTO_INCREMENT,
	fkFilial int,
    FOREIGN KEY (fkFilial) REFERENCES filial(idFilial),
	ram_total float,
	espaco_disco float,
	processador varchar(45),
	data_compra date,
	id_processador varchar(45),
	serial_disco varchar(45),
	hostname varchar(45)
);

create table logs(
	idLog INT PRIMARY KEY AUTO_INCREMENT,
	fkTotem int,
    FOREIGN KEY (fkTotem) REFERENCES totem(idTotem),
	pctg_processador int,
	pctg_memoria_uso int,
	pctg_disco_uso int,
	qtd_servicos int,
	temp float,
	data_hora dateTime
);

create table usuario(
	idUsuario INT PRIMARY KEY AUTO_INCREMENT,
	nome varchar(45),
	email_usuario varchar(45),
	tipo_acesso varchar(45),
	fkFilial int,
    FOREIGN KEY (fkFilial) REFERENCES filial(idFilial),
	senha varchar(45)
);

create table ocorrencias(
	idOcorrencia INT PRIMARY KEY AUTO_INCREMENT,
	fkLog int,
	FOREIGN KEY (fkLog) REFERENCES logs(idLog),
  fkUsuario int,
  FOREIGN KEY (fkUsuario) REFERENCES usuario(idUsuario),
	descricao_erro varchar(45),
	descricao_concerto varchar(45),
	data_inicio date,
	data_final date
);


insert into usuario (email_usuario, senha)
values ("diego@ingresse.com", "123123");