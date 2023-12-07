/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import common.AccesBdd;
import java.sql.ResultSet;
import java.sql.SQLException;
import modele.RoomModele;

/**
 *
 * @author RANTO
 */
public class AuthentificationDao {
    	public boolean verificationUser(RoomModele room) throws SQLException{
		boolean validation=false;
		String sql="select * from room where nom='"+room.getNom()+"' and " +
				"motdepasse='"+room.getMotdepasse()+"'limit 1";
		AccesBdd acces=new AccesBdd();
		acces.loadDriver();
		ResultSet resultat=acces.executeSelect(sql);
		while(resultat.next()){
			validation=true;
		}
		acces.closeConnection();
		return validation;
	}
        public int nbJoueur(String table) throws SQLException{
            String sql="select id from "+table;
            AccesBdd acces=new AccesBdd();
            acces.loadDriver();
            ResultSet nb=acces.executeSelect(sql);
            int a=0;
            while(nb.next()){
                a+=1;
            }
            acces.closeConnection();
            return a;
        }
        public void insertionJoueur(String nom,int id){
            String sql="insert into "+nom+" (id,num,p0,P1,p2,p3,autorisee,pseudo,daty)" +
                        "values ('"+id+"','"+id+"',0,0,0,0,0,'joueur"+id+"',now())";
            AccesBdd acces= new AccesBdd();
            acces.loadDriver(); 
            acces.executeUpdate(sql);
            acces.closeConnection();
        }
}
