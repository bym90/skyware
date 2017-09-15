<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>testList</title>
</head>
<body>
	<table>
		<tr>
			<th>ID</th>
			<th>Pass</th>
			<th>Name</th>
			<th>Phone</th>
		</tr>
		<c:forEach items="${testList}" var="test">
			<tr>
				<td>${test.id}</td>
				<td>${test.pass}</td>
				<td>${test.name}</td>
				<td>${test.phone}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>