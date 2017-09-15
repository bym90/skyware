<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script type="text/javascript"
	src="/resources/js/jquery/jquery-1.11.3.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<title>베이스코드 등록</title>
<script type="text/javascript">
$(function()
{
	//등록
	$("#btnRegBaseCode").on("click", function()
	{
		baseCodeDuplChk();
	});

	//취소
	$("#btnCancel").on("click", function()
	{
		window.close();
	});
});

function baseCodeDuplChk()
{
	var codeId = $("#codeId").val();
	
	$.ajax({
		type : "post",
		url : "/manage/code/codeDuplCheck",
		data : {
			codeId : codeId
		},
		success : function(responseData, statusText, xhr)
		{
// 			$("#userList").html(responseData);
			if (responseData.trim() == "true")
			{
				alert(codeId + "는 중복되는 코드입니다.");
				return false;
			}
			else
			{
				baseCodeInsert();
			}
		},
		error : function(xhr, statusText, error)
		{
			alert("코드중복 조회 실패. 관리자에게 문의하세요.");
			console.log("error : " + statusText + ", " + xhr.status + ", " + error);
		}
	});
}

function baseCodeInsert()
{
	if(confirm("등록하시겠습니까?"))
	{ 	
		var codeId = $("#codeId").val();
		var codeNm = $("#codeNm").val();
		var codeDescription = $("#codeDescription").val();
		var codeSort = $("#codeSort").val();
		var useYn = $("#useYn").val();
		
     	$.ajax({
			type : "post"
			, url : "/manage/code/basecodeRegAction"
			, data : {codeId : codeId,
					  codeNm : codeNm,
					  codeDescription : codeDescription,
					  codeSort : codeSort,
					  useYn : useYn
			}
			, success : function(responseData, statusText, xhr) {
				if (responseData.trim() == 1)
				{
					alert("코드 등록을 완료했습니다");
					opener.parent.location.reload();
					window.close();
				}
				else
				{
					alert("코드 등록 실패. 관리자에게 문의하세요.");
				}
			}
			, error : function(xhr, statusText, error) {
				alert("코드 등록 실패. 관리자에게 문의하세요!");
				console.log("error : " + statusText + ", " + xhr.status + ", " + error);
			}
		}); 
	}
}
</script>
</head>
<body>
	<form id="regBaseCodeForm" name="regBaseCodeForm" action="/manage/code/basecodeRegAction" method="post">
		<div class="container form-inline">
			<table class="table table-hover">
				<colgroup>
					<col style="width: 20%">
					<col style="width: 60%">
					<col style="width: 20%">
				</colgroup>
				<tr>
					<th>코드ID</th>
					<td><input type="text" id="codeId" class="form-control" name="codeId" /></td>
				</tr>
				<tr>
					<th>코드명</th>
					<td><input type="text" id="codeNm" class="form-control" name="codeNm" /></td>
				</tr>
				<tr>
					<th>설명</th>
					<td><input type="text" id="codeDescription" class="form-control" name="codeDescription" /></td>
				</tr>
				<tr>
					<th>정렬값</th>
					<td><input type="number" id="codeSort" class="form-control" name="codeSort" /></td>
				</tr>
				<tr>
					<th>사용유무</th>
					<td>
						<select id="useYn" class="form-control" name="useYn">
							<option value="Y">사용</option>
							<option value="N">사용안함</option>
						</select>
					</td>
				</tr>
			</table>
		</div>
		<div style="float: left;"><input type="button" id="btnRegBaseCode" name="btnRegBaseCode" class="btn btn-primary form-control" value="등록하기" /></div>
		<div style="float: left;"><input type="button" id="btnCancel" name="btnCancel" class="btn btn-primary form-control" value="취소하기" /></div>
	</form>
</body>
</html>