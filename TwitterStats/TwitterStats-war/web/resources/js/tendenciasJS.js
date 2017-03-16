
function mostrarGrafica() {
    
    var max = parseInt($('#nElementos').val());
    var i;
    //var elementos = [];
    var data = [];
    var labels = [];
    var iteracciones = 20;
    if(max < iteracciones){
        iteracciones = max;
    }
    for(i=0; i<iteracciones; i++ ){
        var elemento = '#elemento_'+i;
        var valor = '#valor_'+i; 
        //elementos.push({nombre:$(elemento).val(), valor:$(valor).val()});
        data.push(parseInt($(valor).val()));
        labels.push('#' + $(elemento).val());
    }

    new RGraph.SVG.HBar({
        id: 'chart-container',
        data: data,
        options: {
            backgroundGridBorder: false,
            yaxisColor: '#ddd',
            xaxisColor: '#ddd',
            backgroundGridHlines: false,
            colors: ['#4F81BD'],
            vmargin: 5,
            yaxisLabels: labels,
            labelsAbove: true,
            labelsAboveColor: '#333'
        }
    }).grow();

}



/*
<script src="js/RGraph.svg.common.core.js" type="text/javascript"></script>
<script src="js/RGraph.svg.hbar.js" type="text/javascript"></script>        
<script src="js/tendenciasJS.js" type="text/javascript"></script> 
  
 
 <div style="padding: 15px">
            <div style="width: 750px; height: 500px" id="chart-container"></div>
        </div>


        <script>
            mostrarGrafica();
        </script> 
  
 */

