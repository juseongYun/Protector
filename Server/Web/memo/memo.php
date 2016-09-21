<? 
	session_start(); 
	
	$scale=5;			// 한 화면에 표시되는 글 수
	include "../lib/dbconn.php";

	$sql = "select * from memo order by num desc";
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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta charset="utf-8">
<link href="../css/common.css" rel="stylesheet" type="text/css" media="all">
<link href="../css/memo.css" rel="stylesheet" type="text/css" media="all">
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
			include "../lib/left_menu_03.php";
?>
		</div>
	</div>
	<div id="col2">  
		<div id="title">
			<img src="../img/title_memo.gif">
		</div>

		<div id="memo_row1">
       	<form  name="memo_form" method="post" action="insert.php"> 
			<div id="memo_writer"><span >▷ <?= $_SESSION['usernick'] ?></span></div>
			<div id="memo1"><textarea rows="6" cols="85" name="content"></textarea></div>
			<div id="memo2"><input type="image" src="../img/memo_button.gif"></div>
		</form>	
		</div> <!-- end of memo_row1 -->
<?		
   for ($i=$start; $i<$start+$scale && $i < $total_record; $i++)                    
   {
      mysql_data_seek($result, $i);       
      $row = mysql_fetch_array($result);       
	
	  $memo_id      = $row[id];
	  $memo_num     = $row[num];
      $memo_date    = $row[regist_day];
	  $memo_nick    = $row[nick];

	  $memo_content = str_replace("\n", "<br>", $row[content]);
	  $memo_content = str_replace(" ", "&nbsp;", $memo_content);
?>
		<div id="memo_writer_title">
		<ul>
		<li id="writer_title1"><?= $number ?></li>
		<li id="writer_title2"><?= $memo_nick ?></li>
		<li id="writer_title3"><?= $memo_date ?></li>
		<li id="writer_title4"> 
		      <? 
					if($userid=="admin" || $_SESSION['userid']==$memo_id)
			          echo "<a href='delete.php?num=$memo_num'>[삭제]</a>"; 
			  ?>
		</li>
		</ul>
		</div>
		<div id="memo_content"><?= $memo_content ?>
		</div>
		<div id="ripple"> 
			<div id="ripple1">덧글</div>
			<div id="ripple2">
<?
	    $sql = "select * from memo_ripple where parent='$memo_num'";
	    $ripple_result = mysql_query($sql);

		while ($row_ripple = mysql_fetch_array($ripple_result))
		{
			$ripple_num     = $row_ripple[num];
			$ripple_id      = $row_ripple[id];
			$ripple_nick    = $row_ripple[nick];
			$ripple_content = str_replace("\n", "<br>", $row_ripple[content]);
			$ripple_content = str_replace(" ", "&nbsp;", $ripple_content);
			$ripple_date    = $row_ripple[regist_day];
?>
				<div id="ripple_title">
				<ul>
				<li><?= $ripple_nick ?> &nbsp;&nbsp;&nbsp; <?= $ripple_date ?></li>
				<li id="mdi_del">
					<? 
						if($userid=="admin" || $_SESSION['userid']==$ripple_id)
				            echo "<a href='delete_ripple.php?num=$ripple_num'>삭제</a>";
					?>
				</li>
				</ul>
				</div>
				<div id="ripple_content"> <?= $ripple_content ?></div>
<?
		}
?>
				<form  name="ripple_form" method="post" action="insert_ripple.php"> 
				<input type="hidden" name="num" value="<?= $memo_num ?>"> 
				<div id="ripple_insert">
				    <div id="ripple_textarea">
						<textarea rows="3" cols="80" name="ripple_content"></textarea>
					</div>
					<div id="ripple_button"><input type="image" src="../img/memo_ripple_button.png"></div>
				</div>
				</form>

			</div> <!-- end of ripple2 -->
  		    <div class="clear"></div>
			<div class="linespace_10"></div>
<?
		$number--;
	 }
	 mysql_close();
?>
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
			echo "<a href='memo.php?page=$i'> $i </a>";
		}      
   }
?>			
			&nbsp;&nbsp;&nbsp;&nbsp;다음 ▶</div>
		 </div> <!-- end of ripple -->
	</div> <!-- end of col2 -->
  </div> <!-- end of content -->
</div> <!-- end of wrap -->

</body>
</html>
