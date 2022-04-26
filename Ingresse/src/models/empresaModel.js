var database = require("../database/config");

function cadastrar(empresa, cnpj, senha, email) {
  var instrucao = `
    insert into filial(nome_filial, email_corporativo, senha, cnpj) values
    ('${empresa}', '${email}', '${senha}', '${cnpj}');
    `;
  console.log("Cadastrando empresa");
  return database.executar(instrucao);
}


function login(email, senha){
  var instrucao = 
  `SELECT * FROM filial WHERE email_corporativo = '${email}' AND senha = '${senha}'`;
  return database.executar(instrucao);
}

module.exports = {
  cadastrar,
  login
};