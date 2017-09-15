<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>noticeWrite</title>
<script>
	$(document).ready(function() {
		$("#wBtn").click(function() {	
			var title = $("#noticeTitle").val();
			var body = $("#noticeBody").val();
			
			if(title == ""){
				alert("제목을 입력하세요");
				return;
			} else if(body == ""){
				alert("내용을 입력하세요");
				return;
			}
			
			$("#wfrm").attr("action", "../board/noticeWriteProc")
			$("#wfrm").submit();
		});
		
		$("#cBtn").click(function(){
			$("#wfrm").attr("action", "../board/noticeList")
			$("#wfrm").submit();
		})
	});
</script>

</head>

<body>
<div class="container">
	<form method="POST" action="" id="wfrm">
	<input type="hidden" id="nowPage" name="nowPage" value="${nowPage }">
	<table class="table table-hover">
		<thead>
			<tr>
				<th colspan="2">공지사항 글쓰기</th>
			</tr>
		</thead>
		<tbody>
		<tr>
			<th width="20%">제목</th>
			<td width="*"><input type="text" name="noticeTitle" id="noticeTitle" class="form-control" size="130"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
			<textarea name="noticeBody" id="noticeBody" class="form-control" cols="132" rows="10"></textarea>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="2">			
				<input type="button" id="wBtn" name="wBtn" class="btn btn-primary" value="작성">
				<input type="button" id="cBtn" name="cBtn" class="btn btn-primary" value="취소">
			</td>
		</tr>
		</tbody>
	</table>
	</form>
</div>
</body>
</html>