<?php
	header("Access-Control-Allow-Origin:*");
	require_once("ACG_tool.php"); 	//導入之前寫好的dbtool檔案
	$link=create_connection();

	$sql="SELECT * FROM animation";
	$result=execute_sql($link,"id8606975_acgassistant",$sql);  //中間輸入data base名稱
	$total_records=mysqli_num_rows($result);
	$rows=mysqli_fetch_assoc($result);
	$ArrData = array();

	do{
		$ArrData[]=$rows;
	}while ($rows=mysqli_fetch_assoc($result));

	echo json_encode($ArrData);
 		
	
?>