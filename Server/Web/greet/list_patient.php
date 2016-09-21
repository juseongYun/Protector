<!DOCTYPE HTML >
<html>
 <head>
 <meta charset= "utf-8">
<link href="../css/common.css" rel="stylesheet" type="text/css" media="all">
<link href="../css/alram.css" rel="stylesheet" type="text/css" media="all">
 <? 
	session_start(); 
	$userid = $_SESSION['userid'];	
	if($userid==NULL)
	{
	echo"<script>window.alert('잘못된 접근입니다.'); location.replace('../');</script>";}
  include "../lib/dbconn.php";
?>
<script>

function check_input()
{
 var total_alarm = '<?=$total_alarm?>';
  if(!document.list.product_num.value)
  {
    alert("제품번호를 입력하세요");
    return;
  }
  document.list.submit();
}

function reset_form()
{
  document.list.product_num.value="";
  return;
}
</script>
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
<form name="list" method="post" action="insert_patient.php">
<div id="title">
 <img src="../img/title_userinfo.png">
</div> <!-- end of title-->

<div id="list" style="height:40px">
<div id="list_title" style="height:40px">
 <ul>
 <li> 제품번호</li>
 </ul>
 </div><!-- list_title-->
 <div id="list_content" style="height:40px;width:140px;">
 <ul>
  <li style="padding-top:0px;height:40px; ">
  <input type="text" style="height:30px;width:200px;font-size:20px"class="product_num" name="product_num">
   </li>     
   </ul>
   </div><!-- list_content-->
<div id="button" style="margin-top:0px; height:40px;"><a href="#"><img src="../img/button_save.png" onclick="check_input()"></a>&nbsp;&nbsp;
<a href="#"><img src="../img/button_cancel.png" onclick="reset_form()"></a>
</div><!--button-->
<div class="clear"></div>
</div><!--clear-->
</form>
<div style="height:100px;text-align:left;margin:10px 0 0 20px;">
<?php
	$sql = "select * from patient where id='$userid' order by product_num";
	$result = mysql_query($sql, $connect);
	$total_patient = mysql_num_rows($result); // 전체 글 수
echo "total $total_patient total <br>";
#patient_printing
?>
<table style="font-size:16px;width:80%;">
<tr style="font-weight:bold;background-color:#cccccc;">
<th>이름</th>
<th>id</th>
<th>제품번호</th>
<th></th>
<th></th>
</tr>

<?
	for ($i=0; $i<$total_patient; $i++)                    
   {?>
   <tr>
	<form name="delete<?=$i?>" method="post" action="delete_patient.php">
	<?
	  mysql_data_seek($result, $i);     // 포인터 이동        
      $row = mysql_fetch_array($result); // 하나의 레코드 가져오기"
	  $product_num = $row['product_num'];
	  $sql2 = "select id,name from member where product_num='$product_num'";
	  $result2 = mysql_query($sql2,$connect);
	  $row2 = mysql_fetch_array($result2);
	  ?><td><?=$row2[name]?></td><td><?=$row2[id]?></td><td><?=$product_num?></td><td><a href="patient_login.php?product_num=<?=$product_num?>" style="color:blue;">이동</a></td>
	  <td><a href=# onclick=document.forms.delete<?=$i?>.submit() style="color:red;">삭제</a><td>
	  <input type="hidden" name="product_num" value="<?=$product_num?>"/>
	  </form>
   </tr>
   <?}
   echo'</table>';
?>
</div>
</div> <!-- end of col2 -->
</div> <!-- end of content -->
</div> <!-- end of wrap-->   
</body>
</html>
