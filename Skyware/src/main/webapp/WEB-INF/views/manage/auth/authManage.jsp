<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>권한관리</title>
<script type="text/javascript">
$(function()
{
	//등록
	$("#btnAuthReg").on("click", function()
	{
		var pop = window.open("/popup/regAuthSchView", "regAuthSchPopup", "width=500, height=300, scrollbars=yes, resizable=yes")
	});
	
	//수정
	$(".btnAuthUpt").on("click", function()
	{
		var userId = $(this).parent().parent().find("[class='userId']").text();
		var permissionType = $(this).parent().parent().find("[name='permissionType']").val();
		var userMntYn = ($(this).parent().siblings().find("[name='userMntYn']").prop("checked") == true)? 'Y' : 'N';
		var codeMntYn = ($(this).parent().siblings().find("[name='codeMntYn']").prop("checked") == true)? 'Y' : 'N';
		var boardMntYn = ($(this).parent().siblings().find("[name='boardMntYn']").prop("checked") == true)? 'Y' : 'N';
		var calenderMntYn = ($(this).parent().siblings().find("[name='calenderMntYn']").prop("checked") == true)? 'Y' : 'N';
		
		if (confirm("권한을 수정 하시겠습니까?"))
		{
			$.ajax({
				url : "/manage/auth/authUptAction",
				type : "post",
				data : {
					userId : userId,
					permissionType : permissionType,
					userMntYn : userMntYn,
					codeMntYn : codeMntYn,
					boardMntYn : boardMntYn,
					calenderMntYn : calenderMntYn
				},
				success : function(responseData, statusText, xhr)
				{
					if (responseData.trim() == 1)
					{
						alert("권한 수정이 완료되었습니다.");
						location.reload();
					}
					else
					{
						alert("권한 수정 실패. 관리자에게 문의하세요!");
					}
				},
				error : function(xhr, statusText, error)
				{
					alert("권한 수정 실패. 관리자에게 문의하세요.");
					console.log("error : " + statusText + ", " + xhr.status + ", " + error);
				}
			});
		}
	});
	
	//삭제
	$(".btnAuthDel").on("click", function()
	{
		var userId = $(this).parent().parent().find("[class='userId']").text();
		
		if (confirm("권한을 삭제 하시겠습니까?"))
		{
			$.ajax({
				url : "/manage/auth/authDelAction",
				type : "post",
				data : {userId : userId},
				success : function(responseData, statusText, xhr)
				{
					if (responseData.trim() == 1)
					{
						alert("권한 삭제가 완료되었습니다.");
						location.reload();
					}
					else
					{
						alert("권한 삭제 실패. 관리자에게 문의하세요!");
					}
				},
				error : function(xhr, statusText, error)
				{
					alert("권한 삭제 실패. 관리자에게 문의하세요.");
					console.log("error : " + statusText + ", " + xhr.status + ", " + error);
				}
			});
		}
	});
});

function permissionMod(obj)
{
	var tempObj = $(obj).parent().parent();
	var mst = tempObj.find("[name='permissionType']").val();
	
	// 권한타입이 "M" 일 경우 각 항목 별 Y/N 상관없이 모든 권한을 가져야 함
	// 권한타입이 "M" 일 경우 모두 체크하고 수정 불가로 변경 
	if(mst == "M")
	{
		tempObj.find("[name='userMntYn']").prop("checked",true);
		tempObj.find("[name='userMntYn']").prop("disabled",true);
		
		tempObj.find("[name='codeMntYn']").prop("checked",true);
		tempObj.find("[name='codeMntYn']").prop("disabled",true);
		
		tempObj.find("[name='boardMntYn']").prop("checked",true);
		tempObj.find("[name='boardMntYn']").prop("disabled",true);
		
		tempObj.find("[name='calenderMntYn']").prop("checked",true);
		tempObj.find("[name='calenderMntYn']").prop("disabled",true);
	}
	// 권한타입이 "A" 일 체크해제 하고 수정가능하게 변경
	else if(mst == "A")
	{
		tempObj.find("[name='userMntYn']").prop("checked",false);
		tempObj.find("[name='userMntYn']").prop("disabled",false);
		
		tempObj.find("[name='codeMntYn']").prop("checked",false);
		tempObj.find("[name='codeMntYn']").prop("disabled",false);
		
		tempObj.find("[name='boardMntYn']").prop("checked",false);
		tempObj.find("[name='boardMntYn']").prop("disabled",false);
		
		tempObj.find("[name='calenderMntYn']").prop("checked",false);
		tempObj.find("[name='calenderMntYn']").prop("disabled",false);
	}
	else
	{
		alert("실패!");
	}
}
</script>
</head>
<body>
	<div class="container form-inline">
		<h4 class="bor_solid_bottom pd_b10"><span>권한설정</span><input type="button" id="btnAuthReg" class="btn btn-primary" value="관리자 추가" /></h4>
		<h4 class="bor_b"><span class="ab_l">권한설정 목록</span></h4>
		<table id="adminTable" class="table table-hover">
			<thead>
				<tr>
					<th width="8%">아이디</th>
					<th width="8%">이름</th>
					<th width="10%">Master</th>
					<th width="12%">사용자관리</th>
					<th width="12%">코드관리</th>
					<th width="12%">게시판관리</th>
					<th width="12%">일정관리</th>
					<th width="16%"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${adminList}" var="admin">
					<tr>
						<td class="userId">${admin.userId}</td>
						<td class="userNm">${admin.userNm}</td>
						<c:choose>
							<c:when test="${admin.permissionType eq 'M'}">
								<td>
									<select name="permissionType" class="form-control" onChange="permissionMod(this)">
										<option value="M" selected>M</option>
										<option value="A" >A</option>
									</select>
								</td>
								<td><input type="checkbox" name="userMntYn" value="Y" class="form-control" checked disabled /></td>
								<td><input type="checkbox" name="codeMntYn" value="Y" class="form-control" checked disabled /></td>
								<td><input type="checkbox" name="boardMntYn" value="Y" class="form-control" checked disabled /></td>
								<td><input type="checkbox" name="calenderMntYn" value="Y" class="form-control" checked disabled /></td>
							</c:when>
							<c:otherwise>
								<td>
									<select name="permissionType" class="form-control" onChange="permissionMod(this)">
										<option value="M">M</option>
										<option value="A" selected>A</option>
									</select>
								</td>
								<c:choose>
									<c:when test="${admin.userMntYn eq 'Y'}">
										<td><input type="checkbox" name="userMntYn" value="Y" class="form-control" checked /></td>
									</c:when>
									<c:otherwise>
										<td><input type="checkbox" name="userMntYn" value="N" class="form-control" /></td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${admin.codeMntYn eq 'Y'}">
										<td><input type="checkbox" name="codeMntYn" value="Y" class="form-control" checked /></td>
									</c:when>
									<c:otherwise>
										<td><input type="checkbox" name="codeMntYn" value="N" class="form-control" /></td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${admin.boardMntYn eq 'Y'}">
										<td><input type="checkbox" name="boardMntYn" value="Y" class="form-control" checked /></td>
									</c:when>
									<c:otherwise>
										<td><input type="checkbox" name="boardMntYn" value="N" class="form-control" /></td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${admin.calenderMntYn eq 'Y'}">
										<td><input type="checkbox" name="calenderMntYn" value="Y" class="form-control" checked /></td>
									</c:when>
									<c:otherwise>
										<td><input type="checkbox" name="calenderMntYn" value="N" class="form-control" /></td>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
						<td>
							<input class="btnAuthUpt form-control" type="button" value="수정" />&nbsp;
							<input class="btnAuthDel form-control" type="button" value="삭제" />
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<nav>
			<ul class="pagination">
				<li>
					<c:if test="${curPage eq 1}">
						<a href="javascript://" aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a>
					</c:if> 
					<c:if test="${curPage ne 1}">
						<a href="javascript:paging(${curPage - 1});" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
						</a>
					</c:if>
				</li>
				<c:forEach var="page" begin="${sPage}" end="${ePage}">
					<c:choose>
						<c:when test="${curPage eq page}">
							<li><a href="javascript://">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="javascript:paging(${page})">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<li>
					<c:if test="${ePage ne curPage}">
						<a href="javascript:paging(${curPage + 1})" aria-label="Next"> 
						<span aria-hidden="true">&raquo;</span>
						</a>
					</c:if> <c:if test="${ePage eq curPage}">
						<a href="javascript://" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a>
					</c:if>
				</li>
			</ul>
		</nav>
	</div>
</body>
</html>