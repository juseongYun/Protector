<meta charset="utf-8">
<?
   session_start();

   include "../lib/dbconn.php";

   $sql = "delete from photo where num =".$_GET['num'];
   mysql_query($sql, $connect);
   mysql_close();

   echo "
	   <script>
      opener.location.reload();
	    window.close();
	   </script>
	";
?>

