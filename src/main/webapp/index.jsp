<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Cotent-Type" content="text/html; charset=UTF-8">
<title>routinemate</title>
</head>
<body>
	<script>
		location.href = 'login.jsp';
	</script>
</body>
</html> --%>

<% response.sendRedirect(request.getContextPath() + "/user/login/form"); %>
