var express = require("express"); 
var router = express.Router();

var usuarioController = require("../controllers/usuarioController");

router.post("/cadastrar", function (req, res) {
  usuarioController.cadastrar(req, res);
});


router.post("/login", function (req, res) {
  usuarioController.login(req, res);
});

router.post("/excluir", function (req, res) {
  usuarioController.excluir(req, res);
});

router.get("/listar", function (req, res) {
  usuarioController.listar(req, res);
});

router.get("/notificar", function (req, res) {
  usuarioController.notificar(req, res);
});

router.post("/editar", function (req, res) {
  usuarioController.editar(req, res);
});

module.exports = router;
