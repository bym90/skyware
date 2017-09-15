<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>noticeList</title>

<script>
	/* function paging(nowPage){
		var now = nowPage
		alert("안녕");
		alert(now);
		$.ajax({
			url : "/board/noticeAjaxList",
			type : "post",
			data : "nowPage=" + now,
			success : function(responseData, statusText, xhr)
			{
				$("#container").html("");
				$("#container").html(responseData);
			},
			error : function(xhr, statusText, error)
			{
				alert("페이지 이동 실패. 관리자에게 문의하세요.");
				console.log("error : " + statusText + ", " + xhr.status + ", " + error);
			}
		});
	} 
 */

	$(document).ready(function() {
		$("#wBtn").click(function(){
			$("#lfrm").attr("action", "../board/noticeWrite");
			$("#lfrm").submit();
		});		
	});
	
	function noticeView(no, rownum){
		var noticeNo = no;
		var rownum = rownum;
		$("#lfrm").attr("action", "../board/noticeView?noticeNo="+noticeNo+"&rownum="+rownum);
		$("#lfrm").submit();
	};
	
	function paging(nowPage){
		var now = nowPage;
		location.href="../board/noticeList?nowPage="+now;
	} 
	

</script>

</head>
<body>
	<div class="container" id="container">
		<form method="POST" action="" id="lfrm">
			<input type="hidden" id="nowPage" name="nowPage" value="${nowPage }">
			<table class="table table-hover">
				<thead>
					<tr>
						<th width="10%">No.</th>
						<th width="70%">제목</th>
						<th width="10%">작성자</th>
						<th width="10%">작성날짜${page }</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${noticeList}" var="list">
						<tr id="list" onclick="noticeView(${list.noticeNo }, ${list.rownum })" align="left">
							<td>${list.rownum}</td>
							<td>${list.noticeTitle}</td>
							<td>${list.noticeWriter}</td>
							<td>${list.noticeDate}</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4" align="right"><input type="button" id="wBtn" name="wBtn" value="작성" class="btn btn-primary"></td>
					</tr>
				</tfoot>
			</table>
			<nav>
				<ul class="pagination">
					<li>
						<c:if test="${PINFO.startPage eq 1}">
							<a href="#" aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a>
						</c:if> 
						<c:if test="${PINFO.startPage ne 1}">
							<a href="javascript:paging(${PINFO.startPage - 1});" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span>
							</a>
						</c:if>
					</li>
					<c:forEach var="page" begin="${PINFO.startPage}" end="${PINFO.endPage}">
						<li><a href="javascript:paging(${page})">${page}</a></li>
					</c:forEach>
					<li>
						<c:if test="${PINFO.endPage ne PINFO.totalPage}">
							<a href="javascript:paging(${PINFO.endPage + 1})" aria-label="Next"> 
							<span aria-hidden="true">&raquo;</span>
							</a>
						</c:if> <c:if test="${PINFO.endPage eq PINFO.totalPage}">
							<a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a>
						</c:if>
					</li>
				</ul>
			</nav>

		</form>
	</div>
</body>
</html>