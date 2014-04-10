package com.gonghan.model;

public interface SQL_Operations {
	/**
	 * One sample(formatted): 
	 * SELECT * FROM soc.articles a
	 * JOIN soc.coauthors c 
	 * ON a.id = c.aid 
	 * WHERE c.authors ='JohnW'
	 * 
	 * Get information about all articles written by one author called 'JohnW'
	 */
	public static final String GetArticlesFromOneAuthorName="SELECT * FROM soc.articles a JOIN soc.coauthors c ON a.id = c.aid WHERE c.authors = '%s'";
}
