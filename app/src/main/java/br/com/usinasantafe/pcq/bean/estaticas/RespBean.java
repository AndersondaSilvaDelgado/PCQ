package br.com.usinasantafe.pcq.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcq.pst.Entidade;

@DatabaseTable(tableName="tbrespest")
public class RespBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idResp;
    @DatabaseField
    private Long idQuestao;
    @DatabaseField
    private Long idSubResp;
    @DatabaseField
    private Long seqResp;
    @DatabaseField
    private String descrResp;

    public RespBean() {
    }

    public Long getIdResp() {
        return idResp;
    }

    public void setIdResp(Long idResp) {
        this.idResp = idResp;
    }

    public Long getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(Long idQuestao) {
        this.idQuestao = idQuestao;
    }

    public Long getIdSubResp() {
        return idSubResp;
    }

    public void setIdSubResp(Long idSubResp) {
        this.idSubResp = idSubResp;
    }

    public Long getSeqResp() {
        return seqResp;
    }

    public void setSeqResp(Long seqResp) {
        this.seqResp = seqResp;
    }

    public String getDescrResp() {
        return descrResp;
    }

    public void setDescrResp(String descrResp) {
        this.descrResp = descrResp;
    }
}
