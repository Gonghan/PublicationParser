package com.gonghan.model;

import java.util.ArrayList;

public class MyPerson {
	
	ArrayList<Article>articles;
	ArrayList<String> coauthers;
	String name;

	public MyPerson(String name) {
		this.articles=new ArrayList<Article>();
		this.coauthers=new ArrayList<String>();
		this.name=name;
		update();
	}
	
	private void update(){
		DB_helper.getArticlesFromName(name, articles, coauthers);
	}
	
	public ArrayList<Article> getArticles() {
		return articles;
	}

	public ArrayList<String> getCoauthers() {
		return coauthers;
	}

	public String getName() {
		return name;
	}
}
