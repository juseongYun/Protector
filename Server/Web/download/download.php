<?php session_start(); ?>
<?php
function utf2euc($str) { return iconv("UTF-8","cp949//IGNORE", $str); }
function is_ie() {
    if(!isset($_SERVER['HTTP_USER_AGENT']))return false;
      if(strpos($_SERVER['HTTP_USER_AGENT'], 'MSIE') !== false) return true; // IE8
        if(strpos($_SERVER['HTTP_USER_AGENT'], 'Windows NT 6.1') !== false) return true; // IE11
          return false;
}

          $real_name=$_GET['real_name'];
          $show_name=$_GET['show_name'];
          $file_type=$_GET['file_type'];
          if(!$_SESSION['userid']) {
        echo("<script charset = utf-8>
           window.alert('로그인 후 이용해주세요.')
           history.go(-1)
           </script> ");
           exit;
            }
              $file_path = "./data/".$real_name;
              $len = filesize($file_path);
              if( file_exists($file_path) )
              { 
               if( is_ie() ) $filename = utf2euc($filename);
                   header("Pragma: public");
                   header("Expires: 0");
                   header("Content-Type: application/octet-stream");
                   header("Content-Disposition: attachment; filename=\"$show_name\"");
                   header("Content-Transfer-Encoding: binary");
                   header("Content-Length: $len"); 
                   readfile($file_path);
              } 
 ?>
                        
