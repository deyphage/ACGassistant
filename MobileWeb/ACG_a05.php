<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Document</title>
	<link rel="stylesheet" href="css/jquery.mobile-1.4.5.min.css">
	<script src="js/jquery-2.1.0.min.js"></script>
	<script src="js/jquery.mobile-1.4.5.min.js"></script>

	<script>
		// a0504-註冊頁面，判斷帳號，密碼，Email判斷
	    	var flag_a0504_username = false;
			var flag_a0504_password = false;
			var flag_re_a0504_password = false;
			var flag_a0504_mail = false;

	    $(function(){
	    	  $("#a0504_username").bind("input propertychange",check_a0504_username);
	          $("#a0504_password").bind("input propertychange",check_a0504_password);
	          $("#a0504_re_password").bind("input propertychange",check_a0504_re_password);
	          $("#a0504_mail").bind("input propertychange",check_a0504_mail); 
	    });

	    function check_a0504_username(){
	      if ($("#a0504_username").val().length<6) {
	               $("#msg_a0504_username").css({"background-color":"pink","color":"blue","text-shadow":"0 0 0"});
	               $("#msg_a0504_username").text("帳號不得少於5個字!");
	                flag_a0504_username = false;
	      }else{ 
	          $("#msg_a0504_username").text("");
	            	flag_a0504_username = true;
	      }
	    }
	    
	    function check_a0504_password(){
	      if ($("#a0504_password").val().length<8) {
	               $("#msg_a0504_password").css({"background-color":"pink","color":"blue","text-shadow":"0 0 0"});
	               $("#msg_a0504_password").text("帳號不得少於8個字!");
	               flag_a0504_password = false;
	      }else{ 
	          $("#msg_a0504_password").text("");
	          	   flag_a0504_password = true;
	      }
	    }

	    function check_a0504_re_password(){
	      if ($("#a0504_re_password").val()==$("#a0504_password").val()) {
	               $("#msg_a0504_re_password").text("");
	               flag_a0504_re_password = true;
	               
	      }else{ 
	          $("#msg_a0504_re_password").css({"background-color":"pink","color":"blue","text-shadow":"0 0 0"});
	          $("#msg_a0504_re_password").text("密碼不符合!");
	          	  flag_a0504_re_password = false;
	      }
	    }

	    function check_a0504_mail(){
	      if ($("#a0504_mail").val().length<10) {
	               $("#msg_a0504_mail").css({"background-color":"pink","color":"blue","text-shadow":"0 0 0"});
	               $("#msg_a0504_mail").text("Email不得少於10個字");
	               flag_a0504_mail = false;
	      }else{ 
	          $("#msg_a0504_mail").text("");
	               flag_a0504_mail = true;
	      }
	    }

	    
		$(function(){
				$("#ok-btn").bind("click", send);
			});
			function send(){
				if( flag_a0504_username && flag_a0504_mail && flag_a0504_password && flag_a0504_re_password){
					if(confirm("已經確認註冊資料了嗎?")){
					$.ajax({
						type: "POST",
						url: "https://acgassistant2019.000webhostapp.com/MobileWeb/ACG-a05-0223-api.php",
						data: {Username: $("#a0504_username").val(), Password: $("#a0504_password").val(), 	Email: $("#a0504_mail").val()},
						success: show,
						error: function(){
							alert("error");
						}
					});				
				} //if end
			}else{
				//提醒不正確
				if(!flag_a0504_username){
					alert("帳號不符規定!")
				}
				if(!flag_a0504_password){
					alert("密碼不符規定!")
				}
				if(!flag_a0504_re_password){
					alert("密碼不一致!")
				}
				if(!flag_a0504_mail){
					alert("Email不符合規定!")
				}
			  }
			}

			function show(data){
				if(data){
					alert("註冊成功!");
					location.href = "ACGlogin.php";
				}else{
					alert("註冊失敗!");
				}
	        }

	</script>
</head>
<body>
	</div>
	<!-- a0502 結束 -->
	<!-- a0504 Login -->
	<div data-role="page" id="home">
		<div data-role="header" data-theme="b">
			<h1>
				會員註冊
			</h1>
		</div>
        
        <div role="main" class="ui-content">
        	<div data-role="fieldcontain">
				<label for="a0504_username">帳號:</label>
				<input type="text" name="a0504_username" id="a0504_username">
			</div>
			    <div id="msg_a0504_username"></div>

			<div data-role="fieldcontain">
				<label for="a0504_password">密碼:</label>
				<input type="password" name="a0504_password" id="a0504_password">
			</div>
			    <div id="msg_a0504_password"></div>

			<div data-role="fieldcontain">
				<label for="a0504_re_password">確認密碼:</label>
				<input type="password" name="a0504_re_password" id="a0504_re_password">
			</div>
			    <div id="msg_a0504_re_password"></div>

			<div data-role="fieldcontain">
				<label for="a0504_mail">Email:</label>
				<input type="email" name="a0504_mail" id="a0504_mail">
			</div>
			    <div id="msg_a0504_mail"></div>
			
			<div class="ui-grid-a">
				<div class="ui-block-a">
					<a href="#" data-role="button" data-theme="b" data-rel="back">取消</a>		
				</div>
				<div class="ui-block-b">
					<a href="#" data-role="button" data-theme="b" id="ok-btn">註冊</a>		
				</div>
			</div>
        </div>

		<div data-role="footer" data-theme="b" data-position="fixed">
			<h1>
				頁尾
			</h1>

		</div>

	</div>
	<!-- a0504 結束 -->
</body>
</html>