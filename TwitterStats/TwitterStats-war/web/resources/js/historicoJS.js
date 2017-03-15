 
function mostrarGrafica() {
    
 new RGraph.SVG.Line({
        id: 'chart-container',
        data: [1.911,1.66,1.789,2.113,2.318,2.472,2.652,3.552,4.420,4.102,4.598],
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
            xaxisLabels: ['2005','2006','2007','2008','2009','2010','2011','2012','2013','2014','2015'],
            xaxis: false,
            yaxis: false,
            yaxisDecimals: 3
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