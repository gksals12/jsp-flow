package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.app.dto.OrderDTO;
import com.app.myBatis.config.MyBatisConfig;
import com.app.vo.OrderVO;

public class OrderDAO {
	
	public SqlSession sqlSession;
	
	public OrderDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	public void insert(OrderVO orderVO) {
		sqlSession.insert("order.insert", orderVO);
	}
	
	public List<OrderDTO> selectAll(Long id) {
		return sqlSession.selectList("order.selectAll", id);
	}
	
	public Optional<OrderDTO> select(OrderDTO id) {
		Optional<OrderDTO> order = sqlSession.selectOne("order.select", id);
		return order;
	}
}
