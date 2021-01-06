package br.com.usinasantafe.pcq.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcq.model.pst.Entidade;

@DatabaseTable(tableName="tbtalhaoitemvar")
public class TalhaoItemBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idItemTalhao;
    @DatabaseField
    private Long idCabec;
    @DatabaseField
    private Long idTalhao;
    @DatabaseField
    private String dthrTalhao;

    public TalhaoItemBean() {
    }

    public Long getIdItemTalhao() {
        return idItemTalhao;
    }

    public void setIdItemTalhao(Long idItemTalhao) {
        this.idItemTalhao = idItemTalhao;
    }

    public Long getIdCabec() {
        return idCabec;
    }

    public void setIdCabec(Long idCabec) {
        this.idCabec = idCabec;
    }

    public Long getIdTalhao() {
        return idTalhao;
    }

    public void setIdTalhao(Long idTalhao) {
        this.idTalhao = idTalhao;
    }

    public String getDthrTalhao() {
        return dthrTalhao;
    }

    public void setDthrTalhao(String dthrTalhao) {
        this.dthrTalhao = dthrTalhao;
    }
}