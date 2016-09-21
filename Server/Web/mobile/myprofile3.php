<!DOCTYPE HTML>
<html>
<head> 
<meta charset="utf-8">
  <?
	$id = $_GET['id'];
    $pass=$_GET['pass'];
	$code=$_GET['code'];
	  include "../lib/dbconn.php";
	  $sql = "select * from member where product_num='$code'";
	  $result = mysql_query($sql, $connect);	  
    $row = mysql_fetch_array($result); 
  ?>
</head>
<body>
  <?
    if($pass!=$row[pass])
    {
      echo"wrong paas";
      exit;
    }
  ?>
 아이디=<?=$row[id]?><br/>
 이름=<?=$row[name]?><br/>
 의료보험번호=<?=$row[medical_num]?><br/>
 주민등록번호=<?=$row[social_num]?><br/>
 제품번호=<?=$row[social_num]?><br/>
 비상연락망1=<?=$row[relative1].'<br/>'.$row[emergency_num1]?><br/>
 비상연락망2=<?=$row[relative2].'<br/>'.$row[emergency_num2]?><br/>
 주소=<?=$row[address]?><br/>
 복용약=<?=$row[medicine]?><br/>
 병력=<?=$row[medical_history]?><br/>
 특이사항=<?=$row[special]?><br/>
</body>
</html>

