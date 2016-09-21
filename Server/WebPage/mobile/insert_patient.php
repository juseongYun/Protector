<meta charset="utf-8">

<?
  $table = "patient";
  $userid=$_GET['userid'];
  $mode=$_GET['mode'];
  $userpass=$_GET['pass'];
  $product_num=$_GET['product_num'];
  include "../lib/dbconn.php";
?>
<body>
<?php
    if($mode==NULL)
    {
    echo"Mode Select Please!!";
    exit;
    }
    else if($mode==1)
    {
        if($userid==NULL)
        {
            echo"login Please!!";
            exit;
        }
    $sql = "select * from $table where userid='$userid' order by product_num";
   }
    else if($mode ==2)
    {
       if($userid==null){
         echo"insert ID!!\n";
         exit;
     }
     $sql ="select * from member where id = '$userid'";
     $result = mysql_query($sql, $connect);
     $row = mysql_fetch_array($result);
     $pass=$row[pass];
     $user_id=$row[user_id];
     if($pass != $userpass)
     {
        echo("wrong pass!");
        exit;
     }	
   }
  else if($mode ==3)
 {
     $sql ="select pass from member_doctor where id = '$userid'";
     $result = mysql_query($sql, $connect);
     $row = mysql_fetch_array($result);
     $pass=$row[pass];
     if($pass != $userpass)
     {
         echo("wrong pass!");
         exit;
     }

  	$table = "patient";	
   	$userid=$_GET['userid'];
 }
  $product_num=$_GET['product_num'];
	$sql = "insert into patient";
	$sql .= " values('$userid', '$product_num')";
	$res = mysql_query($sql, $connect);  // $sql 에 저장된 명령 실행
	if($res == false)
	{
	echo'sql에러!!\n 이미 등록된 제품번호인지 확인하시오.';
	}
	mysql_close();                // DB 연결 끊기
?>
  
