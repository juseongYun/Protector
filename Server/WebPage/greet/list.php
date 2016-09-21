
<meta http-equiv="Content-Type" content="text/html; charset=utf-8>">
<?
session_start();
	if($_SESSION['userid']==""){
	echo"<script>window.alert('잘못된 접근입니다.'); location.replace('../');</script>";}
?>
<?
if($_SESSION['userlevel']==5)
{
	   echo"<script>
	    location.replace('listdoctor.php');
	   </script>";
}	
if($_SESSION['userlevel']<9)
{
	   echo"<script>
	    location.replace('list3.php');
	   </script>";
}
else
{

	   echo"<script>
	    location.replace('list2.php');
	   </script>";
}?>