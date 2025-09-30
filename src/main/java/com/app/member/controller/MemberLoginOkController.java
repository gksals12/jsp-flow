package com.app.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.Action;
import com.app.Result;
import com.app.dao.MemberDAO;
import com.app.vo.MemberVO;

public class MemberLoginOkController implements Action {

   @Override
   public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
      Result result = new Result();
      
      MemberVO memberVO = new MemberVO();
      MemberDAO memberDAO = new MemberDAO();
      HttpSession session =  req.getSession();
      
      String memberEmail = null, memberPassword = null, foundMemberEmail = null;
      memberEmail = req.getParameter("memberEmail");
      memberPassword = req.getParameter("memberPassword");
      
      memberVO.setMemberEmail(memberEmail);
      memberVO.setMemberPassword(memberPassword);
      foundMemberEmail = memberDAO.select(memberVO);
      
      result.setRedirect(true);
      
      if(foundMemberEmail == null){
         // 아이디 또는 비밀번호 틀림
         result.setPath("/flow/login.member?isFail=true");
         return result;
      }
      
      // 정상 로그인
      // 모든 컨트롤러에서 memberEmail을 쓸 수 있음
      session.setAttribute("memberEmail", foundMemberEmail);
      result.setPath("/flow");
      
      return result;
   }
}
