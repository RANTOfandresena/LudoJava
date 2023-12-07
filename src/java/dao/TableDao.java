/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import common.AccesBdd;
import modele.RoomModele;

/**
 *
 * @author RANTO
 */
public class TableDao {
    public void creationTablePartie(RoomModele room){
        String sql1="insert into room (nom,motdepasse) values ('"+room.getNom()+"','"+room.getMotdepasse()+"')";
        String sqlTable="CREATE TABLE IF NOT EXISTS "+room.getNom()+" (" +
"      id int(11) NOT NULL PRIMARY KEY ," +
"      num int(11) NOT NULL," +
"      p0 int(11) NOT NULL," +
"      p1 int(11) NOT NULL," +
"      p3 int(11) NOT NULL," +
"      p2 int(11) NOT NULL," +
"      autorisee int(11) NOT NULL," +
"      pseudo varchar(50),"+
"      start varchar(3),"+
"      daty DATETIME)";
        
        String sql2="INSERT INTO "+room.getNom()+" (id, num,p0,p1,p3,p2,autorisee,pseudo,start,daty) VALUES (0,0,0,0,0,0,1,'joueur0',0,now());";
        
        String sqlTableHisto="CREATE TABLE IF NOT EXISTS historique_"+room.getNom()+" " +
"      (" +
"        id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
"        mode int(11) not null," +
"        idjouer int(11) NOT NULL," +
"        pion int(11)," +
"        deplace int(11)," +
"        daty TIME"+
"      )";
        
        AccesBdd acces= new AccesBdd();
        acces.loadDriver();
        acces.executeUpdate(sql1);
        acces.executeUpdate(sqlTable);
        acces.executeUpdate(sql2);
        acces.executeUpdate(sqlTableHisto);
        acces.closeConnection();
    }
    
}
