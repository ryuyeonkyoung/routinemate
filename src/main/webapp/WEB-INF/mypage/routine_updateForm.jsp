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
        <h1>할 일 수정</h1>
        <form action="<c:url value='/mypage/update' />" method="post">
    <input type="hidden" name="taskId" value="${task.taskId}" />
    <label for="description">할 일 설명:</label>
    <input type="text" id="description" name="description" value="${task.description}" />

    <label for="order">순서:</label>
    <input type="number" id="order" name="order" value="${task.order}" />

    <%-- <label for="isCompleted">완료 여부:</label>
    <input type="checkbox" id="isCompleted" name="isCompleted" value="true" 
           <c:if test="${task.completed}">checked</c:if> /> --%>

    <button type="submit">수정하기</button>
</form>
    </div>
</body>
</html>