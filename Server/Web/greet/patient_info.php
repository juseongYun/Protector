<? 
	session_start(); 	
	$patient=$_SESSION['patient'];
	include "../lib/dbconn.php";       // dconn.php 파일을 불러옴
	$userid=$_SESSION['pid'];
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
  <input type="hidden" name="password" value=<?=$row[pass]?>>
  <input type="hidden" class="name" name="name" value=<?=$row[name]?>>
  <li><?=$row[name]?></li>
  <li><input type="hidden" class="medical_num" name="medical_num" value=<?=$row[medical_num]?>>
		<?=$row[medical_num]?></li>
  <li><input type="hidden" class="social_num" name="social_num" value=<?=$row[social_num]?>>		
		<?=$row[social_num]?></li>
  <li><input type="hidden" class="product_num" name="product_num" value=<?=$row[product_num]?>>
		<?=$row[product_num]?></li>
  <li>  <input type="hidden" class="relative1" name="relative1" value=<?=$row[relative1]?>>
  <input type="hidden" class="emergency_num1" name="emergency_num1" value=<?=$row[emergency_num1]?>>
		<?=$row[relative1]?>&nbsp;<?=$row[emergency_num1]?></li>
   <li>  <input type="hidden" class="relative2" name="relative2" value=<?=$row[relative2]?>>
   <input type="hidden" class="emergency_num2" name="emergency_num2" value=<?=$row[emergency_num2]?>>		
		<?=$row[relative2]?>&nbsp;<?=$row[emergency_num2]?> </li>
 
  <li><input type="hidden" class="address" name="address" value=<?=$row[address]?> >
		<?=$row[address]?></li>
  <li style="height:77px;"><textarea rows="4" cols="73" class="medicine" name="medicine"  ><?=$row[medicine]?></textarea></li>
  <li style="height:121px;"><textarea rows="7" cols="73" class="medical_history" name="medical_history" ><?=$row[medical_history]?></textarea></li>
  <li style="height:100px;"><textarea rows="6" cols="73" class="special" name="special" ><?=$row[special]?></textarea></li>
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

