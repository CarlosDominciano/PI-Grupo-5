var database = require("../database/config");

function cadastrar(nome, email, senha, tipoAcesso, empresa) {
  var instrucao = `
    INSERT INTO usuario(nome, email_usuario, senha, tipo_acesso, fkFilial) VALUES
    ('${nome}', '${email}', '${senha}', '${tipoAcesso}', ${empresa});
    `;
  console.log("cadastrando Usuários");
  return database.executar(instrucao);
}

function login(email, senha) {
  var instrucao = `
    SELECT * FROM usuario WHERE email_usuario = '${email}' AND senha = '${senha}'
  `;
  console.assert("Logando usuario");
  return database.executar(instrucao);
}

function excluir(email){
  var instrucao =
  `DELETE FROM usuario WHERE email_usuario = '${email}';`;
  return database.executar(instrucao);
}

function listar(){
  var instrucao =
  `SELECT * FROM usuario;`
  return database.executar(instrucao);
}

function editar(nome, email, telefone, senha, id) {
  var instrucao = 
`UPDATE usuario SET nome = '${nome}' WHERE id_usuario = ${id};`;
database.executar(instrucao);

var instrucao = 
`UPDATE usuario SET email = '${email}' WHERE id_usuario = ${id};`;
database.executar(instrucao);

var instrucao = 
`UPDATE usuario SET telefone = '${telefone}' WHERE id_usuario = ${id};`;
database.executar(instrucao);

var instrucao = 
`UPDATE usuario SET senha = '${senha}' WHERE id_usuario = ${id};`;
database.executar(instrucao);

}

module.exports = {
  cadastrar,
  login,
  excluir,
  listar,
  editar
};
