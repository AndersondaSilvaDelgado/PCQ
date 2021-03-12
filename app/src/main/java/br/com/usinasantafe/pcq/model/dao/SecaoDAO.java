package br.com.usinasantafe.pcq.model.dao;

import java.util.List;

import br.com.usinasantafe.pcq.model.bean.estaticas.SecaoBean;

public class SecaoDAO {

    public SecaoDAO() {
    }

    public boolean verSecao(Long codSecao){
        List<SecaoBean> secaoList = secaoList(codSecao);
        boolean ret = secaoList.size() > 0;
        secaoList.clear();
        return ret;
    }

    public SecaoBean getSecao(Long codSecao){
        List<SecaoBean> secaoList = secaoList(codSecao);
        SecaoBean secaoBean = secaoList.get(0);
        secaoList.clear();
        return secaoBean;
    }

    private List<SecaoBean> secaoList(Long codSecao){
        SecaoBean secaoBean = new SecaoBean();
        return secaoBean.get("codSecao", codSecao);
    }

}
