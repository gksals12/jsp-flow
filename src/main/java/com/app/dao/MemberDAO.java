package com.app.dao;

import org.apache.ibatis.session.SqlSession;

import com.app.myBatis.config.MyBatisConfig;
import com.app.vo.MemberVO;

public class MemberDAO {
	
	public SqlSession sqlSession;
	
	public MemberDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
//	회원가입
	public void insert(MemberVO memberVO) {
		sqlSession.insert("member.insert", memberVO);
	}
	
//	로그인
	public String select(MemberVO memberVO) {
		return sqlSession.selectOne("member.select", memberVO);
	}
	
// 이메일로 ID 조회
	public Long selectIdByMemberEmail(String memberEmail) {
		return sqlSession.selectOne("member.selectIdByMemberEmail", memberEmail);
	}
}
