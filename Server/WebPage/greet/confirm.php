<? 
	session_start(); 
?>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
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

function check_nick()
{
  window.open("check_nick.php?nick=" + document.list.nick.value,
  "NICKcheck","left=200,top=200,width=250,height=100,scrollbars=no,resizable=yes");
}


function set()
{
  document.list.name.value=$name;
  document.list.medical_num.value="";
  document.list.social_num1.value="";
  document.list.social_num2.value="";
  document.list.product_num.value="";
  document.list.relative1.value="선택하세요";
  document.list.emergency_num1_1.value="010";
  document.list.emergency_num1_2.value="";
  document.list.emergency_num1_3.value="";
  document.list.relative2.value="선택하세요";ㅑ
  document.list.emergency_num2_1.value="010";
  document.list.emergency_num2_2.value="";
  document.list.emergency_num2_3.value="";
  document.list.address.value="";
  document.list.medicine.value="";
  document.list.medical_history.value="";
  document.list.special.value="";

  document.list.name.focus();
  document.list.name.select();

  return;
}

function edit()
{
  document.location.href='list.php';

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
<form name="list" method="get" action="list.sql">
<div id="title">
 <img src="../img/mp_title_data.png">
</div> <!-- end of title-->

<div id="list">
<div id="list_title">
 <ul>
 <li> 이름</li>
 <li> 의료보험번호</li>
 <li> 주민등록번호</li>
 <li> 제품번호</li>
 <li> 비상연락망1</li>
 <li> 비상연락망2</li>
 <li> 주소</li>
 <li> 복용약</li><li></li><li></li>
 <li> 병력</li><li></li><li></li><li></li>
 <li> 특이사항</li><li></li>
 </ul>
 </div>
 <div id="list_content">
 <ul>
  <li><input type="text" class="name" name="name">
  <li><input type="text" class="medical_num" name="medical_num"></li>
  <li><input type="text" class="social_num1" name="social_num1"> - <input type="text" class="social_num2"  name="social_num2"></li>
  <li><input type="text" class="product_num" name="product_num"></li>
  <li><select class="relative" name="relative1">
   <option value='선택하세요'>선택하세요</option>
   <option value='할아버지'>할아버지</option>
   <option value='할머니'>할머니</option>
   <option value='아버지'>아버지</option>
   <option value='어머니'>어머니</option>
   <option value='형'>형</option>
   <option value='누나'>누나</option>
   <option value='남동생'>남동생</option>
   <option value='여동생'>여동생</option>
   <option value='친구'>친구</option></select>
   <select class="emergency_num" name="emergency_num1_1">
   <option value='010'>010</option>
   <option value='011'>011</option>
   <option value='016'>016</option>
   <option value='017'>017</option>
   <option value='018'>018</option>
   <option value='019'>019</option>
   </select> - <input type="text" class="emergency_num" name="emergency_num1_2"> - 
   <input type="text" class="emergency_num" name="emergency_num1_3"></li>
  <li><select class="relative" name="relative2">
   <option value='선택하세요'>선택하세요</option>
   <option value='할아버지'>할아버지</option>
   <option value='할머니'>할머니</option>
   <option value='아버지'>아버지</option>
   <option value='어머니'>어머니</option>
   <option value='형'>형</option>
   <option value='누나'>누나</option>
   <option value='남동생'>남동생</option>
   <option value='여동생'>여동생</option>
   <option value='친구'>친구</option></select>
   <select class="emergency_num" name="emergency_num2_1">
   <option value='010'>010</option>
   <option value='011'>011</option>
   <option value='016'>016</option>
   <option value='017'>017</option>
   <option value='018'>018</option>
   <option value='019'>019</option>
   </select> - <input type="text" class="emergency_num" name="emergency_num2_2"> - 
   <input type="text" class="emergency_num" name="emergency_num2_3"></li>
  <li><input type="text" class="address" name="address"></li>
  <li style="height:62px;"><input type="text" class="medicine" name="medicine"></li>
  <li style="height:112px;"><input type="text" class="medical_history" name="medical_history"></li>
  <li style="height:112px;"><input type="text" class="special" name="speical"></li>
</ul>
</div>
<div class="clear"</div>
<div id="must"> 응급상황에 필요한 항목들이니 정확히 입력해주시기 바랍니다^^</div>
</div>

<div id="button"><a href="#"><img src="../img/button_edit.png" onclick="edit()"></a>&nbsp;&nbsp;
</div>
</form>
</div> <!-- end of col2 -->
</div> <!-- end of content -->
</div> <!-- end of wrap-->

</body>
</html>

