package com.app.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.app.Action;
import com.app.Result;
import com.app.dao.MemberDAO;
import com.app.dao.OrderDAO;
import com.app.dto.OrderDTO;

public class OrderReadController implements Action {

	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Result result = new Result();
		Long id = Long.parseLong(req.getParameter("id"));
		HttpSession session = req.getSession();
		String memberEmail = (String)session.getAttribute("memberEmail");
		OrderDAO orderDAO = new OrderDAO();
		OrderDTO orderDTO = new OrderDTO();
		MemberDAO memberDAO = new MemberDAO();
		Long memberId = memberDAO.selectIdByMemberEmail(memberEmail);
		
		orderDTO.setId(id);
		orderDTO.setMemberId(memberId);
		
		orderDAO.select(orderDTO).ifPresent((order) -> {
			req.setAttribute("order", new JSONObject(order));
		});

		result.setPath("/order/order_read.jsp");
		return result;
	}

}
