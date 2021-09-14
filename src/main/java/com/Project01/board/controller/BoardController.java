package com.Project01.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Project01.Temp;
import com.Project01.board.service.BoardService;
import com.Project01.board.vo.ArticleVO;
import com.Project01.board.vo.CategoryVO;

@Controller
public class BoardController {
	
	Temp temp = Temp.getInstance();
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private ArticleVO articleVO;
	
	
	@PostMapping("/api/board/getId")
	@ResponseBody
	public String getId(@RequestBody String session) {
		String key= changeKey(session);
		String id = temp.getSessionId(key);
		System.out.println("api/board/getId의 ID: " + id);
		temp.initId();
		return id;
	}
	
	@PostMapping("/api/board/getCategory")
	@ResponseBody
	public List<CategoryVO> getCategory(@RequestBody String session) {
		List<CategoryVO> category = new ArrayList();
		String key = changeKey(session);
		String id = temp.getSessionId(key);
		category = boardService.getCategory(id);
		for(int i = 0; i<category.size();i++) {
			System.out.println("catList의 "+i+"번째 내용 : " + category.get(i));
		}
		
		return category;
	}
	
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
	
	@PostMapping("/api/board/addArticle")
	public void addArticle(@RequestBody ArticleVO article,HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		int articleNO = boardService.getArticleNO();
		article.setArticleNO(articleNO);
		boardService.addArticle(article);
		System.out.println("수신 ID : " + article.getId());
		System.out.println("수신 제목 : " + article.getTitle());
		System.out.println("수신 내용 : " + article.getContent());
		System.out.println("수신 카테고리 : " + article.getCategory());
	}
	
	private String changeKey(String session) {
		String key = session.replace("%3A", ":").replace("=","");
		System.out.println(key);
		return key;
	}
}
