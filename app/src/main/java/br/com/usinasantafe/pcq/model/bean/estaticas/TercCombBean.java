package br.com.usinasantafe.pcq.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcq.model.pst.Entidade;

@DatabaseTable(tableName="tbterccombest")
public class TercCombBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idTercComb;
    @DatabaseField
    private String descrTercComb;

    public TercCombBean() {
    }

    public Long getIdTercComb() {
        return idTercComb;
    }

    public void setIdTercComb(Long idTercComb) {
        this.idTercComb = idTercComb;
    }

    public String getDescrTercComb() {
        return descrTercComb;
    }

    public void setDescrTercComb(String descrTercComb) {
        this.descrTercComb = descrTercComb;
    }
}
