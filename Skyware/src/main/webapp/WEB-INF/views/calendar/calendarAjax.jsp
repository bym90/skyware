<%@ page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
       
     
     	{"calendarList" : [
     		<c:forEach var = "cal" items = "${calendarList}" varStatus ="st">
     			<c:if test ="${not st.last}" >
     				{"title" : "${cal.schTitle}", "start" : "${cal.schStartDate}", "end" : "${cal.schEndDate}"},
     			</c:if>	
     			<c:if test = "${st.last}" >
     				{"title" : "${cal.schTitle}", "start" : "${cal.schStartDate}", "end" : "${cal.schEndDate}"}
     			</c:if>
     		</c:forEach>
        ]}