<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>사용자 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/JoinForm.css' />" type="text/css">
<script>
function userCreate() {
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
	if (form.password.value != form.password2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		form.name.focus();
		return false;
	}
	if (form.name.value == "") {
		alert("이름을 입력하십시오.");
		form.name.focus();
		return false;
	}
	var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if(emailExp.test(form.email.value)==false) {
		alert("이메일 형식이 올바르지 않습니다.");
		form.email.focus();
		return false;
	}
	var phoneExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
	if(phoneExp.test(form.phone.value)==false) {
		alert("전화번호 형식이 올바르지 않습니다.");
		form.phone.focus();
		return false;
	}
	form.submit();
}

function userList(targetUri) {
	form.action = targetUri;
	form.submit();
}

</script>
</head>
<body>
<form name="form" method="POST" action="<c:url value='/user/register' />">
    <div class="signup-container">
    <nav class="navigation">
        <span class="nav-statistics">월간통계</span>
        <span class="nav-routine-recommendations">루틴추천</span>
        <span class="nav-routine-sharing">루틴공유</span>
        <span class="nav-mypage">mypage</span>
        <span class="nav-login">로그인</span>
    </nav>
    
    <h2 class="app-name">루틴메이트</h2>
    
    
    <h1 class="signup-title">회원가입</h1>
   	<label class="label-username">아이디</label>
    <input type="text" name="userId" class="input-username" placeholder="아이디 입력" />

    <label class="label-password">비밀번호</label>
    <input type="password" name="password" class="input-password" placeholder="비밀번호 입력" />
    
    <label class="label-password-confirm">비밀번호 확인</label>
    <input type="password" name="password2" class="input-password-confirm" placeholder="비밀번호 입력" />

    <label class="label-name">이름</label>
    <input type="text" name="username" class="input-name" placeholder="이름 입력" />

    <label class="label-email">이메일</label>
    <input type="email" name="email" class="input-email" placeholder="이메일 입력" />
    
    <label class="label-birthdate">생년월일</label>
	<input type="date" name="birthDate" class="input-birthdate" placeholder="생년월일 입력" />

	<div class="user-type">
        <label class="type-morning">
          <input type="checkbox" class="checkbox-morning"  name="isMorningPerson" value ="MORNING" /> 아침형
        </label>
        <label class="type-evening">
          <input type="checkbox" class="checkbox-evening"  name="isMorningPerson" value ="EVENING" /> 저녁형
        </label>
	</div>
	 <button value="회원 가입" onClick="userCreate()" class="btn-signup">회원가입</button>
	 
    </div>      
</form>

</body>
</html>