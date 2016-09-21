<? 
	session_start(); 
	$table ="photo" ;
	$mode=$_GET['mode'];
	$num=$_GET['num'];
	include "../lib/dbconn.php";

	if ($mode=="modify")
	{
		$sql = "select * from $table where num=$num";
		$result = mysql_query($sql, $connect);

		$row = mysql_fetch_array($result);       
	
		$item_subject     = $row[subject];
		$item_content     = $row[content];

		$item_file_0 = $row[file_name_0];

		$copied_file_0 = $row[file_copied_0];
	}
//get unique id
$up_id = uniqid(); 
?>
<!DOCTYPE HTML >
<html>
<head>
<meta charset="utf-8">
<title>사진입력</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" media="all">
<link href="../css/board3.css" rel="stylesheet" type="text/css" media="all">

<!--Progress Bar and iframe Styling-->
<link href="style_progress.css" rel="stylesheet" type="text/css" />

<!--Get jQuery-->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.0/jquery.js" type="text/javascript"></script>

<!--display bar only if file is chosen-->
<script>

$(document).ready(function() { 
//show the progress bar only if a file field was clicked
    show_bar = 0;
    $('input[type="file"]').click(function(){
		show_bar = 1;
});
 function readURL(input) {
                       if (input.files && input.files[0]) {
                            var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
                                reader.onload = function (e) {
                                  //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
                                  $("#blah").attr('src', e.target.result);
                                  //이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
                                  //(아래 코드에서 읽어들인 dataURL형식)
                                }                   
                          reader.readAsDataURL(input.files[0]);
                          //File내용을 읽어 dataURL형식의 문자열로 저장
                      }
                 }//readURL()--
$("#upfile").change(function(){
             readURL(this);
           });
});

</script>

<script>
  function check_input()
   {
      if (!document.board_form.subject.value)
      {
          alert("제목을 입력하세요1");    
          document.board_form.subject.focus();
          return;
      }
      if (!document.board_form["upfile[]"].value)
      {
         alert("이미지를 선택하세요!");
         return;
      }
      if (!document.board_form.content.value)
      {
          alert("내용을 입력하세요!");    
          document.board_form.content.focus();
          return;
      }
      document.board_form.submit();
		if (show_bar === 1) { 
			$('#upload_frame').show();
			function set () {
				$('#upload_frame').attr('src','upload_frame.php?up_id=<?php echo $up_id; ?>');
			}
			setTimeout(set);
    }
   }

</script>
</head>

<body style="width:100%">

  <div id="content" style="width:500px;min-height:500px; margin-top:10px;margin-left:10px;">

		<div id="write_form_title">
			<img src="../img/write_form_title.gif">
		</div>

		<div class="clear"></div>
<?
	if($mode=="modify")
	{

?>
		<form  name="board_form" id="board_form" method="post" action="insertphoto.php?mode=modify&num=<?=$num?>&page=<?=$page?>&table=<?=$table?>" enctype="multipart/form-data"> 
<?
	}
	else
	{
?>
		<form  name="board_form" id="board_form" method="post" action="insertphoto.php?table=<?=$table?>" enctype="multipart/form-data"> 
<?
	}
?>
		<div id="write_form">
			<div class="write_line"></div>
			<div id="write_row1"><div class="col1"> 닉네임 </div>
      <div class="col2"><?=$_SESSION['usernick']." ( ".$_SESSION['userid']." )"?></div></div>
			
			<div class="write_line"></div>
			<div id="write_row2"><div class="col1"> 제목   </div>
			                     <div class="col2"><input type="text" name="subject" value="<?=$item_subject?>" ></div>
			</div>
			<div class="write_line"></div>
<div id="preview"><div class="col1"> 미리보기   </div>
 <div class="col2" style="line-height:300px; align = "center""><img id="blah" src="#" onerror="this.src='../img/select.jpg'" width=350px  align = "middle""/ ></div>
</div>

<div class="write_line"></div>
			<div id="write_row3"><div class="col1"> 내용   </div>
			                     <div class="col2"><textarea rows="5" cols="45" name="content" style='width:350px;'><?=$item_content?></textarea></div>
			</div>
			<div class="write_line"></div>

	<!--APC hidden field-->
			<input type="hidden" name="APC_UPLOAD_PROGRESS" id="progress_key" value="<?php echo $up_id; ?>"/>

			<div class="write_line"></div>
			<div id="write_row4"><div class="col1"> 첨부파일   </div>
			     <div class="col2"><input type="file" name="upfile[]" id="upfile" style='width:200px;'>  <font color='red' size=1>20MB까지 업로드 가능!</font></div>
			</div>
<? 	if ($mode=="modify" && $item_file_0)
	{
?>
			<div class="delete_ok"><?=$item_file_0?> 파일이 등록되어 있습니다. <input type="checkbox" name="del_file[]" value="0"> 삭제</div>
			<div class="clear"></div>
<?
	}
?>
			<div class="write_line"></div>

<!--Include the iframe-->
    <iframe id="upload_frame" name="upload_frame" frameborder="0" border="0" src="" scrolling="no" scrollbar="no" > </iframe>
<!---->


		</div>

		<div id="write_button" style="width:60%;">
      <a href="#"> <img src="../img/ok.png" onclick="check_input()"> </a>&nbsp;
								<a href="photo.php?page=<?=$page?>"><img src="../img/list.png"></a>
<br/>

		</form>
  
    </div> <!-- end of content -->

</body>
</html>
