		
		<div id="sub_title">
 			<img src="../img/menu_mypage.png">
		</div>
		<ul>
		<?session_start();
		if($_SESSION['userlevel']!=5)
		{?>
    <li><a href="../greet/list.php">사용자 정보</a></li>
           <li><a href="../greet/photo.php">의료사진</a></li>
            <li><a href="../greet/graph.php">그래프</a></li>
             <li><a href="../greet/alarm.php">약 알림 시간</a></li>
			 <?}
			 else
			 {?>
    <li><a href="../greet/list.php">정보 수정</a></li>
             <li><a href="../greet/list_patient.php">나의 환자</a></li>
			 <?if($_SESSION['pname']!=false)
				 {
				 echo'<hr>';
			 $s=$_SESSION['pname'];
			 echo "<li style='text-align:center;font-size:20px;color:#6500FC;font-weight:900;'>$s 님</li>";
				?>
			<hr>		
           <li><a href="../greet/patient_photo.php">의료사진</a></li>
            <li><a href="../greet/patient_graph.php">그래프</a></li>
             <li><a href="../greet/patient_alarm.php">약 알림 시간</a></li>
				 <?}
			 }
			 ?>
		</ul>
			
