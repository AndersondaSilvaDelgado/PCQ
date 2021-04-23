package br.com.usinasantafe.pcq.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcq.model.pst.Entidade;

@DatabaseTable(tableName="tbtipoapontest")
public class TipoApontBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idTipoApont;
    @DatabaseField
    private String descrTipoApont;

    public TipoApontBean() {
    }

    public Long getIdTipoApont() {
        return idTipoApont;
    }

    public void setIdTipoApont(Long idTipoApont) {
        this.idTipoApont = idTipoApont;
    }

    public String getDescrTipoApont() {
        return descrTipoApont;
    }

    public void setDescrTipoApont(String descrTipoApont) {
        this.descrTipoApont = descrTipoApont;
    }
}
