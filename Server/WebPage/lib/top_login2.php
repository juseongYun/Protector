<script>
function sub(f,url){
  f.action=url;
  f.submit();
}
</script>
<div id="logo"><a href="../index.php"><img src="../img/logo.png" border="0"></a></div>
	<div id="moto" style="margin-left:40px";><img src="../img/moto.png"></div>
	<div id="top_login">
<form method = "post" name="hide">
<input type="hidden" name="url" value="<?="http://".$_SERVER["HTTP_HOST"].$_SERVER["REQUEST_URI"]?>"/>
  <?
    if(!$_SESSION['userid'])
	{
?>
          <a href="#" onclick ="sub(document.hide,'../login/login_form.php')">로그인</a> | 
          <a href="#" onclick ="sub(document.hide,'../member/member_join.php')">회원가입</a> 
<?
	}
	else
	{
?>
		<?=$_SESSION['username']?> (level:<?=$_SESSION['userlevel']?>) | 
    <a href="#" onclick ="sub(document.hide,'../login/logout.php')">로그아웃</a> | 
    <a href="../greet/list.php">정보수정</a>
<?
	}
?>
   </form>
	 </div>
