<?php 
    session_start();
?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" >
	<title>Document</title>
	<link rel="stylesheet" href="css/jquery.mobile-1.4.5.min.css">
	<script src="js/jquery-2.1.0.min.js"></script>
	<script src="js/jquery.mobile-1.4.5.min.js"></script>
	<script src="js/ACGfunc_goto.js"></script>

</head>


<style> 
	.setting{ 
		text-align: center; 
		font-family:微軟正黑體;
	} 

	.loading{
		top:0;
		left:0;
		right:0;
		bottom:0;
		margin-top: 200px;

	}

	.content{
		display: none;
	}


	.a0102btn{
		float: right;
	}

	.a0102font{
		font-family: 微軟正黑體;
	}

	.a0102img{
		margin:auto;
		max-width:100%; 
  		max-height:100%; 
	}

	.a0101padding{
		padding-top: 20px;
		padding-left: 20px;
		padding-bottom: 20px;
		padding-right: 20px;
	}
	.ml10{
		margin-left: 10px;
	}

</style>


<script>
	//---------------------------留言板按鈕，尚未完成----------------------------------------------

	$(function(){
		$.ajax({
	      	type:"GET",
	      	url:"getnews.php",
	      	dataType:"json",
	      	success:show,
	      	error: function(){
	          	alert("getnews ERROR!");
	        }
	    });
	    $.ajax({
	      	type:"GET",
	      	url:"getComment.php",
	      	dataType:"json",
	      	success:creatCommentList,
	      	error: function(){
	          	alert("CommentList ERROR!");
	        }
	    });
		$("#send").bind("click",sendCmt); //按鈕監聽
		//------當a0102頁面被打開，開始建立留言列表-------
		// 	$("#a0102").bind("pageshow",creatCommentList);
		//------------------------------------------------
	});

	// function show_msg(){

	// 	$("#showmsg").text($("#msg").val());
	// }
	//--------------------------------------------------------------------------------------------

	//------------------------------------列表頁面抓資料庫-----------------------------------------

    

    function show(data){ 
    	for (i=0;i<data.length;i++){  //首頁與資料庫連結
    		$("#nwsList").append(
    			'<li id="'+data[i].nwsID+'" class="nwsclick"><a href="#a0102"><img src="images/'+data[i].nwsPic+'.jpg" width="100%" height="100%"><h3>'+data[i].nwsName+'</h3><p>'+data[i].nwsInfo+'</p></a><a href="#"></a></li>');  		
    	}
    	$('#nwsList').listview().listview('refresh');
//-----------------------------------list按下物件轉跳該頁面，尚未完成----------------------------------
     //    $('.nwsclick').click(function() { //選單按下去的監聽
        	
     //    	$("#a0102_content").empty("");
     //    	var j=($(this).attr("id"))-1;

     //    	$("#a0102_content").append(  //data[j]無法代入
     //    	'<div class="a0102btn"><a href="" data-role="button">Dislike</a></div><div class="a0102btn"><a href="" data-role="button">Like</a></div><br><br><div><h1>這是此文章的主題</h1></div><div><img src="images/a0102img.png" alt="" width="350"></div><br><div>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi maxime quis deleniti iure placeat ducimus voluptate perspiciatis nam eveniet eos accusantium maiores nulla temporibus fuga sunt tenetur error, delectus veniam.</div><br><div data-role="fieldcontain"><label for="msg">請輸入你的留言</label><textarea cols="40" rows="5" name="msg" id="msg"></textarea></div><div class="ui-grid-b"><div class="ui-block-a"></div><div class="ui-block-b"><a href="" data-role="button">取消</a></div><div class="ui-block-c"><a href="" data-role="button" id="send">送出</a></div></div><br>');
    	// });

    }	//function show() 結束
//--------------------------------------------------------------------------------------------
	function creatCommentList(data){
		$("#a01_commentShow").html("");
    	for (i=0;i<data.length;i++){  //首頁與資料庫連結
    		$("#a01_commentShow").append(
    			'<div class="ml10"><img src="images/head.png" alt="">第'+data[i].cmtMemID+'號會員說：'+data[i].cmtInfo+'</div><br>');  		
    	}
    	$('#a01_commentShow').listview().listview('refresh');
	}

	function sendCmt(){
		<?php 
		    if(isset($_SESSION["ID"])){
		        echo 'creatCmt();';
		    }else{
		        echo 'alert("請先登入會員");location.href="ACGlogin.php";';
		    }
		?> 
	}
	function creatCmt(){
		$.ajax({
			type:"POST",
			url: "creatCmtAPI.php",
			data:{ID:10,context: $("#msg").val()},
			success:insertCmt,
			error:function(){
				alert("新增留言api回傳失敗");
			}
		});	//ajax end
	}
	function insertCmt(data){
		if(data == 1){
				location.reload();
			}else{
				alert(data);
			}
	}
</script>	


<body>
	<!--a0100 開頭動畫-->
	<div data-role="page" id="a0100" class="setting"> 
		<!--頁首-->
		<div data-role="header" data-theme=b data-position="fixed">
			<h3>歡迎使用宅助理</h3>
		</div>
		
		<!--頁中-->	
		<div data-role="main" class="ui-content">

			<div class="loading">
				<h1>正在讀取中!</h1>
				<img src="images/Pacman-0.6s-200px.svg" alt="">	
			</div>

			<div class="content">
				<h1>讀取完囉!</h1>
				<a href="#a0101" data-role="button">按此進入首頁</a><BR> 
				<img src="images/TitleImg.png" alt="">
			</div>

		</div>

		<!--動畫效果-->

		<script> 
			$('.loading').delay(2000).fadeOut(500,function(){
			$('.content').fadeIn(500);
		});

		</script>
		
		<!--頁尾-->	
		<div data-role="footer" data-theme=b data-position="fixed">
			<h3>歡迎使用宅助理</h3>
		</div>
	
	</div>


	<!--a0101 首頁-->
	<div data-role="page" id="a0101">
		
		<!--頁首 panel-->
		<div data-role="panel" id="a0501_a0101panel" data-position="right">
           	<a href="ACGlogin.php" data-role="button" data-theme="b" id="LogIN" data-icon="user" data-iconpos="right">登入</a>
           	<a href="Member_logout.php" data-role="button" data-theme="b" id="LogOUT" data-icon="user" data-iconpos="right">登出</a>
			<a href="http://192.168.60.105/0223/ACG_a05.php" data-role="button" data-theme="b" id="Reg" data-icon="user" data-iconpos="right">註冊</a>
			<a href="#a0503" data-role="button" data-theme="b" id="Setting" data-icon="gear" data-iconpos="right">Setting</a>
       </div>


		<!-- ---------------------------------------------------------------------------------------------- -->
		<!-- header -->
		<div data-role="header" data-theme=b data-position="fixed">
			<a href="#a0501_a0101panel" data-role="button" data-theme="a" class="ui-btn-right">:</a>
			<h3>頁首</h3>
		</div>
			
		<!--頁中-->	
		<div role="main" class="ui-content">
			<ul data-role="listview" id="nwsList" data-split-icon="heart" data-split-theme="a" class="a0101padding"></ul>
    	</div>
	
	<!--下面四個按鈕-->	
		<div data-role="footer" data-position="fixed" data-theme="b"> 
			<div data-role="navbar"> 
				<ul> 
					<li><a href="#" onclick="goto_a01()" class="ui-btn-active"><img src="images/home.png" alt="" width="30"></a></li>
		            <li><a href="#" onclick="goto_a02()"><img src="images/television.png" alt="" width="30"></a></li> 
		            <li><a href="#" onclick="goto_a03()"><img src="images/running.png" alt="" width="30"></a></li>
		            <li><a href="#" onclick="goto_a04()"><img src="images/calendar.png" alt="" width="30"></a></li>  
				</ul> 
			</div>

		</div>

	</div>

	<!--a0102 從a0101點進去後的畫面-->
	<div data-role="page" id="a0102" class="a0102font">
		
		<!--頁首 上一頁跟panel-->
		<!--頁首 panel-->
		<div data-role="panel" id="a0501_a0102panel" data-position="right">
           	<a href="ACGlogin.php" data-role="button" data-theme="b" id="LogIN" data-icon="user" data-iconpos="right">登入</a>
           	<a href="Member_logout.php" data-role="button" data-theme="b" id="LogOUT" data-icon="user" data-iconpos="right" >登出</a>
			<a href="ACG_a05.php" data-role="button" data-theme="b" id="Reg" data-icon="user" data-iconpos="right">註冊</a>
			<a href="#a0503" data-role="button" data-theme="b" id="Setting" data-icon="gear" data-iconpos="right">Setting</a>
       </div>
		
		<div data-role="header" data-theme=b data-position="fixed">
			<a href="" data-role="button" data-rel="back" data-theme="a">Back</a>
			<a href="#a0501_a0102panel" data-role="button" data-theme="a" class="ui-btn-right">:</a>
			<h3></h3>
		</div>

		<!--頁中-->
		<div data-role="main" class="ui-content" id="a0102_content">

			<!--like dislike-->
			<div class="a0102btn"><a href="" data-role="button">Dislike</a></div>
			<div class="a0102btn"><a href="" data-role="button">Like</a></div><br><br>
			
			<div><h1>這是此文章的主題</h1></div>

			<div><img src="images/a0102img.png" alt="" width="350"></div>
			<br>
			<div>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi maxime quis deleniti iure placeat ducimus voluptate perspiciatis nam eveniet eos accusantium maiores nulla temporibus fuga sunt tenetur error, delectus veniam.</div>
			<br>

			<!--留言區-->
			<div data-role="fieldcontain">
				<label for="msg">請輸入你的留言</label>
				<textarea cols="40" rows="5" name="msg" id="msg"></textarea>			
			</div>
			
			<div class="ui-grid-b">
				<div class="ui-block-a"></div>
				<div class="ui-block-b"><a href="" data-role="button">取消</a></div>
				<div class="ui-block-c"><a href="" data-role="button" id="send">送出</a></div>
			</div>
			<br>

			<div id="a01_commentShow">
				<div class="ml10"><img src="images/head.png" alt="">第'+data[i].cmtMemID+'號會員說：'+data[i].cmtInfo+'</div><br>
				<div class="ml10"><img src="images/head.png" alt="">第'+data[i].cmtMemID+'號會員說：'+data[i].cmtInfo+'</div><br>
				<div class="ml10"><img src="images/head.png" alt="">第'+data[i].cmtMemID+'號會員說：'+data[i].cmtInfo+'</div><br>
				<div class="ml10"><img src="images/head.png" alt="">第'+data[i].cmtMemID+'號會員說：'+data[i].cmtInfo+'</div><br>
			</div>
			
	
		</div>

		<!--下面四個按鈕-->		
		<div data-role="footer" data-position="fixed" data-theme="b"> 
			<div data-role="navbar"> 
				<ul> 
					<li><a href="#" onclick="goto_a01()" class="ui-btn-active"><img src="images/home.png" alt="" width="30"></a></li>
          			<li><a href="#" onclick="goto_a02()"><img src="images/television.png" alt="" width="30"></a></li> 
          			<li><a href="#" onclick="goto_a03()"><img src="images/running.png" alt="" width="30"></a></li>
          			<li><a href="#" onclick="goto_a04()"><img src="images/calendar.png" alt="" width="30"></a></li> 
				</ul> 
			</div>

		</div>
	
	</div>
	


</body>
</html>
