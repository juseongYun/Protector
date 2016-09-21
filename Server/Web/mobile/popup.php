<?php 
$img = $_GET['img'];
$num=$_GET['n'];
$table ="photo" ;
    include "../lib/dbconn.php";

	$sql = "select * from photo where num=$num";
	$result = mysql_query($sql, $connect);	
    $row = mysql_fetch_array($result);  

	$item_id      = $row[id];
	$item_file	= $row[file_copied];
	$item_content = $row[content];
	$item_subject = str_replace(" ", "&nbsp;", $row[subject]);
?>
<html>
<head>
<meta charset='utf-8'>
</head>
<body>
<?
	echo $item_file;
	echo $item_content;
?>
</body>
</html>