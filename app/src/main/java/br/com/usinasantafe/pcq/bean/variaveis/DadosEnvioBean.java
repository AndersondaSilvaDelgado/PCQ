package br.com.usinasantafe.pcq.bean.variaveis;

public class DadosEnvioBean {

    private String cabec;
    private String item;
    private String equip;
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

    public String getEquip() {
        return equip;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

    public String getOrgaoAmb() {
        return orgaoAmb;
    }

    public void setOrgaoAmb(String orgaoamb) {
        this.orgaoAmb = orgaoamb;
    }

    public String getTalhao() {
        return talhao;
    }

    public void setTalhao(String talhao) {
        this.talhao = talhao;
    }
}
