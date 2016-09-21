<?
   session_start();

   include "../lib/dbconn.php";

   $table = $_GET['table'];$num=$_GET['num'];
   $sql = "delete from $table where num = $num";
   mysql_query($sql, $connect);

   mysql_close();

   echo "
	   <script>
	    location.href = 'list.php?table=$table';
	   </script>
	";
?>

