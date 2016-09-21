<?
      include "../lib/dbconn.php";
	$ripple_num=$_GET['ripple_num'];$table=$_GET['table'];$num=$_GET['num'];
      $sql = "delete from free_ripple where num=$ripple_num";
      mysql_query($sql, $connect);
      mysql_close();

      echo "
	   <script>
	    location.href = 'view.php?table=$table&num=$num';
	   </script>
	  ";
?>
