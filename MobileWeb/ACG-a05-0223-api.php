<?php 
	$username = $_POST["Username"];
	$password = $_POST["Password"];
	$email = $_POST["Email"];
	require_once("ACG_tool.php");
	
	$cnt = create_connection();
	$sql = "INSERT INTO member (memID, memName, memPwd, memEmail) 
			VALUES(null, '$username', '$password', '$email')";

	$result = execute_sql($cnt, "id8606975_acgassistant", $sql);
	echo $result;

 ?>