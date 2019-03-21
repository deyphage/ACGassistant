//page的ID必須修正 預設ID名稱為demo-page
//使用規則 adaryimgsrc 放置 廣告圖片, adaryimgtitle 放置廣告台詞, arrayadlink 廣告昌連結
//#adimg 廣告圖片名稱  #adtitle廣告台詞名稱  #adlink廣告超連結

var adpointer=0;
var adimgsrc, adimgtitle, adlink;

var adaryimgsrc = new Array("p1.jpg", "p2.jpg", "p3.jpg", "p4.jpg", "p5.jpg", "p6.jpg");
var adaryimgtitle = new Array("竹鼠一號", "竹鼠二號","竹鼠三號", "竹鼠四號", "竹鼠五號", "竹鼠六號");
var arrayadlink = new Array("http://link01", "http://link02", "http://link03", "http://link04", "http://link05", "http://link06");

// for home page
$( document ).on( "pagecreate", "#demo-page", function() {
    $( document ).on( "swipeleft swiperight", "#demo-page", function( e ) {
        if ( $( ".ui-page-active" ).jqmData( "panel" ) !== "open" ) {
            if ( e.type === "swipeleft" ) {
                adnext();
            } else if ( e.type === "swiperight" ) {
                adprev();
            }
        }
    });
});	





function adprev(){
	adpointer --;
	if(adpointer < 0){
		adpointer = 5;
	}
	adimgsrc = "images/" + adaryimgsrc[adpointer];
	adimgtitle = adaryimgtitle[adpointer];
	adlink = arrayadlink[adpointer];
	$("#adimg").fadeOut(500);
	$("#adimg").attr("src", adimgsrc).fadeIn(500);
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
	$("#adimg").fadeOut(500);
	$("#adimg").attr("src", adimgsrc).fadeIn(500);
	$("#adtitle").text(adimgtitle);
	$("#adlink").attr("href", adlink);
}