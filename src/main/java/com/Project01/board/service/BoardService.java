package com.Project01.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project01.board.dao.BoardDAO;
import com.Project01.board.vo.ArticleVO;

@Service("boardService")
public class BoardService {
	@Autowired
	private BoardDAO boardDAO;
	
	public List listArticles(String id) {
		return boardDAO.listArticles(id);
	}
	
	public List getCategory(String id) {
		return boardDAO.getCategory(id);
	}
	
	public void addArticle(ArticleVO article) {
		boardDAO.addArticle(article);
	}
	
	public int getArticleNO() {
		return boardDAO.getArticleNO();
	}
	
	public ArticleVO viewArticle(int articleNO) {
		return boardDAO.viewArticle(articleNO);
	}
	
	public void modArticle(ArticleVO article) {
		boardDAO.modArticle(article);
	}
	
	public void deleteArticle(int articleNO) {
		boardDAO.deleteArticle(articleNO);
	}
	
	public List searchTitle(String keyword,String id) {
		return boardDAO.searchTitle(keyword, id);
	}
	
	public List searchCategory(String keyword,String id) {
		return boardDAO.searchCategory(keyword, id);
	}
}
