<? 
	session_start();
	if($_SESSION['userid']==""){
	echo"<script>window.alert('잘못된 접근입니다.'); location.replace('../');</script>";}
	include "../lib/dbconn.php";       // dconn.php 파일을 불러옴
	$pnum = $_SESSION['patient'];
    $sql = "select * from alarm where product_num = '$pnum' order by time";	
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
<?}
if($_SESSION['userlevel']==9)
{
  ?> <script>
	    location.replace('alarm2.php');
	   </script>;
<?}
?>
<script>

function check_input()
{
 var total_alarm = '<?=$total_alarm?>';
  if(!(document.list.hour.selectedIndex&&document.list.minute.selectedIndex))
  {
    alert("알림시간을 입력하세요");
    return;
  }
  if(total_alarm==5)
  {
    alert("최대 5개의 알람만 설정이 가능합니다.");
    return;
  }
  document.list.submit();
}

function reset_form()
{
  document.list.hour.selectedIndex=0;
  document.list.minute.selectedIndex=0;

  return;
}
</script>
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
<form name="list" method="post" action="insert_alram.php">
<div id="title">
 <img src="../img/title_time.png">
</div> <!-- end of title-->

<div id="list" style="height:40px">
<div id="list_title" style="height:40px">
 <ul>
 <li> 알림시간</li>
 </ul>
 </div>
 <div id="list_content" style="height:40px;width:140px;">
 <ul>
  <li><select class="hour" name="hour">	
   <option value=NULL>몇</option>
   <option value='01'>01</option>
   <option value='02'>02</option>
   <option value='03'>03</option>
   <option value='04'>04</option>
   <option value='05'>05</option>
   <option value='06'>06</option>
   <option value='07'>07</option>
   <option value='08'>08</option>
   <option value='09'>09</option>
   <option value='10'>10</option>
   <option value='11'>11</option>
   <option value='12'>12</option>
   <option value='13'>13</option>
   <option value='14'>14</option>
   <option value='15'>15</option>
   <option value='16'>16</option>
   <option value='17'>17</option>
   <option value='18'>18</option>
   <option value='19'>19</option>
   <option value='20'>20</option>
   <option value='21'>21</option>
   <option value='22'>22</option>
   <option value='23'>23</option>
   <option value='24'>24</option></select>
   시
   <select class="minute" name="minute">
   <option value=NULL>몇</option>
   <option value='00'>00</option>
   <option value='05'>05</option>
   <option value='10'>10</option>
   <option value='15'>15</option>
   <option value='20'>20</option>
   <option value='25'>25</option>
   <option value='30'>30</option>
   <option value='35'>35</option>
   <option value='40'>40</option>
   <option value='45'>45</option>
   <option value='50'>50</option>
   <option value='55'>55</option></select>
   분
   </li>     
   </ul>
   </div>
<div id="button" style="margin-top:0px; height:40px;"><a href="#"><img src="../img/button_save.png" onclick="check_input()"></a>&nbsp;&nbsp;
<a href="#"><img src="../img/button_cancel.png" onclick="reset_form()"></a>
</div>
<div class="clear"></div>
</div>
</form>
<div style="height:100px;text-align:left;margin:10px 0 0 20px;font-size:16px; line-height:2.0em">
<?		
   for ($i=0; $i<$total_alarm; $i++)                    
   {?>
	   <form name="delete_alarm<?=$i?>" method="post" action="delete_alarm.php">
      <?mysql_data_seek($result, $i);     // 포인터 이동        
      $row = mysql_fetch_array($result); // 하나의 레코드 가져오기"
	  $hour = strtok($row['time'],':');
	  $min = strtok(':');
	  ?>알림시간 <?=$hour?> : <?=$min?>
	  <a href=# onclick=document.forms.delete_alarm<?=$i?>.submit() style="color:red;"> 삭제</a><br/>
	  <input type="hidden" name="hour" value =<?=$hour?>>
	  <input type="hidden" name="minute" value =<?=$min?>>
	  </form>
   <?}
?>
</div>
</div>
</div> <!-- end of col2 -->
</div> <!-- end of content -->
</div> <!-- end of wrap-->

</body>
</html>

