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
public class JoueurModele {
    public String id;
    public String pseudo;
    public String mdp;

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudoo) {
        this.pseudo = pseudoo;
    }
    @Override
    public String toString(){
        String a="{\"id\":\"" + id + "\", \"pseudo\":\"" + pseudo + "\"}";
        return a;
    }

}
