<%@page import="com.app.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<body>
   <form action="/flow/join-ok.member" method="post">
      <div>
         <span>이메일</span>
         <input name="memberEmail" />
      </div>
      <div>
         <span>비밀번호</span>
         <input type="password" name="memberPassword" />
      </div>
      <div>
         <span>비밀번호 확인</span>
         <input type="password" name="memberPasswordConfirm" />
      </div>
      <div>
         <span>이름</span>
         <input name="memberName" />
      </div>
      <button type="button">회원가입</button>
   </form>
</body>
<script type="text/javascript">
   const form = document.querySelector("form");
   const inputs = document.querySelectorAll("form input")
   const submitButton = document.querySelector("button");
   globalThis.flag = [true, true];
   
   submitButton.addEventListener("click", (e) => {
      /* 값 유효성 검사 */
      inputs.forEach((input) => {
         if(!input.value) { globalThis.flag[0] = false; }
      })
      if(!globalThis.flag[0]){
         return alert("값을 입력하세요.");
      }
      
      /* 비밀번호 일치 여부 */
      const password = form.elements.memberPassword.value
      const passwordConfirm = form.elements.memberPasswordConfirm.value
      if(password !== passwordConfirm) { return alert('비밀번호가 일치하지 않습니다.'); }
      
      /* flag가 모두 true라면 전송 */
      if(globalThis.flag.filter((flag) => flag).length === 2){
         form.submit() 
      }
   })
</script>
</html>








