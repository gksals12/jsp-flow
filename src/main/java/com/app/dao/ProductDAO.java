package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.app.myBatis.config.MyBatisConfig;
import com.app.vo.ProductVO;

public class ProductDAO {
	
	public SqlSession sqlSession;
	
	public ProductDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	public void insert(ProductVO productVO) {
		sqlSession.insert("product.insert", productVO);
	}
	
	public List<ProductVO> selectAll() {
		List<ProductVO> products = sqlSession.selectList("product.selectAll");
		return products;
	}
	
	public void update(ProductVO productVO) {
		sqlSession.update("product.update", productVO);
	}
	
	public Optional<ProductVO> select(Long id) {
		return Optional.ofNullable( sqlSession.selectOne("product.select", id));
	}
}
