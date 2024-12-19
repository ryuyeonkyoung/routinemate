<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>사용자 관리(UserMan3)</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/LoginForm.css' />" type="text/css">
<script>
function login() {
	if (form.userId.value == "") {
		alert("사용자 ID를 입력하십시오.");
		form.userId.focus();
		return false;
	} 
	if (form.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.password.focus();
		return false;
	}		
	form.submit();
}

function userCreate(targetUri) {
	form.action = targetUri;
	form.method="GET";		// register form 요청
	form.submit();
}
</script>
</head>
<body>
<div class="signup-container">
    <!-- 메인 제목 -->
    <span class="main-title">루틴메이트</span>
    
    <!-- 메뉴 섹션 -->
    <div class="menu">
      <span class="menu-item">월간통계</span>
      <span class="menu-item">루틴추천</span>
      <span class="menu-item">루틴공유</span>
      <span class="menu-item">mypage</span>
      <span class="menu-item">로그인</span>
    </div>
    
    <!-- 로그인 텍스트 -->
    <span class="login-title">로그인</span>
    
    <!-- 로그인 폼 -->
    <form  name="form" method="POST" action="<c:url value='/user/login' />">
      <!-- ID 섹션 -->
      <div class="id-section">
        <div class="id-box"></div>
        <label for="login-id" class="id-label"></label>
        <input type="text" id="login-id" name="username" class="id-input" placeholder="ID" required />
      </div>
      
      <!-- 패스워드 섹션 -->
      <div class="password-section">
        <div class="password-box"></div>
        <label for="login-password" class="password-label"></label>
        <input type="password" id="login-password" name="password" class="password-input" placeholder="Password" required />
      </div>
      
      <div class="signin-section">
        <div class="signin-box"></div>
        <button type="submit" class="signin-button  value="로그인" onClick="login()">로그인</button>
      </div>
      <p class="signup-title" value="회원가입" onClick="userCreate('<c:url value='/user/register/form'/>')">회원가입</p>
	  </form>
  </div>
</body>
</html>