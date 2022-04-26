var express = require("express");
var router = express.Router();

var empresaController = require("../controllers/empresaController");


router.get("/", function(req, res) {
  empresaController.testar(req, res);
});

router.post("/cadastrar", function (req, res) {
  empresaController.cadastrar(req, res);
});

router.post("/login", function (req, res) {
  empresaController.login(req, res);
});

module.exports = router;
