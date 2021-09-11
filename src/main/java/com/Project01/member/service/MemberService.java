package com.Project01.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project01.member.dao.MemberDAO;
import com.Project01.member.vo.MemberVO;

@Service("memberService")
public class MemberService {
		
		@Autowired
		MemberDAO memberDAO;
		
		public List selectMember() {
			List list = new ArrayList();
			list = memberDAO.selectMember();
			return list;
		}
		
		public void addMember(MemberVO vo) {
			memberDAO.addMember(vo);
		}
		
		public int login(MemberVO vo) {
			int result = memberDAO.memberLogin(vo);
			return result;
		}
}
