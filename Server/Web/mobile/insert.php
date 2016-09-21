<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
</head>
<body>
<?
	$userid=$_GET['userid'];
	$userpw=$_GET['userpw'];
	$mode=$_GET['mode'];
	$name=$_GET['name'];
	$medical_num=$_GET['medical_num'];

	$social_num=$_GET['social_num'];

	$product_num=$_GET['product_num'];

	$relative1=$_GET['relative1'];
	$emergency_num1=$_GET['emergency_num1'];

  	$relative2=$_GET['relative2'];
	$emergency_num2=$_GET['emergency_num2'];

	$address=$_GET['address'];
	$medicine=$_GET['medicine'];
	$medical_history=$_GET['medical_history'];
	$special=$_GET['special'];
	
	include "../lib/dbconn.php";       // dconn.php 파일을 불러옴
	$sql = "select * from member where id='$userid'";
	$result = mysql_query($sql, $connect);	  
    $row = mysql_fetch_array($result); 
	$regist_day = date("Y-m-d (H:i)");  // 현재의 '년-월-일-시-분'을 저장
	
	if ($mode=="modify")
	{
		$sql = "update member set name='$name', medical_num='$medical_num',social_num='$social_num',  product_num='$product_num', relative1='$relative1', emergency_num1='$emergency_num1', relative2='$relative2',emergency_num2='$emergency_num2', address='$address', medicine='$medicine', medical_history='$medical_history', special='$special' where id='$userid'";
	}
	else
	{
		if ($html_ok=="y")
		{
			$is_html = "y";
		}
		else
		{
			$is_html = "";		
		}

		$sql = "insert into member (id,pass,name, medical_num, social_num, product_num, relative1, emergency_num1, relative2, emergency_num2, address, medicine, medical_history, special, regist_day, level) ";
			$sql .= "values('$userid','$userpw','$name', '$medical_num', '$social_num', '$product_num', '$relative1', '$emergency_num1', '$relative2', '$emergency_num2', '$address', '$medicine', '$medical_history', '$special', '$regist_day',7)";
	}
  echo $sql;
	mysql_query($sql, $connect);  // $sql 에 저장된 명령 실행
	mysql_close();                // DB 연결 끊기
?>
</body>
</html>
