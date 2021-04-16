/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.servlet;

import huyvl.choice.ChoiceDAO;
import huyvl.question.CreateQuestionErr;
import huyvl.question.QuestionDAO;
import java.awt.Choice;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HUYVU
 */
@WebServlet(name = "CreateNewQuestionServlet", urlPatterns = {"/CreateNewQuestionServlet"})
public class CreateNewQuestionServlet extends HttpServlet {

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

        String questionID = request.getParameter("txtQuestionIDCreate");
        String questionName = request.getParameter("txtQuestionNameCreate");
        String subjectID = request.getParameter("cbSubjectIDCreate");
        String answer1 = request.getParameter("txtAnswer1");
        String answer2 = request.getParameter("txtAnswer2");
        String answer3 = request.getParameter("txtAnswer3");
        String answer4 = request.getParameter("txtAnswer4");
        String correctAnswer = request.getParameter("cbCorrectAnswerCreate");
        String url = "createNewQuestionPage";

        CreateQuestionErr errors = new CreateQuestionErr();

        try {
            boolean foundErr = false;
            if (questionID.trim().length() < 1 || questionID.trim().length() > 50) {
                foundErr = true;
                errors.setQuestionIDLengthErr("Chars must be from 1-50");
            }
            if (questionName.trim().length() < 1 || questionName.trim().length() > 500) {
                foundErr = true;
                errors.setQuestionNameLengthErr("Chars must be from 1-500");
            }
            if (answer1.trim().length() < 1 || answer1.trim().length() > 500
                    || answer2.trim().length() < 1 || answer2.trim().length() > 500
                    || answer3.trim().length() < 1 || answer3.trim().length() > 500
                    || answer4.trim().length() < 1 || answer4.trim().length() > 500) {
                foundErr = true;
                errors.setAnswerLengthErr("Answer chars must be from 1-500");
            }

            if (foundErr) {
                request.setAttribute("CREATEQUESTIONAERR", errors);
            } else {
                QuestionDAO questionDAO = new QuestionDAO();
                ChoiceDAO choiceDAO = new ChoiceDAO();
                boolean createQuestion = questionDAO.createQuestion(questionID, questionName, correctAnswer, false, subjectID);
                if (createQuestion) {
                    boolean createChoice1 = choiceDAO.createChoice(questionID, "A", answer1, subjectID);
                    boolean createChoice2 = choiceDAO.createChoice(questionID, "B", answer2, subjectID);
                    boolean createChoice3 = choiceDAO.createChoice(questionID, "C", answer3, subjectID);
                    boolean createChoice4 = choiceDAO.createChoice(questionID, "D", answer4, subjectID);
                    if (createChoice1 & createChoice2 & createChoice3 & createChoice4) {
                        url = "loadquestion";
                    }                    
                }
            }

        } catch (SQLException ex) {
            String errMsg = ex.getMessage();
            log("CreateNewQuestionServlet _ SQL: " + errMsg);
            if (errMsg.contains("duplicate")) {
                errors.setQuestionIDIsExist(questionID + " is Existed!!!");
                request.setAttribute("CREATEQUESTIONAERR", errors);
            }
        } catch (NamingException ex) {
            log("CreateNewQuestionServlet_Naming: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
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
