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
<title>regUser</title>
<script type="text/javascript">
//주소찾기 팝업
var josoPopup;

$(function()
{
	//아이디 중복체크
	$("#btnUserIdChk").on("click", function()
	{
		if ($("#userId").val() == "")
		{
			alert("아이디를 입력해주세요!");
			$("#userId").focus();
			return false;
		}
		else
		{
			var data = $("#regUserForm").serialize();
			
			$.ajax({
				url : "/ajax/overlapIdCheck",
				type : "post",
				data : data,
				success : function(responseData, statusText, xhr)
				{
					if (responseData.trim() == "true")
					{
						alert("이미 가입되어 있는 아이디입니다!");
						$("#userIdChk").val("N");
						$("#userId").val("");
						return false;
					}
					else
					{
						alert("가입 가능한 아이디 입니다.");
						$("#userIdChk").val("Y");
						$("#idOk").css("visibility", "visible");
					}
				},
				error : function(xhr, statusText, error)
				{
					alert("아이디 중복 체크 실패! 관리자에게 문의하세요.");
					console.log("error : " + statusText + ", " + xhr.status + ", " + error);
				}
			});
		}
	});
	
	//비밀번호 체크
	$("#userPw2").on("keyup", function(e)
	{
		var pw1 = $("#userPw");
		var pw2 = $(this);
		var result = $("#pwChk");
		
		if (pw1.val() != pw2.val())
		{
			result.css("color", "red");
			result.text("비밀번호가 일치하지 않습니다!");
		}
		else
		{
			result.css("color", "blue");
			result.text("비밀번호가 동일합니다.");
		}
	});
	
	//주소찾기
	$("#btnJusoSearch").on("click", function()
	{
		var leftWin = (screen.width - 200) / 2;
		var topWin = (screen.height - 100) / 2;
		
		josoPopup = window.open("/popup/jusoPopup","josoPopup","width=570, height=420, scrollbars=yes, resizable=yes"); 
// 		josoPopup = window.open("/popup/jusoPopup","josoPopup","width=570, height=420, left=" + leftWin + ", top=" + topWin + "scrollbars=yes, resizable=yes"); 
	});
	
	//취소하기
	$("#btnCancel").on("click", function()
	{
		if (josoPopup != null)
		{
			josoPopup.close();
		}
		
		window.close();
	});
	
	//가입
	$("#btnRegUser").on("click", function()
	{
		var id = $("#userId");
		var pw1 = $("#userPw");
		var pw2 = $("#userPw2");
		var name = $("#userNm");
		var email = $("#userEmail");
		var phone = $("#userPhone");
		var birth = $("#userBirth");
		var addr = $("#userAddrFull");
		var idChk = $("#userIdChk");
		
		if (id.val().length == 0)
		{
			alert("아이디가 입력되지 않았습니다!");
			id.focus();
			return false;
		}
		if (pw1.val().length == 0)
		{
			alert("비밀번호가 입력되지 않았습니다!");
			pw1.focus();
			return false;
		}
		if (pw2.val().length == 0)
		{
			alert("비밀번호 확인이 입력되지 않았습니다!");
			id.focus();
			return false;
		}
		if (name.val().length == 0)
		{
			alert("이름이 입력되지 않았습니다!");
			name.focus();
			return false;
		}
		if (email.val().length == 0)
		{
			alert("이메일이 입력되지 않았습니다!");
			email.focus();
			return false;
		}
		if (phone.val().length == 0)
		{
			alert("전화번호가 입력되지 않았습니다!");
			phone.focus();
			return false;
		}
		if (birth.val().length == 0)
		{
			alert("생년월일이 입력되지 않았습니다!");
			birth.focus();
			return false;
		}
		if (addr.val().length == 0)
		{
			alert("주소가 입력되지 않았습니다!");
			addr.focus();
			return false;
		}
		if (pw1.val() != pw2.val())
		{
			alert("비밀번호가 일치하지않습니다.");
			pw2.focus();
			return false;
		}
		if (idChk.val() == 'N')
		{
			alert("아이디 중복확인을 해주세요.");
			idChk.focus();
			return false;
		}
		
		var data = $("#regUserForm").serialize();
		
		$.ajax({
			url : "/ajax/regUserAction",
			type : "post",
			data : data,
			success : function(responseData, statusText, xhr)
			{
				if (responseData.trim() == 1)
				{
					alert("가입이 완료되었습니다.");
					opener.parent.location.reload();
					window.close();
				}
				else
				{
					alert("가입 실패! 관리자에게 문의하세요!");
					return false;
				}
			},
			error : function(xhr, statusText, error)
			{
				alert("가입 실패! 관리자에게 문의하세요.");
				console.log("error : " + statusText + ", " + xhr.status + ", " + error);
			}
		});
	});
});
	
//주소데이터 받기 - 행안부 데이터
function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.regUserForm.userAddrFullRoad.value = roadFullAddr;
// 		document.regUserForm.roadAddrPart1.value = roadAddrPart1;
// 		document.regUserForm.roadAddrPart2.value = roadAddrPart2;
		document.regUserForm.userAddrDetail.value = addrDetail;
// 		document.regUserForm.engAddr.value = engAddr;
		document.regUserForm.userAddrFull.value = jibunAddr;
		document.regUserForm.userZipcode.value = zipNo;
// 		document.regUserForm.admCd.value = admCd;
// 		document.regUserForm.rnMgtSn.value = rnMgtSn;
// 		document.regUserForm.bdMgtSn.value = bdMgtSn;
// 		document.regUserForm.detBdNmList.value = detBdNmList;
		/** 2017년 2월 추가제공 **/
// 		document.regUserForm.bdNm.value = bdNm;
// 		document.regUserForm.bdKdcd.value = bdKdcd;
		document.regUserForm.userAddrSido.value = siNm;
		document.regUserForm.userAddrSigungu.value = sggNm;
		document.regUserForm.userAddrEMD.value = emdNm;
// 		document.regUserForm.liNm.value = liNm;
		document.regUserForm.userAddrRoad.value = rn;
// 		document.regUserForm.udrtYn.value = udrtYn;
		document.regUserForm.userAddrBuldNo.value = buldMnnm + "-" + buldSlno;
// 		document.regUserForm.buldSlno.value = buldSlno;
// 		document.regUserForm.mtYn.value = mtYn;
		document.regUserForm.userAddrJibun.value = lnbrMnnm + "-" + lnbrSlno;
// 		document.regUserForm.lnbrSlno.value = lnbrSlno;
		/** 2017년 3월 추가제공 **/
// 		document.regUserForm.emdNo.value = emdNo;
}
</script>
</head>
<body>
	<form id="regUserForm" name="regUserForm" action="/regUserAction" method="post">
		<input type="hidden" id="userIdChk" name="userIdChk" value="N" />
		<div class="container form-inline">
			<table class="table table-hover">
				<colgroup>
				    <col style="width:20%">
					<col style="width:60%">
					<col style="width:20%">
				</colgroup>
				<tr>
					<th>계정사용여부</th>
					<td>
						<label><input type="radio" id="useYn" class="form-control" name="useYn" value="Y" />사용함</label>
						<label><input type="radio" id="useYn" class="form-control" name="useYn" value="N" />사용안함</label>
					</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td><input type="text" id="userId" class="form-control" name="userId" /></td>
					<td><input type="button" id="btnUserIdChk" class="form-control" name="btnUserIdChk" value="중복체크" /></td>
					<td><span id="idOk" class="glyphicon glyphicon-ok" aria-hidden="false" style="visibility: hidden;"></span></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" id="userPw" class="form-control" name="userPw" /></td>
				</tr>
				<tr>
					<th>비밀번호확인</th>
					<td><input type="password" id="userPw2" class="form-control" name="userPw2" /></td>
					<td><span id="pwChk"></span></td>
				</tr>
				<tr>
					<th>이름<span class="glyphicons glyphicons-chevron-down"></span></th>
					<td><input type="text" id="userNm" class="form-control" name="userNm" /></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" id="userEmail" class="form-control" name="userEmail" /></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="tel" id="userPhone" class="form-control" name="userPhone" /></td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
						<select id="userGender" class="form-control" name="userGender">
							<option value="M">남자</option>
							<option value="F">여자</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td>
						<select id="userSL" class="form-control" name="userSL">
							<option value="S">양력</option>
							<option value="L">음력</option>
						</select>
					</td>
					<td><input type="date" id="userBirth" class="form-control" name="userBirth" /></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" id="userAddrFull" class="form-control" name="userAddrFull" readonly /></td>
					<td><input type="button" id="btnJusoSearch" class="form-control" name="btnJusoSearch" value="주소검색" /></td>
				</tr>
				<tr>
					<th>상세주소</th>
					<td><input type="text" id="userAddrDetail" class="form-control" name="userAddrDetail" /></td>
				</tr>
			</table>
			<input type="hidden" id="userAddrFullRoad" name="userAddrFullRoad" />
			<input type="hidden" id="userAddrSido" name="userAddrSido" />
			<input type="hidden" id="userAddrSigungu" name="userAddrSigungu" />
			<input type="hidden" id="userAddrEMD" name="userAddrEMD" />
			<input type="hidden" id="userAddrJibun" name="userAddrJibun" />
			<input type="hidden" id="userAddrRoad" name="userAddrRoad" />
			<input type="hidden" id="userAddrBuldNo" name="userAddrBuldNo" />
			<input type="hidden" id="userZipcode" name="userZipcode" />
		</div>
		<div style="float: left;"><input type="button" id="btnRegUser" name="btnRegUser" class="btn btn-primary form-control" value="등록하기" /></div>
		<div style="float: left;"><input type="button" id="btnCancel" name="btnCancel" class="btn btn-primary form-control" value="취소하기" /></div>
	</form>
</body>
</html>