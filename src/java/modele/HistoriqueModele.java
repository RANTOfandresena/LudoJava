/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author RANTO
 */
public class HistoriqueModele {
    String id;
    String mode;	
    String idjouer;
    String pion;
    String deplace;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getIdjouer() {
        return idjouer;
    }

    public void setIdjouer(String idjouer) {
        this.idjouer = idjouer;
    }

    public String getPion() {
        return pion;
    }

    public void setPion(String pion) {
        this.pion = pion;
    }

    public String getDeplace() {
        return deplace;
    }

    public void setDeplace(String deplace) {
        this.deplace = deplace;
    }
    @Override
    public String toString(){
        String a="{\"id\":\"" + id + "\", \"mode\":\"" + mode + "\",\"idjoueur\":\""+idjouer+"\""
                + ",\"pion\":\""+pion+"\",\"deplace\":\""+deplace+"\"}";
        return a;
    }
}
