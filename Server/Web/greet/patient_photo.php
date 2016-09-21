<!DOCTYPE HTML >
<html>
 <head>
 <meta charset= "utf-8">
 <title>Capable Person's Union</title>
 <link href="../css/common.css" rel="stylesheet" type="text/css" media="all">
 <link href="../css/board3.css" rel="stylesheet" type="text/css" media="all">

<? 
	session_start(); 
	$table = "photo";	
	$mode=$_GET['mode'];
	$page=$_GET['page'];
	if($_SESSION['patient']==""){
	echo"<script>window.alert('잘못된 접근입니다.'); location.replace('../');</script>";}
  include "../lib/dbconn.php";
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
			include "../lib/left_menu.php";
?>
		</div>
	</div>

	<div id="col2">        
		<div id="title">
			<img src="../img/title_photo.png">
		</div>
    <div id= "list_content"><br><br>
<?php
	$patient=$_SESSION['patient'];
	$sql = "select id from member where product_num ='$patient'";
	$result = mysql_query($sql, $connect);
	$row=mysql_fetch_array($result);
	$pid=$row['id'];
	$sql="select * from photo where id ='$pid' order by num desc";
	$result = mysql_query($sql, $connect);
	$total_record = mysql_num_rows($result); // 전체 글 수
    #photo printing

    $a_img = array();
    $col = 3; //no. of columns in a page
    $maxrow = 3; //no. of rows in a page
    $dir="./data"; //directory for this script, no need to change

 $totxpage = $col*$maxrow; // images x page
 $total_page = ($total_record%$totxpage==0)?((int)$total_record/$totxpage):((int)($total_record/$totxpage)+1); // number of total pages
?>
<?
 if($total_record == 0){
   echo "<img align=center src=../img/No_image_available.png / style='display:block;margin-left:auto;margin-right:auto;'>";
 }
 else
{

echo "<center><table width=700  cellpadding=2 cellspacing=3>\n";

  // start page
  if($page=="" || $page==1)
  {
   $x=0;
   $page = 1;
  }
  else
   $x = (($page-1)*($totxpage));
  $r=0;

 // page break
$scale=$col*$maxrow;
$start = ($page - 1) * $scale;

for ($i=$start; $i<$start+$scale && $i < $total_record; $i++)                    
   {
      mysql_data_seek($result, $i);       
      // 가져올 레코드로 위치(포인터) 이동  
      $row = mysql_fetch_array($result);       
      // 하나의 레코드 가져오기
	
	  $item_num     = $row[num];
	  $item_id      = $row[id];
	  $item_file	= $row[file_copied];
      $item_date    = $row[regist_day];
	  $item_date = substr($item_date, 0, 10);  

	  $item_subject = str_replace(" ", "&nbsp;", $row[subject]);
  	   $number--;

   if(($x%$col)==0)
    print "<tr>\n";
    $width = 200;
    $imgnumber = ($x+1);
	$thumbnail =  "./data/$item_file";

	print "<td align=center valign=top>";
	print "<TABLE WIDTH=$width BORDER=0 CELLPADDING=0 CELLSPACING=0 >";
	print"<tr><td>$item_num  $item_subject</td></tr>";
	print "<TR><TD><a href='#' onclick=\"window.open('popup.php?n=$item_num','$x','width=550,height=600,directories=no,location=no,menubar=no,scrollbars=no,status=no,toolbar=no,resizable=no');return false\" target=\"_blank\"> <img src=\"$thumbnail\"  width=$width border=0 alt='$a_img[$x]'></a></TD></TR>";
	print "</TABLE></center>";
	print "</td>\n";

   if(($x%$col) == ($col-1))
   {
    print "</tr>\n";
    $r++;
   }
   if($r==$maxrow)
   {
    break;
   }
   else
   $x++;
  }
  print "</table>\n";
 }
?>

<div id="page_button">
        <div id="page_num">
        <?php
		$prepage=$page-1;
        if($page!=$total_page)$prepage=$page-1; if($page>1)print"<a href='photo.php?table=photo&page=$prepage'>◀ 이전 &nbsp;&nbsp;&nbsp;&nbsp;</a>";
 else print"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; ?> 
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
                                      echo "<a href='photo.php?table=$table&page=$i'> $i </a>";
                                          }      
                           }
?>      
      <?php $nextpage=$page+1;
      if($page < $total_page)echo"<a href='photo.php?table=photo&page=$nextpage'>&nbsp;&nbsp;&nbsp;&nbsp;다음 ▶</a>";?>
              </div>
			  <?if($_SESSION['userlevel']<9)
			  {?>
                      <div id="button">
                                <a href="photo.php?page=<?=$page?>"><img src="../img/list.png"></a>&nbsp;
                                <? 
                                  if($_SESSION['userid'])
                                    {
                                      ?>
                                        <a href='#' onclick="window.open('writephoto.php?mode=<?$mode?>','','directories=no,location=no,menubar=no,scrollbars=no,status=no,toolbar=no,resizable=no,width=550,height=600');return false" target=\"_blank\">    
                                        <img src="../img/write.png"></a>
                                            <?
                                              }
			 }
?>
        </div>
              </div> <!-- end of page_button -->
    </div>

	</div> <!-- end of col2 -->
  </div> <!-- end of content -->
</div> <!-- end of wrap -->

</body>
</html>
