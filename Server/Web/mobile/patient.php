<!DOCTYPE HTML >
<html>
 <head>
 <meta charset= "utf-8">
<? 
	$table = "patient";
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
		if($userid==NULL)
		{
			echo"userid Please!!";
			exit;
		}
		$sql = "select * from $table where userid='$userid' order by prodcut_time";
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
		$userid=$row[userid];
		if($pass != $userpass)
		{
			echo("wrong pass!");
			exit;
		}
		if($userid == 'admin')		
		$sql = "select * from $table order by product_num";
		else
		$sql = "select * from $table where userid='$user_id' order by product_num";
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
		$sql = "select * from $table where id='$userid' order by product_num";
	}
	$result = mysql_query($sql, $connect);
	$total_patient = mysql_num_rows($result); // 전체 글 수
echo "total $total_patient total <br>";
#patient_printing
	for ($i=0; $i<$total_patient; $i++)                    
   {
	   mysql_data_seek($result, $i);     // 포인터 이동        
          $row = mysql_fetch_array($result); // 하나의 레코드 가져오기"
	  $product_num = $row['product_num'];
	  ?>등록된 제품<?=$product_num?> 등록된 제품<br/>
   <?}
?>   
</body>
</html>
