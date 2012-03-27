<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="Error.jsp"
    import="biosys.model.*" import="java.util.*" import="java.sql.SQLException" %>
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
</head>
<body>
<h2><a href="main" >Main</a></h2>
<table id='rounded-corner' width=90%>
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
    <form action="handleUpdate" method="post" >
    <input type="hidden" name="action" value="getAliveAction" />
    <tr>
        
        <td></td>
        <td>
            <input type="radio" name="ordercol" onClick='this.form.submit()' value="name" />Ascending<br>
            <input type="radio" name="ordercol" onClick='this.form.submit()' value="name desc" />Descending
        </td>
        <td>
            <input type="radio" name="ordercol" onClick='this.form.submit()' value="name_latin" />Ascending<br>
            <input type="radio" name="ordercol" onClick='this.form.submit()' value="name_latin desc" />Descending
        </td>
        <td>
            <input type="radio" name="ordercol" onClick='this.form.submit()' value="lifespan" />Ascending<br>
            <input type="radio" name="ordercol" onClick='this.form.submit()' value="lifespan desc" />Descending
        </td>
        <td>
            <input type="radio" name="ordercol" onClick='this.form.submit()' value="avg_weight" />Ascending<br>
            <input type="radio" name="ordercol" onClick='this.form.submit()' value="avg_weight desc" />Descending
        </td>
        <td>
            <input type="radio" name="ordercol" onClick='this.form.submit()' value="native_range" />Ascending<br>
            <input type="radio" name="ordercol" onClick='this.form.submit()' value="native_range desc" />Descending
        </td>
        <td>
            <input type="radio" name="ordercol" onClick='this.form.submit()' value="population" />Ascending<br>
            <input type="radio" name="ordercol" onClick='this.form.submit()' value="population desc" />Descending
        </td>
        <td>
            <input type="radio" name="ordercol" onClick='this.form.submit()' value="class" />Ascending<br>
            <input type="radio" name="ordercol" onClick='this.form.submit()' value="class desc" />Descending
        </td>
        <td><input type="hidden" name="ordercol" value='${param.ordercol }' /></td>
        
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
    <c:forEach items='${aliveList}' var="element">
        <tr>
            <td>${element.id}</td>
            <td><a href='xmlalive?id=${element.id }'>${element.name }</a></td>
            <td>${element.nameLatin}</td>
            <td>${element.lifespan }</td>
            <td>${element.avgWeight }</td>
            <td>${element.nativeRange }</td>
            <td>${element.population }</td>
            <td>${element.bioClass }</td>
            <td>
            <form action='editalive' method='post'> 
                <input type="hidden" name="id" value='${element.id }' />
                <button class='green-button'>Edit!</button>
            </form>
            <form action='handleUpdate' method='post'>  
                <input type="hidden" name="id" value='${element.id }' />
                <input type="hidden" name="action" value="deleteAliveAction" />
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