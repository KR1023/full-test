package com.Project01.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Project01.Temp;
import com.Project01.board.service.BoardService;
import com.Project01.board.vo.ArticleVO;
import com.Project01.board.vo.CategoryVO;
import com.Project01.board.vo.SearchVO;

@Controller
public class BoardController {
	
	Temp temp = Temp.getInstance();
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private ArticleVO articleVO;
	private int articleNO = 0;
	private String tempId = null;
	
	@PostMapping("/api/board/getId")
	@ResponseBody
	public String getId(@RequestBody String session) {
		String key= changeKey(session);
		String id = temp.getSessionId(key);
		tempId = id;
		System.out.println("api/board/getId의 ID: " + id);
		return id;
	}
	
	@PostMapping("/api/board/getCategory")
	@ResponseBody
	public List<CategoryVO> getCategory(@RequestBody String session) {
		List<CategoryVO> category = new ArrayList();
		String key = changeKey(session);
		String id = temp.getSessionId(key);
		category = boardService.getCategory(id);
		
		return category;
	}
	
	@PostMapping("/api/listArticles")
	@ResponseBody
	public List listArticles(@RequestBody String id) {
		id = id.replace("=","");
		System.out.println("received Id : " + id);
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
	}
	
	// 단순 articleNO 수신.
	@PostMapping("/api/board/sendArticleNO")
	public void getArticleNO(@RequestBody String articleNO, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String before = removeEqualSign(articleNO);
		int after = Integer.parseInt(before);
		this.articleNO = after;
		System.out.println("this.articleNO : " + this.articleNO);
	}
	
	@GetMapping("/api/board/viewArticle")
	@ResponseBody
	public ArticleVO viewArticle() {
		articleVO = boardService.viewArticle(articleNO);
		return articleVO;
	}
	
	@PostMapping("/api/board/send-article")
	public void snedArticle(@RequestBody ArticleVO article, HttpServletResponse response) {
		response.setHeader("Access-Controll-Allow-Origin","*");
		articleVO = article;
		System.out.println("sender : " + article.getId());
		System.out.println("received articleNO : " + article.getArticleNO());
	}
	
	@GetMapping("/api/board/get-article")
	@ResponseBody
	public ArticleVO getArticle() {
		ArticleVO article = articleVO;
		articleVO = null;
		return article;
	}
	
	@PostMapping("/api/board/mod-article")
	public void modArticle(@RequestBody ArticleVO article, HttpServletResponse response) {
		response.setHeader("Access-Controll-Allow-Origin", "*");
		boardService.modArticle(article);
	}
	
	@PostMapping("/api/board/delete-article")
	public void deleteArticle(@RequestBody String articleNO, HttpServletResponse response) {
		String before = removeEqualSign(articleNO);
		int after = Integer.parseInt(before);
		response.setHeader("Access-Control-Allow-Origin","*");
		boardService.deleteArticle(after);
	}
	
	@RequestMapping(value="/api/board/search-title",method=RequestMethod.POST)
	@ResponseBody
	public List searchTitle(@RequestBody SearchVO keyword, HttpServletResponse response) throws Exception {
		String id = tempId;
		response.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println(keyword.getKeyword());
		List<ArticleVO> articles = new ArrayList<ArticleVO>();
		articles = boardService.searchTitle(keyword.getKeyword(),id);
		return articles;
	}
	
	@PostMapping("/api/board/search-category")
	@ResponseBody
	public List searchCategory(@RequestBody SearchVO keyword, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = tempId;
		response.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println("수신 카테고리 : "+ keyword.getKeyword());
		List<ArticleVO> articles = new ArrayList<ArticleVO>();
		articles = boardService.searchCategory(keyword.getKeyword(),id);
		return articles;
	}
	
	@PostMapping("/api/board/add-category")
	public void addCategory(@RequestBody CategoryVO category, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin","*");
		boardService.addCategory(category);
	}
	
	@PostMapping("/api/board/delete-category")
	public void deleteCategory(@RequestBody CategoryVO category, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		boardService.deleteCategory(category);
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
