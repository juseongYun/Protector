<!DOCTYPE HTML >
<html>
 <head>
 <meta charset= "utf-8">
<? 
	$table = "photo";
	$userid=$_GET['userid'];	
	$mode=$_GET['mode'];
	$page=$_GET['page'];
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
		$sql ="select id from member where product_num = '$product_num'";
		$result = mysql_query($sql, $connect);
		$row = mysql_fetch_array($result);
		$id=$row[id];
		$sql = "select * from $table where id='$id' order by num desc";
	}
	else if($mode ==2)
	{
		$sql ="select pass from member where id = '$userid'";
		$result = mysql_query($sql, $connect);
		$row = mysql_fetch_array($result);
		$pass=$row[pass];
		if($pass != $userpass)
		{
			echo("wrong pass!");
			exit;
		}
		if($userid == 'admin')
		
		$sql = "select * from $table order by num desc";
		else
		$sql = "select * from $table where id='$userid' order by num desc";
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
		$sql = "select * from $table where product_num='$product_num' order by num desc";
	}
	$result = mysql_query($sql, $connect);
	$total_record = mysql_num_rows($result); // 전체 글 수
    #photo printing
    $a_img = array();
    $col = 1; //no. of columns in a page
    $maxrow = 4; //no. of rows in a page

 $totxpage = $col*$maxrow; // images x page
 $total_page = ($total_record%$totxpage==0)?((int)$total_record/$totxpage):((int)($total_record/$totxpage)+1); // number of total pages
?>
<?
 if($total_record == 0){
   echo "<img align=center src=../img/No_image_available.png / style='display:block;margin-left:auto;margin-right:auto;'>";
	exit;
   }
 else
{

  // start page
  if($page=="" || $page==1)
  {
   $x=0;
   $page = 1;
  }
  else
   $x = (($page-1)*($totxpage));
  $r=0;

 // page break
$scale=$col*$maxrow;
$start = ($page - 1) * $scale;

for ($i=$start; $i<$start+$scale && $i < $total_record; $i++)                    
   {
      mysql_data_seek($result, $i);       
      // 가져올 레코드로 위치(포인터) 이동  
      $row = mysql_fetch_array($result);       
      // 하나의 레코드 가져오기
	
	  $item_num     = $row[num];
	  $item_id      = $row[id];
	  $item_file	= $row[file_copied];
      $item_date    = $row[regist_day];
	  $item_date = substr($item_date, 0, 10);  

	  $item_subject = str_replace(" ", "&nbsp;", $row[subject]);
  	   $number--;

	//echo "$item_num<br>$item_id<br/>$item_date<br/>$item_subject</br>$item_file<br/>";
   echo "$item_file<br/>";
    if($r==$maxrow)
   {
    break;
   }
   else
   $x++;
  }
 }
?>   
</body>
</html>
