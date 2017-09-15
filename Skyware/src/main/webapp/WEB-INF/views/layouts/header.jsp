<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
$(function()
{
	$("#btnLogout").on("click", function()
	{
		$(location).attr("href", "/LogoutAction");
	});
});
</script>
<h1><img src="/resources/images/common/skyware_logo.gif" /><input type="button" id="btnLogout" class="btn btn-primary" name="btnLogout" value="로그아웃" /></h1>
<div id="topMenu">
	<ul>
		<c:forEach items="${codeList}" var="code">
			<li><a href="${code.codeUrl}">${code.codeNm}</a></li>
		</c:forEach>
	</ul>
</div>