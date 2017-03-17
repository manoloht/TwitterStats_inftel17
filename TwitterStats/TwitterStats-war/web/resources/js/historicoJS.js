function mostrarGraficaHoras() {

    // Datos horas
    var maxHoras = parseInt($('#nElementosHoras').val());
    var i;
    var dataHoras = [];
    var labelsHoras = [];
    for (i = 0; i < maxHoras; i++) {
        var elemento = '#elementoHoras_' + i;
        var valor = '#valorHoras_' + i;
        var numValor = parseInt($(valor).val());
        dataHoras.push(numValor);
        labelsHoras.push($(elemento).val());
    }

    new RGraph.SVG.Line({
        id: 'chart-container-horas',
        data: dataHoras,
        options: {
            backgroundColor: '#eee',
            backgroundGridBorder: false,
            backgroundGridHlines: false,
            colors: ['#0085BD'],
            linewidth: 3,
            hmargin: 0,
            gutterLeft: 50,
            gutterBottom: 50,
            tickmarksStyle: 'circle',
            tickmarksLinewidth: 3,
            tickmarksSize: 3,
            xaxisLabels: labelsHoras,
            xaxis: false,
            yaxis: false,
            yaxisDecimals: 0
        }
    }).trace();
}


function mostrarGraficaSemanal() {

    // Datos semanal
    var maxSemanal = parseInt($('#nElementosSemanal').val());
    var i;
    var dataSemanal = [];
    var labelsSemanal = [];
    for (i = 0; i < maxSemanal; i++) {
        var elemento = '#elementoSemanal_' + i;
        var valor = '#valorSemanal_' + i;
        var numValor = parseInt($(valor).val());
        dataSemanal.push(numValor);
        labelsSemanal.push($(elemento).val());
    }

    new RGraph.SVG.Line({
        id: 'chart-container-semanal',
        data: dataSemanal,
        options: {
            backgroundColor: '#eee',
            backgroundGridBorder: false,
            backgroundGridHlines: false,
            colors: ['#0085BD'],
            linewidth: 3,
            hmargin: 0,
            gutterLeft: 50,
            gutterBottom: 50,
            tickmarksStyle: 'circle',
            tickmarksLinewidth: 3,
            tickmarksSize: 3,
            xaxisLabels: labelsSemanal,
            xaxis: false,
            yaxis: false,
            yaxisDecimals: 0
        }
    }).trace();
}


function mostrarGraficaDias() {

    // Datos días
    var maxDias = parseInt($('#nElementosDias').val());
    var i;
    var dataDias = [];
    var labelsDias = [];
    for (i = 0; i < maxDias; i++) {
        var elemento = '#elementoDias_' + i;
        var valor = '#valorDias_' + i;
        var numValor = parseInt($(valor).val());
        dataDias.push(numValor);
        labelsDias.push($(elemento).val());


    }

    new RGraph.SVG.Line({
        id: 'chart-container-dias',
        data: dataDias,
        options: {
            backgroundColor: '#eee',
            backgroundGridBorder: false,
            backgroundGridHlines: false,
            colors: ['#0085BD'],
            linewidth: 3,
            hmargin: 0,
            gutterLeft: 50,
            gutterBottom: 50,
            tickmarksStyle: 'circle',
            tickmarksLinewidth: 3,
            tickmarksSize: 3,
            xaxisLabels: labelsDias,
            xaxis: false,
            yaxis: false,
            yaxisDecimals: 0
        }
    }).trace();
}



/*
 <script src="js/RGraph.svg.common.core.js" type="text/javascript"></script>
 <script src="js/RGraph.svg.line.js" type="text/javascript"></script>
 <script src="js/historicoJS.js" type="text/javascript"></script>
 
 
 <div style="width: 600px; height: 450px" id="chart-container"></div>
 
 <script>
 mostrarGrafica();
 </script>
 */