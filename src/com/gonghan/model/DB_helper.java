package com.gonghan.model;

import java.sql.*;

public class DB_helper {

	private static Connection conn;
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://127.0.0.1:3306/";
	private final static String USRE = "root";
	private final static String PASSWORD = "123456";

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
		try {
			Statement statement = conn.createStatement();
			//If the database SOC doesn't exist, create it.
			String sql = String.format("CREATE DATABASE IF NOT EXISTS %s",
					"SOC");
			statement.execute(sql);

			//If the tables: authors and publications doesn't exist, create them
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {
		DB_helper.getConnection();
	}
}
