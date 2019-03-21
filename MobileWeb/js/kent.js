

		$(function(){
			setInterval(gonext,2000);
			$('#goback').bind('click',goback);
			$('#gonext').bind('click',gonext);
		});

	var index=0;
	var curImgSrc,curBook;
	var aryBookSrc = new Array("HTC U11.jpg","HTC U12.jpg");
	var aryBookName = new Array("HTC U11","HTC U12");

	function goback(){
		index--;
		if(index<0){index=1;}
		curImgSrc="images/" +aryBookSrc[index];
		curBook=aryBookName[index];
		$("#pimg").attr("src",curImgSrc);
		$("#pname").text(curBook);
		}

	function gonext(){
		index++;
		if(index>1){index=0;}
		curImgSrc="images/" +aryBookSrc[index];
		curBook=aryBookName[index];
		$("#pimg").attr("src",curImgSrc);
		$("#pname").text(curBook);
	}

