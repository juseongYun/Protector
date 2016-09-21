<? session_start(); ?>

<meta charset="utf-8">
<?
$table=$_GET['table'];
	$userid=$_SESSION['userid'];$username=$_SESSION['username'];
	$usernick=$_SESSION['usernick'];$subject=$_POST['subject'];$content=$_POST['content'];
	$mode=$_GET['mode'];$num=$_GET['num'];
	if(!$_SESSION['userid']) {
		echo("
		<script>
	     window.alert('로그인 후 이용해 주세요.')
	     history.go(-1)
	   </script>
		");
		exit;
	}

	if(!$subject) {
		echo("
	   <script>
	     window.alert('제목을 입력하세요.')
	     history.go(-1)
	   </script>
		");
	   exit;
	}

	if(!$content) {
		echo("
	   <script>
	     window.alert('내용을 입력하세요.')
	     history.go(-1)
	   </script>
		");
	   exit;
	}
	$regist_day = date("Y-m-d (H:i)");  // 현재의 '년-월-일-시-분'을 저장
	include "../lib/dbconn.php";       // dconn.php 파일을 불러옴

	if ($mode=="modify")
	{
		$sql = "update $table set subject='$subject', content='$content' where num=$num";
		mysql_query($sql, $connect);  // $sql 에 저장된 명령 실행
	}
	else
	{
		if ($html_ok=="y")
		{
			$is_html = "y";
		}
		else
		{
			$is_html = "";
			$content = htmlspecialchars($content);
		}

		if ($mode=="response")
		{
			// 부모 글 가져오기
			$sql = "select * from $table where num = $num";
			$result = mysql_query($sql, $connect);
			$row = mysql_fetch_array($result);

			// 부모 글로 부터 group_num, depth, ord 값 설정
			$group_num = $row[group_num];
			$depth = $row[depth] + 1;
			$ord = $row[ord] + 1; 
      $original_id = $row[original_id];

			// 해당 그룹에서 ord 가 부모글의 ord($row[ord]) 보다 큰 경우엔
			// ord 값 1 증가 시킴
			$sql = "update $table set ord = ord + 1 where group_num = $row[group_num] and ord > $row[ord]";
			mysql_query($sql, $connect);  

			// 레코드 삽입
			$sql = "insert into $table (group_num, depth, ord, id, name, subject,";
			$sql .= "content, original_id, regist_day, hit, is_html) ";
			$sql .= "values($group_num, $depth, $ord, '$userid', '$username', '$subject',";
			$sql .= "'$content', '$original_id', '$regist_day', 0, '$is_html')";    

			mysql_query($sql, $connect);  // $sql 에 저장된 명령 실행
		}
		else
		{
			$depth = 0;   // depth, ord 를 0으로 초기화
			$ord = 0;

			// 레코드 삽입(group_num 제외)
			$sql = "insert into $table (depth, ord, id, name, subject,";
			$sql .= "content, original_id, regist_day, hit, is_html) ";
			$sql .= "values($depth, $ord, '$userid', '$username', '$subject',";
			$sql .= "'$content', '$userid', '$regist_day', 0, '$is_html')";    
			mysql_query($sql, $connect);  // $sql 에 저장된 명령 실행

			// 최근 auto_increment 필드(num) 값 가져오기
			$sql = "select last_insert_id()"; 
			$result = mysql_query($sql, $connect);
			$row = mysql_fetch_array($result);
			$auto_num = $row[0]; 

			// group_num 값 업데이트 
			$sql = "update $table set group_num = $auto_num where num=$auto_num";
			mysql_query($sql, $connect);
		}
	}

	mysql_close();                // DB 연결 끊기

	echo "
	   <script>
	    location.href = 'list.php?table=$table&page=$page';
	   </script>
	";
?>
