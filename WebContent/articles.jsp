<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.ArrayList"%>
<%@page import="com.gonghan.model.Article"%>
<%@page import="java.io.PrintWriter"%><html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Articles</title>
</head>
<body>
<h3>The articles written by<%=(String)request.getAttribute("author") %></h3>
<table class="table table-striped table-hover">
	<tr>
		<td>Key</td>
		<td>Date</td>
		<td>Title</td>
		<td>Pages</td>
		<td>Book title</td>
	</tr>
	<%
		ArrayList<Article> articles = (ArrayList<Article>) request
				.getAttribute("articles");
		int index = 1;
		//PrintWriter writer = response.getWriter();
	%>
	<%
		for (Article a : articles) {
	%>
	<tr>
		<td><%=a.getMkey()%></td>
		<td><%=a.getDate()%></td>
		<td><%=a.getTitle()%></td>
		<td><%=a.getPages()%></td>
		<td><%=a.getBooktitle()%></td>
	</tr>
	<%
		}
	%>
</table>
</body>
</html>