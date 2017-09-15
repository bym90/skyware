<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script type="text/javascript" src="/resources/js/jquery/jquery-1.11.3.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<title>사용자 찾기</title>
<script type="text/javascript">
$(function()
{
	//엔터키
	$("#searchId").on("keyup", function(e)
	{
		if (e.keyCode == 13)
		{
			goSearchUser();
		}
	});
	
	//검색
	$("#btnUserSearch").on("click", function()
	{
		goSearchUser();
	});
	
	//취소
	$("#btnCancel").on("click", function()
	{
		window.close();
	});
});

//검색
function goSearchUser()
{
	var searchId = $("#searchId").val();
	
	if(searchId == "")
	{
		alert("검색어를 입력해주세요.");
		return false;
	}
	
	$.ajax({
		url : "/manage/auth/regAuthSchAction",
		type : "post",
		data : {userId : searchId},
		success : function(responseData, statusText, xhr)
		{
			$("#userList").html(responseData);
		},
		error : function(xhr, statusText, error)
		{
			alert("사용자 조회 실패. 관리자에게 문의하세요.");
			console.log("error : " + statusText + ", " + xhr.status + ", " + error);
		}
	});
}

//등록
function regAuth(userId, userNm)
{
	if (confirm("권한을 추가 하시겠습니까?"))
	{
		$.ajax({
			url : "/manage/auth/authRegAction",
			type : "post",
			data : {
				userId : userId,
				userNm : userNm
			},
			success : function(responseData, statusText, xhr)
			{
				if (responseData.trim() == 1)
				{
					alert("권한설정이 완료되었습니다.");
					opener.parent.location.reload();
					window.close();
				}
				else
				{
					alert("권한 설정 실패. 관리자에게 문의하세요!");
				}
			},
			error : function(xhr, statusText, error)
			{
				alert("권한 설정 실패. 관리자에게 문의하세요.");
				console.log("error : " + statusText + ", " + xhr.status + ", " + error);
			}
		});
	}
}
</script>
</head>
<body>
<!-- 	<form id="regAuthForm" name="regAuthForm" action="/regAuthAction" method="post"> -->
		<div class="container form-inline">
			<table class="table table-hover">
				<colgroup>
				    <col style="width:80%">
					<col style="width:20%">
				</colgroup>
				<tbody>
					<tr>
						<td><input type="text" id="searchId" class="form-control" placeholder="ID 또는 이름을 입력해주세요." /></td>
						<td><input type="button" id="btnUserSearch" class="form-control" value="검색" /></td>
					</tr>
				</tbody>
			</table>
			<div id="userList"></div>
		</div>
		<div style="text-align: center;"><input type="button" id="btnCancel" name="btnCancel" class="btn btn-primary form-control" value="취소하기" /></div>
<!-- 	</form> -->
</body>
</html>