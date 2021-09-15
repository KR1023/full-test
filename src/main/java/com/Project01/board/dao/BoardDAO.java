package com.Project01.board.dao;

import java.util.List;

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
}
