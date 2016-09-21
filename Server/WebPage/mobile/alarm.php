<!DOCTYPE HTML >
<html>
 <head>
 <meta charset= "utf-8">
<? 
	$table = "alarm";
	$userid=$_GET['userid'];	
	$mode=$_GET['mode'];
	$page=$_GET['page'];
	$userpass=$_GET['pass'];
	$product_num=$_GET['product_num'];

  include "../lib/dbconn.php";
?>
<body>
<?php
	if($mode==NULL)
	{
		echo"Mode Select Please!!";
		exit;
	}
	else if($mode==1)
	{
		if($product_num==NULL)
		{
			echo"Product Num Please!!";
			exit;
		}
		$sql = "select * from $table where product_num='$product_num' order by time";
	}
	else if($mode ==2)
	{
		if($userid==null){
			echo"insert ID!!\n";
			exit;
		}
		$sql ="select * from member where id = '$userid'";
		$result = mysql_query($sql, $connect);
		$row = mysql_fetch_array($result);
		$pass=$row[pass];
		$product_num=$row[product_num];
		if($pass != $userpass)
		{
			echo("wrong pass!");
			exit;
		}
		if($userid == 'admin')		
		$sql = "select * from $table order by time";
		else
		$sql = "select * from $table where product_num='$product_num' order by time";
	}
	else if($mode ==3)
	{
		$sql ="select pass from member_doctor where id = '$userid'";
		$result = mysql_query($sql, $connect);
		$row = mysql_fetch_array($result);
		$pass=$row[pass];
		if($pass != $userpass)
		{
			echo("wrong pass!");
			exit;
		}
		$sql = "select * from $table where product_num='$product_num' order by time";
	}
	$result = mysql_query($sql, $connect);
	$total_alarm = mysql_num_rows($result); // 전체 글 수
echo "total $total_alarm total <br>";
#alarm_printing
	for ($i=0; $i<$total_alarm; $i++)                    
   {
	   mysql_data_seek($result, $i);     // 포인터 이동        
      $row = mysql_fetch_array($result); // 하나의 레코드 가져오기"
	  $hour = strtok($row['time'],':');
	  $min = strtok(':');
	  ?>알림시간<?=$hour?> : <?=$min?>알림시간<br/>
   <?}
?>   
</body>
</html>
