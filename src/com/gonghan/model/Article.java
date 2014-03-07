package com.gonghan.model;

import java.util.ArrayList;
import java.util.List;

public class Article {
	static int id = 1000;
	String mkey;
	String date;
	String title;
	String pages;
	String booktitle;
	List<String> authors;
	String sql;
	String authorsql;

	public Article() {
		this.authors = new ArrayList<String>();
	}

	public void setMkey(String mkey) {
		this.mkey = mkey;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}

	public void addAuthor(String s) {
		this.authors.add(s);
	}

	private String toSQLString() {
		String sql = String.format("(%d,%s,%s,%s,%s,%s),", id, reStr(mkey),
				reStr(date), reStr(title), reStr(pages), reStr(booktitle));
		return sql;
	}

	private String reStr(String str) {
		if (str == null || str.isEmpty()) {
			return "NULL";
		} else {
			str=str.replace("'", "\"");
			return String.format("'%s'", str);
		}
	}

	private String toAuthorSQL() {
		StringBuffer sql = new StringBuffer();
		for (String author : authors) {
			sql.append(String.format("(NULL,%d,%s),", id, reStr(author)));
		}
		return sql.toString();
	}

	public void update() {
		auto_increment();
		this.sql = this.toSQLString();
//		System.out.println("----------------------");
//		System.out.println(sql);
//		System.out.println("----------------------");
		this.authorsql = this.toAuthorSQL();
		
	}

	public void auto_increment() {
		Article.id++;
	}

}
