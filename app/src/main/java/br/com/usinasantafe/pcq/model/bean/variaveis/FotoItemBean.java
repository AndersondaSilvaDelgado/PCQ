package br.com.usinasantafe.pcq.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcq.model.pst.Entidade;

@DatabaseTable(tableName="tbfotovar")
public class FotoItemBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idFotoItem;
    @DatabaseField
    private Long idCabec;
    @DatabaseField
    private String foto;
    @DatabaseField
    private Long tipoFoto;
    @DatabaseField
    private String dthrFoto;

    public FotoItemBean() {
    }

    public Long getIdFotoItem() {
        return idFotoItem;
    }

    public void setIdFotoItem(Long idFotoItem) {
        this.idFotoItem = idFotoItem;
    }

    public Long getIdCabec() {
        return idCabec;
    }

    public void setIdCabec(Long idCabec) {
        this.idCabec = idCabec;
    }

    public String getDthrFoto() {
        return dthrFoto;
    }

    public void setDthrFoto(String dthrFoto) {
        this.dthrFoto = dthrFoto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Long getTipoFoto() {
        return tipoFoto;
    }

    public void setTipoFoto(Long tipoFoto) {
        this.tipoFoto = tipoFoto;
    }
}
