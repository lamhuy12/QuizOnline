/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.servlet;

import huyvl.choice.ChoiceDTO;
import huyvl.question.QuestionDAO;
import huyvl.question.QuestionDTO;
import huyvl.quiz.QuizDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
@WebServlet(name = "FinishQuizServlet", urlPatterns = {"/FinishQuizServlet"})
public class FinishQuizServlet extends HttpServlet {

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

        String finishQuizID = "";
        String radio = "";
        String subjectID = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        int countCorrect = 0;
        String url = "error";
        int countQuestion =0;

        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("EMAIL");
            HashMap<String, List<ChoiceDTO>> finishQuizAnswer = (HashMap<String, List<ChoiceDTO>>) session.getAttribute("GETQUIZANSWER");
            List<QuestionDTO> finishQuizQuestion = (List<QuestionDTO>) session.getAttribute("GETQUIZQUESTION");

            for (QuestionDTO questionDTO : finishQuizQuestion) {
                finishQuizID = questionDTO.getQuestionID();
                radio = request.getParameter(finishQuizID);
                subjectID = questionDTO.getSubjectID();
                countQuestion++;
                
                QuestionDAO questionDAO = new QuestionDAO();
                String correctAnswer = questionDAO.CorrectAnswer(finishQuizID);
                
                if (radio != null) {
                    if (correctAnswer.equals(radio)) {
                        countCorrect++;
                    }
                }
            }
            float average = 10/countQuestion;
            QuizDAO quizDAO = new QuizDAO();
            boolean resultQuiz = quizDAO.finishQuiz(df.format(new Date()), subjectID, email, (float) (countCorrect * average), new Date());
            if (resultQuiz) {
                url = "resultQuizPage";
                request.setAttribute("SCORE", countCorrect*average);
            }

        } catch (SQLException ex) {
            log("FinishQuizServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("FinishQuizServlet_Naming: " + ex.getMessage());
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
