<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member list</title>
</head>
<body>
	<h1>회원</h1>
	<c:forEach var="member" items="${members }">
	아이디 : ${member.id }<br>
	비밀번호 : ${member.pwd }<br>
	이름 : ${member.name }<br>
	이메일 : ${member.email }<br>
	가입일 : ${member.joinDate }<br><br>
	</c:forEach>
</body>
</html>