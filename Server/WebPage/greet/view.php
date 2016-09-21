<? 
	session_start(); 
	include "../lib/dbconn.php";
        $page = $_GET['page'];
        $num=$_GET['num'];
	$sql = "select * from greet where num=$num";
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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta charset="utf-8">
<link href="../css/common.css" rel="stylesheet" type="text/css" media="all">
<link href="../css/greet.css" rel="stylesheet" type="text/css" media="all">
<script>
    function del(href) 
    {
        if(confirm("한번 삭제한 자료는 복구할 방법이 없습니다.\n\n정말 삭제하시겠습니까?")) {
                document.location.href = href;
        }
    }
</script>
</head>

<body>
<div id="wrap">
  <div id="header">
    <? include "../lib/top_login2.php"; ?>
  </div>  <!-- end of header -->

  <div id="menu">
	<? include "../lib/top_menu2.php"; ?>
  </div>  <!-- end of menu --> 
  
  <div id="content">
	<div id="col1">
		<div id="left_menu">
<?
			include "../lib/left_menu.php";
?>
		</div>
	</div>

	<div id="col2">
        
		<div id="title">
			<img src="../img/title_greet.gif">
		</div>

		<div id="view_comment"> &nbsp;</div>

		<div id="view_title">
			<div id="view_title1"><?= $item_subject ?></div><div id="view_title2"><?= $item_nick ?> | 조회 : <?= $item_hit ?>  
			                      | <?= $item_date ?> </div>	
		</div>

		<div id="view_content">
			<?= $item_content ?>
		</div>

		<div id="view_button">
				<a href="list.php?page=<?=$page?>"><img src="../img/list.png"></a>&nbsp;
<? 
	if($_SESSION['userid']==$item_id || $userlevel==1 || $userid=="admin")
	{
?>
				<a href="modify_form.php?num=<?=$num?>&page=<?=$page?>"><img src="../img/modify.png"></a>&nbsp;
				<a href="javascript:del('delete.php?num=<?=$num?>')"><img src="../img/delete.png"></a>&nbsp;
<?
	}
?>
<? 
	if($_SESSION['userid'] )
	{
?>
				<a href="write_form.php"><img src="../img/write.png"></a>
<?
	}
?>
		</div>

		<div class="clear"></div>

	</div> <!-- end of col2 -->
  </div> <!-- end of content -->
</div> <!-- end of wrap -->

</body>
</html>
