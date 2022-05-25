var express = require("express"); 
var router = express.Router();

var totemController = require("../controllers/totemController");

router.get("/listarTotem", function (req, res) {
  totemController.listarTotem(req, res);
});

  router.get("/trazerInformacoes/:idTotem", function (req, res) {
    totemController.trazerInformacoes(req, res);
})

  router.get("/trazerTotem/:idTotem", function (req, res) {
    totemController.trazerTotem(req, res);
})



module.exports = router;
