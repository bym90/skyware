<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>코드관리</title>
<script type="text/javascript">
$(function() {
	//베이스코드 등록
	$("#btnBaseCodeReg").on("click", function(){
		var pop = window.open("/popup/basecodeRegView", "regBaseCodePopup", "width=600, height=600, scrollbars=yes, resizable=yes"); 
	});
	
	//네이밍코드 등록
	$("#btnCodeReg").on("click", function()
	{
		var codeDiv = $("#baseCodeDiv").val();
		var codeDivNm = $("#baseCodeDiv  option:selected").text();
		var codeNm = $("#codeNm").val();
		var useYn = $("#useYn").val();
		
		if(codeDiv == "")
		{
			alert("네이밍코드를 선택하세요");
			return false;
		}
		if(codeNm == "")
		{
			alert("코드명을 입력하세요");
			$("#codeNm").focus();
			return false;
		}	
		
	    $.ajax({
			type : "post",
			url : "/manage/code/codeRegAction",
			data : {
				codeDiv : codeDiv,
				codeDivNm : codeDivNm,
				codeNm : codeNm,
				useYn : useYn
			},
			success :  function(responseData, statusText, xhr) {
				if (responseData.trim() == "1")
				{
					alert("등록에 성공했습니다");
					location.reload();
				}
				else
				{
					alert("코드등록 실패! 관리자에게 문의하세요.");
					console.log("error : " + statusText + ", " + xhr.status);
				}
			},
			error : function(xhr, statusText, error)
			{
				alert("코드등록 실패! 관리자에게 문의하세요.");
				console.log("error : " + statusText + ", " + xhr.status + ", " + error);
			}
		}); 		
	});
	
	
	//
	$("#codeDiv2").on("change", function()
	{
		var codeDiv = $(this).val();
		
		//네이밍 코드 select box 동일하게
		$("#baseCodeDiv").val(codeDiv);
		
		$.ajax({
			url : "/manage/code/codeList",
			type : "post",
			data : {codeDiv : codeDiv},
			success : function(responseData, statusText, xhr)
			{
//  	 			$("#clientSearch").val("");
				$("#codeList").html(responseData);
			},
			error : function(xhr, statusText, error)
			{
				alert("코드조회 실패! 관리자에게 문의하세요.");
				console.log("error : " + statusText + ", " + xhr.status + ", " + error);
			}
		});
	});
	
	$("#codeDiv2").trigger("change");
});

//수정은 보류 굳이 수정 기능이 있을 필요가 없음 코드 수정시 위험가능성
function codeUpt(codeId)
{
	alert("Upt : " + codeId);
}

//삭제
function codeDel(codeId)
{
	if (confirm(codeId + " 코드를 삭제하시겠습니까?"))
	{
		$.ajax({
			url : "/manage/code/codeDelAction",
			type : "post",
			data : {codeId : codeId},
			success : function(responseData, statusText, xhr)
			{
				if (responseData.trim() == 1)
				{
					alert("코드 삭제가 완료되었습니다.");
					location.reload();
				}
				else
				{
					alert("코드 삭제 실패! 관리자에게 문의하세요!");
				}
			},
			error : function(xhr, statusText, error)
			{
				alert("코드 삭제 실패! 관리자에게 문의하세요.");
				console.log("error : " + statusText + ", " + xhr.status + ", " + error);
			}
		});
	}
}
</script>
</head>
<body>
	<div id="codeReg">
		<h4><label for="inputdefault">공통코드관리</label></h4>
		<input type="button" id="btnBaseCodeReg" class="btn btn-primary" name="btnBaseCodeReg" value="Base 코드등록" />
		<form id="codeRegForm" name="codeRegForm" action="/manage/code/codeRegAction" method="post">
			<div class="container form-inline">
				<table class="table table-hover">
					<tr>
						<td>코드명 : <input id="codeNm" class="form-control" name="codeNm" type="text" /></td>
						<td>사용여부 : 
							<select id="useYn" class="form-control" name=useYn>
<%-- 								<c:forEach var="combo1" items="${useCodeList}" varStatus="status"> --%>
<%-- 									<option value="${combo1.codeId}">${combo1.codeNm}</option> --%>
<%-- 								</c:forEach> --%>
								<option value="Y">사용</option>
								<option value="N">사용안함</option>
							</select>
						</td>
						<td>네이밍코드명 : 
							<select id="baseCodeDiv" class="form-control" name="baseCodeDiv">
								<c:forEach var="combo2" items="${baseCodeList}" varStatus="status">
									<option value="${combo2.codeId}">${combo2.codeNm}</option>
								</c:forEach>
							</select>
						</td>
						<td><input id="btnCodeReg" class="form-control" type="button" value="저장" /></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<div>
		<h4><label for="inputdefault">네이밍 관리목록</label></h4>
		<div class="container form-inline">
			<select id="codeDiv2" class="form-control" name="codeDiv2">
<!-- 				<option value="">선택</option> -->
				<c:forEach var="combo3" items="${baseCodeList}" varStatus="status">
					<option value="${combo3.codeId}">${combo3.codeNm}</option>
				</c:forEach>
			</select>
		</div>
		<div id="codeList" class="container"></div>
	</div>
</body>
</html>