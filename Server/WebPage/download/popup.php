<?php 
session_start();
$img = $_GET['img'];
$num=$_GET['n'];
$table ="photo" ;
  include "../lib/dbconn.php";

	$sql = "select * from $table where num=$num";
	$result = mysql_query($sql, $connect);	
    $row = mysql_fetch_array($result);  

	 $item_id      = $row[id];
	  $item_file	= $row[file_copied];
      $item_date    = $row[regist_day];
	  $item_date = substr($item_date, 0, 10);  
	  $item_content = $row[content];
	  $item_subject = str_replace(" ", "&nbsp;", $row[subject]);
	$sql = "select * from member where id='$item_id'";
	$result = mysql_query($sql, $connect);	
    $row = mysql_fetch_array($result);  
	  $item_name=$row[name];
$size = getimagesize ($itemf_file);
?>
<html>
<head>
<link href="../css/common.css" rel="stylesheet" type="text/css" media="all">
<link href="../css/board3.css" rel="stylesheet" type="text/css" media="all">
<meta charset='utf-8'>
<title><?=$item_subject?></title>
</head>
<body>

  <div id="content" style="width:500px;min-height:500px; margin-top:10px;margin-left:10px;">

		<div id="write_form_title">
			<img src="../img/write_form_title.gif">
		</div>

			<div id="write_form">
			<div class="write_line"></div>
			<div id="write_row1"><div class="col1"> 환자명 </div>
      <div class="col2"><?=$item_id."(".$item_name.")"?></div></div>
			
			<div class="write_line"></div>
			<div id="write_row2"><div class="col1"> 제목   </div>
			                     <div class="col2"><?=$item_subject?></div>
			</div>
			<div class="write_line"></div>
<div id="preview"><div class="col1"> 이미지   </div>
 <div class="col2" style="line-height:300px; align = "center""><img id="blah" src=./data/<?=$item_file?> onerror="this.src='../img/select.jpg'" <?if($size[0]<=$size[1])echo"height=300px";
 else echo"width=350px";?>align = "middle""/ ></div>
</div>

<div class="write_line"></div>
			<div id="write_row3"><div class="col1"> 설명   </div>
			                     <div class="col2"><?=$item_content?></div>
			</div>
			<div class="write_line"></div>
  
    </div> <!-- end of content -->

</body>
