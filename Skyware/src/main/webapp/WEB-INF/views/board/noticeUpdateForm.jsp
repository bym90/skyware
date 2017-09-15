<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>noticeUpdateForm</title>
<script>
	$(document).ready(function() {
		$("#uBtn").click(function() {
			var title = $("#noticeTitle").val();
			var body = $("#noticeBody").val();
			
			if(title == ""){
				alert("제목을 입력하세요");
				return;
			} else if(body == ""){
				alert("내용을 입력하세요");
				return;
			}
			$("#ufrm").attr("action", "../board/noticeUpdate")
			$("#ufrm").submit();
		});
		
		$("#cBtn").click(function(){
			$("#ufrm").attr("action", "../board/noticeView")
			$("#ufrm").submit();
		})
	});
</script>

</head>
<body>
<div class="container">
	<form method="POST" action="" id="ufrm">
	<input type="hidden" id="nowPage" name="nowPage" value="${nowPage }">
	<input type="hidden" id="noticeNo" name="noticeNo" value="${notice.noticeNo }">
	<input type="hidden" id="rownum" name="rownum" value="${notice.rownum }">
	<table class="table">
		<thead>
			<tr>
				<th colspan="2">공지사항 수정하기</th>
			</tr>
		</thead>
		<tbody>
		<tr>
			<th width="20%">제목${notice.rownum }</th>
			<td width="*"><input type="text" name="noticeTitle" id="noticeTitle" size="130" value="${notice.noticeTitle }"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
			<textarea name="noticeBody" id="noticeBody" cols="132" rows="10">${notice.noticeBody }</textarea>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="2">			
				<input type="button" id="uBtn" name="uBtn" class="btn btn-primary" value="수정">
				<input type="button" id="cBtn" name="cBtn" class="btn btn-primary" value="취소">
			</td>
		</tr>
		</tbody>
	</table>
	</form>
</div>
</body>
</html>