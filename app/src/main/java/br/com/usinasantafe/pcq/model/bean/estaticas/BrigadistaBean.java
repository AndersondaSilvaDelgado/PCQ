package br.com.usinasantafe.pcq.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcq.model.pst.Entidade;

@DatabaseTable(tableName="tbbrigadistaest")
public class BrigadistaBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idFuncBrigadista;
    @DatabaseField
    private Long matricBrigadista;
    @DatabaseField
    private String nomeBrigadista;

    public BrigadistaBean() {
    }

    public Long getIdFuncBrigadista() {
        return idFuncBrigadista;
    }

    public void setIdFuncBrigadista(Long idFuncBrigadista) {
        this.idFuncBrigadista = idFuncBrigadista;
    }

    public Long getMatricBrigadista() {
        return matricBrigadista;
    }

    public void setMatricBrigadista(Long matricBrigadista) {
        this.matricBrigadista = matricBrigadista;
    }

    public String getNomeBrigadista() {
        return nomeBrigadista;
    }

    public void setNomeBrigadista(String nomeBrigadista) {
        this.nomeBrigadista = nomeBrigadista;
    }
}
