package br.com.usinasantafe.pcq.model.dao;

import java.util.List;

import br.com.usinasantafe.pcq.model.bean.estaticas.OrigemFogoBean;

public class OrigemFogoDAO {

    public OrigemFogoDAO() {
    }

    public List<OrigemFogoBean> origemFogoList(){
        OrigemFogoBean origemFogoBean = new OrigemFogoBean();
        return origemFogoBean.all();
    }

    public OrigemFogoBean getOrigemFogo(Long idOrigemFogo){
        OrigemFogoBean origemFogoBean = new OrigemFogoBean();
        List<OrigemFogoBean> origemFogoList = origemFogoBean.get("idOrigemFogo", idOrigemFogo);
        origemFogoBean = origemFogoList.get(0);
        origemFogoList.clear();
        return origemFogoBean;
    }

}
