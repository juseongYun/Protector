<? 
	session_start(); 
	$table = "download";	
	$mode=$_GET['mode'];
	$page=$_GET['page'];
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta charset="utf-8">
<link href="../css/common.css" rel="stylesheet" type="text/css" media="all">
<link href="../css/board3.css" rel="stylesheet" type="text/css" media="all">
</head>
<?
	include "../lib/dbconn.php";
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
<?
			include "../lib/left_menu_02.php";
?>
		</div>
	</div>

	<div id="col2">        
		<div id="title">
			<img src="../img/menu02.png">
		</div>
  <div id= "list_content">
  의료기록
  </div>
	</div> <!-- end of col2 -->
  </div> <!-- end of content -->
</div> <!-- end of wrap -->

</body>
</html>
