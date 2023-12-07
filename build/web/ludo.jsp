<%-- 
    Document   : ludo
    Created on : 21 août 2023, 17:56:24
    Author     : RANTO
--%>

<%@page import="java.util.List"%>
<%@page import="modele.JoueurModele"%>
<%@page import="dao.ListeDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    HttpSession session1= request.getSession();
    String id=String.valueOf(session1.getAttribute("id"));
    int idUser=Integer.parseInt(id);
    String room=String.valueOf(session1.getAttribute("room"));
    ListeDao list=new ListeDao();
    List<JoueurModele> idJoueurs=list.listJoueur(room);
    String[] couleur = {"#0000ff", "#008000", "#ff0000", "#ff9100"};
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/f/w3.css">
        <link rel="stylesheet" href="css/ludo.css">
        <link rel="stylesheet" href="css/deLudo.css">
        <link rel="stylesheet" href="css/vict.css">
    </head>
    <body>
        <div id="principe" class="modal" >
            <div class="modal-content animate container" style="overflow-x:hidden;">
                Les joueurs obtient au hasrd une chiffre à tour de rôle dans le sens des aiguilles d'une montre. <br>
                Le chiffre obtenue détermine le nombre de cases que le joueur peut avancer l'un de ses pions sur le plateau,<br>
                 dans le sens de sa propre couleur <br>.
                Un 6 permet de jouer un pion depuis le coin de départ ou de déplacer un pion déjà sur le plateau de 6 cases. <br>
                Si un joueur obtient un 6, il a le droit de rejouer. <br>
                Les joueurs doivent déplacer leurs pions en suivant le chemin de leur couleur respective. <br><br>
                Si un pion atterrit sur une case déjà occupée par un pion adverse,<br>
                 le pion adverse est renvoyé à son coin de départ. <br>
                 Le joueur qui a renvoyé un pion adverse peut continuer à jouer son tour. <br>
                Les joueurs doivent déplacer au moins un de leurs pions à chaque tour,<br>
                 sauf s'ils ne peuvent pas en raison de l'obstruction de leurs propres pions ou d'autres obstacles. <br><br>
                Pour entrer dans la "maison" (zone d'arrivée),<br>
                 un joueur doit obtenir exactement le nombre nécessaire sur le dé pour atterrir <br>
                  sur la case d'entrée de sa maison. <br>
                   Par exemple, si un joueur est à 3 cases de sa maison et obtient un 4, il ne peut pas entrer.
                Le premier joueur à déplacer tous ses pions dans sa maison gagne la partie. <br><br>
    
                Règles spéciales sur le mode en ligne:
    
                Si le joueur ne clique pas sur le boutton pendant 1min30s pour obtenir le chiffre au hasard ,il perd son tour.
                <br>Fin de la partie :
                Le premier joueur à déplacer tous ses pions dans sa maison est déclaré vainqueur.
            </div>
        </div>
        <!-- ------------------------- -->
        <div id="id01" class="modal">
            <div class="modal-content animate container">
                <div class="a">
                    <h1>Le gagnant est:</h1>
                    <img id="imgVict" class="image" src="" width="100px" alt="">
                    <h1 id="nomVict" style="margin-left: 17%;"></h1>
                    <a class="btn success" href="index.jsp">revenir a accuil</a>
                </div>
            </div>
        </div>
        <!-- ----------------------- -->
        <div class='w3-display-container cont' style='background-color: <%out.print(couleur[idUser]);%>;'>
            <div id="e" style="position: absolute;color: white;cursor: pointer;"> 
                <h1>?</h1>
            </div>
            <!-- <button id="a" class="w3-display-middle btn" style="width:50px; height:50px;">go</button> -->
            <div class="de w3-display-middle btn" id="a">
                <div id="c"  class="de-nb dis">
                    <div>3</div>
                    <div>6</div>
                    <div id="b">2</div> 
                    <div>4</div>
                    <div>1</div>
                    <div>5</div>
                </div>
            </div> 
            <div class="canvas">
                <canvas id="t"></canvas>
            </div>
        </div>
           

    <script>
        var dictance=document.getElementById("t")
        var x=parseInt(window.innerHeight*0.9)
        x=600;
        var xpetit=parseInt(x/15)%2==1 ? parseInt(x/15)+1 : parseInt(x/15);
        x=xpetit*15
        dictance.setAttribute("width", x);
        dictance.setAttribute("height", x);
        //console.log(dictance.getAttribute("width"))
    </script>
    <script src="js/f/jquery.min.js"></script>
    <script src="js/app.js"></script>
      
    </body>
</html>
