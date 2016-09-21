<? 
	session_start(); 
	$table = "qna";
	$mode=$_GET['mode'];
	$page=$_GET['page'];
	$userid=$_SESSION['userid'];
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta charset="utf-8">
<link href="../css/common.css" rel="stylesheet" type="text/css" media="all">
<link href="../css/board1.css" rel="stylesheet" type="text/css" media="all">
</head>
<?
	include "../lib/dbconn.php";
	$scale=10;			// 한 화면에 표시되는 글 수

    if ($mode=="search")
	{
		$search=$_POST['search'];
		if(!$search)
		{
			echo("
				<script>
				 window.alert('검색할 단어를 입력해 주세요!');
			     history.go(-1);
				</script>
			");
			exit;
		}

		if($_SESSION['userleve']<=1)
		$sql = "select * from $table where $find like '%$search%' order by num desc";
		else
		$sql = "select * from $table where $find like '%$search%' and id='$userid' order by num desc";
	}
	else
	{
		
		if($_SESSION['userleve']<=1)
		$sql = "select * from $table order by group_num desc, ord asc";

		else
		$sql = "select * from $table where id='$userid' or original_id='$userid' order by group_num desc, ord asc";
	}

	$result = mysql_query($sql, $connect);
	$total_record = mysql_num_rows($result); // 전체 글 수

	// 전체 페이지 수($total_page) 계산 
	if ($total_record % $scale == 0)     
		$total_page = floor($total_record/$scale);      
	else
		$total_page = floor($total_record/$scale) + 1; 
 
	if (!$page)                 // 페이지번호($page)가 0 일 때
		$page = 1;              // 페이지 번호를 1로 초기화
 
	// 표시할 페이지($page)에 따라 $start 계산  
	$start = ($page - 1) * $scale;      
	$number = $total_record - $start;
?>
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
			include "../lib/left_menu_04.php";
?>
		</div>
	</div>

	<div id="col2">        
		<div id="title">
			<img src="../img/title_onetoone.png">
		</div>
		<form  name="board_form" method="post" action="list.php?table=<?=$table?>&mode=search"> 
		<div id="list_search">
			<div id="list_search1">▷ 총 <?= $total_record ?> 개의 게시물이 있습니다.  </div>
			<div id="list_search2"><img src="../img/select_search.gif"></div>
			<div id="list_search3">
				<select name="find">
                    <option value='subject'>제목</option>
                    <option value='content'>내용</option>
                    <option value='nick'>별명</option>
                    <option value='name'>이름</option>
				</select></div>
			<div id="list_search4"><input type="text" name="search"></div>
			<div id="list_search5"><input type="image" src="../img/list_search_button.gif"></div>
		</div>
		</form>
		<div class="clear"></div>

		<div id="list_top_title">
			<ul>
				<li id="list_title1"><img src="../img/list_title1.gif"></li>
				<li id="list_title2"><img src="../img/list_title2.gif"></li>
				<li id="list_title3"><img src="../img/list_title3.gif"></li>
				<li id="list_title4"><img src="../img/list_title4.gif"></li>
				<li id="list_title5"><img src="../img/list_title5.gif"></li>
			</ul>		
		</div>

		<div id="list_content">
<?		
   for ($i=$start; $i<$start+$scale && $i < $total_record; $i++)                    
  {
      mysql_data_seek($result, $i);       
      $row = mysql_fetch_array($result);       
	

  if($_SESSION['userid']== $row[id] || $_SESSION['userlevel']==1 || $_SESSION['userid'] == $row[original_id])
  {
	  $item_num     = $row[num];
	  $item_id      = $row[id];
	  $item_name    = $row[name];
	  $item_hit     = $row[hit];
    $item_date    = $row[regist_day];
	  $item_date = substr($item_date, 0, 10);  
	  $item_subject = str_replace(" ", "&nbsp;", $row[subject]);
	  $item_depth = $row[depth];

      $space = "";
      for ($j=0; $j<$item_depth; $j++)
          $space = "&nbsp;&nbsp;".$space;

?>

			<div id="list_item">
				<div id="list_item1"><?= $number ?></div>
				<div id="list_item2"><?=$space?><a href="view.php?table=<?=$table?>&num=<?=$item_num?>&page=<?=$page?>"><?= $item_subject ?></a></div>
				<div id="list_item3"><?= $item_name ?></div>
				<div id="list_item4"><?= $item_date ?></div>
				<div id="list_item5"><?= $item_hit ?></div>
			</div>
      
<?
   	   $number--;
   }}
?>
			<div id="page_button">
				<div id="page_num"> ◀ 이전 &nbsp;&nbsp;&nbsp;&nbsp; 
<?
   // 게시판 목록 하단에 페이지 링크 번호 출력
   for ($i=1; $i<=$total_page; $i++)
   {
		if ($page == $i)     // 현재 페이지 번호 링크 안함
		{
			echo "<b> $i </b>";
		}
		else
		{ 
			echo "<a href='list.php?table=$table&page=$i'> $i </a>";
		}      
   }
?>			
			&nbsp;&nbsp;&nbsp;&nbsp;다음 ▶
				</div>
				<div id="button">
					<a href="list.php?table=<?=$table?>&page=<?=$page?>"><img src="../img/list.png"></a>&nbsp;
<? 
	if($_SESSION['userid'])
	{
?>
		<a href="write_form.php?table=<?=$table?>"><img src="../img/write.png"></a>
<?
	}
?>
				</div>
			</div> <!-- end of page_button -->		
        </div> <!-- end of list content -->
		<div class="clear"></div>

	</div> <!-- end of col2 -->
  </div> <!-- end of content -->
</div> <!-- end of wrap -->

</body>
</html>
