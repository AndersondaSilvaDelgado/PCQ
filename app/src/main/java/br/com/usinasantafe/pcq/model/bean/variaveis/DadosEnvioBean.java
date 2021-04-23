package br.com.usinasantafe.pcq.model.bean.variaveis;

import android.util.Log;

public class DadosEnvioBean {

    private String cabec;
    private String item;
    private String brigadista;
    private String equip;
    private String foto;
    private String orgaoAmb;
    private String talhao;

    public DadosEnvioBean() {
    }

    public String getCabec() {
        return cabec;
    }

    public void setCabec(String cabec) {
        this.cabec = cabec;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getBrigadista() {
        return brigadista;
    }

    public void setBrigadista(String brigadista) {
        this.brigadista = brigadista;
    }

    public String getEquip() {
        return equip;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getOrgaoAmb() {
        return orgaoAmb;
    }

    public void setOrgaoAmb(String orgaoAmb) {
        this.orgaoAmb = orgaoAmb;
    }

    public String getTalhao() {
        return talhao;
    }

    public void setTalhao(String talhao) {
        this.talhao = talhao;
    }
}
