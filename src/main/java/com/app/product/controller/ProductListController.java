package com.app.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.app.Action;
import com.app.Result;
import com.app.dao.ProductDAO;

public class ProductListController implements Action {

	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Result result = new Result();
		ProductDAO productDAO = new ProductDAO();
		JSONArray productsJSON = new JSONArray();
		
		req.setAttribute("products", productDAO.selectAll());
		productDAO.selectAll().stream().map(JSONObject::new).forEach(productsJSON::put);
		req.setAttribute("productsJSON", productsJSON);
		result.setPath("/productList.jsp");
		return result;
	}

}
