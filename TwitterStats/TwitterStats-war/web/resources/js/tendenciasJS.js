
function mostrarGrafica() {

    new RGraph.SVG.HBar({
        id: 'chart-container',
        data: [12, 7, 26, 5, 14],
        options: {
            backgroundGridBorder: false,
            yaxisColor: '#ddd',
            xaxisColor: '#ddd',
            backgroundGridHlines: false,
            colors: ['#4F81BD'],
            vmargin: 25,
            yaxisLabels: [
                'I had no clean pants to wear',
                'I thought it was Saturday',
                'I forgot to set my alarm',
                'It was still too dark, I thought it was nighttime',
                'I got stuck in traffic'
            ],
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

