<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사용자 관리</title>
<script>
$(function()
{
	//검색
	$("#schBtn").on("click", function()
	{
		var cPage = $("#cPage").val();
		paging(cPage);
	});
	
	//회원추가
	$("#btnUserReg").on("click", function()
	{
		var pop = window.open("/popup/regUserManageView", "regUserPopup", "width=800, height=650, scrollbars=yes, resizable=yes"); 
	});
	
	$("#schBtn").trigger("click");
});

//회원상세
function userDetailView(userId)
{
	var pop = window.open("/popup/regUserDetailView?userId=" + userId, "userDetailPopup", "width=800, height=650, scrollbars=yes, resizable=yes")
}

function paging(cPage)
{
// 	var cPage = $("#cPage").val();
	var schState = $("#schState").val();
	var schType = $("#schType").val();
	var schText = $("#schText").val();
// 	var data = $("#userInfoListForm").serialize();
	
	$.ajax({
		url : "/manage/user/userManageList",
		type : "post",
		data : {
			curPage : cPage,
			schState : schState,
			schType : schType,
			schText : schText
		},
// 		data : data,
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
</script>
</head>
<body>
	<div class="container" id="container">
		<form id="userInfoListForm" name="userInfoListForm">
			<input type="hidden" id="cPage" name="cPage" value="${cPage}">
<%-- 			<input type="hidden" id="curPage" name="curPage" value="${cPage}" /> --%>
<%-- 			<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" /> --%>
			<!-- Contents -->
			<div id="Contents" class="container form-inline">
				<h4 class="bor_solid_bottom pd_b10"><span>사용자 관리</span><input type="button" id="btnUserReg" class="btn btn-primary" value="회원추가" /></h4>
				<div class="registerForm_wrap bg_gray">
					<div class="search_wrap">
						<div class="w100p">
							<select name="schState" id="schState" class="form-control">
								<option value="" selected>회원상태(선택)</option>
								<c:forEach var="obj" items="${selectComboList1}">
									<option value="${obj.codeId}">${obj.codeNm}</option>
								</c:forEach>
							</select>
							<select name="schType" id="schType" class="form-control">
								<option value="" selected>선택</option>
								<c:forEach var="obj" items="${selectComboList2}">
									<option value="${obj.codeId}">${obj.codeNm}</option>
								</c:forEach>
							</select>
							<input type="text" name="schText" id="schText" value="${schInput}" class="form-control" onkeypress="if(event.keyCode==13) {goSearch(); return false;}">
							<button id="schBtn" name="schBtn" type="button" class="btn btn-primary">검색</button>
						</div>
					</div>
				</div>
			</div>
			<div id="userList" class="container"></div>
		</form>
	</div>
</body>
</html>