var usuarioModel = require("../models/usuarioModel");

function listarTotem(req, res) {
  usuarioModel
    .listarTotem()
    .then(function (resultado) {
      res.json(resultado);
    })
    .catch(function (erro) {
      console.log(erro);
      console.log("\nHouve um erro ao pegar os usuarios", erro.sqlMessage);
      res.status(204).json(erro.sqlMessage);
    });
}

function trazerInformacoes(req, res) {
    var idTotem = req.params.idTotem;
    usuarioModel
      .trazerInformacoes(idTotem)
      .then(function (resultado) {
        res.json(resultado);
      })
      .catch(function (erro) {
        console.log(erro);
        console.log("\nHouve um erro ao pegar os usuarios", erro.sqlMessage);
        res.status(204).json(erro.sqlMessage);
      });
  }

  function trazerTotem(req, res) {
    var idTotem = req.params.idTotem;
    usuarioModel
      .trazerTotem(idTotem)
      .then(function (resultado) {
        if (resultado.length > 0) {
          res.status(200).json(resultado);
      } else {
          res.status(204).send("Nenhum resultado encontrado!")
      }
  }).catch(function (erro) {
      console.log(erro);
      console.log("Houve um erro ao buscar as ultimas medidas.", erro.sqlMessage);
      res.status(500).json(erro.sqlMessage);
  });
  }



module.exports = {
    listarTotem,
    trazerInformacoes,
    trazerTotem
};
