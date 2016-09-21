<meta charset=utf-8>
<div id="logo"><a href="index.php"><img src="./img/logo.png" border="0"></a></div>
	<div id="moto" style="margin-left:40px;"><img src="./img/moto.png"></div>
	<div id="top_login">
<?
    if(!$_SESSION['userid'])
	{
?>
          <a href="./login/login_form.php">로그인</a> | <a href="./member/member_join.php">회원가입</a>
<?
	}
	else
	{
?>
		<?=$_SESSION['username']?> (level:<?=$_SESSION['userlevel']?>) | 
		<a href="./login/logout.php">로그아웃</a> | <a href="./greet/list.php">정보수정</a>
<?
	}
?>
	 </div>
