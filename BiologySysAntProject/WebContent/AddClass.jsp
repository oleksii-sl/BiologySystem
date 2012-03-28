<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="Error.jsp"
    import="biosys.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit living</title>
<style type="text/css">
<!--
@import url("style.css");
-->
</style>
</head>
<body>
<h2><a href="main" >Main</a></h2>
<form action='handleUpdate' method='post'>
<table id='rounded-corner' width=40%>
	<thead>
	<tr>
		<th scope='col' class='rounded-company' colspan = 2>Fill class attributes form</th>
	</tr>
	</thead>
	<tr>
		<td>Name</td>
		<td><input type='text' name='name' /></td>
	</tr>
	<tr>
		<td>Parent id</td>
		<td><input type='text' name='parentId' /></td>
	</tr>
</table>
<input type='hidden' name='action' value="addClassAction" />
<button class="green-button">Add</button>
</form>
</body>
</html>