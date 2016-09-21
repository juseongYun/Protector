<? 
	session_start();
	include "../lib/dbconn.php";       // dconn.php 파일을 불러옴
	$userid=$_SESSION['userid'];
	if(!$userid)
	{	
		echo"<script>window.alert('잘못된 접근입니다.');
		location.replace('../');</script>";
	}
	$sql = "select * from member_doctor where id='$userid'";
	$result = mysql_query($sql, $connect);
    $row = mysql_fetch_array($result);
	$userpass=$row[pass];
	$job=$row[job];
	$name=$row[name];
	$phone1= strtok($row[phone_num],'-');
	$phone2 = strtok('-');
	$phone3 = strtok('-');
	$license=$row[license_num];
?>

<!DOCTYPE HTML>
<html>
<head> 
<meta charset="utf-8">
<link href="../css/common.css" rel="stylesheet" type="text/css" media="all">
<link href="../css/list_doctor.css" rel="stylesheet" type="text/css" media="all">
<script>
function check_id()
{
  window.open("check_id.php?id=" + document.list.id.value,
  "IDcheck","left=200,top=200,width=250,height=100,scrollbars=no,resizable=yes");
}
function check_phone(event)
{
	if ((event.keyCode<48)||(event.keyCode>57))
	{
		alert("올바른 전화번호를 입력해 주세요.");
		if (!event)
       event = window.event;

    //IE9 & Other Browsers
    if (event.preventDefault()) {
      event.preventDefault();
    }
    //IE8 and Lower
    else {
      event.cancelBubble = true;
    }
	}
}
function check_input()
{
  if(!document.list.id.value)
  {
    alert("아이디를 입력하세요");
    document.list.id.focus();
    document.list.id.select();
    return;
  }

  if(!document.list.password.value)
  {
    alert("비밀번호를 입력하세요");
    document.list.password.focus();
    document.list.password.select();
    return;
  }
  
  if(!document.list.job.selectedIndex)
  {
    alert("직업군을 선택하세요");
    document.list.job.focus();
    return;
  }

  if(!document.list.license_num.value)
  {
    alert("면허번호를 입력하세요");
    document.list.license_num.focus();
    document.list.license_num.select();
    return;
  }
  
  if(!document.list.phone_num_1.value && !document.list.phone_num_2.value && !document.list.phone_num_3.value)
  {
    alert('연락처를 입력하세요.')
    document.list.phone_num.focus();
    document.list.phone_num.select();
    return;
  }

  document.list.submit();
}

function reset_form()
{
  document.list.id.value="";
  document.list.password.value="";
  document.list.job.value="";
  document.list.license_num.value="";
  document.list.phone_num_1.value="010";
  document.list.phone_num_2.value="";
  document.list.phone_num_3.value="";

  document.list.id.select();

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
<form name="list" method="post" action="../member/insert_doctor.php">
<div id="title">
 <img src="../img/title_userinfo.png">
</div> <!-- end of title-->

<div id="list">
<div id="list_title">
 <ul>
 <li> 아이디</li>
 <li> 비밀번호</li>
 <li> 이름 </li>
 <li> 직업군</li>
 <li> 면허번호</li>
 <li> 연락처</li>
 </ul>
 </div>
 <div id="list_content">
  <input type="hidden" name ="mode" value = "modify">
 <ul>
  <li><?=$userid?><input type="hidden" name ="id" value =<?=$userid?>>
  <li><input type="password" class="password" name="password" value=<?=$userpass?></li>
  <li><input type="text" class="name" name="name" value=<?=$name?>></li>
  <li><select class="job" name="job">
   <option value='선택하세요'>선택하세요</option>
   <option value='의사'<?if($job=='의사')echo"selected";?>>의사</option>
   <option value='사회복지사'<?if($job=='사회복지사')echo"selected";?>>사회복지사</option></select></li>
  <li><input type="text" class="license_num" name="license_num" value=<?=$license?>></li>
  <li><select class="phone_num" name="phone_num_1">
   <option value='010' <?if($phone1=='010')echo"selected";?> >010</option>
   <option value='011'<?if($phone1=='011')echo"selected";?>>011</option>
   <option value='016'<?if($phone1=='016')echo"selected";?>>016</option>
   <option value='017'<?if($phone1=='017')echo"selected";?>>017</option>
   <option value='018'<?if($phone1=='018')echo"selected";?>>018</option>
   <option value='019'<?if($phone1=='019')echo"selected";?>>019</option>
   </select> - <input type="text" class="phone_num" name="phone_num_2" maxlength=4 onKeyPress=check_phone(event) value=<?=$phone2?>> - 
   <input type="text" class="phone_num" name="phone_num_3" maxlength=4 onKeyPress=check_phone(event)  value=<?=$phone3?>></li>
</ul>
</div>
<div class="clear"</div>

<div id="button"><a href="#"><img src="../img/button_save.png" onclick="check_input()"></a>&nbsp;&nbsp;
<a href="#"><img src="../img/button_cancel.png" onclick="reset_form()"></a>
</div>
</form>
</div> <!-- end of col2 -->
</div> <!-- end of content -->
</div> <!-- end of wrap-->

</body>
</html>

