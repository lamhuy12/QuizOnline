/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.servlet;

import huyvl.registration.RegistrationDAO;
import huyvl.subject.SubjectDAO;
import huyvl.subject.SubjectDTO;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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

        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String url = "invalid";

        try {
            RegistrationDAO resgistrationDAO = new RegistrationDAO();
            String role = resgistrationDAO.checkLogin(email, password);

            HttpSession session = request.getSession();
            session.setAttribute("ROLE", role);
            session.setAttribute("EMAIL", email);

            if (role.equals("admin")) {
                url = "admin";
                String fullname = resgistrationDAO.getFullname(email);
                session.setAttribute("FULLNAME", fullname);
                SubjectDAO subDAO = new SubjectDAO();
                List<SubjectDTO> listSub = subDAO.getListSubject();

                if (listSub.isEmpty()) {
                    url = "error";
                } else {
                    url = "loadquestion";
                    session.setAttribute("LISTSUB", listSub);
                }
                
            } else if (role.equals("student")) {
                url = "student";
                String fullname = resgistrationDAO.getFullname(email);
                System.out.println("fullnameStu: " + fullname);
                session.setAttribute("FULLNAME", fullname);

                SubjectDAO subDAO = new SubjectDAO();
                List<SubjectDTO> listSub = subDAO.getListSubject();

                if (listSub.isEmpty()) {
                    url = "error";
                } else {
                    url = "student";
                    session.setAttribute("LISTSUB", listSub);
                }
            }
        } catch (SQLException ex) {
            log("LoginServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("LoginServlet_Naming: " + ex.getMessage());
        } catch (Exception ex) {
            log("LoginServlet_Exception: " + ex.getMessage());
        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
            response.sendRedirect(url);
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
