var mysql = require('mysql2');
var sql = require('mssql');

// (NUVEM)

var sqlServerConfig = {
    user: "ingresseAdmin",
    password: "2adsb#grupo5",
    database: "ingresse-bd",
    server: "ingresse-srv.database.windows.net",
    pool: {
        max: 10,
        min: 0,
        idleTimeoutMillis: 30000,
    },
    options: {
        encrypt: true,
    },
};


// (LOCAL)
/*
var mySqlConfig = {
    host: "localhost",
    user: "hanna",
    password: "hanna100",
    database: "ingresse",
    port: "3306",
};
*/

function executar(instrucao) {
    if (process.env.AMBIENTE_PROCESSO == "producao") {
        return new Promise(function(resolve, reject) {
            sql
                .connect(sqlServerConfig)
                .then(function() {
                    return sql.query(instrucao);
                })
                .then(function(resultados) {
                    console.log(resultados);
                    resolve(resultados.recordset);
                })
                .catch(function(erro) {
                    reject(erro);
                    console.log("ERRO: ", erro);
                });
            sql.on("error", function(erro) {
                return "ERRO NO SQL SERVER (Azure): ", erro;
            });
        });
    } else if (process.env.AMBIENTE_PROCESSO == "desenvolvimento") {
        return new Promise(function(resolve, reject) {
            var conexao = mysql.createConnection(mySqlConfig);
            conexao.connect();
            conexao.query(instrucao, function(erro, resultados) {
                conexao.end();
                if (erro) {
                    reject(erro);
                }
                console.log(resultados);
                resolve(resultados);
            });
            conexao.on("error", function(erro) {
                return "ERRO NO MySQL WORKBENCH (Local): ", erro.sqlMessage;
            });
        });
    } else {
        return new Promise(function(resolve, reject) {
            console.log(
                "\nO AMBIENTE (produção OU desenvolvimento) NÃO FOI DEFINIDO EM app.js\n"
            );
            reject("AMBIENTE NÃO CONFIGURADO EM app.js");
        });
    }
}

module.exports = {
    executar,
};