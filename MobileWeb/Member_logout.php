<?php
session_start();
// session_unregister();
// $myurl='a01-0203.php';
session_unset();
session_destroy();
header('Location: a01.php?refresh');
// echo '<meta http-equiv="REFRESH" CONTENT="0;url=a01-0203.php">';
// header("refresh:5;url= ".$myurl);
// echo '<h1>Wait for a while, we are securely moving you...</h1>';
exit();
?>