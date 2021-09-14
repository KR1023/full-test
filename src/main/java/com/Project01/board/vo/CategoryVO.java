package com.Project01.board.vo;

import org.springframework.stereotype.Component;

@Component("categoryVO")
public class CategoryVO {

	private String name;
	private String id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
