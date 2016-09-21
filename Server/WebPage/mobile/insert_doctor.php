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
	$job=$_GET['job'];
	$name=$_GET['name'];
	
	$license_num=$_GET['license_num'];
	
	$phone_num=$_GET['phone_num'];
	
	include "../lib/dbconn.php";       // dconn.php 파일을 불러옴
	$sql = "select * from member_doctor where id='$userid'";
	$result = mysql_query($sql, $connect);	  
    $row = mysql_fetch_array($result); 
	$regist_day = date("Y-m-d (H:i)");  // 현재의 '년-월-일-시-분'을 저장
	
	if ($mode=="modify")
	{
		$sql = "update member_doctor set name='$name', license_num='$license_num', phone_num='$phone_num' where id='$userid'";
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

		$sql = "insert into member_doctor (id,pass,job,name,phone_num,regist_day,license_num) ";
		$sql .= "values('$userid','$userpw','$job','$name','$phone_num','$regist_day', '$license_num')";
	}
	mysql_query($sql, $connect);  // $sql 에 저장된 명령 실행	echo"$sql<br/>";
	$sql = "insert into member_pos (id,level) ";
	$sql .= "values('$id',5)";
	mysql_query($sql, $connect); // $sql 에 저장된 명령 실행
	mysql_close();                // DB 연결 끊기
?>
</body>
</html>
