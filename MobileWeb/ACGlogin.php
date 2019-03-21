<?php session_start();?>
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
		$(function(){
			$("#login_ok").bind("click", function(){
				$.ajax({
					type: "POST",
					url: "https://acgassistant2019.000webhostapp.com/MobileWeb/ACG-login-api.php",
					data: {Username: $("#username").val(), Password: $("#password").val()},
					success: login,
					error:function(){
						alert("登入api回傳失敗");
					}
				});
			});	
		});

		function login(data){
			if(data ==1){
				alert("會員"+$("#username").val()+"您好");
				location.href = "a01.php#a0102";
			}else{
				alert(data);
			}
		}


	</script>
</head>
<body>
	<div data-role="page" id="home">
		<div data-role="header" data-position="fixed" id="home" data-theme="b">
			<h1>會員登入</h1>
		</div>
		<div role="main" class="ui-content">
			<div data-role="fieldcontain">
				<label for="username">帳號</label>
				<input type="text" name="username" id="username">
			</div>
			<div id="error_username"></div>
			<div data-role="fieldcontain">
				<label for="password">密碼</label>
				<input type="password" name="password" id="password">
			</div>
			<div class="ui-grid-a">
				<div class="ui-block-a">
					<a href="#" data-role="button" data-theme="b">取消</a>		
				</div>
				<div class="ui-block-b">
					<a href="#" data-role="button" data-theme="b" id="login_ok">登入</a>		
				</div>
			</div>
		</div>
		<div data-role="footer" data-position="fixed" data-theme="b">
			<h1>footer</h1>
		</div>
	</div>
</body>
</html>		