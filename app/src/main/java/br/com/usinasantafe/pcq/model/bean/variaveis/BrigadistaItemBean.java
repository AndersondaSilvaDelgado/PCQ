package br.com.usinasantafe.pcq.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcq.model.pst.Entidade;

@DatabaseTable(tableName="tbbrigaditemvar")
public class BrigadistaItemBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idBrigadistaItem;
    @DatabaseField
    private Long idCabec;
    @DatabaseField
    private Long idFunc;
    @DatabaseField
    private String dthrBrigadista;

    public BrigadistaItemBean() {
    }

    public Long getIdBrigadistaItem() {
        return idBrigadistaItem;
    }

    public void setIdBrigadistaItem(Long idBrigadistaItem) {
        this.idBrigadistaItem = idBrigadistaItem;
    }

    public Long getIdCabec() {
        return idCabec;
    }

    public void setIdCabec(Long idCabec) {
        this.idCabec = idCabec;
    }

    public Long getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(Long idFunc) {
        this.idFunc = idFunc;
    }

    public String getDthrBrigadista() {
        return dthrBrigadista;
    }

    public void setDthrBrigadista(String dthrBrigadista) {
        this.dthrBrigadista = dthrBrigadista;
    }
}
