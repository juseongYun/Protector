<? session_start(); ?>
<meta charset="utf-8">
<?
	if($_SESSION['userlevel']!=5)
	{
	$product_num=$_SESSION['userproduct'];
	}
	else
		$product_num=$_SESSION['patient'];
	$hour=$_POST['hour'];
	$minute=$_POST['minute'];

	
  $time="'".$hour.':'.$minute."'";


	if(!$product_num) {
		echo("
		<script>
	     window.alert('로그인 후 이용해 주세요.')
	     history.go(-1)
	   </script>
		");
		exit;
	}


	$regist_day = date("Y-m-d (H:i)");  // 현재의 '년-월-일-시-분'을 저장
	include "../lib/dbconn.php";       // dconn.php 파일을 불러옴
	$sql = "insert into alarm ";
	$sql .= "values('$product_num', $time)";
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
	if($_SESSION['userlevel']==5)
		echo "
	   <script>
	    location.href = 'patient_alarm.php';
	   </script>
	";
	else
	echo "
	   <script>
	    location.href = 'alarm.php';
	   </script>
	";
?>

  
