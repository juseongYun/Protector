<!DOCTYPE HTML>
<html>
<head> 
<meta charset="utf-8">
  <?
    $id = $_GET['id'];
    $pass=$_GET['pass'];
	  include "../lib/dbconn.php";
	  $sql = "select * from member_doctor where id='$id'";
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
 phone_num=<?=$row[phone_num]?>=phone_num<br/>
 license_num=<?=$row[license_num]?>=license_num<br/>
</body>
</html>

