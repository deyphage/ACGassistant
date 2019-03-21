//使用規則 adaryimgsrc 放置 廣告圖片, adaryimgtitle 放置廣告台詞, arrayadlink 廣告昌連結
//#adimg 廣告圖片名稱  #adtitle廣告台詞名稱  #adlink廣告超連結

$(function(){
	setInterval(ad, 2000);
});

var adpointer=0;
var adimgsrc, adimgtitle, adlink;

var adaryimgsrc = new Array("cake01.jpg", "cake02.jpg", "cake03.jpg", "cake04.jpg", "cake05.jpg", "cake06.jpg");
var adaryimgtitle = new Array("草莓蛋糕", "巧克力高","蛋糕03", "蛋糕04", "蛋糕05", "蛋糕06");
var arrayadlink = new Array("http://link01", "http://link02", "http://link03", "http://link04", "http://link05", "http://link06");

function adback(){
	adpointer --;
	if(adpointer < 0){
		adpointer = 5;
	}
	adimgsrc = "images/" + adaryimgsrc[adpointer];
	adimgtitle = adaryimgtitle[adpointer];
	adlink = arrayadlink[adpointer];
	$("#adimg").attr("src", adimgsrc);
	$("#adtitle").text(adimgtitle);
	$("#adlink").attr("href", adlink);
}

function adnext(){
	adpointer ++;
	if(adpointer > 5){
		adpointer = 0;
	}
	adimgsrc = "images/" + adaryimgsrc[adpointer];
	adimgtitle = adaryimgtitle[adpointer];
	adlink = arrayadlink[adpointer];
	$("#adimg").attr("src", adimgsrc);
	$("#adtitle").text(adimgtitle);
	$("#adlink").attr("href", adlink);
}