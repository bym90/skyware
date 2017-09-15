<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form method="POST" action="" id="lfrm">
	<table class="table table-hover">
		<thead>
			<tr>
				<th width="10%">No.</th>
				<th width="70%">제목</th>
				<th width="10%">작성자</th>
				<th width="10%">작성날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${noticeList}" var="list">
				<tr id="list" onclick="noticeView(${list.noticeNo})" align="left">
					<td>${list.noticeNo}</td>
					<td>${list.noticeTitle}</td>
					<td>${list.noticeWriter}</td>
					<td>${list.noticeDate}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4" align="right"><input type="button" id="wBtn"
					name="wBtn" value="글쓰기" class="btn btn-primary"></td>
			</tr>
		</tfoot>
	</table>
	<nav>
		<ul class="pagination">
			<li><c:if test="${PINFO.startPage eq 1}">
					<a href="#" aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a>
				</c:if> <c:if test="${PINFO.startPage ne 1}">
					<a href="javascript:paging(${PINFO.startPage - 1});"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a>
				</c:if></li>
			<c:forEach var="page" begin="${PINFO.startPage}"
				end="${PINFO.endPage}">
				<li><a href="javascript:paging(${page})">${page}</a></li>
			</c:forEach>
			<li><c:if test="${PINFO.endPage ne PINFO.totalPage}">
					<a href="javascript:paging(${PINFO.endPage + 1})" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</c:if> <c:if test="${PINFO.endPage eq PINFO.totalPage}">
					<a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a>
				</c:if></li>
		</ul>
	</nav>

</form>