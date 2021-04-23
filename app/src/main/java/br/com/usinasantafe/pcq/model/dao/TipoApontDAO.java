package br.com.usinasantafe.pcq.model.dao;

import java.util.List;

import br.com.usinasantafe.pcq.model.bean.estaticas.BrigadistaBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.TipoApontBean;

public class TipoApontDAO {

    public TipoApontDAO() {
    }

    public List<TipoApontBean> tipoApontList(){
        TipoApontBean tipoApontBean = new TipoApontBean();
        return tipoApontBean.all();
    }

    public TipoApontBean getTipoApont(Long idTipoApont){
        TipoApontBean tipoApontBean = new TipoApontBean();
        List<TipoApontBean> tipoApontList = tipoApontBean.get("idTipoApont", idTipoApont);
        tipoApontBean = tipoApontList.get(0);
        tipoApontList.clear();
        return tipoApontBean;
    }

}
