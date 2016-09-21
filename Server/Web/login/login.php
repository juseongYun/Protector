<?
           session_start();
		   
   include "../lib/dbconn.php";
?>
<meta charset="utf-8">
<?
  $id = $_POST['id'];  $pass=$_POST['pass']; $isem= $_POST['isem'];
  
   // 이전화면에서 이름이 입력되지 않았으면 "이름을 입력하세요"
   // 메시지 출력
   if($isem!="y")
   {
	   if(!$id) {
		 echo("
			   <script>
				 window.alert('아이디를 입력하세요.')
				 history.go(-2)
			   </script>
			 ");
			 exit;
	   }

	   if(!$pass) {
		 echo("
			   <script>
				 window.alert('비밀번호를 입력하세요.')
				 history.go(-2)
			   </script>
			 ");
			 exit;
	   }
   

	   $sql = "select * from member_pos where id='$id'";
	   $result = mysql_query($sql, $connect);
	   $row = mysql_fetch_array($result);
	   $llevel=$row['level'];
	   if($llevel!=5)
	   {
		   $sql = "select * from member where id='$id'";
		   $result = mysql_query($sql, $connect);
		   $num_match = mysql_num_rows($result);
	   }
	   else
	   {
		   $sql = "select * from member_doctor where id='$id'";
		   $result = mysql_query($sql, $connect);
		   $num_match = mysql_num_rows($result);
	   }
	}
	else
	{
		$product_num=$_POST['product_num'];
		$sql = "select * from member where product_num='$product_num'";
	   $result = mysql_query($sql, $connect);
	   $num_match = mysql_num_rows($result);
	}
   if(!$num_match) 
   {
	   if($isem!="y")
     echo("
           <script>
             window.alert('등록되지 않은 아이디입니다.')
             history.go(-2)
           </script>
         ");
		 else
			 echo("
           <script>
             window.alert('제품번호를 확인해주세요')
             history.go(-2)
           </script>
         ");

    }
    else
    {
        $row = mysql_fetch_array($result);
		if($isem!="y")
		{
			$db_pass = $row[pass];
			$shapass=sha1($pass);
			if($shapass!= $db_pass)
			{
			   echo("
				  <script>
//				  alert('$shapass');
//          alert('$db_pass');
					window.alert('비밀번호가 틀립니다.')
					history.go(-2)
				  </script>
			   ");

			   exit;
			}
			else
			{
				$userid = $row[id];
				$username = $row[name];
				$usernick = $row[nick];
				$userlevel = $row[level];
				$userproduct=$row[product_num];

			   if($userlevel>0)
				{
					/*로그인 방법에 따라 분기
					admin이 아닐경우 POST로 넘어온 level로 재저장.
					*/
				}

			   $_SESSION['userid'] = $userid;
			   $_SESSION['username'] = $username;
			   $_SESSION['userlevel'] = $llevel;
			   $_SESSION['userproduct']=$userproduct;
			   $url=$_POST['url2'];
			   if($url==null)$url="../index.php";
				?>
			   <script>
				  location.href="<?=$url;?>";
				</script>
					<?
			}
		}
		else
		{
			$userid = $row[id];
			$username = $row[name];
			$usernick = $row[nick];
			$userlevel = $row[level];
			$userproduct=$row[product_num];
		   $userlevel=9;
			   $_SESSION['userid'] = $userid;
			   $_SESSION['username'] = $username;
			   $_SESSION['userlevel'] = $userlevel;
			   $_SESSION['userproduct']=$userproduct;
			   echo"<script>
				  location.href='../greet/list.php'
				</script>";
		}
   }          
?>
