package com.app.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.app.Action;
import com.app.Result;
import com.app.dao.MemberDAO;
import com.app.dao.OrderDAO;

public class OrderListController implements Action {

	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Result result = new Result();
		OrderDAO orderDAO = new OrderDAO();
		JSONArray orderList = new JSONArray();
		HttpSession session = req.getSession();
		MemberDAO memberDAO = new MemberDAO();
		
		Long id = memberDAO.selectIdByMemberEmail((String)session.getAttribute("memberEmail"));
		
		orderDAO.selectAll(id).stream().map(JSONObject::new).forEach(orderList::put);
		req.setAttribute("orderList", orderList);
		
		result.setPath("/order/order_list.jsp");
		return result;
	}

}
