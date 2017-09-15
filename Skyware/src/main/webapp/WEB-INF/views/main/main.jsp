<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="/resources/images/common/skyware.ico" />
<link rel="stylesheet" type="text/css" href="/resources/css/common/leftMenu.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/common/topMenu.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/common/footer.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/user/user.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script type="text/javascript" src="/resources/js/jquery/jquery-1.11.3.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<title><decorator:title default="Skyware" /></title>
<decorator:head />
</head>
<body>
	<div id="wrapper" style="text-align: center;">
        <div id="header">
            <c:import url="/header" />
        </div>
        <%-- <div id="leftMenu">
            <c:import url="/leftMenu" />
        </div> --%>
        <div id="content" >
            <decorator:body></decorator:body>
        </div>
        <div style="clear: both;"></div>
        <div id="footer">
            <c:import url="/footer" />
        </div>
    </div>
</body>
</html>