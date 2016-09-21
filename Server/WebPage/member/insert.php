<meta charset="utf-8">
<?php

	$id=$_POST['id'];
	$passwordt=$_POST['password'];
	$password=sha1($passwordt);
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



   $regist_day = date("Y-m-d (H:i)");   // 현재의 '년-월-일-시-분'을 저장

   include "../lib/dbconn.php";       // dconn.php 파일을 불러옴

   $sql = "select * from member where id=".$_POST['id'];
   $result = mysql_query($sql, $connect);
   $exist_id = mysql_num_rows($result);

   if($exist_id) {
     echo("
           <script>
             window.alert('해당 아이디가 존재합니다.')
             history.go(-1)
           </script>
         ");
         exit;
   }
   else
   {            // 레코드 삽입 명령을 $sql에 입력

		$sql = "insert into member (id,pass,name, medical_num, social_num, product_num, relative1, emergency_num1, relative2, emergency_num2, address, medicine, medical_history, special, regist_day,level) ";
		$sql .= "values('$id','$password','$name', '$medical_num', '$social_num', '$product_num', '$relative1', '$emergency_num1', '$relative2', '$emergency_num2', '$address', '$medicine', '$medical_history', '$special', '$regist_day',7)";

		mysql_query($sql, $connect); // $sql 에 저장된 명령 실행
		$sql = "insert into member_pos (id,level) ";
		$sql .= "values('$id',7)";

		mysql_query($sql, $connect); // $sql 에 저장된 명령 실행
   }

   mysql_close();                // DB 연결 끊기
	 ?>
     <script>
      alert("환영합니다 <?=$id?>님");
	    location.href ="../";
	   </script>

