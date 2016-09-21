<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
</head>
<body>
<?
	$product_num=$_GET['product_num'];
	
	$medicine=$_GET['medicine'];
	$medical_history=$_GET['medical_history'];
	$special=$_GET['special'];
	$mode=$_GET['mode'];
	
	include "../lib/dbconn.php";       // dconn.php 파일을 불러옴
	$sql = "select * from member where product_num='$product_num'";
	$result = mysql_query($sql, $connect);	  
    $row = mysql_fetch_array($result); 
	if($product_num != $row[product_num])
    {
      echo"wrong product_num";
      exit;
   }
	$regist_day = date("Y-m-d (H:i)");  // 현재의 '년-월-일-시-분'을 저장
	
	if ($mode=="modify")
	{
		$sql = "update member set medicine='$medicine', medical_history='$medical_history', special='$special' where product_num='$product_num'";
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

		$sql = "insert into member (medicine, medical_history, special, regist_day) ";
			$sql .= "values('$medicine', '$medical_history', '$special', '$regist_day')";
	}
  echo $sql;
	mysql_query($sql, $connect);  // $sql 에 저장된 명령 실행
	mysql_close();                // DB 연결 끊기
?>
</body>
</html>
