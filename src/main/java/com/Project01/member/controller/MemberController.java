package com.Project01.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Project01.Temp;
import com.Project01.member.service.MemberService;
import com.Project01.member.vo.MemberVO;

@Controller("memberController")
public class MemberController {
	
	Temp temp = Temp.getInstance();
	
	private String tempId = null;
	private Map<String, String> memberId = new HashMap<String, String>();
	
	
	
	@Autowired
	MemberService memberService;
	
//	@Autowired
//	MemberVO memberVO;
	
	// 필수임..
	@RequestMapping("/home")
	public String main() {
		return "index";
	}
	
	@PostMapping("/api/login")
	@ResponseBody
	public int login(@RequestBody MemberVO vo) {
		int result = 0;
		System.out.println("로그인 아이디  :" + vo.getId());
		System.out.println("로그인 비밀번호 : " + vo.getPwd());
		MemberVO member = memberService.login(vo);
		if(member != null) {
			tempId = member.getId();
			temp.setId(tempId);
			System.out.println("temp에 ID 저장");
			memberId.put(tempId, tempId);
			System.out.println("size: " + memberId.size());
			result = 1;
		}else {
			result = 0;
		}
		System.out.println(memberId.get(tempId));
		return result;
	}
	
	// 세션값 설정.
	// vue에서 생성된 세션 값을 수신하여 저장.
	@PostMapping("/api/setSession")
	@ResponseBody
	public void setSession(@RequestBody String sess) {
		String key = changeKey(sess); 
		System.out.println(key);
		temp.setSession(key);
		System.out.println("Key : " + key);
		memberId.put(key,tempId);
		
		temp.addUser(memberId, key);
		System.out.println("Key : " + key +", Value : " + tempId);
		System.out.println("로그인 유저 추가 : " + tempId);
		
	}
	
//	@GetMapping("/api/getId")
//	@ResponseBody
//	public String getId() {
//		String id = tempId;
//		String id2 = temp.getId();
//		System.out.println("temp로 ID 받기 : " + id2);
//		tempId = null;
//		return id;
//	}
	
	
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
	
	private String changeKey(String session) {
		String key = session.replace("%3A", ":").replace("=","");
		System.out.println(key);
		return key;
	}
}
