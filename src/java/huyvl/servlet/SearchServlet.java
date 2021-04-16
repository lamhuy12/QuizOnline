/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.servlet;

import huyvl.question.QuestionAnswerDTO;
import huyvl.question.QuestionDAO;
import huyvl.question.QuestionDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

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
        
        String searchQuestionName = request.getParameter("txtSearchByQuestionName");
        String subID = request.getParameter("cbSubjectID");
        String status = request.getParameter("cbStatus");
        boolean checkStatus = false;
        if (status.equals("DeActive")) {
            checkStatus = false;
        } else if (status.equals("Active")) {
            checkStatus = true;
        }
        
        String url = "admin";
        
        try {
            List<QuestionDTO> resultSearch = null;
            QuestionDAO questionDAO = new QuestionDAO();
            if (!searchQuestionName.trim().isEmpty() && (subID.equals("Subject") && (status.equals("Status")))) {
                questionDAO.searchByName(searchQuestionName);
                resultSearch = questionDAO.getListSearchByName();
                url = "admin";
            } else if (!searchQuestionName.trim().isEmpty() && (!subID.trim().isEmpty()) && (status.equals("Status"))) {
                questionDAO.searchByNameSubID(searchQuestionName, subID);
                resultSearch = questionDAO.getListSearchByNameSubID();
                System.out.println("result: " + resultSearch);
                url = "admin";
            } else if (!searchQuestionName.trim().isEmpty() && (subID.equals("Subject")) && (!status.trim().isEmpty())) {
                questionDAO.searchByNameStatus(searchQuestionName, checkStatus);
                resultSearch = questionDAO.getListSearchByNameStatus();
                url = "admin";
            } else if (searchQuestionName.trim().isEmpty() && (!subID.trim().isEmpty()) && (status.equals("Status"))) {
                questionDAO.searchBySubject(subID);
                resultSearch = questionDAO.getListSearchBySubject();
                url = "admin";
            } else if (searchQuestionName.trim().isEmpty() && (!subID.equals("Subject")) && (!status.trim().isEmpty())) {
                questionDAO.searchBySubIDStatus(subID, checkStatus);
                resultSearch = questionDAO.getListSearchBySubIDStatus();
                url = "admin";
            } else if (searchQuestionName.trim().isEmpty() && (subID.equals("Subject")) && (!status.trim().isEmpty())) {
                questionDAO.searchByStatus(checkStatus);
                resultSearch = questionDAO.getListSearchByStatus();
                url = "admin";
            } else {
                questionDAO.searchByNameSubIDStatus(searchQuestionName, subID, checkStatus);
                resultSearch = questionDAO.getListSearchByNameSubIDStatus();
                url = "admin";
            }
            if (resultSearch != null) {
                request.setAttribute("RESULTSEARCH", resultSearch);
            } else {
                request.setAttribute("ERROR", "No records to match");
            }
            
            request.setAttribute("SUBJECTID", subID);
            request.setAttribute("STATUS", status);
        } catch (SQLException ex) {
            log("SearchServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchServlet_Naming: " + ex.getMessage());
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
