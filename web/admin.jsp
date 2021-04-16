<%-- 
    Document   : admin
    Created on : Jan 25, 2021, 1:31:31 PM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
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
        </form> <br/>

        <a href="createNewQuestionPage">Create Question</a> <br/>

        <h1>Search Question</h1>    
        <form action="search">
            Question: <input type="text" name="txtSearchByQuestionName" value="${param.txtSearchByQuestionName}" /> <br/>

            Subject: <select name="cbSubjectID">
                <option>Subject</option>
                <c:forEach var="subject" items="${sessionScope.LISTSUB}">
                    <c:if test="${subject.subjectID == requestScope.SUBJECTID}">
                        <option value="${subject.subjectID}" selected>${subject.name}</option>
                    </c:if>
                    <c:if test="${subject.subjectID != requestScope.SUBJECTID}">
                        <option value="${subject.subjectID}">${subject.name}</option>
                    </c:if>
                </c:forEach>
            </select> <br/>

            Status: <select name="cbStatus">
                <option>Status</option>
                <c:if test="${requestScope.STATUS.equals('Active')}">
                    <option selected>Active</option>
                    <option>DeActive</option>
                </c:if>
                <c:if test="${requestScope.STATUS.equals('DeActive')}">
                    <option>Active</option>
                    <option selected>DeActive</option>
                </c:if>
                <c:if test="${requestScope.STATUS.equals('Status') || empty requestScope.STATUS}">
                    <option>Active</option>
                    <option>DeActive</option>
                </c:if>
            </select> <br/>
            <input type="submit" value="Search" />
        </form>

        <c:set var="resultSearch" value="${requestScope.RESULTSEARCH}" />
        <c:if test="${not empty resultSearch}" >
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>SubjectID</th>
                        <th>QuestionID</th>
                        <th>Question</th>
                        <th>DateCreate</th>
                        <th>Status</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="getListSearch" items="${resultSearch}" varStatus="counter" >
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${getListSearch.subjectID}
                            </td>
                            <td>
                                ${getListSearch.questionID}
                                <input type="hidden" name="txtQuestionID" value="${getListSearch.questionID}" />
                            </td>
                            <td>                      
                                ${getListSearch.questionName}
                            </td>
                            <td>
                                ${getListSearch.dateCreate}
                            </td>
                            <td>
                                ${getListSearch.status}
                                <input type="checkbox" name="chkStatus" value="ON"
                                       <c:if test="${getListSearch.status}">
                                           checked ="checked"
                                       </c:if>
                                       />
                            </td>
                            <td>
                                <form action="updatePage">
                                    <input type="submit" value="Update" />
                                    <input type="hidden" name="txtQuestionID" value="${getListSearch.questionID}" />  
                                    <input type="hidden" name="lastSearchValue" value="${param.txtSearchByQuestionName}" />
                                    <input type="hidden" name="lastSearchSubject" value="${param.cbSubjectID}" />
                                    <input type="hidden" name="lastSearchStatus" value="${param.cbStatus}" />
                                </form>             
                            </td>
                            <td>
                                <c:url var="urlRewriting" value="DeleteQuestionServlet" >
                                    <c:param name="pkDelete" value="${getListSearch.questionID}"/>
                                    <c:param name="lastSearchValue" value="${param.txtSearchByQuestionName}"/>
                                    <c:param name="lastSearchSubject" value="${param.cbSubjectID}" />
                                    <c:param name="lastSearchStatus" value="${param.cbStatus}" />
                                </c:url> 
                                <a href="${urlRewriting}">Delete</a>

                            </td>
                        </tr>                       
                    </c:forEach>

                </tbody>
            </table>
        </c:if>
        <c:if test="${not empty requestScope.ERROR}">${requestScope.ERROR}</c:if>

        <c:set var="get20question" value="${requestScope.LOAD20QUESTION}"/>
        <c:if test="${not empty get20question}" >
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>SubjectID</th>
                        <th>QuestionID</th>
                        <th>Question</th>
                        <th>DateCreate</th>
                        <th>Status</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="getList" items="${get20question}" varStatus="counter" >
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${getList.subjectID}
                            </td>
                            <td>
                                ${getList.questionID}
                                <input type="hidden" name="txtQuestionID" value="${getList.questionID}" />
                            </td>
                            <td>                      
                                ${getList.questionName}
                            </td>
                            <td>
                                ${getList.dateCreate}
                            </td>
                            <td>
                                ${getList.status}
                                <input type="checkbox" name="chkStatus" value="ON"
                                       <c:if test="${getList.status}">
                                           checked ="checked"
                                       </c:if>
                                       />
                            </td>
                            <td>
                                <form action="updatePage">
                                    <input type="submit" value="Update" />
                                    <input type="hidden" name="txtQuestionID" value="${getList.questionID}" />  
                                    <input type="hidden" name="lastSearchValue" value="${param.txtSearchByQuestionName}" />
                                    <input type="hidden" name="lastSearchSubject" value="${param.cbSubjectID}" />
                                    <input type="hidden" name="lastSearchStatus" value="${param.cbStatus}" />
                                </form>             
                            </td>
                            <td>
                                <c:url var="urlRewriting" value="DeleteQuestionServlet" >
                                    <c:param name="pkDelete" value="${getList.questionID}"/>
                                    <c:param name="lastSearchValue" value="${param.txtSearchByQuestionName}"/>
                                    <c:param name="lastSearchSubject" value="${param.cbSubjectID}" />
                                    <c:param name="lastSearchStatus" value="${param.cbStatus}" />
                                </c:url> 
                                <a href="${urlRewriting}">Delete</a>

                            </td>
                        </tr>                       
                    </c:forEach>

                </tbody>
            </table>

        </c:if>
    </body>
</html>
