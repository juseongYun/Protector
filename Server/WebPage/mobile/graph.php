
<html>
<head> 
<meta charset="utf-8">
<?
$product = $_GET['product_num'];
$date = date("Y-m-d H:i",strtotime("-1 hours"));
include"../lib/dbconn.php";
$sql = "select * from graph where product_num ='$product' and date >= '$date'";
 $result =mysql_query($sql,$connect);
 $total_record = mysql_num_rows($result);
 echo "totalrecord=".$total_record."=totalrecord</br>";
 $v="^";
 for($i=0;$i<$total_record;$i++){
     mysql_data_seek($result,$i);
     $row = mysql_fetch_array($result);
    $v=$v.$row[date]."^".$row[temperature]."^".$row[heartbeat]."^";
 }
	?>
     
</head>
<?
	include "../lib/dbconn.php";
?>
<body>
<?echo $v;?>
</body>
</html>

