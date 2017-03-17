 
function mostrarGrafica(zona,datos,labels) {
    
 new RGraph.SVG.Line({
        id: 'chart-container-'+zona,
        data: datos,
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
            tickmarksLinewidth: 5,
            tickmarksSize: 7,
            xaxisLabels: labels,
            xaxis: false,
            yaxis: false,
            yaxisDecimals: 3
        }
    }).trace();
    
}

function mostrarGraficaHoras() {
    
        // Datos horas
    var maxHoras = parseInt($('#nElementosHoras').val());
    var i;
    var dataHoras = [];
    var labelsHoras = [];
    for(i=0; i<maxHoras; i++ ){
        var elemento = '#elementoHoras_'+i;
        var valor = '#valorHoras_'+i;        
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
            tickmarksLinewidth: 5,
            tickmarksSize: 7,
            xaxisLabels: labelsHoras,
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