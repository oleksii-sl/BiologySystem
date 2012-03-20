<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="Error.jsp"
    import="biosys.model.*" import="java.util.List" %>
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
</head>
<body>
<h2><a href="main" >Main</a></h2>
<table id='rounded-corner' width=50%>
    <%  
        List<BioClass> list;
        BiosystemDAO bioSystem = (BiosystemDAO)request.getAttribute("model");
        String level = request.getParameter("level");
        if (level == null || level.isEmpty()) {
            list = bioSystem.getAllClasses();
        } else {
            list = bioSystem.getClassesByLevel(Integer.parseInt(level));
        }
    %>
    <thead>
    <tr>
        <th scope='col' class='rounded-company'>Id</th>
        <th scope='col'>Name</th>
        <th scope='col'>Parent id</th>
        <th scope='col' class='rounded'></th>
    </tr>
    </thead>
    <tr>
    <td scope='col' colspan = 4>
        <form action='hierarchy'>
            Input hierarchy level:
            <input type="text" name="level" size="10" />
            <button class='green-button'>Get classes</button>
        </form>
    </td>
    </tr>
    <% for (BioClass l : list) { %>
        <tr>
            <td><%= l.getId() %></td>
            <td><a href=<%= "xmlclass?id=" + l.getId()%> ><%= l.getName() %></a></td>
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