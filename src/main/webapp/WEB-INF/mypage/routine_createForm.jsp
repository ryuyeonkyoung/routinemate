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
        <h1>할 일 추가</h1>
        <form action="<c:url value='/mypage/create' />" method="POST">
            <input type="hidden" name="userId" value="${userId}" />

            <label for="description">설명</label>
            <input type="text" id="description" name="description" required />

            <label for="order">순서</label>
            <input type="number" id="order" name="order" required />

            <label for="isCompleted">완료 여부</label>
            <select id="isCompleted" name="isCompleted">
                <option value="false">진행 중</option>
                <option value="true">완료됨</option>
            </select>

            <button type="submit">추가</button>
        </form>
    </div>
</body>
</html>