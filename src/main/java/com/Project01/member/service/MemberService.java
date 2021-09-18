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
		
		public MemberVO login(MemberVO vo) {
			MemberVO member = memberDAO.memberLogin(vo);
			return member;
		}
		
		public int checkIdDuplication(String id) {
			int result = memberDAO.checkIdDuplication(id);
			return result;
		}
		
		public MemberVO getMemberInfo(String id) {
			MemberVO member = memberDAO.getMemberInfo(id);
			return member;
		}
		
		public void modMember(MemberVO member) {
			memberDAO.modMember(member);
		}
		
		public void deleteMember(String id) {
			memberDAO.deleteMember(id);
		}
}
