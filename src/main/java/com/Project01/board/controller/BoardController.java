package com.Project01.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Project01.Temp;
import com.Project01.board.service.BoardService;
import com.Project01.board.vo.ArticleVO;

@Controller
public class BoardController {
	
	Temp temp = Temp.getInstance();
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private ArticleVO articleVO;
	
	@PostMapping("/api/listArticles")
	@ResponseBody
	public List listArticles(@RequestBody String id) {
		int index = id.lastIndexOf("=");
		id = id.substring(0,index);
		System.out.println("받아오는 ID : " + id);
		List<ArticleVO> articles = new ArrayList();
		articles = boardService.listArticles(id);
		return articles;
	}
	
	@PostMapping("/api/board/getId")
	@ResponseBody
	public String getId(@RequestBody String session) {
		String key = session.replace("%3A", ":").replace("=","");
		String id = temp.getSessionId(key);
		System.out.println("api/board/getId의 ID: " + id);
		temp.initId();
		return id;
	}
	
	@GetMapping("/list-article")
	public String listArticleView() {
		return "index";
	}
}
