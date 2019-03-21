<?php 
  function create_connection()
  {
    $cnt = mysqli_connect("localhost","id8606975_acg","acg123456")
    or die("無法建立資料連接:".mysqli_connect_error());
    mysqli_query($cnt,"SET NAMES UTF8");
    return $cnt;
  }
  function execute_sql($cnt,$database,$sql)
   {
   	mysqli_select_db($cnt,$database)
   	or die("開啟資料庫失敗:".mysqli_error($cnt));

   	$result = mysqli_query($cnt, $sql);
		return $result;
   }
 ?>

 