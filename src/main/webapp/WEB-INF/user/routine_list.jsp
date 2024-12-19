<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/mypage.css' />" type="text/css">

    <title>할 일 목록</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>할 일 목록</h1>
    
    <!-- 에러 메시지 출력 -->
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <!-- 할 일 목록이 비어 있는 경우 -->
    <c:if test="${empty tasks}">
        <p>현재 등록된 할 일이 없습니다.</p>
    </c:if>
    
    <p>${sessionScope.username}</p>
	<p>현재 사용자 ID: ${userId}</p>
    <!-- 할 일 목록 테이블 -->
    <table>
        <thead>
            <tr>
                <th>번호</th>
                <th>설명</th>
                <th>완료 여부</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="task" items="${tasks}">
                <tr>
                    <td>${task.taskId}</td>
                    <td>${task.description}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>