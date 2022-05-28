var context1 = document.getElementsByClassName("line-chart1");
var context2 = document.getElementsByClassName("line-chart2");
var context3 = document.getElementsByClassName("line-chart3");
var context4 = document.getElementsByClassName("line-chart4");

var nome = "Capitão Broxa";
var totemAtual = [];
var informacoes = [];


var memUsada = 0;
var memTotal = 1000;
var memLivre = memTotal - memUsada;

var atualRam = 0;
var tempAtual = 0;

let cpu = 0;

var chartGraph1 = new Chart(context1, {
    type: 'doughnut',
    data: {
        labels: ["Usada", "Livre"],
        datasets: [{
            data: [memUsada, memLivre],
            borderWidth: 2,
            borderColor: ['#dcfc94',
                            '#555555'],
            backgroundColor: ['#dcfc9455',
                            'transparent'],
            cutout: '80%'
            
        }]
    },
    options: {
        plugins: {
            subtitle: {
                display: true,
                text: '',
                color: "transparente",
            },
            title: {
                display: true,
                text: 'Memória usada',
                align: 'start',
                font: {
                    size: 30,
                },
                color: 'white'
            },
            legend: {
                    display: false
            }
        },
        layout: {
            padding: {
                top: 0,
                bottom: 0
            }
        }
    }
});

var chartGraph2 = new Chart(context2, {
    type: "bar",
    data: { 
        labels: ["12h", "13h", "14h", "15h", "16h", "17h", "18h"],
        datasets: [{
            label: "Usada",
            data: [atualRam, atualRam, atualRam, atualRam,
                 atualRam, atualRam, atualRam],
            borderWidth: 2,
            fill: true,
            tension: 0.3,
            backgroundColor: '#dcfc9455',
            borderColor: '#dcfc94',
            pointRadius: 0,
            pointBorderColor: 'rgb(0, 0, 0)'
        }]
    },
    options: {
        plugins: {
            legend: {
                display: false
            }
        },
        scales: { 
            x: {
                ticks: {
                    color:'white', 
                    fontSize:20
                },
                grid: {
                    display: false
                }
            },
            y:{
                ticks: {
                    color: 'white'
                },
                grid: {
                    color: 'white'
                },
                beginAtZero: true,
                max: 100,
                min: 0
            }
        }
    }
});

var chartGraph3 = new Chart(context3, {
    type: "line",
    data: {
        labels: ["12h", "13h", "14h", "15h", "16h", "17h", "18h"],
        datasets: [{
            label: "Usada",
            data: [cpu, cpu, cpu, cpu, cpu, cpu, cpu],
            borderWidth: 2,
            fill: true,
            tension: 0.3,
            pointBackgroundColor: 'transparent',
            backgroundColor: '#dcfc9455',
            borderColor: '#dcfc94',
            pointRadius: 0,
            pointBorderColor: 'rgb(0, 0, 0)'
        }]
    },
    options: {
        plugins: {
            title: {
                display: true,
                text: 'CPU',
                align: 'start',
                font: {
                    size: 30,
                },
                color: 'white'
            },
            legend: {
                display: false,
                labels: {
                    usePointStyle: true,
                  },
            },
        },
        scales: {
           
            x: {
                ticks: {
                    color:'white', 
                    fontSize:20
                },
                grid: {
                    color: 'white'
                }
            },
            y:{
                ticks: {
                    color: 'white'
                },
                grid: {
                    display: false
                },
                beginAtZero: true,
                max: 100,
                min: 0
            }
        }
    }
});

const memUsadaHtml = document.getElementById("memPorcentagem")
const porc = document.getElementById("porcentagem")
var totemAgora;
function mostrarTotem(idTotem) {
    nome = idTotem;
    
    totemAgora = idTotem

    trazerTotem(idTotem)
    trazerInformacoes(idTotem)
    

    setTimeout(() => {
    memTotal = totemAtual[0].espaco_disco
    memUsada = informacoes[0].pctg_disco_uso
    memLivre = memTotal - memUsada;
    chartGraph1.data.datasets[0].data[1] = memUsada;
    chartGraph1.data.datasets[0].data[0] = memLivre;
    memUsadaHtml.innerHTML = `${(memLivre*100/memTotal).toFixed()}%`
    chartGraph1.update();

    console.log(memTotal)
    console.log(memUsada)
    console.log(memLivre)
    console.log(memTotal)
    console.log(memTotal)
    console.log(memTotal)


    const dataRam = chartGraph2.data.datasets[0].data;
    atualRam = informacoes[0].pctg_memoria_uso;
    for (let i = 0; i <= dataRam.length - 2; i++) {
        dataRam[i] = dataRam[i + 1];
    }
    dataRam[dataRam.length-1] = atualRam;
    chartGraph2.update();


    const dataCpu = chartGraph3.data.datasets[0].data;
    cpu = informacoes[0].pctg_processador;
    for (let i = 0; i <= dataCpu.length - 2; i++) {
        dataCpu[i] = dataCpu[i + 1];
    }
    dataCpu[dataCpu.length - 1] = cpu;
    chartGraph3.update();

    tempAtual = informacoes[0].temp;
    porc.style.height = `${tempAtual*100/100}%`;
    temperaturaAtual.innerHTML = `${tempAtual}ºC`

    if (tempAtual > 70) {
        porc.style.backgroundColor = "red"
    } else {
        porc.style.backgroundColor = "#dcfc9455"
    }

    
    contador = 0;

    relatorio.innerHTML = `
    <span>
        Relatório do Totem
        </span>
    <hr>
    <span> Processos: ${informacoes[0].qtd_processos}
        <span id="horasLigado">
        
        </span> 
    </span>
    <span> Serviços: ${informacoes[0].qtd_servicos}
        <span id="horasLigado">
        
        </span> 
    </span>
    <span> Hostname: ${totemAtual[0].hostname}
        <span id="horasLigado">
        
        </span> 
    </span>
    `

    nomeTotem.innerHTML = `Totem ${nome}`
}, 3000);
    
}


let contador = -1;
setInterval(function() {
    if (contador == 0){
        mostrarTotem(nome)
    }
    console.log(`
    idTotem: ${nome}
    ---------------------------
    RAM: ${atualRam}
    CPU: ${cpu}
    Memória Usada: ${(memLivre*100/memTotal).toFixed()}%
    Temperatura: ${tempAtual}ºC
    `)
}, 8000);

function listarTotem() {
    fetch("/totens/listarTotem", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      }
    }).then(async function (resposta) {
      const containerTotem = document.querySelector(".totens");
      const totens = await resposta.json();
      for (let i = 0; i < totens.length; i++) {

        let idTotens = document.createElement("div");
        console.log(totens[i])
        idTotens.innerHTML = `<span onclick="mostrarTotem(${totens[i].idTotem})">Totem_${totens[i].idTotem}</span>`;

        containerTotem.appendChild(idTotens);
      }

    });
    setTimeout(() => {
        console.log("foi os totens?")
    }, 3000);
  }

  

function trazerTotem() {
    fetch(`/totens/trazerTotem/${totemAgora}`, { cache: 'no-store' }).then(function (response) {
        if (response.ok) {
            response.json().then(function (resposta) {
                
                totemAtual = resposta;
                
                resposta.reverse();
            });
        } else {
            console.error('Nenhum dado encontrado ou erro na API');
        }
    })
        .catch(function (error) {
            console.error(`Erro na obtenção dos dados p/ gráfico: ${error.message}`);
        });
}

function trazerInformacoes() {
    fetch(`/totens/trazerInformacoes/${totemAgora}`, { cache: 'no-store' }).then(function (response) {
        if (response.ok) {
            response.json().then(function (resposta) {
                
                informacoes = resposta;
                
                resposta.reverse();
            });
        } else {
            console.error('Nenhum dado encontrado ou erro na API');
        }
    })
        .catch(function (error) {
            console.error(`Erro na obtenção dos dados p/ gráfico: ${error.message}`);
        });
}


function listarSelenium() {
    fetch("/totens/listarSelenium", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      }
    }).then(async function (resposta) {
        const seleniuns = await resposta.json();
        for (let i = 0; i < seleniuns.length; i++) {
          console.log(totens[i])
          
          cardsLog.innerHTML += `<div class="log"><span class="log-title">
                                        <span>
                                            Filme: ${seleniuns[i].nomeFilme} 
                                        </span>
                                        </span>
                                        <hr>
                                        <span>Data Lanç:${seleniuns[i].dataFilme}</span>
                                        <span>Dias Lanç:${seleniuns[i].dias_para_lancamento}</span></div>`;
  
    }});
    setTimeout(() => {
        console.log("foi o selenium?")
    }, 3000);
  }

window.addEventListener("load", listarTotem);
window.addEventListener("load", listarSelenium);

function sairDaDash() {
    window.location.href = "../index.html";
}