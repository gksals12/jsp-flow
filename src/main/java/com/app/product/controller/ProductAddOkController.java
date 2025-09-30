package com.app.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.Action;
import com.app.Result;
import com.app.dao.ProductDAO;
import com.app.vo.ProductVO;

public class ProductAddOkController implements Action {

	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Result result = new Result();
		String productName = null;
		Integer productPrice = null, productStock = null;
		ProductVO productVO = new ProductVO();
		ProductDAO productDAO = new ProductDAO();
		
		productName = req.getParameter("productName");
		productPrice = Integer.parseInt( req.getParameter("productPrice"));
		productStock = Integer.parseInt(req.getParameter("productStock"));
		
		productVO.setProductName(productName);
		productVO.setProductPrice(productPrice);
		productVO.setProductStock(productStock);
		
		productDAO.insert(productVO);
		
		result.setPath("/flow/list.product");
		result.setRedirect(true);
		return result;
	}

}
