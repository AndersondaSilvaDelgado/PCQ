package br.com.usinasantafe.pcq.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

import br.com.usinasantafe.pcq.pst.Entidade;

@DatabaseTable(tableName="tbcabecvar")
public class CabecBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabec;
    @DatabaseField
    private Long matricColabCabec;
    @DatabaseField
    private Long secaoCabec;
    @DatabaseField
    private Double haIncCanaCabec;
    @DatabaseField
    private Double haIncPalhadaCabec;
    @DatabaseField
    private Double haIncResLegalCabec;
    @DatabaseField
    private Double haIncAppCabec;
    @DatabaseField
    private Double haIncAreaComumCabec;
    @DatabaseField
    private Long qtdeBrigadistaCabec;
    @DatabaseField
    private String empresaTercCabec;
    @DatabaseField
    private String comentCabec;
    @DatabaseField
    private String dthrCabec;
    @DatabaseField
    private Long statusCabec;

    public CabecBean() {
    }

    public Long getIdCabec() {
        return idCabec;
    }

    public void setIdCabec(Long idCabec) {
        this.idCabec = idCabec;
    }

    public Long getMatricColabCabec() {
        return matricColabCabec;
    }

    public void setMatricColabCabec(Long matricColabCabec) {
        this.matricColabCabec = matricColabCabec;
    }

    public String getDthrCabec() {
        return dthrCabec;
    }

    public void setDthrCabec(String dthrCabec) {
        this.dthrCabec = dthrCabec;
    }

    public Long getStatusCabec() {
        return statusCabec;
    }

    public void setStatusCabec(Long statusCabec) {
        this.statusCabec = statusCabec;
    }

    public Long getSecaoCabec() {
        return secaoCabec;
    }

    public void setSecaoCabec(Long secaoCabec) {
        this.secaoCabec = secaoCabec;
    }

    public Double getHaIncCanaCabec() {
        return haIncCanaCabec;
    }

    public void setHaIncCanaCabec(Double haIncCanaCabec) {
        this.haIncCanaCabec = haIncCanaCabec;
    }

    public Double getHaIncResLegalCabec() {
        return haIncResLegalCabec;
    }

    public void setHaIncResLegalCabec(Double haIncResLegalCabec) {
        this.haIncResLegalCabec = haIncResLegalCabec;
    }

    public Double getHaIncAppCabec() {
        return haIncAppCabec;
    }

    public void setHaIncAppCabec(Double haIncAppCabec) {
        this.haIncAppCabec = haIncAppCabec;
    }

    public Double getHaIncAreaComumCabec() {
        return haIncAreaComumCabec;
    }

    public void setHaIncAreaComumCabec(Double haIncAreaComumCabec) {
        this.haIncAreaComumCabec = haIncAreaComumCabec;
    }

    public Double getHaIncPalhadaCabec() {
        return haIncPalhadaCabec;
    }

    public void setHaIncPalhadaCabec(Double haIncPalhadaCabec) {
        this.haIncPalhadaCabec = haIncPalhadaCabec;
    }

    public String getComentCabec() {
        return comentCabec;
    }

    public void setComentCabec(String comentCabec) {
        this.comentCabec = comentCabec;
    }

    public Long getQtdeBrigadistaCabec() {
        return qtdeBrigadistaCabec;
    }

    public void setQtdeBrigadistaCabec(Long qtdeBrigadistaCabec) {
        this.qtdeBrigadistaCabec = qtdeBrigadistaCabec;
    }

    public String getEmpresaTercCabec() {
        return empresaTercCabec;
    }

    public void setEmpresaTercCabec(String empresaTercCabec) {
        this.empresaTercCabec = empresaTercCabec;
    }
}
