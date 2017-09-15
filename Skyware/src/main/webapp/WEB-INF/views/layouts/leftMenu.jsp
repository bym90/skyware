<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="leftMenu">
	<ul>
		<c:forEach items="${codeList}" var="code">
			<li><a href="${code.codeUrl}">${code.codeNm}</a></li>
		</c:forEach>
	</ul>
</div>