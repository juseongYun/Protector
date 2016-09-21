<? 
	session_start(); 
  $table = "member";
?>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta charset="utf-8">
<link href="../css/common.css" rel="stylesheet" type="text/css" media="all">
<link href="../css/list.css" rel="stylesheet" type="text/css" media="all">
</head>
<?
  include "../lib/dbconn.php";
  $userid = $_SESSION['userid'];
  $sql = "select * from $table where id = '$userid'";
  $result = mysql_query($sql, $connect);
  $row = mysql_fetch_array($result);
?>
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
<form name="list" method="post" action="insert.php">
<div id="title">
 <img src="../img/title_userinfo.png">
</div> <!-- end of title-->

<div id="list">
<div id="list_title">
 <ul>
 <li> 이름</li>
 <li> 의료보험번호</li>
 <li> 주민등록번호</li>
 <li> 제품번호</li>
 <li> 비상연락망1</li>
 <li> 비상연락망2</li>
 <li> 주소</li>
 <li> 복용약</li><li></li><li></li>
 <li> 병력</li><li></li><li></li><li></li>
 <li> 특이사항</li><li></li>
 </ul>
 </div>
 <div id="list_content">
 <ul>  
    <li><?= $row[name] ?></li>
    <li><?= $row[medical_num] ?></li>
    <li><?= $row[social_num] ?></li>
    <li><?= $row[product_num] ?></li>
    <li><?= $row[relative1] ?> : <?= $row[emergency_num1] ?></li>
    <?
      if($row[relative2]!="선택하세요")
      {
    ?>
       <li><?= $row[relative2] ?><?= $row[emergency_num2] ?></li>
    <?
      }
    ?>
    <li><?= $row[address] ?></li>
    <li style="height:75px";><?= $row[medicine] ?></a></li>
    <li style="height:100px";><?= $row[medical_history] ?></li>
    <li style="height:181px";><?= $row[special] ?></li>
</ul>
</div>
<div class="clear"</div>	
</div>
<?
  if($_SESSION['userlevel'] <= 7)
  {
?>
<div id="button"><a href="../greet/list.php"><img src="../img/button_save.png"></a>
<?
  }
?>
</div>
</form>
</div> <!-- end of col2 -->
</div> <!-- end of content -->
</div> <!-- end of wrap-->

</body>
</html>

