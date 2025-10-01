package com.app.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.Action;
import com.app.Result;
import com.app.dao.MemberDAO;
import com.app.dao.OrderDAO;
import com.app.dao.ProductDAO;
import com.app.vo.OrderVO;
import com.app.vo.ProductVO;

public class OrderWriteOkController implements Action {

	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Result result = new Result();
		String[] productIds = null, productCounts = null;
		OrderDAO orderDAO = new OrderDAO();
		MemberDAO memberDAO = new MemberDAO();
		ProductDAO productDAO = new ProductDAO();
		HttpSession session = req.getSession();
		
		productIds = req.getParameterValues("productId");
		productCounts = req.getParameterValues("productCount");
		for(int i = 0; i < productIds.length; i++) {
			OrderVO orderVO = new OrderVO();
			Long productId = Long.parseLong(productIds[i]);
			Integer productCount = Integer.parseInt(productCounts[i]);
			String memberEmail = (String)session.getAttribute("memberEmail");
			Long memberID = memberDAO.selectIdByMemberEmail(memberEmail);
			ProductVO productVO = new ProductVO();
			
			orderVO.setMemberId(memberID);
			orderVO.setProductId(productId);
			orderVO.setProductCount(productCount);

			System.out.println("productId: " + orderVO.getProductId());
			System.out.println("memberId: " + orderVO.getMemberId());
			
			orderDAO.insert(orderVO);

			productDAO.select(productId).ifPresent((product) -> {
				productVO.setId(product.getId());
				productVO.setProductName(product.getProductName());
				productVO.setProductPrice(product.getProductPrice());
				productVO.setProductStock(product.getProductStock() - productCount);
				productDAO.update(productVO);
			});
		}
		
		result.setRedirect(true);
		result.setPath("/flow/write-complete.order");
		return result;
	}

}
