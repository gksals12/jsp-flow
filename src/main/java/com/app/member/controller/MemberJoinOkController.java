package com.app.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.Action;
import com.app.Result;
import com.app.dao.MemberDAO;
import com.app.vo.MemberVO;

public class MemberJoinOkController implements Action {

	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Result result = new Result();
		String memberEmail = null, memberPassword = null, memberName = null;
		MemberVO memberVO = new MemberVO();
		MemberDAO memberDAO = new MemberDAO();
		
		memberEmail = req.getParameter("memberEmail");
		memberPassword = req.getParameter("memberPassword");
		memberName = req.getParameter("memberName");
		
		memberVO.setMemberEmail(memberEmail);
		memberVO.setMemberPassword(memberPassword);
		memberVO.setMemberName(memberName);
		
		memberDAO.insert(memberVO);
		
		result.setPath("/flow");
		result.setRedirect(true);
		
		return result;
	}

}
