package com.gonghan.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class contains the basic operations on the database. Inside this class,
 * createTable can create the needed table if not exists. Two add* functions can
 * insert the result from XML into database.
 * 
 * @author Gonghan
 * 
 * 
 */
public class DB_helper {

	private static Connection conn;
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://127.0.0.1:3306/";
	private final static String USRE = "root";
	private final static String PASSWORD = "123456";
	private static Statement statement;

	private DB_helper() {
		init();
	}

	public static Connection getConnection() {
		if (conn == null) {
			init();
			return conn;
		} else {
			return null;
		}
	}

	/**
	 * Source:
	 * http://www.mkyong.com/jdbc/how-to-connect-to-mysql-with-jdbc-driver-java/
	 * Use JDBC to connect to the database
	 */
	private static void init() {

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USRE, PASSWORD);
			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			// Statement statement = conn.createStatement();
			createTables();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create two tables: authors, publications if not exist
	 */
	private static void createTables() {
		if (conn == null) {
			// if conn does not exist, quit
			return;
		}

		// If the database SOC doesn't exist, create it.
		String sql = String.format("CREATE DATABASE IF NOT EXISTS %s", "SOC");
		execute(sql);

		// sql=String.format("CREATE TABLE IF NOT EXISTS publications");
		// If the tables: authors and publications doesn't exist, create them

	}

	private static void execute(String sql) {
		try {
			if (statement == null || statement.isClosed()) {
				statement = conn.createStatement();
			}
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * First, this function gets the name of the author. Then search all related
	 * articles and coauthors. Save the articles and coauthors into the
	 * arrayList passed by the parameters.
	 * 
	 * @param name
	 *            : the name of the author from the user's input
	 * @param articles
	 *            : the articles which is written by the author
	 * @param coauthers
	 *            : the coauthers are the people working on the same articles
	 *            with the author
	 * @exception :1. SQL syntax exception, wrong sql statement. 
	 * 			   2. Database connection exception, the database shuts down unexpectedly.
	 */
	public static void getArticlesFromName(String name,
			ArrayList<Article> articles, ArrayList<String> coauthers) {
		try {
			if (conn == null || conn.isClosed()) {
				init();
			}
			if (statement == null || statement.isClosed()) {
				statement = conn.createStatement();
			}
			String sql = String.format(
					SQL_Operations.GetArticlesFromOneAuthorName, name);
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Article tmpa = new Article();
				tmpa.setBooktitle(rs.getString("booktitle"));
				tmpa.setDate(rs.getString("mdate"));
				tmpa.setMkey(rs.getString("mkey"));
				tmpa.setId(rs.getInt("id"));
				tmpa.setPages(rs.getString("pages"));
				tmpa.setTitle(rs.getString("title"));
				articles.add(tmpa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addArticlesIntoDatabase(List<Article> alist) {
		StringBuffer sb = new StringBuffer("INSERT INTO soc.articles VALUES");
		StringBuffer sb2 = new StringBuffer("INSERT INTO soc.coauthors VALUES");
		try {
			if (conn == null || conn.isClosed()) {
				init();
			}
			if (statement == null || statement.isClosed()) {
				statement = conn.createStatement();
			}

			for (Article a : alist) {
				sb.append(a.sql);
				sb2.append(a.authorsql);
			}
			sb.deleteCharAt(sb.length() - 1);
			sb2.deleteCharAt(sb2.length() - 1);

			statement.execute(sb.toString());
			statement.execute(sb2.toString());
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void addPublicationsIntoDatabase(List<Publication> plist) {
		try {
			if (conn == null || conn.isClosed()) {
				init();
			}
			if (statement == null || statement.isClosed()) {
				statement = conn.createStatement();
			}
			StringBuffer sb = new StringBuffer(
					"INSERT INTO soc.publications VALUES");
			for (Publication p : plist) {
				sb.append(p.toString());
			}
			sb.deleteCharAt(sb.length() - 1);
			// System.out.println(sb.toString());
			statement.execute(sb.toString());
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		DB_helper.getConnection();
	}
}
