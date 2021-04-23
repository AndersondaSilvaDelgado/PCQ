package br.com.usinasantafe.pcq.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcq.model.pst.Entidade;

@DatabaseTable(tableName="tborigemfogoest")
public class OrigemFogoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idOrigemFogo;
    @DatabaseField
    private String descrOrigemFogo;

    public OrigemFogoBean() {
    }

    public Long getIdOrigemFogo() {
        return idOrigemFogo;
    }

    public void setIdOrigemFogo(Long idOrigemFogo) {
        this.idOrigemFogo = idOrigemFogo;
    }

    public String getDescrOrigemFogo() {
        return descrOrigemFogo;
    }

    public void setDescrOrigemFogo(String descrOrigemFogo) {
        this.descrOrigemFogo = descrOrigemFogo;
    }
}
