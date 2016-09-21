<? 
	session_start(); 
	$table = "download";	
	$mode=$_GET['mode'];
	$page=$_GET['page'];
?>
<!DOCTYPE HTML>
<html>
<head> 
<meta charset="utf-8">
<link href="../css/common.css" rel="stylesheet" type="text/css" media="all">
<link href="../css/board3.css" rel="stylesheet" type="text/css" media="all">

<?
		$v="['년도', '매출']";
		for($i=1990;$i<2300;$i++)
			$v=$v.",['$i',".mt_rand(35,40)."]";
		
		$v=$v."]";
	?>
      <script
      src="https://www.gstatic.com/charts/loader.js"></script>
       <script type="text/javascript">
         google.charts.load('current', {'packages':['corechart']});
         google.charts.setOnLoadCallback(drawChart);
         function drawChart() {
            var data = google.visualization.arrayToDataTable([
             <?=$v?>);
            var options = {
               title: 'Company Performance',
			   width:2000,
			   height:800,
               curveType: 'function',
               'chartArea': {'width': '97%'},
               legend: { position: 'bottom' }
               };
            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
               chart.draw(data, options);
             }
    </script>



</head>
<?
	include "../lib/dbconn.php";
?>
<body>
<div id="wrap">
  <div id="header">
    <? include "../lib/top_login2.php"; ?>
  </div>  <!-- end of header -->

  <div id="menu">
	<? include "../lib/top_menu2.php"; ?>
  </div>  <!-- end of menu --> 

  <div id="content">
	<div id="col1">
		<div id="left_menu">
<?
			include "../lib/left_menu_02.php";
?>
		</div>
	</div>

	<div id="col2">        
		<div id="title">
			<img src="../img/menu02.png">
		</div>

   <div id="content" style="width:800px;height:50px;overflow:auto;">
   <div id="curve_chart"></div></div>

	</div> <!-- end of col2 -->
  </div> <!-- end of content -->
</div> <!-- end of wrap -->

</body>
</html>
