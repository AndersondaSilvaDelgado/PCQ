package br.com.usinasantafe.pcq.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcq.pst.Entidade;

@DatabaseTable(tableName="tborgaoambitemvar")
public class OrgaoAmbItemBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idItemOrgAmb;
    @DatabaseField
    private Long idCabec;
    @DatabaseField
    private Long idOrgAmb;
    @DatabaseField
    private String dthrOrgAmb;

    public OrgaoAmbItemBean() {
    }

    public Long getIdItemOrgAmb() {
        return idItemOrgAmb;
    }

    public void setIdItemOrgAmb(Long idItemOrgAmb) {
        this.idItemOrgAmb = idItemOrgAmb;
    }

    public Long getIdCabec() {
        return idCabec;
    }

    public void setIdCabec(Long idCabec) {
        this.idCabec = idCabec;
    }

    public Long getIdOrgAmb() {
        return idOrgAmb;
    }

    public void setIdOrgAmb(Long idOrgAmb) {
        this.idOrgAmb = idOrgAmb;
    }

    public String getDthrOrgAmb() {
        return dthrOrgAmb;
    }

    public void setDthrOrgAmb(String dthrOrgAmb) {
        this.dthrOrgAmb = dthrOrgAmb;
    }
}
