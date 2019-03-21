//使用廣告 adSrcImg 放置圖片檔 adName 放置文字檔
//廣告系統名稱 adarySrc 廣告系統文字 adaryName

// $(function(){
// 	setInterval(ad,2000);
// 	});

var adindex=0;   //目前的頁數
var adSrcImg,adName;//目前取得的圖檔及文字檔

//圖檔陣列
var adarySrc = new Array("cat1.jpg","cat2.jpg","cat3.jpg","cat4.jpg");
//文字檔陣列
var adaryName = new Array("撒嬌","賣萌","討飯","吐舌");

function ad(){  //上一頁
	adindex --;
	if(adindex<0){adindex=3;}//循環手段
	adSrcImg="images/"+adarySrc[adindex];//目前取得的圖檔名稱
	adName=adaryName[adindex];//目前取得的文字檔名稱
	$("#adsrcimg").attr("src",adSrcImg);//動態顯示圖片
	$("#adname").text(adName);//動態顯示文字檔
}