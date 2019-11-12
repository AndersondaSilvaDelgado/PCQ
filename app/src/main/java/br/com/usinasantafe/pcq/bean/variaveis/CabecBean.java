package br.com.usinasantafe.pcq.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

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
    private Long tipoIncCanavialCabec;
    @DatabaseField
    private Double qtdeIncCanavialCabec;
    @DatabaseField
    private Long vegNativaCabec;
    @DatabaseField
    private Double qtdeResVegNativaCabec;
    @DatabaseField
    private Double qtdeAppVegNativaCabec;
    @DatabaseField
    private Double qtdeAreaComVegNativaCabec;
    @DatabaseField
    private Long tanqueCabec;
    @DatabaseField
    private Long saveiroCabec;
    @DatabaseField
    private Long brigCabec;
    @DatabaseField
    private Long qtdeBrigCabec;
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

    public Long getTipoIncCanavialCabec() {
        return tipoIncCanavialCabec;
    }

    public void setTipoIncCanavialCabec(Long tipoIncCanavialCabec) {
        this.tipoIncCanavialCabec = tipoIncCanavialCabec;
    }

    public Double getQtdeIncCanavialCabec() {
        return qtdeIncCanavialCabec;
    }

    public void setQtdeIncCanavialCabec(Double qtdeIncCanavialCabec) {
        this.qtdeIncCanavialCabec = qtdeIncCanavialCabec;
    }

    public Long getVegNativaCabec() {
        return vegNativaCabec;
    }

    public void setVegNativaCabec(Long vegNativaCabec) {
        this.vegNativaCabec = vegNativaCabec;
    }

    public Double getQtdeResVegNativaCabec() {
        return qtdeResVegNativaCabec;
    }

    public void setQtdeResVegNativaCabec(Double qtdeResVegNativaCabec) {
        this.qtdeResVegNativaCabec = qtdeResVegNativaCabec;
    }

    public Double getQtdeAppVegNativaCabec() {
        return qtdeAppVegNativaCabec;
    }

    public void setQtdeAppVegNativaCabec(Double qtdeAppVegNativaCabec) {
        this.qtdeAppVegNativaCabec = qtdeAppVegNativaCabec;
    }

    public Double getQtdeAreaComVegNativaCabec() {
        return qtdeAreaComVegNativaCabec;
    }

    public void setQtdeAreaComVegNativaCabec(Double qtdeAreaComVegNativaCabec) {
        this.qtdeAreaComVegNativaCabec = qtdeAreaComVegNativaCabec;
    }

    public Long getTanqueCabec() {
        return tanqueCabec;
    }

    public void setTanqueCabec(Long tanqueCabec) {
        this.tanqueCabec = tanqueCabec;
    }

    public Long getSaveiroCabec() {
        return saveiroCabec;
    }

    public void setSaveiroCabec(Long saveiroCabec) {
        this.saveiroCabec = saveiroCabec;
    }

    public Long getBrigCabec() {
        return brigCabec;
    }

    public void setBrigCabec(Long brigCabec) {
        this.brigCabec = brigCabec;
    }

    public Long getQtdeBrigCabec() {
        return qtdeBrigCabec;
    }

    public void setQtdeBrigCabec(Long qtdeBrigCabec) {
        this.qtdeBrigCabec = qtdeBrigCabec;
    }

    public String getComentCabec() {
        return comentCabec;
    }

    public void setComentCabec(String comentCabec) {
        this.comentCabec = comentCabec;
    }
}
