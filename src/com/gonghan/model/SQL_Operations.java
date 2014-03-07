package com.gonghan.model;

public interface SQL_Operations {
	public static final String GetArticlesFromOneAuthorName="SELECT * FROM soc.articles a JOIN soc.coauthors c ON a.id = c.aid WHERE c.authors = '%s'";
}
