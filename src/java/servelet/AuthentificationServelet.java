/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelet;

import dao.AuthentificationDao;
import dao.SuppressionDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.JoueurModele;
import modele.RoomModele;

/**
 *
 * @author RANTO
 */
public class AuthentificationServelet extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String nom=request.getParameter("nom");
            String motDePasse=request.getParameter("mdp");
            if(request.getParameter("z")==null){
                RoomModele room = new RoomModele();
                room.setNom(nom);
                room.setMotdepasse(motDePasse);
                AuthentificationDao dao = new AuthentificationDao();
                boolean verif = dao.verificationUser(room);
                if(verif == true){
                    int nombre=dao.nbJoueur(nom);
                    if(nombre<4){
                        dao.insertionJoueur(nom, nombre);
                        HttpSession session= request.getSession();
                        session.setAttribute("id",nombre);
                        //session.getAttribute("id");
                        session.setAttribute("room",nom);
                        session.setAttribute("idHist","0");
                        session.setAttribute("listJ", "");
                        
                        //request.getRequestDispatcher("/room.jsp").forward(request, response); 
                        //response.sendRedirect("room.jsp");
                        response.sendRedirect("RoomServlet");
                    }else{
                        response.sendRedirect("RejoindreServelets");
                        //request.getRequestDispatcher("/liste.jsp").forward(request, response);
                    }
                }else{
                    request.getRequestDispatcher("/liste.jsp").forward(request, response);
                }
            }else if(request.getParameter("a")==null) {//suppression
                SuppressionDao s=new SuppressionDao();
                s.deleteTable(nom);
                request.getRequestDispatcher("/liste.jsp").forward(request, response);
            }
            
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AuthentificationServelet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AuthentificationServelet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
