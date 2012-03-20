<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="Error.jsp"
    import="biosys.model.*" import="java.util.*" import="java.sql.SQLException" %>
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
<%!List<Alive> list;%>
<table id='rounded-corner' width=90%>
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
    	    if (map.containsKey("nameLatinSubstr") && !map.get("nameLatinSubstr")[0].isEmpty()) {
                
                constr.add("substr: " + "name_latin " + map.get("nameLatinSubstr")[0]);
            }
    	    if (map.containsKey("lifespanMin") && map.containsKey("lifespanMax") && 
    	            !map.get("lifespanMin")[0].isEmpty() && !map.get("lifespanMax")[0].isEmpty()) {
    	        
    	        constr.add("between: " + "lifespan " + map.get("lifespanMin")[0] + 
    	                " " + map.get("lifespanMax")[0]);
    	    }
    	    if (map.containsKey("avgWeightMin") && map.containsKey("avgWeightMax") && 
                    !map.get("avgWeightMin")[0].isEmpty() && !map.get("avgWeightMax")[0].isEmpty()) {
                
                constr.add("between: " + "avg_weight " + map.get("avgWeightMin")[0] + 
                        " " + map.get("avgWeightMax")[0]);
            }
    	    if (map.containsKey("rangeSubstr") && !map.get("rangeSubstr")[0].isEmpty()) {
                
                constr.add("substr: " + "native_range " + map.get("rangeSubstr")[0]);
            }
    	    if (map.containsKey("populatationMin") && map.containsKey("populatationMax") && 
                    !map.get("populatationMin")[0].isEmpty() && !map.get("populatationMax")[0].isEmpty()) {
                
                constr.add("between: " + "population " + map.get("populatationMin")[0] + 
                        " " + map.get("populatationMax")[0]);
            }
    	    list = bioSystem.getAllAliveConstraint(col, constr);
    %>
    <thead>
    <tr>
        <th scope='col' class='rounded-company'>Id<form></form></th>
        <th scope='col'>Name</th>
        <th scope='col'>Name latin</th>
        <th scope='col'>Lifespan</th>
        <th scope='col'>Average weight (kg)</th>
        <th scope='col'>Native range</th>
        <th scope='col'>Population</th>
        <th scope='col'>Biological class id</th>
        <th scope='col' class='rounded'></th>
    </tr>
    </thead>
    <form action="alive" method="post" >
    <tr>
        
        <td></td>
        <td>
            <input type="radio" name="col" onClick='this.form.submit()' value="name" />Ascending<br>
            <input type="radio" name="col" onClick='this.form.submit()' value="name desc" />Descending
        </td>
        <td>
            <input type="radio" name="col" onClick='this.form.submit()' value="name_latin" />Ascending<br>
            <input type="radio" name="col" onClick='this.form.submit()' value="name_latin desc" />Descending
        </td>
        <td>
            <input type="radio" name="col" onClick='this.form.submit()' value="lifespan" />Ascending<br>
            <input type="radio" name="col" onClick='this.form.submit()' value="lifespan desc" />Descending
        </td>
        <td>
            <input type="radio" name="col" onClick='this.form.submit()' value="avg_weight" />Ascending<br>
            <input type="radio" name="col" onClick='this.form.submit()' value="avg_weight desc" />Descending
        </td>
        <td>
            <input type="radio" name="col" onClick='this.form.submit()' value="native_range" />Ascending<br>
            <input type="radio" name="col" onClick='this.form.submit()' value="native_range desc" />Descending
        </td>
        <td>
            <input type="radio" name="col" onClick='this.form.submit()' value="population" />Ascending<br>
            <input type="radio" name="col" onClick='this.form.submit()' value="population desc" />Descending
        </td>
        <td>
            <input type="radio" name="col" onClick='this.form.submit()' value="class" />Ascending<br>
            <input type="radio" name="col" onClick='this.form.submit()' value="class desc" />Descending
        </td>
        <td><input type="hidden" name="col" value='${param.col }' /></td>
        
    </tr>
    <tr>
        <td>Id</td>
        <td>Substring:<br>
            <input type="text" name="nameSubstr" value='${param.nameSubstr }' size="12" /><br>
        </td>
        <td  >Substring:<br>
            <input type="text" name="nameLatinSubstr" value='${param.nameLatinSubstr }' size="15" />
        </td>
        <td>Min
            <input type="text" name="lifespanMin" value='${param.lifespanMin }' size="6" /> <br> 
            Max <input type="text" name="lifespanMax" value='${param.lifespanMax }' size="6" /></td>
        <td>Min
            <input type="text" name="avgWeightMin" value='${param.avgWeightMin }' size="7" /> <br> 
            Max <input type="text" name="avgWeightMax" value='${param.avgWeightMax }' size="7" /></td>
        <td>Substring:<br>
            <input type="text" name="rangeSubstr" value='${param.rangeSubstr }' size="20" />
        </td>
        <td>Min
            <input type="text" name="populatationMin" value='${param.populatationMin }' size="10" /> <br> 
            Max <input type="text" name="populatationMax" value='${param.populatationMax }' size="10" />
        </td>
        <td></td>
        <td><button class='green-button'>Refresh!</button></td>
    </tr>
    </form>
    <%
    	for (Alive l : list) {
    %>
        <tr>
            <td><%= l.getId() %></td>
            <td><a href=<%= "xmlalive?id=" + l.getId()%> > <%= l.getName() %></a></td>
            <td><%= l.getNameLatin() %></td>
            <td><%= l.getLifespan() %></td>
            <td><%= l.getAvgWeight() %></td>
            <td><%= l.getNativeRange()%></td>
            <td><%= l.getPopulation() %></td>
            <td><%= l.getBioClass() %></td>
            <td>
            <form action='editliving' method='post'>    
                <input type="hidden" name="id" value=<%= l.getId() %> />
                <button class='green-button'>Edit!</button>
            </form>
            <form action='handleUpdate' method='post'>  
                <input type="hidden" name="id" value=<%= l.getId() %> />
                <input type="hidden" name="action" value="deleteAliveAction" />
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