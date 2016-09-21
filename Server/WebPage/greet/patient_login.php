<!DOCTYPE HTML >
<html>
 <head>
 <meta charset= "utf-8">
<? 
	session_start();
	include "../lib/dbconn.php";
	$id = $_SESSION['userid'];
	$patient=$_GET['product_num'];
?>
</head>
<body>
<?

	$sql = "select * from patient where id='$id' and  product_num='$patient' order by product_num";
	$res = mysql_query($sql, $connect);
	if($res==false)
	{?>
	<script>
	alert("잘못된 접근입니다.");
		location.href='list_patient.php';
	</script>
	<?
	exit;
	}
	$sql = "select * from member where product_num ='$patient' order by product_num";
	$res = mysql_query($sql, $connect);
	$row = mysql_fetch_array($res);
	if($_SESSION['patient']!=false)
	{
		unset($_SESSION['patient']);
		unset($_SESSION['pname']);
		unset($_SESSION['pid']);
	}
	$_SESSION['patient']=$patient;
	$_SESSION['pname']=$row['name'];
	$_SESSION['pid']=$row['id'];
?>
<script>
location.href="list_patient.php";
</script>
</body>
</html>
