<%-- 
    Document   : takeQuiz
    Created on : Feb 3, 2021, 2:14:33 PM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Take Quiz</title>
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

        <h1>Your Quiz</h1>    
        <c:set var="quizQuestion" value="${sessionScope.GETQUIZQUESTION}" />
        <c:set var="quizAnswer" value="${sessionScope.GETQUIZANSWER}" />
        
        Full time: <p id="duration">1</p> minutes
        <p id="time"></p>

        <c:if test="${not empty quizQuestion}" >
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Question</th>
                    </tr>
                </thead>
                <tbody>
                <form action="finishQuiz" id="finishQuiz">
                    <c:forEach var="getQuestion" items="${quizQuestion}" varStatus="counterQuestion">
                        <tr>
                            <td>
                                ${counterQuestion.count}
                            </td>
                            <td>
                                ${getQuestion.questionName}</br>
                                <c:set var="id" value="${getQuestion.questionID}"/>
                                <c:forEach var="getAnswer" items="${quizAnswer[id]}"> 
                                    </br>
                                    <input type="radio" name="${id}" value="${getAnswer.choiceID}" />
                                    ${getAnswer.answer} 
                                </c:forEach>
                                <input type="hidden" name="txtTakeQuizQuestionID" value="${id}" />
                                
                            </td>
                        </tr>
                    </c:forEach>
                    <input type="submit" value="Finish" />
                </form>
            </tbody>
        </table>
    </c:if>

    <script>
        function Time(time, element) {
            let timer = time;
            let minutes, seconds;

            let x = setInterval(() => {
                minutes = parseInt(timer / 60, 10);
                seconds = parseInt(timer % 60, 10);

                minutes = minutes < 10 ? "0" + minutes : minutes;
                seconds = seconds < 10 ? "0" + seconds : seconds;

                element.textContent = "Time remaining: " + minutes + ":" + seconds;

                if (--timer <0 ) {
                    clearInterval(x);
                    document.getElementById("finishQuiz").submit();
                }
            }, 1000);
        }
        
        window.onload = function() {
            let duration = document.getElementById("duration").textContent.trim();
            let minutes = parseInt(duration * 60, 10);
            let element = document.getElementById("time");

            Time(minutes,element);
        }
    </script>
</body>
</html>
