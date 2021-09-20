package com.Project01.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	// 필수
	@RequestMapping("/home")
	public String main() {
		return "index";
	}
	
	@PostMapping("/api/login")
	@ResponseBody
	public int login(@RequestBody MemberVO vo) {
		int result = 0;
		System.out.println("Login ID  :" + vo.getId());
		System.out.println("Login Pwd : " + vo.getPwd());
		MemberVO member = memberService.login(vo);
		if(member != null) {
			tempId = member.getId();
			temp.setId(tempId);
			System.out.println("Saved Id in temp...");
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
		System.out.println("user added : " + tempId);
		
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
	
	@PostMapping("/api/member/checkId")
	@ResponseBody
	public int checkIdDuplication(@RequestBody String id) {
		String after = removeEqualSign(id);
		int result = memberService.checkIdDuplication(after);
		return result;
	}
	
	@PostMapping("/api/member/get-member")
	public void getMember(@RequestBody String sess, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String key = changeKey(sess);
		System.out.println("received key : " + key);
		tempId = temp.getSessionId(key);
		System.out.println("modifying : " + tempId);
	}
	
	@GetMapping("/api/member/get-info")
	@ResponseBody
	public MemberVO getInfo() {
		MemberVO member = memberService.getMemberInfo(tempId);
		return member;
	}
	
	@PutMapping("/api/member/mod-member")
	public void modMember(@RequestBody MemberVO member, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		memberService.modMember(member);
	}
	
	@PostMapping("/api/member/delete-member")
	public void deleteMember(@RequestBody String id, HttpServletResponse response) {
		id = removeEqualSign(id);
		System.out.println("deleted : " + id);
		response.setHeader("Access-Controll-Allow-Origin","*");
		memberService.deleteMember(id);
	}
	
	private String changeKey(String session) {
		String key = session.replace("%3A", ":").replace("=","");
		System.out.println(key);
		return key;
	}
	
	private String removeEqualSign(String before) {
		String after = before.replace("=", "");
		return after;
	}
}
