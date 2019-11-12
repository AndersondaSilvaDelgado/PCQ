package br.com.usinasantafe.pcq.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcq.pst.Entidade;

@DatabaseTable(tableName="tbitemcritvar")
public class ItemCriterioBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idItemCriterio;
    @DatabaseField
    private Long idCabec;
    @DatabaseField
    private Long idQuestao;
    @DatabaseField
    private Long idResp;
    @DatabaseField
    private String dthrItemCriterio;

    public ItemCriterioBean() {
    }

    public Long getIdItemCriterio() {
        return idItemCriterio;
    }

    public void setIdItemCriterio(Long idItemCriterio) {
        this.idItemCriterio = idItemCriterio;
    }

    public Long getIdCabec() {
        return idCabec;
    }

    public void setIdCabec(Long idCabec) {
        this.idCabec = idCabec;
    }

    public Long getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(Long idQuestao) {
        this.idQuestao = idQuestao;
    }

    public Long getIdResp() {
        return idResp;
    }

    public void setIdResp(Long idResp) {
        this.idResp = idResp;
    }

    public String getDthrItemCriterio() {
        return dthrItemCriterio;
    }

    public void setDthrItemCriterio(String dthrItemCriterio) {
        this.dthrItemCriterio = dthrItemCriterio;
    }
}
