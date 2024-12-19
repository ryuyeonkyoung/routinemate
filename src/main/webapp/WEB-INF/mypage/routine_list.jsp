<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/mypage.css' />" type="text/css">
<title>할 일 목록</title>
</head>
<body>
    <div class="list-container">
    <h1>${sessionScope.username}님의 할 일 목록</h1>
    <%-- <p>현재 사용자 ID: ${userId}</p> --%>
    <table>
     <thead>
       <tr>
         <th>번호</th>
         <th>설명</th>
         <th>완료</th>
         <th>수정</th>
         <th>삭제</th>
       </tr>
     </thead>
     <tbody>
        <c:forEach var="task" items="${tasks}">
           <tr>
            <td>${task.taskId}</td>
            <td>${task.description}</td>
            <td>
            <form action="<c:url value='/mypage/update' />" method="post">
        	<!-- taskId 값을 전달 -->
        	<input type="hidden" name="taskId" value="${task.taskId}" />
        
        	<!-- 체크박스 (완료 여부) -->
        	<input type="checkbox" name="isCompleted" value="true"/>
        	<!-- 선택되지 않았을 때 값을 보내기 위한 hidden input -->
        	<input type="hidden" name="isCompleted" value="false" />

        	<!-- 폼 제출 -->
        	<input type="submit" value="수정" style="display: none;" />
    		</form>
            </td>
            <td><a href="<c:url value='/mypage/update?taskId=${task.taskId}' />">수정</a></td>
            <td><a href="<c:url value='/mypage/delete?taskId=${task.taskId}' />">삭제</a></td>
           </tr>
         </c:forEach>
   		</tbody>
	</table>
        <a href="<c:url value='/mypage/create' />">새로운 할 일 추가</a>
    </div>
</body>
</html>