<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<script type="text/javascript" src="js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="js/highcharts/exporting.js"></script>
<script>
$(function () {
     var colors = Highcharts.getOptions().colors;
     var categories = ['70%-80%', '80%-90%', '90%-100%', '100%-110%', '110%-120%', '120%-130%'];
     var name = 'Product Price';
//         data = [{
//                 y: 55.11,
//                 color: colors[0]
// //                 drilldown: {
// //                     name: 'MSIE versions',
// //                     categories: ['MSIE 6.0', 'MSIE 7.0', 'MSIE 8.0', 'MSIE 9.0'],
// //                     data: [10.85, 7.35, 33.06, 2.81],
// //                     color: colors[0]
// //                 }
//             }, {
//                 y: 21.63,
//                 color: colors[1]
// //                 drilldown: {
// //                     name: 'Firefox versions',
// //                     categories: ['Firefox 2.0', 'Firefox 3.0', 'Firefox 3.5', 'Firefox 3.6', 'Firefox 4.0'],
// //                     data: [0.20, 0.83, 1.58, 13.12, 5.43],
// //                     color: colors[1]
// //                 }
//             }, {
//                 y: 11.94,
//                 color: colors[2]
// //                 drilldown: {
// //                     name: 'Chrome versions',
// //                     categories: ['Chrome 5.0', 'Chrome 6.0', 'Chrome 7.0', 'Chrome 8.0', 'Chrome 9.0',
// //                         'Chrome 10.0', 'Chrome 11.0', 'Chrome 12.0'],
// //                     data: [0.12, 0.19, 0.12, 0.36, 0.32, 9.91, 0.50, 0.22],
// //                     color: colors[2]
// //                 }
//             }, {
//                 y: 7.15,
//                 color: colors[3]
// //                 drilldown: {
// //                     name: 'Safari versions',
// //                     categories: ['Safari 5.0', 'Safari 4.0', 'Safari Win 5.0', 'Safari 4.1', 'Safari/Maxthon',
// //                         'Safari 3.1', 'Safari 4.1'],
// //                     data: [4.55, 1.42, 0.23, 0.21, 0.20, 0.19, 0.14],
// //                     color: colors[3]
// //                 }
//             }, {
//                 y: 2.14,
//                 color: colors[4]
// //                 drilldown: {
// //                     name: 'Opera versions',
// //                     categories: ['Opera 9.x', 'Opera 10.x', 'Opera 11.x'],
// //                     data: [ 0.12, 0.37, 1.65],
// //                     color: colors[4]
// //                 }
//             }];

    function setChart(name, categories, data, color) {
	chart.xAxis[0].setCategories(categories, false);
	chart.series[0].remove(false);
	chart.addSeries({
		name: name,
		data: data,
		color: color || 'white'
	}, false);
	chart.redraw();
    }

    var chart = $('#container').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Product share'
        },
//         subtitle: {
//             text: 'Click the columns to view versions. Click again to view brands.'
//         },
        xAxis: {
            categories: categories
        },
        yAxis: {
            title: {
                text: 'Total percent produ'
            }
        },
        plotOptions: {
            column: {
                cursor: 'pointer',
//                 point: {
//                     events: {
//                         click: function() {
//                             var drilldown = this.drilldown;
//                             if (drilldown) { // drill down
//                                 setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);
//                             } else { // restore
//                                 setChart(name, categories, data);
//                             }
//                         }
//                     }
//                 },
                dataLabels: {
                    enabled: true,
                    color: colors[0],
                    style: {
                        fontWeight: 'bold'
                    },
                    formatter: function() {
                        return this.y +'%';
                    }
                }
            }
        },
        tooltip: {
            formatter: function() {
                var point = this.point,
                    s = this.x +':<b>'+ this.y +'% user guess this range</b><br/>';
                return s;
            }
        },
        series: [{
            name: name,
            color: 'white'
        }],
        exporting: {
            enabled: false
        }
    })
    .highcharts(); // return chart
    
     $.ajax({
          type:"GET",
          url:'pricesetup/${productId}',
          datatype:"json",
          success:function(data){
              var jdata = [];
              var i = 0;
              $.each(data,function(i,d){
              	  var element = {"y" : d.percent, "color" : colors[i]};
                  jdata.push(element);
                  i = i+1;
              });
             chart.series[0].setData(jdata); 
          },
          error:function(e){
              alert(e);
          }
  	});
});							
</script>
</head>
<body>
	<div id="container" style="min-width:700px;height:600px"></div>
</body>
</html>