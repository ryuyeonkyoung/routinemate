<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/mypage.css' />" type="text/css">
<title>할 일 목록</title>
</head>
<body>
    <div class="form-container">
        <h1>할 일 삭제</h1>
        <p>정말로 이 할 일을 삭제하시겠습니까?</p>
        <form action="${pageContext.request.contextPath}/mypage/delete" method="POST">
            <input type="hidden" name="taskId" value="${task.taskId}" />
            <input type="hidden" name="userId" value="${task.userId}" />
            <button type="submit">삭제</button>
            <a href="<c:url value='/mypage/view?userId=${task.userId}' />">취소</a>
        </form>
    </div>
</body>
</html>