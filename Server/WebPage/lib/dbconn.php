<?
    $connect=mysql_connect( "localhost", "kdhong", "1234") or  
        die( "SQL server에 연결할 수 없습니다."); 

    mysql_select_db("kdhong_db",$connect);
	  mysql_query("set session character_set_connection=utf8;");
  mysql_query("set session character_set_results=utf8;");
  mysql_query("set session character_set_client=utf8;");
	$sql = "select * from member where id='".$_SESSION['userid'].'\'';
    $result = mysql_query($sql, $connect);

    $row = mysql_fetch_array($result);
?>
