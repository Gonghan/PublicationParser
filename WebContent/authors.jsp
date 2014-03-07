<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Authors Chain</title>
</head>
<body>
<h3>Chain from <%=request.getAttribute("first")%> to <%=request.getAttribute("second")%></h3>
<p>Click the link to see more information about the author</p>
<ul>
	<%
		String[] authors = (String[]) request.getAttribute("persons");
		for (String author : authors) {
	%>
	<li><a href="./ArticleServlet?author=<%=author%>"><%=author%></a>
	</li>
	<%
		}
	%>
</ul>
</body>
</html>