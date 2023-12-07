/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import common.AccesBdd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.HistoriqueModele;

/**
 *
 * @author RANTO
 */
public class HistoriquePartieDao {
    public void envoyeHistorique(String room,String mode,String id,String pion,int deplace) throws SQLException{
        String select="SELECT mode FROM historique_"+room+" WHERE id=(SELECT MAX(id) FROM historique_"+room+");";
        AccesBdd acces=new AccesBdd();
        acces.loadDriver();
        ResultSet resultat= acces.executeSelect(select);
        String nombre="2";
        while(resultat.next()){
            nombre=resultat.getString("mode");
        }
        boolean autorise=true;
        if(!mode.equalsIgnoreCase("0")){
            if(mode.equalsIgnoreCase(nombre)){
                autorise=false;
            }
        }
        if(autorise==true){
            String sql="INSERT INTO historique_"+room+" (mode,idjouer,pion,deplace,daty) VALUES "
                + "('"+mode+"','"+id+"','"+pion+"','"+deplace+"',NOW())";
            acces.executeUpdate(sql);               
        }
        acces.closeConnection();
    }
    
    
    public void envoyerTableNom(String room,String idjouer,String pion,String deplace) throws SQLException{
        String sql="select p"+pion+" from "+room+" where num='"+idjouer+"'";
        AccesBdd acces=new AccesBdd();
        acces.loadDriver();
        ResultSet resultat= acces.executeSelect(sql);
        int nombre=0;
        while(resultat.next()){
            nombre=Integer.parseInt(resultat.getString("p"+pion));
        }
        nombre+=Integer.parseInt(deplace);
        String sql2="update "+room+" set p"+pion+" ='"+nombre+"' where id='"+idjouer+"'";
        acces.executeUpdate(sql2);
        acces.closeConnection();
        
    }
    @SuppressWarnings("empty-statement")
    public void chengementDeTour(String room,String id) throws SQLException{
        String idjouer="0";
        AccesBdd access=new AccesBdd();
        String select="SELECT idjouer FROM historique_"+room+" WHERE id=(SELECT MAX(id) FROM historique_"+room+");";
        String sqlNbJoueur="SELECT MAX(id) FROM "+room+";";
        access.loadDriver();
        ResultSet resultat= access.executeSelect(select);
        if(resultat.next()){
            idjouer=resultat.getString("idjouer");
        }
        String sql1="update "+room+" set autorisee='0' where num='"+idjouer+"'";
        
        int nbs=nombreDeJoueur(room);
        int idJ=Integer.parseInt(idjouer);
        int newid=regleDeTour(room,nbs,idJ);
        //-------------------------------------
        String sql2="update "+room+" set autorisee='1' where num='"+newid +"'";
        
        
        access.executeUpdate(sql1);
        access.executeUpdate(sql2);
        access.closeConnection();
    }
     
    public String autorsationClick(String room) throws SQLException{
        String a = "4";
        String sql="select num from "+room+" where autorisee='1'";
        AccesBdd acces=new AccesBdd();
        acces.loadDriver();
        ResultSet resultat= acces.executeSelect(sql);
        while(resultat.next()){
            a=resultat.getString("num");
        }
        acces.closeConnection();
        return a;
    }
    public List<HistoriqueModele> nouveauActualitee(String room,String idHist,String idJ) throws SQLException{
        List<HistoriqueModele> list = new ArrayList<HistoriqueModele>();;
        String sql="select * from historique_"+room+" where id>"+idHist+";";
        AccesBdd acces=new AccesBdd();
        acces.loadDriver();
        ResultSet resultat=acces.executeSelect(sql);
        while(resultat.next()){
            HistoriqueModele hist=new HistoriqueModele();
            hist.setId(resultat.getString("id"));
            hist.setMode(resultat.getString("mode"));
            hist.setIdjouer(resultat.getString("idjouer"));
            hist.setPion(resultat.getString("pion"));
            hist.setDeplace(resultat.getString("deplace"));
            if(!resultat.getString("idjouer").equalsIgnoreCase(idJ))
            {
                list.add(hist);
            }
            if(resultat.getString("mode").equalsIgnoreCase("0") && resultat.getString("idjouer").equalsIgnoreCase(idJ)){
                list.add(hist);
            }
        }
        acces.closeConnection();
        return list;
    }
    public void mody(String room,String idJ,String pion) {
        String sql="update "+room+" set p"+pion+"=0 where id='"+idJ+"'";
        AccesBdd acces=new AccesBdd();
        acces.loadDriver();
        acces.executeUpdate(sql);
        acces.closeConnection();
    }
    public HistoriqueModele dernierActu(String room) throws SQLException{
        String sql="select * from historique_"+room+" where id=(SELECT MAX(id) FROM historique_"+room+");";
        AccesBdd acces=new AccesBdd();
        acces.loadDriver();
        ResultSet resultat= acces.executeSelect(sql);
        HistoriqueModele hist=new HistoriqueModele();
        while(resultat.next()){
            hist.setId(resultat.getString("id"));
            hist.setMode(resultat.getString("mode"));
            hist.setIdjouer(resultat.getString("idjouer"));
            hist.setPion(resultat.getString("pion"));
            hist.setDeplace(resultat.getString("deplace"));
        }
        return hist;
    }
    public boolean soustracrion(String dateBdd){
        Date date=new Date();
        SimpleDateFormat form=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        try {
            date1 = form.parse(dateBdd);
        } catch (ParseException ex) {
            Logger.getLogger(HistoriquePartieDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        long rest=date.getTime()-date1.getTime();
        return rest<20000;
    }
    public boolean estCeExpiree(String room,int id) throws SQLException{
        String resultat="";
        String sql="select daty from "+room+" where id='"+id+"'";
        AccesBdd acces=new AccesBdd();
        acces.loadDriver();
        ResultSet r=acces.executeSelect(sql);
        if(r.next()){
            resultat=r.getString("daty");
        }
        acces.closeConnection();
        return !soustracrion(resultat);
    }
    public void majDaty(String room,String id){
        String sql="update "+room+" set daty=now() where id='"+id+"'";
        AccesBdd acces=new AccesBdd();
        acces.loadDriver();
        acces.executeUpdate(sql);
        acces.closeConnection();
    }
    public void changementTourParDaty(String room) throws SQLException, ParseException{
        String sql="SELECT daty FROM historique_"+room+" WHERE id=(SELECT MAX(id) FROM historique_"+room+");";
        String sqlrr="select id from "+room+" where autorisee='1'";
        AccesBdd acces=new AccesBdd();
        acces.loadDriver();
        String daty="";
        String id="";
        ResultSet rr=acces.executeSelect(sqlrr);
        if(rr.next()){
            id=rr.getString("id");
            ResultSet r=acces.executeSelect(sql);
            if(r.next()){
                daty=r.getString("daty");
                int idInt=Integer.valueOf(id);
                long rest=soustractiHeure(daty);
                int nbJoueur=nombreDeJoueur(room);
                if(rest>90000){//90seconde
                    String majDaty="update historique_"+room+" set daty=now() where id=(SELECT MAX(id) FROM historique_"+room+")";
                    acces.executeUpdate(majDaty);
                    String sql1="update "+room+" set autorisee='0' where num='"+idInt+"'";
                    acces.executeUpdate(sql1);
                    int idJoueurHorsLigne=regleDeTour(room,nbJoueur,idInt);
                    sql1="update "+room+" set autorisee='1' where num='"+idJoueurHorsLigne+"'";
                    acces.executeUpdate(sql1);
                }
            }
        }
        acces.closeConnection();
    }
    public long soustractiHeure(String daty) throws ParseException{
        Date date=new Date();
        SimpleDateFormat form=new SimpleDateFormat("HH:mm:ss");
        String datezao=form.format(date);
        Date a=form.parse(daty);
        Date b=form.parse(datezao);
        long rest=b.getTime()-a.getTime();
        return rest;
    }
    public int regleDeTour(String room,int nombreJoueur,int numJoueur) throws SQLException{
        int newid=0;
        if(nombreJoueur==2){
            newid=(numJoueur==1?0:1);
            if(estCeExpiree(room,newid)){
                newid=(numJoueur==1?1:0);
            }
        }else if(nombreJoueur==3){
            int[] tab3={0,2,1,0};
            for(int j=0;j!=tab3.length;j++){
                if(numJoueur==tab3[j]){
                    newid=tab3[j+1];
                    int a=1;
                    int verif=0;
                    while(estCeExpiree(room,newid)){
                        a++;
                        a%=3;
                        newid=tab3[j+a];
                        verif++;
                        if(verif==3){
                            break;
                        }
                    }
                    break;
                }
            }  
        }else{
            int[] tab4={0,2,1,3,0};
            for(int j=0;j!=tab4.length;j++){
                if(numJoueur==tab4[j]){
                    newid=tab4[j+1];
                    int a=1;
                    int verif=0;
                    while(estCeExpiree(room,newid)){
                        a++;
                        a%=4;
                        newid=tab4[j+a];
                        verif++;
                        if(verif==4){
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return newid;
    }
    public int nombreDeJoueur(String room) throws SQLException{
        AccesBdd acces=new AccesBdd();
        acces.loadDriver();
        String nombre="select id from "+room;
        ResultSet nb= acces.executeSelect(nombre);
        int nbs=0;
        while(nb.next()){
            nbs++;
        }
        acces.closeConnection();
        return nbs;
    }
}
