function listPage(totalCnt){
	
	var curPage = parseInt($("#curPage").val());
	var pageSize = parseInt($("#pageSize").val());
	var totalPage = parseInt($("#totalPage").val());
	var prev = parseInt((curPage-2))*10;
	var next = parseInt((curPage))*10;
	var lastPage = parseInt((totalPage-1))*10;
	
	var tempPageVal = ((curPage / 10).toString().length == 1)? Math.floor(curPage / 10) -1 : Math.floor(curPage / 10);
	var startPage = eval(tempPageVal * 10 + 1);
	var endPage = eval(startPage + 10);
	
	if(endPage > totalPage) {
		endPage = totalPage + 1;
	}
	
	var htmlLeft = '';
	if(totalPage != 1 && curPage != 1){
		htmlLeft += '<ul class="boardNav">';
		htmlLeft += '<li><a href="javascript:goSelPage(0)" class="btn_first"></a></li>';
		htmlLeft += '<li><a id="prev" href="javascript:goSelPage('+prev+')" class="btn_prev"><</a></li>';
		htmlLeft += '</ul>';
	}
	
	var htmlCenter = '<ul class="boardPage">';
	for(var i=startPage; i<endPage; i++){
		var current = (i-1)*10;
		if(i == curPage){
			htmlCenter += '<li class="on"><a href="javascript:goSelPage('+current+')">'+i+'</a></li>';
		}else{
			htmlCenter += '<li><a href="javascript:goSelPage('+current+')">'+i+'</a></li>';
		}
	}
	htmlCenter += '</ul>';
	
	var htmlRight = "";
	if(totalPage != 1 && curPage != totalPage && totalPage != 0){
		htmlRight += '<ul class="boardNav">';
		htmlRight += '<li><a id="next" href="javascript:goSelPage('+next+')" class="btn_next">next</a></li>';
		htmlRight += '<li><a href="javascript:goSelPage('+lastPage+')" class="btn_last">last</a></li>';
		htmlRight += '</ul>';
	}
	$("#pagingList").html(htmlLeft+htmlCenter+htmlRight)
}

//팝업 페이지 셋팅
function listPagePop(totalCnt){
	
	var curPage = parseInt($("#curPagePop").val());
	var pageSize = parseInt($("#pageSizePop").val());
	var totalPage = parseInt($("#totalPagePop").val());
	var prev = parseInt((curPage-2))*10;
	var next = parseInt((curPage))*10;
	var lastPage = parseInt((totalPage-1))*10;
	
	var tempPageVal = ((curPage / 10).toString().length == 1)? Math.floor(curPage / 10) -1 : Math.floor(curPage / 10);
	var startPage = eval(tempPageVal * 10 + 1);
	var endPage = eval(startPage + 10);
	
	if(endPage > totalPage) {
		endPage = totalPage + 1;
	}
	
	var htmlLeft = '';
	if(totalPage != 1 && curPage != 1){
		htmlLeft += '<ul class="boardNav">';
		htmlLeft += '<li><a href="javascript:goSelPopPage(0)" class="btn_first"></a></li>';
		htmlLeft += '<li><a id="prev" href="javascript:goSelPopPage('+prev+')" class="btn_prev"><</a></li>';
		htmlLeft += '</ul>';
	}
	
	var htmlCenter = '<ul class="boardPage">';
	for(var i=startPage; i<endPage; i++){
		var current = (i-1)*10;
		if(i == curPage){
			htmlCenter += '<li class="on"><a href="javascript:goSelPopPage('+current+')">'+i+'</a></li>';
		}else{
			htmlCenter += '<li><a href="javascript:goSelPopPage('+current+')">'+i+'</a></li>';
		}
	}
	htmlCenter += '</ul>';
	
	var htmlRight = "";
	if(totalPage != 1 && curPage != totalPage && totalPage != 0){
		htmlRight += '<ul class="boardNav">';
		htmlRight += '<li><a id="next" href="javascript:goSelPopPage('+next+')" class="btn_next">next</a></li>';
		htmlRight += '<li><a href="javascript:goSelPopPage('+lastPage+')" class="btn_last">last</a></li>';
		htmlRight += '</ul>';
	}
	$("#pagingPopList").html(htmlLeft+htmlCenter+htmlRight)
}