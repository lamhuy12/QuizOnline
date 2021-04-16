/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.servlet;

import huyvl.choice.ChoiceDAO;
import huyvl.question.CreateQuestionErr;
import huyvl.question.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HUYVU
 */
@WebServlet(name = "UpdateDetailQuestionServlet", urlPatterns = {"/UpdateDetailQuestionServlet"})
public class UpdateDetailQuestionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String lastSearchValue = "";
        String lastSearchSubject = "";
        String lastSearchStatus = "";

        String questionID = request.getParameter("txtQuestionIDUpdate");
        String questionName = request.getParameter("txtQuestionNameUpdate");
        String answer1 = request.getParameter("1");
        String answer2 = request.getParameter("2");
        String answer3 = request.getParameter("3");
        String answer4 = request.getParameter("4");
        String correctAnswer = request.getParameter("cbCorrectAnswer");
        String subjectID = request.getParameter("cbSubjectUpdate");
        String status = request.getParameter("chkStatusUpdate");
        boolean checkStatus = false;
        if (status == null) {
            checkStatus = false;
        } else {
            checkStatus = true;
        }

        String urlRewriting = "updateQuestion";
        CreateQuestionErr errors = new CreateQuestionErr();

        try {
            boolean foundErr = false;
            HttpSession session = request.getSession();
            if (questionName.trim().length() < 1 || questionName.trim().length() > 500) {
                foundErr = true;
                errors.setQuestionNameLengthErr("Chars must be from 1-500");
            }
            if (answer1.trim().length() < 1 || answer1.trim().length() > 500
                    || answer2.trim().length() < 1 || answer2.trim().length() > 500
                    || answer3.trim().length() < 1 || answer3.trim().length() > 500
                    || answer4.trim().length() < 1 || answer4.trim().length() > 500) {
                foundErr = true;
                errors.setAnswerLengthErr("Chars must be from 1-500");
            }

            if (foundErr) {
                request.setAttribute("UPDATEQUESTIONERR", errors);
            } else {
                QuestionDAO questionDAO = new QuestionDAO();
                boolean resultUpdateQuestion = questionDAO.UpdateQuestion(questionID, questionName, correctAnswer, checkStatus, subjectID);

                lastSearchValue = (String) session.getAttribute("LASTSEARCHVALUE");
                lastSearchSubject = (String) session.getAttribute("LASTSEARCHSUBJECT");
                lastSearchStatus = (String) session.getAttribute("LASTSEARCHSTATUS");

                if (resultUpdateQuestion) {
                    ChoiceDAO choiceDAO = new ChoiceDAO();
                    choiceDAO.UpdateChoice(questionID, "A", answer1, subjectID);
                    choiceDAO.UpdateChoice(questionID, "B", answer2, subjectID);
                    choiceDAO.UpdateChoice(questionID, "C", answer3, subjectID);
                    choiceDAO.UpdateChoice(questionID, "D", answer4, subjectID);
                    urlRewriting = "search?"
                            + "txtSearchByQuestionName=" + lastSearchValue
                            + "&cbSubjectID=" + lastSearchSubject
                            + "&cbStatus=" + lastSearchStatus;
                    if (lastSearchValue.isEmpty() && lastSearchStatus.isEmpty() && lastSearchSubject.isEmpty()) {
                        urlRewriting ="loadquestion";
                    }
                }
            }
        } catch (SQLException ex) {
            log("UpdateQuestionServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateQuestionServlet_Naming: " + ex.getMessage());
        } finally {
//            response.sendRedirect(urlRewriting);
            RequestDispatcher rd = request.getRequestDispatcher(urlRewriting);
            rd.forward(request, response);
            out.close();
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
