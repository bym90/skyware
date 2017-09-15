<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/resources/css/common/error.css" />
</head>
<body>
<table id="errTable">
	<tr>
		<td valign="middle" align="center">
			<img src="/resources/images/common/conts_notfound.gif" usemap="#errorMap" border="0">
		</td>
	</tr>
</table>
<map id="errorMap" name="errorMap">
	<area shape="rect" coords="162, 198, 248, 225" href="/">
	<area shape="rect" coords="260, 198, 346, 225" href="javascript:history.back();">
</map>
</body>
</html>