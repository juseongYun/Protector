<!DOCTYPE HTML>
<html>
<head> 
<meta charset="utf-8">
  <?
		$code = $_GET['code'];
	  include "../lib/dbconn.php";
	  $sql = "select * from member where product_num='$code'";
	  $result = mysql_query($sql, $connect);	  
    $row = mysql_fetch_array($result); 
  ?>
</head>
<body>
  <?
    if($row==FALSE)
    {
      echo"wrong Access";
      exit;
    }
  ?>
 id=<?=$row[id]?>=id<br/>
 name=<?=$row[name]?>=name<br/>
 medical_num=<?=$row[medical_num]?>=medical_num<br/>
 social_num=<?=$row[social_num]?>=social_num<br/>
 product_num=<?=$row[product_num]?>=product_num<br/>
 relative1=<?=$row[relative1]?>=relative1<br/>
 emergency_num1=<?=$row[emergency_num1]?>=emergency_num1<br/>
 relative2=<?=$row[relative2]?>=relative2<br/>
 emergency_num2=<?=$row[emergency_num2]?>=emergency_num2<br/>
 address=<?=$row[address]?>=address<br/>
 medicine=<?=$row[medicine]?>=medicine<br/>
 medical_history=<?=$row[medical_history]?>=medical_history<br/>
 special=<?=$row[special]?>=special<br/>
</body>
</html>

