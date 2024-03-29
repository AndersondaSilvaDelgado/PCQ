package br.com.usinasantafe.pcq.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcq.model.pst.Entidade;

@DatabaseTable(tableName="tbitemequipvar")
public class EquipItemBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idItemEquip;
    @DatabaseField
    private Long idCabec;
    @DatabaseField
    private Long idEquip;
    @DatabaseField
    private Long tipoEquip;
    @DatabaseField
    private String dthrEquip;

    public EquipItemBean() {
    }

    public Long getIdItemEquip() {
        return idItemEquip;
    }

    public void setIdItemEquip(Long idItemEquip) {
        this.idItemEquip = idItemEquip;
    }

    public Long getIdCabec() {
        return idCabec;
    }

    public void setIdCabec(Long idCabec) {
        this.idCabec = idCabec;
    }

    public Long getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(Long idEquip) {
        this.idEquip = idEquip;
    }

    public Long getTipoEquip() {
        return tipoEquip;
    }

    public void setTipoEquip(Long tipoEquip) {
        this.tipoEquip = tipoEquip;
    }

    public String getDthrEquip() {
        return dthrEquip;
    }

    public void setDthrEquip(String dthrEquip) {
        this.dthrEquip = dthrEquip;
    }
}
