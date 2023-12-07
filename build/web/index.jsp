<%-- 
    Document   : index.jsp
    Created on : 19 août 2023, 10:25:46
    Author     : RANTO
--%>

<%@page import="dao.UpDateNomRoomDao"%>
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
        <title>index</title>
        <link rel="stylesheet" href="css/f/w3.css">
        <link rel="stylesheet" href="css/index.css">
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
    <div class="cont">
        <div id="e" style="position: absolute;color: white;cursor: pointer;"> 
            <h1>?</h1>
        </div>
        <div class="titre">
            <div  style="color: #ff9100;">L</div>
            <div  style="color: #ff0000;">U</div>
            <div  style="color: #008000;">D</div>
            <div style="color: #0000ff;">O</div>
        </div>
        <div class="menu">
            <div id="choixMenu" class="partie a active">
                <button id="btnOnline"  class="w3-btn w3-ripple w3-blue b">Multijoueur en ligne</button>
                <button id="btnLocale" class="w3-btn w3-ripple w3-blue b">Locale</button>
            </div>

            <div id="online" class="partie a">
                <svg class="relat" height="100" width="50">
                    <polygon id="retourOnline" points="20,0 0,50 20,100" style="fill:rgb(71 68 68 / 95%);" />
                </svg>
                <a href="RejoindreServelet"  class="w3-btn w3-ripple w3-blue b">Rejouindre une partie</a>
                <button id="partie" class="w3-btn w3-ripple w3-blue b">Creé une partie</button>
            </div>
            <div id="creePartie" class="a">
                <h1 class="relat" style="color: #ffffff;margin-left: 30%;top:-40%">Crée la partie</h1>
                <svg class="relat" height="100" width="50">
                    <polygon id="retourPartie" points="20,0 0,50 20,100" style="fill:rgb(71 68 68 / 95%);" />
                </svg>
                <form action="AjoutServelet" method="post" >
                    <input type="text" class="w3-btn w3-ripple w3-blue b" placeholder="Nom de la partie" name="nom" id="inputNom" required>
                    <input type="password" class="w3-btn w3-ripple w3-blue b" placeholder="Mot de passe" name="mdp" id="inputMDP">
                    <button type="submit" class="w3-btn w3-ripple w3-blue b">Crée</button>
                </form>
            </div>

            <div id="locale" class="partie a">
                <svg class="relat" height="100" width="50">
                    <polygon id="retourLocale" points="20,0 0,50 20,100" style="fill:rgb(71 68 68 / 95%);" />
                </svg>
                <form action="LocalServlet" method="post" >
                    <div  style="color: #ffffff;display: flex;margin-left: 20%;">
                        <h1 >Nombre des Joueurs:</h1>
                        <h1 id="nbJouer" >2</h1>
                    </div>
                    <input type="range" class="w3-ripple w3-display-middle" name="num" id="myRange" value="2" min="2" max="4">
                    <button type="submit" class="w3-btn w3-ripple w3-blue b">Joueur</button>
                </form>
            </div>

        </div>

    </div>
    <script src="js/f/jquery.min.js"></script>
    <script src="js/index.js"></script>
    </body>
</html>