<? session_start(); ?>
<meta charset="utf-8">
<?

	$userid=$_SESSION['userid'];
	$product_num=$_POST['product_num'];

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
	$sql = "delete from patient ";
	$sql .= "where product_num='$product_num' and id='$userid'";
	
	$res = mysql_query($sql, $connect);  // $sql 에 저장된 명령 실행
	if($res == false)
	{
	?>
	<script>
	alert("sql에러!!\n 환자삭제실패");
	</script>
	<?
	}
	mysql_close();                // DB 연결 끊기
	echo($sql);
	echo "
	   <script>
	    location.href = 'list_patient.php';
	   </script>
	";
?>

  
