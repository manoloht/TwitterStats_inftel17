/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function mostrarGrafica(cadena){
    
    var max = parseInt($('#nElementos').val());
    var i;
    //var elementos = [];
    var data = [];
    var labels = [];
    for(i=0; i<max; i++ ){
        var elemento = '#elemento_'+i;
        var valor = '#valor_'+i; 
        //elementos.push({nombre:$(elemento).val(), valor:$(valor).val()});
        data.push(parseInt($(valor).val()));
        labels.push($(elemento).val() + ', ' + $(valor).val() + '%');
    }

    new RGraph.SVG.Pie({
        id: 'chart-container',
        data: data,
        options: {
            labels: labels,
            tooltips: labels,
            colors: ['#EC0033','#A0D300','#FFCD00','#00B869','#999999','#FF7300','#004CB0'],
            strokestyle: 'white',
            linewidth: 2,
            shadow: true,
            shadowOffsetx: 2,
            shadowOffsety: 2,
            shadowBlur: 3,
            exploded: 7
        }
    }).roundRobin();
}



