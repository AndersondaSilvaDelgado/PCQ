package br.com.usinasantafe.pcq.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcq.model.pst.Entidade;

@DatabaseTable(tableName="tbcabecvar")
public class CabecBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabec;
    @DatabaseField
    private Long idExtCabec;
    @DatabaseField
    private Long nroAparelhoCabec;
    @DatabaseField
    private Long idFuncCabec;
    @DatabaseField
    private Long tipoApontTrabCabec;
    @DatabaseField
    private Long secaoCabec;
    @DatabaseField
    private Double haIncAppCabec;
    @DatabaseField
    private Double haIncForaAppCabec;
    @DatabaseField
    private Long tercCombCabec;
    @DatabaseField
    private Long origemFogoCabec;
    @DatabaseField
    private Long aceiroCanavialCabec;
    @DatabaseField
    private Long aceiroVegetNativalCabec;
    @DatabaseField
    private String comentCabec;
    @DatabaseField
    private String dthrCabec;
    @DatabaseField
    private String dataInsCabec;
    @DatabaseField
    private Long tipoCabec;
    @DatabaseField
    private Long statusCabec; // 0 - Iniciado; 1 - Aberto; 2 - Fechado; 3-Finalizado; 4 - Recebido; 5 - Fechado; 6 - Finalizado;

    public CabecBean() {
    }

    public Long getIdCabec() {
        return idCabec;
    }

    public void setIdCabec(Long idCabec) {
        this.idCabec = idCabec;
    }

    public Long getIdExtCabec() {
        return idExtCabec;
    }

    public void setIdExtCabec(Long idExtCabec) {
        this.idExtCabec = idExtCabec;
    }

    public Long getNroAparelhoCabec() {
        return nroAparelhoCabec;
    }

    public void setNroAparelhoCabec(Long nroAparelhoCabec) {
        this.nroAparelhoCabec = nroAparelhoCabec;
    }

    public Long getIdFuncCabec() {
        return idFuncCabec;
    }

    public void setIdFuncCabec(Long idFuncCabec) {
        this.idFuncCabec = idFuncCabec;
    }

    public Long getTipoApontTrabCabec() {
        return tipoApontTrabCabec;
    }

    public void setTipoApontTrabCabec(Long tipoApontTrabCabec) {
        this.tipoApontTrabCabec = tipoApontTrabCabec;
    }

    public Long getSecaoCabec() {
        return secaoCabec;
    }

    public void setSecaoCabec(Long secaoCabec) {
        this.secaoCabec = secaoCabec;
    }

    public Double getHaIncAppCabec() {
        return haIncAppCabec;
    }

    public void setHaIncAppCabec(Double haIncAppCabec) {
        this.haIncAppCabec = haIncAppCabec;
    }

    public Double getHaIncForaAppCabec() {
        return haIncForaAppCabec;
    }

    public void setHaIncForaAppCabec(Double haIncForaAppCabec) {
        this.haIncForaAppCabec = haIncForaAppCabec;
    }

    public Long getTercCombCabec() {
        return tercCombCabec;
    }

    public void setTercCombCabec(Long tercCombCabec) {
        this.tercCombCabec = tercCombCabec;
    }

    public Long getOrigemFogoCabec() {
        return origemFogoCabec;
    }

    public void setOrigemFogoCabec(Long origemFogoCabec) {
        this.origemFogoCabec = origemFogoCabec;
    }

    public Long getAceiroCanavialCabec() {
        return aceiroCanavialCabec;
    }

    public void setAceiroCanavialCabec(Long aceiroCanavialCabec) {
        this.aceiroCanavialCabec = aceiroCanavialCabec;
    }

    public Long getAceiroVegetNativalCabec() {
        return aceiroVegetNativalCabec;
    }

    public void setAceiroVegetNativalCabec(Long aceiroVegetNativalCabec) {
        this.aceiroVegetNativalCabec = aceiroVegetNativalCabec;
    }

    public String getComentCabec() {
        return comentCabec;
    }

    public void setComentCabec(String comentCabec) {
        this.comentCabec = comentCabec;
    }

    public String getDthrCabec() {
        return dthrCabec;
    }

    public void setDthrCabec(String dthrCabec) {
        this.dthrCabec = dthrCabec;
    }

    public String getDataInsCabec() {
        return dataInsCabec;
    }

    public void setDataInsCabec(String dataInsCabec) {
        this.dataInsCabec = dataInsCabec;
    }

    public Long getTipoCabec() {
        return tipoCabec;
    }

    public void setTipoCabec(Long tipoCabec) {
        this.tipoCabec = tipoCabec;
    }

    public Long getStatusCabec() {
        return statusCabec;
    }

    public void setStatusCabec(Long statusCabec) {
        this.statusCabec = statusCabec;
    }
}
