<%-- 
    Document   : room
    Created on : 19 août 2023, 16:10:15
    Author     : RANTO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/f/w3.css">
        <link rel="stylesheet" href="css/styleRoom.css">
    </head>
    <body>
        <%
            HttpSession session1= request.getSession();
            String room=String.valueOf(session1.getAttribute("room"));
        %>
        <div class="cont">
            <p class='w3-panel titre'><%out.print(room);%></p>
            <%
        
        String idJ=String.valueOf(session1.getAttribute("id"));
        if(idJ.equalsIgnoreCase("0")){
            out.println("<button class='w3-button w3-xlarge w3-circle w3-blue w3-display-middle' id='go'>"
                +"<svg  width='16' height='16' fill='currentColor' class='bi bi-symmetry-horizontal' viewBox='0 0 16 16'>"+
                    "<path d='M13.5 7a.5.5 0 0 0 .24-.939l-11-6A.5.5 0 0 0 2 .5v6a.5.5 0 0 0 .5.5h11zm.485 2.376a.5.5 0 0 1-.246.563l-11 6A.5.5 0 0 1 2 15.5v-6a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 .485.376zM11.539 10H3v4.658L11.54 10z'/>"+
                "</svg></button>"); 
        }else{
            out.println("<div id='go'></div>");
        }
        %>
            
            <table class="w3-card-4 tab joueur">
                <tbody>
                    <tr>
                        <td class="w3-panel w3-blue player"></td>
                        <td class="w3-panel w3-red player"></td>
                    </tr>
                    <tr>
                        <td class="w3-panel w3-yellow player w3-text-white"></td>
                        <td class="w3-panel player w3-green"></td>
                    </tr>
                </tbody>
            </table>
            <input id="inpNom" type='text' class='w3-input w3-hover-sand' placeholder="changer votre pseudo et ensuit press(entrer)" id="inpNom">
        </div>
        <script src="js/f/jquery.min.js"></script>
        <script src="js/appRoom.js"></script>
    </body>
</html>
