<? 
	session_start(); 
	$table = "download";	
	$mode=$_GET['mode'];
	$page=$_GET['page'];
if($_SESSION['userlevel']==5)
{
	$userid=$_SESSION['pid'];$userproduct=$_SESSION['patient'];
}
else{
	$userid=$_SESSION['userid'];$username=$_SESSION['userproduct'];
}
?>
<!DOCTYPE HTML>
<html>
<head> 
<meta charset="utf-8">
<link href="../css/common.css" rel="stylesheet" type="text/css" media="all">
<link href="../css/board3.css" rel="stylesheet" type="text/css" media="all">

<?
  include"../lib/dbconn.php";
  if($userid == NULL)
  {
    ?>
      <script>
      window.alert('잘못된 접근입니다.');
      location.replace('../');
      </script>
    <?
  }
     $date = date("Y-m-d H:i",strtotime("-1 hours"));
    $sql = "select * from graph where product_num ='$product' and date >= '$date'";
    $result =mysql_query($sql,$connect);
    $total_record = mysql_num_rows($result);
    $v="['시간', '체온','심박']";
		for($i=0;$i<$total_record;$i++){
      mysql_data_seek($result,$i);
      $row = mysql_fetch_array($result);
      $v=$v.",['$row[date]',".$row[temperature].",".$row[heartbeat]."]";
    }

		$v=$v."]";
    if($total_record)
{
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
			   width:800,
			   height:500,
               curveType: 'function',
               'chartArea': {'width': '97%'},
               legend: { position: 'bottom' }
               };
            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
               chart.draw(data, options);
             }
    </script>

<?}
  
?>

</head>
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
			include "../lib/left_menu.php";
?>
		</div>
	</div>

	<div id="col2">        
		<div id="title">
			<img src="../img/title_graph.png">
		</div>

   <div id="content" style="width:800px;height:50px;overflow:auto;">
   <div id="curve_chart"><?if($total_record == 0)echo"No Data!!!<br/>";?></div></div>

	</div> <!-- end of col2 -->
  </div> <!-- end of content -->
</div> <!-- end of wrap -->

</body>
</html>
