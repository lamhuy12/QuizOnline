<%-- 
    Document   : updateQuestion
    Created on : Feb 1, 2021, 1:12:20 PM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
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

        <h1>Update question</h1>
        <form action="updateDetailQuestion">
            <c:set var="detailQuestion" value="${sessionScope.DETAILQUESTION}" />
            <c:set var="answer" value="${sessionScope.GETANSWER}" />
            <c:set var="errors" value="${requestScope.UPDATEQUESTIONERR}" />
            <c:if test="${not empty detailQuestion}" >
                <c:forEach var="get" items="${detailQuestion}" >
                    <font color="red">
                    Question ${get.questionID}: 
                    <input type="hidden" name="txtQuestionIDUpdate" value="${get.questionID}" />
                    </font>
                    <input type="text" name="txtQuestionNameUpdate" value="${get.questionName}" /> <br/>
                    <c:if test="${not empty errors.questionNameLengthErr}" >
                        <font color ="red">
                        ${errors.questionNameLengthErr}
                        </font> </br>
                    </c:if>
                    <c:forEach var="getAnswer" items="${answer}" varStatus="counter">
                        Answer: <input type="text" name="${counter.count}" value="${getAnswer.answer}"/>  <br/>  
                    </c:forEach>
                    <c:if test="${not empty errors.answerLengthErr}" >
                        <font color ="red">
                        ${errors.answerLengthErr}
                        </font> </br>
                    </c:if>
                    Status: <input type="checkbox" name="chkStatusUpdate" value="ON" 
                                   <c:if test="${get.status}">
                                       checked="checked"
                                   </c:if>                        
                                   />     <br/>
                    CorrectAnswer: 
                    <select name="cbCorrectAnswer">
                        <option value="A" <c:if test="${get.correctAnswer eq 'A'}">selected</c:if>>A</option>
                        <option value="B" <c:if test="${get.correctAnswer eq 'B'}">selected</c:if>>B</option>
                        <option value="C" <c:if test="${get.correctAnswer eq 'C'}">selected</c:if>>C</option>
                        <option value="D" <c:if test="${get.correctAnswer eq 'D'}">selected</c:if>>D</option>
                        </select> <br/>
                        Subject: <select name="cbSubjectUpdate">
                        <c:forEach var="subject" items="${sessionScope.LISTSUB}">
                            <option value="${subject.subjectID}" <c:if test="${get.subjectID eq subject.subjectID}">selected</c:if>>${subject.name}</option> 
                        </c:forEach>
                    </select> 
                </c:forEach>
            </c:if> <br/>
            <input type="submit" value="Update" /> <br/>
        </form>


        <a href="loadquestion">Back to search</a>
    </body>
</html>
