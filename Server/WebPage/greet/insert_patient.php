<meta charset="utf-8">
<!DOCTYPE HTML>
<?
  session_start();
  $table = "patient";
  $userid=$_SESSION['userid'];
  $product_num=$_POST['product_num'];
  include "../lib/dbconn.php";
?>
<body>
<?php
	$sql = "select * from member where product_num='$product_num'";
	$res = mysql_query($sql, $connect);  // $sql 에 저장된 명령 실행
	if($res==false)
	{?>
	<script>
	alert("제품번호를 확인하세요.<?=$product_num?>");
		location.href='list_patient.php';
	</script>
	<?
	exit;}
	$sql = "insert into patient ";
	$sql .= "values('$userid', '$product_num')";
	echo$sql;
	$res = mysql_query($sql, $connect);  // $sql 에 저장된 명령 실행
	if($res == false)
	{
		?>
	<script>
	alert(" 이미 설정된 환자인지 확인하시오.");
	</script>
	<?}
	mysql_close();                // DB 연결 끊기
?>
<script>
	    location.href = 'list_patient.php';
	   </script>
