<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error page</title>
</head>
<body>
<h2>An error occurred!</h2>
<h3>Request that failed: ${pageContext.errorData.requestURI}</h3>
<h3>Status code: ${pageContext.errorData.statusCode}</h3>
<h3>Exception: ${pageContext.errorData.throwable} </h3>
<h2><a href="main" >Main</a></h2>
</body>
</html>