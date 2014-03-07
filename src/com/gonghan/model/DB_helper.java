package com.gonghan.model;

import java.sql.*;
import java.util.List;

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
			// System.out.println(sb.toString());
			// System.out.println(sb2.toString());

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
