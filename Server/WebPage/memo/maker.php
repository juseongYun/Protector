<!DOCTYPE HTML>
<html>
<head> 
<meta charset="utf-8">
<link href="../css/common.css" rel="stylesheet" type="text/css" media="all">
<link href="../css/board4.css" rel="stylesheet" type="text/css" media="all">
</head>

<body>
<? 
	session_start(); 
	$userid=$_SESSION['userid'];
?>
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
          <? include "../lib/left_menu_03.php"; ?>
        </div>
      </div> <!-- end of col1 -->

      <div id="col2">
        <div id="intro">
          <img id="maker" src="../img/title_maker.png">                          
        </div><!-- end of title-->
                                                           
        <div id="maker">
          <img id="maker_photo" src="../img/maker_photo.PNG">
           <!-- <div class="clear"></div> -->
        </div> <!-- end of maker -->

      </div> <!-- end of col2 -->
    </div> <!-- end of content -->
  </div> <!-- end of wrap -->

</body>
</html>

