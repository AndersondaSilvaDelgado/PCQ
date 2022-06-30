package br.com.usinasantafe.pcq.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.model.bean.estaticas.QuestaoBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.RespBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.CabecBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.RespItemBean;
import br.com.usinasantafe.pcq.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pcq.util.Tempo;

public class RespostaDAO {

    public RespostaDAO() {
    }

    public List<QuestaoBean> questaoList(){
        QuestaoBean questaoBean = new QuestaoBean();
        return questaoBean.orderBy("seqQuestao",true);
    }

    public QuestaoBean getQuestao(Long idQuestao){
        QuestaoBean questaoBean = new QuestaoBean();
        List<QuestaoBean> questaoList = questaoBean.get("idQuestao", idQuestao);
        questaoBean = questaoList.get(0);
        questaoList.clear();
        return questaoBean;
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

    public void salvarAtualizarItem(RespItemBean respItemBean, Long idCabec){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        pesqArrayList.add(getPesqIdQuestao(respItemBean.getIdQuestao()));
        List<RespItemBean> itemList = respItemBean.get(pesqArrayList);
        for (RespItemBean respItemBeanBD : itemList) {
            respItemBeanBD.delete();
        }
        itemList.clear();
        respItemBean.setIdCabec(idCabec);
        respItemBean.setDthrItem(Tempo.getInstance().dthrAtualString());
        respItemBean.insert();
    }

    public List<RespItemBean> respItemList(Long idCabec){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        RespItemBean respItemBean = new RespItemBean();
        return respItemBean.getAndOrderBy(pesqArrayList, "seqQuestao",true);
    }

    public void delItem(Long idCabec){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        RespItemBean respItemBean = new RespItemBean();
        List<RespItemBean> itemList = respItemBean.get(pesqArrayList);
        for (RespItemBean respItemBeanBD : itemList) {
            respItemBeanBD.delete();
        }
        itemList.clear();
    }

    private EspecificaPesquisa getPesqIdCabec(Long idCabec){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabec");
        pesquisa.setValor(idCabec);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdQuestao(Long idQuestao){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idQuestao");
        pesquisa.setValor(idQuestao);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    public ArrayList<String> respostaAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("RESPOSTA");
        RespBean respBean = new RespBean();
        List<RespBean> cabecList = respBean.orderBy("idResp", true);
        for (RespBean respBeanBD : cabecList) {
            dadosArrayList.add(dadosResp(respBeanBD));
        }
        cabecList.clear();
        return dadosArrayList;
    }

    private String dadosResp(RespBean respBean){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(respBean, respBean.getClass()).toString();
    }

}
