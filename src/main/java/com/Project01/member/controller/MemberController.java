package com.Project01.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Project01.member.service.MemberService;
import com.Project01.member.vo.MemberVO;

@Controller("memberController")
public class MemberController {
	
	private String tempId = null;
	private Map<String, String> memberId = new HashMap<String, String>();
	
	@Autowired
	MemberService memberService;
	
//	@Autowired
//	MemberVO memberVO;
	
	@PostMapping("/api/login")
	@ResponseBody
	public int login(@RequestBody MemberVO vo) {
		int result = 0;
		MemberVO member = memberService.login(vo);
		if(member != null) {
			tempId = member.getId();
			memberId.put(tempId, tempId);
			System.out.println("size: " + memberId.size());
			result = 1;
		}else {
			result = 0;
		}
		System.out.println(memberId.get(tempId));
		return result;
	}
	
	@GetMapping("/api/getId")
	@ResponseBody
	public String getId() {
		String id = tempId;
		tempId = null;
		return id;
	}
	
	@PostMapping("/api/logout")
	@ResponseBody
	public void logout(@RequestBody String id) {
		int equal = id.lastIndexOf("=");
		String memId = id.substring(0,equal); 
		System.out.println(memId);
		memberId.remove(memId);
		System.out.println("--size: " + memberId.size());
	}
	
	@PostMapping("/api/member/addMember")
	public void addMember(@RequestBody MemberVO vo, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		memberService.addMember(vo);
	}
	
	
	@GetMapping("/home")
	public String main() {
		return "index";
	}
}
