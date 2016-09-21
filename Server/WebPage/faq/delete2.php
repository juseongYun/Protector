<?
   include "../lib/dbconn.php";

   $sql = "delete from memo where num = ".$_GET['num'];
   mysql_query($sql, $connect);
echo $sql;
   mysql_close();

   echo "
	   <script>
	    location.href = 'memo.php';
	   </script>
	";
?>

