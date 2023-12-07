package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import dao.UpDateNomRoomDao;
import dao.ListeDao;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");

    HttpSession session1= request.getSession();
    boolean roomm=session1.getAttribute("room")!=null;
    if(roomm==true){
        String roomm1=String.valueOf(session1.getAttribute("room"));
        UpDateNomRoomDao daoStart =new UpDateNomRoomDao();
        if(daoStart.goStart(roomm1)==true){
            String nomRoom=String.valueOf(session1.getAttribute("room"));
            String id=String.valueOf(session1.getAttribute("id"));
            ListeDao dao= new ListeDao(); 
            dao.supprimeJour(nomRoom, id);
            session1.setAttribute("room", null);
            session1.setAttribute("id", null);
        }
    } 

      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>index</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/f/w3.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/index.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/vict.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"principe\" class=\"modal\" >\n");
      out.write("            <div class=\"modal-content animate container\" style=\"overflow-x:hidden;\">\n");
      out.write("                Les joueurs obtient au hasrd une chiffre à tour de rôle dans le sens des aiguilles d'une montre. <br>\n");
      out.write("                Le chiffre obtenue détermine le nombre de cases que le joueur peut avancer l'un de ses pions sur le plateau,<br>\n");
      out.write("                 dans le sens de sa propre couleur <br>.\n");
      out.write("                Un 6 permet de jouer un pion depuis le coin de départ ou de déplacer un pion déjà sur le plateau de 6 cases. <br>\n");
      out.write("                Si un joueur obtient un 6, il a le droit de rejouer. <br>\n");
      out.write("                Les joueurs doivent déplacer leurs pions en suivant le chemin de leur couleur respective. <br><br>\n");
      out.write("                Si un pion atterrit sur une case déjà occupée par un pion adverse,<br>\n");
      out.write("                 le pion adverse est renvoyé à son coin de départ. <br>\n");
      out.write("                 Le joueur qui a renvoyé un pion adverse peut continuer à jouer son tour. <br>\n");
      out.write("                Les joueurs doivent déplacer au moins un de leurs pions à chaque tour,<br>\n");
      out.write("                 sauf s'ils ne peuvent pas en raison de l'obstruction de leurs propres pions ou d'autres obstacles. <br><br>\n");
      out.write("                Pour entrer dans la \"maison\" (zone d'arrivée),<br>\n");
      out.write("                 un joueur doit obtenir exactement le nombre nécessaire sur le dé pour atterrir <br>\n");
      out.write("                  sur la case d'entrée de sa maison. <br>\n");
      out.write("                   Par exemple, si un joueur est à 3 cases de sa maison et obtient un 4, il ne peut pas entrer.\n");
      out.write("                Le premier joueur à déplacer tous ses pions dans sa maison gagne la partie. <br><br>\n");
      out.write("    \n");
      out.write("                Règles spéciales sur le mode en ligne:\n");
      out.write("    \n");
      out.write("                Si le joueur ne clique pas sur le boutton pendant 1min30s pour obtenir le chiffre au hasard ,il perd son tour.\n");
      out.write("                <br>Fin de la partie :\n");
      out.write("                Le premier joueur à déplacer tous ses pions dans sa maison est déclaré vainqueur.\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    <div class=\"cont\">\n");
      out.write("        <div id=\"e\" style=\"position: absolute;color: white;cursor: pointer;\"> \n");
      out.write("            <h1>?</h1>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"titre\">\n");
      out.write("            <div  style=\"color: #ff9100;\">L</div>\n");
      out.write("            <div  style=\"color: #ff0000;\">U</div>\n");
      out.write("            <div  style=\"color: #008000;\">D</div>\n");
      out.write("            <div style=\"color: #0000ff;\">O</div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"menu\">\n");
      out.write("            <div id=\"choixMenu\" class=\"partie a active\">\n");
      out.write("                <button id=\"btnOnline\"  class=\"w3-btn w3-ripple w3-blue b\">Multijoueur en ligne</button>\n");
      out.write("                <button id=\"btnLocale\" class=\"w3-btn w3-ripple w3-blue b\">Locale</button>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div id=\"online\" class=\"partie a\">\n");
      out.write("                <svg class=\"relat\" height=\"100\" width=\"50\">\n");
      out.write("                    <polygon id=\"retourOnline\" points=\"20,0 0,50 20,100\" style=\"fill:rgb(71 68 68 / 95%);\" />\n");
      out.write("                </svg>\n");
      out.write("                <a href=\"RejoindreServelet\"  class=\"w3-btn w3-ripple w3-blue b\">Rejouindre une partie</a>\n");
      out.write("                <button id=\"partie\" class=\"w3-btn w3-ripple w3-blue b\">Creé une partie</button>\n");
      out.write("            </div>\n");
      out.write("            <div id=\"creePartie\" class=\"a\">\n");
      out.write("                <h1 class=\"relat\" style=\"color: #ffffff;margin-left: 30%;top:-40%\">Crée la partie</h1>\n");
      out.write("                <svg class=\"relat\" height=\"100\" width=\"50\">\n");
      out.write("                    <polygon id=\"retourPartie\" points=\"20,0 0,50 20,100\" style=\"fill:rgb(71 68 68 / 95%);\" />\n");
      out.write("                </svg>\n");
      out.write("                <form action=\"AjoutServelet\" method=\"post\" >\n");
      out.write("                    <input type=\"text\" class=\"w3-btn w3-ripple w3-blue b\" placeholder=\"Nom de la partie\" name=\"nom\" id=\"inputNom\" required>\n");
      out.write("                    <input type=\"password\" class=\"w3-btn w3-ripple w3-blue b\" placeholder=\"Mot de passe\" name=\"mdp\" id=\"inputMDP\">\n");
      out.write("                    <button type=\"submit\" class=\"w3-btn w3-ripple w3-blue b\">Crée</button>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div id=\"locale\" class=\"partie a\">\n");
      out.write("                <svg class=\"relat\" height=\"100\" width=\"50\">\n");
      out.write("                    <polygon id=\"retourLocale\" points=\"20,0 0,50 20,100\" style=\"fill:rgb(71 68 68 / 95%);\" />\n");
      out.write("                </svg>\n");
      out.write("                <form action=\"LocalServlet\" method=\"post\" >\n");
      out.write("                    <div  style=\"color: #ffffff;display: flex;margin-left: 20%;\">\n");
      out.write("                        <h1 >Nombre des Joueurs:</h1>\n");
      out.write("                        <h1 id=\"nbJouer\" >2</h1>\n");
      out.write("                    </div>\n");
      out.write("                    <input type=\"range\" class=\"w3-ripple w3-display-middle\" name=\"num\" id=\"myRange\" value=\"2\" min=\"2\" max=\"4\">\n");
      out.write("                    <button type=\"submit\" class=\"w3-btn w3-ripple w3-blue b\">Joueur</button>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("    </div>\n");
      out.write("    <script src=\"js/f/jquery.min.js\"></script>\n");
      out.write("    <script src=\"js/index.js\"></script>\n");
      out.write("    </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
