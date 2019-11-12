package br.com.usinasantafe.pcq.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcq.pst.Entidade;

@DatabaseTable(tableName="tbsubitemcritvar")
public class SubItemCriterioBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idSubItemCriterio;
    @DatabaseField
    private Long idItemCriterio;
    @DatabaseField
    private Long idResp;
    @DatabaseField
    private String dthrSubItemCriterio;

    public SubItemCriterioBean() {
    }

    public Long getIdSubItemCriterio() {
        return idSubItemCriterio;
    }

    public void setIdSubItemCriterio(Long idSubItemCriterio) {
        this.idSubItemCriterio = idSubItemCriterio;
    }

    public Long getIdItemCriterio() {
        return idItemCriterio;
    }

    public void setIdItemCriterio(Long idItemCriterio) {
        this.idItemCriterio = idItemCriterio;
    }

    public Long getIdResp() {
        return idResp;
    }

    public void setIdResp(Long idResp) {
        this.idResp = idResp;
    }

    public String getDthrSubItemCriterio() {
        return dthrSubItemCriterio;
    }

    public void setDthrSubItemCriterio(String dthrSubItemCriterio) {
        this.dthrSubItemCriterio = dthrSubItemCriterio;
    }
}
