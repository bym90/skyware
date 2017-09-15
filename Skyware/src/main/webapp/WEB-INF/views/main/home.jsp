<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Skyware</title>
<link rel="shortcut icon" href="/resources/images/common/skyware.ico" />
<link rel="stylesheet" type="text/css" href="/resources/css/common/home.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
$(function() {
	var msg = '<%=request.getParameter("result") %>';
	var chk = '<%=request.getParameter("chk") %>';
	
	if (chk == "true")
	{
		alert("세션이 종료되었습니다. 다시 로그인 해 주세요.");
	}
	
	if (msg != "")
	{
		if (msg == 0)
		{
			alert("아이디를 찾을 수 없습니다.");
			$("#userId").focus();
		}
		else if (msg == 1)
		{
			alert("비밀번호를 잘 못 입력하셨습니다.");
			$("#userPw").focus();
		}
		else if (msg == 2)
		{
			alert("회원승인이 되지않았습니다.\n관리자에게 문의하세요.");
			$("#userId").focus();
		}
	}
	
	$("#regUserView").on("click", function()
	{
		var pop = window.open("/popup/regUserView","regUserPopup","width=600,height=600, scrollbars=yes, resizable=yes"); 
	});
});
</script>
</head>
<body>
	<form id="loginForm" name="loginForm" action="/LoginAction" method="post">
	<div id="home" style="text-align: center;">
		<h1><img src="/resources/images/common/skyware_logo.gif" alt="Skyware" /></h1>
		<div id="login">
			<ul>
				<li>
					<label><input type="checkbox" id="saveId" name="saveId" value="save" ${saveId != null ? 'checked' : ''} /> 아이디 저장</label>
				</li>
			</ul>
			<div>
				<input type="text" id="userId" class="form-control" name="userId" placeholder="아이디" value="${saveId}" />
				<input type="password" id="userPw" class="form-control" name="userPw" placeholder="비밀번호" /><br>
				<p><input type="submit" id="btnLogin" name="btnLogin" class="btn btn-primary form-control" value="로그인" /></p>
			</div>
			<div><a id="regUserView" href="javascript:">아이디 등록 신청</a></div>
		</div>
    </div>
    </form>
</body>
</html>