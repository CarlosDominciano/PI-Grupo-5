var database = require("../database/config");

function cadastrar(nome, email, senha, telefone) {
  var instrucao = `
    INSERT INTO usuario(nome, email_usuario, senha, telefone) VALUES
    ('${nome}', '${email}', '${senha}', '${telefone}');
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

module.exports = {
  cadastrar,
  login
};
