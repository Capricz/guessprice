<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="js/highcharts/exporting.js"></script>
<script>
    $(function () {
     var chart;
     $(document).ready(function() {
        chart = new Highcharts.Chart({
        chart: {
            renderTo: 'container',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: 'User Activity Level'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    formatter: function () {
                         return '<b>' + this.point.name + '</b>: ' + Highcharts.numberFormat(this.percentage, 2) + ' %';
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'User Activity Level'
        }]
       });
    
    });
    
    $.ajax({
          type:"GET",
          url:'getPieDataForUser',
          datatype:"json",
          success:function(data){
              var jdata = [];
              $.each(data,function(i,d){
                  jdata.push([d.region,d.percent]);
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
 	<div id="container" style="min-width:700px;height:500px"></div>
</body>
</html>