<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="Error.jsp"
    import="biosys.model.*" import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hierarchy classes navigation</title>
<style type="text/css">
<!--
@import url("style.css");
-->
</style>
<jsp:useBean id="classesHierarchy" type="java.util.List<biosys.model.BioClass>" scope="session" />
</head>
<body>
<h2><a href="main" >Main</a></h2>
<table id='rounded-corner' width=40%>
    <thead>
    <tr>
        <th scope='col' class='rounded-company'>Id</th>
        <th scope='col'>Name</th>
        <th scope='col'>Parent id</th>
        <th scope='col' class='rounded'>
            <form action="handleUpdate" method="post" >
                <input type="hidden" name="action" value="getClassesHierarchyAction" />
                <button class='green-button'>Full tree!</button>
            </form>
        </th>
    </tr>
    </thead>
    <c:forEach items="${classesHierarchy}" var="element">
        <tr>
            <td>${element.id}</td>
            <td><a href='xmlclass?id=${element.id }'>${element.name }</a></td>
            <td>${element.parentId}</td>
            <td>
            <form action='handleUpdate' method='post'> 
                <input type="hidden" name="id" value='${element.id }' />
                <input type="hidden" name="action" value="getClassesHierarchyAction" />
                <button class='green-button'>Get children!</button>
            </form>
            </td>
        </tr>
    </c:forEach>
    </table>
</body>
</html>