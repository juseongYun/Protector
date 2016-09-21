<?
  session_start();
  unset($_SESSION['userid']);
  unset($_SESSION['username']);
  unset($_SESSION['userlevel']);

  unset($_SESSION['userproduct']);
 // $url=$_POST['url'];
  if($url==null)$url="../index.php";
 ?>
  <script>
     location.href = "<?=$url?>"; 
  </script>
