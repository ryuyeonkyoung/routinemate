<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>사용자 관리(UserMan3)</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/mainpage.css' />" type="text/css">
</head>
<body>
  <div class="signup-container">
    <nav class="navigation">
        <span class="nav-statistics">월간통계</span>
        <span class="nav-routine-recommendations">루틴추천</span>
        <span class="nav-routine-sharing">루틴공유</span>   
		<c:choose>
		   <%-- 로그인 된 경우 --%>
		   <c:when test="${not empty sessionScope.username}">
		   		<div class = "dropdown">
				<span class="nav-login">${sessionScope.username}님</span>
				<div class="dropdown-content">
			       <a href="<c:url value='/mypage' />">마이페이지</a>
			       <a href="<c:url value='/user/logout' />">로그아웃</a>
			       <p>${sessionScope.username}님 환영합니다.</p>
			     </div>   
				</div> 
		   </c:when>
		   <%-- 로그인 된 경우 --%>
		   <c:otherwise>
		       <span class="nav-login"><a href="<c:url value='/user/login' />">로그인</a></span>
		   </c:otherwise>
		</c:choose>
    </nav>
    
    <a href="/UserMan3c//user/mainpage" class="app-link">
    		<h2 class="app-name">루틴메이트</h2>
	</a>


    <section class="appexplain">
      <div class="app-background"></div>
      <p class="routine-mate">Routine<br />Mate</p>
        <p class="quote">
            갓생을 살고싶은 사람들을 위한<br />
            습관 형성 서비스
        </p>
    </section>

<!--   <section class="board">
        <iframe src="loginform.html" width="100%", height="800px", frameborder="0" scrolling="no">
          </iframe>
    </section> -->
  </div>
</body>
</html>
