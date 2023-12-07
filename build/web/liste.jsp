<%-- 
    Document   : liste
    Created on : 19 août 2023, 10:49:31
    Author     : RANTO
--%>

<%@page import="dao.UpDateNomRoomDao"%>
<%@page import="modele.JoueurModele"%>
<%@page import="java.util.Random"%>
<%@page import="modele.RoomModele"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.ListeDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
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
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/f/w3.css">
        <link rel="stylesheet" href="css/list.css">
    </head>
    <body>
            <%
        ListeDao list = new ListeDao();
        UpDateNomRoomDao go=new UpDateNomRoomDao();
        List<RoomModele> listroom=new ArrayList<RoomModele>();
        List<JoueurModele> listjoueur=new ArrayList<JoueurModele>();
        listroom=list.listeRoom();
        String[] couleur = {"#f44336", "#2196f3", "#ffeb3b", "#4caf50"};
        Random hasard=new Random();
        int i;
        
%> 
    <div class="cont">
        <h3 class="titre">Rejoindre une partie</h3>
        <div class="list">
            <%for(int a=listroom.size()-1;a!=-1;a--){
                RoomModele room=listroom.get(a);
                i=hasard.nextInt(4);
            %>
            <div class="partie" style="background-color: <%out.print(couleur[i]);%>;">
                
                <form  class="form" action="AuthentificationServelet" method="post">
                    <p class="nom"><%out.print(room.nom);%></p>
                    <p style="text-align: center;margin-bottom: 1%;"><span style="border-bottom:5px solid #ffff;">Listes joueurs</span></p>
                    <div class="joueur">
                        
                    <% String[] c={"#2196f3", "#4caf50","#f44336", "#ffeb3b"};
                       listjoueur=list.listJoueur(room.nom);  
                        for(int ii=0;ii!=4;ii++){
                            if(listjoueur.size()>ii){
                                out.print("<div class='nomJ' style='background-color: "+c[ii]+";'>"+listjoueur.get(ii).pseudo+"</div>");
                            }else{
                                out.print("<div class='nomJ' style='background-color: "+c[ii]+";'></div>");
                            }
                        }
                    %>
                    </div>
                    <% boolean enJeu=go.goStart(room.nom);
                    if(enJeu==false){%>
                        <input type="text" name="nom" value="<%out.print(room.nom);%>" readonly style="display: none;">
                        <input style="text-align: center;" class="inp" type="password" name="mdp" placeholder="entrer le mot de passe rejoindre la partie">
                        <button type="submit" name="a" class="go w3-button" style="background-color: <%out.print(couleur[i]);%>;position: relative;left:214px;">valider</button>
                        <button type="submit" name="z" class="w3-button">  </button>
                    <%}else{%>
                        <input type="text" name="nom" value="<%out.print(room.nom);%>" readonly style="display: none;">
                        <button type="submit" name="z" class="w3-button">  </button>
                        <p class="k">la patie est déjâ terminé ou déjâ en jeu</p>
                    <%}%>
                </form>
            </div>
            <%}%>
        </div>
    </div>
    </body>
</html>
