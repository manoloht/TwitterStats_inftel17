function drawGraph() {
    var chart = c3.generate({
        size: {
            height: cHeight,
            width: 630,
        },
        data: {
            columns: [
                ['Menciones'].concat(data)
            ],
            type: 'bar',
            labels: true,
            colors: {
                Menciones: '#32CDFD'
            }
        },
        bar: {
            width: {
                ratio: 0.5
            }
        },
        axis: {
            rotated: true,
            x: {
                type: 'category',
                categories: labels
            }
        }
        
    });    
}
