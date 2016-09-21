<?
	session_start();
?>
<!DOCTYPE HTML>
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8>">
<link rel="stylesheet" type="text/css" href="css/common.css">
</head>

<body>
<div id="wrap">
	<div id="header">
    <? include "./lib/top_login1.php"; ?>
	</div>  <!-- end of header -->

	<div id="menu">
	<? include "./lib/top_menu1.php"; ?>
	</div>  <!-- end of menu --> 

  <div id="content">
		<div id="main_img"><img src="./img/main.jpg"
style="    width: 985px;
"></div>

<!-- 최근 글 불러오기 시오후 1:02 2016-02-20작 -->
        <? include "./lib/func.php"; ?>

		<div id="latest">
			<div id="latest1">
				<div id="title_latest1" style=" height: 45px;">
				<img src="./img/1tab3_3_notice.png"></div>
	  			<div class="latest_box">
				<? latest_article("notice", 10, 30); ?>
				</div>
			</div>
			<div id="latest2">
				<div id="title_latest2" style=" height: 45px;">
				<img src="./img/2tab3_3_notice.png"></div>
				<div class="latest_box">
				<img src="./img/3tab3_3_notice.png"style="padding-left: 110px;padding-top: 10px;">
				<form style="height: 115px;"method="post" action="login/login.php">	
					<input type="text" name="product_num" 
						style="margin-left: 90px; margin-top: 20px; height: 30px; 
							width: 170px; margin-right: 10px;"<br>
					<input type="submit" name="choice" value="입력"
						style="height: 54px; width: 84px;"><br>
					<input type="hidden" name="isem" value="y"/>
				</form>
			</div>

		</div>
<div id="latest3" style="
    height: 100px;
    width: 1000px;
    padding-top: 10;
    padding-top: 10px;
">
	<div id="title_latest3" style=" height: 90px;">
	<img src="./img/logo2.png"><img src="./img/4tab3_3_notice.png"style="
    padding-left: 70px;
"></div>

</div>
</div>
<!-- 최근 글 불러오기 끝 -->

  </div> <!-- end of content -->
</div> <!-- end of wrap -->

</body>
</html>
