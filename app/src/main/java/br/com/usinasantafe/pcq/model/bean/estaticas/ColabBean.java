package br.com.usinasantafe.pcq.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcq.model.pst.Entidade;

@DatabaseTable(tableName="tbcolabest")
public class ColabBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idFuncColab;
    @DatabaseField
    private Long matricColab;
    @DatabaseField
    private String nomeColab;

    public ColabBean() {
    }

    public Long getIdFuncColab() {
        return idFuncColab;
    }

    public void setIdFuncColab(Long idFuncColab) {
        this.idFuncColab = idFuncColab;
    }

    public Long getMatricColab() {
        return matricColab;
    }

    public void setMatricColab(Long matricColab) {
        this.matricColab = matricColab;
    }

    public String getNomeColab() {
        return nomeColab;
    }

    public void setNomeColab(String nomeColab) {
        this.nomeColab = nomeColab;
    }
}
