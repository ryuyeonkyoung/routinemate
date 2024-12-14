<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>RoutineMate</title>
  <link rel="stylesheet" href="mainpage.css">
</head>
<body>
  <div class="signup-container">
    <nav class="navigation">
        <span class="nav-statistics">월간통계</span>
        <span class="nav-routine-recommendations">루틴추천</span>
        <span class="nav-routine-sharing">루틴공유</span>
        <span class="nav-mypage">mypage</span>
        <div class="dropdown">
            <span class="nav-login">홈</span>
            <div class="dropdown-content">
              <p>${user.userId}님 환영합니다.</p>
              <a href="mypage.html">마이페이지</a>
              <p>로그아웃</p>
            </div>
          </div>

            
    </nav>
    <h2 class="app-name">루틴메이트</h2>


    <section class="appexplain">
      <div class="app-background"></div>
      <p class="routine-mate">Routine<br />Mate</p>
        <p class="quote">
            갓생을 살고싶은 사람들을 위한<br />
            습관 형성 서비스
        </p>
    </section>

    <section class="board">
        <iframe src="loginform.html" width="100%", height="800px", frameborder="0" scrolling="no">
          </iframe>
    </section>
  </div>
</body>
</html>
