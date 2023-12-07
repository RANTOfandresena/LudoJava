/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelet;

import dao.ListeDao;
import dao.UpDateNomRoomDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.JoueurModele;

/**
 *
 * @author RANTO
 */
public class NomUpDateRoomServlet extends HttpServlet {

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
        HttpSession session1= request.getSession();
        String nomRoom=String.valueOf(session1.getAttribute("room"));
        String listJSession=String.valueOf(session1.getAttribute("listJ"));
        ListeDao list = new ListeDao();
        List<JoueurModele> listjoueur=new ArrayList<JoueurModele>();
        String a="";
        try {
            listjoueur=list.listJoueur(nomRoom);
            String listString=listjoueur.toString();
            if(!listString.equalsIgnoreCase(listJSession)){
                a=listString;
                session1.setAttribute("listJ", listString);
            }
            String sseData ="data: "+a+"\n\n";
            response.getWriter().write(sseData);
        } catch (SQLException ex) {
            Logger.getLogger(RoomServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        UpDateNomRoomDao dao=new UpDateNomRoomDao();
        HttpSession session= request.getSession();
        String room=String.valueOf(session.getAttribute("room"));
        String id=String.valueOf(session.getAttribute("id"));
        String pseudoMod=request.getParameter("a");
        dao.updateNom(room, id, pseudoMod);
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
