/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.servlet;

import huyvl.choice.ChoiceDAO;
import huyvl.choice.ChoiceDTO;
import huyvl.question.QuestionDAO;
import huyvl.question.QuestionDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "TakeQuizServlet", urlPatterns = {"/TakeQuizServlet"})
public class TakeQuizServlet extends HttpServlet {

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

        String subjectID = request.getParameter("txtQuizSubjectID");
//        String questionID = request.getParameter("txtQuizQuestionID");
        String questionId = "";
        String url = "error";

        try {
            HttpSession session = request.getSession();
            HashMap<String, List<ChoiceDTO>> getQuizAnswer = new HashMap<>();
            QuestionDAO questionDAO = new QuestionDAO();
            questionDAO.getQuestionBySubjectID(subjectID);
            List<QuestionDTO> quizQuestion = questionDAO.getQuizQuestion();
            url = "takeQuizPage";
            session.setAttribute("GETQUIZQUESTION", quizQuestion);

//            ChoiceDAO choiceDAO = new ChoiceDAO();
//            choiceDAO.searchQuizQuestionID(subjectID);
//            List<ChoiceDTO> questionID = choiceDAO.getQuizQuestionID();
            for (QuestionDTO questionDTO : quizQuestion) {
                ChoiceDAO choiceDAO = new ChoiceDAO();
                questionId = questionDTO.getQuestionID();
                choiceDAO.searchQuizAnswer(questionId, subjectID);
                List<ChoiceDTO> quizAnswer = choiceDAO.getQuizAnswer();
                if(quizAnswer !=null){
                    getQuizAnswer.put(questionId, quizAnswer);
                }
            }
//            request.setAttribute("QUESTIONID", quizQuestion);
            session.setAttribute("GETQUIZANSWER", getQuizAnswer);

        } catch (SQLException ex) {
            log("TakeQuizServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("TakeQuizServlet_Naming: " + ex.getMessage());
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
        processRequest(request, response);    }

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
