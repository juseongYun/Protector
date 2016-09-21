<meta charset="utf-8">
<?php

	$id=$_POST['id'];
	$tpassword=$_POST['password'];
	
  $job=$_POST['job'];
	$license_num=$_POST['license_num'];

 	$phone_num_1=$_POST['phone_num_1'];
	$phone_num_2=$_POST['phone_num_2'];
  $phone_num_3=$_POST['phone_num_3'];
 $name = $_POST['name'];
  $phone_num=$phone_num_1.'-'.$phone_num_2.'-'.$phone_num_3;

  $regist_day = date("Y-m-d (H:i)");   // 현재의 '년-월-일-시-분'을 저장

  include "../lib/dbconn.php";       // dconn.php 파일을 불러옴

  $sql = "select * from member where id=".$_POST['id'];
  $result = mysql_query($sql, $connect);
  $exist_id = mysql_num_rows($result);
  $mode=$_POST['mode'];

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
  { // 레코드 삽입 명령을 $sql에 입력
	$password = sha1($tpassword);
	if ($mode=="modify")
	{
		$sql = "update member_doctor set name='$name', pass='$password',job='$job', license_num='$license_num', phone_num='$phone_num' where id='$id'";
		mysql_query($sql, $connect); // $sql 에 저장된 명령 실행
	echo"$sql<br/>";
	mysql_close();                // DB 연결 끊기
	}
	else
	{

	$sql = "insert into member_doctor (id, pass,name, job, license_num, phone_num, regist_day) ";
	$sql .= "values('$id','$password','$name','$job', '$license_num', '$phone_num', '$regist_day')";
	
	mysql_query($sql, $connect); // $sql 에 저장된 명령 실행
	echo"$sql<br/>";
		$sql = "insert into member_pos (id,level) ";
		$sql .= "values('$id',5)";
	mysql_query($sql, $connect); // $sql 에 저장된 명령 실행
	echo"$sql<br/>";
    }
  }

  mysql_close();                // DB 연결 끊기
 ?>
    <script>
    alert("환영합니다 <?=$id?>님");
    location.href ="../";
   </script>

