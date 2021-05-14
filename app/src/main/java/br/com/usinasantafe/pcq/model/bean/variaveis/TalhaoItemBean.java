package br.com.usinasantafe.pcq.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcq.model.pst.Entidade;

@DatabaseTable(tableName="tbtalhaoitemvar")
public class TalhaoItemBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idTalhaoItem;
    @DatabaseField
    private Long idCabec;
    @DatabaseField
    private Long idTalhao;
    @DatabaseField
    private String dthrTalhao;
    @DatabaseField
    private Double haIncCanaTalhao;
    @DatabaseField
    private Long altCanaTalhao;
    @DatabaseField
    private Double haIncPalhadaTalhao;
    @DatabaseField
    private Long tipoTalhao;

    public TalhaoItemBean() {
    }

    public Long getIdTalhaoItem() {
        return idTalhaoItem;
    }

    public void setIdTalhaoItem(Long idTalhaoItem) {
        this.idTalhaoItem = idTalhaoItem;
    }

    public Long getIdCabec() {
        return idCabec;
    }

    public void setIdCabec(Long idCabec) {
        this.idCabec = idCabec;
    }

    public Long getIdTalhao() {
        return idTalhao;
    }

    public void setIdTalhao(Long idTalhao) {
        this.idTalhao = idTalhao;
    }

    public String getDthrTalhao() {
        return dthrTalhao;
    }

    public void setDthrTalhao(String dthrTalhao) {
        this.dthrTalhao = dthrTalhao;
    }

    public Double getHaIncCanaTalhao() {
        return haIncCanaTalhao;
    }

    public void setHaIncCanaTalhao(Double haIncCanaTalhao) {
        this.haIncCanaTalhao = haIncCanaTalhao;
    }

    public Long getAltCanaTalhao() {
        return altCanaTalhao;
    }

    public void setAltCanaTalhao(Long altCanaTalhao) {
        this.altCanaTalhao = altCanaTalhao;
    }

    public Double getHaIncPalhadaTalhao() {
        return haIncPalhadaTalhao;
    }

    public void setHaIncPalhadaTalhao(Double haIncPalhadaTalhao) {
        this.haIncPalhadaTalhao = haIncPalhadaTalhao;
    }

    public Long getTipoTalhao() {
        return tipoTalhao;
    }

    public void setTipoTalhao(Long tipoTalhao) {
        this.tipoTalhao = tipoTalhao;
    }
}
