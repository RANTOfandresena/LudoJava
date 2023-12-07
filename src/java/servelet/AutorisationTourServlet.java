/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelet;

import dao.HistoriquePartieDao;
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

/**
 *
 * @author RANTO
 */
public class AutorisationTourServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
        /*    HistoriquePartieDao dao=new HistoriquePartieDao();
            HttpSession session= request.getSession();
            String room=String.valueOf(session.getAttribute("room"));
            String id=String.valueOf(session.getAttribute("id"));
        
        try {
            char resultat='0';
            if(dao.autorsationClick(id, room)){
                resultat='1';
            }
            out.println(resultat);
            } catch (SQLException ex) {
                Logger.getLogger(AutorisationTourServlet.class.getName()).log(Level.SEVERE, null, ex);
            */
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
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        HistoriquePartieDao dao=new HistoriquePartieDao();
        HttpSession session= request.getSession();
        String room=String.valueOf(session.getAttribute("room"));
        String id=String.valueOf(session.getAttribute("id"));
        try {
            String resultat=dao.autorsationClick(room);
            String env=(resultat.equalsIgnoreCase(id)?"1":"0");
            String sseData ="data: "+env+","+resultat+"\n\n";
            // Écrivez les données SSE dans la réponse
            response.getWriter().write(sseData);
        } catch (SQLException ex) {
            Logger.getLogger(AutorisationTourServlet.class.getName()).log(Level.SEVERE, null, ex);
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
