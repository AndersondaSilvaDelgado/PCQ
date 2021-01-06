package br.com.usinasantafe.pcq.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcq.model.pst.Entidade;

@DatabaseTable(tableName="tbquestest")
public class QuestaoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idQuestao;
    @DatabaseField
    private Long seqQuestao;
    @DatabaseField
    private String descrQuestao;

    public QuestaoBean() {
    }

    public Long getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(Long idQuestao) {
        this.idQuestao = idQuestao;
    }

    public Long getSeqQuestao() {
        return seqQuestao;
    }

    public void setSeqQuestao(Long seqQuestao) {
        this.seqQuestao = seqQuestao;
    }

    public String getDescrQuestao() {
        return descrQuestao;
    }

    public void setDescrQuestao(String descrQuestao) {
        this.descrQuestao = descrQuestao;
    }
}
