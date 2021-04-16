<%-- 
    Document   : resultQuiz
    Created on : Feb 3, 2021, 5:06:17 PM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Page</title>
    </head>
    <body>
        <c:set var="fullname" value="${sessionScope.FULLNAME}" />
        <c:if test="${not empty fullname}" >
            <font color="red" >
            Welcome, ${fullname}
            </font>
        </c:if>

        <form action="logout">
            <input type="submit" value="LogOut" />
        </form>

        <a href="student.jsp">Click to take exam again</a> <br/>
        
        <c:set var="score" value="${requestScope.SCORE}" />
        <c:if test="${not empty score}" >
            Your score is <font color="red">
                ${score}
            </font> /10.0
        </c:if>
        
    </body>
</html>
