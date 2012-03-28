<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="biosys.model.*" import="java.util.*" errorPage="Error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<jsp:useBean id="classList" type="java.util.List<biosys.model.BioClass>" scope="session" />
</head>
<body>
<h2><a href="main" >Main</a></h2>
<table id='rounded-corner' width=50%>
    <thead>
    <tr>
        <th scope='col' class='rounded-company'>Id</th>
        <th scope='col'>Name</th>
        <th scope='col'>Parent id</th>
        <th scope='col' class='rounded'></th>
    </tr>
    </thead>
    <form action="handleUpdate" method="post" >
    <input type="hidden" name="action" value="getClassesAction" />
    <tr>
        <td></td>
        <td>
            <input type="radio" name="ordercol" onClick='this.form.submit()' value="name" />Ascending<br>
            <input type="radio" name="ordercol" onClick='this.form.submit()' value="name desc" />Descending
        </td>
        <td>
            <input type="radio" name="ordercol" onClick='this.form.submit()' value="parent" />Ascending<br>
            <input type="radio" name="ordercol" onClick='this.form.submit()' value="parent desc" />Descending
        </td>
        <td><input type="hidden" name="ordercol" value='${param.col }' /></td>
    </tr>
    <tr>
        <td>Id</td>
        <td>Substring:<br>
            <input type="text" name="nameSubstr" value='${param.nameSubstr }' size="12" /><br>
        </td>
        <td>
            Min: <input type="text" name="parentMin" value='${param.parentMin }' size="5" /><br>
            Max: <input type="text" name="parentMax" value='${param.parentMax }' size="5" />
        </td>
        <td><button class='green-button'>Refresh!</button></td>
    </tr>
    </form>
    <c:forEach items='${classList}' var='element'>
        <tr>
            <td>${element.id}</td>
            <td><a href='xmlclass?id=${element.id }'>${element.name }</a></td>
            <td>${element.parentId}</td>
            <td>
            <form action='editclass' method='post'> 
                <input type='hidden' name='id' value='${element.id }' />
                <button class='green-button'>Edit!</button>
            </form>
            <form action='handleUpdate' method='post'>  
                <input type='hidden' name='id' value='${element.id }' />
                <input type='hidden' name='action' value='deleteClassAction' />
                <button class='green-button'>Delete!</button>
            </form>
            </td>
        </tr>
    </c:forEach>
    <tfoot>
        <tr>
            <td colspan="8" class="rounded-foot-left"><em>The above data were fictional and made up, please do not sue me</em></td>
            <td class="rounded-foot-right">&nbsp;</td>
        </tr>
    </tfoot>
    </table>
</body>
</html>