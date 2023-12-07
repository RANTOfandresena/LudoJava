/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import common.AccesBdd;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author RANTO
 */
public class UpDateNomRoomDao {
    public void updateNom(String room,String id,String pseudo){
        String sql="update "+room+" set pseudo='"+pseudo+"' where num='"+id+"'";
        AccesBdd acces=new AccesBdd();
        acces.loadDriver();
        acces.executeUpdate(sql);
        acces.closeConnection();
    }
    public boolean goStart(String room) throws SQLException{
        boolean ret=false;
        String sql="select start from room where nom='"+room+"'";
        AccesBdd acces=new AccesBdd();
        acces.loadDriver();
        ResultSet resultat=acces.executeSelect(sql);
        if(resultat.next()){
            ret=resultat.getString("start").equalsIgnoreCase("1");
        }
        acces.closeConnection();
        return ret;
    }
    public void updateStart(String room){
        String sql="update room set start='1' where nom='"+room+"'";
        AccesBdd acces=new AccesBdd();
        acces.loadDriver();
        acces.executeUpdate(sql);
        acces.closeConnection();
    }
}
