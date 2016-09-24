<? session_start(); ?>
<meta charset="utf-8">
<?
	$level=$_SESSION['userlevel'];
	if($level==5)	
		$userid=$_SESSION['pid'];
	
	else
		$userid=$_SESSION['userid'];
	
	$mode=$_POST['mode'];
	$num=$_POST['num'];
	$name=$_POST['name'];
	$medical_num=$_POST['medical_num'];

	$social_num1=$_POST['social_num1'];
	$social_num2=$_POST['social_num2'];
  $social_num=$social_num1.'-'.$social_num2;

	$product_num=$_POST['product_num'];

	$relative1=$_POST['relative1'];
	$emergency_num1_1=$_POST['emergency_num1_1'];
	$emergency_num1_2=$_POST['emergency_num1_2'];
	$emergency_num1_3=$_POST['emergency_num1_3'];

  $relative2=$_POST['relative2'];
	$emergency_num2_1=$_POST['emergency_num2_1'];
	$emergency_num2_2=$_POST['emergency_num2_2'];
	$emergency_num2_3=$_POST['emergency_num2_3'];

	$emergency_num1=$emergency_num1_1.'-'.$emergency_num1_2.'-'.$emergency_num1_3;
	$emergency_num2=$emergency_num2_1.'-'.$emergency_num2_2.'-'.$emergency_num2_3;

	$address=$_POST['address'];
	$medicine=$_POST['medicine'];
	$medical_history=$_POST['medical_history'];
	$special=$_POST['special'];
	
	if(!$userid) {
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

		$sql = "insert into member (name, medical_num, social_num, product_num, relative1, emergency_num1, relative2, emergency_num2, address, medicine, medical_history, special, regist_day) ";
		$sql .= "values('$name', '$medical_num', '$social_num', '$product_num', '$relative1', '$emergency_num1', '$relative2', '$emergency_num2', '$address', '$medicine', '$medical_history', '$special', '$regist_day')";
	}

	mysql_query($sql, $connect);  // $sql 에 저장된 명령 실행
	mysql_close();                // DB 연결 끊기
	echo($sql);?>
	
	   <script>
	    location.href = 'list.php?page=<?=$page?>';
	   </script>
  
