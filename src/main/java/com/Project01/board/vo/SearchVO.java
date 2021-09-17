package com.Project01.board.vo;

import org.springframework.stereotype.Component;

@Component("searchVO")
public class SearchVO {

	private String keyword;
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
