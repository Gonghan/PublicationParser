package com.gonghan.model;

import java.util.ArrayList;
import java.util.List;

public class Publication {

	private String editor;
	private List<String> authors;
	private String title;
	private String mdate;
	private String key;

	public String getMdate() {
		return mdate;
	}

	//rename or explain mdate
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	private String school;
	private String url;
	private String pages;
	private String booktitle;

	public Publication() {
		this.authors = new ArrayList<String>();
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void addAuthor(String author) {
		this.authors.add(author);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getBooktitle() {
		return booktitle;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}

	@Override
	public String toString() {
		String value = String.format(
				"(NULL,%s,NULL,%s,%s,%s,%s,%s,%s,%s),",
				reStr(getEditor()), reStr(getTitle()), reStr(getMdate()),
				reStr(getKey()), reStr(getSchool()), reStr(getUrl()),
				reStr(getPages()), reStr(getBooktitle()));
		return value;
	}

	private String reStr(String str) {
		if (str==null || str.isEmpty()) {
			return "NULL";
		} else {
			return String.format("'%s'", str);
		}
	}

}
