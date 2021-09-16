package com.Project01.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Project01.member.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAO {

	@Autowired
	SqlSession sqlSession;
	
	public List selectMember() {
		List list = sqlSession.selectList("mapper.member.selectAllMembers");
		return list;
	}
	
	public void addMember(MemberVO vo) {
		int result = sqlSession.insert("mapper.member.insertMember",vo);
	}
	
	public MemberVO memberLogin(MemberVO vo) {
		MemberVO member = sqlSession.selectOne("mapper.member.loginMember",vo);
		return member;
	}
	
	public int checkIdDuplication(String id) {
		int result = sqlSession.selectOne("mapper.member.checkId",id);
		return result;
	}
	
}
