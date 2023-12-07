/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import common.AccesBdd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.JoueurModele;
import modele.PositionModele;
import modele.RoomModele;

/**
 *
 * @author RANTO
 */
public class ListeDao {
    public List<RoomModele> listeRoom(){
        List<RoomModele> listRoom=new ArrayList<RoomModele>();
        String sql="select * from room";
        AccesBdd acces =new AccesBdd();
        acces.loadDriver();
        ResultSet resultat = acces.executeSelect(sql);
        try {
            while(resultat.next()){
                RoomModele room = new RoomModele();
                room.setId(resultat.getString("id"));
                room.setNom(resultat.getString("nom"));
                room.setMotdepasse(resultat.getString("motdepasse"));
                listRoom.add(room);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        acces.closeConnection();
        return listRoom;
    }
    public List<JoueurModele> listJoueur(String nom) throws SQLException{
        List<JoueurModele> list = new ArrayList<JoueurModele>();
        String sql="select num,pseudo from "+nom;
        AccesBdd acces =new AccesBdd();
        acces.loadDriver();
        ResultSet resultat = acces.executeSelect(sql);
        while(resultat.next()){
            JoueurModele joueur = new JoueurModele();
            joueur.setId(resultat.getString("num"));
            joueur.setPseudo(resultat.getString("pseudo"));
            list.add(joueur);
        }
        acces.closeConnection();
        return list;
    }
    public List<PositionModele> positionMaj(String room) throws SQLException{
        List<PositionModele> list = new ArrayList<PositionModele>();
        String sql="select * from "+room;
        AccesBdd acces =new AccesBdd();
        acces.loadDriver();
        ResultSet resultat = acces.executeSelect(sql);
        while(resultat.next()){
            PositionModele joueur = new PositionModele();
            joueur.setNum(resultat.getString("num"));
            joueur.setP0(resultat.getString("p0"));
            joueur.setP1(resultat.getString("p1"));
            joueur.setP2(resultat.getString("p2"));
            joueur.setP3(resultat.getString("p3"));
            joueur.setPseudo(resultat.getString("pseudo"));
            list.add(joueur);
        }
        acces.closeConnection();
        return list;
    }
    public void supprimeJour(String room,String id){
        String sql="DELETE FROM "+room+" WHERE id='"+id+"'";
        AccesBdd acces= new AccesBdd();
        acces.loadDriver();
        acces.executeUpdate(sql);
        acces.closeConnection();
    }
    public boolean changementPlace(String room,String idd) throws SQLException{
        boolean retour=false; 
        AccesBdd acces= new AccesBdd();
        acces.loadDriver();
        int id=Integer.valueOf(idd);
        int nvlId=id-1;
        if(nvlId>-1){
            String sql="select id from "+room+" where id='"+nvlId+"'";
            ResultSet resultat = acces.executeSelect(sql);
            if(!resultat.next()){
                retour=true;
                if(nvlId==0){
                    sql="update "+room+" set id='"+nvlId+"',num='"+nvlId+"',autorisee='1' where id='"+id+"'";
                }else{
                    sql="update "+room+" set id='"+nvlId+"',num='"+nvlId+"' where id='"+id+"'";
                }
                
                acces.executeUpdate(sql);
            }
        }
        acces.closeConnection();
        return retour;
    }
    public String nyMpandresy(String room) throws SQLException{
        String retour="$";
        String sql="select id,pseudo from "+room+" where p0>=57 and  p1>=57 and  p2>=57 and  p3>=57";
        AccesBdd acces =new AccesBdd();
        acces.loadDriver();
        ResultSet resultat = acces.executeSelect(sql);
        if(resultat.next()){
            retour = resultat.getString("id")+","+resultat.getString("pseudo");
        }
        acces.closeConnection();
        return retour;
    }
}
