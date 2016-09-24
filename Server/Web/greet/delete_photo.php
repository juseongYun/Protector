<meta charset="utf-8">
<?
   session_start();

   include "../lib/dbconn.php";

   
   $sql = "select file_copied from photo where num =".$_GET['num'];
   $result = mysql_query($sql, $connect);
   $row = mysql_fetch_array($result);
   			
   $upload_dir = './data/';
   $file = $upload_dir."/".$row['file_copied'];
   if($row['file_copied'] &&is_file($file)) unlink($file);
   
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

