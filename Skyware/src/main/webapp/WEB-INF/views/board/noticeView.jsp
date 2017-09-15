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
<title>noticeView</title>
<script>
	$(document).ready(function() {
		$("#lBtn").click(function() {
			$("#vfrm").attr("action", "../board/noticeList");
			$("#vfrm").submit();
		});
		
		$("#uBtn").click(function() {
			var userNm = "${sessionScope.userNm}";
			var writer = $("#noticeWriter").val();
			if(writer != userNm){
				alert("작성자만 수정할 수 있습니다.");
				return;	
			}
			$("#vfrm").attr("action", "../board/noticeUpdateForm");
			$("#vfrm").submit();
		});
		
		$("#dBtn").click(function() {
			var userNm = "${sessionScope.userNm}";
			var writer = $("#noticeWriter").val();
			if(writer != userNm){
				alert("작성자만 삭제할 수 있습니다.");
				return;	
			}
			$("#vfrm").attr("action", "../board/noticeDel");
			$("#vfrm").submit();
		});
	});
</script>
</head>
<body>
	<div class="container">
		<form method="POST" action="" id="vfrm">
		<input type="hidden" id="nowPage" name="nowPage" value="${nowPage }">
		<input type="hidden" id="noticeNo" name="noticeNo" value="${notice.noticeNo}">
		<input type="hidden" id="noticeTitle" name="noticeTitle" value="${notice.noticeTitle}">
		<input type="hidden" id="noticeBody" name="noticeBody" value="${notice.noticeBody}">
		<input type="hidden" id="noticeWriter" name="noticeWriter" value="${notice.noticeWriter}">
		<input type="hidden" id="rownum" name="rownum" value="${notice.rownum}">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="10%">No.</th>
					<th width="70%">제목</th>
					<th width="10%">작성자</th>
					<th width="10%">작성날짜</th>
				</tr>
			</thead>
			<tbody>
				<tr align="left">
					<td>${notice.rownum}</td>
					<td>${notice.noticeTitle}</td>
					<td>${notice.noticeWriter}</td>
					<td>${notice.noticeDate}</td>
				</tr>
			</tbody>
			<tfoot>
				<tr align="left">
					<td>내용</td>
					<td colspan="3">${notice.noticeBody}</td>
				</tr>
				<tr>
					<td colspan="4" align="right">
					<input type="button" id="lBtn" name="lBtm" value="목록" class="btn btn-primary"> 
					<input type="button" id="uBtn" name="uBtn" value="수정" class="btn btn-primary"> 
					<input type="button" id="dBtn" name="dBtn" value="삭제" class="btn btn-primary"></td>
				</tr>
			<tfoot>
		</table>
		</form>
	</div>
</body>
</html>