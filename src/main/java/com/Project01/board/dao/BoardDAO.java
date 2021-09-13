package com.Project01.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Project01.board.vo.ArticleVO;

@Repository("boardDAO")
public class BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List listArticles(String id) {
		List<ArticleVO> articles = sqlSession.selectList("mapper.board.listArticles",id);
		return articles;
	}
}
