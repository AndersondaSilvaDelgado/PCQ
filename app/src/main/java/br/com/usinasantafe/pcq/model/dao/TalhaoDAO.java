package br.com.usinasantafe.pcq.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.model.bean.variaveis.TalhaoItemBean;
import br.com.usinasantafe.pcq.util.Tempo;

public class TalhaoDAO {

    public TalhaoDAO() {
    }

    public void setTalhaoCabec(ArrayList<Long> talhaoCabec, Long idCabec){
        for(int i = 0; i < talhaoCabec.size(); i++){
            TalhaoItemBean talhaoItemBean = new TalhaoItemBean();
            talhaoItemBean.setIdCabec(idCabec);
            talhaoItemBean.setIdTalhao(talhaoCabec.get(i));
            talhaoItemBean.setDthrTalhao(Tempo.getInstance().dataComHora());
            talhaoItemBean.insert();
        }
    }

    public JsonArray dadosEnvioTalhao(Long idCabec){
        TalhaoItemBean talahoBean = new TalhaoItemBean();
        List talhaoList = talahoBean.get("idCabec", idCabec);
        JsonArray talhaoJsonArray = new JsonArray();
        for (int i = 0; i < talhaoList.size(); i++) {
            talahoBean = (TalhaoItemBean) talhaoList.get(i);
            Gson cabecGson = new Gson();
            talhaoJsonArray.add(cabecGson.toJsonTree(talahoBean, talahoBean.getClass()));
        }
        talhaoList.clear();
        return talhaoJsonArray;
    }

    public void delTalhao(Long idCabec){
        TalhaoItemBean talahoBean = new TalhaoItemBean();
        List talhaoList = talahoBean.get("idCabec", idCabec);
        for (int i = 0; i < talhaoList.size(); i++) {
            talahoBean = (TalhaoItemBean) talhaoList.get(i);
            talahoBean.delete();
        }
        talhaoList.clear();
    }

}
