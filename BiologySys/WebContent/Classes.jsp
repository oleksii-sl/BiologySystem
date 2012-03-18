<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="Error.jsp"
    import="biosys.model.*" import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main page</title>
<style type="text/css">
<!--
@import url("style.css");
-->
</style>
</head>
<body>
<h2><a href="main" >Main</a></h2>
<%!List<BioClass> list; %>
<form action="classes" method="post">
	<input type="hidden" name="name" value="name" />
	<button class='green-button'>Name asc!</button>
</form>
<table id='rounded-corner' width=50%>
	<% 	
	DBBiosystem system = (DBBiosystem)request.getAttribute("model");
	String col = (String)request.getParameter("name");
	if (col == null)
		list = system.getAllClasses(); 
	else
		list = system.getAllClassesOrderBy(col);
	%>
	<thead>
	<tr>
		<th scope='col' class='rounded-company'>Id</th>
		<th scope='col'>Name</th>
		<th scope='col'>Parent id</th>
		<th scope='col' class='rounded'></th>
	</tr>
	</thead>
	<% for (BioClass l : list) { %>
		<tr>
			<td><%= l.getId() %></td>
			<td><%= l.getName() %></td>
			<td><%= l.getParentId() %></td>
			<td>
			<form action='editclass' method='post'>	
				<input type="hidden" name="id" value=<%= l.getId() %> />
				<button class='green-button'>Edit!</button>
			</form>
			<form action='handleUpdate' method='post'>	
				<input type="hidden" name="id" value=<%= l.getId() %> />
				<input type="hidden" name="delete_class" />
				<button class='green-button'>Delete!</button>
			</form>
			</td>
		</tr>
	<%} %>
	</table>
</body>
</html>