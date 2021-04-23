package br.com.usinasantafe.pcq.model.dao;

import java.util.List;

import br.com.usinasantafe.pcq.model.bean.estaticas.TercCombBean;

public class TercCombDAO {

    public TercCombDAO() {
    }

    public List<TercCombBean> tercCombList(){
        TercCombBean tercCombBean = new TercCombBean();
        return tercCombBean.all();
    }

    public TercCombBean getTercComb (Long idTercComb){
        TercCombBean tercCombBean = new TercCombBean();
        List<TercCombBean> origemFogoList = tercCombBean.get("idTercComb", idTercComb);
        tercCombBean = origemFogoList.get(0);
        origemFogoList.clear();
        return tercCombBean;
    }

}
