<? 	include "../lib/dbconn.php";
  $table=$_GET['t'];
  $page = $_GET['page'];
  $num=$_GET['num'];
  $sql = "select * from $table where num=$num";
  $result = mysql_query($sql, $connect);

    $row = mysql_fetch_array($result);       
      // 하나의 레코드 가져오기
	
	$item_num     = $row[num];
	$item_id      = $row[id];
	$item_name    = $row[name];
  	$item_nick    = $row[nick];
	$item_hit     = $row[hit];

    $item_date    = $row[regist_day];

	$item_subject = str_replace(" ", "&nbsp;", $row[subject]);

	$item_content = $row[content];
	$is_html      = $row[is_html];

	if ($is_html!="y")
	{
		$item_content = str_replace(" ", "&nbsp;", $item_content);
		$item_content = str_replace("\n", "<br>", $item_content);
	}	

	$new_hit = $item_hit + 1;

	$sql = "update greet set hit=$new_hit where num=$num";   // 글 조회수 증가시킴
	mysql_query($sql, $connect);
?>
<!DOCTYPE HTML>
<html>
<head> 
<meta charset="utf-8">
</head>

<body>
<?if($table==NULL ||$num == NULL){echo "error";exit;}?>
<?= $item_subject ?><?= $item_nick ?><?= $item_hit ?><?= $item_date ?>
<?= $item_content ?>
</body>
</html>
