package br.com.usinasantafe.pcq.model.dao;

import java.util.List;

import br.com.usinasantafe.pcq.model.bean.estaticas.RespBean;

public class RespDAO {

    public RespDAO() {
    }

    public List<RespBean> respIdQuestaoList(Long idQuestao){
        RespBean respBean = new RespBean();
        return respBean.get("idQuestao", idQuestao);
    }

    public List<RespBean> respIdRespList(Long idResp){
        RespBean respBean = new RespBean();
        return respBean.get("idResp", idResp);
    }

    public RespBean getResp(Long idResp){
        List<RespBean> respList = respIdRespList(idResp);
        RespBean respBean = respList.get(0);
        respList.clear();
        return respBean;
    }

}
