var context1 = document.getElementsByClassName("line-chart1");
var context2 = document.getElementsByClassName("line-chart2");
var context3 = document.getElementsByClassName("line-chart3");
var context4 = document.getElementsByClassName("line-chart4");


var memUsada = (Math.random() *1000 +1).toFixed();
var memTotal = 1000;
var memLivre = memTotal - memUsada;

var chartGraph1 = new Chart(context1, {
    type: 'doughnut',
    data: {
        labels: ["Livre", "Usada"],
        datasets: [{
            data: [memLivre , memUsada],
            borderWidth: 1,
            borderColor: 'rgba(0,0,0,0.5)',
            backgroundColor: ['rgba(0, 255, 0, 0.5)', 
                            'rgba(0, 0, 255, 0.5)'],
        }]
    },
    options: {
        plugins: {
            title: {
                display: true,
                text: 'Armazenamento (GB)',
                color: 'white',
            },
            legend: {
                labels: {
                    color: "white"
                }
            }
        },
        tooltip: {
            fontColor: "white"
        },

    }
});

var atualRam = (Math.random() *255 +1).toFixed();

var chartGraph2 = new Chart(context2, {
    type: "line",
    data: { 
        labels: ["12h", "13h", "14h", "15h", "16h", "17h", "18h"],
        datasets: [{
            label: "Usada",
            data: [atualRam, atualRam, atualRam, atualRam,
                 atualRam, atualRam, atualRam],
            borderWidth: 2,
            borderColor: 'rgba(0,0,0, 0.5)',
            backgroundColor: 'rgba(0, 0, 255, 0.5)',
        }]
    },
    options: {
        plugins: {
            title: {
                display: true,
                text: 'RAM',
                color: 'white',
            }
        }
    }
});

let cpu = (Math.random() * 100).toFixed();

var chartGraph3 = new Chart(context3, {
    type: "line",
    data: {
        labels: ["12h", "13h", "14h", "15h", "16h", "17h", "18h"],
        datasets: [{
            label: "Usada",
            data: [cpu, cpu, cpu, cpu, cpu, cpu, cpu],
            borderWidth: 1,
            borderColor: 'rgba(0,0,0, 0.5)',
            backgroundColor: 'rgba(0, 0, 255, 0.5)',
        }]
    },
    options: {
        plugins: {
            title: {
                display: true,
                text: 'CPU',
                color: 'white', 
            }
        }
    }
});

var processos = Math.random()

var chartGraph4 = new Chart(context4, {
    type: "bar",
    data: {
        labels: [1,1,1,1,1,1,1,1],
        datasets: [{
            label: "AOBA",
            data: [5,4,8,7,2,5,9],
            borderWidth: 1,
            borderColor: 'rgba(0,0,0, 0.5)',
            backgroundColor: 'rgba(0,0, 255, 0.5)',
        }]
    },
    options: {
        plugins: {
            title: {
                display: true,
                text: 'Processos',
                color: 'white',
            }
        }
    }
});

const mostrarTotem1 = () => {
    memUsada = (Math.random() *1000 ).toFixed();
    memLivre = memTotal - memUsada;
    chartGraph1.data.datasets[0].data[1] = memUsada;
    chartGraph1.data.datasets[0].data[0] = memLivre;
    chartGraph1.update();

    const dataRam = chartGraph2.data.datasets[0].data;
    atualRam = (Math.random() *100).toFixed();
    for (let i = 0; i <= dataRam.length - 2; i++) {
        dataRam[i] = dataRam[i + 1];
    }
    dataRam[dataRam.length-1] = cpu;
    chartGraph2.update();
    

    const dataCpu = chartGraph3.data.datasets[0].data;
    cpu = (Math.random() *100).toFixed();
    for (let i = 0; i <= dataCpu.length - 2; i++) {
        dataCpu[i] = dataCpu[i + 1];
    }
    dataCpu[dataCpu.length - 1] = cpu;
    chartGraph3.update();
}

