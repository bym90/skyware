<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-hover">
	<colgroup>
	    <col style="width:50%">
		<col style="width:50%">
	</colgroup>
	<tr>
		<th>아이디</th>
		<th>이름</th>
	</tr>
	<c:choose>
		<c:when test="${not empty userList}">
			<c:forEach var="user" items="${userList}">
				<tr onclick="regAuth('${user.userId}', '${user.userNm}');" style="cursor: pointer;">
		<!-- 						<td><input type="text" id="userId" class="form-control" name="userId" /></td> -->
		<!-- 						<td><input type="text" id="userNm" class="form-control" name="userNm" /></td> -->
					<td>${user.userId}</td>
					<td>${user.userNm}</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr><td colspan="2">사용자가 없습니다.</td></tr>
		</c:otherwise>
	</c:choose>
</table>