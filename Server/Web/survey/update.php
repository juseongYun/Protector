<?
   include "../lib/dbconn.php";
   $composer = $_GET['composer'];
   $sql = "update survey set $composer = $composer + 1";
   mysql_query($sql, $connect);

   mysql_close();

   Header("location:result.php");
?>

