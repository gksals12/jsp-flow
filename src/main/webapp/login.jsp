<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page import="com.app.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>

   <c:if test="${not empty param.isFail}">
      <script>
         alert("아이디 또는 비밀번호를 확인하세요.")
      </script>
   </c:if>
   
   <form action="/flow/login-ok.member" method="post">
      <div>
         <span>이메일</span>
         <input name="memberEmail" />
      </div>
      <div>
         <span>비밀번호</span>
         <input type="password" name="memberPassword" />
      </div>

      <button type="button">로그인</button>
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
    
      /* flag가 모두 true라면 전송 */
      if(globalThis.flag.filter((flag) => flag).length === 2){
         form.submit() 
      }
   })
   
</script>
</html>








