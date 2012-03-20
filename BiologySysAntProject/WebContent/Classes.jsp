<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="biosys.model.*" import="java.util.*" %>
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
<table id='rounded-corner' width=50%>
    <%  
        BiosystemDAO bioSystem = (BiosystemDAO)request.getAttribute("model");
        Map<String, String[]> map = request.getParameterMap();   
        String col;
        if (map.containsKey("col") && !map.get("col")[0].isEmpty())
                col = (String)request.getParameter("col");
        else 
            col = null;
        
        List<String> constr = new LinkedList<String>();
        if (map.containsKey("nameSubstr") && !map.get("nameSubstr")[0].isEmpty()) {
            
            constr.add("substr: " + "name " + map.get("nameSubstr")[0]);
        }
        if (map.containsKey("parentMin") && map.containsKey("parentMax") && 
                !map.get("parentMin")[0].isEmpty() && !map.get("parentMax")[0].isEmpty()) {
            
            constr.add("between: " + "parent " + map.get("parentMin")[0] + 
                    " " + map.get("parentMax")[0]);
        }
        list = bioSystem.getAllClassesConstraint(col, constr);
    %>
    <thead>
    <tr>
        <th scope='col' class='rounded-company'>Id</th>
        <th scope='col'>Name</th>
        <th scope='col'>Parent id</th>
        <th scope='col' class='rounded'></th>
    </tr>
    </thead>
    <form action="classes" method="post" >
    <tr>
        <td></td>
        <td>
            <input type="radio" name="col" onClick='this.form.submit()' value="name" />Ascending<br>
            <input type="radio" name="col" onClick='this.form.submit()' value="name desc" />Descending
        </td>
        <td>
            <input type="radio" name="col" onClick='this.form.submit()' value="parent" />Ascending<br>
            <input type="radio" name="col" onClick='this.form.submit()' value="parent desc" />Descending
        </td>
        <td><input type="hidden" name="col" value='${param.col }' /></td>
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
                <input type="hidden" name="action" value="deleteClassAction" />
                <button class='green-button'>Delete!</button>
            </form>
            </td>
        </tr>
    <%} %>
    <tfoot>
        <tr>
            <td colspan="8" class="rounded-foot-left"><em>The above data were fictional and made up, please do not sue me</em></td>
            <td class="rounded-foot-right">&nbsp;</td>
        </tr>
    </tfoot>
    </table>
</body>
</html>