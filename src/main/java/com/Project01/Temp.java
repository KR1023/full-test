package com.Project01;

import java.util.HashMap;
import java.util.Map;

public class Temp {
	
	private static Temp instance = new Temp();
	private String id;
	private String session;
	private String articleNO;
	

	private Map<String, String> users = new HashMap<String, String>();

	private Temp() {}
	
	public static Temp getInstance() {
		if(instance == null)
		{
			instance = new Temp();
		}
		return instance;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void initId() {
		this.id = null;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}
	
	public String getArticleNO() {
		return articleNO;
	}

	public void initArticleNO() {
		this.articleNO = null;
	}
	public void setArticleNO(String articleNO) {
		this.articleNO = articleNO;
	}
	
	public void initSession() {
		this.session = null;
	}
	
	// sessionId, userId 별로 저장.
	public void addUser(Map user,String key) {
		users.put(key,(String) user.get(key));
	}
	
	// userId를 반환
	public String getSessionId(String sess) {
		return users.get(sess);
	}
	
	// user 로그아웃 시 user를 Map에서 제거.
	public void removeUser(String sess) {
		users.remove(sess);
	}
}
