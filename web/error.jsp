<%-- 
    Document   : error
    Created on : Jan 29, 2021, 10:47:08 AM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1>Something went wrong. Please try again</h1> <br/>
        <c:set var="getRole" value="${sessionScope.ROLE}" />
        <c:if test="${getRole eq 'admin'}" >
            <a href="admin.jsp">Back to search</a> <br/>
        </c:if>
        <c:if test="${getRole eq 'student'}" >
            <a href="student.jsp">take quiz</a>
        </c:if>
        
    </body>
</html>
