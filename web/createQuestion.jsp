<%-- 
    Document   : createQuestion
    Created on : Jan 29, 2021, 12:51:46 PM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create question</title>
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

        <h1>Create question</h1>
        <form action="createNewQuestion" method="POST">
            <c:set var="errors" value="${requestScope.CREATEQUESTIONAERR}" />

            Subject: <select name="cbSubjectIDCreate">
                <c:forEach var="subject" items="${sessionScope.LISTSUB}">
                    <option value="${subject.subjectID}">${subject.name}</option>
                </c:forEach>
            </select> <br/>
            QuestionID: <input type="text" name="txtQuestionIDCreate" value="${param.txtQuestionIDCreate}" /> <br/>
            <c:if test="${not empty errors.questionIDLengthErr}" >
                <font color ="red">
                ${errors.questionIDLengthErr}
                </font> </br>
            </c:if>
            <c:if test="${not empty errors.questionIDIsExist}" >
                <font color ="red">
                ${errors.questionIDIsExist}
                </font> </br>
            </c:if>
            QuestionName: <input type="text" name="txtQuestionNameCreate" value="${param.txtQuestionNameCreate}" /> <br/>
            <c:if test="${not empty errors.questionNameLengthErr}" >
                <font color ="red">
                ${errors.questionNameLengthErr}
                </font> </br>
            </c:if>
            A: <input type="text" name="txtAnswer1" value="${param.txtAnswer1}" /> <br/>
            B: <input type="text" name="txtAnswer2" value="${param.txtAnswer2}" /> <br/>
            C: <input type="text" name="txtAnswer3" value="${param.txtAnswer3}" /> <br/>
            D: <input type="text" name="txtAnswer4" value="${param.txtAnswer4}" /> <br/>
            <c:if test="${not empty errors.answerLengthErr}" >
                <font color ="red">
                ${errors.answerLengthErr}
                </font> </br>
            </c:if>
            CorrectAnswer: <select name="cbCorrectAnswerCreate">
                <option value="A">A</option>
                <option value="B">B</option>
                <option value="C">C</option>
                <option value="D">D</option>
            </select> <br/>
            <input type="submit" value="Create new question" />
        </form>
        <a href="loadquestion">Back to search</a>
    </body>
</html>
