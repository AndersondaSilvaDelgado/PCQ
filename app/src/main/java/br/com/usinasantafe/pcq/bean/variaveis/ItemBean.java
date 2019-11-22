package br.com.usinasantafe.pcq.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcq.pst.Entidade;

@DatabaseTable(tableName="tbitemcritvar")
public class ItemBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idItem;
    @DatabaseField
    private Long idCabec;
    @DatabaseField
    private Long idQuestao;
    @DatabaseField
    private Long idResp;
    @DatabaseField
    private Long idSubResp;
    @DatabaseField
    private String dthrItem;

    public ItemBean() {
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
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

    public String getDthrItem() {
        return dthrItem;
    }

    public void setDthrItem(String dthrItem) {
        this.dthrItem = dthrItem;
    }

    public Long getIdSubResp() {
        return idSubResp;
    }

    public void setIdSubResp(Long idSubResp) {
        this.idSubResp = idSubResp;
    }
}
