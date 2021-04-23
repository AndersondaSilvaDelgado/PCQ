package br.com.usinasantafe.pcq.model.dao;

import java.util.List;

import br.com.usinasantafe.pcq.model.bean.estaticas.SecaoBean;

public class SecaoDAO {

    public SecaoDAO() {
    }

    public boolean verSecao(Long codSecao){
        List<SecaoBean> secaoList = secaoCodList(codSecao);
        boolean ret = secaoList.size() > 0;
        secaoList.clear();
        return ret;
    }

    public SecaoBean getCodSecao(Long codSecao){
        List<SecaoBean> secaoList = secaoCodList(codSecao);
        SecaoBean secaoBean = secaoList.get(0);
        secaoList.clear();
        return secaoBean;
    }

    public SecaoBean getIdSecao(Long idSecao){
        List<SecaoBean> secaoList = secaoIdList(idSecao);
        SecaoBean secaoBean = secaoList.get(0);
        secaoList.clear();
        return secaoBean;
    }

    private List<SecaoBean> secaoCodList(Long codSecao){
        SecaoBean secaoBean = new SecaoBean();
        return secaoBean.get("codSecao", codSecao);
    }

    private List<SecaoBean> secaoIdList(Long idSecao){
        SecaoBean secaoBean = new SecaoBean();
        return secaoBean.get("idSecao", idSecao);
    }

}
