package br.com.usinasantafe.pcq.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.model.bean.estaticas.BrigadistaBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.RespBean;
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
        return brigadistaBean.orderBy("nomeBrigadista", true);
    }

    public List<BrigadistaItemBean> brigadistaItemList(Long idCabec){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        BrigadistaItemBean brigadistaItemBean = new BrigadistaItemBean();
        return brigadistaItemBean.get(pesqArrayList);
    }

    public void setBrigadistaCabec(ArrayList<Long> brigadistaCabec, Long idCabec){
        delBrigadista(idCabec);
        for(int i = 0; i < brigadistaCabec.size(); i++){
            BrigadistaItemBean brigadistaItemBean = new BrigadistaItemBean();
            brigadistaItemBean.setIdCabec(idCabec);
            brigadistaItemBean.setIdFunc(brigadistaCabec.get(i));
            brigadistaItemBean.setDthrBrigadista(Tempo.getInstance().dthrAtualString());
            brigadistaItemBean.insert();
        }
    }

    public void delBrigadista(Long idCabec){
        BrigadistaItemBean brigadistaItemBean = new BrigadistaItemBean();
        List<BrigadistaItemBean> brigadistaItemList = brigadistaItemBean.get("idCabec", idCabec);
        for (BrigadistaItemBean brigadistaItemBD : brigadistaItemList) {
            brigadistaItemBD.delete();
        }
        brigadistaItemList.clear();
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

    public ArrayList<String> brigadistaItemAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("BRIGADISTA");
        BrigadistaItemBean brigadistaItemBean = new BrigadistaItemBean();
        List<BrigadistaItemBean> brigadistaItemList = brigadistaItemBean.orderBy("idBrigadistaItem", true);
        for (BrigadistaItemBean brigadistaItemBeanBD : brigadistaItemList) {
            dadosArrayList.add(dadosBrigadista(brigadistaItemBeanBD));
        }
        brigadistaItemList.clear();
        return dadosArrayList;
    }

    private String dadosBrigadista(BrigadistaItemBean brigadistaItemBean){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(brigadistaItemBean, brigadistaItemBean.getClass()).toString();
    }

}
