package com.Project01.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Project01.board.vo.ArticleVO;
import com.Project01.board.vo.CategoryVO;

@Repository("boardDAO")
public class BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List listArticles(String id) {
		List<ArticleVO> articles = sqlSession.selectList("mapper.board.listArticles",id);
		return articles;
	}
	
	public List<CategoryVO> getCategory(String id) {
		List<CategoryVO> category = sqlSession.selectList("mapper.board.getCategory",id);
		return category;
	}
	
	public void addArticle(ArticleVO article) {
		int result = sqlSession.insert("mapper.board.addArticle",article);
	}
	
	public int getArticleNO() {
		int articleNO = sqlSession.selectOne("mapper.board.getArticleNO");
		return articleNO;
	}
	
	public ArticleVO viewArticle(int articleNO) {
		ArticleVO article = sqlSession.selectOne("mapper.board.viewArticle",articleNO);
		return article;
	}
	
	public void modArticle(ArticleVO article) {
		int result = sqlSession.update("mapper.board.modArticle",article);
	}
	
	public void deleteArticle(int articleNO) {
		int result = sqlSession.delete("mapper.board.deleteArticle",articleNO);
	}
	
	public List<ArticleVO> searchTitle(String keyword, String id) {
		Map<String, String> condition = new HashMap<String,String>();
		condition.put("title", keyword);
		condition.put("id", id);
		List<ArticleVO> articles = sqlSession.selectList("mapper.board.searchTitle",condition);
		return articles;
	}
	
	public List<ArticleVO> searchCategory(String keyword, String id) {
		Map<String, String> condition = new HashMap<String,String>();
		condition.put("category", keyword);
		condition.put("id", id);
		List<ArticleVO> articles = sqlSession.selectList("mapper.board.searchCategory",condition);
		return articles;
	}
	
	public void addCategory(CategoryVO category) {
		int result = sqlSession.insert("mapper.board.addCategory",category);
	}
}
