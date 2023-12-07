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
public class PositionModele {
    String num,p0,p1,p3,p2,pseudo;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getP0() {
        return p0;
    }

    public void setP0(String p0) {
        this.p0 = p0;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }


    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    @Override
    public String toString(){
        String a="{\"num\":" + num + ", \"p0\":" + p0 + ", \"p1\":" + p1 + ", \"p2\":" + p2 + ",\"p3\":"+p3
                + ",\"pseudo\":\""+pseudo+"\"}";
        return a;
    }
}
