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
<jsp:useBean id="model" type="biosys.model.DBBiosystem" scope="request"/>
<form action='handleUpdate' method='post'>
<table id='rounded-corner' width=40%>
	<%  pageContext.setAttribute("living", model.getLiving(Integer.parseInt(request.getParameter("id")))); %>
	<thead>
	<tr>
		<th scope='col' class='rounded-company' colspan = 2>Edit living attributes and push Update</th>
	</tr>
	</thead>
	<tr>
		<td>Name</td>
		<td><input type='text' name='name' value='${living.name }' /></td>
	</tr>
	<tr>
		<td>Name latin</td>
		<td><input type='text' name='nameLatin' value='${living.nameLatin }' /></td>
	</tr>
	<tr>
		<td>Lifespan</td>
		<td><input type='text' name='lifespan' value='${living.lifespan }' /></td>
	</tr>
	<tr>
		<td>Average weight (kg)</td>
		<td><input type='text' name='avgWeight' value='${living.avgWeight }' /></td>
	</tr>
	<tr>
		<td>Native range</td>
		<td><input type='text' name='nativeRange' value='${living.nativeRange }' /></td>
	</tr>
	<tr>
		<td>Population</td>
		<td><input type='text' name='population' value='${living.population}' /></td>
	</tr>
	<tr>
		<td>Biological class id</td>
		<td><input type='text' name='bioClass' value='${living.bioClass }' /></td>
	</tr>
</table>
<input type='hidden' name='id' value = '${param.id }' />
<input type='hidden' name='update_living' />
<button class="green-button">Update</button>
</form>
</body>
</html>