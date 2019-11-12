package br.com.usinasantafe.pcq.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcq.pst.Entidade;

@DatabaseTable(tableName="tbequipest")
public class EquipBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idEquip;
    @DatabaseField
    private Long nroEquip;
    @DatabaseField
    private Long tipoEquip;

    public EquipBean() {
    }

    public Long getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(Long idEquip) {
        this.idEquip = idEquip;
    }

    public Long getNroEquip() {
        return nroEquip;
    }

    public void setNroEquip(Long nroEquip) {
        this.nroEquip = nroEquip;
    }

    public Long getTipoEquip() {
        return tipoEquip;
    }

    public void setTipoEquip(Long tipoEquip) {
        this.tipoEquip = tipoEquip;
    }
}
