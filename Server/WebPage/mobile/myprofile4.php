<!DOCTYPE HTML>
<html>
<head> 
<meta charset="utf-8">
  <?
  if($id == "")$id = $_GET['id'];
  if($pass == "")$pass=$_GET['pass'];
  if($name == "")$name=$_GET['name'];
  if($medical_num == "")$medical_num=$_GET['medical_num'];
  if($social_num == "")$social_num$_GET['social_num'];
  if($product_num == "")$product_num=$_GET['product_num'];
  if($re1 == "")$re1=$_GET['re1'];
  if($re2 == "")$re2=$_GET['re2'];
  if($address == "")$address=$_GET['address'];
  if($medicine == "")$medicine=$_GET['medicine'];
  if($medical_history == "")$medical_history=$_GET['medical_history'];
  if($special == "")$special=$_GET['special'];


	  include "../lib/dbconn.php";
	  $sql = "select * from member where id='$id'";
	  $result = mysql_query($sql, $connect);	  
    $row = mysql_fetch_array($result); 
  ?>
</head>
<body>
  <?
    if($pass!=$row[pass])
    {
      echo"wrong pass";
      exit;
   }
  ?>
 id=<?=$row[id]?>=id<br/>
 name=<?=$row[name]?>=name<br/>
 medical_num=<?=$row[medical_num]?>=medical_num<br/>
 social_num=<?=$row[social_num]?>=social_num<br/>
 product_num=<?=$row[product_num]?>=product_num<br/>
 re1=<?=$row[relative1].'> '.$row[emergency_num1]?>=re1<br/>
 re2=<?=$row[relative2].'> '.$row[emergency_num2]?>=re2<br/>
 address=<?=$row[address]?>=address<br/>
 medicine=<?=$row[medicine]?>=medicine<br/>
 medical_history=<?=$row[medical_history]?>=medical_hostory<br/>
 special=<?=$row[special]?>=special<br/>
</body>
</html>

