<?php
header("Access-Control-Allow-Origin:*");
require_once("ACG_tool.php");

$cmtMemID=$_POST["ID"];
$cmtNews=22;
$cmtInfo=$_POST["context"];


$link=create_connection();
mysqli_query($link,"SET NAMES UTF8");


$sql = "INSERT INTO comment(cmtID,cmtMemID,cmtNews,cmtInfo) VALUES(null,'$cmtMemID','$cmtNews','$cmtInfo')";
	
if(execute_sql($link,"id8606975_acgassistant",$sql)){
	echo 1;
}else{
	echo "commentFail";
}

mysqli_close($link);
?>