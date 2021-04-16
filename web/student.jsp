<%-- 
    Document   : student
    Created on : Jan 25, 2021, 1:31:52 PM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Page</title>
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

        <a href="historyPage">History</a>
        
        <h1>Choose subject you want to do quiz</h1>

        <c:set var="subject" value="${sessionScope.LISTSUB}" />
        <c:if test="${not empty subject}" >
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>SubjectID</th>
                        <th>Subject</th>
                        <th>Take quiz</th>
                    </tr>
                </thead>
                <tbody>                         
                <form action="takeQuiz">  
                    <c:forEach var="getSub" items="${subject}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${getSub.subjectID}
                            </td>
                            <td>
                                ${getSub.name}
                            </td>
                            <td>
                                <input type="submit" value="Take quiz" />
                                <input type="hidden" name="txtQuizSubjectID" value="${getSub.subjectID}" />
                            </td>
                        </tr>
                    </c:forEach>
                </form> 
            </tbody>
        </table>

    </c:if>
</body>
</html>
