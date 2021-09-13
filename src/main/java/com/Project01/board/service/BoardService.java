package com.Project01.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project01.board.dao.BoardDAO;

@Service("boardService")
public class BoardService {
	@Autowired
	private BoardDAO boardDAO;
	
	public List listArticles(String id) {
		return boardDAO.listArticles(id);
	}
	
}
