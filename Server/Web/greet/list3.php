<? 
	session_start(); 	
	include "../lib/dbconn.php";       // dconn.php 파일을 불러옴
	$userid=$_SESSION['userid'];
	$sql = "select * from member where id='$userid'";
	$result = mysql_query($sql, $connect);
    $row = mysql_fetch_array($result);
	$social1 = strtok($row[social_num],'-');
	$social2 = strtok('-');
	$emergency_num1_1 = strtok($row[emergency_num1],'-');
	$emergency_num1_2 = strtok('-');
	$emergency_num1_3 = strtok('-');
	$emergency_num2_1 = strtok($row[emergency_num2],'-');
	$emergency_num2_2 = strtok('-');
	$emergency_num2_3 = strtok('-');
?>

<!DOCTYPE HTML>
<html>
<head> 
<meta charset="utf-8">
<link href="../css/common.css" rel="stylesheet" type="text/css" media="all">
<link href="../css/list.css" rel="stylesheet" type="text/css" media="all">
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

  if(!document.list.name.value)
  {
    alert("이름을 입력하세요");
    document.list.name.focus();
    document.list.name.select();
    return;
  }

  if(!document.list.product_num.value)
  {
    alert("제품번호를 입력하세요");
    document.list.product_num.focus();
    document.list.product_num.select();
    return;
  }

  if(!document.list.medical_num.value)
  {
    alert("의료보험번호를 입력하세요");
    document.list.medical_num.focus();
    document.list.medical_num.select();
    return;
  }

  if(!document.list.social_num1.value||!document.list.social_num2.value)
  {
    alert("주민등록번호를 입력하세요");
    document.list.social_num1.focus();
    document.list.social_num1.select();
    return;
  }

  if(!document.list.emergency_num1_2.value||!document.list.emergency_num1_3.value)
  {
	window.alert('비상연락망을 적어도 1개 \n입력하세요.')
    document.list.emergency_num1_2.focus();
    document.list.emergency_num1_2.select();
    return;
  }

  if(!document.list.address.value)
  {
    alert("주소를 입력하세요");
    document.list.address.focus();
    document.list.address.select();
    return;
  }

  if(!document.list.medicine.value)
  {
    alert("복용중인 약을 입력하세요");
    document.list.medicine.focus();
    document.list.medicine.select();
    return;
  }

  if(!document.list.medical_history.value)
  {
    alert("병력을 입력하세요.\n수술경험, 알레르기 등 알았던 병을 모두 적어주세요.");
    document.list.medical_history.focus();
    document.list.medical_history.select();
    return;
  }

  document.list.submit();
}

function reset_form()
{
  document.list.id.value="";
  document.list.password.value="";
  document.list.name.value="";
  document.list.medical_num.value="";
  document.list.social_num1.value="";
  document.list.social_num2.value="";
  document.list.product_num.value="";
  document.list.relative1.value="선택하세요";
  document.list.emergency_num1_1.value="010";
  document.list.emergency_num1_2.value="";
  document.list.emergency_num1_3.value="";
  document.list.relative2.value="선택하세요";
  document.list.emergency_num2_1.value="010";
  document.list.emergency_num2_2.value="";
  document.list.emergency_num2_3.value="";
  document.list.address.value="";
  document.list.medicine.value="";
  document.list.medical_history.value="";
  document.list.special.value="";

  document.list.id.focus();
  document.list.id.select();

  return;
}
</script>
</head>
<body>
<? if(!$_SESSION['userid'])
{
	?><script> alert("Log in Please");
	location.href="../login/login_form.php";
	</script>
<?}
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
 <li> 아이디</li>
 <li> 비밀번호</li>
 <li> 이름</li>
 <li> 의료보험번호</li>
 <li> 주민등록번호</li>
 <li> 제품번호</li>
 <li> 비상연락망1</li>
 <li> 비상연락망2</li>
 <li> 주소</li>
 <li> 복용약</li><li></li><li></li>
 <li> 병력</li><li></li><li></li><li></li><li></li>
 <li> 특이사항</li><li></li>
 </ul>
 </div>
 <div id="list_content">
 <ul>
  <input type="hidden" name ="mode" value = "modify">
  <input type="hidden" class="id" name="id" value=<?=$userid?>>
  <li><?=$userid?></li>
  <li><input type="password" class="password" name="password" value=<?=$row[pass]?>>
  <li><input type="text" class="name" name="name" value=<?=$row[name]?>>
  <li><input type="text" class="medical_num" name="medical_num" value=<?=$row[medical_num]?>></li>
  <li><input type="text" class="social_num1" name="social_num1" value=<?=$social1?>>
  &nbsp- &nbsp<input type="password" class="social_num2"  name="social_num2" value=<?=$social2?>></li>
  <input type="hidden" class="product_num" name="product_num" value=<?=$row[product_num]?>>
  <li><?=$row[product_num]?></li>
  <li><select class="relative" name="relative1">
   <option value='선택하세요'<?if($row[relative1]=='선택하세요')echo"selected";?>>선택하세요</option>
   <option value='할아버지' <?if($row[relative1]=='할아버지')echo"selected";?>>할아버지</option>
   <option value='할머니'<?if($row[relative1]=='할머니')echo"selected";?>>할머니</option>
   <option value='아버지'<?if($row[relative1]=='아버지')echo"selected";?>>아버지</option>
   <option value='어머니'<?if($row[relative1]=='어머니')echo"selected";?>>어머니</option>
   <option value='형'<?if($row[relative1]=='형')echo"selected";?>>형</option>
   <option value='누나'<?if($row[relative1]=='누나')echo"selected";?>>누나</option>
   <option value='남동생'<?if($row[relative1]=='남동생')echo"selected";?>>남동생</option>
   <option value='여동생'<?if($row[relative1]=='여동생')echo"selected";?>>여동생</option>
   <option value='친구'<?if($row[relative1]=='친구')echo"selected";?>>친구</option></select>
   <select class="emergency_num" name="emergency_num1_1" value=<?=$emergency_num1_1?>>
   <option value='010'>010</option>
   <option value='011'>011</option>
   <option value='016'>016</option>
   <option value='017'>017</option>
   <option value='018'>018</option>
   <option value='019'>019</option>
   </select> - <input type="text" class="emergency_num" name="emergency_num1_2" value=<?=$emergency_num1_2?> maxlength=4 onKeyPress=check_phone(event) > - 
   <input type="text" class="emergency_num" name="emergency_num1_3" value =<?=$emergency_num1_3?> maxlength=4 onKeyPress=check_phone(event) > </li>
  <li><select class="relative" name="relative2">
   <option value='선택하세요' <?if($row[relative2]=='선택하세요')echo"selected";?>>선택하세요</option>
   <option value='할아버지'  <?if($row[relative2]=='할아버지')echo"selected";?>>할아버지</option>
   <option value='할머니' <?if($row[relative2]=='할머니')echo"selected";?>>할머니</option>
   <option value='아버지' <?if($row[relative2]=='아버지')echo"selected";?>>아버지</option>
   <option value='어머니' <?if($row[relative2]=='어머니')echo"selected";?>>어머니</option>
   <option value='형' <?if($row[relative2]=='형')echo"selected";?>>형</option>
   <option value='누나' <?if($row[relative2]=='누나')echo"selected";?>>누나</option>
   <option value='남동생' <?if($row[relative2]=='남동생')echo"selected";?>>남동생</option>
   <option value='여동생' <?if($row[relative2]=='여동생')echo"selected";?>>여동생</option>
   <option value='친구' <?if($row[relative2]=='친구')echo"selected";?>>친구</option></select>
   <select class="emergency_num" name="emergency_num2_1" value =<?=$emergency_num2_1?>>
   <option value='010'>010</option>
   <option value='011'>011</option>
   <option value='016'>016</option>
   <option value='017'>017</option>
   <option value='018'>018</option>
   <option value='019'>019</option></select>
   - <input type="text" class="emergency_num" name="emergency_num2_2"  value =<?=$emergency_num2_2?> maxlength=4 onKeyPress=check_phone(event) >
   &nbsp-&nbsp
   <input type="text" class="emergency_num" name="emergency_num2_3" value =<?=$emergency_num2_3?> maxlength=4 onKeyPress=check_phone(event) ></li>
  <li><input type="text" class="address" name="address" value=<?=$row[address]?> ></li>
  <li style="height:77px;"><textarea rows="4" cols="73" class="medicine" name="medicine"  ><?=$row[medicine]?></textarea></li>
  <li style="height:121px;"><textarea rows="7" cols="73" class="medical_history" name="medical_history" ><?=$row[medical_history]?></textarea></li>
  <li style="height:80px;"><textarea rows="6" cols="73" class="special" name="special" ><?=$row[special]?></textarea></li>
</ul>
</div>
<div class="clear"</div>
<div id="must"> 응급상황에 필요한 항목들이니 정확히 입력해주시기 바랍니다^^</div>
</div>

<div id="button"><a href="#"><img src="../img/button_save.png" onclick="check_input()"></a>&nbsp;&nbsp;
<a href="#"><img src="../img/button_cancel.png" onclick="reset_form()"></a>
</div>
</form>
</div> <!-- end of col2 -->
</div> <!-- end of content -->
</div> <!-- end of wrap-->

</body>
</html>

