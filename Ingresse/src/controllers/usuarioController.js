var usuarioModel = require("../models/usuarioModel");

function cadastrar(req, res) {
  var nome = req.body.nome;
  var email = req.body.email;
  var senha = req.body.senha;
  var telefone = req.body.telefone;

  if (nome == undefined) {
    res.status(400).send("Seu nome está undefined!");
  } else if (email == undefined) {
    res.status(400).send("Seu email está undefined!");
  } else if (senha == undefined) {
    res.status(400).send("Sua senha está undefined!");
  } else if (telefone == undefined) {
    res.status(400).send("Seu telefone está undefined!");
  } else {

    usuarioModel.cadastrar(nome, email, senha, telefone)
      .then(
        function (resultado) {
          res.json(resultado);
        }
      ).catch(
        function (erro) {
          console.log(erro);
          console.log(
            "\nHouve um erro ao realizar o cadastro! Erro: ",
            erro.sqlMessage
          );
          res.status(500).json(erro.sqlMessage);
        }
      );
  }
}

function login(req, res) {
  var email = req.body.email;
  var senha = req.body.senha;

  if (email == undefined) {
      res.status(400).send("Seu email está undefined!");
  } else if (senha == undefined) {
      res.status(400).send("Sua senha está indefinida!");
  } else {
      
      usuarioModel.login(email, senha)
          .then(
              function (resultado) {
                  console.log(`\nResultados encontrados: ${resultado.length}`);
                  console.log(`Resultados: ${JSON.stringify(resultado)}`); // transforma JSON em String

                  if (resultado.length == 1) {
                      console.log(resultado);
                      res.json(resultado[0]);
                  } else if (resultado.length == 0) {
                      res.status(403).send("Email e/ou senha inválido(s)");
                  } else {
                      res.status(403).send("Mais de um usuário com o mesmo login e senha!");
                  }
              }
          ).catch(
              function (erro) {
                  console.log(erro);
                  console.log("\nHouve um erro ao realizar o login! Erro: ", erro.sqlMessage);
                  res.status(500).json(erro.sqlMessage);
              }
          );
  }

}

module.exports = {
  cadastrar,
  login
};
