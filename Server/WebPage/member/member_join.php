<? 
	session_start(); 
?>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta charset="utf-8">
<link href="../css/common.css" rel="stylesheet" type="text/css" media="all">
<link href="../css/join2.css" rel="stylesheet" type="text/css" media="all">
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
      <?
         include "../lib/left_menu.php";
      ?>
      </div>
    </div> <!-- end of col1 -->

    <div id="col2">
      <form name="member_form_join" method="post" action="member_join.php">
      <div id="title">
        <img src="../img/title_join.png">
      </div>

      <div id="join_msg">
        <img src="../img/comment_join.png">
       </div>

      <div id="join">
        <ul>
          <a href="#" onclick ="sub(document.hide,'../member/member_form.php')"><img src="../img/join_user.png">
          <a href="#" onclick ="sub(document.hide,'../member/member_form_doctor.php')"><img src="../img/join_doctor.png">
        </ul>
      </div> <!-- end of join-->

    </div> <!-- end of col2 -->
  </div>_<!-- end of content-->
</div> <!-- end of wrap-->

</body>
</html>

