package br.com.usinasantafe.pcq.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.model.bean.estaticas.BrigadistaBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.BrigadistaItemBean;
import br.com.usinasantafe.pcq.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pcq.util.Tempo;

public class BrigadistaDAO {

    public BrigadistaDAO() {
    }

    public BrigadistaBean getBrigadista(Long idFunc){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdFunc(idFunc));
        BrigadistaBean brigadistaBean = new BrigadistaBean();
        List<BrigadistaBean> brigadistaList = brigadistaBean.get(pesqArrayList);
        brigadistaBean = brigadistaList.get(0);
        brigadistaList.clear();
        return brigadistaBean;
    }

    public List<BrigadistaBean> brigadistaList(){
        BrigadistaBean brigadistaBean = new BrigadistaBean();
        return brigadistaBean.all();
    }

    public List<BrigadistaItemBean> brigadistaItemList(Long idCabec){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        BrigadistaItemBean brigadistaItemBean = new BrigadistaItemBean();
        return brigadistaItemBean.get(pesqArrayList);
    }

    private EspecificaPesquisa getPesqIdFunc(Long idFuncBrigadista){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idFuncBrigadista");
        pesquisa.setValor(idFuncBrigadista);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdCabec(Long idCabec){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabec");
        pesquisa.setValor(idCabec);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    public void setBrigadistaCabec(ArrayList<Long> brigadistaCabec, Long idCabec){
        for(int i = 0; i < brigadistaCabec.size(); i++){
            BrigadistaItemBean brigadistaItemBean = new BrigadistaItemBean();
            brigadistaItemBean.setIdCabec(idCabec);
            brigadistaItemBean.setIdFunc(brigadistaCabec.get(i));
            brigadistaItemBean.setDthrBrigadista(Tempo.getInstance().dataComHora());
            brigadistaItemBean.insert();
        }
    }

    public JsonArray dadosEnvioBrigadista(Long idCabec){
        BrigadistaItemBean brigadistaItemBean = new BrigadistaItemBean();
        List<BrigadistaItemBean> brigadistaList = brigadistaItemBean.get("idCabec", idCabec);
        JsonArray brigadistaJsonArray = new JsonArray();
        for (int i = 0; i < brigadistaList.size(); i++) {
            brigadistaItemBean = brigadistaList.get(i);
            Gson cabecGson = new Gson();
            brigadistaJsonArray.add(cabecGson.toJsonTree(brigadistaItemBean, brigadistaItemBean.getClass()));
        }
        brigadistaList.clear();
        return brigadistaJsonArray;
    }

}
