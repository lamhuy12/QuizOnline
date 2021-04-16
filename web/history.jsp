<%-- 
    Document   : history
    Created on : Feb 3, 2021, 4:28:44 PM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
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

        <a href="student">Click to take exam again</a> <br/>
        
        <h1>History</h1>
        <form action="searchHistory">
            subjectName: <input type="text" name="txtSearchSubjectNameHistory" value="${param.txtSearchSubjectNameHistory}" /> <br/>
            <input type="submit" value="Search" />
        </form>

        <c:set var="searchHistory" value="${requestScope.HISTORY}" />
        <c:set var="searchValue" value="${param.txtSearchSubjectNameHistory}"/>
        <c:if test="${not empty searchValue}" >
            <c:if test="${not empty searchHistory}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>quizID</th>
                            <th>SubjectID</th>
                            <th>Email</th>
                            <th>Total</th>
                            <th>Time</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="detail" items="${searchHistory}" varStatus="counter">
                            <tr>
                                <th>
                                    ${counter.count}
                                </th>
                                <th>
                                    ${detail.quizID}
                                </th>
                                <th>
                                    ${detail.subjectID}
                                </th>
                                <th>
                                    ${detail.email}
                                </th>
                                <th>
                                    ${detail.total}
                                </th>
                                <th>
                                    ${detail.createDate}
                                </th>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
        </c:if>
        <c:if test="${not empty requestScope.ERRORHISTORY}">${requestScope.ERRORHISTORY}</c:if>

    </body>
</html>
