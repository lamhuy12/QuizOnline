<%-- 
    Document   : CreateNewAccount
    Created on : Jan 25, 2021, 12:38:12 PM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <body>
        <h1>Create your own account</h1>
        <form action="createNewAccount" method="POST">
            <c:set var="errors" value="${requestScope.CREATEER}"/>

            Email: <input type="text" name="txtEmailCreate" value="${param.txtEmailCreate}" placeholder="bruce@gmail.com"/> <br/>
            <c:if test="${not empty errors.emailLengthErr}">
                <font color ="red">
                ${errors.emailLengthErr}
                </font> </br>
            </c:if>  
            <c:if test="${not empty errors.emailIsExisted}">
                <font color ="red">
                ${errors.emailIsExisted}
                </font> </br>
            </c:if>  
            <c:if test="${not empty errors.emailFormErr}">
                <font color ="red">
                ${errors.emailFormErr}
                </font> </br>
            </c:if>
            Password: <input type="password" name="txtPasswordCreate" value="${param.txtPasswordCreate}" placeholder="enter password"/> <br/>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color ="red">
                ${errors.passwordLengthErr}
                </font> </br>
            </c:if> 
            Confirm: <input type="password" name="txtConfirm" value="${param.txtConfirm}" /> </br>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color ="red">
                ${errors.confirmNotMatched}
                </font> </br>
            </c:if> 
            Fullname: <input type="text" name="txtFullnameCreate" value="${param.txtFullnameCreate}" placeholder="your name"/> <br/>
            <c:if test="${not empty errors.fullnameLengthErr}">
                <font color ="red">
                ${errors.fullnameLengthErr}
                </font> </br>
            </c:if> 
            <input type="submit" value="Create my account" />
        </form>
        <a href="login.html">Login</a>
    </body>
</html>
