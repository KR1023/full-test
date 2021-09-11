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
import org.springframework.web.servlet.ModelAndView;

import com.Project01.member.service.MemberService;
import com.Project01.member.vo.MemberVO;

@Controller("memberController")
public class MemberController {
	private String accId = null;
	private String sessionId = null;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberVO memberVO;
	
	@PostMapping("/api/login")
	@ResponseBody
	public int login(@RequestBody MemberVO vo) {
		int result = 0;
		memberVO = memberService.login(vo);
		if(memberVO != null) {
			accId = memberVO.getId();
			result = 1;
		}else {
			result = 0;
		}
		return result;
	}
	
	@GetMapping("/api/getId")
	@ResponseBody
	public Map<String,String> getAuth() {
		Map<String, String> info = new HashMap<String,String>();
		info.put("accId",accId);
		info.put("sessionId", sessionId);
		return info;
	}
	
	@GetMapping("/api/getSession")
	@ResponseBody
	public String getSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		System.out.println(session);
		return sessionId;
	}
	
	
	@PostMapping("/api/member/addMember")
	public void addMember(@RequestBody MemberVO vo, HttpServletResponse response) {
		response.addHeader("Access-COntrol-Allow-Origin", "*");
		memberService.addMember(vo);
//		return "/";
	}
	
	@GetMapping("/test")
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView("redirect:/");
		return mav;
	}
	
	@GetMapping("/home")
	public String main() {
		return "index";
	}
}
