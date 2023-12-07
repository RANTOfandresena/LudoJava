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
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.HistoriqueModele;

/**
 *
 * @author RANTO
 */
public class ControleurLudoServlet extends HttpServlet {

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
        List<HistoriqueModele> list;
        HistoriquePartieDao dao=new HistoriquePartieDao();
        HttpSession session= request.getSession();
        String room=String.valueOf(session.getAttribute("room"));
        String idHist=String.valueOf(session.getAttribute("idHist"));
        String idJ=String.valueOf(session.getAttribute("id"));
        try {
            list=dao.nouveauActualitee(room, idHist,idJ);
            if(list.isEmpty()==false){
                idHist=list.get(list.size()-1).getId();
                session.setAttribute("idHist", idHist);
            }
            String sseData ="data: "+list.toString()+"\n\n";
            response.getWriter().write(sseData);
        } catch (SQLException ex) { 
            Logger.getLogger(ControleurLudoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        //processRequest(request, response);
        
        HistoriquePartieDao dao=new HistoriquePartieDao();
        HttpSession session= request.getSession();
        String room=String.valueOf(session.getAttribute("room"));
        String id=String.valueOf(session.getAttribute("id"));
        
        try {
            if(dao.autorsationClick(room).equalsIgnoreCase(id)){
                String mode=request.getParameter("a");
                if(mode.equalsIgnoreCase("0")){
                    Random hasard=new Random();
                    int[] listhasard={1,1,2,3,4,5,6,6,6};
                    int data=listhasard[hasard.nextInt(8)]; 
                    dao.envoyeHistorique(room, mode, id, "", data);
                }else {
                    String pion=request.getParameter("b");
                    if(mode.equalsIgnoreCase("1")){
                        dao.envoyeHistorique(room, mode, id, pion, 1);
                        dao.envoyerTableNom(room, id, pion, "1");
                    }else if(mode.equalsIgnoreCase("2")){ 
                        String depleString=request.getParameter("c");
                        int depl=Integer.parseInt(depleString);
                        dao.envoyeHistorique(room, "2", id , pion, depl);
                        dao.envoyerTableNom(room, id, pion, depleString);
                    }
                }
                response.getWriter().write(id);
                response.setStatus(HttpServletResponse.SC_OK);  
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControleurLudoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
