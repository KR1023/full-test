package com.Project01.member.controller;

import java.util.List;

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
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberVO memberVO;
	
	@PostMapping("/api/login")
	@ResponseBody
	public int login(@RequestBody MemberVO vo, HttpServletRequest request) {
		memberVO = memberService.login(vo);
		HttpSession session = request.getSession();
		int result = 0;
		result = memberService.login(vo);
		
		return result;
	}
	
	@GetMapping("/member/membersList")
	public ModelAndView listMembers() {
		ModelAndView mav = new ModelAndView();
		List members = memberService.selectMember();
		mav.addObject("members", members);
		mav.setViewName("memberList");
		return mav;
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
}
