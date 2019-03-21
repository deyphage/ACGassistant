<?php
	session_start();

	$username = $_POST["Username"];
	$password = $_POST["Password"];

	require_once("ACG_tool.php");
	$cnt = create_connection();

	$sql = "SELECT * FROM member WHERE memName ='$username' AND memPwd ='$password'";

	$result = execute_sql($cnt, "id8606975_acgassistant", $sql);
	$row=mysqli_fetch_assoc($result);
	if(mysqli_num_rows($result) == 1){
		$_SESSION["ID"]=$row["memID"];
		echo 1;//"login success";
	}else{
		echo "帳號或密碼錯誤!!";//"login fail";
	}

	
?>