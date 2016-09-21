<meta charset="utf-8">

<?

/*
   delete_alarm.php
   1.$product_num 제품번호
   2.$hour삭제할 시 $minute 삭제할 분
   3. 이동하기전에 alarm.php에서 알람 수를 확인하여
   5개 초과시 에러처리
   */

  $table = "alarm";
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
        if($product_num==NULL)
        {
            echo"Product Num Please!!";
            exit;
        }
    $sql = "select * from $table where product_num='$product_num' order by time";
    
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
     $product_num=$row[product_num];
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

  	$table = "alarm";	
   	$product_num=$_GET['product_num'];
 }
  $hour=$_GET['hour'];
	$minute=$_GET['minute'];
	$time=$hour.":".$minute;
	$sql = "insert into alarm ";
	$sql .= "values('$product_num', '$time')";
	$res = mysql_query($sql, $connect);  // $sql 에 저장된 명령 실행
	if($res == false)
	{
	echo'sql에러!!\n 이미 설정된 알람시간인지 확인하시오.';
	}
	mysql_close();                // DB 연결 끊기
?>
  
