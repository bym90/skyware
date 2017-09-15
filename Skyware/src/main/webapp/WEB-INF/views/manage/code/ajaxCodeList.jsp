<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table class="table table-hover">
	<colgroup>
	    <col style="width:3%">
		<col style="width:5%">
		<col style="width:10%">
		<col style="width:10%">
		<col style="width:10%">
		<col style="width:20%">
		<col style="width:10%">
		<col style="width:*">
		<col style="width:5%">
<%-- 		<col style="width:5%"> --%>
		<col style="width:5%">
	</colgroup>
	<tr>
		<th>NO</th>
		<th>코드ID</th>
		<th>코드명</th>
		<th>네이밍코드ID</th>
		<th>네이밍코드명</th>
		<th>코드설명</th>
		<th>코드URL</th>
		<th>코드등록일</th>
		<th>사용여부</th>
<!-- 		<th>수정</th> -->
		<th>삭제</th>
	</tr>
	<c:forEach var="code" items="${codeList}">
	<tr>
		<td>${code.codeSort}</td>
		<td class="codeId">${code.codeId}</td>
		<td>${code.codeNm}</td>
		<td>${code.codeDiv}</td>
		<td>${code.codeDivNm}</td>
		<td>${code.codeDescription}</td>
		<td>${code.codeUrl}</td>
		<td><fmt:formatDate value="${code.codeRegDate}" pattern="yyyy-MM-dd a hh:mm:ss" /></td>
		<td>${code.useYn}
<!-- 			<select> -->
<%-- 				<option value="Y" ${code.useYn eq 'Y' ? 'selected' : '' }>사용</option> --%>
<%-- 				<option value="N" ${code.useYn eq 'N' ? 'selected' : '' }>사용안함</option> --%>
<!-- 			</select> -->
		</td>
<%-- 		<td><input class="btnCodeUpt form-control" type="button" name="btnCodeUpt" value="수정" onclick="codeUpt('${code.codeId}');" /></td> --%>
		<td><input class="btnCodeDel form-control" type="button" name="btnCodeDel" value="삭제" onclick="codeDel('${code.codeId}');" /></td>
	</tr>
	</c:forEach>
</table>