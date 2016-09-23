<? session_start(); ?>
<meta charset="utf-8">
<?

	if($_SESSION['userlevel'] == 5)
	{
		
		$userid=$_SESSION['pid'];
		$product_num=$_SESSION['patient'];	
		$path='patient_alarm.php';
	}
	else
	{
		$userid=$_SESSION['userid'];
		$product_num=$_SESSION['userproduct'];
		$path='alarm.php';
	}
	$hour=$_POST['hour'];
	$minute=$_POST['minute'];

	
  $time=$hour.':'.$minute;


	if(!$userid) {
		echo("
		<script>
	     window.alert('로그인 후 이용해 주세요.')
	     history.go(-1)
	   </script>
		");
		exit;
	}
	include "../lib/dbconn.php";       // dconn.php 파일을 불러옴
	$sql = "delete from alarm ";
	$sql .= "where product_num='$product_num' and time='$time'";
	?><script>alert("<?=$sql?>");</script><?
	$res = mysql_query($sql, $connect);  // $sql 에 저장된 명령 실행
	if($res == false)
	{
	?>
	<script>
	alert("sql에러!!\n 이미 설정된 알람시간인지 확인하시오.");
	</script>
	<?
	}
	mysql_close();                // DB 연결 끊기
	echo($sql);
	?>
	   <script>
	    location.href ='<?=$path?>'
	   </script>

  
