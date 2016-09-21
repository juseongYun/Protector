<? 
	session_start();
	if($_SESSION['userid']==""){
	echo"<script>window.alert('잘못된 접근입니다.'); location.replace('../');</script>";}
	include "../lib/dbconn.php";       // dconn.php 파일을 불러옴
	$pnum = $_SESSION['userproduct'];
    $sql = "select * from alarm where product_num = '$pnum' order by time ";	
	$result = mysql_query($sql, $connect);	
    $row = mysql_fetch_array($result);
	if($row ==NULL)$total_alarm=0;
	else	$total_alarm = mysql_num_rows($result);
	// 현재 등록되있는 알람 수
?>
<!DOCTYPE HTML>
<html>
<head> 
<meta charset="utf-8">
<link href="../css/common.css" rel="stylesheet" type="text/css" media="all">
<link href="../css/alram.css" rel="stylesheet" type="text/css" media="all">
<?
if($_SESSION['userid']==NULL)
{
  ?> <script>
	  alert("wrong access");
	    location.replace('../');
	   </script>
<?}?>
</head>

<body>
<div id="wrap">
<div id="header">
 <? include "../lib/top_login2.php"; ?>
</div>  <!-- end of header -->

<div id="menu">
 <? include "../lib/top_menu2.php"; ?>
</div>  <!-- end of menu --> 

<div id="content">
<div id="col1">
<div id="left_menu">
 <? include "../lib/left_menu.php"; ?>
</div>
</div> <!-- end of col1-->

<div id="col2">
<div id="title">
 <img src="../img/title_time.png">
</div> <!-- end of title-->
<div style="height:100px;text-align:left;margin:10px 0 0 20px;font-size:16px; line-height:2.0em">

<?		
   for ($i=0; $i<$total_alarm; $i++)                    
   {
      mysql_data_seek($result, $i);     // 포인터 이동        
      $row = mysql_fetch_array($result); // 하나의 레코드 가져오기"
	  $hour = strtok($row['time'],':');
	  $min = strtok(':');
	  echo "알림시간 $hour : $min<br/>";
   }
?>
</div>
</div> <!-- end of col2 -->
</div> <!-- end of content -->
</div> <!-- end of wrap-->

</body>
</html>

