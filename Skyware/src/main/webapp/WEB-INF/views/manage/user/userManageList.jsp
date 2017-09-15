<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<div class="container form-inline">
	<h4 class="bor_b"><span class="ab_l">사용자 목록</span></h4>
	<table id="userTable" class="table table-hover">
		<thead>
			<tr>
				<th width="5%">No</th>
				<th width="10%">아이디</th>
				<th width="10%">이름</th>
				<th width="10%">연락처</th>
				<th width="35%">주소</th>
				<th width="20%">가입일</th>
				<th width="8%">사용여부</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="user">
				<tr id="userDetail" onclick="userDetailView('${user.userId}')">
					<td>${user.userNo}</td>
					<td>${user.userId}</td>
					<td>${user.userNm}</td>
					<td>${user.userPhone}</td>
					<td>${user.userAddrFullRoad}</td>
					<td><fmt:formatDate value="${user.userRegDate}" pattern="yyyy-MM-dd a hh:mm:ss" /></td>
					<td>${user.useYn}</td>
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