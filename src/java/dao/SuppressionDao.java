/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import common.AccesBdd;

/**
 *
 * @author RANTO
 */
public class SuppressionDao {
    public void deleteTable(String room){
        //String col="DELETE FROM room WHERE nom='"+room+"'";
        //String table1="DROP TABLE "+room;
        String table2="DROP TABLE historique_"+room;
        AccesBdd acces= new AccesBdd();
        acces.loadDriver();
        //acces.executeUpdate(col);
        //acces.executeUpdate(table1);
        acces.executeUpdate(table2);
        acces.closeConnection();
        
    }
}
